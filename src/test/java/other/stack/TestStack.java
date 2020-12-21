package other.stack;

import java.util.Stack;

/**
 * 栈是Vector的一个子类，它实现了一个标准的后进先出。栈只定义了默认构造函数，用来创建一个空栈
 * 
 * @author xuwenjin 2020年12月21日
 */
public class TestStack {

	/**
	 * push：把新元素压入栈顶
	 */
	static void showpush(Stack<String> stack, String str) {
		stack.push(str);
		System.out.println("push(" + str + ")");
		// 结果 a -> a,b -> a,b,c
		System.out.println("stack: " + stack);
	}

	/**
	 * pop：移除栈顶的元素，并作为此函数的值返回(栈中没有元素时，会报EmptyStackException异常)。
	 */
	static void showpop(Stack<String> stack) {
		System.out.print("pop -> ");
		String a = (String) stack.pop();
		System.out.println(a);
		System.out.println("stack: " + stack);
	}

	/**
	 * pop：移除栈顶的元素，并作为此函数的值返回(栈中没有元素时，会报EmptyStackException异常)。
	 */
	static void showpeek(Stack<String> stack) {
		System.out.print("peek -> ");
		String a = (String) stack.peek();
		System.out.println(a);
		System.out.println("stack: " + stack);
	}

	public static void main(String args[]) {
		Stack<String> stack = new Stack<String>();
		System.out.println("stack: " + stack);
		showpush(stack, "a");
		showpush(stack, "b");
		showpush(stack, "c");
		System.out.println("--------------peek()--------------");
		showpeek(stack);
		showpeek(stack);
		showpeek(stack);
		System.out.println("--------------pop()--------------");
		showpop(stack);
		showpop(stack);
		showpop(stack);
		showpop(stack);
	}

}
