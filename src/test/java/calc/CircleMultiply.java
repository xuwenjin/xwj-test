package calc;

import java.math.BigInteger;

/**
 * 阶乘
 * 
 * @author xuwenjin 2020年12月17日
 */
public class CircleMultiply {

	public static void main(String[] args) {
		System.out.println(calc(50));
		System.out.println(calc2(50));
	}

	/**
	 * 只能计算到long的最大值。是一个以9开头的十九位的数：*9223372036854775807 
	 */
	private static long calc(int num) {
		long sum = 1;
		for (int i = 1; i <= num; i++) {
			sum *= i;
		}
		return sum;
	}

	/**
	 * BigInteger可以计算很大的数
	 */
	private static BigInteger calc2(int num) {
		BigInteger sum = BigInteger.valueOf(1);
		for (int i = 1; i <= num; i++) {
			sum = sum.multiply(BigInteger.valueOf(num));
		}
		return sum;
	}

}
