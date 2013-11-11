package edu.heartbeatproject;



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

public class CurrentBeat extends Activity {
	

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.v("Inside","InsideServiceStarter");
		setContentView(R.layout.currentbeat);
	    SharedPreferences pref = CurrentBeat.this.getSharedPreferences("store", 0); // 0 - for private mode
     	String beat = pref.getString("heartrate", null);
     	 EditText e = (EditText) findViewById(R.id.editText1);
     	 e.setText(beat);

	}
	
	
}