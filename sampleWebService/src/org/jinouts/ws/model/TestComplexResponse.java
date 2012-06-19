
package org.jinouts.ws.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for mbrDetailList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="mbrDetailList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="mbrDetail" type="{https://pointsbank.citi.epsilon.com}mbrDetail" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TestComplexResponse 
{

    protected List<MbrDetail> mbrDetail;
    protected String applId;
    protected int intId;
    protected double doubleId;
    protected float floatId;
       
    protected Date date;
    protected Calendar cal;
    protected ArrayList<String> list;
    protected String[] strArray;
    
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

	public String getApplId ( )
	{
		return applId;
	}

	public void setApplId ( String applId )
	{
		this.applId = applId;
	}

	public int getIntId ( )
	{
		return intId;
	}

	public void setIntId ( int intId )
	{
		this.intId = intId;
	}

	public double getDoubleId ( )
	{
		return doubleId;
	}

	public void setDoubleId ( double doubleId )
	{
		this.doubleId = doubleId;
	}

	public float getFloatId ( )
	{
		return floatId;
	}

	public void setFloatId ( float floatId )
	{
		this.floatId = floatId;
	}

	public Date getDate ( )
	{
		return date;
	}

	public void setDate ( Date date )
	{
		this.date = date;
	}

	public Calendar getCal ( )
	{
		return cal;
	}

	public void setCal ( Calendar cal )
	{
		this.cal = cal;
	}

	public ArrayList<String> getList ( )
	{
		return list;
	}

	public void setList ( ArrayList<String> list )
	{
		this.list = list;
	}

	public String[] getStrArray ( )
	{
		return strArray;
	}

	public void setStrArray ( String[] strArray )
	{
		this.strArray = strArray;
	}

}
