package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，找到最长不含有重复字符的子字符串
 */
class LongestSubstring {

	public static void main(String[] args) {
		// String str = "abc";
		// String str = "abcabcbb";
		// String str = "cdd";
		String str = "abba";
		System.out.println(test(str));
	}

	/**
	 * 解题分析：
	 * 1、滑动窗口。
	 * 其实就是一个队列，比如abcbcba，进入这个队列（窗口）为 abc 满足题目要求，当再进入 b，队列变成了 abcb，这时不满足要求。所以，我们要移动这个队列。
	 * 移动队列方法：将队列中第一次出现b及b之前的所有元素移除
	 */
	public static int test(String str) {
		Set<Character> set = new HashSet<>();
		int len = str.length();
		if (len <= 1) {
			return len;
		}

		int max = 0;
		int left = 0;
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			while (set.contains(c)) {
				// 记录最近出现的重复元素的下标+1。并删除当前重复元素及之前的所有元素
				set.remove(str.charAt(left++));
			}
			set.add(c);
			max = Math.max(max, set.size());
		}
		return max;
	}

	/**
	 * 解题分析：
	 * 1、滑动窗口。
	 * 其实就是一个队列，比如abcbcba，进入这个队列（窗口）为 abc 满足题目要求，当再进入 b，队列变成了 abcb，这时不满足要求。所以，我们要移动这个队列。
	 * 移动队列方法：将队列中第一次出现b及b之前的所有元素移除
	 */
	public static int test2(String str) {
		HashMap<Character, Integer> map = new HashMap<>();
		int len = str.length();
		if (len <= 1) {
			return len;
		}

		int max = 0;
		int left = 0;
		for (int i = 0; i < len; i++) {
			char c = str.charAt(i);
			if (map.containsKey(c)) {
				// 记录最近出现的重复元素的下标+1
				// 这里使用Math.max，是为了防止当前重复元素的下标比现有重复元素小的情况。比如abba
				left = Math.max(left, map.get(c) + 1);
			}
			map.put(c, i);

			// 计算两个下标间子字符串长度时，是没有算边界的，所以这里的+1
			max = Math.max(max, i - left + 1);
		}
		return max;
	}

}