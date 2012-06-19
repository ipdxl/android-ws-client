/**
 * 
 */
package org.jinouts.ws.client.test;

import java.lang.reflect.Field;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.jinouts.ws.AndProxyClientRespTestWS;
import org.jinouts.ws.AndProxyClientRespTestWS_Service;
import org.jinouts.ws.MbrDetail;
import org.jinouts.ws.TestComplexResponse;
import org.jinouts.ws.util.WSTestUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author asraf
 *
 */
public class WebServiceProxyRespTest
{
	private AndProxyClientRespTestWS wsService = null;
	
	@Before
	public void setup () throws Exception
	{
		AndProxyClientRespTestWS_Service service = new AndProxyClientRespTestWS_Service ( );
		this.wsService = (AndProxyClientRespTestWS) service.getAndProxyClientRespTestWSPort ( );
		Assert.assertNotNull ( wsService );
	}
	
	@Test
	public void testPrimitiveResponse () throws Exception
	{
		String resp =  wsService.testPrimitiveResponse (  "user12", "pass123" );
		System.out.println ("Response is " + resp );
		
		Assert.assertNotNull ( resp );
		Assert.assertEquals ( "Success", resp );
		
	}
	
	
	@Test
	public void testDateResponse () throws Exception
	{
		XMLGregorianCalendar xmc =  wsService.testDateResponse ( "user1212" );
		System.out.println ("Response is " + xmc );		
	}
	
	@Test
	public void testArrayResponse() throws Exception
	{
		List<String> list =  wsService.testArrayResponse ( "user1212" );
		
		if ( list != null )
		{
			for ( String st : list )
			{
				System.out.println ( "String in list: " + st );
			}
		}
		
		Assert.assertNotNull ( list );
		Assert.assertTrue ( isExpectedArray ( list, null ) );
	
	}
	
	@Test
	public void testListResponse() throws Exception
	{
		List<String> list =  wsService.testListResponse ( "user1212" );
		
		if ( list != null )
		{
			for ( String st : list )
			{
				System.out.println ( "String in list: " + st );
			}
		}
		
		Assert.assertNotNull ( list );
		Assert.assertTrue ( isExpectedList ( list, null ) );
	}
	
