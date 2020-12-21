package other.stack;

import java.util.Stack;

/**
 * Stack应用场景---使用stack做符号匹配
 */
public class CheckExpression {

	public static boolean isValid(String expstr) {
		// 创建栈
		Stack<String> stack = new Stack<>();

		int i = 0;
		while (i < expstr.length()) {
			char ch = expstr.charAt(i);
			i++;
			switch (ch) {
			case '(':
				// 如果是左括号，直接入栈
				stack.push(ch + "");
				break;
			case ')':
				if (stack.isEmpty()) {
					// 如果当前字符是右括号，且栈为空，表明该字符前没有左括号，则直接返回false
					return false;
				}
				if (!stack.pop().equals("(")) {
					// 如果当前字符是右括号，且栈顶的元素不是左括号，则直接返回false
					return false;
				}
			}
		}

		return stack.isEmpty();
	}

	public static void main(String args[]) {
		System.out.println("(()" + " ----> " + isValid("(()"));
		System.out.println("(())" + " ----> " + isValid("(())"));
		System.out.println("(()))" + " ----> " + isValid("(()))"));
	}

}