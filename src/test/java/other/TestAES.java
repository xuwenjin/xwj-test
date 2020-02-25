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
		SecretKey secretKey = initSecretKey();
		Key key = new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);

		String data = "xwj security aes";
		String encodeStr = encode(key, data);
		String decodeStr = decode(key, encodeStr);
		System.out.println("加密:" + encodeStr);
		System.out.println("解密:" + decodeStr);

	}

	/**
	 * 初始化秘钥生成器
	 */
	@SneakyThrows
	public static SecretKey initSecretKey() {
		KeyGenerator keyGenerator = KeyGenerator.getInstance(ALGORITHM);
		keyGenerator.init(128); // 密钥的长度最少支持为128、192、256
		return keyGenerator.generateKey();
	}

	/**
	 * AES加密
	 * 
	 * @param key
	 *            秘钥
	 * @param data
	 *            被加密的数据
	 */
	@SneakyThrows
	public static String encode(Key key, String data) {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] bytes = cipher.doFinal(data.getBytes("UTF-8"));
		return Base64.getEncoder().encodeToString(bytes);
	}

	/**
	 * AES解密
	 * 
	 * @param key
	 *            秘钥
	 * @param data
	 *            加密后的字符串
	 */
	@SneakyThrows
	public static String decode(Key key, String data) {
		byte[] bytes = Base64.getDecoder().decode(data);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(2, key);
		byte[] decryptBytes = cipher.doFinal(bytes);
		return new String(decryptBytes);
	}

}
