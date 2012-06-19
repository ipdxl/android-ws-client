
package org.jinouts.ws.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for requestHeader complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="requestHeader">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="partnerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerSystemId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerServerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="partnerSessionId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="conversationId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="requestDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "requestHeader", propOrder = {
    "applId",
    "userName",
    "password",
    "partnerId",
    "partnerSystemId",
    "partnerServerId",
    "partnerSessionId",
    "requestId",
    "conversationId",
    "requestDate"
})
public class RequestHeader {

    @XmlElement(required = true)
    protected String applId;
    @XmlElement(required = true)
    protected String userName;
    @XmlElement(required = true)
    protected String password;
    protected String partnerId;
    protected String partnerSystemId;
    protected String partnerServerId;
    protected String partnerSessionId;
    @XmlElement(required = true)
    protected String requestId;
    @XmlElement(required = true)
    protected String conversationId;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar requestDate;

    /**
     * Gets the value of the applId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApplId() {
        return applId;
    }

    /**
     * Sets the value of the applId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApplId(String value) {
        this.applId = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the partnerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerId() {
        return partnerId;
    }

    /**
     * Sets the value of the partnerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerId(String value) {
        this.partnerId = value;
    }

    /**
     * Gets the value of the partnerSystemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerSystemId() {
        return partnerSystemId;
    }

    /**
     * Sets the value of the partnerSystemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerSystemId(String value) {
        this.partnerSystemId = value;
    }

    /**
     * Gets the value of the partnerServerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerServerId() {
        return partnerServerId;
    }

    /**
     * Sets the value of the partnerServerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerServerId(String value) {
        this.partnerServerId = value;
    }

    /**
     * Gets the value of the partnerSessionId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPartnerSessionId() {
        return partnerSessionId;
    }

    /**
     * Sets the value of the partnerSessionId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPartnerSessionId(String value) {
        this.partnerSessionId = value;
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the conversationId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConversationId() {
        return conversationId;
    }

    /**
     * Sets the value of the conversationId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConversationId(String value) {
        this.conversationId = value;
    }

    /**
     * Gets the value of the requestDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRequestDate() {
        return requestDate;
    }

    /**
     * Sets the value of the requestDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRequestDate(XMLGregorianCalendar value) {
        this.requestDate = value;
    }

}
