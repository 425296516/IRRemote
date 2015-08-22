/**
 * 
 */
package com.etek.bean.air;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;


/**
 * @author jiangs
 */
@JSONType(ignores = { "caculate_number", "time_limit", "time" }, orders = {
		"remote_id", "protocol", "current_key", "last_key", "power", "mode",
		"temp", "wind_amount", "wind_direction", "wind_hoz", "wind_ver",
		"super_mode", "sleep", "hot", "time", "temp_display", "power_saving",
		"anion", "comfort", "flash_air", "light", "wet", "mute", "time_limit",
		"caculate_number" })
public class AirRemoteState {
	AirRemoteState() {
	}

	public AirRemoteState(String remote_id) {
		if (remote_id == null) {
			throw new NullPointerException(
					"the string param 'remote_id' can not be null");
		}

		this.remote_id = remote_id;
		power = AirPower.POWER_OFF;
		this.mode = AirMode.AUTO;
		this.temp = AirTemp.T26;
		this.wind_amount = AirWindAmount.AUTO;
		this.wind_direction = AirWindDirection.AUTO;
		wind_hoz = AirWindHoz.HOZ_OFF;
		wind_ver = AirWindVer.VER_OFF;

		this.super_mode = AirSuper.SUPER_OFF;
		this.sleep = AirSleep.SLEEP_OFF;
		this.hot = AirAidHot.AIDHOT_OFF;
		this.time = AirTime.TIME_OFF;
		this.temp_display = AirTempDisplay.DISPLAY_NONE;

		this.power_saving = AirPowerSaving.POWER_SAVING_OFF;
		this.anion = AirAnion.ANION_OFF;
		this.comfort = AirComfort.COMFORT_OFF;
		this.flash_air = AirFlashAir.FLASH_OFF;
		this.light = AirLight.LIGHT_ON;
		this.wet = AirWet.WET_OFF;
		this.mute = AirMute.MUTE_OFF;
		this.caculate_number = 0;
	}

	@JSONField(name = "remote_id")
	String remote_id;

	@JSONField(name = "protocol")
	int protocol;

	// 空调主要状态
	@JSONField(name = "current_key")
	int current_key; // 触发的按钮

	@JSONField(name = "last_key")
	int last_key;

	@JSONField(name = "caculate_number")
	int caculate_number;// 本次遥控器打开后，是第几次点击按钮

	@JSONField(name = "power")
	AirPower power;// 开关

	@JSONField(name = "mode")
	AirMode mode;// 空调模式

	@JSONField(name = "temp")
	AirTemp temp;// 温度

	@JSONField(name = "wind_amount")
	AirWindAmount wind_amount;// 风量

	@JSONField(name = "wind_direction")
	AirWindDirection wind_direction;// 风向

	@JSONField(name = "wind_hoz")
	AirWindHoz wind_hoz; // 水平风向

	@JSONField(name = "wind_ver")
	AirWindVer wind_ver; // 垂直风向

	// 其他状态

	@JSONField(name = "super_mode")
	AirSuper super_mode; // 强力

	@JSONField(name = "sleep")
	AirSleep sleep;// 睡眠

	@JSONField(name = "hot")
	AirAidHot hot;// 辅热 / 干燥

	@JSONField(name = "time")
	AirTime time;// 定时

	@JSONField(name = "temp_display")
	AirTempDisplay temp_display;// 温度显示

	@JSONField(name = "power_saving")
	AirPowerSaving power_saving;// 节能模式

	@JSONField(name = "anion")
	AirAnion anion;// 负离子

	@JSONField(name = "comfort")
	AirComfort comfort;// 舒适

	@JSONField(name = "flash_air")
	AirFlashAir flash_air;// 清新、换气

	@JSONField(name = "light")
	AirLight light;// 灯光

	@JSONField(name = "wet")
	AirWet wet;// 加湿

	@JSONField(name = "mute")
	AirMute mute;// 静音

	@JSONField(name = "time_limit")
	int time_limit; // 定时量，单位分钟(用于定时开，定时关) 30~1440，半小时~24小时

	public int getProtocol() {
		return protocol;
	}

	public void setProtocol(int protocol) {
		this.protocol = protocol;
	}

	public AirPower getPower() {
		return power;
	}

	public void setPower(AirPower power) {
		this.power = power;
	}

	public AirMute getMute() {
		return mute;
	}

	public void setMute(AirMute mute) {
		this.mute = mute;
	}

