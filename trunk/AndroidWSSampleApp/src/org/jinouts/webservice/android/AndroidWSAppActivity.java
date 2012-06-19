package org.jinouts.webservice.android;

import org.jinos.webservice.android.R;
import org.jinouts.ws.EnterResponse;
import org.jinouts.ws.Login;
import org.jinouts.ws.LoginService;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class AndroidWSAppActivity extends Activity 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) 
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
    }
    
    
    public void callWebService ( View view )
    {
    	Log.i ( "AndroidWSAppActivity", "Button Pressed, Now call the webservice !!!!!!!!" );
    	LoginServiceTask loginTask = new LoginServiceTask ( );
    	loginTask.execute ( new Void[]{} );
    }
    
    private class LoginServiceTask extends AsyncTask<Void, Void, String>
    {

		@Override
		protected String doInBackground ( Void... params )
		{
			LoginService service = new LoginService ( );
			Login login = service.getLoginPort ( );
			EditText userEditText = (EditText)findViewById ( R.id.userIdText );
			EditText passEditText = (EditText)findViewById ( R.id.passEditText );
			
			String user = userEditText.getText ( ).toString ( );
			String passwd = passEditText.getText ( ).toString ( );
			String resp = login.enter (  user, passwd );
			
			return resp;
		}
		
		@Override
		protected void onPostExecute(String resp)
        {        	
        	TextView respTextView = (TextView)findViewById ( R.id.respTextView );
        	if ( resp != null )
        	{
        		Log.i (  "AndroidWSAppActivity ", "Success Code From Resp !!!!!!: "+ resp );
            	//Log.i (  "AndroidWSAppActivity ", "Error Code From Resp !!!!!!: "+ resp.getErrorCode ( ) );
            	
        		respTextView.setText ( "Response: "+resp );
    	        
        	}
        	else
        	{
        		respTextView.setText ( "Couldn't get Response" );    	        
        	}
        	
        	
        }
    	
    }
}