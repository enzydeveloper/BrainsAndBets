package com.bb.view.android;


import com.brainsandbets.R;

import android.app.Activity;
import android.os.Bundle;

public class StartLobbyActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start_lobby);

		// if (savedInstanceState == null) {
		// getFragmentManager().beginTransaction()
		// .add(R.id.container, new PlaceholderFragment()).commit();
		// }

		initializeControls();
	}

	/**
	 * Set up all the pre configured layout buttons and actions
	 */
	private void initializeControls() {
		
//		//Set up start_lobby_button
//		Button startLobbyButton = (Button) findViewById(R.id.start_lobby_button);
//		startLobbyButton.setOnClickListener(new Button.OnClickListener(){
//			@Override
//			public void onClick(View v) {
//				startLobbyAction();
//			}
//		});
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
