package your.tutorial.graph;


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

public class AlarmRing extends Activity implements OnClickListener{
	private Button btnAlarm;
	private Button btnStop;
	private Uri notification; 
	private MediaPlayer player;
 	private AudioManager mAudioManager;
 	private int originalVolume;
 	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.alarm);
		

	     btnStop = (Button) findViewById(R.id.btStop);
	     btnStop.setOnClickListener((OnClickListener) this);
	     //get the notification sound
	     notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
	     //make a player that will play the sound
	     player = MediaPlayer.create(getApplicationContext(), notification);
	     //get the audio system and get the original volume of the device
	     mAudioManager = (AudioManager) getSystemService(AUDIO_SERVICE);
	     originalVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	     playAlarm();
	}
	 @Override
	    public void onClick(View v) {
	        
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

	

}
