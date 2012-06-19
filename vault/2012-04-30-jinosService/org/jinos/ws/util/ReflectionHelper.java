/**
 * CC-LGPL 2.1
 * http://creativecommons.org/licenses/LGPL/2.1/
 */
package org.jinos.ws.util;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The ReflectionHelper is provides static methods to invoke getter and setter
 * methods in bean instances
 * 
 * Changelog: ANDROIDSOAP-6 - 2011-01-08 ANDROIDSOAP-1 - 2011-01-06 JFPORTAL-94
 * (2010-02-24) JFPORTAL-94 (2009-11-01) JFPORTAL-79 (2009-10-11) JFPORTAL-79
 * (2009-09-12) JFPORTAL-78 (2009-09-03) First implementation (2009-05-26)
 * 
 * @author Auth Gábor <auth.gabor@javaforum.hu>
 * @author Chris Wolf
 */
@SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.ExcessiveMethodLength",
		"PMD.NcssMethodCount", "PMD.NPathComplexity" })
public final class ReflectionHelper
{

	/**
	 * The private constructor, because all methods are static
	 */
	private ReflectionHelper()
	{
		super ( );
	}

	/**
	 * Returns a value of the field
	 * 
	 * @param instance
	 *            The bean
	 * @param fieldName
	 *            The field name of the bean
	 * @return The value; null, if the field is not found
	 */
	public static Object invokeGetter ( Object instance, String fieldName )
	{
		if ( instance != null )
		{
			return invokeGetter ( instance.getClass ( ), instance, fieldName );
		}

		return null;
	}

	/**
	 * Return a value of the field
	 * 
	 * @param instanceClass
	 *            The class of the instance
	 * @param instance
	 *            The bean
	 * @param fieldName
	 *            The field name of the bean
	 * @return The value; null, if the field is not found
	 */
	@SuppressWarnings("rawtypes")
	private static Object invokeGetter ( Class instanceClass, Object instance,
			String fieldName )
	{
		try
		{
			if ( "java.lang.Object".equals ( instanceClass.getName ( ) ) )
			{
				return null;
			}

			Method method = getGetterMethod ( instanceClass, instance,
					fieldName );
			if ( method != null )
			{
				return method.invoke ( instance );
			}
		}
		catch ( Exception except )
		{
			// Log.e(ReflectionHelper.class.getSimpleName(), except.toString(),
			// except);
			except.printStackTrace ( );
		}

		return null;
	}

	/**
	 * Set the field value in the bean instance
	 * 
	 * @param instance
	 *            The bean instance
	 * @param fieldName
	 *            The field name
	 * @param value
	 *            The new value
	 */
	public static boolean invokeSetter ( Object instance, String fieldName,
			Object value )
	{
		if ( instance != null )
		{
			return invokeSetter ( instance.getClass ( ), instance, fieldName,
					value );
		}

		return false;
	}

