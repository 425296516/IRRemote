package com.etek.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;


@JSONType(orders={"errcode","data"})
public class ETResponse {

	@JSONField(name="errcode")
	int errcode;
    
	@JSONField(name="data")
	Object data;

	
	public int getErrcode()
	{
		return errcode;
	}

	public void setErrcode(int errcode)
	{
		this.errcode = errcode;
	}
	
	public Object getData()
	{
		return data;
	}

	public void setData(Object data)
	{
		this.data = data;
	}
	
	
	/** 获取指定类的结果对象 */
	public <T> T getData(Class<T> type)
	{
		if (this.data == null || this.data.equals(""))
		{
			return null;
		}
		else
		{
			T t = (T) JSON.parseObject(data.toString(), type);
			return t;
		}
	}

	/** 获取指定数据类型的结果对象 */
	public <T> T getData(TypeReference<T> type)
	{
		if (this.data == null || this.data.equals(""))
		{
			return null;
		}
		else
		{
			T t = (T) JSON.parseObject(data.toString(), type);
			return t;
		}
	} 
	
	
	
}
