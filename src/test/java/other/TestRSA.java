package other;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

import lombok.SneakyThrows;

/**
 * 非对称算法-RSA
 */
public class TestRSA {

	public static final String ALGORITHM = "RSA";

	public static void main(String[] args) {
		// 1、初始化密钥
		KeyPair keyPair = getKeyPair();
		RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
		String publicKeyStr = Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded());
		String privateKeyStr = Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded());
		System.out.println("Public Key: " + publicKeyStr);
		System.out.println("Private Key: " + privateKeyStr);

		PublicKey publicKey = getPublicKey(publicKeyStr);
		PrivateKey privateKey = getPrivateKey(privateKeyStr);

		// 公钥加密、私钥解密
		String data = "xwj security rsa";
		String encryptStr = publicEncrypt(data, publicKey);
		String decryptStr = privateDecrypt(encryptStr, privateKey);
		System.out.println("公钥加密、私钥解密-加密:" + encryptStr);
		System.out.println("公钥加密、私钥解密-解密:" + decryptStr);

		// 私钥加密、公钥解密
		String encryptStr2 = privateEncrypt(data, privateKey);
		String decryptStr2 = publicDecrypt(encryptStr2, publicKey);
		System.out.println("私钥加密、公钥解密-加密:" + encryptStr2);
		System.out.println("私钥加密、公钥解密-解密:" + decryptStr2);

		// 签名、验证
		String sign = sign(data, privateKey);
		boolean verify = verify(publicKey, "xwj security rsa", sign);
		System.out.println("签名:" + sign);
		System.out.println("验证:" + verify);
	}

	/**
	 * 公钥加密
	 */
	@SneakyThrows
	public static String publicEncrypt(String data, PublicKey publicKey) {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		byte[] result = cipher.doFinal(data.getBytes());
		return Base64.getEncoder().encodeToString(result);
	}

	/**
	 * 私钥解密
	 */
	@SneakyThrows
	public static String privateDecrypt(String data, PrivateKey privateKey) {
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(Base64.getDecoder().decode(data));
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
		byte[] decodedKey = Base64.getDecoder().decode(publicKey.getBytes());
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(decodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return keyFactory.generatePublic(x509EncodedKeySpec);
	}

	/**
	 * 获取私钥
	 */
	@SneakyThrows
	public static PrivateKey getPrivateKey(String privateKey) {
		byte[] decodedKey = Base64.getDecoder().decode(privateKey.getBytes());
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		return keyFactory.generatePrivate(keySpec);
	}

	/**
	 * 私钥对原始数据进行签名
	 */
	@SneakyThrows
	public static String sign(String data, PrivateKey privateKey) {
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initSign(privateKey);
		signature.update(data.getBytes());
		return new String(Base64.getEncoder().encodeToString(signature.sign()));
	}

	/**
	 * 公钥、原始数据、原始签名数据进行验证
	 */
	@SneakyThrows
	public static boolean verify(PublicKey publicKey, String data, String sign) {
		Signature signature = Signature.getInstance("MD5withRSA");
		signature.initVerify(publicKey);
		signature.update(data.getBytes());
		return signature.verify(Base64.getDecoder().decode(sign.getBytes()));
	}

}
