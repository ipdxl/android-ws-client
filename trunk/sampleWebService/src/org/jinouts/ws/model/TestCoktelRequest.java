
package org.jinouts.ws.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "request" )
public class TestCoktelRequest 
{

    @XmlElement(required = true)
    protected String applId;
    protected int intId;
    protected double doubleId;
    protected float floatId;
       
    protected Date date;
    protected Calendar cal;
    protected ArrayList<String> list;
    protected String[] strArray;
    
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
