package com.etek.constant;

public class ErrorCode
{
	/**
	 * 错误码 ：请求成功
	 * */
	public final static int ERRCODE_SUCCESS = 10000;
	
	/**
	 * 错误码 ： 请求失败
	 * */
	public final static int ERRCODE_FAILED = 10001;
	
	/**
	 * 错误码 ： 未发现请求参数
	 * */
	public final static int ERRCODE_NO_PARAMS = 10002;
	
	/**
	 * 错误码 ： 无请求令牌
	 * */
	public final static int ERRCODE_NO_TOKEN = 10003;
	
	/**
	 * 错误码 ：无具体请求所需的参数，比如下载遥控器的遥控器id
	 * */
	public final static int ERRCODE_NO_ACTION_PARAMS = 10004;
	
	/**
	 * 错误码 ： 授权验证失败
	 * */
	public final static int ERRCODE_AUTHED_FAILED = 10005;
	
	/**
	 * 错误码 ： 禁止操作，可能的原因为ip访问过多，下载遥控器数量限制等
	 * */
	public final static int ERRCODE_FORBIDDEN = 10016;
	
	/**
	 * 错误码 ：未找到遥控器 
	 * */
	public final static int ERRCODE_REMOTE_NOT_FOUND = 10017;

	/**
	 * 错误码 ：精确匹配流程完成:比如电视的精确匹配按键为5种，而此5种按键都已被用户标记
	 * */
	public final static int ERRCODE_MATCH_COMPLETED = 10018;
	
	/**
	 * 错误码 ：注册失败-> 没有有效的手机号或Email
	 * */
	public final static int ERRCODE_REGISTER_NOT_FOUND_VALID_PHONE_OR_EMAIL = 10101;
	
	/**
	 * 错误码 ：注册失败-> 手机号或邮箱已被注册
	 * */
	public final static int ERRCODE_REGISTER_PHONE_OR_EMAIL_EXISTS = 10102;
	
	/**
	 * 错误码 ：登陆失败->手机号或邮箱未注册
	 * */
	public final static int ERRCODE_LOGIN_PHONE_OR_EMAIL_NOT_REGISTERED = 10201;
	
	/**
	 * 错误码 ：登陆失败->密码不正确
	 * */
	public final static int ERRCODE_LOGIN_PW_WRONG = 10202;
	
	
	
}
