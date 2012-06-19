
package org.jinouts.ws;

import java.util.ArrayList;
import java.util.List;
import org.jinouts.xml.bind.annotation.XmlAccessType;
import org.jinouts.xml.bind.annotation.XmlAccessorType;
import org.jinouts.xml.bind.annotation.XmlType;


/**
 * <p>Java class for testListOfObjRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="testListOfObjRequest">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mbrList" type="{http://ws.jinouts.org/}mbrDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "testListOfObjRequest", propOrder = {
    "mbrList"
})
public class TestListOfObjRequest {

    protected List<MbrDetail> mbrList;

    /**
     * Gets the value of the mbrList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mbrList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMbrList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MbrDetail }
     * 
     * 
     */
    public List<MbrDetail> getMbrList() {
        if (mbrList == null) {
            mbrList = new ArrayList<MbrDetail>();
        }
        return this.mbrList;
    }

}
