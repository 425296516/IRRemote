package com.etek.utils;


public class UserVerifier
{
	/**手机号验证正则表达式*/
	final static String telRegex = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。   
	
	/**email验证正则表达式*/
	final static String emailRegex = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
	
	/**用户名验证正则表达式*/
	final static String nameRegex = "^[a-zA-Z0-9_\u4e00-\u9fa5]{5,20}+$"; //大小写字母+数字+中文+下划线，5~20个
	
	/**密码验证正则表达式*/
	final static String pwRegex = "^[a-z,A-Z0-9_]{6,16}$";//大小写字母+数字+下划线，6~16个
	
	/**手机号码是否合法*/
	public static boolean isValidPhone(String phone)
	{
		if(phone==null||phone.equals(""))
		{
			return false;
		}
		return phone.matches(telRegex);
	}
	/**email是否合法*/
	public static boolean isValidEmail(String email)
	{
		if(email==null||email.equals(""))
		{
			return false;
		}
		return email.matches(emailRegex);
	}
	
	/**name是否合法*/
	public static boolean isValidName(String name)
	{
		if(name==null||name.equals(""))
		{
			return false;
		}
		return name.matches(nameRegex);
	}
	/**pw是否合法*/
	public static boolean isValidPassword(String pw)
	{
		if(pw==null||pw.equals(""))
		{
			return false;
		}
		return pw.matches(pwRegex);
	}
	
}
