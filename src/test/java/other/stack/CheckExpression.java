package other.stack;

import java.util.Stack;

/**
 * 使用stack做符号匹配
 */
public class CheckExpression {

	public static String isValid(String expstr) {
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
				// 如果是右括号
				if (stack.isEmpty() || !stack.pop().equals("(")) {
					// 当前栈为空或者栈中无左括号，则直接返回
					return "(";
				}
			}
		}
		// 最后检测是否为空,为空则检测通过
		if (stack.isEmpty())
			return "check pass!";
		else
			return "check exception!";
	}

	public static void main(String args[]) {
		String expstr = "((5-3)*8-2)";
		String expstr2 = "(5-3)*(8-2)";
		System.out.println(expstr + "  " + isValid(expstr2));
	}

}