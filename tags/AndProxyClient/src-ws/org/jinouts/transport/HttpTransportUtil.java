/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html * 
 * 
 */
package org.jinouts.transport;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

/**
* @author asraf
* asraf344@gmail.com
* 
*/
public class HttpTransportUtil
{
	public static String sendRequestAndGetRespXML ( String reqXMLString, String url )
			throws Exception
	{
		HttpPost post = new HttpPost ( url );
		post.setEntity ( new StringEntity ( reqXMLString ) );
		post.setHeader ( "Content-type", "text/xml; charset=UTF-8" );

		HttpParams httpParameters = new BasicHttpParams ( );
		HttpConnectionParams.setConnectionTimeout ( httpParameters, 5000 );
		HttpConnectionParams.setSoTimeout ( httpParameters, 6000 );

		/*
		 * if (this.isDEBUG()) { Log.i(this.getClass().getSimpleName(),
		 * "Request: " + envelope.toString()); }
		 */

		HttpClient client = new DefaultHttpClient ( httpParameters );
		HttpResponse response = client.execute ( post );
		int status = response.getStatusLine ( ).getStatusCode ( );
		System.out.println ( "Status  " + status );
		System.out.println ( "WSInvocationHandler: " + "Status  " + status );

		InputStream is = response.getEntity ( ).getContent ( );

		InputStreamReader isr = new InputStreamReader ( is );
		BufferedReader br = new BufferedReader ( isr );
		StringBuilder sb = new StringBuilder ( );
		for ( String line = br.readLine ( ); line != null; line = br.readLine ( ) )
		{
			sb.append ( line );
			sb.append ( "\n" );
		}
		String resp = sb.toString ( );
		System.out.println ( "WSInvocationHandler: " + "Response: "
				+ sb.toString ( ) );
		br.close ( );
		isr.close ( );
		is.close ( );

		return resp;
	}
}
