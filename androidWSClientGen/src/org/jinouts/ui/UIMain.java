/**
 * 
 */
package org.jinouts.ui;

import org.jinouts.ui.biz.JinoutsFrameController;
import org.jinouts.util.WSClientGenPropertiesLoader;

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
		// Load the properties
		WSClientGenPropertiesLoader.loadProperties ( );
		
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
