/**
 * 
 */
package org.jinouts.ui;

import org.jinouts.ui.biz.JinoutsFrameController;

/**
 * @author asraf
 * 
 */
public class UIMain
{

	static final String title = "Jinos Android WS Client Generator";

	/**
	 * @param args
	 */
	public static void main ( String[] args )
	{
		java.awt.EventQueue.invokeLater ( new Runnable ( )
		{
			public void run ( )
			{
				JinoutsFrameController controller = new JinoutsFrameController ( );
				JinoutsJFrame frame = new JinoutsJFrame ( title, controller );
				frame.setVisible ( true );
			}
		} );

	}

}
