package com.etek.main;










import java.io.IOException;
import java.util.Locale;

import com.etek.constant.Globals;
import com.etek.db.IRDataBase;
import com.etek.db.LocalDB;
import com.etek.db.UserDB;
import com.etek.irremote.R;

import com.etek.utils.ETLogger;
import com.etek.utils.Tools;


import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;


import android.view.KeyEvent;

public class StartActivity extends BaseActivity {
	static String TAG = "irremotestart";
	private final int SPLASH_DISPLAY_LENGHT = 2000;//Integer.valueOf(this.getString(R.string.splash_delay_value));

	Context mContext;
	 
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start);
        mContext = this;
        DisplayMetrics dm = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(dm);
		Globals.screenHeight= dm.widthPixels;
		Globals.screenWidth  = dm.heightPixels;
		

		Globals.tvLocation = retrieveStringFromPreff("location");
		Globals.NETCONNECT = Tools.testConnectivityManager();
		
		Globals.LocalLanguage = fetchCurrentLang();
		 createDatabase();
//		 IRDataBase.getRemoteList(mContext);
//		Log.v(TAG, "LocalLanguage ==" + Globals.LocalLanguage);
		if (retrieveIntFromPreff("initial")==1){
			Globals.INITIAL = true;
//			Log.v(TAG, "initial == 1");
		}else {
			Globals.INITIAL = false;
//			Log.v(TAG, "initial == 0");
		}
		Globals.DEVICE = Tools.getDeviceType();
//		Log.v(TAG, "device type is "+ Globals.DEVICE);
		ETLogger.init();
		
        new Handler().postDelayed(new Runnable() {
			public void run() {
				Intent mainIntent = new Intent(StartActivity.this,
						MainActivity.class);
				startActivity(mainIntent);
				finish();
			}

		}, SPLASH_DISPLAY_LENGHT);
    }
    
private void createDatabase() {
		

		UserDB mUserDB = new UserDB(mContext);
		try {
			mUserDB.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		LocalDB mLocalDB = new LocalDB(mContext);
		try {
			mLocalDB.createDataBase();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			return true;
		} else {
			return super.onKeyDown(keyCode, event);
		}
	}
	
	/**
	 * 获取版本号
	 * @return 当前应用的版本号
	 */
	public String getVersion() {
	    try {
	        PackageManager manager = this.getPackageManager();
	        PackageInfo info = manager.getPackageInfo(this.getPackageName(), 0);
	        String version = info.versionName;
//	        return this.getString(R.string.version_name) + version;
	        return "version"+ version;
	    } catch (Exception e) {
	        e.printStackTrace();
//	        return this.getString(R.string.can_not_find_version_name);
	        return "can not find this version";
	    }
	}
	
	
	private static int fetchCurrentLang()
    {
        Locale locale = Locale.getDefault();
//        Log.v(TAG, locale.toString());
        if(locale.equals(Locale.CHINA) || locale.equals(Locale.CHINESE) || locale.equals(Locale.PRC) || locale.equals(Locale.SIMPLIFIED_CHINESE)){
            return 0;
        }
        if(locale.equals(Locale.TAIWAN) || locale.equals(Locale.TRADITIONAL_CHINESE))
            return 1;
        if(locale.equals(Locale.ENGLISH) || locale.equals(Locale.US) || locale.equals(Locale.UK) || locale.equals(Locale.CANADA))
            return 2;
        if(locale.equals(Locale.CANADA_FRENCH )|| locale.equals(Locale.FRANCE) || locale.equals(Locale.FRENCH))
            return 3;
        if(locale.equals(Locale.GERMAN) || locale.equals(Locale.GERMANY))
            return 4;
        if(locale.equals(Locale.ITALIAN )|| locale.equals(Locale.ITALY))
            return 5;
        if(locale.equals(Locale.JAPAN )|| locale.equals(Locale.JAPANESE))
            return 7;
        if(locale.equals(Locale.KOREA )|| locale.equals(Locale.KOREAN))
            return 6;
        return -1;
    }

	
}
