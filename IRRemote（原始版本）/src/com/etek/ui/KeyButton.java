package com.etek.ui;
import com.etek.bean.IRKey;

import com.etek.irremote.R;



import android.content.Context;

import android.util.AttributeSet;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import android.widget.TextView;



public class KeyButton extends FrameLayout {
    
    private  Button imgKey;  
    private TextView  txtKey;

    public KeyButton(Context context) {
        super(context,null);
    }
    
    public KeyButton(Context context,AttributeSet attributeSet) {
        super(context, attributeSet);
        
        LayoutInflater.from(context).inflate(R.layout.key_button, this,true);
        
        this.imgKey = (Button)findViewById(R.id.key_img);
//        this.txtKey = (TextView)findViewById(R.id.key_txt);
        
        this.setClickable(true);
        this.setFocusable(true);
    }
    public void setKeyResource(IRKey key) {
    	imgKey.setText(key.getName());
//    	txtKey.setVisibility(View.GONE);
    }
    
//    public void setKeyResource(Key key) {
//    	switch (key.getType())
//		{
//		case KeyType.BASE_OVAL:
//	
//
//		case KeyType.BASE_OVAL_CYAN:
//		case KeyType.BASE_OVAL_ORANGE:
//		case KeyType.BASE_OVAL_PURPLE:
//
//		case KeyType.BASE_ROUND:
//		case KeyType.BASE_SQUARE:
//		case KeyType.MEMORYKEY:
//		case KeyType.MEMORYKEY_ONE:
//		case KeyType.MEMORYKEY_TWO:
//			
//			if(key.getName()!=null&&key.getName().equals("")==false)
//			{
//			
//				txtKey.setText(key.getName());
//			}
//			else
//			{
//				txtKey.setText(RemoteUtils.getKeyName(key.getType()));
//				
//			}
//			break;
//		case KeyType.POWER:
//			imgKey.setBackgroundResource(R.drawable.main_btn_power_selector);
//			imgKey.setImageResource(R.drawable.main_ic_power);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//	
//		case KeyType.ONE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_1);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.TWO:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_2);
//			imgKey.setVisibility(View.VISIBLE);
//		
//			break;
//		case KeyType.THREE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_3);
//			imgKey.setVisibility(View.VISIBLE);
//		
//			break;
//		case KeyType.FOUR:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_4);
//			imgKey.setVisibility(View.VISIBLE);
//	
//			break;
//		case KeyType.FIVE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_5);
//			imgKey.setVisibility(View.VISIBLE);
//
//			break;
//		case KeyType.SIX:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_6);
//			imgKey.setVisibility(View.VISIBLE);
//			
//			break;
//		case KeyType.SEVEN:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_7);
//			imgKey.setVisibility(View.VISIBLE);
//	
//			break;
//		case KeyType.EIGHT:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_8);
//			imgKey.setVisibility(View.VISIBLE);
//
//			break;
//		case KeyType.NINE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_9);
//			imgKey.setVisibility(View.VISIBLE);
//
//			break;
//		case KeyType.ZERO:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_0);
//			imgKey.setVisibility(View.VISIBLE);
//	
//			break;
//		case KeyType.BACK:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_back);
//			imgKey.setVisibility(View.VISIBLE);
//
//			break;
//		case KeyType.OPEN:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_eject);
//			imgKey.setVisibility(View.VISIBLE);
//
//			break;
//		case KeyType.MENU_EXIT:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_exit);
//			imgKey.setVisibility(View.VISIBLE);
//
//			break;
//		case KeyType.FAVORITES:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_favorite);
//			imgKey.setVisibility(View.VISIBLE);
//	
//			break;
//		case KeyType.FORWARD:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_ff);
//	
//			break;
//		case KeyType.HOME:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_home);
//
//			break;
//		case KeyType.SIGNAL:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_input);
//
//			break;
//		case KeyType.MUTE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_mute);
//
//			break;
//		case KeyType.NEXT:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_next);
//
//			break;
//		case KeyType.PAUSE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_pause);
//
//			break;
//		case KeyType.PLAY:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_play);
//
//			break;
//		case KeyType.PREVIOUS:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_prev);
//
//			break;
//		case KeyType.REWIND:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_quickskip);
//			
//			break;
//		case KeyType.REC:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_rec);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.SETTING:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_setting);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.STOP:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_stop);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.STEP_SLOW:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_rew);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.WIND_VERTICAL:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_ic_vertical);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.TEN_PLUS:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_num_hyphen);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.REPEAT:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.universal_ic_repeat);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.INFORMATION:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.universal_ic_info);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.MENU:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.universal_ic_menu);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.SHUTTER_ONE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.universal_ic_shuffle);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.PLAY_PAUSE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.widget_noti_ic_play_pause);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.BASE_OVAL_BLUE:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_color_b_selector);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.BASE_OVAL_GREEN:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_color_g_selector);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.BASE_OVAL_YELLOW:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_color_r_selector);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//		case KeyType.BASE_OVAL_RED:
//			imgKey.setBackgroundResource(R.drawable.btn_normal_selector);
//			imgKey.setImageResource(R.drawable.main_color_y_selector);
//			imgKey.setVisibility(View.VISIBLE);
//			txtKey.setVisibility(View.GONE);
//			break;
//				
//		default:
//			
//			txtKey.setText(key.getName());
//			break;
//		}
//    }
    public void setImgResource(int resourceID) {
//        this.imgKey.setImageResource(resourceID);
    }
    
    public void setText(String text) {
        this.txtKey.setText(text);
    }
    
    public void setTextColor(int color) {
        this.txtKey.setTextColor(color);
    }
    
    public void setTextSize(float size) {
        this.txtKey.setTextSize(size);
    }
    
}