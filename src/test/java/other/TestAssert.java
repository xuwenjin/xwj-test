package other;

/**
 * 测试java关键字assert(断言)
 */
public class TestAssert {

	public static void main(String[] args) {
		test1(0);
	}

	private static void test1(int a) {
		// assert 关键字为断言
		// 如果[boolean表达式]为true，则程序继续执行。
		// 如果为false，则程序抛出AssertionError，并终止执行。
		assert a == 0;

		System.out.println(a);
	}

}
