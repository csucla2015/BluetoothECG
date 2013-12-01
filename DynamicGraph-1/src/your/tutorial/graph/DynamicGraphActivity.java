package your.tutorial.graph;

import java.util.Random;

import org.achartengine.GraphicalView;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

public class DynamicGraphActivity extends Activity {
	private Button btnAlarm;
	private Button btnStop;
	private Uri notification; 
	private MediaPlayer player;
 	private AudioManager mAudioManager;
 	private int originalVolume;
	private static GraphicalView view;
	private LineGraph line = new LineGraph();
	private static Thread thread;
	static final int RANGE_DIALOG_ID = 0;
	boolean serviceOn=false;
	String TAG = "test";
	protected Dialog onCreateDialog(int id) {
        switch (id) {
        
        case RANGE_DIALOG_ID:
        	final CharSequence[] items = {"30 min", "1 hr", "1.5hr", "2 hr"};
        	AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Pick a range");
            builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int item) {
                	if (ServiceStarter.serviceOn)
                		SAService.wakeupRange = (item+1)*30;
                }
            });
            AlertDialog alert = builder.create();
            return alert;
        }
        return null;
	}
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		  notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
		     //make a player that will play the sound
		     player = MediaPlayer.create(getApplicationContext(), notification);
		     //get the audio system and get the original volume of the device
		     mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
		     originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);

		//Toast.makeText(getApplicationContext(), "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg, Toast.LENGTH_SHORT).show();
			
		     alert(22, "Male", (float) 1.8288, 170, "African American", 100, "Moderate") ;

        // Go grab the device list
        new CountDownTimer(1 * 1000, 1000) {
            int x =0;

            @Override
            public void onTick(long millisUntilFinished) {
            	
            	/*if(x==0)
            	{
            		startService(new Intent(getApplicationContext(), SAService.class));
                    serviceOn = true;
                    Intent serverIntent = new Intent(getApplicationContext(), DeviceListActivity.class);
                    startActivityForResult(serverIntent, 1);
                    x++;
            	}*/
            
         
            }

            @Override
            public void onFinish() 
            {
        		Toast.makeText(getApplicationContext(), "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg, Toast.LENGTH_SHORT).show();

        		  thread = new Thread() {
          			public void run()
          			{	

          				//Condition for loop needs to be changed to as long as heartbeat tab is open!
          				for (int i=0;i<200;i++) 
          				{
          					try {
          						
								Thread.sleep(2000);
								if(i==5)
          						{
									 Intent i1 = new Intent(DynamicGraphActivity.this, MainActivity.class);
										
										startActivity(i1);	
										finish();
          						}
								Log.v("i",String.valueOf(i));
								 if(SAService.avgRate > 150)
	                                 	playAlarm();
	                                 
								 if (i > 30){
	                                 line.delPoint(0);
	                                 Random randomGenerator = new Random();
	                                int ran = -5 + randomGenerator.nextInt(10);
		                        	 Log.v("ran", String.valueOf(ran));

	                                 Point p1 = new Point(i,(int) SAService.avgRate + ran);
	                                
	                                 line.addNewPoints(p1);
	                         }else{
	                        	 Random randomGenerator = new Random();
	                                int ran = -5 + randomGenerator.nextInt(10);	                        	 
	                                Log.v("ran", String.valueOf(ran));
	                             Point p1 = new Point(i,(int) SAService.avgRate + ran);

	                                 line.addNewPoints(p1);
	                         }
							} catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
          					Log.v("hearrate","Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg);
          					try {
          						Thread.sleep(50);
          					} catch (InterruptedException e) {
          						
          						// TODO Auto-generated catch block
          						e.printStackTrace();
          					}
          					Point p = MockData.getDataFromReceiver(i); // We got new data!
          		
          					view.repaint();
          				}
          			}
          		};
          		thread.start();
            	Log.v("work,","work");
            }
            
          
        }.start();	 
  
        Log.v("werread","weread");
        if (DeviceListActivity.serviceOn)
        		
		{
            Log.v("here1","here1");

		}
		Toast.makeText(getApplicationContext(), "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg, Toast.LENGTH_SHORT).show();


		
	}

	@Override
	protected void onStart() {
		super.onStart();
		view = line.getView(this);
		setContentView(view);

	}	
	////Hans function
	public void alert(int age, String gender, float height, float weight, String ethnicity, float hbm, String intensity) {
        double wtkg = weight/2.2;
        double htmeter = height/39.37;
        double BMI = wtkg/(htmeter*htmeter);
        double MHR = 0;
        double exceedMHR = 0;
        double modExmin = 0;
        double modExmax = 0;
        double vigExmin = 0;
        double vigExmax = 0;
        if (gender.equals("Male")) {
           MHR = 214 - (0.8 * age);
           exceedMHR = MHR * 1.1;
        }
       else {
          MHR = 209 - (0.9 * age);
          if (ethnicity == "African American") //African Females
               exceedMHR = MHR;
           else
               exceedMHR = MHR * 1.1;
       }
       if (hbm > exceedMHR)
           Log.v(TAG,"Danger! HBM exceeds Maximum heart rate");
       if (gender == "Female" && ethnicity == "African American")
       {
           modExmin = 0.36 * MHR;
           modExmax = 0.77 * MHR;
           vigExmin = 0.567 * MHR;
           vigExmax = 0.935 * MHR;
       }
       else
       {
           modExmin = 0.45 * MHR;
           modExmax = 0.77 * MHR;
           vigExmin = 0.63 * MHR;
           vigExmax = 0.935 * MHR;
       }
       
       if (BMI <= 18.5) //underweight
       {
           modExmin *= 0.95;
           modExmax *= 0.95;
           vigExmin *= 0.95;
           vigExmax *= 0.95;
       }
       else if(BMI >= 25 && BMI <= 29.9) //overweight
       {
           modExmin *= 1.05;
           modExmax *= 1.05;
           vigExmin *= 1.05;
           vigExmax *= 1.05;
       }
       else if (BMI >= 30) //obese
       {
           modExmin *= 1.1;
           modExmax *= 1.1;
           vigExmin *= 1.1;
           vigExmax *= 1.1;
       }
       Log.v("mod", String.valueOf((modExmin)));
       Log.v("mod", String.valueOf((vigExmin)));
       Log.v("mod", String.valueOf((exceedMHR)));

       
       if (intensity == "Moderate")
       {
           if (hbm < modExmin)
               Log.v(TAG,"You are not getting the most of your workout");
           if (hbm > modExmax)
               Log.v(TAG,"Alert! Heart beat per minute is too high!");
       }
       else
       {
           if (hbm < vigExmin)
               Log.v(TAG,"You are not getting the most of your workout");
           if (hbm > vigExmax)
               Log.v(TAG,"Alert! HBM is too high!");
       }
       
    }

	////////
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
                Log.v("heart","heart");
            	if (serviceOn)
        		{
                    Log.v("here2","here2");
        		}
                /*
                thread = new Thread() {
    			public void run()
    			{	
    				//Condition for loop needs to be changed to as long as heartbeat tab is open!
    				for (int i=0;i<200;i++) 
    				{
    					
    					try {
    						Thread.sleep(50);
    					} catch (InterruptedException e) {
    						
    						// TODO Auto-generated catch block
    						e.printStackTrace();
    					}
    					Point p = MockData.getDataFromReceiver(i); // We got new data!
    					line.addNewPoints(p); // Add it to our graph
    				//	if (i % 50 == 0)
    					//	line.delPoints();
    					view.repaint();
    				}
    			}
    		};
    		thread.start();
    		*/
            }
            if (DeviceListActivity.serviceOn)
    		{
                Log.v("here3","here3");
    		}
            break;
        }
    }
	public void playAlarm()
	 {
		 //set volume to MAX
		 mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC), 0);
    	 //start the alarm
		 player.start();
	 }
	 public void stopAlarm()
	 {
		 //set the volume to the original volume
		 mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, originalVolume, 0);
		 //reset the player so that it can play again when called to play
		 player.stop();
		 player.reset();
		 player = MediaPlayer.create(getApplicationContext(), notification);
	 }

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/

}