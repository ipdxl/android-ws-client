/**
 * 
 */
package org.jinouts.util;

import java.io.File;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class AndroidWSGenUtilTest
{

	/**
	 * @param args
	 */
	public static void main ( String[] args )
	{
		// TODO Auto-generated method stub
		try
		{
			//testCXFHomeCommand ( );
			//testCXFBinDir ( );
			testCXFCommandWithSpace();
		}
		catch ( Exception e )
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	static void testCXFHomeCommand () throws Exception
	{
		System.out.println ("The cxf Home is valid: "+AndroidWSClientGenUtil.isCXFHOMEValid ( ) );
	}
	
	static void testCXFBinDir () throws Exception
	{
		String cxfPath = "D:\\andPro\\androidWSClientGen\\apache-cxf-2.5.1\\bin";
		
		System.out.println ("The cxf bin is valid: "+AndroidWSClientGenUtil.isCXFBinValid ( new File ( cxfPath ) )  );
	}
	
	static void testCXFCommandWithSpace () throws Exception
	{
		String cxfPath = "D:\\andPro\\androidWSClientGen\\apache cxf-2.5.1\\bin";
		
		String wsFullCommand = "wsdl2java -ant -frontend jaxws21  -client -d D:\\vault D:/asraf.sohel/download/wsdl.xml";
		
		System.out.println ( wsFullCommand );
		String[] cmdWSClientArray = { "cmd.exe", "/c", wsFullCommand };
		
		Runtime runtime = Runtime.getRuntime ( );
		
		File batDir = new File ( AndroidWSClientGenProp.CXF_HOME + File.separator + "bin");
		System.out.println ( batDir.getAbsolutePath ( ) );
		
		Process proc = runtime.exec ( cmdWSClientArray, null, new File(cxfPath) );
		AndroidWSClientGenUtil.logForProc ( proc );
		
		
		
		//System.out.println ("The cxf bin is valid: "+AndroidWSClientGenUtil.isCXFBinValid ( new File ( cxfPath ) )  );
	}
	
	

}
