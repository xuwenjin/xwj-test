package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 算术-字符串
 * 
 * @author xwj
 *
 */
class AlgStr {

	/**
	 * 给定一个字符串，找到最长不含有重复字符的子字符串
	 */
	public static int lengthOfLongestSubstring(String s) {
		Set<Character> chars = new HashSet<>();
		int max = 0;
		for (int i = 0, j = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			while (chars.contains(c)) {
				chars.remove(s.charAt(j++));
			}
			chars.add(c);
			max = Math.max(max, i - j + 1);
		}
		return max;
	}

	public static int lengthOfLongestSubstring2(String s) {
		int n = s.length(), ans = 0;
		Map<Character, Integer> map = new HashMap<>();
		// try to extend the range [i, j]
		for (int j = 0, i = 0; j < n; j++) {
			if (map.containsKey(s.charAt(j))) {
				i = Math.max(map.get(s.charAt(j)), i);
			}
			ans = Math.max(ans, j - i + 1);
			map.put(s.charAt(j), j + 1);
		}
		return ans;
	}

	public static void main(String[] args) {
		String s = "abcacbb";
		int maxLen = lengthOfLongestSubstring2(s);
		System.out.println(maxLen);
	}

}