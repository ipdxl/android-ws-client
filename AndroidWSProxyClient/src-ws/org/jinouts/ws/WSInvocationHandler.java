/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html * 
 * 
 */
package org.jinouts.ws;

import hu.javaforum.android.androidsoap.GenericHandler;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;

import org.jinouts.jws.WebMethod;
import org.jinouts.jws.WebParam;
import org.jinouts.transport.HttpTransportUtil;
import org.jinouts.ws.util.JinosWSUtil;
import org.jinouts.ws.util.WSRequestXMLUtils;
import org.jinouts.ws.util.XMLConstants;
import org.jinouts.ws.util.XMLReflectionUtil;
import org.jinouts.ws.util.XMLUtils;
import org.jinouts.xml.namespace.QName;
import org.jinouts.xml.ws.RequestWrapper;
import org.jinouts.xml.ws.ResponseWrapper;

import com.eviware.soapui.impl.wsdl.support.soap.SoapVersion;

/**
* @author asraf
* asraf344@gmail.com
* 
*/
public class WSInvocationHandler implements InvocationHandler
{
	private URL wsdlLocation;
	private QName serviceName;

	public WSInvocationHandler(URL wsdlLocation, QName serviceName)
	{
		this.wsdlLocation = wsdlLocation;
		this.serviceName = serviceName;
	}

	@Override
	public Object invoke ( Object proxy, Method method, Object[] args )
			throws Throwable
	{
		System.out.println ( method.getName ( ) );

		WSDLFactory factory = WSDLFactory.newInstance ( );
		WSDLReader wsdlReader = factory.newWSDLReader ( );
		Definition definition = wsdlReader.readWSDL ( wsdlLocation.toString ( ) );

		String appNameSpace = JinosWSUtil.getNameSpaceFromDefinition( definition );
		//System.out.println ( "def Namespace: " + definition.getNamespaces ( ) );
		//System.out.println ( "App namespace: " + appNameSpace );

		// get the soap Version
		SoapVersion soapVersion = JinosWSUtil.getVersionForWSDL ( definition, method.getName ( ) );
		String bodyXML = getSoapBodyXML ( method, args );
		String requestXML = XMLUtils.getFullRequestXmlFromBody ( soapVersion, bodyXML, appNameSpace );

		System.out.println ( "Full Request XML Is " + requestXML );

		
		// now get the response wrapper class
		Annotation respWrapperAnnotation = method.getAnnotation ( ResponseWrapper.class ); //JinosWSUtil.getAnnotationByNameFromAnnotationArr ( methodAnnotationArr, JinosConstants.RESPONSE_WRAPPER );
		
		Class respClazz = null;
		if ( respWrapperAnnotation != null )
		{
			ResponseWrapper respWrapper = (ResponseWrapper) respWrapperAnnotation;
			String className = respWrapper.className ( );
			System.out.println ( "Response Class: " + className );
			try
			{
				respClazz = Class.forName ( className );
			}			
			catch ( Exception ex )
			{
				ex.printStackTrace ( );
			}
		}
		
		
		String wrapperResultName = getWrapperResultName ( respClazz );	
		// now Get the response
		String responseXML = HttpTransportUtil.sendRequestAndGetRespXML ( requestXML, wsdlLocation.toString ( ) );
		GenericHandler genericHandler = new GenericHandler ( wrapperResultName, respClazz, "android" );
		genericHandler.parseWithPullParser(responseXML);
		Object obj = genericHandler.getObject ( );
		
		//Object responseObj = ReflectionHelper.invokeGetter ( obj, wrapperResultName );
		Object responseObj = XMLReflectionUtil.invokeGetter( obj, wrapperResultName );
		
		return responseObj;
	}

