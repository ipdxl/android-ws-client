/**
 * 
 */
package org.jinouts.ws.client.test;


import junit.framework.TestSuite;

import org.jinouts.ws.AndProxyClientReqTestWS;
import org.jinouts.ws.AndProxyClientReqTestWS_Service;
import org.junit.Assert;
import org.junit.Before;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class PublicWebServiceTest extends TestSuite
{
	
	private AndProxyClientReqTestWS wsService = null;
	
	@Before
	public void setup () throws Exception
	{
		AndProxyClientReqTestWS_Service service = new AndProxyClientReqTestWS_Service ( );
		this.wsService = (AndProxyClientReqTestWS) service.getAndProxyClientReqTestWSPort ( );
		Assert.assertNotNull ( wsService );
	}
	
	/*@Test
	public void testPrimitiveRequestType () throws Exception
	{
		String userId = "asraf";
		String passWd = "123";
		
		String resp =  wsService.testPrimitiveRequest (  userId, passWd );
		System.out.println ("Response for testPrimitiveRequestType is: "+resp);
		
		// validate
		Assert.assertNotNull ( resp );
		Assert.assertEquals ( userId+passWd, resp );
	}
	
	@Test
	public void testJosipServices () throws Exception
	{
		 ZahtjevWS ss = new ZahtjevWS( );
	     ZahtjevWSPortType port = ss.getZahtjevWSHttpSoap12Endpoint();  
	     
	     hr.pardus.pozip.xsd.Status resp = port.zaporka ( "k","k","k");
	     System.out.println (resp.getStatus ( ) );
	}*/
	
	
}
