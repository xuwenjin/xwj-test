package other;

import java.security.Key;
import java.security.SecureRandom;
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
		long start = System.currentTimeMillis();
		SecretKey secretKey = initSecretKey();
		Key key = new SecretKeySpec(secretKey.getEncoded(), ALGORITHM);
		System.out.println("1111:" + (System.currentTimeMillis() - start));

		String data = "xwj security aes";
		String encodeStr = encode(key, data);
		System.out.println("2222:" + (System.currentTimeMillis() - start));
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

	@SneakyThrows
	public static byte[] encrypt(String content, String password) {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");// 创建AES的Key生产者
		kgen.init(128, new SecureRandom(password.getBytes()));// 利用用户密码作为随机数初始化出
		// 加密没关系，SecureRandom是生成安全随机数序列，password.getBytes()是种子，只要种子相同，序列就一样，所以解密只要有password就行
		SecretKey secretKey = kgen.generateKey();// 根据用户密码，生成一个密钥
		byte[] enCodeFormat = secretKey.getEncoded();// 返回基本编码格式的密钥，如果此密钥不支持编码，则返回
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");// 转换为AES专用密钥
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		byte[] byteContent = content.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化为加密模式的密码器
		byte[] result = cipher.doFinal(byteContent);// 加密
		return result;
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
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptBytes = cipher.doFinal(bytes);
		return new String(decryptBytes);
	}

}
