package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试罗马数字与阿拉伯数字互转
 * 
 * 罗马数字共有7个，即Ⅰ（1）、Ⅴ（5）、Ⅹ（10）、Ⅼ（50）、Ⅽ（100）、Ⅾ（500）和Ⅿ（1000）
 * 
 * 罗马数字特点：
 *  1、重复次数：
 *	      一个罗马数字重复几次，就表示这个数的几倍。但是最多只允许连续出现三次(三次后被其它数值隔开是可以的)
 *	2、右加左减：	
 *	     在较大的罗马数字的右边记上较小的罗马数字，表示大数字加小数字。
 *	     在较大的罗马数字的左边记上较小的罗马数字，表示大数字减小数字。
 *	     左减的数字有限制，仅限于Ⅰ、Ⅹ、Ⅽ。比如45是ⅩⅬⅤ，不是ⅤⅬ。
 *	     左减时跨度不能超过两位。比如，99是ⅩⅭⅨ（ [100-10]+[10-1]），不是ⅠⅭ（100-1）。
 *	     左减数字必须为一位，比如8是Ⅷ，不是ⅡⅩ。
 *	     右加数字不连续超过三位，比如14是ⅪⅤ，不是ⅪⅢ。
 * 
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
		 int arabicTotal = right2Left(romanNum);

		// 从左往右遍历
//		int arabicTotal = left2Right(romanNum);

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

		// 1、判断romanNum中有没有非罗马字符，有则直接返回0
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
	 * 解题分析：
	 * 1、左减数字必须为一位，左减时跨度不能超过两位，且左减的数字仅限于Ⅰ、Ⅹ、Ⅽ，所以左减的情况只有：IV、IX、XL、XC、CD、CM
	 * 2、除了左减的情况，其它都是右加(单个罗马字符)。且左减的优先级高于右加，所以得先判断是否为左减
	 * 3、所有左减 + 所有右加，即可得到结果
	 */
	public static int left2Right(String romanNum) {
		// 1、所有左减(两个字符)、右加(单个字符)对应的阿拉伯数字，一起放入map中
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
		
		// 2、判断romanNum中有没有非罗马字符，有则直接返回0
		for (int i = 0; i < romanLen; i++) {
			if (!map.containsKey(romanNum.substring(i, i + 1))) {
				return 0;
			}
		}

		// 3、如果romanNum长度为1位，则直接返回对应数字
		if (romanLen == 1) {
			return map.get(romanNum);
		}

		int index = 0;
		int arabicTotal = 0; // 结果
		while (index < romanLen) {
			if (index < romanLen - 1 && map.containsKey(romanNum.substring(index, index + 2))) {
				// a、先判断左减在map中是否存在，存在则将值取出加到结果中，并向后移2个字符 --> 可以减少遍历次数
				arabicTotal += map.get(romanNum.substring(index, index + 2));
				index += 2;
			} else {
				// b、不存在则为右加，将值取出加到结果中，并向后移 1 个字符
				arabicTotal += map.get(romanNum.substring(index, index + 1));
				index++;
			}
		}
		return arabicTotal;
	}

	/**
	  * 阿拉伯数字转换为罗马数字
	  * 
	  * 解题分析：
	  * 1、从阿拉伯数字的最高位开始，寻找最适合(最接近且小于)它的罗马字符(一个字符(右加)或者两个字符(左减))
	  * 2、找到后，减去它得到余数，再寻找最适合余数的罗马字符，依此类推，直到余数为0
	  * 3、取出的每个罗马字符，依次(从最高位到低位)拼接到输出的罗马数字字符串上
	  * 
	  * 例子：
	  * 1425 --> 1000 + 400 + 20 + 5 --> 1000 + 400 + 10 + 10 + 5 --> M + CD + X + X + V
	  * 643 --> 600 + 40 + 3 --> 500 + 100 + 40 + 3 --> 500 + 100 + 40 + 1 + 1 + 1 --> D + C + XL + I + I + I
	  */
	public static String arabic2Roman(int arabicNum) {
		int[] values =    { 1000, 900, 500, 400,  100, 90,   50,  40,   10,  9,    5,   4,    1 };
		String[] romans = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		if (arabicNum < 1 || arabicNum > 3999) {
			// 暂不处理不在1~3999这个范围外的数据
			return null;
		}

		StringBuilder romanNum = new StringBuilder();
		// 直到余数等于0
		for (int i = 0; i < romans.length && arabicNum > 0; i++) {
			while (arabicNum >= values[i]) {
				// 寻找最适合(最接近且小于)它的数字，然后将对应罗马字符拼接
				arabicNum -= values[i];
				romanNum.append(romans[i]);
			}
		}

		return romanNum.toString();
	}

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap<>();
		map.put("C", 100);
		System.out.println(map.containsKey('C'));
		System.out.println(map.containsKey("C"));
		
		// System.out.println("--------正常测试----------");
		 System.out.println("C ----> " + romanToArabic("C")); // 100
		// System.out.println("IV ----> " + romanToArabic("IV")); // 4
		// System.out.println("XLII ----> " + romanToArabic("XLII")); // 42
		// System.out.println("XXXIX ----> " + romanToArabic("XXXIX")); // 39
		// System.out.println("MCMXLIV ----> " + romanToArabic("MCMXLIV")); //
		// 1944
		// System.out.println("MMVI ----> " + romanToArabic("MMVI")); // 2006
		// System.out.println("MMIII ----> " + romanToArabic("MMIII")); // 2003
		// System.out.println("MDC ----> " + romanToArabic("MDC")); // 2003
		//
		// System.out.println("--------异常测试----------");
		// System.out.println(" ----> " + romanToArabic(""));
		// System.out.println("IC ----> " + romanToArabic("IC"));
		// System.out.println("XLII ----> " + romanToArabic("XLII"));
		// System.out.println("AB ----> " + romanToArabic("AB")); //

		// 阿拉伯数字转换为罗马数字
		System.out.println("1425 ----> " + arabic2Roman(1425)); // MCDXXV
		System.out.println("643 ----> " + arabic2Roman(643)); // DCXLIII
	}

}
