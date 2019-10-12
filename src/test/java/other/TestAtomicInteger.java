package other;

import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

/**
 * 测试AtomicInteger(原子性)
 */
public class TestAtomicInteger {

	AtomicInteger count = new AtomicInteger();

	@Test
	public void test1() {
		int result = 0;

		// 以原子方式将当前值加 1，返回旧值
//		result = count.getAndIncrement();

		// 以原子方式将当前值加 1，返回新值
//		result = count.incrementAndGet();

		// 以原子方式将给定值与当前值相加，返回旧值
//		result = count.getAndAdd(5);

		// 以原子方式将给定值与当前值相加，返回相加后的新值
//		result = count.addAndGet(5);

		// 设置为给定值
		count.set(8);

		// 获取当前值
		System.out.println(count.get());
		System.out.println(result);
	}

}
