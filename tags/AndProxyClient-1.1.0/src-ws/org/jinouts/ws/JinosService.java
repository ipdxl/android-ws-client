/**
 * Apache License, Version 2.0
 * http://www.apache.org/licenses/LICENSE-2.0.html * 
 * 
 */
package org.jinouts.ws;

import java.lang.reflect.Proxy;
import java.net.URL;

import org.jinouts.xml.namespace.QName;
import org.jinouts.xml.ws.WebServiceFeature;

/**
* @author asraf
* asraf344@gmail.com
* 
*/
public class JinosService
{
	
	private URL wsdlLocation;
	private QName serviceName;

	public JinosService(URL wsdlLocation, QName serviceName)
	{
		this.wsdlLocation = wsdlLocation;
		this.serviceName = serviceName;
	}
	
	public <T> T getPort ( QName portName, Class<T> serviceEndpointInterface )
	{
		return this.getPort ( serviceEndpointInterface.getClassLoader ( ),
				serviceEndpointInterface );
	}

	public <T> T getPort ( QName portName, Class<T> serviceEndpointInterface,
			WebServiceFeature... features )
	{
		return this.getPort ( serviceEndpointInterface.getClassLoader ( ),
				serviceEndpointInterface );
	}

	public <T> T getPort ( Class<T> serviceEndpointInterface )
	{
		return this.getPort ( serviceEndpointInterface.getClassLoader ( ),
				serviceEndpointInterface );
	}

	public <T> T getPort ( Class<T> serviceEndpointInterface,
			WebServiceFeature... features )
	{
		return this.getPort ( serviceEndpointInterface.getClassLoader ( ),
				serviceEndpointInterface );
	}

	public <T> T getPort ( ClassLoader cl, Class<T> serviceInterface )
	{		
		WSInvocationHandler wh = new WSInvocationHandler ( this.wsdlLocation, this.serviceName );
		T proxy = (T) Proxy.newProxyInstance ( cl, 	new Class[] { serviceInterface }, wh );

		return proxy;
	}

	public URL getWsdlLocation ( )
	{
		return wsdlLocation;
	}

	public void setWsdlLocation ( URL wsdlLocation )
	{
		this.wsdlLocation = wsdlLocation;
	}

	public QName getServiceName ( )
	{
		return serviceName;
	}

	public void setServiceName ( QName serviceName )
	{
		this.serviceName = serviceName;
	}
	
	
}
