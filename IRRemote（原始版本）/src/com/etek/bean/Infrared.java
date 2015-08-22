/**
 * 
 */
package com.etek.bean;

import java.util.Arrays;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;
import com.etek.ircore.RemoteCore;
import com.etek.utils.ETLogger;
import com.ircode.IRCode;



/**
 * @author 红外信号
 */
@JSONType(ignores = { "key_id" }, orders = { "key_type", "freq", "func",
		"mark", "ir_mark", "data" })
public class Infrared {

	@JSONField(name = "key_id")
	private long key_id;

	@JSONField(name = "key_type")
	private int key_type;

	@JSONField(name = "func")
	private int func;// 枚举类型，存放空调模式的制冷制热等一对多模式的枚举值

//	@JSONField(serialzeFeatures = { SerializerFeature.BrowserCompatible,
//			SerializerFeature.UseSingleQuotes }, name = "data")
	@JSONField(name = "data")
	private byte[] data;// 红外码值

	@JSONField(name = "freq")
	private int freq;// 此信号的频率

	@JSONField(name = "mark")
	int mark; // 此信号功能标记：A/B信号及空调温度值

	@JSONField(name = "signal")
	private int[] signal;// 红外码值

	public Infrared() {

	}
	public Infrared(IRCode ir) {
		this.signal = ir.getDatas();
		this.freq = ir.getFrequency();
		if(isValid())
		{
		this.data =RemoteCore.prontoToETcode(ir.getFrequency(), ir.getDatas());
		}
		/*if(data!=null){
			for(int i=0; i<data.length;i++){
				ETLogger.debug("data"+"["+i+"]= "+data[i]);
			}
		}*/	
	}
	public long getKey_id() {
		return key_id;
	}

	public void setKey_id(long key_id) {
		this.key_id = key_id;
	}

	public int getKey_type() {
		return key_type;
	}

	public void setKey_type(int key_type) {
		this.key_type = key_type;
	}

	public int getFunc() {
		return func;
	}

	public void setFunc(int func) {
		this.func = func;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public int getFreq() {
		return freq;
	}

	public void setFreq(int freq) {
		this.freq = freq;
	}

	public int getMark() {
		return mark;
	}

	public void setMark(int mark) {
		this.mark = mark;
	}

	public int[] getSignal() {
		return signal;
	}

	public void setSignal(int[] signal) {
		this.signal = signal;
	}

	public void setInfrared(IRCode ir) {
		this.signal = ir.getDatas();
		this.freq = ir.getFrequency();
		if(isValid())
		{
		this.data =RemoteCore.prontoToETcode(ir.getFrequency(), ir.getDatas());
		}
	}

	public boolean isValid() {
		if (freq < 0 || freq > 600000)
			return false;
		if (signal == null) {
			return false;
		}
		if (signal.length < 2) {
			return false;
		}
		for (int d : signal) {
			if (d <= 3) {
				return false;
			}
		}

		return true;

	}

	public String irString() {
		if (isValid()) {
			StringBuilder ret = new StringBuilder();
			ret.append(freq).append(",");

			for (int index = 0; index < signal.length; index++) {
				ret.append(signal[index]);
				if (index + 1 != signal.length) {
					ret.append(",");
				}
			}
			return ret.toString();
		} else {
			return "";
		}
	}
	@Override
	public String toString() {
		return "Infrared [key_id=" + key_id + ", key_type=" + key_type
				+ ", func=" + func + ", data=" + Arrays.toString(data)
				+ ", freq=" + freq + ", mark=" + mark + ", signal="
				+ Arrays.toString(signal) + "]";
	}

	
	
}
