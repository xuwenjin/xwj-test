package util;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;

import lombok.SneakyThrows;

/**
 * 非对称算法(RSA)工具类
 */
public class RSAUtil {

	private static final String ALGORITHM = "RSA";
	private static final int KEY_SIZE = 1024;
	public static final String PUB_KEY = "pub";
	public static final String PRI_KEY = "pri";

	/**
	 * 初始化秘钥生成器
	 */
	@SneakyThrows
	public static KeyPair initKeyPair() {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
		generator.initialize(KEY_SIZE);
		return generator.generateKeyPair();
	}

	/**
	 * 生成秘钥
	 */
	@SneakyThrows
	public static Map<String, String> generateKey() {
		KeyPair keyPair = initKeyPair();
		byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
		byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
		Map<String, String> keyMap = new HashMap<>();
		keyMap.put(PUB_KEY, Base64Util.encode(publicKeyBytes));
		keyMap.put(PRI_KEY, Base64Util.encode(privateKeyBytes));
		return keyMap;
	}

	/**
	 * 公钥加密
	 */
	@SneakyThrows
	public static String publicEncrypt(String data, String publicKey) {
		PublicKey key = getPublicKey(publicKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] result = cipher.doFinal(data.getBytes());
		return Base64Util.encode(result);
	}

	/**
	 * 私钥解密
	 */
	@SneakyThrows
	public static String privateDecrypt(String data, String privateKey) {
		PrivateKey key = getPrivateKey(privateKey);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] result = cipher.doFinal(Base64Util.decode(data));
		return new String(result);
	}

	/**
	 * 私钥加密
	 */
	@SneakyThrows
	public static String privateEncrypt(String data, PrivateKey privateKey) {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(result);
	}

	/**
	 * 公钥解密
	 */
	@SneakyThrows
	public static String publicDecrypt(String data, PublicKey publicKey) {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(Base64.getDecoder().decode(data));
		return new String(result);
	}

	/**
	 * 获取秘钥生成器
	 */
	@SneakyThrows
	public static KeyPair getKeyPair() {
		KeyPairGenerator generator = KeyPairGenerator.getInstance(ALGORITHM);
		generator.initialize(1024);
		return generator.generateKeyPair();
	}

	/**
	 * 获取公钥
	 */
	@SneakyThrows
	public static PublicKey getPublicKey(String publicKey) {
		byte[] decodedKey = Base64Util.decode(publicKey);
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return keyFactory.generatePublic(x509EncodedKeySpec);
	}

	/**
	 * 获取私钥
	 */
	@SneakyThrows
	public static PrivateKey getPrivateKey(String privateKey) {
		byte[] decodedKey = Base64Util.decode(privateKey.getBytes());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return keyFactory.generatePrivate(keySpec);
	}

	public static void main(String[] args) {
		// 生成公钥私钥
		Map<String, String> keyMap = generateKey();
		System.out.println("Public Key: " + keyMap.get(PUB_KEY));
		System.out.println("Private Key: " + keyMap.get(PRI_KEY));
	}

}
