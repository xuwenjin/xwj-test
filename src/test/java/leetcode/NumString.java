package leetcode;

/**
 * 字符串中，连续相同字母，变成字母个数+字母。
 * 
 * 例如：
 * abbccc => a2b3c
 * 
 * @author xuwenjin 2021年1月19日
 */
public class NumString {

	public static void main(String[] args) {
		// String str = "abbcccd";
		String str = "abccddeeefghhsggiisi";
		System.out.println(test(str));
	}

	/**
	 * 解题分析：
	 * 
	 * 使用一个临时字符变量tmpChar记录当前出现的最新字符，如果又出现了新的字符，计算两次新字符之间的字符个数，就可以得到该字符重复次数
	 * 
	 * (暂时没有处理count=1的显示)
	 */
	public static String test(String str) {
		StringBuilder builder = new StringBuilder();

		int count = 1;
		char tmpChar = str.charAt(0);
		for (int i = 1; i < str.length(); i++) {
			char ch = str.charAt(i);
			if (ch == tmpChar) {
				// 当前字符与新字符一样时，表示字符重复，数量+1
				count++;
			} else {
				// 出现新字符时，打印之前字符的结果
				builder.append(count).append(tmpChar);
				// 出现新字符时，tmpChar记录该新字符
				tmpChar = ch;
				// 出现新字符时，记录数量为1
				count = 1;
			}
		}
		// 最后一个字符在遍历中是没有打印的，这里打印
		builder.append(count).append(tmpChar);

		return builder.toString();
	}

}
