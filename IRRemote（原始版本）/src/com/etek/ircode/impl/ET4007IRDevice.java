package com.etek.ircode.impl;


import com.etek.constant.Globals;
import com.etek.utils.ETLogger;
import com.etek.utils.Tools;

import com.etek.ircode.IInfraredDevice;
import com.etek.ircore.RemoteCore;
import com.ircode.IRCode;


public class ET4007IRDevice implements IInfraredDevice
{
	
	private static final String ET_IR_LEARN = "/sys/class/etek/sec_ir/ir_learn";
	private static final String ET_IR_STATE = "/sys/class/etek/sec_ir/ir_state";
 @Override
	public void sendIr(int freq, int[] data)
	{
		ETLogger.debug("sendIr..........####......freq = "+freq+" , data.length = "+(data==null?-1:data.length));
		byte[] codes = RemoteCore.prontoToETcode(freq,
				data);
//		String codeStr = Tools.bytesToHexString(codes);
		RemoteCore.sendIRCode(codes,codes.length);
		
	}

	public ET4007IRDevice() {
	RemoteCore.IRinit();
	Globals.ISLEARN =true;
	// TODO Auto-generated constructor stub
}

	@Override
	public boolean hasLearn() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public IRCode irCodeReceiver() {
		// TODO Auto-generated method stub
		String data = Tools.readSysFile(ET_IR_LEARN);
		byte[] learnNewData = Tools.strToIntarray(data);
		IRCode  ircode = RemoteCore.ET4007Learn(learnNewData);
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
		String state = Tools.readSysFile(ET_IR_STATE);
//		Logger.debug("et4007 state is "+state);
		if("1".equals(state)){
			return true;
		}else {
			return false;
		}
		// TODO Auto-generated method stub
		
	}

	

}
