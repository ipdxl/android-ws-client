/**
 * 
 */
package org.jinouts.ws.axis;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.beanutils.BeanUtils;
import org.jinouts.ws.model.MbrDetail;
import org.jinouts.ws.model.TestComplexResponse;
import org.jinouts.ws.utils.WSTestUtil;


/**
 * @author asraf
 * 
 */
@WebService(serviceName = "Axis2RespTestWS", name = "Axis2RespTestWS", targetNamespace = "http://axis.ws.jinouts.org/")
public class Axis2RespTestWS
{
	@WebMethod
	public String testPrimitiveResponse ( @WebParam(name = "user") String userId, @WebParam(name = "pass") String password )
	{
		System.out.println ( "In the login Method: user-" + userId + "Passwd: " + password );
		return "Success";
	}

	@WebMethod
	public Date testDateResponse ( @WebParam(name = "user") String userId )
	{
		Date date = new Date ( );
		return date;
	}
	
	@WebMethod
	public ArrayList<String> testListResponse ( @WebParam(name = "user") String userId )
	{
		ArrayList<String> list = new ArrayList<String> ( );
		list.add ( WSTestUtil.LIST_FIRST_ELEMENT );
		list.add ( WSTestUtil.LIST_SEC_ELEMENT );
		list.add (WSTestUtil.LIST_THIRD_ELEMENT );
		
		
		return list;
	}
	@WebMethod
	public String[] testArrayResponse ( @WebParam(name = "user") String userId )
	{
		String[] array = { WSTestUtil.ARRAY_FIRST_ELEMENT,
						   WSTestUtil.ARRAY_SEC_ELEMENT, 
						   WSTestUtil.ARRAY_THIRD_ELEMENT };		
		
		return  array;
	}

	@WebMethod
	public List<MbrDetail> testListOfObjResponse ( @WebParam(name = "user") String userId )
	{
		return getDummyMemberList ( );
		
		
	}

	

	@WebMethod
	public TestComplexResponse testComplexResponseObject ( @WebParam(name = "user") String userId )
	{
		System.out.println ( "Invoking testComplexRequest!!!!" );
		TestComplexResponse resp = new TestComplexResponse ( );
		
		List<MbrDetail> mbrList = getDummyMemberList ( );
		resp.setMbrDetail ( mbrList );
		
		ArrayList<String> list = new ArrayList<String> ( );
		list.add ( WSTestUtil.LIST_FIRST_ELEMENT );
		list.add ( WSTestUtil.LIST_SEC_ELEMENT );
		list.add (WSTestUtil.LIST_THIRD_ELEMENT );
		resp.setList (list);
		
		String[] array = { WSTestUtil.ARRAY_FIRST_ELEMENT,
				   WSTestUtil.ARRAY_SEC_ELEMENT, 
				   WSTestUtil.ARRAY_THIRD_ELEMENT };	
		resp.setStrArray ( array );
		
		resp.setApplId ( "appI" );
		resp.setDoubleId ( 12.09 );
		
		return resp;
	}
	
	@WebMethod
	public ArrayList<TestComplexResponse> testListOfComplexResponseObject ( @WebParam(name = "user") String userId )
	{
		ArrayList<TestComplexResponse> respList = new ArrayList<TestComplexResponse> ( );
		System.out.println ( "Invoking testComplexRequest!!!!" );
		
		TestComplexResponse compResp1 = new TestComplexResponse ( );
		
		List<MbrDetail> mbrList = getDummyMemberList ( );
		compResp1.setMbrDetail ( mbrList );
		
		ArrayList<String> list = new ArrayList<String> ( );
		list.add ( WSTestUtil.LIST_FIRST_ELEMENT );
		list.add ( WSTestUtil.LIST_SEC_ELEMENT );
		list.add (WSTestUtil.LIST_THIRD_ELEMENT );
		compResp1.setList (list);
		
		String[] array = { WSTestUtil.ARRAY_FIRST_ELEMENT,
				   WSTestUtil.ARRAY_SEC_ELEMENT, 
				   WSTestUtil.ARRAY_THIRD_ELEMENT };
		compResp1.setStrArray ( array );
		
		compResp1.setApplId ( "appI1111" );
		compResp1.setDoubleId ( 12.09 );
		
		respList.add ( compResp1 );
		
		TestComplexResponse compResp2 = new TestComplexResponse ( );
		
		List<MbrDetail> mbrList2 = getDummyMemberList ( );
		compResp2.setMbrDetail ( mbrList );
		
		ArrayList<String> list2 = new ArrayList<String> ( );
		list2.add ( 2+WSTestUtil.LIST_FIRST_ELEMENT );
		list2.add ( 2+WSTestUtil.LIST_SEC_ELEMENT );
		list2.add ( 2+WSTestUtil.LIST_THIRD_ELEMENT );
		compResp2.setList (list2);
		
		String[] array2 = { 2+WSTestUtil.ARRAY_FIRST_ELEMENT,
				   2+WSTestUtil.ARRAY_SEC_ELEMENT, 
				   2+WSTestUtil.ARRAY_THIRD_ELEMENT };	
		compResp2.setStrArray ( array2 );
		
		compResp2.setApplId ( "appI222" );
		compResp2.setDoubleId ( 22.09 );
		
		respList.add ( compResp2 );
		
		return respList;
	}

	static List<MbrDetail>  getDummyMemberList ()
	{
		List<MbrDetail> mbrList = new ArrayList<MbrDetail> ( );
		Date date = new Date();
		
		MbrDetail mbr1 = new MbrDetail ( );
		mbr1.setAddress2 ( "add11" );
		mbr1.setZip ( "zip" );
		mbr1.setAddress1 ( "add11111" );		
		mbr1.setRegistrationDate ( date );
		mbrList.add ( mbr1 );
		
		MbrDetail mbr2 = new MbrDetail ( );
		mbr2.setAddress2 ( "add12" );
		mbr2.setZip ( "zip22" );
		mbr2.setAddress1 ( "add22" );		
		mbr2.setRegistrationDate ( date );
		mbrList.add ( mbr2 );
		
		MbrDetail mbr3 = new MbrDetail ( );
		mbr3.setAddress2 ( "add31" );
		mbr3.setZip ( "zip3" );
		mbr3.setAddress1 ( "add31" );		
		mbr3.setRegistrationDate ( date );
		mbrList.add ( mbr3 );
		
		return mbrList;
	}
	static void printObject ( Object obj )
	{
		Field[] fieldArray = obj.getClass ( ).getDeclaredFields ( );
		for ( Field f : fieldArray )
		{
			try
			{

				System.out.println ( "Value for " + f.getName ( ) + " " + BeanUtils.getProperty ( obj, f.getName ( ) ) );

			}
			catch ( Exception ex )
			{
				ex.printStackTrace ( );
			}
		}
	}
}
