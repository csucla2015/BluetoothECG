package your.tutorial.graph;

import org.achartengine.GraphicalView;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.widget.Toast;

public class DynamicGraphActivity extends Activity {

	private static GraphicalView view;
	private LineGraph line = new LineGraph();
	private static Thread thread;
	static final int RANGE_DIALOG_ID = 0;
	boolean serviceOn=false;

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
		//Toast.makeText(getApplicationContext(), "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg, Toast.LENGTH_SHORT).show();
		
        // Go grab the device list
        new CountDownTimer(15 * 1000, 1000) {
            int x =0;

            @Override
            public void onTick(long millisUntilFinished) {
            	
            	if(x==0)
            	{
            		startService(new Intent(getApplicationContext(), SAService.class));
                    serviceOn = true;
                    Intent serverIntent = new Intent(getApplicationContext(), DeviceListActivity.class);
                    startActivityForResult(serverIntent, 1);
                    x++;
            	}
            
         
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
								Log.v("i",String.valueOf(i));
								 if (i > 30){
	                                 line.delPoint(0);
	                                 Point p1 = new Point(i,(int) SAService.avgRate);
	                                 line.addNewPoints(p1);
	                         }else{
	                             Point p1 = new Point(i,(int) SAService.avgRate);

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
          					
          	        		//if(i%5==0)
          					//Toast.makeText(getApplicationContext(), "Heart Rate: " + SAService.avgRate + " min avg: " + SAService.minAvg, Toast.LENGTH_SHORT).show();

          				//	if (i % 50 == 0)
          					//	line.delPoints();
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

}