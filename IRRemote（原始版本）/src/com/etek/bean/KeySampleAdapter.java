package com.etek.bean;

import java.util.ArrayList;

import com.etek.ircode.CallbackOnInfraredSended;
import com.etek.ircode.IInfraredSender;
import com.etek.ircode.impl.InfraredSender;
import com.etek.irremote.R;
import com.etek.ui.KeyButton;
import com.etek.utils.ETLogger;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import android.widget.TextView;

public class KeySampleAdapter extends BaseAdapter {
	Context mContext;
	LayoutInflater mInflater;
	ArrayList<IRKey> mkeys;

	public KeySampleAdapter(Context context, ArrayList<IRKey> keys) {
		mContext = context;
		mInflater = LayoutInflater.from(mContext);
		mkeys = keys;

		ETLogger.debug("keyadapt init");

	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (mkeys != null) {
			return mkeys.size();
		}
		return 0;
	}

	@Override
	public IRKey getItem(int position) {
		// TODO Auto-generated method stub
		if (mkeys != null) {
			return mkeys.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (convertView == null)// 初始化一条item
		{
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list_item_key, parent,
					false);
			holder.txt_key_name = (TextView) convertView
					.findViewById(R.id.list_key_name);

			holder.btn_send = (KeyButton) convertView
					.findViewById(R.id.list_key_button);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		final IRKey key = mkeys.get(position);
		
		if (key != null) {

			holder.txt_key_name.setText(key.getName());
			holder.btn_send.setKeyResource(key);

		} else {
			holder.txt_key_name.setText("未知按键");
		}

		return convertView;
	}

	@Override
	public void notifyDataSetChanged() {
		// TODO Auto-generated method stub
		super.notifyDataSetChanged();
		Log.v("DEBUG", "CHANGE");
	}

	public final class ViewHolder {
		public TextView txt_key_name;
		public KeyButton btn_send;
	}

}