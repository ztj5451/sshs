package com.yunrong.util;

import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * 使用盐值的SHA-256加密
 * 
 * @author ZYL
 * 
 */
public class SHA_256 {

	/**
	 * 返回SHA-256加密密码
	 * 
	 * @param rawPass
	 *            未加密密码
	 * @param userDetails
	 * @return
	 */
	public static String encodePassword(String rawPass, UserDetails userDetails) {
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
		ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
		// 以用户名作为加密盐值
		reflectionSaltSource.setUserPropertyToUse("username");
		// 反射调用getUsername()的用户名
		Object salt = reflectionSaltSource.getSalt(userDetails);
		// ShaPasswordEncoder算出加密后的数值
		String encPass = shaPasswordEncoder.encodePassword(rawPass, salt);
		return encPass;
	}

	/**
	 * 返回密码是否匹配
	 * 
	 * @param encPass
	 *            SHA-256加密密码
	 * @param rawPass
	 *            未加密密码
	 * @param userDetails
	 * @return
	 */
	public static boolean isPasswordValid(String encPass, String rawPass,
			UserDetails userDetails) {
		ShaPasswordEncoder shaPasswordEncoder = new ShaPasswordEncoder(256);
		ReflectionSaltSource reflectionSaltSource = new ReflectionSaltSource();
		// 以用户名作为加密的私钥
		reflectionSaltSource.setUserPropertyToUse("username");
		// 反射调用getUsername()的用户名
		Object salt = reflectionSaltSource.getSalt(userDetails);
		// ShaPasswordEncoder算出加密后的数值
		boolean result = shaPasswordEncoder.isPasswordValid(encPass, rawPass,
				salt);
		return result;
	}

	public static void main(String[] args) {

	}

}
