package org.jinouts.ws.client;

import org.jinos.ws.client.R;
import org.jinouts.webservice.service.Login;
import org.jinouts.webservice.service.LoginService;
import org.jinouts.webservice.service.LoginServiceResponse;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    	
    	
    	LoginServiceTask loginTask = new LoginServiceTask ( );
    	loginTask.execute ( new Void[]{} );
    	
    }
  
    private class LoginServiceTask extends AsyncTask<Void, Void, LoginServiceResponse>
    {
		@Override
		protected LoginServiceResponse doInBackground ( Void... params )
		{
			LoginService service = new LoginService ( );
			Login login = service.getLoginPort ( );
			
			LoginServiceResponse resp = login.login ( "asraf", "asraf" );
			
			return resp;
		}
		
		@Override
		protected void onPostExecute(LoginServiceResponse resp)
        {        	
        	TextView respTextView = (TextView)findViewById ( R.id.respTextView );
        	if ( resp != null )
        	{
        		Log.i (  "AndroidWSAppActivity ", "Success Code From Resp !!!!!!: "+ resp.getSuccessCode ( ) );
            	Log.i (  "AndroidWSAppActivity ", "Error Code From Resp !!!!!!: "+ resp.getErrorCode ( ) );
            	
        		respTextView.setText ( "SuccessCode: "+resp.getSuccessCode ( ) + " Message: "+resp.getSuccessMessage ( ) 
            			+" \nErrorCode: "+resp.getErrorCode ( ) + " Message: "+resp.getErrorMessage ( ) );
    	        
        	}
        	else
        	{
        		respTextView.setText ( "Couldn't get Response" );    	        
        	}
        	
        	
        }
    	
    }
   

	
}