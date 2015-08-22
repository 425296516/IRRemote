package com.etek.ircode.impl;

import android.annotation.SuppressLint;



import com.etek.ircode.IInfraredDevice;
import com.etek.ircore.RemoteCore;
import com.etek.utils.ETLogger;
import com.ircode.IRCode;

public class DummyIRDevice implements IInfraredDevice
{
	
	@SuppressLint("NewApi") @Override
	public void sendIr(int freq, int[] data)
	{
		// TODO Auto-generated method stub
		ETLogger.debug("sendIr..........####......freq = "+freq+" , data.length = "+(data==null?-1:data.length));
		byte[] codes = RemoteCore.prontoToETcode(freq,data);
		for(int i=0 ; i<codes.length;i++){
			ETLogger.debug("codes ["+i+" ] = "+codes[i]);
		}
		RemoteCore.sendIRCode(codes,codes.length);
	}

	@Override
	public boolean hasLearn() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public IRCode irCodeReceiver() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean sendLearnCmd(int cmd) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		return false;
	}

	

}
