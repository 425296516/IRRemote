package com.etek.learn;

import java.util.ArrayList;

import com.etek.bean.Infrared;
import com.etek.bean.IRKey;

import com.etek.bean.KeyColumn;
import com.etek.bean.KeySampleAdapter;
import com.etek.bean.air.AirRemoteState;
import com.etek.constant.DeviceType;
import com.etek.constant.Globals;
import com.etek.db.LocalDB;

import com.etek.ircode.IInfraredDevice;


import com.etek.ircode.impl.DummyIRDevice;
import com.etek.ircode.impl.ET4003IRDevice;
import com.etek.ircode.impl.ET4007IRDevice;


import com.etek.irremote.R;

import com.etek.ui.HorizontalListView;
import com.etek.ui.TitleBarView;

import com.etek.utils.ETLogger;


import com.ircode.IRCode;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;



import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;


import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;


import android.widget.AdapterView;

import android.widget.Button;


import android.widget.EditText;
import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemClickListener;


@SuppressLint({ "HandlerLeak", "InflateParams" })
public class LearnButtonEdit extends Activity implements OnClickListener {

	String TAG = "LearnFuncation";
	Context mContext;

	int init = 0;
	private LayoutInflater inflater;

	TextView btType;
//	Button btNext;
	Button btSave;
//	Button btProv;
	Button btSender;
	private Button btLearn;
	TextView learnData;
	private EditText nameEdit;
	private TextView keyName;
	LinearLayout keyNameEdit;
	int allIndex = 0;
	int curIndex = 0;
	TextView tvIndex;
//	private LinearLayout layout;

	// private ListView remoteListview;
	// IInfraredLearner mLearner;
	TitleBarView mTitleBarView;
	Dialog dialog;
	IInfraredDevice mDevice ;
	private KeySampleAdapter listAdapt;
	// private PopupWindow popupWindow;
	private Handler mMainHandler, mLearnHandler;
	LearnLoop learnLoop;
	HorizontalListView hListView;
	ArrayList<IRKey> keyLists;
	ArrayList<IRKey> sampleKeyLists;
	// String[] btListsStrings;
	IRKey lKey = new IRKey();
	protected boolean isLearning;

	final static int MAXINDX = 200;
	IRCode ircode = new IRCode();
	boolean add;
	AirRemoteState airState;


	protected void onCreate(Bundle paramBundle) {
		super.onCreate(paramBundle);
		setContentView(R.layout.learn_irkey);
		mContext = this;
		getDeviceType();
		add = getIntent().getBooleanExtra("mode", true);
		curIndex = getIntent().getIntExtra("index", 0);
		ETLogger.debug("curIndex " + curIndex);
		keyLists = (ArrayList<IRKey>) Globals.mRemote.getKeys();

		
		sampleKeyLists = new ArrayList<IRKey>();
		sampleKeyLists.clear();
		
		
		LocalDB lrdb = new LocalDB(mContext);
		lrdb.open();
		ArrayList<KeyColumn> keyColumns = lrdb.getKeyArray();

		for (int i = 0; i < keyColumns.size(); i++) {
			IRKey key = new IRKey();
			key.setId(i);
			key.setType(keyColumns.get(i).getType());
			String name = keyColumns.get(i).getName();
			name = lrdb.strTranslator(name);
			key.setName(name);
			sampleKeyLists.add(key);
			// Logger.debug(key.getName()+"_"+key.getType()+"_"+key.getId());
		}
		lrdb.close();
		if (keyLists != null) {
			allIndex = keyLists.size();

		} else {
			keyLists = new ArrayList<IRKey>();
			allIndex = 0;
		}
		if (add) {
			allIndex += 1;
			curIndex = allIndex;
			lKey.setType(sampleKeyLists.get(0).getType());
			lKey.setName(sampleKeyLists.get(0).getName());
			lKey.setId(allIndex);

		} else {

			lKey = keyLists.get(curIndex);
			curIndex += 1;
		}
		initButtonType();

		learnLoop = new LearnLoop();

		mMainHandler = new Handler() {

			@SuppressLint("HandlerLeak")
			@Override
			public void handleMessage(Message msg) {
				int index = msg.getData().getInt("index");
				if (msg.getData().getBoolean("state")) {

					Message m = new Message();
					Bundle bundle = new Bundle();
					bundle.putInt("learn", 1);
					m.setData(bundle);
					mLearnHandler.sendMessage(m);
					// Logger.debug(" button learning ");
				} else {
					ETLogger.debug("finish button learn");
					isLearning = false;
					if (index < MAXINDX) {
						ircode = mDevice.irCodeReceiver();
						ETLogger.debug(ircode.toString());
						if (ircode.isValid()) {
							learnData.setText(ircode.toString());
							Infrared inf = new Infrared();
							inf.setInfrared(ircode);
							ArrayList<Infrared> infrareds = new ArrayList<Infrared>();
							infrareds.add(inf);
							lKey.setInfrareds(infrareds);
						} else {
							learnData.setText("Learn Error");
						}
					}
					dialog.dismiss();
				}
				if (index > MAXINDX) {
					mDevice.sendLearnCmd(0);
					dialog.dismiss();
				}
				//

			}

		};
		learnLoop.start();

	}
	public void getDeviceType() {
	
		int type = Globals.DEVICE;
		
		switch (type) {
		
		case DeviceType.ET4003:
			mDevice = new ET4003IRDevice();
			break;
		case DeviceType.ET4007:
			ETLogger.debug("4007irdevice");
			mDevice = new ET4007IRDevice();
			break;
		default:
			ETLogger.debug("dummyirdevice");
			mDevice = new DummyIRDevice();
			break;

		}
		// TODO Auto-generated constructor stub
	}

