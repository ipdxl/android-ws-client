/**
 * 
 */
package org.jinouts.ws.exception;

/**
 * @author asraf
 * asraf344@gmail.com
 */
public class FieldNotMatchedException extends JinosException
{
	public FieldNotMatchedException()
	{
		super( ExceptionConstants.FIELD_NOT_MATCH );
	}
}
