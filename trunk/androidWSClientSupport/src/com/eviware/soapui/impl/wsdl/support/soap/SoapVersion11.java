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

package com.eviware.soapui.impl.wsdl.support.soap;

import java.io.IOException;
import java.util.List;

import javax.xml.namespace.QName;

import org.xmlsoap.schemas.soap.envelope.EnvelopeDocument;

import com.eviware.soapui.impl.wsdl.support.Constants;

/**
 * SoapVersion for SOAP 1.1
 * 
 * @author ole.matzura
 */

public class SoapVersion11 extends AbstractSoapVersion
{
	private final static QName envelopeQName = new QName( Constants.SOAP11_ENVELOPE_NS, "Envelope" );
	private final static QName bodyQName = new QName( Constants.SOAP11_ENVELOPE_NS, "Body" );
	private final static QName faultQName = new QName( Constants.SOAP11_ENVELOPE_NS, "Fault" );
	private final static QName headerQName = new QName( Constants.SOAP11_ENVELOPE_NS, "Header" );

	

	public final static SoapVersion11 instance = new SoapVersion11();

	private SoapVersion11()
	{
				
	}



	public String getEnvelopeNamespace()
	{
		return Constants.SOAP11_ENVELOPE_NS;
	}

	public String getEncodingNamespace()
	{
		return Constants.SOAP_ENCODING_NS;
	}

	

	public String toString()
	{
		return "SOAP 1.1";
	}

	public String getContentTypeHttpHeader( String encoding, String soapAction )
	{
		if( encoding == null || encoding.trim().length() == 0 )
			return getContentType();
		else
			return getContentType() + ";charset=" + encoding;
	}

	public String getSoapActionHeader( String soapAction )
	{
		if( soapAction == null || soapAction.length() == 0 )
		{
			soapAction = "\"\"";
		}
		else
		{
			soapAction = "\"" + soapAction + "\"";
		}

		return soapAction;
	}

	public String getContentType()
	{
		return "text/xml";
	}

	public QName getBodyQName()
	{
		return bodyQName;
	}

	public QName getEnvelopeQName()
	{
		return envelopeQName;
	}

	public QName getHeaderQName()
	{
		return headerQName;
	}

	
	public String getName()
	{
		return "SOAP 1.1";
	}

	public String getFaultDetailNamespace()
	{
		return "";
	}

	
}
