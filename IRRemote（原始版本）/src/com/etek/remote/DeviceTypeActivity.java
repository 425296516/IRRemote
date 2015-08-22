package com.etek.remote;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import com.etek.bean.Brand;
//
//import com.etek.bean.Brand;
import com.etek.bean.Remote;
import com.etek.bean.RemoteProduct;
import com.etek.constant.ApplianceType;
import com.etek.constant.Globals;
import com.etek.db.LocalDB;

import com.etek.http.HttpRequest;
import com.etek.irremote.R;
import com.etek.main.BaseActivity;

import com.etek.ui.TitleBarView;

import com.etek.utils.RemoteApplication;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

import android.widget.SimpleAdapter;

public class DeviceTypeActivity extends BaseActivity implements
		OnItemClickListener {
	protected static final int GET_BRAND_OK = 0;
	String TAG = "DeviceTypeActivity";

	GridView gvBrandGV;
	ArrayList<RemoteProduct> brandLists;

	public int devId;
	public static DeviceTypeActivity instance;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		instance = this;
		RemoteApplication.getInstance().addActivity(instance);
		setContentView(R.layout.select_type);
		initData();
		initUI();

	}

	private Handler handler = new Handler() {

		// Intent intent = new Intent();

		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case GET_BRAND_OK:
				Globals.MBrands = (ArrayList<Brand>) msg.obj;
				// myApplication.setBrands((List<Brand>) msg.obj);
				Intent i = new Intent(DeviceTypeActivity.this,
						BrandListActivity.class);
				startActivity(i);
				break;

			default:
				break;
			}
		}
	};
	private TitleBarView mTitleBarView;

	private void initUI() {

		gvBrandGV = (GridView) findViewById(R.id.tv_brand_grid);
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		mTitleBarView.setCommonTitle(View.GONE, View.VISIBLE, View.GONE,
				View.GONE);
		mTitleBarView.setTitleText(R.string.device_type);

		ArrayList<HashMap<String, Object>> al = new ArrayList<HashMap<String, Object>>();

		for (int i = 0; i < typeLists.size(); i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			int type = typeLists.get(i).getType();
			int strid = Globals.getTypeStrID(type);
			String typeStr = getResources().getString(strid);
			// Globals.deviceID = strid;
			// Logger.debug(""+strid);
			// Logger.debug(""+typeStr);
			int typeImg = Globals.getImgID(type);
			// String desc = "all " + getBrandCount(type) + " brand";

			map.put("name", typeStr);
			map.put("image", typeImg);
			map.put("desc", "");

			al.add(map);
		}

		SimpleAdapter sa = new SimpleAdapter(this, al,
				R.layout.select_type_item, new String[] { "name", "image",
						"desc" }, new int[] { R.id.type_name, R.id.type_image,
						R.id.type_desc });
		gvBrandGV.setAdapter(sa);
		gvBrandGV.setOnItemClickListener(this);

	}

	final int[] DEVTAB = { ApplianceType.TV, ApplianceType.AIR_CONDITIONER,
			ApplianceType.STB, ApplianceType.DVD, ApplianceType.IPTV,
			ApplianceType.CAMERA, ApplianceType.GAME, ApplianceType.LIGHT,
			ApplianceType.AMPLIFIER };

	private ArrayList<Remote> typeLists;
	public LocalDB mRmtDB;

	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		devId = DEVTAB[position];
		Globals.deviceID = devId;
		Log.d(TAG, "id = " + devId);
		Thread thread = new Thread(new GetBrandRunnable(devId ));
		thread.start();
		// Intent i = new Intent(DeviceTypeActivity.this,
		// LocalBrandListActivity.class);
		// startActivity(i);
	}

	@Override
	public void onStart() {
		super.onStart();

	}

	private void initData() {
		if (Globals.NETCONNECT) {
			typeLists = new ArrayList<Remote>();
			Remote rmt = new Remote();
			rmt.setType(ApplianceType.TV);
			typeLists.add(rmt);
			rmt = new Remote();
			rmt.setType(ApplianceType.AIR_CONDITIONER);
			typeLists.add(rmt);
			rmt = new Remote();
			rmt.setType(ApplianceType.STB);
			typeLists.add(rmt);
			rmt = new Remote();
			rmt.setType(ApplianceType.DVD);
			typeLists.add(rmt);
			rmt = new Remote();
			rmt.setType(ApplianceType.IPTV);
			typeLists.add(rmt);
			rmt = new Remote();
			rmt.setType(ApplianceType.CAMERA);
			typeLists.add(rmt);
			// rmt = new Remote();
			// rmt.setType(ApplianceType.GAME);
			// typeLists.add(rmt);
			// rmt = new Remote();
			// rmt.setType(ApplianceType.PROJECTOR);
			// typeLists.add(rmt);
			// rmt = new Remote();
			// rmt.setType(ApplianceType.LIGHT);
			// typeLists.add(rmt);
			// rmt = new Remote();
			// rmt.setType(ApplianceType.AMPLIFIER);
			// typeLists.add(rmt);
		} else {
			typeLists = new ArrayList<Remote>();
			Remote rmt = new Remote();
			rmt.setType(ApplianceType.TV);
			typeLists.add(rmt);
			rmt = new Remote();
			rmt.setType(ApplianceType.AIR_CONDITIONER);
			typeLists.add(rmt);
			rmt = new Remote();
			rmt.setType(ApplianceType.STB);
			typeLists.add(rmt);
		}
	}

	class GetBrandRunnable implements Runnable {

		private Integer idDevice;
		private ArrayList<String> list = new ArrayList<String>();
		private ArrayList<String> nameList = new ArrayList<String>();
		GetBrandRunnable(Integer idDevice) {
			this.idDevice = idDevice;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub

			if (Globals.NETCONNECT) {

				List<Brand> brands = new ArrayList<Brand>();
				Gson gson = new Gson();

				Object[][] comBrands = gson.fromJson(
						HttpRequest.sendGet(Globals.GETSERVERBRAND 
								+ idDevice.toString()),
						new TypeToken<Object[][]>() {
						}.getType());

				for (Object[] object : comBrands) {
					Brand brand = new Brand();
					brand.setId(Double.valueOf((Double) object[0]).intValue());
					brand.setBrand((String) object[1]);
					if (Globals.LocalLanguage == 0) {
						brand.setBrand_tra((String) object[3]);
					} else {
						brand.setBrand_tra((String) object[5]);
					}
					brand.setSortLetters((String) object[1]);
					brands.add(brand);
				}

				Message message = new Message();
				message.what = GET_BRAND_OK;
				message.obj = brands;
				handler.sendMessage(message);
			}else {
				mRmtDB = new LocalDB(getApplicationContext());
				mRmtDB.open();
				String mTypeName = Globals.getTypeStr(idDevice-1);
				list = mRmtDB.getBrand(mTypeName);
				
				
				nameList = mRmtDB.translateBrands(list);
				mRmtDB.close();
				List<Brand> brands = new ArrayList<Brand>();

				for (int i=0;i<list.size();i++) {
					Brand brand = new Brand();
					brand.setId(i);
					brand.setBrand(list.get(i));
					brand.setBrand_tra(nameList.get(i));
					
					brand.setSortLetters(list.get(i));
					brands.add(brand);
				}
				Message message = new Message();
				message.what = GET_BRAND_OK;
				message.obj = brands;
				handler.sendMessage(message);
				
			}
		}

	}
}
