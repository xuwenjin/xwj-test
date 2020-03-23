package other;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;
import util.Base64Util;
import util.RSAUtil;

/**
 * 测试Jwt
 * 
 * 1、JSON Web Token (JWT)是一种基于 token 的认证方案
 * 2、简单的说，JWT就是一种Token的编码算法，服务器端负责根据一个密码和算法生成Token，然后发给客户端，客户端只负责后面每次请求都在HTTP
 * header里面带上这个Token，服务器负责验证这个Token是不是合法的，有没有过期等，并可以解析出subject和claim里面的数据。
 * 3、JWT里面的数据是BASE64编码的，没有加密，因此不要放如敏感数据
 * 4、生成的token看起来是这样的:aaa.bbb.ccc，也就是base64(Header)+base64(playload)+base64(Signature)
 * 
 * RS256和HS256的区别可参考：https://blog.csdn.net/qq_38741963/article/details/90272276
 */
public class TestJwt {

	/** 过期时间 */
	private static final long EXPIRE = 30L;// 1小时
	/** 秘钥 */
	private static final String SECRET = "xuwenjin";

	/**
	 * 生成token(RS256加密 + 标准声明(建议但不强制使用))
	 * 
	 * @param privateKey
	 *            RSA私钥
	 */
	@SneakyThrows
	public static String generateTokenByPriKey(String id, String subject, String privateKey) {
		/**
		 * iss(Issuer): jwt签发者
		 * 
		 * sub: jwt所面向的用户
		 * 
		 * aud: 接收jwt的一方
		 * 
		 * exp: jwt的过期时间，这个过期时间必须要大于签发时间
		 * 
		 * nbf: 定义在什么时间之前，该jwt都是不可用的.
		 * 
		 * iat: jwt的签发时间
		 * 
		 * jti: jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击。
		 */
		Date now = new Date();
		Date expireDate = new Date(System.currentTimeMillis() + EXPIRE);
		JwtBuilder builder = Jwts.builder();
		String compactJws = builder.setId(id) // jwt的唯一身份标识，主要用来作为一次性token,从而回避重放攻击
				.setIssuer("provider")// jwt签发者
				.setSubject(subject) // jwt所面向的用户
				.setAudience("consumer") // 接收jwt的一方
				.setIssuedAt(now) // jwt的签发时间
				.setExpiration(expireDate) // jwt的过期时间，这个过期时间必须要大于签发时间
				.setNotBefore(now) // 当前时间之前不可用
				.signWith(SignatureAlgorithm.RS256, RSAUtil.getPrivateKey(privateKey)).compact();

		return compactJws;
	}

	/**
	 * RSA公钥解析token
	 * 
	 * @param pubKey
	 *            RSA公钥
	 */
	@SneakyThrows
	public static Claims parserTokenByPubKey(String token, String pubKey) {
		return Jwts.parser().setSigningKey(RSAUtil.getPublicKey(pubKey)).parseClaimsJws(token).getBody();
	}

	/**
	 * 是否过期
	 */
	@SneakyThrows
	public static boolean isExpirateByPubKey(String token, String pubKey) {
		Claims chaims = parserTokenByPubKey(token, pubKey);
		Integer exp = (Integer) chaims.get(Claims.EXPIRATION);
		long now = (long) System.currentTimeMillis() / 1000;
		return now - exp > 0;
	}

	/**
	 * 生成token(HS256加密 + 私有声明)
	 * 
	 * @param secretKey
	 *            固定秘钥
	 * @param claims
	 *            私有声明
	 */
	@SneakyThrows
	public static String generateToken(String subject, String secretKey, Map<String, Object> claims) {
		byte[] secretKeyBytes = Base64Util.decode(secretKey.getBytes("utf-8"));

		JwtBuilder builder = Jwts.builder().setSubject(subject)
				.setExpiration(DateTime.now().plusSeconds((int) EXPIRE).toDate());
		if (claims != null) {
			for (String key : claims.keySet()) {
				builder.claim(key, claims.get(key));
			}
		}
		return builder.signWith(SignatureAlgorithm.HS256, secretKeyBytes).compact();
	}

	/**
	 * 解密token
	 * 
	 * @param token
	 *            生成的token字符串
	 * @param secretKey
	 *            固定秘钥
	 */
	@SneakyThrows
	public static Claims parserToken(String token, String secretKey) {
		byte[] bytes = Base64Util.decode(secretKey.getBytes("utf-8"));
		return Jwts.parser().setSigningKey(bytes).parseClaimsJws(token).getBody();
	}

	/**
	 * 是否过期
	 */
	@SneakyThrows
	public static boolean isExpirate(String token, String secretKey) {
		Claims chaims = parserToken(token, secretKey);
		Integer exp = (Integer) chaims.get(Claims.EXPIRATION);
		long now = (long) System.currentTimeMillis() / 1000;
		System.out.println("exp:" + exp + ", now:" + now);
		return now - exp > 0;
	}

