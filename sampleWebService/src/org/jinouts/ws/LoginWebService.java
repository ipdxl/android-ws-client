/**
 * 
 */
package org.jinouts.ws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * @author asraf
 *
 */
@WebService(serviceName="LoginService",  name="login", targetNamespace = "http://ws.jinouts.org/" )
public class LoginWebService
{
	@WebMethod
	public String enter ( @WebParam(name="user") String userId, @WebParam(name="pass") String password )
	{
		System.out.println ("In the login Method: user-" +userId + "Passwd: "+ password );
		return "Success";
	}
}
