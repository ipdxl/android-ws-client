/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html * 
 * 
 */
package org.jinouts.ws.util;

import hu.javaforum.commons.ReflectionHelper;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

/**
* @author asraf
* asraf344@gmail.com
* 
*/
public class WSRequestXMLUtils
{
	public static String getXMLForKeyValue ( Object valueObj, String name, String tagInitial, int level )
	{
		// String builder to append the String
		StringBuilder sb = new StringBuilder ( );

		// if value is null just return null
		if ( valueObj == null )
		{
			return null;
		}

		// if its primitive type then just call toString
		if ( isToStringable ( valueObj ) )
		{
			return XMLUtils.getXMLTagWithNameValue ( name, valueObj.toString ( ), tagInitial );
		}

		boolean valueProcessed = appendXMLForJavaConvertableObject ( sb, valueObj, name, name, tagInitial, level );
		
		// get class name
		String valueClassName = valueObj.getClass ( ).getSimpleName ( );

		
		if ( name != null && level == 0 )
		{
			valueClassName = name;
		}
		else
		{
			StringBuilder nameSB = new StringBuilder ( );
			nameSB.append ( valueClassName );
			// make the first character lower case
			nameSB.setCharAt ( 0, Character.toLowerCase ( nameSB.charAt ( 0 ) ) );
			valueClassName = nameSB.toString ( );
		}


		
		if ( !valueProcessed )
		{
			// now get the start tag
			sb.append ( XMLUtils.getXMLStartTag ( valueClassName, tagInitial ) );
			// its a businness object so get the xml for this object
			getXMLForBizObject ( sb, valueObj, valueClassName, name, tagInitial, level );
			sb.append ( XMLUtils.getXMLEndTag ( valueClassName, tagInitial ) );
		}
		

		return sb.toString ( );
	}

	public static void getXMLForBizObject ( StringBuilder sb, Object valueObj, String valueClassName, String name, String tagInitial, int level )
	{
		// get the field list from the object
		Field[] fields = ReflectionHelper.iterateFields ( valueObj.getClass ( ) );

		// now fitered the list
		int filteredFieldsSize = 0;
		for ( int count = 0; count < fields.length; count++ )
		{
			Field field = fields[count];
			if ( (field.getModifiers ( ) & Modifier.STATIC) != 0 )

			{
				continue;
			}
			else if ( field.getName ( ).startsWith ( "__" ) )
			{
				continue;
			}
			fields[filteredFieldsSize] = field;
			filteredFieldsSize++;
		}

		// now get the xml for the fields in the object
		for ( int count = 0; count < filteredFieldsSize; count++ )
		{
			Field field = fields[count];
			String fieldName = field.getName ( );

			field.setAccessible ( true );
			Object value;
			try
			{
				value = field.get ( valueObj );
			}
			catch ( Exception e )
			{
				throw new RuntimeException ( "Cannot get  value for the field '"
						+ fieldName + "': " + e );
			}

			if ( value == null )
			{
				continue;
			}
			
			boolean valueProcessed = appendXMLForJavaConvertableObject ( sb, value, fieldName, valueClassName, tagInitial, level+1 );
			
			// else its another object or some primitive
			if ( !valueProcessed )
			{
				sb.append ( getXMLForKeyValue ( value, fieldName, tagInitial, level + 1 ) );

			}
		}
	}

	public static boolean appendXMLForJavaConvertableObject ( StringBuilder sb, Object value, String fieldName, String valueClassName, String tagInitial, int level )
	{

		boolean isConvertable = false;
		// / if its array of object
		if ( value instanceof Object[] )
		{
			getXMLForObjArray ( sb, value, fieldName, tagInitial, level );
			isConvertable = true;
		}

		// if its some primitive array
		else if ( value.getClass ( ).isArray ( ) )
		{
			getXMLForArray ( sb, value, valueClassName, fieldName, tagInitial, level );
			isConvertable = true;
		}
		// if its some list
		else if ( value instanceof List )
		{
			List listValue = (List) value;
			getXMLForList ( sb, listValue, fieldName, tagInitial, level );
			isConvertable = true;
		}

		// if its some date
		else if ( value instanceof Date )
		{
			getXMLForDate ( sb, (Date) value, fieldName, tagInitial, level );
			isConvertable = true;

		}

		// if its some date value
		else if ( value instanceof XMLGregorianCalendar )
		{
			sb.append ( XMLUtils.getXMLTagWithNameValue ( fieldName, value.toString ( ), tagInitial ) );
			isConvertable = true;
		}
		
		return isConvertable;
	}

	public static void getXMLForDate ( StringBuilder sb, Date date, String name, String tagInitial, int level )
	{

		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH );

		sb.append ( XMLUtils.getXMLStartTag ( name, tagInitial ) );
		// formatter.format((Date)value, sb, 0);
		sb.append ( formatter.format ( date ) );
		sb.append ( XMLUtils.getXMLEndTag ( name, tagInitial ) );

	}

	public static void getXMLForList ( StringBuilder sb, List listValue, String name, String tagInitial, int level )
	{

		for ( int listCount = 0; listCount < listValue.size ( ); listCount++ )
		{
			Object itemValue = listValue.get ( listCount );
			sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level  ) );
		}

	}

	public static void getXMLForObjArray ( StringBuilder sb, Object value, String name, String tagInitial, int level )
	{
		Object[] array = (Object[]) value;
		for ( int arrayCount = 0; arrayCount < array.length; arrayCount++ )
		{
			Object itemValue = array[arrayCount];
			sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level + 1 ) );
		}
	}

	public static void getXMLForArray ( StringBuilder sb, Object value, String name, String fieldName, String tagInitial, int level )
	{

		int arrayLength = Array.getLength ( value );
		Class componentClass = value.getClass ( ).getComponentType ( );
		if ( componentClass.equals ( byte.class ) )
		{
			sb.append ( XMLUtils.getXMLStartTag ( fieldName, tagInitial ) );
			sb.append ( "><!-- HEX VALUE: -->" );

			byte[] byteArray = (byte[]) value;
			for ( int arrayCount = 0; arrayCount < arrayLength; arrayCount++ )
			{
				if ( byteArray[arrayCount] >= 0 && byteArray[arrayCount] < 10 )
				{
					sb.append ( '0' );
				}
				sb.append ( Integer.toHexString ( byteArray[arrayCount] < 0 ? byteArray[arrayCount] + 256
						: byteArray[arrayCount] ) );
			}
			sb.append ( XMLUtils.getXMLEndTag ( fieldName, tagInitial ) );

		}
		else
		{
			for ( int arrayCount = 0; arrayCount < arrayLength; arrayCount++ )
			{
				Object itemValue = Array.get ( value, arrayCount );
				sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level + 1 ) );
			}
		}

	}

	public static String getXMLForDate ( List listValue, String name, String tagInitial, int level )
	{
		StringBuilder sb = new StringBuilder ( );
		for ( int listCount = 0; listCount < listValue.size ( ); listCount++ )
		{
			Object itemValue = listValue.get ( listCount );
			sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level + 1 ) );
		}

		return sb.toString ( );
	}

	public static boolean isToStringable ( Object valueObj )
	{
		try
		{
			if ( valueObj.getClass ( ).getPackage ( ).getName ( ).startsWith ( "java.lang" ) )
				return true;
		}

		catch ( Exception ex )
		{
			ex.printStackTrace ( );
		}

		return false;
	}

}