	void initButtonType() {

		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE, View.GONE,
				View.GONE);
		mTitleBarView.setTitleText(R.string.learn_key);

		listAdapt = new KeySampleAdapter(mContext, sampleKeyLists);
		btType = (TextView) findViewById(R.id.learn_type);

		keyName = (TextView) findViewById(R.id.learn_name_edit);
		tvIndex = (TextView) findViewById(R.id.learn_index_tv);

		keyNameEdit = (LinearLayout) findViewById(R.id.ll_learn_name);
		keyNameEdit.setOnClickListener(this);
		btSave = (Button) findViewById(R.id.learn_bt_save);
		btSave.setOnClickListener(this);
		btLearn = (Button) findViewById(R.id.learn_bt_learn);
		btLearn.setOnClickListener(this);
		btSender = (Button) findViewById(R.id.learn_bt_send);
		btSender.setOnClickListener(this);
//		btNext = (Button) findViewById(R.id.learn_bt_next);
//		btNext.setOnClickListener(this);
//		btProv = (Button) findViewById(R.id.learn_bt_previous);
//		btProv.setOnClickListener(this);

		learnData = (TextView) findViewById(R.id.learn_data_context);

		showKey(lKey);

		hListView = (HorizontalListView) findViewById(R.id.allbt_listview);

		hListView.setAdapter(listAdapt);

		hListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub

				// hListViewAdapter.setSelectIndex(position);
				allIndex = position;
				// Logger.debug("position is " + position);
				listAdapt.notifyDataSetChanged();

				String type = String.valueOf(sampleKeyLists.get(position)
						.getType());
				btType.setText(type);
				keyName.setText(sampleKeyLists.get(position).getName());
				// btImgShow.setBackgroundResource(Globals.bckImgIDs[position]);
				// btImgShow.setImageResource(Globals.srcImgIDs[position]);
				// btName.setHint(Globals.srcStrIDs[position]);

			}
		});
	}

	public void learningPopup() {

		if (inflater == null)
			inflater = getLayoutInflater();
		View localView = inflater.inflate(R.layout.learning_popup, null);

		dialog = new Dialog(mContext, android.R.style.Theme_DeviceDefault_Panel);
		// dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(localView);
		dialog.show();
		final Button quit = (Button) localView.findViewById(R.id.learning_quit);

		quit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});

	}

	@SuppressLint("InflateParams") @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.learn_bt_learn:

			learningPopup();
			mDevice.sendLearnCmd(1);
			isLearning = true;

			Message m = new Message();
			Bundle bundle = new Bundle();
			bundle.putInt("learn", 1);
			m.setData(bundle);
			mLearnHandler.sendMessage(m);

			break;
//		case R.id.learn_bt_next:
//			nextKey();
//			break;
		case R.id.learn_bt_save:
			saveKeys();

			Intent intent = new Intent();
			intent.putExtra("index", allIndex);
			// 通过Intent对象返回结果，调用setResult方法
			setResult(Activity.RESULT_OK, intent);
			finish();// 结束当前的activity的生命周期
			break;

		case R.id.ll_learn_name:
			editNameDialog();
			break;
