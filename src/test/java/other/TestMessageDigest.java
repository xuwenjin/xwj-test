package other;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 测试MessageDigest摘要算法(java自带的)
 * 
 * @author xuwenjin
 */
public class TestMessageDigest {

	/**
	 * 由于MD5 与SHA-1均是从MD4 发展而来，它们的结构和强度等特性有很多相似之处 SHA-1与MD5 的最大区别在于其摘要比MD5 摘要长
	 * 32 比特（1byte=8bit，相当于长4byte，转换16进制后比MD5多8个字符）。 对于强行攻击，：MD5 是2128
	 * 数量级的操作，SHA-1 是2160数量级的操作。 对于相同摘要的两个报文的难度：MD5是 264 是数量级的操作，SHA-1 是280
	 * 数量级的操作。 因而，SHA-1 对强行攻击的强度更大。 但由于SHA-1 的循环步骤比MD5 多（80:64）且要处理的缓存大（160
	 * 比特:128 比特），SHA-1 的运行速度比MD5 慢。
	 * 
	 * @param source
	 *            需要加密的字符串
	 * @param hashType
	 *            加密类型 （MD5 和 SHA）
	 * @return
	 */
	public static void main(String[] args) {
		getNum();
	}

	public static String getUUID() {
		UUID uuid = UUID.randomUUID(); 
		System.out.println(uuid.toString());
		return uuid.toString();
	}

	/**
	 * MD5加密算法
	 * 
	 * @return
	 */
	public static String getNum() {
		String numStr = "";
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			String guidStr = getUUID();
			md.update(guidStr.getBytes(), 0, guidStr.length()); // 使用指定的 byte数组，从指定的偏移量开始更新摘要。
			numStr = new BigInteger(1, md.digest()).toString(16); // 通过执行诸如填充之类的最终操作完成哈希计算
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		System.out.println(numStr);
		return numStr;
	}

}
