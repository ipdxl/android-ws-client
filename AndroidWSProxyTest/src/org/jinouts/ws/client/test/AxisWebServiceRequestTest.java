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

import org.jinouts.ws.axis.ComplexRequestWS;
import org.jinouts.ws.axis.ComplexRequestWSPortType;
import org.jinouts.ws.axis.model.xsd.ComplexRequest;
import org.jinouts.ws.axis.model.xsd.MbrDetail;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class AxisWebServiceRequestTest extends TestSuite
{
	
	private ComplexRequestWSPortType wsService = null;
	
	@Before
	public void setup () throws Exception
	{
		ComplexRequestWS service = new ComplexRequestWS ( );
		this.wsService = (ComplexRequestWSPortType) service.getComplexRequestWSHttpSoap12Endpoint();
		Assert.assertNotNull ( wsService );
	}
	
	
	
	@Test
	public void testComplexRequestObject() throws Exception
	{
		ComplexRequest request = new ComplexRequest ( );
		
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
		
		
		request.getMbrDetail ( ).add ( mbr1 );
		request.getMbrDetail ( ).add ( mbr2 );
		request.getMbrDetail ( ).add ( mbr3 );
		
		request.getStrArray ( ).add ( "a1" );
		request.getStrArray ( ).add ( "a2" );
		request.getStrArray ( ).add ( "a3" );
		
		request.setApplId ( "appI" );
		request.setDoubleId ( 12.09 );
		request.setFloatId ( 2.09f );
		request.setIntId ( 2 );
		
		String resp =  wsService.testComplexRequest ( request );
		
		
		System.out.println ("Response for testComplexRequestObject is " + resp );
		
		/*String expectedResult = WSTestUtil.getStringForComplexRequest ( request );
		System.out.println ("expectedResult for testComplexRequestObject is " + expectedResult );
		// validate
		Assert.assertNotNull ( resp );
		Assert.assertEquals ( expectedResult, resp );*/
	}
	
	
}
