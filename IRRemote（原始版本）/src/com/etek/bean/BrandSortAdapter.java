package com.etek.bean;

import java.util.List;

import com.etek.constant.Globals;
import com.etek.irremote.R;





import android.annotation.SuppressLint;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class BrandSortAdapter extends BaseAdapter implements SectionIndexer{
	private List<Brand> list = null;
	private Context mContext;
	String TAG = "BrandSortAdapter";
	public BrandSortAdapter(Context mContext, List<Brand> list) {
		this.mContext = mContext;
		this.list = list;
	}
	
	
	public void updateListView(List<Brand> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams") public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		String title = "";
		final Brand mBrand = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.brand_item_list, null);
			viewHolder.tvTitle = (TextView) view.findViewById(R.id.title);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		int section = getSectionForPosition(position);
		
	
		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mBrand.getSortLetters());
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		
			
//			Log.v(TAG, "chinese is "+mBrand.getBrand_cn());
			if ("".equalsIgnoreCase(mBrand.getBrand_tra())){
				title = mBrand.getSortLetters();
			
			}else {
				title = mBrand.getBrand();
			
			}
		
		
		viewHolder.tvTitle.setText(title);
		
		return view;

	}
	


	final static class ViewHolder {
		TextView tvLetter;
		TextView tvTitle;
	}


	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	
	
	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
	
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}