package leetcode;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * 
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 
 * @author xuwenjin 2020年12月21日
 */
public class LongestCommonPrefix {

	public static void main(String[] args) {
		String[] strArr = { "flower", "flow", "flight" };
		System.out.println(longestPrefix(strArr));
	}

	public static String longestPrefix(String[] strArr) {
		if (strArr == null || strArr.length == 0) {
			return "";
		}

		String commonPrefix = strArr[0];
		for (int i = 1; i < strArr.length; i++) {
			commonPrefix = longestPrefixTwoStr(commonPrefix, strArr[i]);
			if (commonPrefix.length() == 0) {
				// 如果当前前缀是空，则直接跳出
				break;
			}
		}
		return commonPrefix;
	}

	/**
	 * 从str1和str2中取最长前缀
	 */
	public static String longestPrefixTwoStr(String str1, String str2) {
		int minLen = Math.min(str1.length(), str2.length()); // 只需要遍历最短的那个字符串就行

		int index = 0;
		for (int i = 0; i < minLen; i++) {
			if (str1.charAt(i) != str2.charAt(i)) {
				break;
			}
			index++;
		}

		return str1.substring(0, index);
	}

}
