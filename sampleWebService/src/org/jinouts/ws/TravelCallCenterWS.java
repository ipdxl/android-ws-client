/**
 * 
 */
package org.jinouts.ws;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.ws.BindingType;
import javax.xml.ws.soap.SOAPBinding;

import org.jinouts.ws.model.MbrDetail;
import org.jinouts.ws.model.MbrDetailList;
import org.jinouts.ws.model.RequestHeader;
import org.jinouts.ws.model.TravelCallCenterMemberAuthenticationRequest;
import org.jinouts.ws.model.TravelCallCenterMemberAuthenticationResponse;


/**
 * @author asraf
 *
 */
@WebService(serviceName="TravelCallCenterWS",  name="travelCallCenterWS", targetNamespace = "http://ws.aprosoft.com/" )
public class TravelCallCenterWS
{
	@WebMethod
	 public TravelCallCenterMemberAuthenticationResponse getMMInfo(
		        @WebParam(name="request")
		        RequestHeader requestHeader,
		        @WebParam(name="travelrequest")
		        TravelCallCenterMemberAuthenticationRequest travelCallCenterMemberAuthenticationRequest
		    )
	{
		if ( requestHeader.getRequestDate ( ) != null )
		{
			System.out.println ("Date: " +requestHeader.getRequestDate ( ).toGregorianCalendar ( ).getTime ( ).toString ( ) );
			System.out.println ("dateString: " + new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss" ).format ( requestHeader.getRequestDate ( ).toGregorianCalendar ( ).getTime ( ) ));
		}
		System.out.println ("Invoking the getTravelMemberInfo() " );
		TravelCallCenterMemberAuthenticationResponse response = new TravelCallCenterMemberAuthenticationResponse ( );
		
		MbrDetailList mbrDetailList = new MbrDetailList ( );
		List<MbrDetail> mbrDetailArrList = new ArrayList<MbrDetail> ( );
		
		MbrDetail mbrDetail1 = new MbrDetail ( );
		mbrDetail1.setAddress1 ( "add1" );
		mbrDetail1.setBusinessPhone ( "12131" );
		mbrDetail1.setRegistrationDate ( new Date ( ) );
		XMLGregorianCalendar xmc = null;
		try
		{
			xmc = DatatypeFactory.newInstance ( ).newXMLGregorianCalendar (  new GregorianCalendar ( 2011,5,5) );
		}
		catch ( Exception ex )
		{
			ex.printStackTrace ( );
		}
		mbrDetail1.setBirthDate ( xmc );
		
		
		MbrDetail mbrDetail2 = new MbrDetail ( );
		mbrDetail2.setAddress1 ( "add21" );
		mbrDetail2.setBusinessPhone ( "b12131" );
		
		MbrDetail mbrDetail3 = new MbrDetail ( );
		mbrDetail3.setAddress1 ( "add31" );
		mbrDetail3.setBusinessPhone ( "b3434" );
		
		mbrDetailArrList.add ( mbrDetail1 );
		mbrDetailArrList.add ( mbrDetail2 );
		mbrDetailArrList.add ( mbrDetail3 );
		
		mbrDetailList.setMbrDetail ( mbrDetailArrList );
		response.setMemberDetailList ( mbrDetailList );
		
		System.out.println ("Returning response" );
		return response;
	}
}
