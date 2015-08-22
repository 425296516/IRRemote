package com.etek.service;


import java.util.List;

import com.etek.bean.Brand;
import com.etek.constant.Globals;
import com.etek.db.UserDB;


import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.util.Log;

public class InitService extends Service  {
		private static final String TAG = "InitService";
		private Handler mHandler = new Handler(Looper.getMainLooper());
		UserDB user;
	

		@Override
		public IBinder onBind(Intent arg0) {
//			Log.v(TAG, "InitService onBind");
//			if (Globals.INITIAL==false){
//			Log.v(TAG, "start update database");
//			user = new UserDB(getApplicationContext());
//			IRClient.load_brands(this);
//			}
			return null;
		}

		@Override
		public void onCreate() {
			
		}

		@Override
		public void onDestroy() {
			Log.v(TAG, "onDestroy");
			
		}

		@Override
		public void onStart(Intent intent, int startId) {
			Log.v(TAG, "onStart");
		
		}

		

		public void putIntToPreff(String index, int data) {
			SharedPreferences.Editor localEditor = getSharedPreferences(
					Globals.REMOTE_SHARED_PREFF, 0).edit();
			localEditor.putInt(index, data);
			localEditor.commit();
		}
		
	}