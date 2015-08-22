package com.etek.ircode;



/**按键发送后回调*/
public  interface CallbackOnInfraredSended
{
	/**按键已发送*/
	public void onInfrardSended();
	
	public void onLongPress(int position);
}
