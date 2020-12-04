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
		int num = 2;
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
		// int num = 7;
		int num = -7;
		System.out.println(num + "的二进制：" + Integer.toBinaryString(num));

		// num大于0时，右移，相当于num除以pow(2, n)
		print(num >>> 1);
		print(num >>> 2);
		print(num >>> 3);
	}

	/**
	 * 测试移位运算与四则运算效率
	 */
	@Test
	public void testSpeed() {
		int num = 10000 * 10000;

		long t1 = System.currentTimeMillis();
		for (int i = 0; i < num; i++) {
			int s = i * 4;
		}
		long t2 = System.currentTimeMillis();
		System.out.println("除法运算用时：" + (t2 - t1));

		for (int i = 0; i < num; i++) {
			int s = i >> 2;
		}

		long t3 = System.currentTimeMillis();
		System.out.println("右移运算用时：" + (t3 - t2));

	}

	private void print(int num) {
		System.out.println("十进制：" + num + ",  二进制：" + Integer.toBinaryString(num));
	}

}
