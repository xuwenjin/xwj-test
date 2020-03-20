package util;

import java.util.Base64;

/**
 * Base64工具类
 */
public class Base64Util {

	/**
	 * base64加密
	 */
	public static String encode(byte[] bytes) {
		return Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * base64解密
	 */
	public static final byte[] decode(String base64String) {
		// java自带Base64工具需要把Base64中的换行去掉才能正常使用
		return Base64.getDecoder().decode(base64String.replace("\r\n", ""));
	}

	/**
	 * base64解密
	 */
	public static final byte[] decode(byte[] base64Bytes) {
		return Base64.getDecoder().decode(base64Bytes);
	}

}
