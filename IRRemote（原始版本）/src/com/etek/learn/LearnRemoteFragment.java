package com.etek.learn;

import java.util.List;

import com.etek.bean.Brand;
import com.etek.bean.IRKey;
import com.etek.bean.IRKeyAdapter;
import com.etek.bean.Remote;
import com.etek.constant.ApplianceType;
import com.etek.constant.Globals;
import com.etek.db.IRDataBase;

import com.etek.http.HttpRequest;
import com.etek.ircode.CallbackOnInfraredSended;
import com.etek.irremote.R;
import com.etek.ui.TitleBarView;
import com.etek.utils.ETJSON;
import com.etek.utils.ETLogger;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;

import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;

import android.widget.GridView;
import android.support.v4.app.Fragment;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

//implements OnClickListener,CallbackOnInfraredSended 
public class LearnRemoteFragment extends Fragment implements OnClickListener,
		CallbackOnInfraredSended {

	Context mContext;

	private LayoutInflater inflater;

	TitleBarView mTitleBarView;

	private GridView gvRemoteKeys;

	private IRKeyAdapter rmtKeyAdapter;
	private Button add;

	private Button save;
	private TextView tvModel;
	private TextView tvType;
	private TextView tvBrand;
	private ListView remoteListview;

	private LinearLayout layout;

	private ListAdapter listAdapt;

	private PopupWindow popupWindow;

	private EditText nameEdit;

	private View mBaseView;
	String url = "http://localhost:8080/PhoneRemoteServer/wyf/saveuserremotedata";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		mContext = getActivity();
		mBaseView = inflater.inflate(R.layout.learn_new_remote, null);
		createView();
		// TODO Auto-generated method stub
		return mBaseView;
	}

	void createView() {

		// setContentView(R.layout.learn_new_remote);
		Globals.mRemote = new Remote();
		initKeysView();

		mTitleBarView = (TitleBarView) mBaseView.findViewById(R.id.title_bar);
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE, View.GONE,
				View.GONE);
		mTitleBarView.setTitleText(R.string.learn_remote);


		save = (Button) mBaseView.findViewById(R.id.btn_learn_save);
		 save.setOnClickListener(this);
		add = (Button) mBaseView.findViewById(R.id.btn_learn_add);

		 add.setOnClickListener(this);

		tvModel = (TextView) mBaseView.findViewById(R.id.learn_remote_model);
		tvBrand = (TextView) mBaseView.findViewById(R.id.learn_remote_brand);
		tvType = (TextView) mBaseView.findViewById(R.id.learn_remote_type);
		 tvType.setOnClickListener(this);
		 tvBrand.setOnClickListener(this);
		 tvModel.setOnClickListener(this);
		listAdapt = ArrayAdapter.createFromResource(mContext,
				R.array.type_array, R.layout.option_item);
		String model = getResources().getString(R.string.model);
		Globals.mRemote.setModel(model);
		Globals.mRemote.setId("learn_0");
		String brand = getResources().getString(R.string.brand);
		Brand b = new Brand();
		b.setBrand(brand);
		Globals.mRemote.setBrand(b);
		Globals.mRemote.setType(ApplianceType.TV);

	}

	private void initKeysView() {

		gvRemoteKeys = (GridView) mBaseView.findViewById(R.id.gv_remote_key);
		 rmtKeyAdapter = new IRKeyAdapter(mContext, Globals.mRemote,this);
		 gvRemoteKeys.setAdapter(rmtKeyAdapter);
	}

	public void onClick(View v) {

		// TODO Auto-generated method stub
		switch (v.getId()) {
	
		case R.id.btn_learn_save:
			Globals.mRemote.setLearn(1);
			IRDataBase.saveRemote(mContext, Globals.mRemote);
			String sUserRemote = ETJSON.toJSONString(Globals.mRemote);
			HttpRequest.sendPost(url, sUserRemote);
			break;
		case R.id.btn_learn_add:

			Intent i = new Intent(getActivity(), LearnButtonEdit.class);
			i.putExtra("mode", true);
			startActivityForResult(i, Globals.LEARN_RESUIT_REQUEST);
			break;
		case R.id.learn_remote_type:
			showWindow(v);
			break;
		case R.id.learn_remote_model:
			editModelDialog();
			break;
		case R.id.learn_remote_brand:
			editBrandDialog();
			break;
		default:

			break;
		}

	}

	public void showWindow(View v) {

		// 找到布局文件
		layout = (LinearLayout) LayoutInflater.from(mContext).inflate(
				R.layout.type_spinner_dropdown, null);
		// 实例化listView
		remoteListview = (ListView) layout.findViewById(R.id.listView);
		// 设置listView的适配器
		remoteListview.setAdapter(listAdapt);

		// 实例化一个PopuWindow对象
		popupWindow = new PopupWindow(v);
		// 设置弹框的宽度为布局文件的宽
		popupWindow.setWidth(tvType.getWidth());
		// 高度随着内容变化
		popupWindow.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置一个透明的背景，不然无法实现点击弹框外，弹框消失
		popupWindow.setBackgroundDrawable(new BitmapDrawable());
		// 设置点击弹框外部，弹框消失
		popupWindow.setOutsideTouchable(true);
		// 设置焦点
		popupWindow.setFocusable(true);
		// 设置所在布局
		popupWindow.setContentView(layout);
		// 设置弹框出现的位置，在v的正下方横轴偏移textview的宽度，为了对齐~纵轴不偏移
		popupWindow.showAsDropDown(v, 0, 0);
		// listView的item点击事件
		remoteListview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int position,
					long arg3) {
				// TODO Auto-generated method stub

				tvType.setText(listAdapt.getItem(position).toString());
				int type = Globals.TypeArray[position];
				Globals.mRemote.setType(type);
				popupWindow.dismiss();
				popupWindow = null;

			}
		});

	}

	void editBrandDialog() {

		Builder dialog = new AlertDialog.Builder(mContext);
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.dialog_edit, null);
		dialog.setView(layout);
		dialog.setTitle(R.string.brand);
		nameEdit = (EditText) layout.findViewById(R.id.edit_content);
		nameEdit.setText(tvBrand.getText().toString());
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String name = nameEdit.getText().toString();
				tvBrand.setText(name);
				Brand brand = new Brand();
				brand.setBrand(name);
				Globals.mRemote.setBrand(brand);
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

	void editModelDialog() {

		Builder dialog = new AlertDialog.Builder(mContext);
		LayoutInflater inflater = (LayoutInflater) mContext
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		LinearLayout layout = (LinearLayout) inflater.inflate(
				R.layout.dialog_edit, null);
		dialog.setView(layout);
		dialog.setTitle(R.string.model);
		nameEdit = (EditText) layout.findViewById(R.id.edit_content);
		nameEdit.setText(tvModel.getText().toString());
		dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				String name = nameEdit.getText().toString();
				tvModel.setText(name);
				Globals.mRemote.setModel(name);
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

	@SuppressLint({ "InlinedApi", "InflateParams" })
	public void learnRemotePopup(final int position) {

		if (inflater == null)
			inflater = getActivity().getLayoutInflater();
		View localView = inflater.inflate(R.layout.learn_remote_popup, null);

		TextView title = (TextView) localView.findViewById(R.id.title);
		title.setText("remote" + position);
		final Dialog dialog = new Dialog(mContext,
				android.R.style.Theme_DeviceDefault_Panel);
		dialog.setCanceledOnTouchOutside(true);
		dialog.setContentView(localView);
		dialog.show();

		final Button learnEdit = (Button) localView
				.findViewById(R.id.remote_learn_edit);
		final Button learnClean = (Button) localView
				.findViewById(R.id.remote_learn_clean);

		OnClickListener viewClickListener = new View.OnClickListener() {
			public void onClick(View paramView) {

				if (paramView == learnEdit) {
					dialog.dismiss();
					Intent i = new Intent(getActivity(), LearnButtonEdit.class);
					i.putExtra("mode", false);
					i.putExtra("index", position);
					startActivityForResult(i, Globals.LEARN_RESUIT_REQUEST);

				}
				if (paramView == learnClean) {
					dialog.dismiss();
					List<IRKey> keys = Globals.mRemote.getKeys();
					keys.remove(position);
					Globals.mRemote.setKeys(keys);
					rmtKeyAdapter.notifyDataSetChanged();
				}

			}
		};

		learnEdit.setOnClickListener(viewClickListener);
		learnClean.setOnClickListener(viewClickListener);

	}

	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case Globals.LEARN_RESUIT_REQUEST:
			ETLogger.debug(" LEARN_RESUIT");
			if (resultCode == Activity.RESULT_OK) {
				// rmtKeyAdapter.setSeclection(0);
				for (int i = 0; i < rmtKeyAdapter.getCount(); i++) {
					ETLogger.debug("rmt value is "
							+ rmtKeyAdapter.getItem(i).getName());
				}
				rmtKeyAdapter.notifyDataSetChanged();
				ETLogger.debug("data is right");
			}
			break;

		}
	}

	@Override
	public void onInfrardSended() {
		// TODO Auto-generated method stub
		// Logger.debug(" onInfrardSended ");
	}

	@Override
	public void onLongPress(int position) {
		// TODO Auto-generated method stub
		ETLogger.debug(" longpress " + position);
		learnRemotePopup(position);

	}

}
