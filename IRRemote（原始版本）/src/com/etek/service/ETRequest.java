/**
 * 
 */
package com.etek.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.annotation.JSONField;


/**
 * @author jiangs
 */
public class ETRequest
{
	@JSONField(name="token")
	String token;   // 身份标签，通过恬家提供的sdk接口获取，用于验证请求身份
	
	@JSONField(name="action_params")
	String action_params;


	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}

	public String getAction_params()
	{
		return action_params;
	}

	public void setAction_params(String action_params)
	{
		this.action_params = action_params;
	}
	
	public void setAction_params(Object params_obj)
	{
		this.action_params = JSON.toJSONString(params_obj);
	}
	
	
	
}
