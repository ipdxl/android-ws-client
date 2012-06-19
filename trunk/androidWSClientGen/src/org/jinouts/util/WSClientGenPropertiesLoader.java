/**
 * 
 */
package org.jinouts.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class WSClientGenPropertiesLoader
{
	public static void loadProperties () 
	{
		Properties properties = new Properties ( );
		try
		{
			properties.load( new FileInputStream ( "conf/andWSClientGen.properties" ));
			
			// now get the properties
			String wsClientGenCommand = (String)properties.get ( "cxf.ws.Client.Command" );
			
			
			
			System.out.println ("Properties: "+wsClientGenCommand  );
			
			AndroidWSClientGenProp.wsClientGenCommand = wsClientGenCommand;
			
			
		}
		catch ( FileNotFoundException e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
