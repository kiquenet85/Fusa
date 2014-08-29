package com.example.zonamigosfusa.otro;


import android.annotation.TargetApi;
import android.app.ActionBar;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.zonamigosfusa.R;

public class SecondActivity extends ActionBarActivity {
	
	private String tag = "FusaEnterpriseLog";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_second);
		actionBar();
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.second, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			callPreferences();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void callPreferences() {
		Intent intent=new Intent(this,SettingsActivity.class);
        startActivity(intent);
	}

		// Called before subsequent visible lifetimes
		// for an Activity process.
		@Override
		public void onRestart() {
			super.onRestart();
			// Load changes knowing that the Activity has already
			// been visible within this process.
			Log.d(tag, "In the onRestart() "+this.getClass().getSimpleName()+" event");
		}

		// Called at the start of the visible lifetime.
		@Override
		public void onStart() {
			super.onStart();
			// Apply any required UI change now that the Activity is visible.
			Log.d(tag, "In the onStart() "+this.getClass().getSimpleName()+" event");
		}

		// Called at the start of the active lifetime.
		@Override
		public void onResume() {
			super.onResume();
			// Resume any paused UI updates, threads, or processes required
			// by the Activity but suspended when it was inactive.
			Log.d(tag, "In the onResume() "+this.getClass().getSimpleName()+" event");
		}

		// Called at the end of the active lifetime.
		@Override
		public void onPause() {
			// Suspend UI updates, threads, or CPU intensive processes
			// that don’t need to be updated when the Activity isn’t
			// the active foreground Activity.
			super.onPause();
			Log.d(tag, "In the onPause() "+this.getClass().getSimpleName()+" event");
		}

		// Called at the end of the visible lifetime.
		@Override
		public void onStop() {
			// Suspend remaining UI updates, threads, or processing
			// that aren’t required when the Activity isn’t visible.
			// Persist all edits or state changes
			// as after this call the process is likely to be killed.
			super.onStop();
			Log.d(tag, "In the onStop() "+this.getClass().getSimpleName()+" event");
		}

		// Sometimes called at the end of the full lifetime.
		@Override
		public void onDestroy() {
			// Clean up any resources including ending threads,
			// closing database connections etc.
			super.onDestroy();
			Log.d(tag, "In the onDestroy() "+this.getClass().getSimpleName()+" event");
		}

		@TargetApi(Build.VERSION_CODES.HONEYCOMB)
		public void actionBar() {
			
			if (Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB){
				ActionBar actionBar;
			    actionBar = getActionBar();
				actionBar.setDisplayHomeAsUpEnabled(true);
				// actionBar.setDisplayShowHomeEnabled(true);
				// actionBar.hide();
				// actionBar.show(); //---show it again---	
			}else{
				android.support.v7.app.ActionBar actionBar;
				actionBar=getSupportActionBar();
				actionBar.setDisplayHomeAsUpEnabled(true);
				// actionBar.setDisplayShowHomeEnabled(true);
				// actionBar.hide();
				// actionBar.show(); //---show it again---	
			}
		}


	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_second,
					container, false);
			return rootView;
		}
	}
}
