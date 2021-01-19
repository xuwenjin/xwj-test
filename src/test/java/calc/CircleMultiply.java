package calc;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * 阶乘
 * 
 * @author xuwenjin 2020年12月17日
 */
public class CircleMultiply {

	public static void main(String[] args) {
		System.out.println(calc(5));
		System.out.println(calc2(500));
		System.out.println(calc3(500));
		System.out.println(calc4(500));
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
		long t1 = System.currentTimeMillis();
		BigInteger sum = BigInteger.valueOf(1);
		for (int i = 1; i <= num; i++) {
			sum = sum.multiply(BigInteger.valueOf(i));
		}
		System.out.println("calc2--------->" + (System.currentTimeMillis() - t1));
		return sum;
	}

	/**
	 * BigDecimal可以计算很大的数
	 */
	private static BigDecimal calc3(int num) {
		BigDecimal sum = BigDecimal.valueOf(1);
		for (int i = 1; i <= num; i++) {
			sum = sum.multiply(BigDecimal.valueOf(i));
		}
		return sum;
	}

	/**
	 * BigInteger可以计算很大的数
	 */
	private static BigInteger calc4(int num) {
		long t1 = System.currentTimeMillis();
		if (num <= 0) {
			return BigInteger.valueOf(0);
		}

		int[] numArr = new int[num];
		for (int i = 0; i < num; i++) {
			numArr[i] = i + 1;
		}
		BigInteger sum = circleMultiply(numArr);
		System.out.println("calc4--------->" + (System.currentTimeMillis() - t1));
		return sum;
	}

	/**
	 * 阶乘(二分法)
	 */
	private static BigInteger circleMultiply(int[] numArr) {
		int len = numArr.length;
		if (len == 1) {
			return BigInteger.valueOf(numArr[0]);
		}
		if (len == 2) {
			return BigInteger.valueOf(numArr[0]).multiply(BigInteger.valueOf(numArr[1]));
		}

		int mid = len / 2;
		int[] leftNumArr = Arrays.copyOfRange(numArr, 0, mid);
		int[] rightNumArr = Arrays.copyOfRange(numArr, mid, len);
		return circleMultiply(leftNumArr).multiply(circleMultiply(rightNumArr));
	}

}
