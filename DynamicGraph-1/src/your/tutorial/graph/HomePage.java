package your.tutorial.graph;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class HomePage extends Activity {
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("Inside","InsideServiceStarter");


		startService(new Intent(getApplicationContext(), SAService.class));
        Intent serverIntent = new Intent(getApplicationContext(), DeviceListActivity.class);
        startActivityForResult(serverIntent, 1);
		setContentView(R.layout.homepage);
		 Button submitButton = (Button) findViewById(R.id.button1);

		  submitButton.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) {
		    	  Intent i1 = new Intent(HomePage.this, DynamicGraphActivity.class);
					
					startActivity(i1);		     
		      }
		    });
		  Button emergencies = (Button) findViewById(R.id.button3);

		  emergencies.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) 
		      {
		    	  Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
		    	  intent.setType("text/plain");
		    	  intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
		    	  intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
		    	  intent.setData(Uri.parse("mailto:default@recipient.com")); // or just "mailto:" for blank
		    	  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
		    	  startActivity(intent);  		     
		      }
		    });
		  Button currentrun = (Button) findViewById(R.id.button2);

		  currentrun.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) 
		      {
		    	  Intent i1 = new Intent(HomePage.this, CurrentBeat.class);			
				  startActivity(i1);			     
		      }
		    });
		  }
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
        case 1:
            // When DeviceListActivity returns with a device to connect
            if (resultCode == Activity.RESULT_OK) {
                // Get the device MAC address
                String address = data.getExtras()
                                     .getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
                
                // Send the adderss to the service
                Intent msgToService = new Intent(this, SAService.class);
                msgToService.putExtra("edu.ucla.cs.SmartAlarm.address", address);
                startService(msgToService);
 
            }
        
            break;
        }
    }
}
