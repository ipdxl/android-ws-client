/**
 * CC-LGPL 2.1
 * http://creativecommons.org/licenses/LGPL/2.1/
 */
package org.jinos.transport;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import android.util.Log;

/**
 * Simple Http transport
 *
 * Changelog:
 * ANDROIDSOAP-6 - 2011-01-08
 * ANDROIDSOAP-5 - 2011-01-07
 * ANDROIDSOAP-1 - 2011-01-06
 *
 * @author Gábor Auth <gabor.auth@javaforum.hu>
 * @author Chris Wolf
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class HttpTransport 
{

	private String url;
	
  /**
   * Creates a new instance
   *
   * @param url The URL
   */
  public HttpTransport(String url)
  {
    this.url = url;
  }

  /**
   * Creates a new instance with authorization
   *
   * @param url The URL
   * @param username The username
   * @param password The password
   */
  public HttpTransport(String url, String username, String password)
  {
    //super(url, username, password);
  }

  /**
   * Call the service
   *
   * @param <T> The return type
   * @param envelope The request envelope
   * @param resultClass The class of the result in the response
   * @return The response
   */
 
  public <T> T call(String reqXMLString, Class<T> resultClass) throws Exception
  {
    return call(reqXMLString, resultClass, null);
  }

  /**
   * Call the service
   *
   * @param <T> The return type
   * @param envelope The request envelope
   * @param resultClass The class of the result in the response
   * @param httpHeaders The custom Http headers
   * @return The response
   * @throws Exception An exception
   */
 
  
  public <T> T call(String reqXMLString, Class<T> resultClass, Map<String, String> httpHeaders) throws Exception
  {
    try
    {
      long start = System.nanoTime();

      HttpPost post = new HttpPost(this.getUrl());
      post.setEntity(new StringEntity(reqXMLString));
      post.setHeader("Content-type", "text/xml; charset=UTF-8");
      if (httpHeaders != null)
      {
        for (Map.Entry<String, String> entry : httpHeaders.entrySet())
        {
          post.setHeader(entry.getKey(), entry.getValue());
        }
      }
    /*  if (this.getUsername() != null && this.getPassword() != null)
      {
        post.addHeader("Authorization", "Basic " + Base64Encoder.encodeString(this.getUsername() + ":" + this.getPassword()));
      }*/

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

      Log.i(this.getClass().getSimpleName(), "The server has been replied. " + ((System.nanoTime() - start) / 1000) + "us, status is " + status);
      start = System.nanoTime();

      InputStream is = response.getEntity().getContent();

      if ( true)
      {
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        StringBuilder sb = new StringBuilder();
        for (String line = br.readLine(); line != null; line = br.readLine())
        {
          sb.append(line);
          sb.append("\n");
        }
        Log.i(this.getClass().getSimpleName(), "Response: " + sb.toString());
        br.close();
        isr.close();
        is.close();

        is = new ByteArrayInputStream(sb.toString().getBytes("UTF-8"));

        Log.i(this.getClass().getSimpleName(), "DEBUG mode delay: " + ((System.nanoTime() - start) / 1000) + "us");
        start = System.nanoTime();
      }

    /*  GenericHandler handler = new GenericHandler(resultClass, this.isDEBUG());
      if (status == 200)
      {
        handler.parseWithPullParser(is);
      } else
      {
        throw new Exception("Can't parse the response, status: " + status);
      }

      Log.i(this.getClass().getSimpleName(), "The reply has been parsed: " + ((System.nanoTime() - start) / 1000) + "us");
*/
      //return (T) handler.getObject();
      return null;
    }
    
    catch (Exception e)
    {
      Log.e(this.getClass().getSimpleName(), e.toString(), e);
      throw e;
    }
    
  }

public String getUrl ( )
{
	return url;
}

public void setUrl ( String url )
{
	this.url = url;
}
}
