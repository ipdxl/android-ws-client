
package org.jinouts.ws.model;

import javax.print.attribute.standard.Sides;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for travelCallCenterMemberAuthenticationResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="travelCallCenterMemberAuthenticationResponse">
 *   &lt;complexContent>
 *     &lt;extension base="{https://pointsbank.citi.epsilon.com}baseResponse">
 *       &lt;sequence>
 *         &lt;element name="memberDetailList" type="{https://pointsbank.citi.epsilon.com}mbrDetailList" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "travelCallCenterMemberAuthenticationResponse", propOrder = {
    "memberDetailList"
})
public class TravelCallCenterMemberAuthenticationResponse
    extends BaseResponse
{

    protected MbrDetailList memberDetailList;

    /**
     * Gets the value of the memberDetailList property.
     * 
     * @return
     *     possible object is
     *     {@link MbrDetailList }
     *     
     */
    public MbrDetailList getMemberDetailList() {
        return memberDetailList;
    }

    @Override
	public String toString ( )
	{
    	int listSize = (this.memberDetailList == null) ? 0 : this.memberDetailList.getMbrDetail ( ).size ( ); 
		String respString = "Error code: "+this.errorCode + " Error message " + this.errorMessage +" list size: " + listSize ;
		
		return respString;
	}

	/**
     * Sets the value of the memberDetailList property.
     * 
     * @param value
     *     allowed object is
     *     {@link MbrDetailList }
     *     
     */
    public void setMemberDetailList(MbrDetailList value) {
        this.memberDetailList = value;
    }
    
    

}
