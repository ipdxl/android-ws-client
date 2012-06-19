/**
 * 
 */
package org.jinouts.ws;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import org.apache.commons.beanutils.BeanUtils;
import org.jinouts.ws.model.MbrDetail;
import org.jinouts.ws.model.TestCoktelRequest;
import org.jinouts.ws.model.TestComplexRequest;
import org.jinouts.ws.utils.WSTestUtil;

/**
 * @author asraf
 * 
 */
@WebService(serviceName = "AndProxyClientReqTestWS", name = "AndProxyClientReqTestWS", targetNamespace = "http://ws.jinouts.org/")
public class AndProxyClientReqTestWS
{
	@WebMethod
	public String testPrimitiveRequest ( @WebParam(name = "user") String userId, @WebParam(name = "pass") String password )
	{
		System.out.println ( "In the login Method: user-" + userId + "Passwd: " + password );
		
		String result =  userId+password; 
		System.out.println ("Result Returning is: " + result );
		return result;
	}

	@WebMethod
	public String testListDateArrayRequest ( @WebParam(name = "date") Date date, @WebParam(name = "list") ArrayList<String> list, @WebParam(name = "array") String[] array )
	{
		System.out.println ( "In testListDateArrayRequest - date " + date );

		if ( list != null )
		{
			for ( String st : list )
			{
				System.out.println ( "String in list: " + st );
			}
		}
		
		if ( array != null )
		{
			for ( String st : array )
			{
				System.out.println ( "String in Array: " + st );
			}
		}
		
		String result = date.toString ( ) + WSTestUtil.getStringForArrayAndList ( list, array );
		System.out.println ("Result Returning is: " + result );
		
		return result;
	}

	@WebMethod
	public String testListOfObjRequest ( @WebParam(name = "mbrList") ArrayList<MbrDetail> mbrList )
	{

		if ( mbrList != null )
		{

			for ( MbrDetail mbr : mbrList )
			{
				printObject ( mbr );
			}
		}
		
		String result = WSTestUtil.getStringForObjList ( mbrList );
		System.out.println ("Result Returning is: " + result );
		
		return result;
	}

	@WebMethod
	public String testObjectRequest ( @WebParam(name = "request") TestCoktelRequest request )
	{

		if ( request != null )
		{
			Method[] methodArray = request.getClass ( ).getDeclaredMethods ( );
			for ( Method m : methodArray )
			{
				if ( m.getName ( ).startsWith ( "get" ) )
				{
					try
					{
						Object value = m.invoke ( request, null );
						System.out.println ( "Value from method  " + m.getName ( ) + " is:  " + value );
					}
					catch ( Exception e )
					{
						// TODO Auto-generated catch block
						e.printStackTrace ( );
					}

				}

			}
		}
		else
		{
			return "Error: Request is null";
		}
		
		String result = WSTestUtil.getStringForCokTelRequest ( request );
		System.out.println ("Result Returning is: " + result );
		return result;
	}

	@WebMethod
	public String testComplexRequestObject ( @WebParam(name = "request") TestComplexRequest request )
	{
		System.out.println ( "Invoking testComplexRequest!!!!" );

		List<MbrDetail> mbrList = request.getMbrDetail ( );
		if ( mbrList != null )
		{

			for ( MbrDetail mbr : mbrList )
			{
				printObject ( mbr );
			}
		}

		List<String> list = request.getList ( );
		if ( list != null )
		{
			for ( String st : list )
			{
				System.out.println ( "String in list: " + st );
			}
		}

		String[] array = request.getStrArray ( );
		if ( array != null )
		{
			for ( String st : array )
			{
				System.out.println ( "String in Array: " + st );
			}
		}

		String result = WSTestUtil.getStringForComplexRequest ( request );
		System.out.println ("Result Returning is: " + result );
		
		return result;
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
