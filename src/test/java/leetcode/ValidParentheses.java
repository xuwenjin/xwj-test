package leetcode;

import java.util.Stack;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效
 * 
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 
 * 注意空字符串可被认为是有效字符串。
 *
 * @author xuwenjin 2020年12月21日
 */
public class ValidParentheses {

	public static void main(String[] args) {
		System.out.println(isValid("()[]{}"));
		System.out.println(isValid("(]"));
		System.out.println(isValid("([)]"));
		System.out.println(isValid("{[]}"));
	}

	public static boolean isValid(String str) {
		if (str == null || str.trim().length() == 0) {
			return true;
		}

		Stack<Character> stack = new Stack<>();
		for (char ch : str.toCharArray()) {
			if (ch == '(' || ch == '[' || ch == '{') {
				stack.push(ch);
			}
			if (ch == ')') {
				if (stack.isEmpty() || !stack.pop().equals('(')) {
					return false;
				}
			}
			if (ch == ']') {
				if (stack.isEmpty() || !stack.pop().equals('[')) {
					return false;
				}
			}
			if (ch == '}') {
				if (stack.isEmpty() || !stack.pop().equals('{')) {
					return false;
				}
			}
		}

		return stack.isEmpty();
	}

}
