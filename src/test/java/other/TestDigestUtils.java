package other;

import java.nio.charset.Charset;

import org.junit.Test;
import org.springframework.util.DigestUtils;

/**
 * 测试DigestUtils
 * 
 * @author xwj
 */
public class TestDigestUtils {

	@Test
	public void testMd5() {
		String password = "123456中国";
		String dePwd = DigestUtils.md5DigestAsHex(password.getBytes());
		System.out.println("md5加密后字符2：" + dePwd);
		dePwd = DigestUtils.md5DigestAsHex(password.getBytes(Charset.forName("UTF-8")));
		System.out.println("md5加密后字符2：" + dePwd);
	}

}
