/**
 * 
 */
package org.jinouts.ui.biz;

import java.io.File;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;
import org.jinouts.util.AndroidWSClientGenProp;
import org.jinouts.util.AndroidWSClientGenUtil;

/**
 * @author asraf
 *
 */
public class JinoutsFrameController
{
	
	public void generateJavaFromWSDL ( File cxfBinFile, File destDir, String wsdlUrl )
	{
		try
		{		
			File tempDir = new File ( AndroidWSClientGenProp.tempDirPath );
			File jaxbBindingFile = new File ( AndroidWSClientGenProp.jaxbBindingPath );
			
			String destDirFullPath = destDir.getAbsolutePath ( ) + File.separator + AndroidWSClientGenProp.wsClientStub;
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
			
			File cxfWSDL2JavaDir = null;
			if ( cxfBinFile != null )
			{
				cxfWSDL2JavaDir = cxfBinFile;
			}
			else
			{
				System.out.println ( "cxf Home : " + AndroidWSClientGenProp.CXF_HOME_BIN_FULLPATH );
				cxfWSDL2JavaDir = new File ( AndroidWSClientGenProp.CXF_HOME_BIN_FULLPATH );
			}
			String wsFullCommand = AndroidWSClientGenProp.wsClientGenCommand +" "+  tempDirFullPath + " -b "+jaxbBindingFile.getAbsolutePath ( ) +" "+ wsdlUrl;;
			
			System.out.println ("Command to execute: "+wsFullCommand );
			String[] cmdWSClientArray = { "cmd.exe", "/c", wsFullCommand };
			
			Runtime runtime = Runtime.getRuntime ( );
			Process proc = runtime.exec ( cmdWSClientArray, null, cxfWSDL2JavaDir );
			//InputStream is = p1.getInputStream ( );
			AndroidWSClientGenUtil.logForProc ( proc );
			
            // any error???
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);
            
            // now modified the cxf generated file
            System.out.println ( "Now Modifying the imports of file " +" src " + tempDirFullPath +" dest "+destDirFullPath );
            CXFToJinoutsWSConverter.modifyImportOfFile ( tempDirFullPath, destDirFullPath );
            
            File libDir = new File ( AndroidWSClientGenProp.libDirPath );
            // now copy the library to the dist dir
            FileUtils.copyDirectoryToDirectory ( libDir, destDir );
            
            if ( AndroidWSClientGenProp.deleteTemp )
            {
            	 // now delete the temp file
                FileUtils.cleanDirectory ( tempDir );
            }
           
            
            JOptionPane.showMessageDialog ( null, "The WS Client Stub generated in the directory: \""+ destDirFullPath+"\"");
		}
		catch ( Exception ex )
		{
			ex.printStackTrace ( );
		}
	}
	
	
}