	/**
	 * 测试(RS256加密 + 标准声明(建议但不强制使用))
	 */
	@Test
	public void test1() {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC+A+RXQMh7nEpKaOclUyrlQr4l4sT99QnyvM2/hRotFVLyvMXsgrXweWm9N5RU4NVnergdSn143uvYp5Ec6sHTu+s/MwHGG/d7EJR8up8GJHMtHkW44sGiYerl26lZxFo3AFrmrOSLELds5MBM+5CgCHKqyiYxxmvIiEuoemB4CQIDAQAB";
		String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAL4D5FdAyHucSkpo5yVTKuVCviXixP31CfK8zb+FGi0VUvK8xeyCtfB5ab03lFTg1Wd6uB1KfXje69inkRzqwdO76z8zAcYb93sQlHy6nwYkcy0eRbjiwaJh6uXbqVnEWjcAWuas5IsQt2zkwEz7kKAIcqrKJjHGa8iIS6h6YHgJAgMBAAECgYB5cUdZNMBtsGAS2qUQx+zchiG9WQgKP7hR+bWOOGWYds6C8X+WP5xEy3R3SLR24xPBhT9zCQ7UV7VozQAD+U5TBuDgsSWOtg3tnBHuTtwBK890ZbEv6z2EUP2QoK7iP+v5lJI1nTJpZtZ65OVKQv9GHeQ3TJp1ae27KT2svey/WQJBAPnoY2PTAODjbdM+l8pLml0f2Vd83nzVXK8OmQbkOdUQv7B+yHxt6zjGq9UMeOudnYObPFdS/81oRN/YGaZpSm8CQQDCpbqu3KDbBVxmZchhJfYHfvCiujMHfIa81fVYTK3rdahZlDR9J4ifh2yvbYXnCh4yyDfKpCVw67hr7U49mwEHAkAGe4S4fiyzqLKcnC8LzFJAwCa/IjoTOuWglNxbVWg6oqiWR3Oj5qYHXv/uEtjAI+KGG2zBRyHjjiTbOZvQuUJ5AkEAqSfRtsjx2aUtCagGnbaZuyXsBd7/HdBwX4cpMlVhB7E2XrLXcrR6nPjZ0RLDPWejmso5AhfomdugZ9rRFeSw3QJADB2KQH5Nt7bhLedJvNthEqtqVE6zuXBraiOGWwYsFSv5h109AgeuVZQZiY7Sl2WUnYHIH//003dllZRwwdzl5A==";

		String subject = "mySubject";
		String token = generateTokenByPriKey("myid", subject, privateKey);
		System.out.println(token);

		// 解密token
		Claims chaims = parserTokenByPubKey(token, publicKey);
		System.out.println("id: " + chaims.getId());
		System.out.println("subject: " + chaims.getSubject());
		System.out.println("issuer: " + chaims.getIssuer());
		System.out.println("chaims: " + chaims);

		// 是否过期
		System.out.println("isExpirate: " + isExpirateByPubKey(token, publicKey));
	}

	/**
	 * 测试(HS256加密 + 私有声明)
	 */
	@Test
	public void test2() {
		// 由于时间戳不一样，每次生成token都不同
		String subject = "mySubject";
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", "1");
		claims.put("userName", "xwj");
		String token = generateToken(subject, SECRET, claims);
		System.out.println(token);
	}

	/**
	 * 测试解密
	 */
	@Test
	public void testParseToken() {
		/**
		 * 如果token已过期，则会报过期异常
		 */
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJteVN1YmplY3QiLCJleHAiOjE1ODQ5Mjc1MDQsInVzZXJOYW1lIjoieHdqIiwidXNlcklkIjoiMSJ9.Ys06PeJMqRHVMbNmUYO0PZn2K591wh4opLJmVlK-em0";
		// 解密token
		Claims chaims = parserToken(token, SECRET);
		System.out.println("subject: " + chaims.getSubject());
		System.out.println("chaims: " + chaims);

		// 是否过期
		System.out.println("isExpirate: " + isExpirate(token, SECRET));
	}

	/**
	 * 测试解密jwt第一二部分
	 */
	@Test
	public void testParsePlayload() {
		/**
		 * 如果token已过期，则会报过期异常
		 */
		String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJteVN1YmplY3QiLCJleHAiOjE1ODQ5Mjc1MDQsInVzZXJOYW1lIjoieHdqIiwidXNlcklkIjoiMSJ9.Ys06PeJMqRHVMbNmUYO0PZn2K591wh4opLJmVlK-em0";
		String[] tokenArr = token.split("\\.");
		String header = tokenArr[0];
		String playload = tokenArr[1];
		System.out.println("header:" + header);
		byte[] headerBytes = Base64Util.decode(header);
		System.out.println("header解密：" + new String(headerBytes));

		System.out.println("playload:" + playload);
		byte[] playloadBytes = Base64Util.decode(playload);
		System.out.println("playload解密：" + new String(playloadBytes));
	}

}