/**
 * 
 */
package org.jinos.ws;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.wsdl.Definition;
import javax.wsdl.factory.WSDLFactory;
import javax.wsdl.xml.WSDLReader;
import org.jinos.xml.namespace.QName;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.jinos.jws.WebMethod;
import org.jinos.jws.WebParam;
import org.jinos.ws.util.JinosWSUtil;
import org.jinos.ws.util.XMLConstants;
import org.jinos.ws.util.XMLUtils;

import android.util.Log;

import com.eviware.soapui.impl.wsdl.support.soap.SoapVersion;

/**
 * @author asraf
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
		
		
		
		WSDLFactory factory = WSDLFactory.newInstance();
		WSDLReader wsdlReader = factory.newWSDLReader();
		Definition definition =  wsdlReader.readWSDL ( wsdlLocation.toString ( ) );
		
		String appNameSpace = (String)definition.getNamespaces ( ).get ( "tns" );
		System.out.println ("def Namespace: " + definition.getNamespaces ( ) );
		System.out.println ("App namespace: " + appNameSpace );
		
		// get the soap Version
		SoapVersion soapVersion = JinosWSUtil.getVersionForWSDL ( definition, method.getName ( ) );
		String bodyXML = getSoapBodyXML( method, args );
		String requestXML = getFullRequestXmlFromBody ( soapVersion, bodyXML, appNameSpace ); 
		
		Log.i ("WSInvocationHandler: ","Request XML Is " + requestXML );
		
		// now call the http to send the request
		//HttpTransport httpTransport = new HttpTransport (  wsdlLocation.toString ( ) );
		//httpTransport.call ( requestXML, null );
		sendRequest ( requestXML, wsdlLocation.toString ( ) );
		// TODO Auto-generated method stub
		return null;
	}
	
	private void sendRequest ( String reqXMLString, String url ) throws Exception
	{
		HttpPost post = new HttpPost( url );
	      post.setEntity(new StringEntity(reqXMLString));
	      post.setHeader("Content-type", "text/xml; charset=UTF-8");
	  
	      HttpParams httpParameters = new BasicHttpParams();
	      HttpConnectionParams.setConnectionTimeout(httpParameters, 5000);
	      HttpConnectionParams.setSoTimeout(httpParameters, 6000);

	     /* if (this.isDEBUG())
	      {
	        Log.i(this.getClass().getSimpleName(), "Request: " + envelope.toString());
	      }*/

	      HttpClient client = new DefaultHttpClient(httpParameters);
	      HttpResponse response = client.execute(post);
	      int status = response.getStatusLine().getStatusCode();
	      System.out.println ("Status  " + status );
	      Log.i ("WSInvocationHandler: ", "Status  " + status );
	      
	      InputStream is = response.getEntity().getContent();

	     
	        InputStreamReader isr = new InputStreamReader(is);
	        BufferedReader br = new BufferedReader(isr);
	        StringBuilder sb = new StringBuilder();
	        for (String line = br.readLine(); line != null; line = br.readLine())
	        {
	          sb.append(line);
	          sb.append("\n");
	        }
	        Log.i ("WSInvocationHandler: ", "Response: " + sb.toString() );
	        br.close();
	        isr.close();
	        is.close();
	}
	
	private String getSoapBodyXML (  Method method, Object[] argumentsArray )
	{
		Map<String, Object> parameters = new HashMap<String, Object>();
		
	
		Annotation[][] paramAnnotationArr = method.getParameterAnnotations (); 
		//TODO: get the version
		if ( paramAnnotationArr != null && paramAnnotationArr.length > 0 ) 
        {
           try 
           {
              for ( int i=0;i<paramAnnotationArr.length;i++ )
              {
            	  Annotation[] annotationArr = paramAnnotationArr[i];
	           	   if ( annotationArr.length > 0)
	           	   {
	           		   System.out.println ( annotationArr[0].toString ( ));
	           		   Annotation an = annotationArr[0];
	           		   WebParam wp = (WebParam)an;
	           		   System.out.println (wp.name ( ));
	           		   parameters.put ( wp.name ( ), argumentsArray[i] );
	           	   }
           	  
              }
           }
           catch (Throwable ex) 
           {
             ex.printStackTrace ( );
             
           }
        }
		
		if ( argumentsArray != null && argumentsArray.length > 0 )
		{
			for ( Object argObj : argumentsArray )
			{
				System.out.println ( "arguments : "+argObj.toString ( ) );
			}
		}
		
		StringBuilder sb = new StringBuilder();

		String wsOperationName = getWSOperationNameFromMethod( method );
	    
	    sb.append( XMLUtils.getXMLStartTag (  wsOperationName ) );
	   
	    if (parameters != null)
	    {
		      for (Map.Entry<String, Object> entry : parameters.entrySet())
		      {
		        sb.append( XMLUtils.getXMLForKeyValue ( entry.getValue(), entry.getKey(), XMLConstants.SOAP_WS_TAG_INITIAL, false, 0));
		      }
	    }
	    
	    sb.append( XMLUtils.getXMLEndTag ( wsOperationName ) );

	    System.out.println ( "The Request xml is:" + sb.toString() );
	    
	    return sb.toString ( );
	}
	
	private String getFullRequestXmlFromBody ( SoapVersion soapVersion, String bodyXML, String appNameSpace )
	{
		StringBuilder sb = new StringBuilder();
		String envelopeXML = getEnvelopXMLFromSoapVersion ( soapVersion, appNameSpace );
		sb.append ( envelopeXML );
		sb.append ( XMLConstants.HEADER_SELF_ENDING );
		sb.append ( XMLConstants.SOAP_BODY_START );
		sb.append ( bodyXML );
		sb.append ( XMLConstants.SOAP_BODY_END );
		sb.append (  XMLConstants.SOAP_ENVELOPE_END_TAG );
		
		return sb.toString ( );
	}
	
	private String getEnvelopXMLFromSoapVersion ( SoapVersion soapVersion, String appNameSpace )
	{
		StringBuilder sb = new StringBuilder();
		sb.append ( XMLConstants.SOAP_ENVELOPE_START_TAG );
		sb.append ( XMLConstants.SOAP_ENV_NS_TAG );
		sb.append ( "=\""+ soapVersion.getEnvelopeNamespace ( ) +"\" " );
		sb.append ( XMLConstants.APP_NS_TAG );
		sb.append ( "=\""+ appNameSpace +"\" >" );
		
		return sb.toString ( );
	}
	
	private String getWSOperationNameFromMethod ( Method method)
	{
		String operationName = method.getName ( );
		Annotation[] methodAnnotationArr = method.getAnnotations ( );
		
		if ( methodAnnotationArr != null && methodAnnotationArr.length > 0 )
		{
			for ( Annotation methodAnn : methodAnnotationArr )
			{
				System.out.println ( methodAnn.annotationType ( ) );
				if (  methodAnn.annotationType ( ).toString ( ).contains ( "WebMethod")  )
				{
					WebMethod webMethod = (WebMethod)  methodAnn;
					operationName = webMethod.operationName ( );
					break;
				}
			}
		}
	
		return operationName;
	}

}
