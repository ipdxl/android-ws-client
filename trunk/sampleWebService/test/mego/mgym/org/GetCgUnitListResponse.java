
package mego.mgym.org;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="GetCgUnitListResult" type="{http://org.mGym.mego/}ArrayOfCgUnitSnip" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getCgUnitListResult"
})
@XmlRootElement(name = "GetCgUnitListResponse")
public class GetCgUnitListResponse {

    @XmlElement(name = "GetCgUnitListResult")
    protected ArrayOfCgUnitSnip getCgUnitListResult;

    /**
     * Gets the value of the getCgUnitListResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCgUnitSnip }
     *     
     */
    public ArrayOfCgUnitSnip getGetCgUnitListResult() {
        return getCgUnitListResult;
    }

    /**
     * Sets the value of the getCgUnitListResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCgUnitSnip }
     *     
     */
    public void setGetCgUnitListResult(ArrayOfCgUnitSnip value) {
        this.getCgUnitListResult = value;
    }

}
