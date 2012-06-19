/**
 * CC-LGPL 2.1
 * http://creativecommons.org/licenses/LGPL/2.1/
 */
package hu.javaforum.android.androidsoap;

import hu.javaforum.commons.ReflectionHelper;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Builds the tree of the objects from the SOAP XML
 * 
 * Changelog: ANDROIDSOAP-6 - 2011-01-07 ANDROIDSOAP-5 - 2011-01-07
 * ANDROIDSOAP-3 - 2011-01-07 ANDROIDSOAP-1 - 2011-01-06
 * 
 * @author Gábor Auth <gabor.auth@javaforum.hu>
 * @author Chris Wolf cw10025 gmail com
 * 
 *  Changes added by : 
 *  asraf344@gmail.com
 *  
 */
@SuppressWarnings("PMD.CyclomaticComplexity")
public class GenericHandler
{

	/**
	 * DEBUG mode for AndroidSOAP developers... :)
	 */
	private boolean debug = false;
	/**
	 * The parser type
	 */
	private String parserTypeName;
	/**
	 * The result root object
	 */
	private final Object result; // TODO: not thread-safe
	/**
	 * The path in the SOAP XML
	 */
	private final List<String> xmlPath = new ArrayList<String> ( );
	/**
	 * The objects in the XML
	 */
	private final List<Object> objectPath = new ArrayList<Object> ( );
	/**
	 * The collection objects in the XML
	 */
	private final List<Object> collectionPath = new ArrayList<Object> ( );
	/**
	 * The ArrayFieldInfo list
	 */
	private final Stack<ArrayFieldInfo> arrayFieldData = new Stack<ArrayFieldInfo> ( );

	private Object wrappedResult;
	/**
	 * The String content of the XML leaves
	 */
	private StringBuilder content = null;

	/**
	 * Normally used constructor.
	 * 
	 * @param resultClass
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public GenericHandler(@SuppressWarnings("rawtypes") Class resultClass) throws InstantiationException, IllegalAccessException
	{
		this ( resultClass, false, "android" );
	}

	/**
	 * Constructor used in development - allows enabling debug mode.
	 * 
	 * @param resultClass
	 *            The class of the result tag
	 * @param debug
	 *            True, if you need a DEBUG mode
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public GenericHandler(@SuppressWarnings("rawtypes") Class resultClass, boolean debug) throws InstantiationException, IllegalAccessException
	{
		this ( resultClass, debug, "android" );
	}

	/**
	 * Constructor used in development - allows non-standard parser to be used.
	 * 
	 * @param resultClass
	 *            The class of the result tag
	 * @param debug
	 *            True, if you need a DEBUG mode
	 * @param parserTypeName
	 *            Factory method parameter to select parser implementation
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public GenericHandler(@SuppressWarnings("rawtypes") Class resultClass, boolean debug, String parserTypeName) throws InstantiationException, IllegalAccessException
	{
		this.debug = debug;
		this.result = resultClass.newInstance ( );
		this.parserTypeName = parserTypeName;
	}

	/**
	 * Constructor used for case where we have wrapped result and field name
	 * does not match result. This is a common use-case for
	 * document-literal/wrapped web services.
	 * 
	 * @param resultName
	 * @param resultClass
	 * @param parserTypeName
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	public GenericHandler(String resultName, Class resultClass, String parserTypeName) throws Exception // InstantiationException,
																										// IllegalAccessException
	{
		/*
		 * code to disable private, package-private, etc. access to allow
		 * instantiating member and local classes
		 * 
		 * Constructor ctor = resultClass.getDeclaredConstructor((Class[])null);
		 * ctor.setAccessible(true);this.result =
		 * ctor.newInstance();//resultClass.newInstance();
		 */
		this.result = resultClass.newInstance ( );
		if ( this.parserTypeName == null )
			this.parserTypeName = parserTypeName;

