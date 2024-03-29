package com.bb.view.android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
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
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	
	

	/**
	 * Set up all the pre configured layout buttons and actions
	 */
	private void initializeControls() {
		
		//Set up start_lobby_button
		Button createLobbyButton = (Button) findViewById(R.id.create_lobby_button);
		createLobbyButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				startLobbyAction();
			}
		});
		
		//Set up find_lobby_button
		Button findLobbyButton = (Button) findViewById(R.id.find_lobby_button);
		findLobbyButton.setOnClickListener(new Button.OnClickListener(){
			@Override
			public void onClick(View v) {
				findLobbyAction();
			}
		});
	}

	protected void startLobbyAction() {
//		// Start up the next activity
//		Intent intent = new Intent(getApplication(), StartLobbyActivity.class);		
//		startActivity(intent);
	}

	protected void findLobbyAction() {
//		// Start up the next activity
//		Intent intent = new Intent(getApplication(), StartLobbyActivity.class);		
//		startActivity(intent);
	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onStop() {
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		// clientResourceObject.killClient();
		super.onDestroy();
		finish();
	}

	@Override
	public void onBackPressed() {
		// // do something on back.
		// System.out.println("DEBUG InitialMenuActivity~ Back button pressed, killingClient, finish()");
		// clientResourceObject.killClient();
		finish();
	}
}
