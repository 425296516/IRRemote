package com.etek.remote;




import java.util.HashMap;
import java.util.Map;


import com.etek.bean.IRKey;
import com.etek.bean.Infrared;
import com.etek.bean.Remote;
import com.etek.bean.IRKeyAdapter;
import com.etek.bean.air.AirRemoteState;
import com.etek.constant.ApplianceType;


import com.etek.db.IRDataBase;

import com.etek.ircode.AirRemoteStateMananger;
import com.etek.ircode.CallbackOnInfraredSended;
import com.etek.irremote.R;
import com.etek.ui.KeyButton;
import com.etek.utils.ETLogger;
import com.etek.utils.RemoteApplication;
import com.etek.utils.RemoteUtils;





import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import android.os.Handler;
import android.os.Looper;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;

import android.widget.TextView;


public class RemoteView extends Fragment implements CallbackOnInfraredSended
{
	private final static String TAG = "RemoteActivity";
	KeyButton test;
//	public final static String INTENT_PARAMS_REMOTE_JSON = "intent_params_remote_json";

	TextView txt_remote_name;// 遥控器名字
	TextView txtview_mode;// 模式
	TextView txtview_temp;// 温度
	TextView txtview_temp_symbol; 
	
	TextView txtview_wind_amout;// 风量

	View rlayout_air_screen;// 空调状态展示屏

//	ListView listview_remote_key;// 按键展示
	GridView gridview_remote_key;
	Remote mRemote;
	Context mContext;
	private int Index = 0;
	Handler mHandler = new Handler(Looper.getMainLooper());

	private View remoteView;
	RemoteView(int index){
		this.Index = index;	
	}
	Map<String,Infrared> airInfs = new HashMap<String,Infrared> ();
	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
	
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		
		remoteView = inflater.inflate(R.layout.remote, null);
		mContext = getActivity();
		rlayout_air_screen = remoteView.findViewById(R.id.rlayout_air_screen);

		txt_remote_name = (TextView) remoteView.findViewById(R.id.txt_remote_name);
		txtview_mode = (TextView) remoteView.findViewById(R.id.txtview_mode);
		txtview_temp = (TextView) remoteView.findViewById(R.id.txtview_temp);
		txtview_temp_symbol = (TextView) remoteView.findViewById(R.id.txtview_temp_symbol);
		txtview_wind_amout = (TextView) remoteView.findViewById(R.id.txtview_wind_amout);

		
//		listview_remote_key = (ListView) remoteView.findViewById(R.id.listview_remote_key);
		gridview_remote_key = (GridView) remoteView.findViewById(R.id.gv_remote_key);
		mRemote = IRDataBase.getRemote(Index);
//		Key key = new Key();
//		key.setType(KeyType.TWO);
//		key.setName("test");
//		test.setKeyResource(key);
		if (mRemote == null)
		{
			txt_remote_name.setText("未接收到遥控器参数");
			txt_remote_name.setTextColor(Color.RED);
		}
		else
		{
			txt_remote_name.setText(RemoteUtils.getRemoteName(mRemote));

			// 如果是空调，则现在状态屏
			if (mRemote.getType()==ApplianceType.AIR_CONDITIONER)
			{
				rlayout_air_screen.setVisibility(View.VISIBLE);
				
				showAirState();
			}
			else 
			{
				rlayout_air_screen.setVisibility(View.GONE);
			}
			// 初始化按键展示
//			KeyAdapter mKeyAdapter = new KeyAdapter(MyApplication.getAppContext(), mRemote,this);
			IRKeyAdapter rmtKeyAdapter = new IRKeyAdapter(mContext, mRemote,this);
			gridview_remote_key.setAdapter(rmtKeyAdapter);
			
//			listview_remote_key.setAdapter(mKeyAdapter);
		}
		return remoteView;
	}
	void getAirValueTable(){
		for (IRKey ir:mRemote.getKeys()){
			airInfs.put(ir.getName(), ir.getInfrareds().get(0));	
			ETLogger.debug(ir.getName()+" ___  "+ir.getInfrareds().get(0).irString());
		}
		
	}
	/** 展示或刷新空调状态屏 ,这里只示范 模式，温度及风量的状态展示和刷新，其他状态类似*/
	void showAirState()
	{
		// 获取状态
		AirRemoteState state = AirRemoteStateMananger.getInstance(RemoteApplication.getAppContext()).getAirRemoteState(mRemote.getId());
		
		Log.d(TAG, "showAirState......##........state = "+state);
		
		if (state != null)
		{
			Log.i(TAG, "showAirState......##......state.power = "+state.getPower()+" , state.mode = "+state.getMode()+" , state.temp="+state.getTemp()+" , state.wind_amount = "+state.getWind_amount());

			switch (state.getPower())
			{
			case POWER_ON:
				txtview_mode.setTextColor(Color.RED);
				txtview_temp.setTextColor(Color.BLUE);
				txtview_temp_symbol.setTextColor(Color.BLUE);
				txtview_wind_amout.setTextColor(Color.BLACK);

				break;

			default:
				txtview_mode.setTextColor(Color.GRAY);
				txtview_temp.setTextColor(Color.GRAY);
				txtview_temp_symbol.setTextColor(Color.GRAY);
				txtview_wind_amout.setTextColor(Color.GRAY);
				break;
			}
			
			switch (state.getMode())
			{
			case WIND:
				txtview_mode.setText("送风");
				txtview_temp.setVisibility(View.INVISIBLE);
				txtview_temp_symbol.setVisibility(View.INVISIBLE);

				break;

			case COOL:
				txtview_mode.setText("制冷");
				txtview_temp.setVisibility(View.VISIBLE);
				txtview_temp_symbol.setVisibility(View.VISIBLE);
				txtview_temp.setText(String.valueOf(state.getTemp().value()));
				break;

			case HOT:
				txtview_mode.setText("取暖");
				txtview_temp.setVisibility(View.VISIBLE);
				txtview_temp_symbol.setVisibility(View.VISIBLE);
				txtview_temp.setText(String.valueOf(state.getTemp().value()));

				break;

			case DRY:
				txtview_mode.setText("抽湿");
				txtview_temp.setVisibility(View.INVISIBLE);
				txtview_temp_symbol.setVisibility(View.INVISIBLE);
				break;

			default:
				txtview_mode.setText("自动");
				txtview_temp.setVisibility(View.INVISIBLE);
				txtview_temp_symbol.setVisibility(View.INVISIBLE);
				break;
			}
		}

		switch (state.getWind_amount())
		{
		case LEVEL_1:
			txtview_wind_amout.setText("风量：一档");
			break;
		case LEVEL_2:
			txtview_wind_amout.setText("风量：二档");

			break;

		case LEVEL_3:
			txtview_wind_amout.setText("风量：三档");

			break;

		case LEVEL_4:
			txtview_wind_amout.setText("风量：四档");

			break;

		default:
			txtview_wind_amout.setText("风量：自动");

			break;
		}
	}



	@Override
	public void onInfrardSended()
	{
		// TODO Auto-generated method stub
		// 如果是空调遥控器，则刷新状态屏
		if (mRemote != null && mRemote.getType()==ApplianceType.AIR_CONDITIONER)
		{
			showAirState();
		}
	}


	@Override
	public void onLongPress(int position) {
		// TODO Auto-generated method stub
		
	}

}
