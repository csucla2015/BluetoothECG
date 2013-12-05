package your.tutorial.graph;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
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

public class InputScreen extends Activity {
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("Inside","InsideServiceStarter");


	  
		setContentView(R.layout.inputscreen);
		final ImageButton submitButton = (ImageButton) findViewById(R.id.submitbutton);

		  submitButton.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) {
		    	     startService(new Intent(getApplicationContext(), SAService.class));
	    		        ServiceStarter.serviceOn = true;
	    		        
	    		        // Go grab the device list
	                    Intent serverIntent = new Intent(getApplicationContext(), DeviceListActivity.class);
	                    startActivityForResult(serverIntent, 1);
		    	  EditText e2 = (EditText) findViewById(R.id.editText1);
		 	     String name = e2.getText().toString();
		 	     Log.v("str2",name);
		 	    EditText e3 = (EditText) findViewById(R.id.editText2);
		 	     String age = e3.getText().toString();
		 	     Log.v("str2",age);
		 	    EditText e4 = (EditText) findViewById(R.id.editText3);
		 	     String docphone = e4.getText().toString();
		 	     Log.v("str2",docphone);
		 	    EditText e5 = (EditText) findViewById(R.id.editText4);
		 	     String relative = e5.getText().toString();
		 	     Log.v("str2",relative);
		 	    EditText e6 = (EditText) findViewById(R.id.editText5);
		 	     String height = e6.getText().toString();
		 	     Log.v("str2",height);
		 	    EditText e7 = (EditText) findViewById(R.id.editText6);
		 	     String weight = e6.getText().toString();
		 	     Log.v("str2",weight);
		 	     SharedPreferences pref1 = InputScreen.this.getSharedPreferences("store", 0); // 0 - for private mode
	 		   	 Editor editor = pref1.edit();
	 		     editor.putString("name", name); 
	 		     editor.putString("age", age);
	 		     editor.putString("docphone", docphone); 
	 		     editor.putString("relphone", relative); 
	 		     editor.putString("height", height);
	 		     editor.putString("ethnicity", weight);
	             
	             String toastText = "Service is not on";
 				if (ServiceStarter.serviceOn)
 				{
 					toastText = "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg;
 				}
 				editor.putString("heartrate", String.valueOf(SAService.avgRate));
 				editor.commit();
 				Intent intent = new Intent(InputScreen.this, StartScreen.class);
 			    		
	     		startActivity(intent);
	     		finish();
 				//Toast.makeText(getApplicationContext(), toastText, Toast.LENGTH_SHORT).show();
		 	     
		      }
		    });
		}
}
