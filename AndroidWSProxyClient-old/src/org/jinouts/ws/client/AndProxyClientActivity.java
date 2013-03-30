package org.jinouts.ws.client;

import org.jinos.ws.client.R;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class AndProxyClientActivity extends Activity 
{
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    public void callWSTest ( View view )
    {
    	Log.d ( "AndProxyClientActivity", "Button Pressed !!!!!!!!" );
    	EditText userText = (EditText)findViewById ( R.id.userTextField );
    	EditText passText = (EditText)findViewById ( R.id.passTextField );
    	
    	Log.d ( "AndProxyClientActivity", "User and  Pass: "+userText.getText ( ).toString ( ) + " "+ passText.getText ( ).toString ( ) );
    	
    	
    	/*LoginServiceTask loginTask = new LoginServiceTask ( );
    	loginTask.execute ( new Void[]{} );*/
    	
    }
  
	
}