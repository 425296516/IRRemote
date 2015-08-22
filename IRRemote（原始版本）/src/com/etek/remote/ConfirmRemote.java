package com.etek.remote;

import java.util.ArrayList;
import java.util.List;

import com.etek.bean.IRKey;
import com.etek.bean.IRKeyAdapter;
import com.etek.bean.Remote;
import com.etek.bean.air.AirMode;
import com.etek.bean.air.AirPower;
import com.etek.bean.air.AirRemoteState;
import com.etek.bean.air.AirRemoteStateDisplay;
import com.etek.bean.air.AirTempDisplay;

import com.etek.constant.ApplianceType;
import com.etek.constant.Globals;
import com.etek.db.IRDataBase;

import com.etek.ircode.AirRemoteStateMananger;
import com.etek.ircode.CallbackOnInfraredSended;
import com.etek.irremote.R;
import com.etek.ui.TitleBarView;
import com.etek.utils.ETLogger;
import com.etek.utils.RemoteApplication;


import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;


import android.os.Bundle;



import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class ConfirmRemote extends Activity implements
		OnClickListener, CallbackOnInfraredSended {
	
	static Context mContext;
	public static ConfirmRemote instance;
	

	ArrayList<String> indexs;


	private TitleBarView mTitleBarView;
	
	Button back;
	Button save;
	Button edit;
	EditText nameEdit;
	RelativeLayout airScreenRl ;
	private GridView gvRemoteKeys;
	TextView airMode,airTemp,airWindAmount;

	Remote mRemote;
	AirRemoteState airRemoteState;
	IRKeyAdapter rmtKeyAdapter;
	AirRemoteStateMananger arsm;
	List<IRKey> airKeys;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		instance = this;
		
		RemoteApplication.getInstance().addActivity(instance);
	
		createView();
	}

	 void createView() {

		setContentView(R.layout.confirm_remote);
		mRemote = Globals.mRemote;
		initKeysView();
	
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE, View.GONE,
				View.GONE);
		mTitleBarView.setTitleTextStr(mRemote.getId());
		mTitleBarView.setBtnLeft(R.string.remote);

		
		back = (Button) findViewById(R.id.btn_match_back);
		back.setOnClickListener(this);
		save = (Button) findViewById(R.id.btn_match_save);
		save.setOnClickListener(this);
		edit = (Button) findViewById(R.id.btn_match_edit);
		edit.setOnClickListener(this);
		airScreenRl = (RelativeLayout) findViewById(R.id.rlayout_air_screen);
		airMode = (TextView) findViewById(R.id.air_mode);
		airTemp = (TextView) findViewById(R.id.air_temp);
		airWindAmount = (TextView) findViewById(R.id.air_wind_amout);
		arsm = AirRemoteStateMananger.getInstance(mContext);
		if(mRemote.getType()==ApplianceType.AIR_CONDITIONER){
			airRemoteState =arsm.getAirRemoteState(mRemote.getId());
			showAirStatus(airRemoteState);
		}else  {
			airScreenRl.setVisibility(View.GONE);
		}
		
		
	}

	

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_match_back:
			finish();
			break;
		case R.id.btn_match_save:
			IRDataBase.saveRemote(mContext, mRemote);
			RemoteApplication.getInstance().quitActivity();
			finish();
			break;
		case R.id.btn_match_edit:
			initDialog();
			break;
		default:
			break;
		}
	}

	private void initKeysView() {
		
		gvRemoteKeys = (GridView) findViewById(R.id.gv_remote_key);
		rmtKeyAdapter = new IRKeyAdapter(mContext, mRemote,this);
		gvRemoteKeys.setAdapter(rmtKeyAdapter);
	}
	
	void initDialog(){
	
	  Builder dialog = new AlertDialog.Builder(this);
	   LayoutInflater inflater = (LayoutInflater) this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	   LinearLayout layout = (LinearLayout)inflater.inflate(R.layout.dialog_edit, null);
	   dialog.setView(layout);
	   nameEdit = (EditText)layout.findViewById(R.id.edit_content);
	   nameEdit.setText(mRemote.getName());
	   dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int which) {
	     String name = nameEdit.getText().toString();
	     mRemote.setName(name);
	     dialog.dismiss();
	    }
	   });
	  
	   dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
	    public void onClick(DialogInterface dialog, int which) {
	    	dialog.dismiss();
	    }
	   
	   });
	   dialog.show();
	
	}
	
	void showAirStatus(AirRemoteState airRemoteState){
		if(airRemoteState.getPower()==AirPower.POWER_OFF){
			airMode.setText(R.string.power_off);
			airTemp.setText("");
			airWindAmount.setText("");
		}else {
			ETLogger.debug("REMOTETEST","temp ="  +airRemoteState.getTemp());
			airMode.setText(AirRemoteStateDisplay.getModeStrId(airRemoteState));
			airTemp.setText("" +AirRemoteStateDisplay.getTempStr(airRemoteState));
			airWindAmount.setText(AirRemoteStateDisplay.getWindStrId(airRemoteState));
		}
		
	}
	
	

	@Override
	public void onLongPress(int position) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onInfrardSended() {
		// TODO Auto-generated method stub
		airRemoteState = arsm.getAirRemoteState(mRemote.getId());
//		Logger.debug("REMOTETEST","airRemoteState = "+airRemoteState.getPower()+"_"+airRemoteState.getRemote_id()+"_"+airRemoteState.getTemp()+"_"+airRemoteState.getTemp_display());
		showAirStatus( airRemoteState);
	}
	
}
