package org.jinouts.webservice.service;

import org.jinouts.jws.WebMethod;
import org.jinouts.jws.WebParam;
import org.jinouts.jws.WebResult;
import org.jinouts.jws.WebService;
import org.jinouts.xml.bind.annotation.XmlSeeAlso;
import org.jinouts.xml.ws.RequestWrapper;
import org.jinouts.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.5.1
 * 2012-05-04T19:38:31.655+06:00
 * Generated source version: 2.5.1
 * 
 */
@WebService(targetNamespace = "http://service.webservice.jinouts.org/", name = "login")
@XmlSeeAlso({ObjectFactory.class})
public interface Login {

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "login", targetNamespace = "http://service.webservice.jinouts.org/", className = "org.jinouts.webservice.service.Login_Type")
    @WebMethod
    @ResponseWrapper(localName = "loginResponse", targetNamespace = "http://service.webservice.jinouts.org/", className = "org.jinouts.webservice.service.LoginResponse")
    public org.jinouts.webservice.service.LoginServiceResponse login(
        @WebParam(name = "user", targetNamespace = "")
        java.lang.String user,
        @WebParam(name = "pass", targetNamespace = "")
        java.lang.String pass
    );
}