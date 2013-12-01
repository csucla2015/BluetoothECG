
package your.tutorial.graph;

import java.util.ArrayList;



import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	private Button btnAlarm;
	private Button btnStop;
	private Uri notification; 
	private MediaPlayer player;
 	private AudioManager mAudioManager;
 	private int originalVolume;
 	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainforemail);
        notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
	     //make a player that will play the sound
	     player = MediaPlayer.create(getApplicationContext(), notification);
	     //get the audio system and get the original volume of the device
	     mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
	     originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	     try {   
	            LongOperation l=new LongOperation();
	            l.execute();
	            Toast.makeText(this, l.get(), Toast.LENGTH_SHORT).show();
	            
	        } catch (Exception e) {   
	            Log.e("SendMail", e.getMessage(), e);   
	        } 
	     playAlarm();
     	sendSMS("3107093095", "this is a test");
     	

    }
    
    
    public void sending(View v) {
    	stopAlarm();
       
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
	    private void sendSMS(String phoneNumber, String message) {
	        ArrayList<PendingIntent> sentPendingIntents = new ArrayList<PendingIntent>();
	        ArrayList<PendingIntent> deliveredPendingIntents = new ArrayList<PendingIntent>();
	        PendingIntent sentPI = PendingIntent.getBroadcast(this, 0,
	                new Intent(this, SmsSentReceiver.class), 0);
	        PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,
	                new Intent(this, SmsDeliveredReceiver.class), 0);
	        try {
	            SmsManager sms = SmsManager.getDefault();
	            ArrayList<String> mSMSMessage = sms.divideMessage(message);
	            for (int i = 0; i < mSMSMessage.size(); i++) {
	                sentPendingIntents.add(i, sentPI);
	                deliveredPendingIntents.add(i, deliveredPI);
	            }
	            sms.sendMultipartTextMessage(phoneNumber, null, mSMSMessage,
	                    sentPendingIntents, deliveredPendingIntents);

	        } catch (Exception e) {

	            e.printStackTrace();
	            Toast.makeText(getBaseContext(), "SMS sending failed...",Toast.LENGTH_SHORT).show();
	        }

	    }
}



