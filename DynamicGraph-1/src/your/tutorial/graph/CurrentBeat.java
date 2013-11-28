package your.tutorial.graph;




import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class CurrentBeat extends Activity {
	boolean clickedb=true;
	EditText e;
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		Log.v("Inside","InsideServiceStarter");
		setContentView(R.layout.currentbeat);
	    SharedPreferences pref = CurrentBeat.this.getSharedPreferences("store", 0); // 0 - for private mode
     	String beat = pref.getString("heartrate", null);
     	e = (EditText) findViewById(R.id.editText1);
  	    final Button myButton = new Button(this);
  	    anotherfunction();
	}
	 void anotherfunction()
	 {  
		 new CountDownTimer(6 * 1000, 1000) {
	            int x = 5;
	            
	            @Override
	            public void onTick(long millisUntilFinished) {
		     		e.setText(String.valueOf(SAService.avgRate));

	            	Log.v("tick","tick");
	            }

	            @Override
	            public void onFinish() 
	            {
	            
		            	Log.v("gotcha","gotcha");
			     		e.setText(String.valueOf(SAService.avgRate));

	            }
	            
	          
	        }.start();	  

	}
		
	 
}