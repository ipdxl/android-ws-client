
package org.jinouts.ws.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
public class MbrDetailList 
{

    protected List<MbrDetail> mbrDetail;

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

	public void setMbrDetail ( List<MbrDetail> mbrDetail )
	{
		this.mbrDetail = mbrDetail;
	}

}