	@Test
	public void testListOfObjectResponse () throws Exception
	{
		List<MbrDetail> mbrList = wsService.testListOfObjResponse (  "user1212" );
		
		if ( mbrList != null )
		{
			for ( MbrDetail mbr : mbrList )
			{
				printObject ( mbr );
			}
		}
		
		List<MbrDetail> expecMbrList = WSTestUtil.getDummyMemberList ( );
		
		Assert.assertNotNull ( mbrList );
		Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 0 ), expecMbrList.get ( 0 ) ) );
		Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 1 ), expecMbrList.get ( 1 ) ) );
		Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 2 ), expecMbrList.get ( 2 ) ) );
		
	}
	
	@Test
	public void testComplexObjectResponse () throws Exception
	{
		TestComplexResponse resp = wsService.testComplexResponseObject (  "user1212" );
		
		System.out.println ( "Invoking testComplexRequest!!!!" );
		
		System.out.println ( "resp: "+resp.getApplId ( ) );
		System.out.println ( "resp: "+resp.getDate ( ) );
		System.out.println ( "resp: "+resp.getCal ( ) );
		System.out.println ( "resp: "+resp.getDoubleId ( ) );
		
		List<MbrDetail> mbrList = resp.getMbrDetail ( );
		if ( mbrList != null )
		{
			for ( MbrDetail mbr : mbrList )
			{
				printObject ( mbr );
			}
		}
		List<MbrDetail> expecMbrList = WSTestUtil.getDummyMemberList ( );
		
		Assert.assertNotNull ( mbrList );
		Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 0 ), expecMbrList.get ( 0 ) ) );
		Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 1 ), expecMbrList.get ( 1 ) ) );
		Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 2 ), expecMbrList.get ( 2 ) ) );
		
		
		List<String> list = resp.getList ( );
		if ( list != null )
		{
			for ( String st : list )
			{
				System.out.println ( "String in list: " + st );
			}
		}
		
		Assert.assertNotNull ( list );
		Assert.assertTrue ( isExpectedList ( list, null ) );
		
		List<String> array = resp.getStrArray ( );
		if ( array != null )
		{
			for ( String st : array )
			{
				System.out.println ( "String in Array: " + st );
			}
		}
		
		Assert.assertNotNull ( array );
		Assert.assertTrue ( isExpectedArray ( array, null ) );
	}
	
	@Test
	public void testListOfComplexObjectResponse () throws Exception
	{
		List<TestComplexResponse> complList = wsService.testListOfComplexResponseObject ( "user1212" );
		
		System.out.println ( "Invoking testComplexRequest!!!!" );
		
		List<MbrDetail> expecMbrList = WSTestUtil.getDummyMemberList ( );
		
		for ( int i=0; i<complList.size ( ); i++ )
		{
			TestComplexResponse resp = complList.get ( i );
			System.out.println ("!!!!!!! For comp Response " + i );
			System.out.println ( "resp: "+resp.getApplId ( ) );
			System.out.println ( "resp: "+resp.getDate ( ) );
			System.out.println ( "resp: "+resp.getCal ( ) );
			System.out.println ( "resp: "+resp.getDoubleId ( ) );
			
			List<MbrDetail> mbrList = resp.getMbrDetail ( );
			if ( mbrList != null )
			{

				for ( MbrDetail mbr : mbrList )
				{
					printObject ( mbr );
				}
			}
			
			Assert.assertNotNull ( mbrList );
			Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 0 ), expecMbrList.get ( 0 ) ) );
			Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 1 ), expecMbrList.get ( 1 ) ) );
			Assert.assertTrue ( WSTestUtil.isTwoMemberEquaul ( mbrList.get ( 2 ), expecMbrList.get ( 2 ) ) );
			
			
			List<String> list = resp.getList ( );
			if ( list != null )
			{
				for ( String st : list )
				{
					System.out.println ( "String in list: " + st );
				}
			}
			String listPrefix = null;
			
			
			List<String> array = resp.getStrArray ( );
			if ( array != null )
			{
				for ( String st : array )
				{
					System.out.println ( "String in Array: " + st );
				}
			}
			
			if ( i > 0)
			{
				listPrefix = ""+(i+1);				
			}
			
			Assert.assertNotNull ( list );
			Assert.assertTrue ( isExpectedList ( list, listPrefix ) );
			
			Assert.assertNotNull ( array );
			Assert.assertTrue ( isExpectedArray ( array, listPrefix ) );
		}
		
	}
	
	static boolean isExpectedList ( List<String> list,  String prefix )
	{
		if ( list.size ( ) != 3 )
		{
			return false;
		}
		
		if ( prefix == null )
		{
			if ( !(list.get ( 0 ).equals ( WSTestUtil.LIST_FIRST_ELEMENT ) && list.get ( 1 ).equals ( WSTestUtil.LIST_SEC_ELEMENT ) && list.get ( 2 ).equals( WSTestUtil.LIST_THIRD_ELEMENT ) ) )
			{
				return false;
			}
		}
		else
		{	
			if ( !(list.get ( 0 ).equals ( prefix+WSTestUtil.LIST_FIRST_ELEMENT ) && list.get ( 1 ).equals ( prefix+WSTestUtil.LIST_SEC_ELEMENT ) && list.get ( 2 ).equals( prefix+WSTestUtil.LIST_THIRD_ELEMENT ) ) )
			{
				return false;
			}
		}
		return true;
	}
	
	static boolean isExpectedArray ( List<String> list, String prefix )
	{
		if ( list.size ( ) != 3 )
		{
			return false;
		}
		
		if ( prefix == null )
		{
			if ( !(list.get ( 0 ).equals ( WSTestUtil.ARRAY_FIRST_ELEMENT ) && list.get ( 1 ).equals ( WSTestUtil.ARRAY_SEC_ELEMENT ) && list.get ( 2 ).equals( WSTestUtil.ARRAY_THIRD_ELEMENT ) ) )
			{
				return false;
			}
		}
		else
		{	
			if ( !(list.get ( 0 ).equals ( prefix+WSTestUtil.ARRAY_FIRST_ELEMENT ) && list.get ( 1 ).equals ( prefix+WSTestUtil.ARRAY_SEC_ELEMENT ) && list.get ( 2 ).equals( prefix+WSTestUtil.ARRAY_THIRD_ELEMENT ) ) )
			{
				return false;
			}
		}
		return true;
	}
	
	static void printObject ( Object obj )
	{
		Field[] fieldArray = obj.getClass ( ).getDeclaredFields ( );
		for ( Field f : fieldArray )
		{
			try
			{

				System.out.println ( "Value for " +  f.getName ( ) + " "
						+ BeanUtils.getProperty ( obj, f.getName ( ) ) );

			}
			catch ( Exception ex )
			{
				ex.printStackTrace ( );
			}
		}
	}
	
	
}
