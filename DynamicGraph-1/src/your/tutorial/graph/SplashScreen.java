package your.tutorial.graph;


import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
 
public class SplashScreen extends Activity {
 
    // Splash screen timer
    private static int SPLASH_TIME_OUT = 3000;
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
 
        new Handler().postDelayed(new Runnable() {
 
            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */
 
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
            	  File f4 = new File(
                    		"/data/data/your.tutorial.graph/shared_prefs/TourPref.xml");
                    		if (f4.exists())
                    		{	
                    			 Intent i = new Intent(SplashScreen.this, StartScreen.class);
                                 startActivity(i);
                  
                    		}
               
                    		else
                    		{
                    			 SharedPreferences pref555 = getApplicationContext().getSharedPreferences("TourPref", 0); // 0 - for private mode
                 		    	Editor editor555 = pref555.edit();
                 		    	editor555.putString("firstimevisit", "no");
                 		    	editor555.commit();
                 		    	 Intent i = new Intent(SplashScreen.this, InputScreen.class);
                                 startActivity(i);
                  
                    			Log.v("does not", "Does not");
                    		}
                    		
                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
 
}