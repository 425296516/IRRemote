package com.etek.ircode.impl;

import android.annotation.SuppressLint;

import android.util.Log;


import com.etek.ircode.IInfraredDevice;
import com.etek.ircore.RemoteCore;
import com.etek.utils.Tools;
import com.ircode.IRCode;



public class ET4003IRDevice implements IInfraredDevice
{
	private static final String ET_IR_SEND = "/sys/class/etek/sec_ir/ir_send";
	private static final String ET_IR_LEARN = "/sys/class/etek/sec_ir/ir_learn";
	private static final String ET_IR_STATE = "/sys/class/etek/sec_ir/ir_state";
	@SuppressLint("NewApi") @Override
	public void sendIr(int freq, int[] data)
	{
		// TODO Auto-generated method stub
		Log.i(TAG, "sendIr..........####......freq = "+freq+" , data.length = "+(data==null?-1:data.length));
		byte[] codes = RemoteCore.prontoToETcode(freq,
				data);
//		String codeStr = Tools.bytesToHexString(codes);
		RemoteCore.sendIRCode(codes,codes.length);
//		sendIRCode(sendData, length);
	}

	@Override
	public boolean hasLearn() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public IRCode irCodeReceiver() {
		String data = Tools.readSysFile(ET_IR_LEARN);
//		Log.v(TAG, "data= "+data);
		
		byte[] learnNewData = Tools.strToIntarray2(data);
		IRCode  ircode = RemoteCore.ET4003Learn(learnNewData);
//		IRCode ircode = new IRCode("38028,343,172,21,65,21,65,21,65,21,22,21,65,21,65,21,65,21,65,21,22,21,22,21,22,21,65,21,22,21,22,21,22,21,22,21,65,21,22,21,22,21,65,21,22,21,22,21,22,21,22,21,22,21,65,21,65,21,22,21,65,21,65,21,65,21,65,21,1673,343,86,21,3732");
		return ircode;
	}

	@Override
	public boolean sendLearnCmd(int cmd) {
		if(cmd == 1){
			RemoteCore.learnIRCodeStart();
			}else {
				RemoteCore.learnIRCodeStop();	
			}
			return false;
	}

	@Override
	public boolean getState() {
		// TODO Auto-generated method stub
		String state = Tools.readSysFile(ET_IR_STATE);
//		Logger.debug("et4007 state is "+state);
		if("1".equals(state)){
			return true;
		}else {
			return false;
		}
	}

	

}
