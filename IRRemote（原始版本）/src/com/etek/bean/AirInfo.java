package com.etek.bean;


import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;


@JSONType(orders={"id","power","mode","temp","wind","wind_direct"})
public class AirInfo implements Cloneable
{

	
	@JSONField(name="id")
	private int id;//id
	
	@JSONField(name="power")
	private int power;
	
	@JSONField(name="mode")
	private int mode;
	
	
	@JSONField(name="temp")
	private int temp;
	
	@JSONField(name="wind")
	private int	 wind;
	
	@JSONField(name="wind_direct")
	private int	wind_direct ;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPower() {
		return power;
	}

	public void setPower(int power) {
		this.power = power;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

	public int getTemp() {
		return temp;
	}

	public void setTemp(int temp) {
		this.temp = temp;
	}

	public int getWind() {
		return wind;
	}

	public void setWind(int wind) {
		this.wind = wind;
	}

	public int getWind_direct() {
		return wind_direct;
	}

	public void setWind_direct(int wind_direct) {
		this.wind_direct = wind_direct;
	}

	public AirInfo(int id, int power, int mode, int temp,
			int wind, int wind_direct) {
		super();
		this.id = id;
		this.power = power;
		this.mode = mode;
		this.temp = temp;
		this.wind = wind;
		this.wind_direct = wind_direct;
	}
	
	

}
