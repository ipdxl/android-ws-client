
package org.jinouts.ws.model;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for mbrDetail complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mbrDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="address1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="address2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="businessPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callCenterHint" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="callCenterPin" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="city" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="country" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="firstName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="homePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastSsn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mbrLevelCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mbrLevelId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mbrLevelName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="mbrStatus" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="memberId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="middleInitial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="pointBalance" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="sourceName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="state" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="zip" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "mbrDetail", propOrder = {
    "address1",
    "address2",
    "businessPhone",
    "callCenterHint",
    "callCenterPin",
    "city",
    "country",
    "email",
    "firstName",
    "homePhone",
    "lastName",
    "lastSsn",
    "mbrLevelCode",
    "mbrLevelId",
    "mbrLevelName",
    "mbrStatus",
    "memberId",
    "middleInitial",
    "pointBalance",
    "sourceId",
    "sourceName",
    "state",
    "zip",
    "registrationDate",
    "birthDate"
})
public class MbrDetail {

    protected String address1;
    protected String address2;
    protected String businessPhone;
    protected String callCenterHint;
    protected String callCenterPin;
    protected String city;
    protected String country;
    protected String email;
    protected String firstName;
    protected String homePhone;
    protected String lastName;
    protected String lastSsn;
    protected String mbrLevelCode;
    protected String mbrLevelId;
    protected String mbrLevelName;
    protected String mbrStatus;
    protected String memberId;
    protected String middleInitial;
    protected String pointBalance;
    protected String sourceId;
    protected String sourceName;
    protected String state;
    protected String zip;
    protected Date registrationDate;
    protected XMLGregorianCalendar birthDate;
    

    /**
     * Gets the value of the address1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Sets the value of the address1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress1(String value) {
        this.address1 = value;
    }

    /**
     * Gets the value of the address2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Sets the value of the address2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress2(String value) {
        this.address2 = value;
    }

    /**
     * Gets the value of the businessPhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessPhone() {
        return businessPhone;
    }

    /**
     * Sets the value of the businessPhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessPhone(String value) {
        this.businessPhone = value;
    }

    /**
     * Gets the value of the callCenterHint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallCenterHint() {
        return callCenterHint;
    }

    /**
     * Sets the value of the callCenterHint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallCenterHint(String value) {
        this.callCenterHint = value;
    }

    /**
     * Gets the value of the callCenterPin property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallCenterPin() {
        return callCenterPin;
    }

    /**
     * Sets the value of the callCenterPin property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallCenterPin(String value) {
        this.callCenterPin = value;
    }

    /**
     * Gets the value of the city property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCity() {
        return city;
    }

    /**
     * Sets the value of the city property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCity(String value) {
        this.city = value;
    }

    /**
     * Gets the value of the country property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCountry() {
        return country;
    }

    /**
     * Sets the value of the country property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCountry(String value) {
        this.country = value;
    }

    /**
     * Gets the value of the email property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the value of the email property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * Gets the value of the firstName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the value of the firstName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFirstName(String value) {
        this.firstName = value;
    }

    /**
     * Gets the value of the homePhone property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * Sets the value of the homePhone property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHomePhone(String value) {
        this.homePhone = value;
    }

    /**
     * Gets the value of the lastName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the value of the lastName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastName(String value) {
        this.lastName = value;
    }

    /**
     * Gets the value of the lastSsn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLastSsn() {
        return lastSsn;
    }

    /**
     * Sets the value of the lastSsn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLastSsn(String value) {
        this.lastSsn = value;
    }

    /**
     * Gets the value of the mbrLevelCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMbrLevelCode() {
        return mbrLevelCode;
    }

    /**
     * Sets the value of the mbrLevelCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMbrLevelCode(String value) {
        this.mbrLevelCode = value;
    }

    /**
     * Gets the value of the mbrLevelId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMbrLevelId() {
        return mbrLevelId;
    }

    /**
     * Sets the value of the mbrLevelId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMbrLevelId(String value) {
        this.mbrLevelId = value;
    }

    /**
     * Gets the value of the mbrLevelName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMbrLevelName() {
        return mbrLevelName;
    }

    /**
     * Sets the value of the mbrLevelName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMbrLevelName(String value) {
        this.mbrLevelName = value;
    }

    /**
     * Gets the value of the mbrStatus property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMbrStatus() {
        return mbrStatus;
    }

    /**
     * Sets the value of the mbrStatus property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMbrStatus(String value) {
        this.mbrStatus = value;
    }

    /**
     * Gets the value of the memberId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMemberId() {
        return memberId;
    }

    /**
     * Sets the value of the memberId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMemberId(String value) {
        this.memberId = value;
    }

    /**
     * Gets the value of the middleInitial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMiddleInitial() {
        return middleInitial;
    }

    /**
     * Sets the value of the middleInitial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMiddleInitial(String value) {
        this.middleInitial = value;
    }

    /**
     * Gets the value of the pointBalance property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPointBalance() {
        return pointBalance;
    }

    /**
     * Sets the value of the pointBalance property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPointBalance(String value) {
        this.pointBalance = value;
    }

    /**
     * Gets the value of the sourceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceId() {
        return sourceId;
    }

    /**
     * Sets the value of the sourceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceId(String value) {
        this.sourceId = value;
    }

    /**
     * Gets the value of the sourceName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSourceName() {
        return sourceName;
    }

    /**
     * Sets the value of the sourceName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSourceName(String value) {
        this.sourceName = value;
    }

    /**
     * Gets the value of the state property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the value of the state property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * Gets the value of the zip property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZip() {
        return zip;
    }

    /**
     * Sets the value of the zip property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZip(String value) {
        this.zip = value;
    }

	public Date getRegistrationDate ( )
	{
		return registrationDate;
	}

	public void setRegistrationDate ( Date registrationDate )
	{
		this.registrationDate = registrationDate;
	}

	public XMLGregorianCalendar getBirthDate ( )
	{
		return birthDate;
	}

	public void setBirthDate ( XMLGregorianCalendar birthDate )
	{
		this.birthDate = birthDate;
	}

}
