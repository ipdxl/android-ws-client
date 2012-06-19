/**
 * 
 */
package org.jinouts.ws.util;

import hu.javaforum.commons.ReflectionHelper;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.datatype.XMLGregorianCalendar;

import org.apache.commons.beanutils.BeanUtils;
import org.jinouts.ws.MbrDetail;
import org.jinouts.ws.Request;
import org.jinouts.ws.TestComplexRequest;

/**
 * @author asraf asraf344@gmail.com
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
	
	public static String getStringForArray ( String[] array )
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

	public static String getStringForList ( List<String> list )
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

	public static String getStringForArrayAndList ( ArrayList<String> list, String[] array )
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
						String attrName = f.getName ( );
						if ( !f.getType ( ).equals ( XMLGregorianCalendar.class ) )
						{
							String value = BeanUtils.getProperty ( obj, f.getName ( ) );
							result += attrName + ":" + value;

						}
						else
						{
							System.out.println ( "Got calendar for : " + attrName );
							XMLGregorianCalendar xmc = (XMLGregorianCalendar) ReflectionHelper.invokeGetter ( obj, f.getName ( ) );
							String value = null;
							if ( xmc != null )
							{
								value = xmc.toGregorianCalendar ( ).getTime ( ).toString ( );
							}

							result += attrName + ":" + value;

						}

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

	public static String getStringForObjList ( List<?> list )
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

	public static String getStringForCokTelRequest ( Request req )
	{
		String result = "";

		try
		{

			result += req.getApplId ( ) + req.getDoubleId ( ) + req.getFloatId ( ) + getStringForList ( req.getStrArray ( ) ) + getStringForList ( req.getList ( ) );
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}

		return result;
	}

	public static String getStringForComplexRequest ( TestComplexRequest req )
	{
		String result = "";

		try
		{

			result += req.getApplId ( ) + req.getDoubleId ( ) + req.getFloatId ( );
			result += getStringForList ( req.getStrArray ( ) ) + getStringForList ( req.getList ( ) );
			result += getStringForObjList ( req.getMbrDetail ( ) );
		}
		catch ( Exception ex )
		{
			result = SERVER_ERROR;
			ex.printStackTrace ( );
		}

		return result;
	}

	public static  boolean isTwoMemberEquaul ( MbrDetail mbrDetail1, MbrDetail mbrDetail2 )
	{
		// check for addr1
		if ( mbrDetail1.getAddress1 ( ) != null )
		{
			if ( !mbrDetail1.getAddress1 ( ).equals ( mbrDetail2.getAddress1 ( ) ) )
			{
				return false;
			}
		}

		// check for addr2
		if ( mbrDetail1.getAddress2 ( ) != null )
		{
			if ( !mbrDetail1.getAddress2 ( ).equals ( mbrDetail2.getAddress2 ( ) ) )
			{
				return false;
			}
		}

		// check for addr2
		if ( mbrDetail1.getZip ( ) != null )
		{
			if ( !mbrDetail1.getZip ( ).equals ( mbrDetail2.getZip ( ) ) )
			{
				return false;
			}
		}
		
		// check for addr2
		/*if ( mbrDetail1.getRegistrationDate ( )  != null )
		{
			if ( !mbrDetail1.getRegistrationDate ( ).equals ( mbrDetail2.getRegistrationDate ( ) ) )
			{
				System.out.println ("Mismatch for date");
				return false;
			}
		}*/

		return true;
	}
	
	public static List<MbrDetail>  getDummyMemberList ()
	{
		List<MbrDetail> mbrList = new ArrayList<MbrDetail> ( );
		Date date = new Date();
		
		MbrDetail mbr1 = new MbrDetail ( );
		mbr1.setAddress2 ( "add11" );
		mbr1.setZip ( "zip" );
		mbr1.setAddress1 ( "add11111" );		
	//	mbr1.setRegistrationDate ( date );
		mbrList.add ( mbr1 );
		
		MbrDetail mbr2 = new MbrDetail ( );
		mbr2.setAddress2 ( "add12" );
		mbr2.setZip ( "zip22" );
		mbr2.setAddress1 ( "add22" );		
		//mbr2.setRegistrationDate ( date );
		mbrList.add ( mbr2 );
		
		MbrDetail mbr3 = new MbrDetail ( );
		mbr3.setAddress2 ( "add31" );
		mbr3.setZip ( "zip3" );
		mbr3.setAddress1 ( "add31" );		
		//mbr3.setRegistrationDate ( date );
		mbrList.add ( mbr3 );
		
		return mbrList;
	}

}