	/**
	 * Set the field value in the bean instance
	 * 
	 * @param instanceClass
	 *            The class of the instance
	 * @param instance
	 *            The bean instance
	 * @param fieldName
	 *            The field name
	 * @param value
	 *            The new value
	 * @todo Eliminate the SuppressWarnings annotation
	 */
	@SuppressWarnings({ "PMD.CyclomaticComplexity",
			"PMD.ExcessiveMethodLength", "PMD.NcssMethodCount",
			"PMD.NPathComplexity", "rawtypes", "unchecked" })
	private static boolean invokeSetter ( Class instanceClass, Object instance,
			String fieldName, Object value )
	{
		/**
		 * numerous changes to support primitive types other then string as well
		 * as support object array fields on objects. TODO: CJW
		 */
		if ( "java.lang.Object".equals ( instanceClass.getName ( ) ) )
		{
			return false;
		}

		String lastFieldName = fieldName;
		Object parameter = value;
		String methodName = null;
		Field field = null;
		Class fieldClass = null;

		// need to calculate method name early for error reporting
		StringBuilder sb = new StringBuilder ( lastFieldName.length ( ) + 3 );
		sb.append ( "set" );
		sb.append ( lastFieldName );
		sb.setCharAt ( 3, Character.toUpperCase ( sb.charAt ( 3 ) ) );
		methodName = sb.toString ( );

		try
		{
			if ( instance != null && lastFieldName != null && value != null )
			{
				if ( lastFieldName.indexOf ( "." ) > -1 )
				{
					String parentFieldName = lastFieldName.substring ( 0,
							lastFieldName.indexOf ( "." ) );
					lastFieldName = lastFieldName.substring ( lastFieldName
							.indexOf ( "." ) + 1 );
					Object parent = invokeGetter ( instance, parentFieldName );
					if ( parent == null )
					{
						return false;
					}
					else
					{
						System.out.println ( "Invoking " + parentFieldName
								+ "." + lastFieldName + "(Object object)" );
						return invokeSetter ( parent, lastFieldName, value );
					}
				}

				/**
				 * If the field's class and the value's class are not equal,
				 * then need to cast or convert the value to the field's class
				 */
				field = null;

				try
				{
					/**
					 * TODO: Do it, if field is another protected keyword...
					 */
					if ( "return".equals ( lastFieldName ) )
					{
						field = instanceClass.getDeclaredField ( "_"
								+ lastFieldName );
					}
					else
					{
						field = instanceClass.getDeclaredField ( lastFieldName );
					}
					fieldClass = field.getType ( );
				}
				catch ( Exception except )
				{
					System.out.println ( ReflectionHelper.class
							.getSimpleName ( ) + "*" + except.toString ( ) );
				}

				// Class parameterClass = value == null ? null :
				// value.getClass();// NOPMD - UnusedLocalVariable
				if ( field == null )
				{
					field = instanceClass.getDeclaredField ( "_"
							+ lastFieldName );
					// parameterClass = field.getType();
				}

				if ( !value.getClass ( ).equals ( field.getType ( ) ) )
				{
					String stringValue = null;
					if ( value instanceof String )
					{
						stringValue = (String) value;
					}
					else if ( value instanceof String[] )
					{
						String[] stringArrayValue = (String[]) value;
						if ( stringArrayValue.length != 1 )
						{
							System.out
									.println ( ReflectionHelper.class
											.getSimpleName ( )
											+ "More than one element found in the String array..." );
							return false;
						}

						stringValue = stringArrayValue[0];
					} /*
					 * else { Log.e(ReflectionHelper.class.getSimpleName(),
					 * "Type \"" + ftype.getName() + "\" not yet supported");
					 * return false; }
					 */

					if ( field.getType ( ).equals ( String.class ) )
					{
						parameter = stringValue;
					}
					/*
					 * else if (fieldClass.equals(byte[].class)) { parameter =
					 * Base64Encoder.decode(stringValue); }
					 */
					else if ( fieldClass.equals ( short.class )
							|| fieldClass.equals ( Short.class ) )
					{
						parameter = Short.parseShort ( stringValue );
					}
					else if ( fieldClass.equals ( int.class )
							|| fieldClass.equals ( Integer.class ) )
					{
						parameter = Integer.parseInt ( stringValue );
					}
					else if ( fieldClass.equals ( long.class )
							|| fieldClass.equals ( Long.class ) )
					{
						parameter = Long.parseLong ( stringValue );

					}
					else if ( fieldClass.equals ( boolean.class )
							|| fieldClass.equals ( Boolean.class ) )
					{
						parameter = Boolean.parseBoolean ( stringValue );
					}
					else if ( fieldClass.equals ( float.class )
							|| fieldClass.equals ( Float.class ) )
					{
						parameter = Float.parseFloat ( stringValue );
					}
					else if ( fieldClass.equals ( double.class )
							|| fieldClass.equals ( Double.class ) )
					{
						parameter = Double.parseDouble ( stringValue );
					}
					else if ( fieldClass.equals ( Calendar.class ) )
					{
						if ( stringValue != null
								&& stringValue
										.matches ( "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9]" ) )
						{
							DateFormat format = new SimpleDateFormat (
									"yyyy-MM-dd HH:mm:ss", Locale.ENGLISH );
							Calendar cal = Calendar.getInstance ( );
							cal.setTime ( format.parse ( stringValue ) );
							parameter = (Calendar) cal;
						}
						else if ( stringValue != null
								&& stringValue
										.matches ( "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]" ) )
						{
							DateFormat format = new SimpleDateFormat (
									"yyyy-MM-dd HH:mm", Locale.ENGLISH );
							Calendar cal = Calendar.getInstance ( );
							cal.setTime ( format.parse ( stringValue ) );
							parameter = (Calendar) cal;
						}
						else if ( stringValue != null
								&& stringValue
										.matches ( "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]" ) )
						{
							DateFormat format = new SimpleDateFormat (
									"yyyy-MM-dd", Locale.ENGLISH );
							Calendar cal = Calendar.getInstance ( );
							cal.setTime ( format.parse ( stringValue ) );
							parameter = (Calendar) cal;
						}
						else
						{
							System.out
									.println ( ReflectionHelper.class
											.getSimpleName ( )
											+ "Unsupported date format: "
											+ stringValue );
							parameter = null;
						}
					}
					else if ( fieldClass.equals ( Date.class ) )
					{
						if ( stringValue != null
								&& stringValue
										.matches ( "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]T[0-9][0-9]:[0-9][0-9]:[0-9][0-9].*" ) )
						{
							DateFormat format = new SimpleDateFormat (
									"yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH );
							parameter = format.parse ( stringValue );
						}
						else if ( stringValue != null
								&& stringValue
										.matches ( "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]:[0-9][0-9]" ) )
						{
							DateFormat format = new SimpleDateFormat (
									"yyyy-MM-dd HH:mm:ss", Locale.ENGLISH );
							parameter = format.parse ( stringValue );
						}
						else if ( stringValue != null
								&& stringValue
										.matches ( "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9] [0-9][0-9]:[0-9][0-9]" ) )
						{
							DateFormat format = new SimpleDateFormat (
									"yyyy-MM-dd HH:mm", Locale.ENGLISH );
							parameter = format.parse ( stringValue );
						}
						else if ( stringValue != null
								&& stringValue
										.matches ( "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]" ) )
						{
							DateFormat format = new SimpleDateFormat (
									"yyyy-MM-dd", Locale.ENGLISH );
							parameter = format.parse ( stringValue );
						}
						else
						{
							System.out
									.println ( ReflectionHelper.class
											.getSimpleName ( )
											+ "Unsupported date format: "
											+ stringValue );
							parameter = null;
						}
					}
					else if ( fieldClass.isArray ( ) ) // array of domain
														// objects
					{
						// parameter = ftype.cast(value); no such luck...

						String arrayElementTypeName = fieldClass.getName ( )
								.substring ( 2,
										fieldClass.getName ( ).length ( ) - 1 );
						Class arrayElementType = Class
								.forName ( arrayElementTypeName );
						Object[] at = (Object[]) Array.newInstance (
								arrayElementType, 0 );
						parameter = ((List<Object>) value).toArray ( at );

					}
					else
					{
						System.out.println ( ReflectionHelper.class
								.getSimpleName ( )
								+ "The '"
								+ field.getType ( ).getName ( )
								+ "' type isn't supported yet (fieldName was '"
								+ lastFieldName + "')" );
						return false;
					}
				}

				/**
				 * What is this?! :)
				 */
				// if (parameter != null)
				// {
				// parameterClass = parameter.getClass();
				// if (parameter instanceof Calendar)
				// {
				// parameterClass = Calendar.class;
				// } else if (parameter instanceof Date)
				// {
				// parameterClass = Date.class;
				// }
				// }
				Method method = instanceClass.getDeclaredMethod ( methodName,
						fieldClass );
				method.invoke ( instance, parameter );
				return true;
			}
		}
		catch ( NoSuchMethodException except )
		{
			System.out.println ( ReflectionHelper.class.getSimpleName ( )
					+ "Invoking (super) "
					+ instanceClass.getSuperclass ( ).getName ( ) + "."
					+ methodName + "(Object object)" );
			return invokeSetter ( instanceClass.getSuperclass ( ), instance,
					lastFieldName, parameter );
		}
		catch ( NoSuchFieldException except )
		{
			System.out.println ( ReflectionHelper.class.getSimpleName ( )
					+ "Invoking (super) "
					+ instanceClass.getSuperclass ( ).getName ( ) + "."
					+ methodName + "(Object object)" );
			return invokeSetter ( instanceClass.getSuperclass ( ), instance,
					lastFieldName, parameter );
		}
		catch ( Exception except )
		{
			System.out.println ( ReflectionHelper.class.getSimpleName ( )
					+ "Cannot invoke " + instanceClass.getName ( ) + "."
					+ methodName );
		}

		return false;
	}

	/**
	 * Returns 'true', it the field is exists in the bean instance
	 * 
	 * @param instance
	 *            The bean instance
	 * @param fieldName
	 *            The field name
	 * @return True, if the field is exists
	 */
	public static Boolean isGetterExists ( Object instance, String fieldName )
	{
		if ( instance != null )
		{
			return isGetterExists ( instance.getClass ( ), instance, fieldName );
		}

		return Boolean.FALSE;
	}

	/**
	 * Returns 'true', it the field is exists in the bean instance
	 * 
	 * @param instanceClass
	 *            The bean class
	 * @param instance
	 *            The bean instance
	 * @param fieldName
	 *            The field name
	 * @return True, if the field is exists
	 */
	private static Boolean isGetterExists ( @SuppressWarnings("rawtypes")
	Class instanceClass, Object instance, String fieldName )
	{
		return getGetterMethod ( instanceClass, instance, fieldName ) != null;
	}

	/**
	 * Return the getter method of the field
	 * 
	 * @param instanceClass
	 *            The bean class
	 * @param instance
	 *            The bean instance
	 * @param fieldName
	 *            The field name
	 * @return The getter method, if it is exists null, if the getter method is
	 *         not exists
	 */
	private static Method getGetterMethod ( @SuppressWarnings("rawtypes")
	Class instanceClass, Object instance, String fieldName )
	{
		if ( "java.lang.Object".equals ( instanceClass.getName ( ) ) )
		{
			return null;
		}

		try
		{
			StringBuilder sb = new StringBuilder ( fieldName.length ( ) + 3 );
			sb.append ( "get" );
			sb.append ( fieldName );
			sb.setCharAt ( 3, Character.toUpperCase ( sb.charAt ( 3 ) ) );
			return instanceClass.getDeclaredMethod ( sb.toString ( ) );
		}
		catch ( NoSuchMethodException except )
		{
			System.out.println ( ReflectionHelper.class.getSimpleName ( )
					+ "Invoking " + instanceClass.getSuperclass ( ).getName ( )
					+ "." + fieldName + "()" );
			return getGetterMethod ( instanceClass.getSuperclass ( ), instance,
					fieldName );
		}
		catch ( Exception except )
		{
			System.out.println ( ReflectionHelper.class.getSimpleName ( )
					+ except.toString ( ) );
		}

		return null;
	}

	/**
	 * Gets the class of the field
	 * 
	 * @param objectClass
	 *            The object
	 * @param fieldName
	 *            The field name of the object
	 * @return The class
	 */
	@SuppressWarnings("rawtypes")
	public static Class getFieldClass ( Class objectClass, String fieldName )
	{
		try
		{
			Field field = null;
			/**
			 * TODO: Do it, if field is another protected keyword...
			 */
			if ( "return".equals ( fieldName ) )
			{
				field = objectClass.getDeclaredField ( "_" + fieldName );
			}
			else
			{
				field = objectClass.getDeclaredField ( fieldName );
			}

			if ( field != null )
			{
				return field.getType ( );
			}
		}
		catch ( Exception except )
		{

			System.out.println ( ReflectionHelper.class.getSimpleName ( )
					+ except.toString ( ) );
		}

		return null;
	}

	/**
	 * Gets the first generic class of the field
	 * 
	 * @param objectClass
	 *            The object
	 * @param fieldName
	 *            The name of the field of the object
	 * @return The class
	 */
	@SuppressWarnings("rawtypes")
	public static Class getFieldGenericClass ( Class objectClass,
			String fieldName )
	{
		try
		{
			Field field = objectClass.getDeclaredField ( fieldName );
			Class fieldClass = getFieldClass ( objectClass, fieldName );
			if ( field != null )
			{
				Type returnType = field.getGenericType ( );

				if ( returnType instanceof ParameterizedType )
				{
					ParameterizedType type = (ParameterizedType) returnType;
					Type[] typeArguments = type.getActualTypeArguments ( );
					for ( Type typeArgument : typeArguments )
					{
						return (Class) typeArgument;
					}
				}
				else if ( fieldClass.isArray ( ) )
				{
					String arrayElementType = fieldClass.getName ( ).substring (
							2, fieldClass.getName ( ).length ( ) - 1 );
					return Class.forName ( arrayElementType );
				}
			}
		}
		catch ( Exception except )
		{
			System.out.println ( ReflectionHelper.class.getSimpleName ( )
					+ except.toString ( ) );
		}

		return null;
	}

	/**
	 * Dumps the object as XML
	 * 
	 * @param object
	 *            The object
	 * @param firstName
	 *            The name of the object
	 * @param nsprefix
	 *            The tags generated with this namespace prefix if it not null
	 * @param formatted
	 *            True, if the output is formatted
	 * @param level
	 *            Recursion level, starts with 0
	 * @return
	 */
	@SuppressWarnings({ "PMD.CyclomaticComplexity", "rawtypes" })
	public static String dumpXML ( Object object, String firstName,
			String nsprefix, boolean formatted, int level )
	{
		String NEWLINE = "";
		String INDENT = "";
		String INDENT_STRING = "  ";

		// Handle case where we don't want parameters map key to be top-level
		// element,
		// in that case use magic key of "--" TODO: CJW
		boolean hasEnclosingElement = "--".equals ( firstName ) ? false : true;

		if ( object == null )
		{
			return "";
		}

		if ( object.getClass ( ).getPackage ( ).getName ( )
				.startsWith ( "java." ) )
		{
			return object.toString ( );
		}

		if ( formatted )
		{
			NEWLINE = "\n";
			StringBuilder sb = new StringBuilder ( INDENT_STRING.length ( )
					* level );
			for ( int count = 0; count < level; count++ )
			{
				sb.append ( INDENT_STRING );
			}
			INDENT = sb.toString ( );
		}

		StringBuilder sb = new StringBuilder ( );

		String name = object.getClass ( ).getSimpleName ( );

		// Handle case where we don't want parameters map key to be top-level
		// element
		// TODO: CJW
		if ( hasEnclosingElement || level > 0 )
		{
			if ( firstName != null && level == 0 )
			{
				name = firstName;
			}
			else
			{
				StringBuilder nameSB = new StringBuilder ( );
				nameSB.append ( name );
				nameSB.setCharAt ( 0,
						Character.toLowerCase ( nameSB.charAt ( 0 ) ) );
				name = nameSB.toString ( );
			}

			// if (level>0)
			// sb.append(NEWLINE);
			//sb.append ( INDENT ).append ( "<" ).append ( name ).append ( ">" );
			sb.append ( INDENT ).append ( "<" ).append ( nsprefix == null ? "" : (nsprefix + ":") )
			.append ( name ).append ( ">");
			sb.append ( NEWLINE );
		}

		Field[] fields = iterateFields ( object.getClass ( ) );

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
				value = field.get ( object );
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
					sb.append ( ReflectionHelper.dumpXML ( itemValue,
							firstName, nsprefix, formatted, level + 1 ) );
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
							.append ( nsprefix == null ? "" : (nsprefix + ":") )
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
							.append ( nsprefix == null ? "" : (nsprefix + ":") )
							.append ( fieldName ).append ( ">" );
					sb.append ( NEWLINE );
				}
				else
				{
					for ( int arrayCount = 0; arrayCount < arrayLength; arrayCount++ )
					{
						Object itemValue = Array.get ( value, arrayCount );
						sb.append ( ReflectionHelper.dumpXML ( itemValue,
								firstName, nsprefix, formatted, level + 1 ) );
					}
				}
			}
			else if ( value instanceof List )
			{
				List listValue = (List) value;
				for ( int listCount = 0; listCount < listValue.size ( ); listCount++ )
				{
					Object itemValue = listValue.get ( listCount );
					sb.append ( ReflectionHelper.dumpXML ( itemValue,
							firstName, nsprefix, formatted, level + 1 ) );
				}
			}
			else if ( value instanceof Date )
			{
				SimpleDateFormat formatter = new SimpleDateFormat (
						"yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH );
				sb.append ( INDENT ).append ( INDENT_STRING );
				sb.append ( "<" )
						.append ( nsprefix == null ? "" : (nsprefix + ":") )
						.append ( fieldName ).append ( ">" );
				// formatter.format((Date)value, sb, 0);
				sb.append ( formatter.format ( (Date) value ) );
				sb.append ( "</" )
						.append ( nsprefix == null ? "" : (nsprefix + ":") )
						.append ( fieldName ).append ( ">" );
				if ( formatted )
					sb.append ( NEWLINE );

			}
			else if ( value instanceof XMLGregorianCalendar)
			{
				sb.append( "<"  );
				sb.append ( fieldName );
				sb.append ( ">" );
				sb.append ( value.toString ( ) );
				sb.append( "</"  );
				sb.append ( fieldName );
				sb.append ( ">" );
			}
			
			else
			{
				if ( formatted && level == 0 )
					sb.append ( INDENT_STRING ).append ( "<" );
				else
					sb.append ( INDENT ).append ( INDENT ).append ( "<" );
				sb.append ( nsprefix == null ? "" : (nsprefix + ":") );
				sb.append ( fieldName );
				sb.append ( ">" );
				sb.append ( ReflectionHelper.dumpXML ( value, firstName,
						nsprefix, formatted, level + 1 ) );
				sb.append ( "</" );
				sb.append ( nsprefix == null ? "" : (nsprefix + ":") );
				sb.append ( fieldName );

				if ( formatted )
					sb.append ( ">" ).append ( NEWLINE );
				else
					sb.append ( ">" );
			}
		}

