/**
 * 
 */
package com.etek.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * @author Administrator
 * 
 */
public class ETJSON
{
	public static String toJSONString(Object obj)
	{
		if (RemoteApplication.TEST_MODE)
		{
			return JSON.toJSONString(obj, SerializerFeature.WriteNullBooleanAsFalse, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty, SerializerFeature.WriteNullNumberAsZero, SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.WriteNullStringAsEmpty);
		}
		else
		{
			return "";
		}
	}
}
