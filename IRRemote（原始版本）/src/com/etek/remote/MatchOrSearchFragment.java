package com.etek.remote;

import com.etek.constant.Globals;
import com.etek.irremote.R;

import com.etek.ui.TitleBarView;
import com.etek.utils.Tools;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MatchOrSearchFragment extends Fragment {
	
	static Context mContext;
	private RelativeLayout remoteMatchSetups;
	private RelativeLayout remoteSearchSetups;
	private TitleBarView mTitleBarView;
	private View mBaseView;
	
	@SuppressLint("InflateParams") @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContext = getActivity();
		mBaseView = inflater.inflate(R.layout.match_search, null);

		// mSearchView = inflater.inflate(R.layout.common_search_l, null);
		initUI();

		return mBaseView;
	}
	private void initUI() {

		mTitleBarView = (TitleBarView) mBaseView.findViewById(R.id.title_bar);
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE, View.GONE,
				View.GONE);
		mTitleBarView.setTitleText(R.string.type_desc);
		mTitleBarView.setTitleText(R.string.device_type);
		

		remoteMatchSetups = (RelativeLayout) mBaseView.findViewById(R.id.settings_match_remote);

		remoteSearchSetups = (RelativeLayout) mBaseView.findViewById(R.id.settings_search_remote);

		

		remoteMatchSetups.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Globals.NETCONNECT = Tools.testConnectivityManager();
				
				if (Globals.NETCONNECT){
					Toast.makeText(getActivity(),
							"net is  working ", Toast.LENGTH_LONG).show();
				}else {
					Toast.makeText(getActivity(),
							"net is not working ", Toast.LENGTH_LONG).show();
				}
								
					Intent intent = new Intent(getActivity(),
							DeviceTypeActivity.class);
					startActivity(intent);
			

			}
		});

		remoteSearchSetups.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Globals.NETCONNECT = Tools.testConnectivityManager();
				
				if (Globals.NETCONNECT){
					Toast.makeText(getActivity(),
							"net is  working ", Toast.LENGTH_LONG).show();
					Intent intent = new Intent(getActivity(),
							SearchRemote.class);
					startActivity(intent);
				}else {
					Toast.makeText(getActivity(),
							"net is not working ", Toast.LENGTH_LONG).show();
				}
				

			}
		});

	}
	
}
