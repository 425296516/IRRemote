package com.etek.ircode.impl;

import java.util.List;

import com.etek.bean.Infrared;
import com.etek.bean.IRKey;
import com.etek.bean.Remote;
import com.etek.bean.air.AirRemoteState;
import com.etek.constant.ApplianceType;
import com.etek.constant.DeviceType;
import com.etek.constant.Globals;
import com.etek.ircode.AirRemoteStateMananger;
import com.etek.ircode.IInfraredDevice;
import com.etek.ircode.IInfraredFetcher;
import com.etek.ircode.IInfraredSender;
import com.etek.utils.ETJSON;
import com.etek.utils.ETLogger;
import com.etek.utils.RemoteApplication;
import com.etek.utils.RemoteUtils;
//import com.general.ircore.RemoteCore;



public class InfraredSender implements IInfraredSender {

	IInfraredFetcher mFetcher = new InfraredFetcher(
			RemoteApplication.getAppContext());
	IInfraredDevice mDevice = new DummyIRDevice();

	public InfraredSender() {
		super();
		int type = Globals.DEVICE;
		ETLogger.debug("type is "+ type);
		switch (type) {
		
		case DeviceType.ET4003:
			mDevice = new ET4003IRDevice();
			break;
		case DeviceType.ET4007:
			ETLogger.debug("4007irdevice");
			mDevice = new ET4007IRDevice();
			break;
		default:
			ETLogger.debug("dummyirdevice");
			mDevice = new DummyIRDevice();
			break;

		}
		// TODO Auto-generated constructor stub
	}

	@Override
	public void send(Remote remote, IRKey key) {
		// TODO Auto-generated method stub
		
		if (remote == null || remote.getId() == null || key == null) {
			ETLogger.error("remote is null ");
			return;
		}
		
		List<Infrared> infrareds = null;
		ETLogger.debug("remote.getType() "+remote.getType());
		if (remote.getType() == ApplianceType.AIR_CONDITIONER) {
			ETLogger.debug("is air conditioner ");
			if (RemoteUtils.isMultiAirRemote(remote)) {
				ETLogger.debug("isMultiAirRemote");
				AirRemoteState state = AirRemoteStateMananger.getInstance(
						RemoteApplication.getAppContext()).getAirRemoteState(
						remote.getId());
				
				infrareds = mFetcher.fetchAirInfrareds(remote, key, state);
				
			}else if (RemoteUtils.isDiyAirRemote(remote)) {
				ETLogger.debug("isDiyAirRemote");
				AirRemoteState state = AirRemoteStateMananger.getInstance(
						RemoteApplication.getAppContext()).getAirRemoteState(
						remote.getId());
				
				infrareds = mFetcher.fetchAirInfrareds(remote, key, state);
				
			}  else  {
				ETLogger.debug("other");
				infrareds = mFetcher.fetchInfrareds(remote, key);
			}
		} else {
			ETLogger.debug("remote is OK ");
			infrareds = mFetcher.fetchInfrareds(remote, key);
		}
		
		if (infrareds != null) {
//			Logger.debug("infrareds is  "+infrareds.get(0).irString());
			for (Infrared ir : infrareds) {
//				Logger.debug("infrareds is  "+ir.irString());
				if (ir != null && ir.getSignal() != null&&ir.isValid()) {
//					Logger.debug("infrareds is  "+infrareds.get(0).irString());
					mDevice.sendIr(ir.getFreq(), ir.getSignal());
				}
			}
		}
	}
}
