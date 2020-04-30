package other;

import java.util.HashMap;

import org.junit.Test;

/**
 * 测试数据类型
 */
public class TestDataType {

	@Test
	public void test1() {
		float a = 0.125f;
		double b = 0.125d;
		System.out.println(a - b); // 0.0
		System.out.println((a - b) == 0.0); // true
	}

	@Test
	public void test2() {
		double c = 0.8;
		double d = 0.7;
		double e = 0.6;
		System.out.println(c - d);
		System.out.println(d - e);
		System.out.println((c - d) == (d - e)); // false
	}

	@Test
	public void test3() {
		System.out.println(1.0 / 0); // Infinity
		System.out.println(0.0 / 0.0); // NaN
		System.out.println(1 / 0.0); // Infinity
		System.out.println(1 / 0); // 抛异常
	}

	@Test
	public void test4() {
		int a = -2;
		System.out.println(a >> 3); // 正数右移用0补位、负数右移用1补位
		System.out.println(a >>> 3); // 无论正负，都用0补位
	}

	@Test
	public void test5() {
//		a(null); // 编译出错
	}

	void a(String s) {
		System.out.println("str");
	}

	void a(Integer s) {
		System.out.println("int");
	}

	@Test
	public void test6() {
		// 如果只有double和Integer方法，下面的1、2、3都会调用double方法
		b(-1); // 1、会调用double方法
		b(1); // 2、会调用double方法
		b(1.0); // 3、会调用double方法

		// 如果有double、Integer、int方法，下面的1和2会调用int方法，3调用double方法
		b(-1); // 1、会调用double方法
		b(1); // 2、会调用double方法
		b(1.0); // 3、会调用double方法
	}

	void b(double s) {
		System.out.println("double");
	}

	void b(Integer s) {
		System.out.println("Integer");
	}

	void b(int s) {
		System.out.println("int");
	}

	@Test
	public void test7() {
		int size = 10;
		HashMap<Integer, Integer> map = new HashMap<>(size);
		for (int i = 0; i < size; i++) {
			map.put(i, i);
		}
	}

}
