/**
 * 
 */
package org.jinouts.ui.biz;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

/**
 * @author asraf
 *
 */
public class JinoutsFrameController
{
	String cxfDirPath = "./apache-cxf-2.5.1/bin";
	String tempDirPath = "./temp";
	String libDirPath = "andr-lib";
	String wsClientStub = "wsClientStub";
	String wsClientGenCommand = "wsdl2java -ant -frontend jaxws21 -client -d ";
	
	public void generateJavaFromWSDL ( File destDir, String wsdlUrl )
	{
		try
		{
			File cxfDir = new File ( cxfDirPath );
			File tempDir = new File ( tempDirPath );
			String destDirFullPath = destDir.getAbsolutePath ( ) + File.separator + wsClientStub;
			destDir = new File ( destDirFullPath );
			String tempDirFullPath = tempDir.getAbsolutePath ( );
			
			if ( !destDir.exists ( ) )
			{
				destDir.mkdir ( );
			}
				
			if ( !tempDir.exists ( ) )
			{
				tempDir.mkdir ( );
			}
			String wsFullCommand = cxfDir.getAbsolutePath ( ) + File.separator + wsClientGenCommand +  tempDirFullPath + " "+ wsdlUrl;
			
			System.out.println (cxfDir.getAbsolutePath ( ));
			System.out.println ("Command to execute: "+wsFullCommand );
			String[] cmdWSClientArray = { "cmd.exe", "/c", wsFullCommand };
			
			Runtime runtime = Runtime.getRuntime ( );
			Process proc = runtime.exec ( cmdWSClientArray );
			//InputStream is = p1.getInputStream ( );
			logForProc ( proc );
			
            // any error???
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);
            
            // now modified the cxf generated file
            System.out.println ( "Now Modifying the imports of file " +" src " + tempDirFullPath +" dest "+destDirFullPath );
            CXFToJinoutsWSConverter.modifyImportOfFile ( tempDirFullPath, destDirFullPath );
            
            File libDir = new File ( libDirPath );
            // now copy the library to the dist dir
            FileUtils.copyDirectoryToDirectory ( libDir, destDir );
            
            // now delete the temp file
            FileUtils.cleanDirectory ( tempDir );
            
            JOptionPane.showMessageDialog ( null, "The WS Client Stub generated in the directory: \""+ destDirFullPath+"\"");
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