	public int getCaculate_number() {
		return caculate_number;
	}

	public void setCaculate_number(int caculate_number) {
		this.caculate_number = caculate_number;
	}

	public AirMode getMode() {
		return mode;
	}

	public void setMode(AirMode mode) {
		this.mode = mode;
	}

	public AirTemp getTemp() {
		return temp;
	}

	public void setTemp(AirTemp temp) {
		this.temp = temp;
	}

	public String getRemote_id() {
		return remote_id;
	}

	public void setRemote_id(String remote_id) {
		this.remote_id = remote_id;
	}

	public AirTempDisplay getTemp_display() {
		return temp_display;
	}

	public void setTemp_display(AirTempDisplay temp_display) {
		this.temp_display = temp_display;
	}

	public AirWindAmount getWind_amount() {
		return wind_amount;
	}

	public void setWind_amount(AirWindAmount wind_amount) {
		this.wind_amount = wind_amount;
	}

	public AirWindDirection getWind_direction() {
		return wind_direction;
	}

	public void setWind_direction(AirWindDirection wind_direction) {
		this.wind_direction = wind_direction;
	}

	public AirWindHoz getWind_hoz() {
		return wind_hoz;
	}

	public void setWind_hoz(AirWindHoz wind_hoz) {
		this.wind_hoz = wind_hoz;
	}

	public AirWindVer getWind_ver() {
		return wind_ver;
	}

	public void setWind_ver(AirWindVer wind_ver) {
		this.wind_ver = wind_ver;
	}

	public AirTime getTime() // 空调的定时状态 默认为关，即上次定时后，关闭空调再开启时定时状态重置
	{
		if (time == null) {
			time = AirTime.TIME_OFF;
		}
		return time;
	}

	public void setTime(AirTime time) {
		this.time = time;
	}

	public AirSuper getSuper_mode() {
		return super_mode;
	}

	public void setSuper_mode(AirSuper super_mode) {
		this.super_mode = super_mode;
	}

	public AirSleep getSleep() {
		return sleep;
	}

	public void setSleep(AirSleep sleep) {
		this.sleep = sleep;
	}

	public AirAidHot getHot() {
		return hot;
	}

	public void setHot(AirAidHot hot) {
		this.hot = hot;
	}

	public AirPowerSaving getPower_saving() {
		return power_saving;
	}

	public void setPower_saving(AirPowerSaving power_saving) {
		this.power_saving = power_saving;
	}

	public AirAnion getAnion() {
		return anion;
	}

	public void setAnion(AirAnion anion) {
		this.anion = anion;
	}

	public AirComfort getComfort() {
		return comfort;
	}

	public void setComfort(AirComfort comfort) {
		this.comfort = comfort;
	}

	public AirFlashAir getFlash_air() {
		return flash_air;
	}

	public void setFlash_air(AirFlashAir flash_air) {
		this.flash_air = flash_air;
	}

	public AirLight getLight() {
		return light;
	}

	public void setLight(AirLight light) {
		this.light = light;
	}

	public AirWet getWet() {
		return wet;
	}

	public void setWet(AirWet wet) {
		this.wet = wet;
	}

	public int getCurrent_key() {
		return current_key;
	}

	public void setCurrent_key(int current_key) {
		this.current_key = current_key;
	}

	public int getLast_key() {
		return last_key;
	}

	public void setLast_key(int last_key) {
		this.last_key = last_key;
	}

	public int getTime_limit() {
		return time_limit;
	}

	public void setTime_limit(int time_limit) {
		this.time_limit = time_limit;
	}

	@Override
	public String toString() {
		return "AirRemoteState [remote_id=" + remote_id + ", protocol="
				+ protocol + ", current_key=" + current_key + ", last_key="
				+ last_key + ", caculate_number=" + caculate_number
				+ ", power=" + power + ", mode=" + mode + ", temp=" + temp
				+ ", wind_amount=" + wind_amount + ", wind_direction="
				+ wind_direction + ", wind_hoz=" + wind_hoz + ", wind_ver="
				+ wind_ver + ", super_mode=" + super_mode + ", sleep=" + sleep
				+ ", hot=" + hot + ", time=" + time + ", temp_display="
				+ temp_display + ", power_saving=" + power_saving + ", anion="
				+ anion + ", comfort=" + comfort + ", flash_air=" + flash_air
				+ ", light=" + light + ", wet=" + wet + ", mute=" + mute
				+ ", time_limit=" + time_limit + "]";
	}

	
	
}
