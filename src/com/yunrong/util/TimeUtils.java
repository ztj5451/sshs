package com.yunrong.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期格式转换工具
 * 
 * @author Administrator
 * 
 */
public class TimeUtils {
	//返回完整日期格式
	public static String getFullDateTime(Date date) {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
	}

	public static String getDateTime() {
		return new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss").format(new Date());
	}

	// 聊天时间格式化

	public static String getChatDateTime() {
		return new SimpleDateFormat("MM-dd HH:mm:ss").format(new Date());
	}

	public static String getDateTime(String paramString) {
		StringBuffer localStringBuffer = new StringBuffer();
		localStringBuffer
				.append(paramString)
				.append("_")
				.append(new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss")
						.format(new Date()));
		return localStringBuffer.toString();
	}

	public static String getImageName() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public static String getLocationTime() {
		return new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
	}

	public static String getNormalDataTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
	}
}
