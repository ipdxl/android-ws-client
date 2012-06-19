
package org.jinouts.ws;

import org.jinouts.xml.bind.JAXBElement;
import org.jinouts.xml.bind.annotation.XmlElementDecl;
import org.jinouts.xml.bind.annotation.XmlRegistry;
import org.jinouts.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the org.jinouts.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EnterResponse_QNAME = new QName("http://ws.jinouts.org/", "enterResponse");
    private final static QName _Enter_QNAME = new QName("http://ws.jinouts.org/", "enter");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jinouts.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EnterResponse }
     * 
     */
    public EnterResponse createEnterResponse() {
        return new EnterResponse();
    }

    /**
     * Create an instance of {@link Enter }
     * 
     */
    public Enter createEnter() {
        return new Enter();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnterResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "enterResponse")
    public JAXBElement<EnterResponse> createEnterResponse(EnterResponse value) {
        return new JAXBElement<EnterResponse>(_EnterResponse_QNAME, EnterResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Enter }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "enter")
    public JAXBElement<Enter> createEnter(Enter value) {
        return new JAXBElement<Enter>(_Enter_QNAME, Enter.class, null, value);
    }

}