		Object wrappedResultObject = null;
		try
		{
			Class wrappedResultClass = ReflectionHelper.getFieldClass ( resultClass, resultName );
			if ( wrappedResultClass == null )
			{
				wrappedResultClass = ReflectionHelper.getFieldClass ( resultClass, "_" + resultName );
			}

			if ( wrappedResultClass != null )
			{
				wrappedResultObject = getInstanceOfClass ( wrappedResultClass );
				ReflectionHelper.invokeSetter ( result, resultName, wrappedResultObject );
			}

		}
		catch ( Exception except )
		{
			except.printStackTrace ( );
		}

		this.wrappedResult = wrappedResultObject;
	}

	/**
	 * Gets the result object
	 * 
	 * @return The object
	 */
	public Object getObject ( )
	{
		return this.result;
	}

	/**
	 * Gets the type of the parser
	 * 
	 * @return The name of the parser type
	 */
	public String getParserTypeName ( )
	{
		return parserTypeName;
	}

	/**
	 * Parser factory to allow parser implementations other then Android's
	 * native Expat pull parser. (e.g. when not running code in emulator or
	 * device)
	 * 
	 * @param typeName
	 *            special name "android" means use the native parser from
	 *            Android framework
	 * @return instance of XmlPullParser
	 * @throws Exception
	 */
	private static XmlPullParser createParser ( String typeName )
			throws Exception
	{

		XmlPullParserFactory factory = XmlPullParserFactory.newInstance ( );
		factory.setNamespaceAware ( true );
		XmlPullParser xpp = factory.newPullParser ( );

		return xpp;
	}

	/**
	 * XmlPullParser based XML processing
	 * 
	 * @param is
	 *            The InputStream instance
	 */
	public void parseWithPullParser ( String xml )
	{
		try
		{
			XmlPullParser parser = GenericHandler.createParser ( this.parserTypeName ); // new
																						// org.xmlpull.mxp1.MXParser();
			parser.setFeature ( XmlPullParser.FEATURE_PROCESS_NAMESPACES, true );
			// parser.setInput(is, null);
			parser.setInput ( new StringReader ( xml ) );

			for ( int eventType = parser.getEventType ( ); eventType != XmlPullParser.END_DOCUMENT; eventType = parser.next ( ) )
			{
				switch (eventType)
				{
				case XmlPullParser.START_DOCUMENT:
				{
					break;
				}
				case XmlPullParser.START_TAG:
				{
					String name = parser.getName ( );
					//System.out.println ( "start for: " + name );
					String prefix = null;
					if ( "Envelope".equals ( name ) || "Header".equals ( name ) || "Body".equals ( name ) )
					{
						prefix = "env:"; // TODO: Hack-Hack-Hack... :)
					}
					name = prefix == null ? name : prefix + ":" + name;
					this.startElement ( name );
					break;
				}
				case XmlPullParser.TEXT:
				{
					String text = parser.getText ( );
					if ( text != null )
					{
						char[] ch = text.toCharArray ( );
						this.characters ( ch, 0, ch.length );
					}
					break;
				}
				case XmlPullParser.END_TAG:
				{
					String name = parser.getName ( );
					//System.out.println ( "End for: " + name );
					String prefix = null;
					if ( "Envelope".equals ( name ) || "Header".equals ( name ) || "Body".equals ( name ) )
					{
						prefix = "env:"; // TODO: Hack-Hack-Hack... :)
					}
					name = prefix == null ? name : prefix + ":" + name;
					this.endElement ( name );
					break;
				}
				default:
				{
					break;
				}
				}
			}
		}
		catch ( Exception except )
		{
			except.printStackTrace ( );
		}
	}

	/**
	 * The characters between the XML tags
	 * 
	 * @param ch
	 *            The array
	 * @param start
	 *            The start position
	 * @param length
	 *            The length of the content
	 * @throws SAXException
	 */
	public void characters ( char[] ch, int start, int length )
	{
		if ( content == null )
		{
			content = new StringBuilder ( );
		}
		content.append ( ch, start, length );
	}

	/**
	 * Start element hook
	 * 
	 * @param originalName
	 *            The name of the tag
	 * @param attributes
	 *            The attributes of the tag
	 * @throws SAXException
	 */
	@SuppressWarnings({ "PMD.CyclomaticComplexity", "PMD.ExcessiveMethodLength", "PMD.NPathComplexity", "rawtypes", "unchecked" })
	public void startElement ( String originalName )
	{
		this.content = null;

		// need to convert the first char to lowercase for non-Java webservices
		StringBuilder sb = new StringBuilder ( originalName );
		sb.setCharAt ( 0, Character.toLowerCase ( sb.charAt ( 0 ) ) );
		String name = sb.toString ( );

		/**
		 * Hide the SOAP envelope
		 */
		if ( name.indexOf ( "env:" ) == 0 )
		{
			// Log.d(this.getClass().getSimpleName(), "Skipping " + name);
			return;
		}

		if ( this.xmlPath.isEmpty ( ) )
		{
			this.objectPath.add ( result );
			this.collectionPath.add ( null );
		}
		
		else
		{
			/**
			 * Gets the last object...
			 */
			Object object = this.objectPath.get ( this.objectPath.size ( ) - 1 );
			Class fieldClass = ReflectionHelper.getFieldClass ( object.getClass ( ), name );
			
			String fieldName =  name;
			// if the field name is protected name in Java, like "return"
			if (fieldClass == null)
			{
				fieldName = "_" + name;
				fieldClass =   ReflectionHelper.getFieldClass ( object.getClass ( ), fieldName);				
			}
			
			/**
			 * ...detect collection types
			 */
			if ( fieldClass.equals ( List.class ) || fieldClass.isArray ( ) )
			{
				/**
				 * Gets the generic class
				 */
				Class fieldGenericClass = ReflectionHelper.getFieldGenericClass ( object.getClass ( ), fieldName );
				List listObject = null;
				if ( fieldClass.equals ( List.class ) )
				{
					listObject = (List) ReflectionHelper.invokeGetter ( object, name );
					this.collectionPath.add ( listObject );
				}
				else
				{
					// this.xmlPath.get(xmlPath.size()-1).equals(anObject) =
					// name;
					ArrayFieldInfo afi = null;
					if ( this.arrayFieldData.size ( ) > 0 )
					{
						afi = this.arrayFieldData.peek ( );
					}
					if ( afi == null )
					{
						afi = new ArrayFieldInfo ( name, this.xmlPath.size ( ) );
						this.arrayFieldData.push ( afi );
					}
					listObject = afi.arrayData;
					this.collectionPath.add ( null );
				}

				/**
				 * Creates a new instance, and put it into the objects
				 * collection
				 */
				try
				{
					Object fieldObject = fieldGenericClass.newInstance ( );
					this.objectPath.add ( fieldObject );
					//listObject.add ( fieldObject );
				}
				catch ( Exception except )
				{
					except.printStackTrace ( );
				}
			}
			else
			{
				/**
				 * Creates a new simple instance, and put it into the objects
				 * collection
				 */
				try
				{
					if ( fieldClass.isPrimitive ( ) || fieldClass.isArray ( ) )
					{
						this.objectPath.add ( fieldClass );
						this.collectionPath.add ( null );
					}
					else
					{
						Object fieldObject = null;
						try
						{
							fieldObject = fieldClass.newInstance ( );
						}
						catch ( Exception ex )
						{
							ex.toString ( );
						}
						this.objectPath.add ( fieldObject );
						this.collectionPath.add ( null );

						Object parentObject = this.objectPath.get ( this.objectPath.size ( ) - 2 );
						ReflectionHelper.invokeSetter ( parentObject, name, fieldObject );
					}
				}
				catch ( Exception except )
				{
					except.printStackTrace ( );
				}
			}
		}

		this.xmlPath.add ( name );

	}

	/**
	 * End element hook
	 * 
	 * @param originalName
	 *            The name of the tag
	 * @throws SAXException
	 */
	@SuppressWarnings({ "PMD.NPathComplexity", "rawtypes" })
	public void endElement ( String originalName )
	{
		if ( originalName.indexOf ( "env:" ) == 0 )
		{
			// not processing Envelope elements
			return;
		}

		// need to convert the first char to lowercase for non-Java webservices
		StringBuilder sb = new StringBuilder ( originalName );
		sb.setCharAt ( 0, Character.toLowerCase ( sb.charAt ( 0 ) ) );
		String name = sb.toString ( );

		this.xmlPath.remove ( this.xmlPath.size ( ) - 1 );
		/**
		 * if the parse depth drops back to where we first encountered array
		 * elements, gather the assembled array elements and call the parent
		 * object's array setter TODO: now handling array fields CJW
		 */
		if ( this.arrayFieldData != null && this.arrayFieldData.size ( ) > 0 )
		{
			ArrayFieldInfo afi = this.arrayFieldData.peek ( );
			if ( this.xmlPath.size ( ) < afi.arrayFieldDepth )
			{
				this.arrayFieldData.pop ( );
				Object object = this.objectPath.get ( this.objectPath.size ( ) - 1 );

				ReflectionHelper.invokeSetter ( object, afi.fieldName, afi.arrayData );
			}
		}

		if ( this.xmlPath.isEmpty ( ) )
		{
			this.objectPath.remove ( result );
			this.collectionPath.remove ( this.collectionPath.size ( ) - 1 );
		}
		
		else
		{
			Object object = this.objectPath.get ( this.objectPath.size ( ) - 2 );
			Class fieldClass = ReflectionHelper.getFieldClass ( object.getClass ( ), name );
			fieldClass = fieldClass == null ? ReflectionHelper.getFieldClass ( object.getClass ( ), "_" + name ) : fieldClass;

			/**
			 * Save the content, if the field is String - data conversions occur
			 * in invokeSetter
			 */
			if ( fieldClass != null && !fieldClass.isArray ( )  && !fieldClass.equals ( List.class ) )
			{

				ReflectionHelper.invokeSetter ( object, name, content == null ? null : content.toString ( ) );
			}

			else if ( fieldClass.equals ( List.class )  )
			{
				
				Object fieldObject = this.objectPath.get ( this.objectPath.size ( ) - 1 );
				
				// if its list of primitive type
				if ( content != null ||   fieldObject.getClass ( ).equals ( String.class ) || fieldObject.getClass ( ).isPrimitive ( ) )
				{
					ReflectionHelper.invokeSetter ( object, name,  content.toString ( ) );
				}
				// if its the list of some business class,
				//field class is list now add the object generated so far to the list
				else
				{
					ReflectionHelper.invokeSetter ( object, name, fieldObject );
				}
				
				
			}
			
			this.objectPath.remove ( this.objectPath.size ( ) - 1 );
			this.collectionPath.remove ( this.collectionPath.size ( ) - 1 );
		}

		this.content = null;
	}

	/**
	 * The array field info, this class holds an information about arrays
	 */
	static class ArrayFieldInfo
	{

		/**
		 * The field name
		 */
		String fieldName;
		/**
		 * The field depth
		 */
		int arrayFieldDepth;
		/**
		 * The data of array as List
		 */
		List<Object> arrayData;

		/**
		 * The constructor
		 * 
		 * @param fieldName
		 *            The name of the field
		 * @param arrayFieldDepth
		 *            The depth of the field
		 */
		public ArrayFieldInfo(String fieldName, int arrayFieldDepth)
		{
			this.fieldName = fieldName;
			this.arrayFieldDepth = arrayFieldDepth;
			this.arrayData = new ArrayList<Object> ( );
		}

		/**
		 * Add an element
		 * 
		 * @param nextElement
		 *            The element
		 */
		public void add ( Object nextElement )
		{
			this.arrayData.add ( nextElement );
		}
	}

	public boolean isDebug ( )
	{
		return debug;
	}

	public void setDebug ( boolean debug )
	{
		this.debug = debug;
	}

	public static Object getInstanceOfClass ( Class clazz )
	{
		Object object = null;

		try
		{
			if ( clazz.equals ( XMLGregorianCalendar.class ) )
			{
				object = DatatypeFactory.newInstance ( ).newXMLGregorianCalendar ( );
			}
			else if ( clazz.equals ( List.class ) )
			{
				//object = new ArrayList ( );
			}
			else
			{
				object = clazz.newInstance ( );
			}
		}
		catch ( Exception ex )
		{
			ex.printStackTrace ( );
		}

		return object;
	}
}
