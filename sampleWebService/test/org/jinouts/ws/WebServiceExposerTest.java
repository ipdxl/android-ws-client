/**
 * 
 */
package org.jinouts.ws;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author asraf
 *
 */
public class WebServiceExposerTest 
{
	/**
	 * @param args
	 */
	public static void main ( String[] args )
	{
		ApplicationContext context = new ClassPathXmlApplicationContext ( "applicationContext.xml" );
	//	LoginWebService loginWebService = (LoginWebService) context.getBean ( "loginWebService" );

		
	}
	

	

}
