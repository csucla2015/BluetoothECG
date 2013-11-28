package your.tutorial.graph;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MailSenderActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.currentbeat);
    }
    
    public void sending(View v) {
        try {   
            LongOperation l=new LongOperation();
            l.execute();
            Toast.makeText(this, l.get(), Toast.LENGTH_SHORT).show();
            
        } catch (Exception e) {   
            Log.e("SendMail", e.getMessage(), e);   
        } 
    }
}