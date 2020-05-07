package calc;

import org.junit.Test;

/**
 * 移位算法
 */
public class YiWei {

	/**
	 * 带符号左移右移
	 * 
	 * 位数左移之后，低位的补0
	 * 
	 * 如果被位移的数是负数，那么右移之后，高位全都补1；如果是正数，那么右移之后，高位全都补0。也就是正数依然是正数，负数依然是负数。
	 */
	@Test
	public void test1() {
		// int num = 7;
		int num = -7;
		System.out.println(num + "的二进制：" + Integer.toBinaryString(num));

		// num大于0时，右移，相当于num除以pow(2, n)
		print(num >> 1);
		print(num >> 2);
		print(num >> 3);

		// num大于0时，左移，相当于num乘以pow(2, n)
		print(num << 1);
		print(num << 2);
		print(num << 3);
	}

	/**
	 * 无符号右移
	 * 
	 * 无论是正数还是负数，在移位之后，高位都补0。即移位之后永远都是正数
	 */
	@Test
	public void test2() {
//		int num = 7;
		 int num = -7;
		System.out.println(num + "的二进制：" + Integer.toBinaryString(num));

		// num大于0时，右移，相当于num除以pow(2, n)
		print(num >>> 1);
		print(num >>> 2);
		print(num >>> 3);
	}

	private void print(int num) {
		System.out.println("十进制：" + num + ",  二进制：" + Integer.toBinaryString(num));
	}

}
