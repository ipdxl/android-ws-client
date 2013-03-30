/**
 * 
 */
package org.jinouts.ws.exception;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class JinosException extends Exception
{
	protected int errorCode;
	protected String errorMessage;
	
	public JinosException ( int errorCode )
	{
		this.errorCode = errorCode;
	}
	
	public int getErrorCode ( )
	{
		return errorCode;
	}
	public void setErrorCode ( int errorCode )
	{
		this.errorCode = errorCode;
	}
	public String getErrorMessage ( )
	{
		return errorMessage;
	}
	public void setErrorMessage ( String errorMessage )
	{
		this.errorMessage = errorMessage;
	}
	
	
}
