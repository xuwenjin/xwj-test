package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个Excel表格中的列名称，返回其相应的列序号。
 * 
 * 例如，
 *   A -> 1
 *   B -> 2
 *   C -> 3
 *   ...
 *   Z -> 26
 *   AA -> 27
 *   AB -> 28 
 * 
 * @author xuwenjin 2021年1月19日
 */
public class ExcelTitleToNum {

	public static void main(String[] args) {
		System.out.println(test2("A"));
		System.out.println(test2("Z"));
		System.out.println(test2("AA"));
		System.out.println(test2("AB"));
		System.out.println(test2("ZY"));
	}

	/**
	 * 初始化到Map中
	 */
	public static int test(String str) {
		Map<Character, Integer> map = new HashMap<>();
		map.put('A', 1);
		map.put('B', 2);
		map.put('C', 3);
		map.put('D', 4);
		map.put('E', 5);
		map.put('F', 6);
		map.put('G', 7);
		map.put('H', 8);
		map.put('I', 9);
		map.put('J', 10);
		map.put('K', 11);
		map.put('L', 12);
		map.put('M', 13);
		map.put('N', 14);
		map.put('O', 15);
		map.put('P', 16);
		map.put('Q', 17);
		map.put('R', 18);
		map.put('S', 19);
		map.put('T', 20);
		map.put('U', 21);
		map.put('V', 22);
		map.put('W', 23);
		map.put('X', 24);
		map.put('Y', 25);
		map.put('Z', 26);

		int wei = 0;
		int total = 0;
		for (int i = str.length() - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			total += map.get(ch) * Math.pow(26, wei++);
		}
		return total;
	}

	/**
	 * 使用字符相减
	 */
	public static int test2(String str) {
		int total = 0;

		int len = str.length();
		if (len == 1) {
			return str.charAt(0) - 'A' + 1;
		}

		int wei = 0;
		for (int i = len - 1; i >= 0; i--) {
			char ch = str.charAt(i);
			total += (ch - 'A' + 1) * Math.pow(26, wei++);
		}
		return total;
	}

}
