/**
 * 
 */
package org.jinos.ws.util;

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
 *
 */
public class XMLUtils
{
	public static String getXMLForKeyValue ( Object objValue, String valueName,
			String tagInitial, boolean formatted, int level )
	{
		String NEWLINE = "";
		String INDENT = "";
		String INDENT_STRING = "  ";

		// Handle case where we don't want parameters map key to be top-level
		// element,
		// in that case use magic key of "--" TODO: CJW
		boolean hasEnclosingElement = "--".equals ( valueName ) ? false : true;

		if ( objValue == null )
		{
			return null;
		}

		if ( objValue.getClass ( ).getPackage ( ).getName ( )
				.startsWith ( "java." ) )
		{
			return getXMLTagWithNameValue ( valueName, objValue.toString ( ), tagInitial );
		}

		

		StringBuilder sb = new StringBuilder ( );

		String valueClassName = objValue.getClass ( ).getSimpleName ( );

		// Handle case where we don't want parameters map key to be top-level
		// element
		// TODO: CJW
		if ( hasEnclosingElement || level > 0 )
		{
			if ( valueName != null && level == 0 )
			{
				valueClassName = valueName;
			}
			else
			{
				StringBuilder nameSB = new StringBuilder ( );
				nameSB.append ( valueClassName );
				nameSB.setCharAt ( 0,
						Character.toLowerCase ( nameSB.charAt ( 0 ) ) );
				valueClassName = nameSB.toString ( );
			}

			// if (level>0)
			// sb.append(NEWLINE);
			//sb.append ( INDENT ).append ( "<" ).append ( name ).append ( ">" );
			sb.append ( INDENT ).append ( "<" ).append ( tagInitial == null ? "" : (tagInitial + ":") )
			.append ( valueClassName ).append ( ">");
			sb.append ( NEWLINE );
		}

		Field[] fields = ReflectionHelper.iterateFields ( objValue.getClass ( ) );

		int filteredFieldsSize = 0;
		for ( int count = 0; count < fields.length; count++ )
		{
			Field field = fields[count];
			if ( (field.getModifiers ( ) & Modifier.STATIC) != 0 ) // NOPMD -
																	// ConfusingTernary
			{
				continue;
			}
			else if ( field.getName ( ).startsWith ( "__" ) )
			{ // skip internal Axis-generated fields
				continue;
			}
			fields[filteredFieldsSize] = field;
			filteredFieldsSize++;
		}

		for ( int count = 0; count < filteredFieldsSize; count++ )
		{
			Field field = fields[count];
			String fieldName = field.getName ( );

			field.setAccessible ( true );
			Object value;
			try
			{
				value = field.get ( objValue );
			}
			catch ( Exception e )
			{
				throw new RuntimeException ( "Cannot get field value '"
						+ fieldName + "': " + e );
			}

			if ( value == null )
			{
				continue;
			}

			if ( value instanceof Object[] )
			{
				Object[] array = (Object[]) value;
				for ( int arrayCount = 0; arrayCount < array.length; arrayCount++ )
				{
					Object itemValue = array[arrayCount];
					sb.append ( getXMLForKeyValue ( itemValue,
							valueName, tagInitial, formatted, level + 1 ) );
				}
			}
			else if ( value.getClass ( ).isArray ( ) )
			{
				int arrayLength = Array.getLength ( value );
				Class componentClass = value.getClass ( ).getComponentType ( );
				if ( componentClass.equals ( byte.class ) )
				{
					sb.append ( INDENT ).append ( INDENT_STRING );
					sb.append ( "<" )
							.append ( tagInitial == null ? "" : (tagInitial + ":") )
							.append ( fieldName )
							.append ( "><!-- HEX VALUE: -->" );
					byte[] byteArray = (byte[]) value;
					for ( int arrayCount = 0; arrayCount < arrayLength; arrayCount++ )
					{
						if ( byteArray[arrayCount] >= 0
								&& byteArray[arrayCount] < 10 )
						{
							sb.append ( '0' );
						}
						sb.append ( Integer
								.toHexString ( byteArray[arrayCount] < 0 ? byteArray[arrayCount] + 256
										: byteArray[arrayCount] ) );
					}
					sb.append ( "</" )
							.append ( tagInitial == null ? "" : (tagInitial + ":") )
							.append ( fieldName ).append ( ">" );
					sb.append ( NEWLINE );
				}
				else
				{
					for ( int arrayCount = 0; arrayCount < arrayLength; arrayCount++ )
					{
						Object itemValue = Array.get ( value, arrayCount );
						sb.append ( getXMLForKeyValue ( itemValue,
								valueName, tagInitial, formatted, level + 1 ) );
					}
				}
			}
			else if ( value instanceof List )
			{
				List listValue = (List) value;
				for ( int listCount = 0; listCount < listValue.size ( ); listCount++ )
				{
					Object itemValue = listValue.get ( listCount );
					sb.append ( getXMLForKeyValue ( itemValue,
							valueName, tagInitial, formatted, level + 1 ) );
				}
			}
			else if ( value instanceof Date )
			{
				SimpleDateFormat formatter = new SimpleDateFormat (
						"yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH );
				sb.append ( INDENT ).append ( INDENT_STRING );
				sb.append ( "<" )
						.append ( tagInitial == null ? "" : (tagInitial + ":") )
						.append ( fieldName ).append ( ">" );
				// formatter.format((Date)value, sb, 0);
				sb.append ( formatter.format ( (Date) value ) );
				sb.append ( "</" )
						.append ( tagInitial == null ? "" : (tagInitial + ":") )
						.append ( fieldName ).append ( ">" );
				if ( formatted )
					sb.append ( NEWLINE );

			}
			else if ( value instanceof XMLGregorianCalendar)
			{
				sb.append ( getXMLTagWithNameValue ( fieldName, value.toString ( ), tagInitial ) );
				
			}
			
			else
			{
				 getXMLTagWithNameValue ( fieldName, getXMLForKeyValue ( value, valueName,
							tagInitial, formatted, level + 1 ), tagInitial );
				
			}
		}

		if ( hasEnclosingElement || level > 0 )
		{
			sb.append ( INDENT ).append ( "</" )
					.append ( tagInitial == null ? "" : (tagInitial + ":") )
					.append ( valueClassName ).append ( ">" ).append ( NEWLINE );
		}

		return sb.toString ( );
	}
	
	public static String getXMLStartTag ( String tagName  )
	{
		return getXMLStartTag( tagName, XMLConstants.SOAP_WS_TAG_INITIAL );
	}
	
	public static String getXMLEndTag ( String tagName )
	{
		return getXMLEndTag( tagName, XMLConstants.SOAP_WS_TAG_INITIAL );
	}
	
	public static String getXMLStartTag ( String tagName, String tagInitial )
	{
		return "<"+tagInitial + ":" +tagName+">";
	}
	
	public static String getXMLEndTag ( String tagName, String tagInitial )
	{
		return XMLConstants.TAG_ENDING+tagInitial + ":" +tagName +">" ; 
	}
	
	public static String getXMLTagWithNameValue ( String tagName, String value, String tagInitial )
	{
		StringBuilder sb = new StringBuilder ( );
		sb.append ( getXMLStartTag ( tagName, tagInitial ) ); 
		sb.append ( value );
		sb.append ( getXMLEndTag ( tagName, tagInitial ) );
		
		return sb.toString ( );
	}

}
