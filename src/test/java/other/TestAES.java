package other;

import java.security.Key;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import lombok.SneakyThrows;

/**
 * 对称算法-AES
 */
public class TestAES {

	public static final String ALGORITHM = "AES";

	public static String src = "xwj security aes";

	public static void main(String[] args) {
		jdkAES();
	}

	@SneakyThrows
	public static void jdkAES() {
		// 生成key
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		keyGenerator.init(128); // 密钥的长度最少支持为128、192、256
		SecretKey secretKey = keyGenerator.generateKey();
		byte[] keyBytes = secretKey.getEncoded();

		// key转换
		Key key = new SecretKeySpec(keyBytes, ALGORITHM);

		// 加密
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] result = cipher.doFinal(src.getBytes());
		System.out.println("jdk aes encrypt: " + Base64.getEncoder().encodeToString(result));

		// 解密
		cipher.init(Cipher.DECRYPT_MODE, key);
		result = cipher.doFinal(result);
		System.out.println("jdk aes desrypt: " + new String(result));
	}

}
