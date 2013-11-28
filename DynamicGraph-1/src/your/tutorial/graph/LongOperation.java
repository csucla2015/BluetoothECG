package your.tutorial.graph;

import android.os.AsyncTask;
import android.util.Log;

public class LongOperation extends AsyncTask<Void, Void, String> {
	@Override
	protected String doInBackground(Void... params) {
		try{GMailSender sender = new GMailSender("meetbhagdev@gmail.com","catalinaisland12");
		   sender.sendMail("subject",   
	                "body",   
	                "meetbhagdev@gmail.com",   
	                "meetbhagdev@ucla.edu");
			}
		catch(Exception e){Log.e("error",e.getMessage(),e);return "Email Not Sent";}
		return "Email Sent";
	}

	@Override
	protected void onPostExecute(String result) 
	{
	}
	@Override
	protected void onPreExecute() 
	{
	}
	
	@Override
	protected void onProgressUpdate(Void... values) 
	{
	}
}