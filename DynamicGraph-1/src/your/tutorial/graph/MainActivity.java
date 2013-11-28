package your.tutorial.graph;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainforemail);
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