	private String getWrapperResultName ( Class respClazz)
	{
		Field[] responseFields = respClazz.getDeclaredFields ( );
		Field firstField = responseFields[0];
		String wrapperResultName = XMLReflectionUtil.getFieldNameForXML(firstField);
		
		
		return wrapperResultName;
		
	}
	private String getSoapBodyXML ( Method method, Object[] argumentsArray )
	{
		// get the method parameters annotation
		Annotation[][] paramAnnotationArr = method.getParameterAnnotations ( );

		Map<String, Object> methodParamNameValueMap = null;
		Map<String, String> paramNameNamespaceMap = new HashMap<String, String> ( );
		
		if ( paramAnnotationArr != null && paramAnnotationArr.length > 0 )
		{
			methodParamNameValueMap = getMethodParamNameValueMap ( paramAnnotationArr, argumentsArray,  paramNameNamespaceMap );
		}

		// string builder to append the xml
		StringBuilder sb = new StringBuilder ( );

		String wsOperationName = getWSOperationNameFromMethod ( method );

		sb.append ( XMLUtils.getXMLStartTag ( wsOperationName ) );

		if ( methodParamNameValueMap != null )
		{
			for ( Map.Entry<String, Object> entry : methodParamNameValueMap.entrySet ( ) )
			{
				String paramName = entry.getKey ( );
				String namespaceInitial =  getNameSpaceInitialForParamName( paramName, paramNameNamespaceMap );
				sb.append ( WSRequestXMLUtils.getXMLForKeyValue ( entry.getValue ( ) , paramName, namespaceInitial,  0 ) );
			}
		}

		sb.append ( XMLUtils.getXMLEndTag ( wsOperationName ) );

		
		// free resources
		paramAnnotationArr = null;
		methodParamNameValueMap = null;
		
		// now return xml
		return sb.toString ( );
	}

	private String getNameSpaceInitialForParamName ( String paramName, Map<String, String> paramNameNamespaceMap )
	{
		String namespaceInitial = paramNameNamespaceMap.get ( paramName );
		
		if ( namespaceInitial != null )
		{
			return XMLConstants.SOAP_WS_TAG_INITIAL;
		}
		
		return namespaceInitial;
	}
	
	private Map<String, Object> getMethodParamNameValueMap ( Annotation[][] paramAnnotationArr, Object[] argumentsArray, Map<String, String> paramNameNamespaceMap )
	{
		Map<String, Object> methodParamNameValueMap = new HashMap<String, Object> ( );

		try
		{
			for ( int i = 0; i < paramAnnotationArr.length; i++ )
			{
				Annotation[] annotationArr = paramAnnotationArr[i];
				if ( annotationArr.length > 0 )
				{
					//System.out.println ( annotationArr[0].toString ( ) );
					Annotation an = annotationArr[0];
					WebParam wp = (WebParam) an;
					// get the name and name space
					String paramName = wp.name ( );
					String targetNameSpace = wp.targetNamespace ( );
					//System.out.println ( paramName );
					methodParamNameValueMap.put ( paramName, argumentsArray[i] );
					if ( targetNameSpace != null && !targetNameSpace.isEmpty ( ) )
					{
						paramNameNamespaceMap.put ( paramName, targetNameSpace );
					}
					
				}

			}
		}
		catch ( Throwable ex )
		{
			ex.printStackTrace ( );

		}

		return methodParamNameValueMap;
	}

	// TODO: to be moved to another class
	

	private String getWSOperationNameFromMethod ( Method method )
	{
		String operationName = null;
		
			String operationNameFromWebMethod = null;
			String operationNameFromReqWrapper = null;

			Annotation webMethodAnnotation = method.getAnnotation ( WebMethod.class );//JinosWSUtil.getAnnotationByNameFromAnnotationArr ( methodAnnotationArr, JinosConstants.WEB_METHOD );
			if ( webMethodAnnotation != null )
			{
				WebMethod webMethod = (WebMethod) webMethodAnnotation;
				operationNameFromWebMethod = webMethod.operationName ( );
				if ( operationNameFromWebMethod != null
						&& !operationNameFromWebMethod.isEmpty ( ) )
				{
					operationName = operationNameFromWebMethod;
				}
			}
			// if operation name is still null get it from req wrapper
			if ( operationName == null )
			{
				Annotation reqWrapperAnnotation = method.getAnnotation ( RequestWrapper.class ); //JinosWSUtil.getAnnotationByNameFromAnnotationArr ( methodAnnotationArr, JinosConstants.REQUEST_WRAPPER );
				if ( reqWrapperAnnotation != null )
				{
					RequestWrapper reqWrapper = (RequestWrapper) reqWrapperAnnotation;
					operationNameFromReqWrapper = reqWrapper.localName ( );
					if ( operationNameFromReqWrapper != null
							&& !operationNameFromReqWrapper.isEmpty ( ) )
					{
						operationName = operationNameFromReqWrapper;
					}
					else
					{
						operationName = method.getName ( );
					}
				}
				else
				{
					operationName = method.getName ( );
				}

			}

		

		return operationName;
	}

}
