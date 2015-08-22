package com.etek.bean;

import java.util.List;
import com.etek.constant.Globals;
import com.etek.irremote.R;
import com.etek.utils.ETJSON;
import com.etek.utils.ETLogger;

import android.content.Context;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RemoteAdapter extends BaseAdapter {
	protected static final String TAG = "RemoteAdapter";
	private Context mContext;
	private List<Remote> mRemoteList;

	public RemoteAdapter(Context context, List<Remote> lists) {
		this.mContext = context;
		this.mRemoteList = lists;

	}

	@Override
	public int getCount() {
		if (mRemoteList != null) {
			return mRemoteList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		return mRemoteList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final Holder holder;

		Remote rmt = mRemoteList.get(position);
//		ETLogger.info(TAG, "mRemoteList size " + mRemoteList.size()
//				+ "   position is" + position);
		if (convertView == null) {

			convertView = View.inflate(mContext, R.layout.list_remote_item,
					null);
		}

		holder = new Holder();
		holder.iv = (ImageView) convertView.findViewById(R.id.remote_pic);
		holder.tv_name = (TextView) convertView.findViewById(R.id.remote_name);
		holder.tv_desc = (TextView) convertView
				.findViewById(R.id.remote_description);
		holder.tv_model = (TextView) convertView.findViewById(R.id.remote_time);
		convertView.setTag(holder);
		int type = rmt.getType();

		int bm = Globals.getImgID(type);
		// String path = rmt.getImgPath();

		holder.iv.setImageResource(bm);
		holder.tv_name.setText(rmt.getName());
		holder.tv_desc.setText(rmt.getModel());
		holder.tv_model.setText("NUM " + position);
//		ETLogger.info(TAG, ETJSON.toJSONString(rmt));

		return convertView;
	}

	class Holder {
		ImageView iv;
		TextView tv_name;
		TextView tv_desc;
		TextView tv_model;
	}

	public void remove(Object item) {

		// TODO Auto-generated method stub

	}

	public void clearRemotes() {
		// TODO Auto-generated method stub
		mRemoteList.clear();
	}

	public void showRemotes(List<Remote> remotes) {
		// TODO Auto-generated method stub
		if (remotes == null) {
			return;
		}

		// if (mRemoteList == null) {
		// mRemoteList = new ArrayList<Remote>();
		// }
		mRemoteList = remotes;
		notifyDataSetChanged();
	}

}
