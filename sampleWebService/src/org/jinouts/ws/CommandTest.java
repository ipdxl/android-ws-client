/**
 * 
 */
package org.jinouts.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author asraf
 * 
 */
public class CommandTest
{

	/**
	 * @param args
	 */
	public static void main ( String[] args )
	{
		try
		{
			//testCommand ( );
			testEcho ( );

		}
		catch ( Exception ex )
		{
			ex.printStackTrace ( );
		}

	}

	static void testCommand ( ) throws Exception
	{
		String cxfDirCommand = "cd D:\\andPro\\RNDandroid\\apache-cxf-2.5.1\\bin";
		String wsClientGenCommand = "D:\\andPro\\RNDandroid\\apache-cxf-2.5.1\\bin\\wsdl2java -ant -frontend jaxws21 -client -d D:\\andPro\\vault http://116.68.194.105:9010/AndProxyClientRespTestWS?WSDL";
		Runtime runtime = Runtime.getRuntime ( );
		try
		{
			System.out.println (cxfDirCommand);
			String[] cmdDDir = { "cmd.exe", "/c", "D:" };
			
			Process proc = runtime.exec ( cmdDDir );
			
			logForProc ( proc );
            
			String[] cmdArray = { "cmd.exe", "/c", cxfDirCommand };
			proc = runtime.exec ( cmdArray );
			//InputStream is = p1.getInputStream ( );
			logForProc ( proc );
			
			String[] cmdWSClientArray = { "cmd.exe", "/c", wsClientGenCommand };
			proc = runtime.exec ( cmdWSClientArray );
			//InputStream is = p1.getInputStream ( );
			logForProc ( proc );
			
            // any error???
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);
			/*int i = 0;
			while ((i = is.read ( )) != -1)
			{
				System.out.print ( (char) i );
			}*/
		}
		catch ( Exception ex )
		{
			ex.printStackTrace ( );
		}

	}
	
	static void testEcho ( ) throws Exception
	{
		String echoCommand = "echo %CXF_HOME%";
		
		Runtime runtime = Runtime.getRuntime ( );
		try
		{
			System.out.println (echoCommand);
			
            
			String[] cmdArray = { "cmd.exe", "/c", echoCommand };
			Process proc = runtime.exec ( cmdArray );
			//InputStream is = p1.getInputStream ( );
			logForProc ( proc );
			
			
			
            // any error???
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);
			
		}
		catch ( Exception ex )
		{
			ex.printStackTrace ( );
		}

	}

	static void logForProc ( Process proc )
	{
		 // any error message?
        StreamGobbler errorGobbler = new  StreamGobbler(proc.getErrorStream(), "ERROR");            
        
        // any output?
        StreamGobbler  outputGobbler = new 
            StreamGobbler(proc.getInputStream(), "OUTPUT");
            
        // kick them off
        errorGobbler.start();
        outputGobbler.start();
	}
	public static class StreamGobbler extends Thread
	{
		InputStream is;
		String type;

		StreamGobbler(InputStream is, String type)
		{
			this.is = is;
			this.type = type;
		}

		public void run ( )
		{
			try
			{
				InputStreamReader isr = new InputStreamReader ( is );
				BufferedReader br = new BufferedReader ( isr );
				String line = null;
				while ((line = br.readLine ( )) != null)
					System.out.println ( type + ">" + line );
			}
			catch ( IOException ioe )
			{
				ioe.printStackTrace ( );
			}
		}
	}

}
