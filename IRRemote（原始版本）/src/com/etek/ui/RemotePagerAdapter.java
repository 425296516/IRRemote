package com.etek.ui;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class RemotePagerAdapter extends FragmentPagerAdapter { 
private ArrayList<Fragment> fragments; 
public RemotePagerAdapter(FragmentManager fm) { 
super(fm); 
// TODO Auto-generated constructor stub 
} 
public RemotePagerAdapter(FragmentManager fm,ArrayList<Fragment> fragments){ 
super(fm); 
this.fragments = fragments; 
} 
/* (non-Javadoc) 
 * @see android.support.v4.app.FragmentPagerAdapter#getItem(int) 
 */ 
@Override 
public Fragment getItem(int arg0) { 
return fragments.get(arg0); 
} 
/* (non-Javadoc) 
 * @see android.support.v4.view.PagerAdapter#getCount() 
 */ 
@Override 
public int getCount() { 
return fragments.size(); 
} 
@Override 
public int getItemPosition(Object object) { 
// TODO Auto-generated method stub 
return super.getItemPosition(object); 
} 
} 