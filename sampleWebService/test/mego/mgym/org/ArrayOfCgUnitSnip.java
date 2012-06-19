
package mego.mgym.org;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ArrayOfCgUnitSnip complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ArrayOfCgUnitSnip">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CgUnitSnip" type="{http://org.mGym.mego/}CgUnitSnip" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ArrayOfCgUnitSnip", propOrder = {
    "cgUnitSnip"
})
public class ArrayOfCgUnitSnip {

    @XmlElement(name = "CgUnitSnip", nillable = true)
    protected List<CgUnitSnip> cgUnitSnip;

    /**
     * Gets the value of the cgUnitSnip property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the cgUnitSnip property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCgUnitSnip().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link CgUnitSnip }
     * 
     * 
     */
    public List<CgUnitSnip> getCgUnitSnip() {
        if (cgUnitSnip == null) {
            cgUnitSnip = new ArrayList<CgUnitSnip>();
        }
        return this.cgUnitSnip;
    }

}
