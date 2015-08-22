package com.etek.setups;


import com.etek.constant.Globals;
import com.etek.irremote.R;

import com.etek.ui.TitleBarView;
import com.etek.ui.UISwitchButton;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.CompoundButton.OnCheckedChangeListener;

public class SettingsFragment extends Fragment {

	private Context mContext;
	private View mBaseView;
	private TitleBarView mTitleBarView;
	private UISwitchButton switchVir;
	private RelativeLayout locationSetups;
	private RelativeLayout deviceSettings;

	private TextView deviceDesc;
	private TextView locationDesc;

	String TAG = "Setups";
	private RelativeLayout tvinfoListShow;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mBaseView = inflater.inflate(R.layout.settings, null);
		getSharedpreff();
		findView();
		init();

		return mBaseView;
	}

	private void findView() {
		mTitleBarView = (TitleBarView) mBaseView.findViewById(R.id.title_bar);
		switchVir = (UISwitchButton) mBaseView
				.findViewById(R.id.switch_vibrate);
		locationSetups = (RelativeLayout) mBaseView
				.findViewById(R.id.location_setting);
		deviceSettings = (RelativeLayout) mBaseView
				.findViewById(R.id.terminal_selection);
		deviceDesc = (TextView) mBaseView.findViewById(R.id.terminal_desc);
		locationDesc = (TextView) mBaseView.findViewById(R.id.location_desc);
		
		tvinfoListShow  = (RelativeLayout) mBaseView
				.findViewById(R.id.tv_info_show);
		

	}

	private void init() {
		mTitleBarView.setCommonTitle(View.GONE, View.VISIBLE, View.GONE,
				View.GONE);
		mTitleBarView.setTitleText(R.string.settings);
		switchVir.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				if (isChecked) {
					Log.v("test", "virbrate is on");
				} else {
					Log.v("test", "virbrate is off");
				}
			}
		});

		locationSetups.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				Intent intent = new Intent(mContext,
//						SelectLocationActivity.class);
//				startActivityForResult(intent, Globals.ID_INFORLOCATION);
			}
		});
		locationSetups.setVisibility(View.GONE);

		
		tvinfoListShow.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				if(Tools.testConnectivityManager()){
//				Intent intent = new Intent(mContext, TVInfoActivity.class);
//				startActivity(intent);
//				}else {
//					Toast.makeText(mContext, R.string.no_net_error, Toast.LENGTH_SHORT).show();
//				}
//				
//				
			}
		});
		tvinfoListShow.setVisibility(View.GONE);
		Log.v(TAG, "device is "+Globals.DEVICE );
		String tmrStr = this.getResources().getStringArray(
				R.array.device_array)[Globals.DEVICE];
		deviceDesc.setText(tmrStr);
//		locationDesc.setText(Globals.tvLocation);
	}

	public void getSharedpreff() {
		Globals.tvLocation = mContext.getSharedPreferences(
				Globals.REMOTE_SHARED_PREFF, 0).getString("location", "");
//		Globals.DEVICE = mContext.getSharedPreferences(
//				Globals.REMOTE_SHARED_PREFF, 0).getInt("terminal", 0);
		Globals.proOrCh = mContext.getSharedPreferences(
				Globals.REMOTE_SHARED_PREFF, 0).getBoolean("proorch", false);

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case (Globals.ID_TERMINAL): 
			if (resultCode == Activity.RESULT_OK) {
				String tmrStr = this.getResources().getStringArray(
						R.array.device_array)[Globals.DEVICE];
				deviceDesc.setText(tmrStr);
//				Globals.commandManager = new CommandManager(mContext);
			}
			break;
		case (Globals.ID_INFORLOCATION): 
			if (resultCode == Activity.RESULT_OK) {
				locationDesc.setText(Globals.tvLocation);
			}
			break;
		

		}
	}
	
}
