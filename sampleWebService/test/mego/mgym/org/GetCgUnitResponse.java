
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
 *         &lt;element name="GetCgUnitResult" type="{http://org.mGym.mego/}CgUnitSnip" minOccurs="0"/>
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
    "getCgUnitResult"
})
@XmlRootElement(name = "GetCgUnitResponse")
public class GetCgUnitResponse {

    @XmlElement(name = "GetCgUnitResult")
    protected CgUnitSnip getCgUnitResult;

    /**
     * Gets the value of the getCgUnitResult property.
     * 
     * @return
     *     possible object is
     *     {@link CgUnitSnip }
     *     
     */
    public CgUnitSnip getGetCgUnitResult() {
        return getCgUnitResult;
    }

    /**
     * Sets the value of the getCgUnitResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link CgUnitSnip }
     *     
     */
    public void setGetCgUnitResult(CgUnitSnip value) {
        this.getCgUnitResult = value;
    }

}
