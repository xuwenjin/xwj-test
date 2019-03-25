package other;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.SneakyThrows;

/**
 * 1、JSON Web Token (JWT)是一种基于 token 的认证方案
 * 2、简单的说，JWT就是一种Token的编码算法，服务器端负责根据一个密码和算法生成Token，然后发给客户端，客户端只负责后面每次请求都在HTTP
 * header里面带上这个Token，服务器负责验证这个Token是不是合法的，有没有过期等，并可以解析出subject和claim里面的数据。
 * 3、JWT里面的数据是BASE64编码的，没有加密，因此不要放如敏感数据
 * 
 * @author xuwenjin 2019年3月25日
 */
public class TokenUtils {

	private static Long timeLimit = 1000 * 60 * 60 * 24L;// 1天
	public static final String PRIVATEKEY = "privateKey";
	public static final String ACCESSTOKEN = "AccessToken";// 公私钥

	/**
	 * 生成token
	 * 
	 * 生成的token看起来是这样的: aaa.bbb.ccc，也就是base64(Header) + base64(Chaims) +
	 * base64(Signature)
	 * 
	 * @param subject   代表这个JWT的主体，即它的所有人。一般存放userId，userName之类的
	 * @param secretKey 秘钥
	 * @param claims    私有声明
	 */
	@SneakyThrows
	public static String createToken(String subject, String secretKey, Map<String, Object> claims) {
		byte[] secretKeyBytes = Base64.getDecoder().decode(secretKey.getBytes("utf-8"));
		String userToken = createToken(subject, secretKeyBytes, claims);
		return userToken;
	}

	private static String createToken(String subject, byte[] secretKeyBytes, Map<String, Object> claims) {
		String userToken = null;
		JwtBuilder builder = Jwts.builder().setSubject(subject)
				.setExpiration(new Date(System.currentTimeMillis() + timeLimit));
		if (claims != null) {
			for (String key : claims.keySet()) {
				builder.claim(key, claims.get(key));
			}
		}
		userToken = builder.signWith(SignatureAlgorithm.HS512, secretKeyBytes).compact();

		return userToken;
	}

	/**
	 * 解密token
	 * 
	 * @param token     生成的token字符串
	 * @param secretKey 秘钥
	 */
	@SneakyThrows
	public static Claims parserToken(String token, String secretKey) {
		byte[] bytes = Base64.getDecoder().decode(secretKey.getBytes("utf-8"));
		Claims claims = Jwts.parser().setSigningKey(bytes).parseClaimsJws(token).getBody();
		return claims;
	}

	/**
	 * 是否过期
	 */
	@SneakyThrows
	public static boolean isValid(String token, String secretKey) {
		Claims chaims = parserToken(token, secretKey);
		Integer exp = (Integer) chaims.get(Claims.EXPIRATION);
		return exp - System.currentTimeMillis() > 0;
	}

	public static void main(String[] args) {
		// 生成token
		String subject = "subject";
		String secretKey = "xuwenjin";
		Map<String, Object> claims = new HashMap<>();
		claims.put("userId", "xwj");
		String token = createToken(subject, secretKey, claims);
		System.out.println(token);
		System.out.println(token.length());

		// 解密token
		Claims chaims = parserToken(token, secretKey);
		System.out.println(chaims.getSubject());
		System.out.println(chaims.get("userId"));

		System.out.println(isValid(token, secretKey));

	}

}