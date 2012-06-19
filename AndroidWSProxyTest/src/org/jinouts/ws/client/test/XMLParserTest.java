/**
 * 
 */
package org.jinouts.ws.client.test;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

/**
 * @author asraf
 *
 */
public class XMLParserTest
{
	
		 
	@Test
	public void testWithGenericHandler ()  throws Exception
	{
		File file = new File ("./vault/response.txt");
		String resp = FileUtils.readFileToString ( file );
		
		System.out.println (resp);
		/*GenericHandler handler = new GenericHandler("GetCgUnitResult", GetCgUnitResponse.class, "org.xmlpull.mxp1.MXParser");
		handler.parseWithPullParser(resp);
		//GetCgUnitResponse respObj = (GetCgUnitResponse)handler.getObject ( );
		
		System.out.println (respObj.getGetCgUnitResult ( ).getLocation ( ).getLatitude ( ));
	*/}
	
}
