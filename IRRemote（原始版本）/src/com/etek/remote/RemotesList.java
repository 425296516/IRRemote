package com.etek.remote;

import java.util.ArrayList;

import java.util.List;

import com.etek.bean.Remote;
import com.etek.bean.RemoteAdapter;
import com.etek.constant.Globals;

import com.etek.irremote.R;
import com.etek.main.MainActivity;

import com.etek.ui.SwipeMenu;
import com.etek.ui.SwipeMenuCreator;
import com.etek.ui.SwipeMenuItem;
import com.etek.ui.SwipeMenuListView;
import com.etek.ui.SwipeMenuListView.OnMenuItemClickListener;
import com.etek.ui.SwipeMenuListView.OnSwipeListener;
import com.etek.ui.TitleBarView;

import com.etek.utils.ETLogger;
import com.etek.utils.Tools;
import com.special.ResideMenu.ResideMenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;

import android.widget.AdapterView.OnItemClickListener;

public class RemotesList extends Fragment implements OnItemClickListener {

	// private ListView mDrawerList;
	// private String[] mNavMenuTitles;

	private RemoteAdapter mAdapter;
	private TitleBarView mTitleBarView;

	private int selected = -1;
	private Context mContext;
	private View mBaseView;
	ImageButton addRemote;
	private ResideMenu resideMenu;

	private List<Remote> remotes = new ArrayList<Remote>();

	private String TAG = "RemotesList";
	private SwipeMenuListView mRmtsListView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		mContext = getActivity();
		mBaseView = inflater.inflate(R.layout.remotes_list, null);

		findView();
		init();
		return mBaseView;
	}

	private void findView() {

		mTitleBarView = (TitleBarView) mBaseView.findViewById(R.id.title_bar);

		mTitleBarView.setCommonTitle(View.VISIBLE, View.VISIBLE, View.GONE,
				View.GONE);
		mTitleBarView.setTitleText(R.string.device);

		mRmtsListView = (SwipeMenuListView) mBaseView
				.findViewById(R.id.lv_remotes);

		addRemote = (ImageButton) mBaseView.findViewById(R.id.add_remote);
		MainActivity parentActivity = (MainActivity) getActivity();
		resideMenu = parentActivity.getResideMenu();
		addRemote.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);

			}
		});

	}

	private void init() {

		remotes = Globals.mRemotes;
		// Logger.debug("remotelist"+Globals.mRemotes.size());
		if (remotes == null || remotes.size() == 0) {
			addRemote.setVisibility(View.VISIBLE);
		} else {
			addRemote.setVisibility(View.GONE);
		}
		mAdapter = new RemoteAdapter(mContext, remotes);
		mRmtsListView.setAdapter(mAdapter);
		mRmtsListView.setOnItemClickListener(this);

		if (selected != -1) {
			mRmtsListView.setItemChecked(selected, true);
			mRmtsListView.setSelection(selected);
		} else {
			mRmtsListView.setItemChecked(0, true);
			mRmtsListView.setSelection(0);
		}
		// step 1. create a MenuCreator
		SwipeMenuCreator creator = new SwipeMenuCreator() {

			@Override
			public void create(SwipeMenu menu) {

				SwipeMenuItem deleteItem = new SwipeMenuItem(mContext);
				// set item background
				// deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
				// 0x3F, 0x25)));
				// set item width
				deleteItem.setWidth(dp2px(90));
				// set a icon
				deleteItem.setIcon(R.drawable.ic_delete_selector);
				// add to menu
				menu.addMenuItem(deleteItem);
			}
		};
		// set creator
		mRmtsListView.setMenuCreator(creator);

		// step 2. listener item click event
		mRmtsListView.setOnMenuItemClickListener(new OnMenuItemClickListener() {
			@Override
			public void onMenuItemClick(int position, SwipeMenu menu, int index) {

				switch (index) {
				case 0:
					// open
					// open(item);
					break;
				case 1:
					// delete
					// delete(item);
					// mAppList.remove(position);
					mAdapter.notifyDataSetChanged();
					break;
				}
			}
		});

		// set SwipeListener
		mRmtsListView.setOnSwipeListener(new OnSwipeListener() {

			@Override
			public void onSwipeStart(int position) {
				// swipe start
			}

			@Override
			public void onSwipeEnd(int position) {
				// swipe end
			}
		});

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		// Log.v(TAG, "start");
		super.onStart();
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		Log.v(TAG, "onResume");
		super.onResume();

	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {

		mRmtsListView.setItemChecked(position, true);
		mRmtsListView.setSelection(position);
//		selected = position;
		Globals.mIndex= position;
		Intent i = new Intent(getActivity(), RemoteActivity.class);
		startActivity(i);
	}

	private int dp2px(int dp) {
		return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
				getResources().getDisplayMetrics());
	}

}
