package jre8Test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * 测试Base64
 * 
 * 对Base64编码的支持已经被加入到Java 8官方库中，这样不需要使用第三方库就可以进行Base64编码
 * 
 * @author XU.WJ 2018年3月1日
 */
public class TestBase64 {

	public static void main(String[] args) {
		//加密
		String text = "Base64 finally in Java 8!";
		String encode = Base64.getEncoder().encodeToString(text.getBytes(StandardCharsets.UTF_8));
		System.out.println(encode);

		//解密
		String decode = new String(Base64.getDecoder().decode(encode), StandardCharsets.UTF_8);
		System.out.println(decode);
	}

}
