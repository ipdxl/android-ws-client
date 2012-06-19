
package org.jinouts.ws.axis.model.xsd;

import java.util.ArrayList;
import java.util.List;
import org.jinouts.xml.bind.annotation.XmlAccessType;
import org.jinouts.xml.bind.annotation.XmlAccessorType;
import org.jinouts.xml.bind.annotation.XmlElement;
import org.jinouts.xml.bind.annotation.XmlSchemaType;
import org.jinouts.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ComplexRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ComplexRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="applId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cal" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}date" minOccurs="0"/>
 *         &lt;element name="doubleId" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="floatId" type="{http://www.w3.org/2001/XMLSchema}float" minOccurs="0"/>
 *         &lt;element name="intId" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="mbrDetail" type="{http://model.axis.ws.jinouts.org/xsd}MbrDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="strArray" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ComplexRequest", propOrder = {
    "applId",
    "cal",
    "date",
    "doubleId",
    "floatId",
    "intId",
    "mbrDetail",
    "strArray"
})
public class ComplexRequest {

    @XmlElement(nillable = true)
    protected String applId;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cal;
    @XmlElement(nillable = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar date;
    protected Double doubleId;
    protected Float floatId;
    protected Integer intId;
    @XmlElement(nillable = true)
    protected List<MbrDetail> mbrDetail;
    @XmlElement(nillable = true)
    protected List<String> strArray;

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
     * Gets the value of the cal property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCal() {
        return cal;
    }

    /**
     * Sets the value of the cal property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCal(XMLGregorianCalendar value) {
        this.cal = value;
    }

    /**
     * Gets the value of the date property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDate() {
        return date;
    }

    /**
     * Sets the value of the date property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDate(XMLGregorianCalendar value) {
        this.date = value;
    }

    /**
     * Gets the value of the doubleId property.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDoubleId() {
        return doubleId;
    }

    /**
     * Sets the value of the doubleId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDoubleId(Double value) {
        this.doubleId = value;
    }

    /**
     * Gets the value of the floatId property.
     * 
     * @return
     *     possible object is
     *     {@link Float }
     *     
     */
    public Float getFloatId() {
        return floatId;
    }

    /**
     * Sets the value of the floatId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Float }
     *     
     */
    public void setFloatId(Float value) {
        this.floatId = value;
    }

    /**
     * Gets the value of the intId property.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getIntId() {
        return intId;
    }

    /**
     * Sets the value of the intId property.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setIntId(Integer value) {
        this.intId = value;
    }

    /**
     * Gets the value of the mbrDetail property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mbrDetail property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMbrDetail().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MbrDetail }
     * 
     * 
     */
    public List<MbrDetail> getMbrDetail() {
        if (mbrDetail == null) {
            mbrDetail = new ArrayList<MbrDetail>();
        }
        return this.mbrDetail;
    }

    /**
     * Gets the value of the strArray property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the strArray property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStrArray().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getStrArray() {
        if (strArray == null) {
            strArray = new ArrayList<String>();
        }
        return this.strArray;
    }

}
