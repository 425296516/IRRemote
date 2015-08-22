package com.etek.main;

import java.io.IOException;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.View;


import com.etek.db.IRDataBase;
import com.etek.db.LocalDB;
import com.etek.db.UserDB;
import com.etek.irremote.R;
import com.etek.learn.LearnRemoteFragment;
import com.etek.remote.MatchOrSearchFragment;
import com.etek.remote.RemotesList;
import com.etek.setups.SettingsFragment;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

public class MainActivity extends BaseActivity implements View.OnClickListener{

    private ResideMenu resideMenu;
    private MainActivity mContext;
    private ResideMenuItem itemHome;
    private ResideMenuItem itemRemote;
    private ResideMenuItem itemLearn;
    private ResideMenuItem itemSettings;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mContext = this;
        setUpMenu();
        if( savedInstanceState == null )
            changeFragment(new MatchOrSearchFragment());
    }

    private void setUpMenu() {
    	  mContext = this;
        // attach to current activity;
        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.drawable.studio);
        resideMenu.attachToActivity(this);
        resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        resideMenu.setScaleValue(0.6f);
      
        createDatabase();
        IRDataBase.getRemoteList(mContext);
        // create menu items;
        itemHome     = new ResideMenuItem(this, R.drawable.icon_home,     R.string.remote);
        itemRemote  = new ResideMenuItem(this, R.drawable.img_add_remote_pressed,  R.string.add_remote);
        itemLearn = new ResideMenuItem(this, R.drawable.icon_calendar, R.string.learn_remote);
        itemSettings = new ResideMenuItem(this, R.drawable.icon_settings, R.string.settings);

        itemHome.setOnClickListener(this);
        itemRemote.setOnClickListener(this);
        itemLearn.setOnClickListener(this);
        itemSettings.setOnClickListener(this);

        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemRemote, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemLearn, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return resideMenu.dispatchTouchEvent(ev);
    }

    @Override
    public void onClick(View view) {

        if (view == itemHome){
            changeFragment(new RemotesList());
        }else if (view == itemRemote){
            changeFragment(new MatchOrSearchFragment());
        }else if (view == itemLearn){
            changeFragment(new LearnRemoteFragment());
        }else if (view == itemSettings){
            changeFragment(new SettingsFragment());
        }

        resideMenu.closeMenu();
    }

    

	private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
//            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
//            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };

    private void changeFragment(Fragment targetFragment){
        resideMenu.clearIgnoredViewList();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_fragment, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }

    // What good method is to access resideMenu？
    public ResideMenu getResideMenu(){
        return resideMenu;
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
}
