package calc;

import org.junit.Test;

/**
 * 或运算、与运算、模运算
 */
public class AndOr {

	/**
	 * 与运算
	 */
	@Test
	public void testAnd() {
		int a = 15;
		System.out.println(a + "的二进制：" + Integer.toBinaryString(a));
		int b = 17;
		System.out.println(b + "的二进制：" + Integer.toBinaryString(b));

		System.out.println("a & b ---> " + (a & b));

		// b &= a 相当于 b = a & b
		b &= a;
		System.out.println("b &= a ---> " + (b));
	}

	/**
	 * 或运算
	 */
	@Test
	public void testOr() {
		int a = 15;
		System.out.println(a + "的二进制：" + Integer.toBinaryString(a));
		int b = 17;
		System.out.println(b + "的二进制：" + Integer.toBinaryString(b));

		System.out.println("a | b ---> " + (a | b));

		// b |= a 相当于 b = a | b
		b |= a;
		System.out.println("b |= a ---> " + (b));
	}

	/**
	 * 或运算
	 */
	@Test
	public void testOr2() {
		int MAXIMUM_CAPACITY = 1 << 30;

		// 模拟HashMap扩容
		int cap = 5;
		int n = cap - 1;
		System.out.println(n + "计算前的二进制：" + Integer.toBinaryString(n));
		n |= n >>> 1;
		System.out.println("n |= n >>> 1计算后的二进制：" + Integer.toBinaryString(n));
		n |= n >>> 2;
		System.out.println("n |= n >>> 2计算后的二进制：" + Integer.toBinaryString(n));
		n |= n >>> 4;
		System.out.println("n |= n >>> 4计算后的二进制：" + Integer.toBinaryString(n));
		n |= n >>> 8;
		System.out.println("n |= n >>> 8计算后的二进制：" + Integer.toBinaryString(n));
		n |= n >>> 16;
		System.out.println("n |= n >>> 16计算后的二进制：" + Integer.toBinaryString(n));
		n = (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
		System.out.println(n + "计算后的二进制：" + Integer.toBinaryString(n));
		System.out.println(n);

		// 结果：大于等于入参，并且最靠近入参且是2的幂次方
	}

	/**
	 * 模运算(取余)
	 */
	@Test
	public void test() {
		int a = 10;
		System.out.println(a + "的二进制：" + Integer.toBinaryString(a));
		int b = 3;
		System.out.println(b + "的二进制：" + Integer.toBinaryString(b));

		System.out.println("a % b ---> " + (a % b));

		// a %= b 相当于a = a % b
		a %= b;
		System.out.println("a %= b ---> " + (a));
	}

}
