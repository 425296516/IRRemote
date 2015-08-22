package com.etek.main;



import com.etek.constant.Globals;
import com.etek.irremote.R;

import android.os.Bundle;

import android.content.SharedPreferences;

import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.Menu;

public class BaseActivity extends FragmentActivity {

	public LayoutInflater inflater = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/**
	 * @param set
	 *            or get SharedPreferences
	 * @param index
	 *            data
	 */
	public void putIntToPreff(String index, int data) {
		SharedPreferences.Editor localEditor = getSharedPreferences(
				Globals.REMOTE_SHARED_PREFF, 0).edit();
		localEditor.putInt(index, data);
		localEditor.commit();
	}

	public void putStringToPreff(String index, String data) {
		SharedPreferences.Editor localEditor = getSharedPreferences(
				Globals.REMOTE_SHARED_PREFF, 0).edit();
		localEditor.putString(index, data);
		localEditor.commit();
	}

	public int retrieveIntFromPreff(String index) {
		return getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getInt(
				index, 0);
	}

	public String retrieveStringFromPreff(String index) {
		return getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getString(
				index, "");
	}

	public String retrieveStringFromPreff(String index, String data) {
		return getSharedPreferences(Globals.REMOTE_SHARED_PREFF, 0).getString(
				index, data);
	}

}
