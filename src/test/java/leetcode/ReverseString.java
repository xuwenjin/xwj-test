package leetcode;

/**
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 * 
 * 输入：['h','e','l','l','o']
 * 输出：['o','l','l','e','h']
 * 
 * 输入：['H','a','n','n','a','h']
 * 输出：['h','a','n','n','a','H']
 * 
 * @author xuwenjin 2021年1月19日
 */
public class ReverseString {

	public static void main(String[] args) {
		// char[] charArr = { 'h', 'e', 'l', 'l', 'o' };
		char[] charArr = { 'H', 'a', 'n', 'n', 'a', 'h' };
		test(charArr);
		System.out.println(charArr);
	}

	/**
	 * 解题分析：
	 */
	public static void test(char[] charArr) {
		int len = charArr.length;
		int index = len - 1;
		for (int i = 0; i < len / 2; i++) {
			// 前后字符交换
			char temp = charArr[i];
			charArr[i] = charArr[index];
			charArr[index] = temp;
			index--;
		}
	}

}
