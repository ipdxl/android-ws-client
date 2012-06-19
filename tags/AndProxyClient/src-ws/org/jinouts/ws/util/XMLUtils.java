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

import com.eviware.soapui.impl.wsdl.support.soap.SoapVersion;

/**
* @author asraf
* asraf344@gmail.com
* 
*/
public class XMLUtils
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
		if ( isToString( valueObj ) )
		{
			return getXMLTagWithNameValue ( name, valueObj.toString ( ), tagInitial );
		}

		// get class name
		String valueClassName = valueObj.getClass ( ).getSimpleName ( );

		// if its not the first tag-value then get the name as priority
		//if ( level > 0 )
		//{
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

			// now get the start tag
			sb.append ( getXMLStartTag ( valueClassName, tagInitial ) );

		//}

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

			// / if its array of object
			if ( value instanceof Object[] )
			{
				Object[] array = (Object[]) value;
				for ( int arrayCount = 0; arrayCount < array.length; arrayCount++ )
				{
					Object itemValue = array[arrayCount];
					sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level + 1 ) );
				}
			}

			// if its some primitive array
			else if ( value.getClass ( ).isArray ( ) )
			{
				int arrayLength = Array.getLength ( value );
				Class componentClass = value.getClass ( ).getComponentType ( );
				if ( componentClass.equals ( byte.class ) )
				{
					sb.append ( getXMLStartTag ( fieldName, tagInitial ) );
					sb.append ( "><!-- HEX VALUE: -->" );

					byte[] byteArray = (byte[]) value;
					for ( int arrayCount = 0; arrayCount < arrayLength; arrayCount++ )
					{
						if ( byteArray[arrayCount] >= 0
								&& byteArray[arrayCount] < 10 )
						{
							sb.append ( '0' );
						}
						sb.append ( Integer.toHexString ( byteArray[arrayCount] < 0 ? byteArray[arrayCount] + 256
								: byteArray[arrayCount] ) );
					}
					sb.append ( getXMLEndTag ( fieldName, tagInitial ) );

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
			// if its some list
			else if ( value instanceof List )
			{
				List listValue = (List) value;
				for ( int listCount = 0; listCount < listValue.size ( ); listCount++ )
				{
					Object itemValue = listValue.get ( listCount );
					sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level + 1 ) );
				}
			}

			// if its some date
			else if ( value instanceof Date )
			{
				SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH );

				sb.append ( getXMLStartTag ( fieldName, tagInitial ) );
				// formatter.format((Date)value, sb, 0);
				sb.append ( formatter.format ( (Date) value ) );
				sb.append ( getXMLEndTag ( fieldName, tagInitial ) );

			}

			// if its some date value
			else if ( value instanceof XMLGregorianCalendar )
			{
				sb.append ( getXMLTagWithNameValue ( fieldName, value.toString ( ), tagInitial ) );
			}

			else
			{
				sb.append ( getXMLForKeyValue ( value, fieldName, tagInitial, level + 1 ) );

			}
		}

	//	if ( level > 0 )
		//{
			sb.append ( getXMLEndTag ( valueClassName, tagInitial ) );

		//}

		return sb.toString ( );
	}

	public static String getXMLForList( List listValue, String name, String tagInitial, int level )
	{
		StringBuilder sb = new StringBuilder ( );
		for ( int listCount = 0; listCount < listValue.size ( ); listCount++ )
		{
			Object itemValue = listValue.get ( listCount );
			sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level + 1 ) );
		}
		
		return sb.toString ( );
	}
	
	public static String getXMLForArray( Object value, String name, String fieldName, String tagInitial, int level )
	{
		StringBuilder sb = new StringBuilder ( );
		
		int arrayLength = Array.getLength ( value );
		Class componentClass = value.getClass ( ).getComponentType ( );
		if ( componentClass.equals ( byte.class ) )
		{
			sb.append ( getXMLStartTag ( fieldName, tagInitial ) );
			sb.append ( "><!-- HEX VALUE: -->" );

			byte[] byteArray = (byte[]) value;
			for ( int arrayCount = 0; arrayCount < arrayLength; arrayCount++ )
			{
				if ( byteArray[arrayCount] >= 0
						&& byteArray[arrayCount] < 10 )
				{
					sb.append ( '0' );
				}
				sb.append ( Integer.toHexString ( byteArray[arrayCount] < 0 ? byteArray[arrayCount] + 256
						: byteArray[arrayCount] ) );
			}
			sb.append ( getXMLEndTag ( fieldName, tagInitial ) );

		}
		else
		{
			for ( int arrayCount = 0; arrayCount < arrayLength; arrayCount++ )
			{
				Object itemValue = Array.get ( value, arrayCount );
				sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level + 1 ) );
			}
		}
		
		return sb.toString ( );
	}
	
	public static String getXMLForDate( List listValue, String name, String tagInitial, int level )
	{
		StringBuilder sb = new StringBuilder ( );
		for ( int listCount = 0; listCount < listValue.size ( ); listCount++ )
		{
			Object itemValue = listValue.get ( listCount );
			sb.append ( getXMLForKeyValue ( itemValue, name, tagInitial, level + 1 ) );
		}
		
		return sb.toString ( );
	}
	
	public static boolean isToString ( Object valueObj )
	{
		if ( valueObj.getClass ( ).getPackage ( ).getName ( ).startsWith ( "java." ) )
			return true;
		
		else
			return false;
	}
	
	public static String getXMLStartTag ( String tagName )
	{
		return getXMLStartTag ( tagName, XMLConstants.SOAP_WS_TAG_INITIAL );
	}

	public static String getXMLEndTag ( String tagName )
	{
		return getXMLEndTag ( tagName, XMLConstants.SOAP_WS_TAG_INITIAL );
	}

	public static String getXMLStartTag ( String tagName, String tagInitial )
	{
		StringBuilder sb = new StringBuilder ( );

		// now append the tag
		sb.append ( "<" ).append ( tagInitial == null ? "" : (tagInitial + ":") ).append ( tagName ).append ( ">" );

		return sb.toString ( );
	}

	public static String getXMLEndTag ( String tagName, String tagInitial )
	{
		StringBuilder sb = new StringBuilder ( );

		sb.append ( XMLConstants.TAG_ENDING );
		sb.append ( tagInitial == null ? "" : (tagInitial + ":") ).append ( tagName ).append ( ">" );

		return sb.toString ( );
	}

	public static String getXMLTagWithNameValue ( String tagName, String value, String tagInitial )
	{
		StringBuilder sb = new StringBuilder ( );
		sb.append ( getXMLStartTag ( tagName, tagInitial ) );
		sb.append ( value );
		sb.append ( getXMLEndTag ( tagName, tagInitial ) );

		return sb.toString ( );
	}

	public static String getFullRequestXmlFromBody ( SoapVersion soapVersion, String bodyXML, String appNameSpace )
	{
		StringBuilder sb = new StringBuilder ( );
		String envelopeXML = getEnvelopXMLFromSoapVersion ( soapVersion, appNameSpace );
		sb.append ( envelopeXML );
		sb.append ( XMLConstants.HEADER_SELF_ENDING );
		sb.append ( XMLConstants.SOAP_BODY_START );
		sb.append ( bodyXML );
		sb.append ( XMLConstants.SOAP_BODY_END );
		sb.append ( XMLConstants.SOAP_ENVELOPE_END_TAG );

		return sb.toString ( );
	}

	public static String getEnvelopXMLFromSoapVersion ( SoapVersion soapVersion, String appNameSpace )
	{
		StringBuilder sb = new StringBuilder ( );
		sb.append ( XMLConstants.SOAP_ENVELOPE_START_TAG );
		sb.append ( XMLConstants.SOAP_ENV_NS_TAG );
		sb.append ( "=\"" + soapVersion.getEnvelopeNamespace ( ) + "\" " );
		sb.append ( XMLConstants.APP_NS_TAG );
		sb.append ( "=\"" + appNameSpace + "\" >" );

		return sb.toString ( );
	}

}
