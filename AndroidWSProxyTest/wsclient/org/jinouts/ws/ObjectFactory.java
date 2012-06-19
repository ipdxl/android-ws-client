
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

    private final static QName _TestListOfObjResponseResponse_QNAME = new QName("http://ws.jinouts.org/", "testListOfObjResponseResponse");
    private final static QName _TestDateResponse_QNAME = new QName("http://ws.jinouts.org/", "testDateResponse");
    private final static QName _TestListOfObjResponse_QNAME = new QName("http://ws.jinouts.org/", "testListOfObjResponse");
    private final static QName _TestComplexResponseObject_QNAME = new QName("http://ws.jinouts.org/", "testComplexResponseObject");
    private final static QName _TestPrimitiveResponse_QNAME = new QName("http://ws.jinouts.org/", "testPrimitiveResponse");
    private final static QName _TestArrayResponse_QNAME = new QName("http://ws.jinouts.org/", "testArrayResponse");
    private final static QName _TestComplexResponseObjectResponse_QNAME = new QName("http://ws.jinouts.org/", "testComplexResponseObjectResponse");
    private final static QName _TestListResponseResponse_QNAME = new QName("http://ws.jinouts.org/", "testListResponseResponse");
    private final static QName _TestListResponse_QNAME = new QName("http://ws.jinouts.org/", "testListResponse");
    private final static QName _TestDateResponseResponse_QNAME = new QName("http://ws.jinouts.org/", "testDateResponseResponse");
    private final static QName _TestListOfComplexResponseObject_QNAME = new QName("http://ws.jinouts.org/", "testListOfComplexResponseObject");
    private final static QName _TestListOfComplexResponseObjectResponse_QNAME = new QName("http://ws.jinouts.org/", "testListOfComplexResponseObjectResponse");
    private final static QName _TestPrimitiveResponseResponse_QNAME = new QName("http://ws.jinouts.org/", "testPrimitiveResponseResponse");
    private final static QName _TestArrayResponseResponse_QNAME = new QName("http://ws.jinouts.org/", "testArrayResponseResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.jinouts.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link TestComplexResponseObject }
     * 
     */
    public TestComplexResponseObject createTestComplexResponseObject() {
        return new TestComplexResponseObject();
    }

    /**
     * Create an instance of {@link TestPrimitiveResponse }
     * 
     */
    public TestPrimitiveResponse createTestPrimitiveResponse() {
        return new TestPrimitiveResponse();
    }

    /**
     * Create an instance of {@link TestDateResponse }
     * 
     */
    public TestDateResponse createTestDateResponse() {
        return new TestDateResponse();
    }

    /**
     * Create an instance of {@link TestListOfObjResponse }
     * 
     */
    public TestListOfObjResponse createTestListOfObjResponse() {
        return new TestListOfObjResponse();
    }

    /**
     * Create an instance of {@link TestListOfObjResponseResponse }
     * 
     */
    public TestListOfObjResponseResponse createTestListOfObjResponseResponse() {
        return new TestListOfObjResponseResponse();
    }

    /**
     * Create an instance of {@link TestArrayResponse }
     * 
     */
    public TestArrayResponse createTestArrayResponse() {
        return new TestArrayResponse();
    }

    /**
     * Create an instance of {@link TestComplexResponseObjectResponse }
     * 
     */
    public TestComplexResponseObjectResponse createTestComplexResponseObjectResponse() {
        return new TestComplexResponseObjectResponse();
    }

    /**
     * Create an instance of {@link TestDateResponseResponse }
     * 
     */
    public TestDateResponseResponse createTestDateResponseResponse() {
        return new TestDateResponseResponse();
    }

    /**
     * Create an instance of {@link TestListOfComplexResponseObject }
     * 
     */
    public TestListOfComplexResponseObject createTestListOfComplexResponseObject() {
        return new TestListOfComplexResponseObject();
    }

    /**
     * Create an instance of {@link TestListResponse }
     * 
     */
    public TestListResponse createTestListResponse() {
        return new TestListResponse();
    }

    /**
     * Create an instance of {@link TestListResponseResponse }
     * 
     */
    public TestListResponseResponse createTestListResponseResponse() {
        return new TestListResponseResponse();
    }

    /**
     * Create an instance of {@link TestArrayResponseResponse }
     * 
     */
    public TestArrayResponseResponse createTestArrayResponseResponse() {
        return new TestArrayResponseResponse();
    }

    /**
     * Create an instance of {@link TestPrimitiveResponseResponse }
     * 
     */
    public TestPrimitiveResponseResponse createTestPrimitiveResponseResponse() {
        return new TestPrimitiveResponseResponse();
    }

    /**
     * Create an instance of {@link TestListOfComplexResponseObjectResponse }
     * 
     */
    public TestListOfComplexResponseObjectResponse createTestListOfComplexResponseObjectResponse() {
        return new TestListOfComplexResponseObjectResponse();
    }

    /**
     * Create an instance of {@link TestComplexResponse }
     * 
     */
    public TestComplexResponse createTestComplexResponse() {
        return new TestComplexResponse();
    }

    /**
     * Create an instance of {@link MbrDetail }
     * 
     */
    public MbrDetail createMbrDetail() {
        return new MbrDetail();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestListOfObjResponseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testListOfObjResponseResponse")
    public JAXBElement<TestListOfObjResponseResponse> createTestListOfObjResponseResponse(TestListOfObjResponseResponse value) {
        return new JAXBElement<TestListOfObjResponseResponse>(_TestListOfObjResponseResponse_QNAME, TestListOfObjResponseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestDateResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testDateResponse")
    public JAXBElement<TestDateResponse> createTestDateResponse(TestDateResponse value) {
        return new JAXBElement<TestDateResponse>(_TestDateResponse_QNAME, TestDateResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestListOfObjResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testListOfObjResponse")
    public JAXBElement<TestListOfObjResponse> createTestListOfObjResponse(TestListOfObjResponse value) {
        return new JAXBElement<TestListOfObjResponse>(_TestListOfObjResponse_QNAME, TestListOfObjResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestComplexResponseObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testComplexResponseObject")
    public JAXBElement<TestComplexResponseObject> createTestComplexResponseObject(TestComplexResponseObject value) {
        return new JAXBElement<TestComplexResponseObject>(_TestComplexResponseObject_QNAME, TestComplexResponseObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestPrimitiveResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testPrimitiveResponse")
    public JAXBElement<TestPrimitiveResponse> createTestPrimitiveResponse(TestPrimitiveResponse value) {
        return new JAXBElement<TestPrimitiveResponse>(_TestPrimitiveResponse_QNAME, TestPrimitiveResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestArrayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testArrayResponse")
    public JAXBElement<TestArrayResponse> createTestArrayResponse(TestArrayResponse value) {
        return new JAXBElement<TestArrayResponse>(_TestArrayResponse_QNAME, TestArrayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestComplexResponseObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testComplexResponseObjectResponse")
    public JAXBElement<TestComplexResponseObjectResponse> createTestComplexResponseObjectResponse(TestComplexResponseObjectResponse value) {
        return new JAXBElement<TestComplexResponseObjectResponse>(_TestComplexResponseObjectResponse_QNAME, TestComplexResponseObjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestListResponseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testListResponseResponse")
    public JAXBElement<TestListResponseResponse> createTestListResponseResponse(TestListResponseResponse value) {
        return new JAXBElement<TestListResponseResponse>(_TestListResponseResponse_QNAME, TestListResponseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestListResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testListResponse")
    public JAXBElement<TestListResponse> createTestListResponse(TestListResponse value) {
        return new JAXBElement<TestListResponse>(_TestListResponse_QNAME, TestListResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestDateResponseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testDateResponseResponse")
    public JAXBElement<TestDateResponseResponse> createTestDateResponseResponse(TestDateResponseResponse value) {
        return new JAXBElement<TestDateResponseResponse>(_TestDateResponseResponse_QNAME, TestDateResponseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestListOfComplexResponseObject }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testListOfComplexResponseObject")
    public JAXBElement<TestListOfComplexResponseObject> createTestListOfComplexResponseObject(TestListOfComplexResponseObject value) {
        return new JAXBElement<TestListOfComplexResponseObject>(_TestListOfComplexResponseObject_QNAME, TestListOfComplexResponseObject.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestListOfComplexResponseObjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testListOfComplexResponseObjectResponse")
    public JAXBElement<TestListOfComplexResponseObjectResponse> createTestListOfComplexResponseObjectResponse(TestListOfComplexResponseObjectResponse value) {
        return new JAXBElement<TestListOfComplexResponseObjectResponse>(_TestListOfComplexResponseObjectResponse_QNAME, TestListOfComplexResponseObjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestPrimitiveResponseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testPrimitiveResponseResponse")
    public JAXBElement<TestPrimitiveResponseResponse> createTestPrimitiveResponseResponse(TestPrimitiveResponseResponse value) {
        return new JAXBElement<TestPrimitiveResponseResponse>(_TestPrimitiveResponseResponse_QNAME, TestPrimitiveResponseResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TestArrayResponseResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://ws.jinouts.org/", name = "testArrayResponseResponse")
    public JAXBElement<TestArrayResponseResponse> createTestArrayResponseResponse(TestArrayResponseResponse value) {
        return new JAXBElement<TestArrayResponseResponse>(_TestArrayResponseResponse_QNAME, TestArrayResponseResponse.class, null, value);
    }

}