		if ( hasEnclosingElement || level > 0 )
		{
			sb.append ( INDENT ).append ( "</" )
					.append ( nsprefix == null ? "" : (nsprefix + ":") )
					.append ( name ).append ( ">" ).append ( NEWLINE );
		}

		return sb.toString ( );
	}

	/**
	 * Query fields of the class into the array
	 * 
	 * @param c
	 *            The class
	 * @return The array
	 */
	public static Field[] iterateFields ( @SuppressWarnings("rawtypes")
	Class c )
	{
		Field[] parentFields = {};
		if ( !c.getSuperclass ( ).getPackage ( )
				.equals ( Object.class.getPackage ( ) ) )
		{
			parentFields = iterateFields ( c.getSuperclass ( ) );
		}

		Field[] declaredFields = c.getDeclaredFields ( );
		if ( declaredFields.length > 0 )
		{
			Field[] fields = new Field[declaredFields.length
					+ parentFields.length];
			System.arraycopy ( declaredFields, 0, fields, 0,
					declaredFields.length );
			System.arraycopy ( parentFields, 0, fields, declaredFields.length,
					parentFields.length );

			return fields;
		}

		return parentFields;
	}

	/**
	 * Convert the object into XML safe string
	 * 
	 * @param o
	 *            The object
	 * @return The string
	 */
	@SuppressWarnings({ "PMD.CyclomaticComplexity" })
	public static String quoteXMLValue ( Object o )
	{
		if ( o == null )
		{
			return null;
		}

		String s = o.toString ( );
		StringBuilder str = new StringBuilder ( s.length ( ) * 5 / 4 );
		int seqStart = 0;
		int seqEnd = 0;

		for ( int count = 0; count < s.length ( ); count++ )
		{
			char ch = s.charAt ( count );
			if ( ch == '<' || ch == '>' || ch == '"' || ch == '&'
					|| ((int) ch) > 192 || ((int) ch) == 26 )
			{
				if ( seqEnd > seqStart )
				{
					str.append ( s.substring ( seqStart, seqEnd ) );
				}

				if ( ch == '"' )
				{
					str.append ( "&quot;" );
				}
				else if ( ch == '<' )
				{
					str.append ( "&lt;" );
				}
				else if ( ch == '>' )
				{
					str.append ( "&gt;" );
				}
				else if ( ch == '&' )
				{
					str.append ( "&amp;" );
				}
				else if ( (int) ch == 26 )
				{
					str.append ( "_" );
				}
				else
				{
					str.append ( "&#" );
					str.append ( Integer.toString ( ch ) );
					str.append ( ';' );
				}
				seqStart = count + 1;
			}
			else
			{
				seqEnd = count + 1;
			}
		}
		if ( seqEnd > seqStart )
		{
			str.append ( s.substring ( seqStart, seqEnd ) );
		}

		return str.toString ( );
	}
}
