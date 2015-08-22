package com.etek.remote;

import java.util.ArrayList;
import java.util.List;


import com.etek.constant.Globals;
import com.etek.irremote.R;
import com.etek.ui.RemotePagerAdapter;
import com.etek.ui.TitleBarView;
import com.etek.utils.ETLogger;

import android.os.Bundle;
import android.os.Parcelable;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;


public class RemoteActivity extends FragmentActivity {
	private ViewPager mPager;// 页卡内容
	private ArrayList<Fragment> listViews; // Tab页面列表


	private TitleBarView mTitleBarView;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_remote);

		InitViewPager();
	
	}



	/**
	 * 初始化ViewPager
	 */
	private void InitViewPager() {
		
		
		mTitleBarView = (TitleBarView) findViewById(R.id.title_bar);
		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE, View.GONE,
				View.VISIBLE);
		mTitleBarView.setTitleText(R.string.remote);
		mTitleBarView.setBtnLeft(R.string.back);
		mTitleBarView.setBtnLeftOnclickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Logger.debug("left click ");
				finish();
			}
		});

		
		

		mPager = (ViewPager) findViewById(R.id.vPager);
		listViews = new ArrayList<Fragment>();
		for(int i=0;i<Globals.mRemotes.size();i++){

		listViews.add(new RemoteView(i));
		
		}
		
		// 用support包，只能用getSupportFragmentManager();
		FragmentManager fragmentManager = this.getSupportFragmentManager();
		// 通过fragment适配器把fragment添加入viewpager中
		mPager.setAdapter(new RemotePagerAdapter(fragmentManager,
				listViews));
		mPager.setCurrentItem(Globals.mIndex);
		mPager.setOnPageChangeListener(new MyOnPageChangeListener());
	}

	/**
	 * ViewPager适配器
	 */
	public class MyPagerAdapter extends PagerAdapter {
		public List<View> mListViews;

		public MyPagerAdapter(List<View> mListViews) {
			this.mListViews = mListViews;
		}

		@Override
		public void destroyItem(View arg0, int arg1, Object arg2) {
			((ViewPager) arg0).removeView(mListViews.get(arg1));
		}

		@Override
		public void finishUpdate(View arg0) {
		}

		@Override
		public int getCount() {
			return mListViews.size();
		}

		@Override
		public Object instantiateItem(View arg0, int arg1) {
			((ViewPager) arg0).addView(mListViews.get(arg1), 0);
			return mListViews.get(arg1);
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == (arg1);
		}

		@Override
		public void restoreState(Parcelable arg0, ClassLoader arg1) {
		}

		@Override
		public Parcelable saveState() {
			return null;
		}

		@Override
		public void startUpdate(View arg0) {
		}
	}

	public class MyOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int arg0) {
			ETLogger.debug("page is "+arg0);
			
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {
		}
	}
}
