package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度
 */
public class LengthOfLongestSubstring {

	public static void main(String[] args) {
		String s = "abcab";
//		int longest = test1(s);
		int longest = test2(s);
		System.out.println(longest);
	}

	public static int test1(String s) {
		int longest = 0;
		int len = s.length();
		for (int i = 0; i < len; i++) {
			for (int j = i + 1; j <= len; j++) {
				if (allUnique(s, i, j)) {
					longest = Math.max(longest, j - i);
				}
			}
		}
		return longest;
	}

	private static boolean allUnique(String s, int start, int end) {
		Set<Character> set = new HashSet<Character>();
		for (int i = start; i < end; i++) {
			char c = s.charAt(i);
			if (set.contains(c)) {
				return false;
			}
			set.add(c);
		}
		return true;
	}

	public static int test2(String s) {
		int longest = 0;
		int len = s.length();
		Map<Character, Integer> map = new HashMap<>();
		for (int j = 0, i = 0; j < len; j++) {
			char jChar = s.charAt(j);
			if (map.containsKey(jChar)) {
				i = Math.max(map.get(jChar), i);
				System.out.println("i:" + i);
			}
			System.out.println("j:" + j + ", i:" + i);
			longest = Math.max(longest, j - i + 1);
			System.out.println("longest:" + longest);
			map.put(jChar, j + 1);
			System.out.println("map:" + map);
		}
		return longest;
	}

}
