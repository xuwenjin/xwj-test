package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试罗马数字与阿拉伯数字互转
 */
public class RomanAndArabic {

	/**
	 * 罗马数字转阿拉伯数字(从左往右，效率可能会更高)
	 * 
	 * romanNum为空或者包含非罗马字符返回0
	 */
	public static int romanToArabic(String romanNum) {
		if (romanNum == null || romanNum.length() == 0) {
			return 0;
		}

		// 从右往左遍历
		// int arabicTotal = right2Left(romanNum);

		// 从左往右遍历
		int arabicTotal = left2Right(romanNum);

		return arabicTotal;
	}

	/**
	 * 从右往左遍历
	 * 
	 * 一位一位的遍历，每次将当前位与右边一位相比，如果大于则相加，否则相减
	 */
	public static int right2Left(String romanNum) {
		Map<Character, Integer> romanArabicMap = new HashMap<>(8);
		romanArabicMap.put('I', 1);
		romanArabicMap.put('V', 5);
		romanArabicMap.put('X', 10);
		romanArabicMap.put('L', 50);
		romanArabicMap.put('C', 100);
		romanArabicMap.put('D', 500);
		romanArabicMap.put('M', 1000);

		int romanLen = romanNum.length();

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
				// 如果当前数字比右边大，则相加
				arabicTotal += theArabicNum;
			} else {
				// 如果当前数字比右边小，则相减
				arabicTotal -= theArabicNum;
			}
		}

		return arabicTotal;
	}

	/**
	 * 从左往右遍历
	 * 
	 * 左减数字必须为一位，比如8是Ⅷ，不用ⅡⅩ。--> 组合只有两种，一种是 1 个字符，一种是 2 个字符，其中 2 个字符优先于 1 个字符(可能是右边减去左边，所有必须先看两位)
	 * 右加数字不连续超过三位，比如14是ⅪⅤ，不用ⅪⅢ。 
	 * 
	 * 先判断两个字符的在map中是否存在，存在则从map中取出对应数字并相加，再向后移2个字符。不存在则从map中取出当前字符对应数字并相加，再向后移 1 个字符
	 */
	public static int left2Right(String romanNum) {
		Map<String, Integer> map = new HashMap<>();
		map.put("I", 1);
		map.put("IV", 4);
		map.put("V", 5);
		map.put("IX", 9);
		map.put("X", 10);
		map.put("XL", 40);
		map.put("L", 50);
		map.put("XC", 90);
		map.put("C", 100);
		map.put("CD", 400);
		map.put("D", 500);
		map.put("CM", 900);
		map.put("M", 1000);

		int romanLen = romanNum.length();

		int arabicTotal = 0;
		for (int i = 0; i < romanLen;) {
			if (i < romanLen - 1 && map.containsKey(romanNum.substring(i, i + 2))) {
				// 先判断两个字符的在map中是否存在，存在则从map中取出对应数字并相加，再向后移2个字符 --> 可以减少遍历次数
				arabicTotal += map.get(romanNum.substring(i, i + 2));
				i += 2;
			} else {
				// 不存在则从map中取出当前字符对应数字并相加，再向后移 1 个字符
				arabicTotal += map.get(romanNum.substring(i, i + 1));
				i++;
			}
		}

		return arabicTotal;
	}

	/**
	  * 阿拉伯数字转换为罗马数字
	  */
	public static String arabic2Roman(int arabicNum) {
		int[] arabicArr =   { 1000, 900, 500, 400,  100,  90,  50,  40,   10,   9,    5,   4,    1 };
		String[] romanArr = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		String romanNum = "";
		if (arabicNum < 1 || arabicNum > 3999) {
			romanNum = "-1";
		}

		for (int i = 0; i < romanArr.length; i++) {
			// 将num转为从最高位到最低位的一个个罗马组合(一个字符或者两个字符)
			// 例如：1425 = 1000 + 400 + 20 + 5 --> M + CD + XX + V
			while (arabicNum >= arabicArr[i]) {
				romanNum += romanArr[i];
				arabicNum -= arabicArr[i];
			}
		}
		
		return romanNum;
	}

	public static void main(String[] args) {
//		System.out.println("--------正常测试----------");
//		System.out.println("IV ----> " + romanToArabic("IV")); // 4
//		System.out.println("XLII ----> " + romanToArabic("XLII")); // 42
//		System.out.println("XXXIX ----> " + romanToArabic("XXXIX")); // 39
//		System.out.println("MCMXLIV  ----> " + romanToArabic("MCMXLIV")); // 1944
//		System.out.println("MMVI ----> " + romanToArabic("MMVI")); // 2006
//		System.out.println("MMIII ----> " + romanToArabic("MMIII")); // 2003
//		System.out.println("MDC ----> " + romanToArabic("MDC")); // 2003
//
//		System.out.println("--------异常测试----------");
//		System.out.println(" ----> " + romanToArabic(""));
//		System.out.println("IC ----> " + romanToArabic("IC"));
//		System.out.println("XLII ----> " + romanToArabic("XLII"));
//		System.out.println("AB ----> " + romanToArabic("AB")); // 上面的82行会出现空指针异常
		
		// 阿拉伯数字转换为罗马数字
		System.out.println("1425 ----> " + arabic2Roman(1425)); // MCDXXV
	}

}
