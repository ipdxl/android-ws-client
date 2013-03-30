/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html * 
 * 
 */
package org.jinouts.ws.util;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.wsdl.Binding;
import javax.wsdl.BindingOperation;
import javax.wsdl.Definition;
import javax.wsdl.extensions.soap.SOAPBinding;
import javax.wsdl.extensions.soap12.SOAP12Binding;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;

import com.eviware.soapui.impl.wsdl.support.Constants;
import com.eviware.soapui.impl.wsdl.support.soap.SoapVersion;
import com.eviware.soapui.impl.wsdl.support.wsdl.WsdlUtils;

/**
* @author asraf
* asraf344@gmail.com
* 
*/
public class JinosWSUtil
{
	public static SoapVersion getVersionForWSDL( String wsdlURL, String bindingName ) throws Exception
	{				
		// Steps :
		//1.  Get the definition
		// 2 detect the version from here
		//3.Get the soap builder
		//4. build the message
		
		WSDLFactory factory = WSDLFactory.newInstance();
		WSDLReader wsdlReader = factory.newWSDLReader();
		Definition definition =  wsdlReader.readWSDL ( wsdlURL );
		
		return getVersionForWSDL ( definition, bindingName );
	}
	
	public static SoapVersion getVersionForWSDL( Definition definition, String bindingName ) throws Exception
	{				
		// Steps :
		//1.  Get the definition
		// 2 detect the version from here
		//3.Get the soap builder
		//4. build the message		
				
		//System.out.println ("def Namespace: " + definition.getNamespaces ( ) );
		
		Binding binding = getBindingOperation ( definition, bindingName );
		
		if ( binding != null )
		{
			if ( canImportSoap11 ( binding ) )
			{
				return SoapVersion.Soap11;
			}
			
		}
		
		return  SoapVersion.Soap12;
	}
	
	public static String getNameSpaceFromDefinition ( Definition definition )
	{
		// 
		String appNameSpace = (String) definition.getNamespaces ( ).get ( JinosConstants.NAMESPACE_TNS );
		
		if ( appNameSpace == null  )
		{
			appNameSpace = (String) definition.getNamespaces ( ).get ( JinosConstants.NAMESPACE_NS );
		}
		
		return appNameSpace;
	}
	
	static  Binding getBindingOperation ( Definition definition, String bindingName )
	{
		Map<?, ?> bindingMap = definition.getAllBindings();
		
		if( !bindingMap.isEmpty() )
		{
			Iterator<?> i = bindingMap.values().iterator();
			while( i.hasNext() )
			{
				Binding binding = ( Binding )i.next();
				
				List<BindingOperation> bl = binding.getBindingOperations ( );
				for ( BindingOperation bo : bl)
				{
					//System.out.println ("binding opetion name: " + bo.getName ( ));
					
					// return the binding name
					if ( bo != null && bo.getName ( ).equalsIgnoreCase ( bindingName ) )
					{
						return binding;
					}				
					
				}		
								
			}
		}
		
		return null;
	}
	
	static boolean canImportSoap11( Binding binding )
	{
		List<?> list = binding.getExtensibilityElements();
		SOAPBinding soapBinding = WsdlUtils.getExtensiblityElement( list, SOAPBinding.class );
		if ( soapBinding != null)
		{
			System.out.println ("soap Binding: " + soapBinding.getTransportURI ( ) );
		}
		
		return soapBinding == null ? false
				: ( soapBinding.getTransportURI().startsWith( Constants.SOAP_HTTP_TRANSPORT ) || soapBinding
						.getTransportURI().startsWith( Constants.SOAP_MICROSOFT_TCP ) );
	}
	
	
	public static boolean canImportSoap12( Binding binding )
	{
		List<?> list = binding.getExtensibilityElements();
		SOAP12Binding soapBinding = WsdlUtils.getExtensiblityElement( list, SOAP12Binding.class );
		return soapBinding == null ? false : soapBinding.getTransportURI().startsWith( Constants.SOAP_HTTP_TRANSPORT )
				|| soapBinding.getTransportURI().startsWith( Constants.SOAP12_HTTP_BINDING_NS )
				|| soapBinding.getTransportURI().startsWith( Constants.SOAP_MICROSOFT_TCP );
	}
	
	public static  Annotation getAnnotationByNameFromAnnotationArr ( Annotation[] annotationArr, String name )
	{
		 Annotation annotationToReturn = null;
		
		if ( annotationArr != null && annotationArr.length > 0 )
		{
			for ( Annotation annotation : annotationArr )
			{
				//System.out.println ( annotation.annotationType ( ) );
				if (  annotation.annotationType ( ).toString ( ).contains ( name)  )
				{
					annotationToReturn =   annotation;
				
				}
			}
		}
		
		return annotationToReturn;
	}
		
		
}
