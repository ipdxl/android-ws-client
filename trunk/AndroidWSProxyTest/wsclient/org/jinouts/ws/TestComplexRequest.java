
package org.jinouts.ws;

import java.util.ArrayList;
import java.util.List;
import org.jinouts.xml.bind.annotation.XmlAccessType;
import org.jinouts.xml.bind.annotation.XmlAccessorType;
import org.jinouts.xml.bind.annotation.XmlElement;
import org.jinouts.xml.bind.annotation.XmlSchemaType;
import org.jinouts.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for testComplexRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="testComplexRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mbrDetail" type="{http://ws.jinouts.org/}mbrDetail" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="applId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="intId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="doubleId" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *         &lt;element name="floatId" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="date" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="cal" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="list" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
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
@XmlType(name = "testComplexRequest", propOrder = {
    "mbrDetail",
    "applId",
    "intId",
    "doubleId",
    "floatId",
    "date",
    "cal",
    "list",
    "strArray"
})
public class TestComplexRequest {

    @XmlElement(nillable = true)
    protected List<MbrDetail> mbrDetail;
    protected String applId;
    protected int intId;
    protected double doubleId;
    protected float floatId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar date;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar cal;
    @XmlElement(nillable = true)
    protected List<String> list;
    @XmlElement(nillable = true)
    protected List<String> strArray;

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
     * Gets the value of the intId property.
     * 
     */
    public int getIntId() {
        return intId;
    }

    /**
     * Sets the value of the intId property.
     * 
     */
    public void setIntId(int value) {
        this.intId = value;
    }

    /**
     * Gets the value of the doubleId property.
     * 
     */
    public double getDoubleId() {
        return doubleId;
    }

    /**
     * Sets the value of the doubleId property.
     * 
     */
    public void setDoubleId(double value) {
        this.doubleId = value;
    }

    /**
     * Gets the value of the floatId property.
     * 
     */
    public float getFloatId() {
        return floatId;
    }

    /**
     * Sets the value of the floatId property.
     * 
     */
    public void setFloatId(float value) {
        this.floatId = value;
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
     * Gets the value of the list property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the list property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getList() {
        if (list == null) {
            list = new ArrayList<String>();
        }
        return this.list;
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
