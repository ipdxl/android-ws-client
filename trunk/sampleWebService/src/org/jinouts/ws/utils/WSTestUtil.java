/**
 * 
 */
package org.jinouts.ws.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.BeanUtils;
import org.jinouts.ws.model.TestCoktelRequest;
import org.jinouts.ws.model.TestComplexRequest;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class WSTestUtil
{
	public static final String SERVER_ERROR = "serverError";
	
	public static final String 	LIST_FIRST_ELEMENT = "L11";
	public static final String 	LIST_SEC_ELEMENT = "L22";
	public static final String 	LIST_THIRD_ELEMENT = "L33";
	public static final String 	LIST_FOURTH_ELEMENT = "L44";
	
	public static final String 	ARRAY_FIRST_ELEMENT = "A11";
	public static final String 	ARRAY_SEC_ELEMENT = "A22";
	public static final String 	ARRAY_THIRD_ELEMENT = "A33";
	public static final String 	ARRAY_FOURTH_ELEMENT = "A44";
	
	public  static String getStringForArray (  String[] array )
	{
		String result = "";
		
		try
		{
			if ( array != null )
			{
				result += array.length;
				for ( String st : array )
				{
					result += st;
				}
			}
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}
		
		
		return result;
	}
	
	public static String getStringForList (  ArrayList<String> list )
	{
		String result = "";
		
		try
		{
			if ( list != null )
			{
				result += list.size ( );
				for ( String st : list )
				{
					result += st;
				}
			}
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}
			
		return result;
	}
	
	public static String getStringForArrayAndList (  ArrayList<String> list, String[] array )
	{
		String result = "";
		
		try
		{
			result += getStringForArray ( array ) + getStringForList ( list );
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}
		
		
		
		return result;
	}
	
	public static String getStringForObj ( Object obj )
	{
		String result = "";
		try
		{

			if ( obj != null )
			{
				Field[] fieldArray = obj.getClass ( ).getDeclaredFields ( );
				for ( Field f : fieldArray )
				{
					try
					{
						String attrName =  f.getName ( );
						String value = BeanUtils.getProperty ( obj, f.getName ( ) ); 
						result += attrName +":"+value;
					}
					catch ( Exception ex )
					{
						ex.printStackTrace ( );
					}
				}
			}
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}
		
		
		
		return result;
	}
	
	public static String getStringForObjList (  List<?> list )
	{
		String result = "";
		
		try
		{
			if ( list != null )
			{
				result += list.size ( );
				for ( Object obj : list )
				{
					result += getStringForObj ( obj );
				}
			}
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}

		return result;
	}
	
	public static String getStringForCokTelRequest (  TestCoktelRequest req )
	{
		String result = "";
		
		try
		{
			
			result += req.getApplId ( ) + req.getDoubleId ( )+ req.getFloatId ( )+ getStringForArray ( req.getStrArray ( ) )+ getStringForList ( req.getList ( ) );
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}

		return result;
	}
	
	public static String getStringForComplexRequest (  TestComplexRequest req )
	{
		String result = "";
		
		try
		{
			
			result += req.getApplId ( ) + req.getDoubleId ( )+ req.getFloatId ( );
			result += getStringForArray ( req.getStrArray ( ) )+ getStringForList ( req.getList ( ) );
			result += getStringForObjList ( req.getMbrDetail ( ) );
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}

		return result;
	}
	
	
}