//		case R.id.learn_bt_previous:
//			previousKey();
//			break;

		case R.id.learn_bt_send:
			if (ircode.isValid()) {
				mDevice.sendIr(ircode.getFrequency(), ircode.getDatas());
			}

			break;
			
		default:
			break;
		}
	}

	void editNameDialog() {

		Builder dialog = new AlertDialog.Builder(this);
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.dialog_edit, null);
		dialog.setView(layout);
		nameEdit = (EditText) layout.findViewById(R.id.edit_content);
		nameEdit.setText(keyName.getText().toString());
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String name = nameEdit.getText().toString();
				keyName.setText(name);
				dialog.dismiss();
			}
		});

		dialog.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}

				});
		dialog.show();

	}

	void nextKey() {
		ETLogger.debug("curIndex =" + curIndex + "     allIndex =" + allIndex);
		curIndex++;
		if (curIndex >= allIndex) {
			add = true;
			curIndex = allIndex;
			if (lKey.getInfrareds() != null) { // add key
				allIndex++;
				curIndex = allIndex;
				saveOrupdataKey(true);

				setNewKey();
				showKey(lKey);

			} else {
				Toast.makeText(mContext, "no infrared value",
						Toast.LENGTH_SHORT).show();
			}
		} else {

			lKey = keyLists.get(curIndex - 1);
			showKey(lKey);
		}
	}

	private void setNewKey() {
		// TODO Auto-generated method stub
		lKey = new IRKey();
		lKey.setType(sampleKeyLists.get(0).getType());
		lKey.setName(sampleKeyLists.get(0).getName());
		lKey.setId(allIndex);
		lKey.setInfrareds(null);
		ircode = new IRCode();
	}

	void previousKey() {
		if (add) { // 如果没有学习成果，删除此显示值。
			if (lKey.getInfrareds() != null) {
				saveOrupdataKey(true);
			} else {
				allIndex--;
			}
		}
		add = false;
		if (curIndex > 1) {
			curIndex--;

			lKey = keyLists.get(curIndex - 1);
			showKey(lKey);

		} else {
			Toast.makeText(mContext, "the first key", Toast.LENGTH_SHORT)
					.show();
		}
	}

	void saveOrupdataKey(boolean state) {
		if (state) {
			int type = Integer.valueOf(btType.getText().toString());
			String name = keyName.getText().toString();
			lKey.setType(type);
			lKey.setName(name);

			keyLists.add(lKey);
		} else {
			int type = Integer.valueOf(btType.getText().toString());
			String name = keyName.getText().toString();
			lKey.setType(type);
			lKey.setName(name);
			keyLists.remove(curIndex-1);
			keyLists.add(curIndex - 1, lKey);
			ETLogger.debug("updata value"+lKey.getName()+lKey.getId()+lKey.getType()+curIndex);
		}

	}

	void showKey(IRKey key) {
		String newAddStr = getResources().getString(R.string.add);
		if (add) {
			tvIndex.setText("( " + String.valueOf(curIndex) + " / "
					+ String.valueOf(allIndex) + " )          " + newAddStr);
		} else {
			tvIndex.setText("( " + String.valueOf(curIndex) + " / "
					+ String.valueOf(allIndex) + " )");
		}

		String t = String.valueOf(key.getType());
		btType.setText(t);
		keyName.setText(key.getName());

		if (key.getInfrareds() != null) {
			learnData.setText(key.getInfrareds().get(0).irString());
		} else {
			learnData.setText("Infrareds is null");
		}
	}

	void saveKeys() {
		ETLogger.debug("save value"+lKey.getName()+lKey.getId()+lKey.getType()+curIndex);
		if (lKey.getInfrareds() != null) {
			saveOrupdataKey(add);
		}

		Globals.mRemote.setKeys(keyLists);
	}

	class LearnLoop extends Thread {

		String str = "";
		int i = 0;

		public void run() {

			this.setName("LearnLoop");

			// 初始化消息循环队列，需要在Handler创建之前
			Looper.prepare();

			mLearnHandler = new Handler() {
				@Override
				public void handleMessage(Message msg) {
					Message toMain = mMainHandler.obtainMessage();
					Bundle bundle = new Bundle();
					if (isLearning) {

						Boolean ret = mDevice.getState();
						// Log.v("test", "data is " + i);
						ETLogger.debug("device state is "+ ret);
						if (i > MAXINDX) {

							isLearning = false;
							bundle.putInt("index", i);
							bundle.putBoolean("state", ret);
							toMain.setData(bundle);
							mMainHandler.sendMessage(toMain);
							i = 0;
						} else {
							bundle.putInt("index", i);
							bundle.putBoolean("state", ret);
							toMain.setData(bundle);
							mMainHandler.sendMessage(toMain);
						}

						i++;
						try {
							Thread.sleep(100);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}

				}

			};

			Looper.loop();
		}
	}
	
	
	
}
