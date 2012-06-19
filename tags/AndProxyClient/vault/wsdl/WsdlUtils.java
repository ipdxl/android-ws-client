/*
 *  soapUI, copyright (C) 2004-2011 smartbear.com 
 *
 *  soapUI is free software; you can redistribute it and/or modify it under the 
 *  terms of version 2.1 of the GNU Lesser General Public License as published by 
 *  the Free Software Foundation.
 *
 *  soapUI is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without 
 *  even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. 
 *  See the GNU Lesser General Public License for more details at gnu.org.
 */

package com.eviware.soapui.impl.wsdl.support.wsdl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.wsdl.extensions.ElementExtensible;
import javax.wsdl.extensions.ExtensibilityElement;
import javax.wsdl.extensions.UnknownExtensibilityElement;
import javax.xml.namespace.QName;

import org.w3c.dom.Element;

/**
 * Wsdl-related tools
 * 
 * @author Ole.Matzura
 */

public class WsdlUtils
{
	
	public static <T extends ExtensibilityElement> T getExtensiblityElement( List<?> list, Class<T> clazz )
	{
		List<T> elements = getExtensiblityElements( list, clazz );
		return elements.isEmpty() ? null : elements.get( 0 );
	}

	public static <T extends ExtensibilityElement> List<T> getExtensiblityElements( List list, Class<T> clazz )
	{
		List<T> result = new ArrayList<T>();

		for( Iterator<T> i = list.iterator(); i.hasNext(); )
		{
			T elm = i.next();
			if( clazz.isAssignableFrom( elm.getClass() ) )
			{
				result.add( elm );
			}
		}

		return result;
	}

	public static Element[] getExentsibilityElements( ElementExtensible item, QName qname )
	{
		if( item == null )
			return new Element[0];

		List<Element> result = new ArrayList<Element>();

		List<?> list = item.getExtensibilityElements();
		for( Iterator<?> i = list.iterator(); i.hasNext(); )
		{
			ExtensibilityElement elm = ( ExtensibilityElement )i.next();
			if( elm.getElementType().equals( qname ) && elm instanceof UnknownExtensibilityElement )
			{
				result.add( ( ( UnknownExtensibilityElement )elm ).getElement() );
			}
		}

		return result.toArray( new Element[result.size()] );
	}

	
}
