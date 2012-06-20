package org.jinouts.ws.axis;

import org.jinouts.jws.WebMethod;
import org.jinouts.jws.WebParam;
import org.jinouts.jws.WebResult;
import org.jinouts.jws.WebService;
import org.jinouts.xml.bind.annotation.XmlSeeAlso;
import org.jinouts.xml.ws.Action;
import org.jinouts.xml.ws.RequestWrapper;
import org.jinouts.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.1
 * 2012-05-29T20:07:16.597+06:00
 * Generated source version: 2.5.1
 * 
 */
@WebService(targetNamespace = "http://axis.ws.jinouts.org", name = "ComplexRequestWSPortType")
@XmlSeeAlso({org.jinouts.ws.axis.model.xsd.ObjectFactory.class, ObjectFactory.class})
public interface ComplexRequestWSPortType {

    @WebResult(name = "return", targetNamespace = "http://axis.ws.jinouts.org")
    @Action(input = "urn:testComplexRequest", output = "urn:testComplexRequestResponse")
    @RequestWrapper(localName = "testComplexRequest", targetNamespace = "http://axis.ws.jinouts.org", className = "org.jinouts.ws.axis.TestComplexRequest")
    @WebMethod(action = "urn:testComplexRequest")
    @ResponseWrapper(localName = "testComplexRequestResponse", targetNamespace = "http://axis.ws.jinouts.org", className = "org.jinouts.ws.axis.TestComplexRequestResponse")
    public java.lang.String testComplexRequest(
        @WebParam(name = "request", targetNamespace = "http://axis.ws.jinouts.org")
        org.jinouts.ws.axis.model.xsd.ComplexRequest request
    );
}