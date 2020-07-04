package jre8Test;

/**
 * 测试传递方法
 */
public class TestFunction {

	public static void main(String[] args) {
		CommonTest test = new CommonTest();
		String result = test.testIn(() -> {
			return "哈哈哈哈";
		});
		System.out.println(result);
	}

}
