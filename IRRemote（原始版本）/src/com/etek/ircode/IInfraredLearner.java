package com.etek.ircode;

import com.ircode.IRCode;



/**红外信号发送接口
 * */
public interface IInfraredLearner
{
	final static String TAG = "IInfraredSeneder";
	 public  interface IRCodeReceiver
	    {

	        public abstract void onIRCodeReceived(IRCode ircode);
	    }
	/**
	 * 发送信号
	 * @param remote 遥控器数据
	 * @param key 点击的按键数据
	 * */
	
	 public abstract void waitForIRCode(IRCodeReceiver ircodereceiver);
	
	
}
