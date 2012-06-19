/**
 * 
 */
package org.jinouts.ws.client.test;


import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import junit.framework.TestSuite;

import org.jinouts.ws.AndProxyClientReqTestWS;
import org.jinouts.ws.AndProxyClientReqTestWS_Service;
import org.jinouts.ws.MbrDetail;
import org.jinouts.ws.Request;
import org.jinouts.ws.TestComplexRequest;
import org.jinouts.ws.util.WSTestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class WebServiceRequestTest extends TestSuite
{
	
	private AndProxyClientReqTestWS wsService = null;
	
	@Before
	public void setup () throws Exception
	{
		AndProxyClientReqTestWS_Service service = new AndProxyClientReqTestWS_Service ( );
		this.wsService = (AndProxyClientReqTestWS) service.getAndProxyClientReqTestWSPort ( );
		Assert.assertNotNull ( wsService );
	}
	
	@Test
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
	public void testObjectRequestType () throws Exception
	{
		
		Request request = new Request ( );
		request.setApplId ( "app" );
		request.setDoubleId ( 12.0 );
		request.setFloatId (  12.11f );
		request.setIntId (  12 );
	
		request.getList ( ).add ( "l1" );
		request.getList ( ).add ( "l2" );
		request.getStrArray ( ).add ( "al2" );
		request.getStrArray ( ).add ( "al3" );
		XMLGregorianCalendar xmc = DatatypeFactory.newInstance ( ).newXMLGregorianCalendar (  new GregorianCalendar ( 2012,10,5) );
		XMLGregorianCalendar xmc2 = DatatypeFactory.newInstance ( ).newXMLGregorianCalendar (  new GregorianCalendar ( 2009,9,9) );
		request.setCal ( xmc );
		request.setDate ( xmc2 );
		
		String resp =  wsService.testObjectRequest ( request );
		System.out.println ("Response for testObjectRequestType is " + resp );
		
		String expectedResult = WSTestUtil.getStringForCokTelRequest ( request );
		// validate
		Assert.assertNotNull ( resp );
		Assert.assertEquals ( expectedResult, resp );
	}
	
	@Test
	public void testComplexRequestObject() throws Exception
	{
		List<MbrDetail> mbrList = new ArrayList<MbrDetail> ( );
		XMLGregorianCalendar xmc = DatatypeFactory.newInstance ( ).newXMLGregorianCalendar (  new GregorianCalendar ( 2011,5,5) );
		
		MbrDetail mbr1 = new MbrDetail ( );
		mbr1.setAddress2 ( "add11" );
		mbr1.setZip ( "zip" );
		mbr1.setAddress1 ( "add11111" );		
		mbr1.setRegistrationDate ( xmc );
		mbrList.add ( mbr1 );
		
		MbrDetail mbr2 = new MbrDetail ( );
		mbr2.setAddress2 ( "add12" );
		mbr2.setZip ( "zip22" );
		mbr2.setAddress1 ( "add22" );		
		//mbr2.setRegistrationDate ( xmc );
		mbrList.add ( mbr2 );
		
		MbrDetail mbr3 = new MbrDetail ( );
		mbr3.setAddress2 ( "add31" );
		mbr3.setZip ( "zip3" );
		mbr3.setAddress1 ( "add31" );		
	    mbr3.setRegistrationDate ( xmc );
		mbrList.add ( mbr3 );
		
		TestComplexRequest request = new TestComplexRequest ( );
		request.getMbrDetail ( ).add ( mbr1 );
		request.getMbrDetail ( ).add ( mbr2 );
		request.getMbrDetail ( ).add ( mbr3 );
		
		
		request.getList ( ).add ( "li" );
		request.getList ( ).add ( "l2" );
		request.getList ( ).add ( "l3" );//Arrays.asList ( "l1","l2","l3" );
		
		request.getStrArray ( ).add ( "a1" );
		request.getStrArray ( ).add ( "a2" );
		request.getStrArray ( ).add ( "a3" );
		
		request.setApplId ( "appI" );
		request.setDoubleId ( 12.09 );
		
		String resp =  wsService.testComplexRequestObject ( request );
		
		
		System.out.println ("Response for testComplexRequestObject is " + resp );
		
		String expectedResult = WSTestUtil.getStringForComplexRequest ( request );
		System.out.println ("expectedResult for testComplexRequestObject is " + expectedResult );
		// validate
		Assert.assertNotNull ( resp );
		Assert.assertEquals ( expectedResult, resp );
	}
	
	@Test
	public void testListOfObjectRequestType () throws Exception
	{
		
		List<MbrDetail> mbrList = new ArrayList<MbrDetail> ( );
		XMLGregorianCalendar xmc = DatatypeFactory.newInstance ( ).newXMLGregorianCalendar (  new GregorianCalendar ( 2011,5,5) );
		
		MbrDetail mbr1 = new MbrDetail ( );
		mbr1.setAddress2 ( "add11" );
		mbr1.setZip ( "zip" );
		mbr1.setAddress1 ( "add11111" );		
		mbr1.setRegistrationDate ( xmc );
		mbrList.add ( mbr1 );
		
		MbrDetail mbr2 = new MbrDetail ( );
		mbr2.setAddress2 ( "add12" );
		mbr2.setZip ( "zip22" );
		mbr2.setAddress1 ( "add22" );		
		mbr2.setRegistrationDate ( xmc );
		mbrList.add ( mbr2 );
		
		MbrDetail mbr3 = new MbrDetail ( );
		mbr3.setAddress2 ( "add31" );
		mbr3.setZip ( "zip3" );
		mbr3.setAddress1 ( "add31" );		
		mbr3.setRegistrationDate ( xmc );
		mbrList.add ( mbr3 );
		
		String resp =  wsService.testListOfObjRequest ( mbrList );
		System.out.println ("Response for testListOfObjRequest is " + resp );
		
		String expectedResult = WSTestUtil.getStringForObjList ( mbrList );
		// validate
		Assert.assertNotNull ( resp );
		Assert.assertEquals ( expectedResult, resp );
	}
	
	@Test
	public void testListDateRequestType () throws Exception
	{
		
		XMLGregorianCalendar xmc = DatatypeFactory.newInstance ( ).newXMLGregorianCalendar (  new GregorianCalendar ( 2011,5,5) );
		List<String> list = new ArrayList<String> ( );
		list.add ( "li" );
		list.add ( "l2" );
		list.add ( "l3" );//Arrays.asList ( "l1","l2","l3" );
		List<String> array =  new ArrayList<String> ( );//Arrays.asList ( "a1","a2","a3" );
		array.add ( "a1" );
		array.add ( "a2" );
		array.add ( "a3" );
		
		String resp =  wsService.testListDateArrayRequest ( xmc, list, array );
						
		System.out.println ("Response for testListDateArrayRequest is: " + resp );
		
		String expectedResult =  new GregorianCalendar ( 2011,5,5).getTime ( ).toString ( ) + WSTestUtil.getStringForList ( array )+WSTestUtil.getStringForList ( list );
		
		// validate
		Assert.assertNotNull ( resp );
		Assert.assertEquals ( expectedResult, resp );
	}
}
