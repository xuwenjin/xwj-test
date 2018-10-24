package other;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
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

	public static String src = "xwj security rsa";

	public static void main(String[] args) {
		jdkRSA();
	}

	@SneakyThrows
	public static void jdkRSA() {
		// 1、初始化密钥
		KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
		keyPairGenerator.initialize(512);
		KeyPair keyPair = keyPairGenerator.generateKeyPair();
		RSAPublicKey rsaPublicKey = (RSAPublicKey) keyPair.getPublic();
		RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) keyPair.getPrivate();
		System.out.println("Public Key: " + Base64.getEncoder().encodeToString(rsaPublicKey.getEncoded()));
		System.out.println("Private Key: " + Base64.getEncoder().encodeToString(rsaPrivateKey.getEncoded()));

		// 2、私钥加密、公钥解密-加密
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		byte[] result = cipher.doFinal(src.getBytes());
		System.out.println("私钥加密、公钥解密-加密:" + Base64.getEncoder().encodeToString(result));

		// 3、私钥加密、公钥解密-解密
		X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
		keyFactory = KeyFactory.getInstance(ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		result = cipher.doFinal(result);
		System.out.println("私钥加密、公钥解密-解密:" + new String(result));

		// 4、公钥加密、私钥解密-加密
		x509EncodedKeySpec = new X509EncodedKeySpec(rsaPublicKey.getEncoded());
		keyFactory = KeyFactory.getInstance(ALGORITHM);
		publicKey = keyFactory.generatePublic(x509EncodedKeySpec);
		cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		result = cipher.doFinal(src.getBytes());
		System.out.println("公钥加密、私钥解密-加密:" + Base64.getEncoder().encodeToString(result));

		// 5、公钥加密、私钥解密-解密
		pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(rsaPrivateKey.getEncoded());
		keyFactory = KeyFactory.getInstance(ALGORITHM);
		privateKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);
		cipher = Cipher.getInstance(ALGORITHM);
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		result = cipher.doFinal(result);
		System.out.println("公钥加密、私钥解密-解密:" + new String(result));
	}

}
