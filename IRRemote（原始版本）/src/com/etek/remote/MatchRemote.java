package com.etek.remote;

import java.util.ArrayList;

import java.util.List;

import com.etek.bean.IRKeyAdapter;
import com.etek.bean.Infrared;

import com.etek.bean.IRKey;


import com.etek.bean.Remote;

import com.etek.constant.ApplianceType;
import com.etek.constant.Globals;
import com.etek.db.IRDataBase;


import com.etek.http.HttpRequest;
import com.etek.ircode.CallbackOnInfraredSended;
import com.etek.ircode.IInfraredSender;
import com.etek.ircode.impl.InfraredSender;
import com.etek.irremote.R;


import com.etek.ui.TitleBarView;

import com.etek.utils.ETLogger;
import com.etek.utils.RemoteApplication;
import com.etek.utils.RemoteUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.ircode.IRCode;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.GridView;

import android.widget.TextView;



@SuppressLint("HandlerLeak") public class MatchRemote extends Activity implements OnClickListener,
		CallbackOnInfraredSended {
	private final static String TAG = "MatchRemote";

	static Context mContext;
	public static MatchRemote instance;

	private static String mBrand;

	Remote mRemote;

	private int maxIndex = 0;
	int modelIndex = 0;
	Boolean is_net_air;
	int airType = 0;
	int idModelSearch = 0;
	IInfraredSender mSeneder;

	public final int GET_IR_KEY_OK = 100;
	public final int GET_IR_KEY_FAIL = 101;

	private TitleBarView mTitleBarView;
	private Button next, test;
	private TextView indexShow;
	private GridView gvRemoteKeys;
	private IRKeyAdapter rmtKeyAdapter;

	// KeyAdapter rmtKeyAdapter;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		instance = this;
		RemoteApplication.getInstance().addActivity(instance);
		initData();
		createView();
		mSeneder = new InfraredSender();
	}

	public void createView() {

		setContentView(R.layout.match_remote);
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE, View.GONE,
				View.GONE);
		mTitleBarView.setTitleTextStr(mBrand);
		mTitleBarView.setBtnLeft(R.string.brand);
		mTitleBarView.setBtnLeftOnclickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});

		indexShow = (TextView) findViewById(R.id.tv_show_index);

		next = (Button) findViewById(R.id.btn_match_next);
		next.setOnClickListener(this);
		test = (Button) findViewById(R.id.btn_match_test);
		test.setOnClickListener(this);
		test.setText(R.string.test);

		gvRemoteKeys = (GridView) findViewById(R.id.gv_remote_key);

		setIndexShow();

		initKeysView();
	}

	public void initData() {
		maxIndex = Globals.modelSearchs.size();

		if (Globals.deviceID == ApplianceType.AIR_CONDITIONER
				) {
			if(Globals.NETCONNECT == false){
				airType = 1;
			}else {
				airType = 2;
			}
		} else {
		airType = 0;
		}

		mBrand = Globals.MBrand.getBrand();

		getDBRemote();

	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.btn_match_test:
			Thread thread = new Thread(new GetKeysRunnable(idModelSearch));
			thread.start();
		
			break;
		case R.id.btn_match_next:
			reInitViewPager();
			break;

		default:
			break;
		}
	}

	private void reInitViewPager() {
		modelIndex++;
		if (modelIndex >= maxIndex) {
			Intent i = new Intent(MatchRemote.this, WarningPager.class);
			startActivity(i);
		} else {

			setIndexShow();
			getDBRemote();
			initKeysView();

		}

	}

	void getDBRemote() {
		if (Globals.NETCONNECT) {
			mRemote = new Remote();
			mRemote.setBrand(Globals.MBrand);
			mRemote.setKeys(Globals.modelSearchs.get(modelIndex).getKeys());
			idModelSearch = Globals.modelSearchs.get(modelIndex).getId();
			mRemote.setId(Globals.MBrand.getBrand() + "_" + idModelSearch);
			mRemote.setModel(Globals.MBrand.getBrand() + "_" + idModelSearch);
			

		} else {
			int modelNumIndex = Globals.modelSearchs.get(modelIndex).getId();
			mRemote = getDBRemote(mContext,
					Globals.getDBType(Globals.deviceID), modelNumIndex);
			Message message = new Message();
			message.what = GET_IR_KEY_OK;
			message.obj = mRemote.getKeys();
			handler.sendMessage(message);
		}
	}

	private void setIndexShow() {
		int indextxt = modelIndex + 1;
		String str = getResources().getString(R.string.count_text) + " ( "
				+ indextxt + " / " + maxIndex + " )";
		Log.v(TAG, str);

		indexShow.setText(str);

	}

	private void initKeysView() {

		rmtKeyAdapter = new IRKeyAdapter(mContext, mRemote, this);
		gvRemoteKeys.setAdapter(rmtKeyAdapter);
	}

	public static Remote getDBRemote(Context mContext, int type, int index) {
		Remote newRemote = new Remote();
		if (type != 5) {
			// Log.v(TAG, "key type is "+type);
			List<IRKey> keys = IRDataBase.getDBkeys(mContext, type, index);
			// for (Key k : keys){
			// Log.v(TAG, "key name is "+k.getName());
			// }
			newRemote.setKeys(keys);
			String idStr = Globals.getTypeStr(Globals.deviceID) + "_" + index;
			String name = Globals.getTypeStr(Globals.deviceID) + "_"
					+ Globals.MBrand.getBrand() + "_" + index;
			String model = Globals.MBrand.getBrand() + "_" + index;
			newRemote.setId(idStr);
			newRemote.setType(Globals.deviceID);
			newRemote.setName(name);
			newRemote.setModel(model);
			newRemote.setBrand(Globals.MBrand);
			return newRemote;
		} else {

			List<IRKey> keys = IRDataBase.getAirkeys(mContext,  index);
			newRemote.setKeys(keys);
			String idStr = Globals.getTypeStr(Globals.deviceID) + "_" + index;
			String name = Globals.getTypeStr(Globals.deviceID) + "_"
					+ Globals.MBrand.getBrand() + "_" + index;
			String model = Globals.MBrand.getBrand() + "_" + index;
			newRemote.setId(idStr);
			newRemote.setType(Globals.deviceID);
			newRemote.setName(name);
			newRemote.setModel(model);
			newRemote.setBrand(Globals.MBrand);
			return newRemote;
		}
	}

	class GetKeysRunnable implements Runnable {

		private Integer idModelSearch;

		GetKeysRunnable(Integer idModelSearch) {
			this.idModelSearch = idModelSearch;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			mRemote = new Remote();
			List<IRKey> irKeys = new ArrayList<IRKey>();
			Gson gson = new Gson();
			
			String remoteKeyId = Globals.GETSERVERREMOTEKEY
					+ idModelSearch.toString();
			ETLogger.debug("idModelSearch="+idModelSearch+" remoteKeyId="+remoteKeyId);
//			String  date = HttpRequest.sendGet(remoteKeyId);
//			Logger.debug(date);
			Object[][] comIrKeys = gson.fromJson(
					HttpRequest.sendGet(remoteKeyId),
					new TypeToken<Object[][]>() {
					}.getType());

			for (Object[] object : comIrKeys) {
				IRKey irKey = new IRKey();
				irKey.setName((String) object[0]);
//				ETLogger.debug((String) object[1]);
				List<Infrared> infs = new ArrayList<Infrared>();
				IRCode ir = new IRCode((String) object[1]);
				Infrared inf = new Infrared(ir);
			
				infs.add(inf);
				//Log.d("MatchRemote","setName="+object[0]+"IRCode1="+object[1]+"IRCode2="+object[2]);
				ir = new IRCode((String) object[2]);
				inf = new Infrared(ir);
				infs.add(inf);
				irKey.setProtocol(0);
				irKey.setInfrareds(infs);
				// irKey.setDescription((String) object[5]);
				irKeys.add(irKey);
			}
			if (irKeys != null && irKeys.size() > 0) {

				Message message = new Message();
				message.what = GET_IR_KEY_OK;
				message.obj = irKeys;
				handler.sendMessage(message);
			} else {
				Message message = new Message();
				message.what = GET_IR_KEY_FAIL;
				message.obj = irKeys;
				handler.sendMessage(message);
			}
		}

	}

	private Handler handler = new Handler() {

		@SuppressWarnings("unchecked")
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case GET_IR_KEY_OK:
				List<IRKey> keys = (List<IRKey>) msg.obj;
				if(Globals.deviceID==ApplianceType.AIR_CONDITIONER&& isAirDiy(keys)){
					mRemote = new Remote();
					mRemote.setBrand(Globals.MBrand);
				
					mRemote.setId(Globals.MBrand.getBrand() + "_" + idModelSearch);
					mRemote.setModel(Globals.MBrand.getBrand() + "_" + idModelSearch);
					mRemote.setType(Globals.deviceID);	
					mRemote.setAir_keys(keys);
					mRemote.setKeys(IRDataBase.getAirkeys(mContext,  0));
				}else {
					mRemote = new Remote();
					mRemote.setBrand(Globals.MBrand);
				
					mRemote.setId(Globals.MBrand.getBrand() + "_" + idModelSearch);
					mRemote.setModel(Globals.MBrand.getBrand() + "_" + idModelSearch);
					mRemote.setType(Globals.deviceID);	
					mRemote.setKeys(keys);
				}
				
				ETLogger.debug("airtype ="+airType);
				if(RemoteUtils.isDiyAirRemote(mRemote)){
					mRemote.setAir_keys((List<IRKey>) msg.obj);
					mRemote.setKeys(IRDataBase.getAirkeys(mContext,  0));
				}
				
				
				
				Globals.mRemote = mRemote;
				
				Intent i = new Intent(MatchRemote.this, ConfirmRemote.class);
				startActivity(i);
				break;
			
			default:
				break;
			}
		}
	};

	
	boolean isAirDiy(List<IRKey> keys ){
		for (IRKey key : keys )
		{
			if (key != null && key.getName().equalsIgnoreCase("poweroff"))
			{
				ETLogger.debug("is diy air");
				return true;
			}
		}
		return false;
	}
	@Override
	public void onInfrardSended() {
		// TODO Auto-generated method stub

	}

	@Override
	public void onLongPress(int position) {
		// TODO Auto-generated method stub

	}

}
