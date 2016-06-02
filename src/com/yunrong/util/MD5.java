package com.yunrong.util;

import java.security.MessageDigest;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;

public class MD5 {
	private static final Logger log = Logger.getLogger(MD5.class);

	/**
	 * 获得MD5加密密码的结果
	 * 
	 * @param string
	 * @return
	 */
	public static String encode(String string) {
		String md5Code = null;
		try {
			// 获得MD5加密方法实例
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			// 获得加密后的字节数组
			byte[] bytes = md5.digest(string.getBytes());
			md5Code = byteArrayToHexString(bytes);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return md5Code;
	}

	/**
	 * 重载方法，用于多次加密
	 * 
	 * @param string
	 * @param times
	 * @return
	 */
	public static String encode(String string, int times) {
		String md5Code = encode(string);
		for (int i = 1; i < times; i++) {
			md5Code = encode(md5Code);
		}
		return md5Code;
	}

	/**
	 * 字节数组转为十六进制字符串
	 * 
	 * @param bytes
	 * @return
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		StringBuffer sb = new StringBuffer();
		// 遍历字节数组
		for (byte b : bytes) {
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}

	/**
	 * 字节转为十六进制字符串
	 * 
	 * @param b
	 * @return
	 */
	private static String byteToHexString(byte b) {
		String hexStr = null;
		int n = b;
		if (n < 0) {
			// 标准移位算法
			n = b & 0x7F + 128;
		}
		hexStr = Integer.toHexString(n / 16) + Integer.toHexString(n % 16);
		return hexStr.toUpperCase();
	}

	/**
	 * MD5密码验证方法
	 * 
	 * @param inputString
	 * @param MD5Code
	 * @return
	 */
	public static boolean verifyPassword(String inputString, String MD5Code) {
		return encode(inputString).equals(MD5Code);
	}

	/**
	 * 多次加密时，MD5密码验证方法
	 * 
	 * @param inputString
	 *            当前输入密码
	 * @param MD5Code
	 *            数据库中MD5密码
	 * @param times
	 * @return
	 */
	public static boolean verifyPassword(String inputString, String MD5Code,
			int times) {
		return encode(inputString, times).equals(MD5Code);
	}

	/**
	 * 主函数
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("admin:" + encode("123").toLowerCase());
		Md5PasswordEncoder aa = new Md5PasswordEncoder();
		aa.setEncodeHashAsBase64(false);
		String dd = aa.encodePassword("123", "admin");
		System.out.println(dd);
		System.out.println(dd.length());

		dd = aa.encodePassword("123", "user1");
		System.out.println(dd);
		System.out.println(dd.length());

		dd = aa.encodePassword("123", "user2");
		System.out.println(dd);
		System.out.println(dd.length());
		

	}

}
