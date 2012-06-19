
package mego.mgym.org;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CgUnitSnip complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CgUnitSnip">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="Name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UnitArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DetailAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Contact" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Path" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="BusinessHours" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="PictureName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Location" type="{http://org.mGym.mego/}Location" minOccurs="0"/>
 *         &lt;element name="SupportedSports" type="{http://org.mGym.mego/}ArrayOfString" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CgUnitSnip", propOrder = {
    "id",
    "name",
    "unitArea",
    "detailAddress",
    "contact",
    "path",
    "description",
    "businessHours",
    "pictureName",
    "location",
    "supportedSports"
})
public class CgUnitSnip {

    @XmlElement(name = "ID")
    protected int id;
    @XmlElement(name = "Name")
    protected String name;
    @XmlElement(name = "UnitArea")
    protected String unitArea;
    @XmlElement(name = "DetailAddress")
    protected String detailAddress;
    @XmlElement(name = "Contact")
    protected String contact;
    @XmlElement(name = "Path")
    protected String path;
    @XmlElement(name = "Description")
    protected String description;
    @XmlElement(name = "BusinessHours")
    protected String businessHours;
    @XmlElement(name = "PictureName")
    protected String pictureName;
    @XmlElement(name = "Location")
    protected Location location;
    @XmlElement(name = "SupportedSports")
    protected ArrayOfString supportedSports;

    /**
     * Gets the value of the id property.
     * 
     */
    public int getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setID(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the unitArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitArea() {
        return unitArea;
    }

    /**
     * Sets the value of the unitArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitArea(String value) {
        this.unitArea = value;
    }

    /**
     * Gets the value of the detailAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * Sets the value of the detailAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDetailAddress(String value) {
        this.detailAddress = value;
    }

    /**
     * Gets the value of the contact property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContact() {
        return contact;
    }

    /**
     * Sets the value of the contact property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContact(String value) {
        this.contact = value;
    }

    /**
     * Gets the value of the path property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPath() {
        return path;
    }

    /**
     * Sets the value of the path property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPath(String value) {
        this.path = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the businessHours property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBusinessHours() {
        return businessHours;
    }

    /**
     * Sets the value of the businessHours property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBusinessHours(String value) {
        this.businessHours = value;
    }

    /**
     * Gets the value of the pictureName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPictureName() {
        return pictureName;
    }

    /**
     * Sets the value of the pictureName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPictureName(String value) {
        this.pictureName = value;
    }

    /**
     * Gets the value of the location property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setLocation(Location value) {
        this.location = value;
    }

    /**
     * Gets the value of the supportedSports property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getSupportedSports() {
        return supportedSports;
    }

    /**
     * Sets the value of the supportedSports property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setSupportedSports(ArrayOfString value) {
        this.supportedSports = value;
    }

}
