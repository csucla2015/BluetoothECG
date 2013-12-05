package your.tutorial.graph;


import your.tutorial.graph.tabsadp.TabsPagerAdapter;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartScreen extends FragmentActivity implements
		ActionBar.TabListener {

	private ViewPager viewPager;
	private TabsPagerAdapter mAdapter;
	private ActionBar actionBar;
	// Tab titles
	private String[] tabs = { "Graph", "Run", "Emergency" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Initilization
		viewPager = (ViewPager) findViewById(R.id.pager);
		actionBar = getActionBar();
		mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

		viewPager.setAdapter(mAdapter);
		actionBar.setHomeButtonEnabled(false);
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);		

		// Adding Tabs
		for (String tab_name : tabs) {
			actionBar.addTab(actionBar.newTab().setText(tab_name)
					.setTabListener(this));
		}
		
		
		
		 Button submitButton = (Button) findViewById(R.id.button1);
		 if(submitButton != null){
		  submitButton.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) {
		    	  Intent i1 = new Intent(StartScreen.this, DynamicGraphActivity.class);
					
					startActivity(i1);		     
		      }
		    });
		 }
		 
		 
		  Button currentrun = (Button) findViewById(R.id.button2);
		  if(currentrun != null){
		  currentrun.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) 
		      {
		    	  //changing for nitin, now goes to devicespeed demo
		    	//  Intent i1 = new Intent(HomePage.this, CurrentBeat.class);	
		    	  Intent i1 = new Intent(StartScreen.this, DeviceSpeedDemoActivity.class);
				  startActivity(i1);			     
		      }
		    });
		  }
		 
		  Button emergencies = (Button) findViewById(R.id.button3);
		  if(emergencies != null){
		  emergencies.setOnClickListener(new OnClickListener() {
		      public void onClick(View v) 
		      {
		    	  /*Intent intent = new Intent(Intent.ACTION_SENDTO); // it's not ACTION_SEND
		    	  intent.setType("text/plain");
		    	  intent.putExtra(Intent.EXTRA_SUBJECT, "Subject of email");
		    	  intent.putExtra(Intent.EXTRA_TEXT, "Body of email");
		    	  intent.setData(Uri.parse("mailto:default@recipient.com")); // or just "mailto:" for blank
		    	  intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK); // this will make such that when user returns to your app, your app is displayed, instead of the email app.
		    	  startActivity(intent);  */
		    	  Intent i1 = new Intent(StartScreen.this, MainActivity.class);
				  startActivity(i1);		     
		      }
		    });
		  }
		  

		  

		/**
		 * on swiping the viewpager make respective tab selected
		 * */
		viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// on changing the page
				// make respected tab selected
				actionBar.setSelectedNavigationItem(position);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
			}
		});
	}

	@Override
	public void onTabReselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// on tab selected
		// show respected fragment view
		viewPager.setCurrentItem(tab.getPosition());
		  
	}

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

}

