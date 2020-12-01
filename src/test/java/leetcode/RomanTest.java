package leetcode;

import java.util.HashMap;
import java.util.Map;

public class RomanTest {

	private static final String[] rnums = { "m", "cm", "d", "cd", "c", "xc", "l", "xl", "x", "Mx", "v", "Mv", "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

	private static final int[] anums = { 1000000, 900000, 500000, 400000, 100000, 90000, 50000, 40000, 10000, 9000, 5000, 4000, 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

	public static String toRomanNumber(int number) {
		if (number == 0)
			return "ZERO";
		if ((number < 0) || (number > 3999999)) {
			return "OVERFLOW";
		}

		StringBuilder output = new StringBuilder();
		for (int i = 0; (number > 0) && (i < anums.length); i++) {
			while (number >= anums[i]) {
				number -= anums[i];
				output.append(rnums[i]);
			}
		}
		return output.toString();
	}

	/**
	 * 罗马数字转阿拉伯数字
	 */
	public static int toArabicNumber(String romanNumber) {
		int number = 0;
		StringBuilder buffer = new StringBuilder(romanNumber);
		for (int i = 0; (buffer.length() > 0) && (i < anums.length); i++) {
			while (buffer.indexOf(rnums[i]) == 0) {
				number += anums[i];
				buffer.delete(0, rnums[i].length());
			}
		}
		return number;
	}

	/**
	 * 罗马数字转阿拉伯数字
	 */
	public static int romanToArabic(String romanNum) {
		if (romanNum == null || romanNum.length() == 0) {
			return 0;
		}

		Map<Character, Integer> romanArabicMap = new HashMap<>();
		romanArabicMap.put('I', 1);
		romanArabicMap.put('V', 5);
		romanArabicMap.put('X', 10);
		romanArabicMap.put('L', 50);
		romanArabicMap.put('C', 100);
		romanArabicMap.put('D', 500);
		romanArabicMap.put('M', 1000);

		int romanLen = romanNum.length();

		int res = iterR2L(romanArabicMap, romanNum, romanLen);
		// int res = iterL2R(map, romanNum);

		return res;
	}

	/**
	 * 右边往左边遍历
	 */
	public static int iterR2L(Map<Character, Integer> romanArabicMap, String romanNum, int romanLen) {
		// 1、校验romanNum中有非罗马字符，则直接返回0
		for (int i = 0; i < romanLen; i++) {
			if (!romanArabicMap.containsKey(romanNum.charAt(i))) {
				return 0;
			}
		}

		// 2、如果romanNum长度为1位，则直接返回对应数字
		int arabicTotal = romanArabicMap.get(romanNum.charAt(romanLen - 1)); // 最右侧罗马字符对应数字
		if (romanLen == 1) {
			return arabicTotal;
		}

		// 3、从右往左遍历，计算合计值。最右边第二个 --> 最左侧
		for (int i = romanLen - 2; i >= 0; i--) {
			int theArabicNum = romanArabicMap.get(romanNum.charAt(i)); // 当前罗马字符对应数字
			int rightArabicNum = romanArabicMap.get(romanNum.charAt(i + 1)); // 右侧罗马字符对应数字
			if (theArabicNum >= rightArabicNum) {
				arabicTotal += theArabicNum;
			} else {
				arabicTotal -= theArabicNum;
			}
		}

		return arabicTotal;
	}

	/**
	 * 左边往右边遍历
	 */
	public static int iterL2R(Map<Character, Integer> romanArabicMap, String romanNum, int romanLen) {
		// 最右侧罗马字符对应数字
		int res = romanArabicMap.get(romanNum.charAt(0));
		if (romanLen == 1) {
			return res;
		}

		// 从左往右遍历 MCM、MDC 最左侧 --> 最右侧
		for (int i = 1; i < romanLen - 1; i++) {
			int theNum = romanArabicMap.get(romanNum.charAt(i)); // 当前罗马字符对应数字
			int leftNum = romanArabicMap.get(romanNum.charAt(i - 1)); // 左侧罗马字符对应数字
			if (theNum >= leftNum) {
				res += (theNum - leftNum);
			}
		}
		return res;
	}

	public static void main(String[] args) {
		System.out.println("--------正常测试----------");
		System.out.println("IV ----> " + romanToArabic("IV")); // 4
		System.out.println("XLII ----> " + romanToArabic("XLII")); // 42
		System.out.println("XXXIX ----> " + romanToArabic("XXXIX")); // 39
		System.out.println("MCMXLIV  ----> " + romanToArabic("MCMXLIV")); // 1944
		System.out.println("MMVI ----> " + romanToArabic("MMVI")); // 2006
		System.out.println("MMIII ----> " + romanToArabic("MMIII")); // 2003
		System.out.println("MDC ----> " + romanToArabic("MDC")); // 2003

		System.out.println("--------异常测试----------");
		System.out.println(" ----> " + romanToArabic(""));
		System.out.println("IC ----> " + romanToArabic("IC"));
		System.out.println("XLII ----> " + romanToArabic("XLII"));
		System.out.println("AB ----> " + romanToArabic("AB")); // 上面的82行会出现空指针异常
	}

}
