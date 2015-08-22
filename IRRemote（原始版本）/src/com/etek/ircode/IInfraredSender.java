package com.etek.ircode;

import com.etek.bean.IRKey;
import com.etek.bean.Remote;

/**红外信号发送接口
 * */
public interface IInfraredSender
{
	final static String TAG = "IInfraredSeneder";

	/**
	 * 发送信号
	 * @param remote 遥控器数据
	 * @param key 点击的按键数据
	 * */
	public void send(Remote remote,IRKey key);
	
	
	
	
}
