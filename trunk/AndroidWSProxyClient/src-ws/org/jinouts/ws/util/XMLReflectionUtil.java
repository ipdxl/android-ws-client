/**
 * 
 */
package org.jinouts.ws.util;

import java.lang.reflect.Field;

import org.jinouts.ws.exception.FieldNotMatchedException;
import org.jinouts.ws.exception.MethodNotMatchedException;
import org.jinouts.xml.bind.annotation.XmlElement;

import hu.javaforum.commons.ReflectionHelper;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class XMLReflectionUtil
{
	public static boolean invokeSetter ( Object instance, String fieldName, Object value ) 
	{
		boolean isSet = false;
		try
		{
			isSet = ReflectionHelper.invokeSetter ( instance, fieldName,  value );
		}
		catch ( FieldNotMatchedException e )
		{
			// get the field name by xml element
			fieldName = getFieldNameByXMLElement ( instance.getClass ( ), fieldName );

			try
			{
				if ( fieldName != null )
				{
					isSet = ReflectionHelper.invokeSetter ( instance, fieldName,  value );
				}
				
			}
			
			catch ( Exception e1 )
			{
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		catch ( MethodNotMatchedException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		return isSet;
	}
	
	public static Object invokeGetter ( Object instance, String fieldName )
	{
		Object obj = null;
		try
		{
			obj = ReflectionHelper.invokeGetter ( instance, fieldName );
		}
		catch ( MethodNotMatchedException e )
		{
			// try to get the field name by xml element
			fieldName = getFieldNameByXMLElement ( instance.getClass ( ), fieldName );
			
			if ( fieldName != null )
			{
				try
				{
					obj = ReflectionHelper.invokeGetter ( instance, fieldName );
				}
				catch ( Exception e1 )
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}
				
		return obj;
	}
	
	public static String getFieldNameByXMLElement ( Class instanceClass, String xmlName  )
	{
		Field[] fields = instanceClass.getDeclaredFields ( );
		for ( Field field : fields )
		{
			XmlElement xmlElement =  field.getAnnotation ( XmlElement.class );
			if ( xmlName.equalsIgnoreCase (  xmlElement.name ( ) ) )
			{
				return field.getName ( );
				
			}
		}
		
		return null;
	}
}
