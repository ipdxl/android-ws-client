/**
 * 
 */
package org.jinouts.util;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class AndroidWSClientGenProp
{
	//public static String cxfDirPath = "./apache-cxf-2.5.1/bin";
	public static String tempDirPath = "./temp";
	public static String jaxbBindingPath = "./conf/jaxbBinding.xml";
	
	public static String libDirPath = "andr-lib";
	public static String wsClientStub = "wsClientStub";
	public static String wsClientGenCommand = "wsdl2java -ant -frontend jaxws21 -b ./conf/jaxbBinding.xml -client -d ";
	
	//
	public static String WSDL2JAVA = "wsdl2java.bat";
	public static boolean deleteTemp = true;
	public static String CXF_HOME = "%CXF_HOME%";
	public static String CXF_HOME_BIN_FULLPATH = null;

}
