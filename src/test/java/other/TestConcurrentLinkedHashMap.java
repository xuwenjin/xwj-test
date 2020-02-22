package other;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.Weighers;

/**
 * 测试google的ConcurrentLinkedHashMap，它本身是对ConcurrentHashMap的封装，可以用来实现一个基于LRU策略的缓存
 * 
 * LRU(最近最久未使用)原理：
 * LRU的设计原理就是，当数据在最近一段时间经常被访问，那么它在以后也会经常被访问。这就意味着，如果经常访问的数据，我们需要然其能够快速命中，而不常访问的数据，我们在容量超出限制内，要将其淘汰。
 * 每次访问的数据都会放在栈顶，当访问的数据不在内存中，且栈内数据存储满了，我们就要选择移除栈底的元素，因为在栈底部的数据访问的频率是比较低的。所以要将其淘汰。
 * 
 * @author xwj
 */
public class TestConcurrentLinkedHashMap {

	private static ConcurrentLinkedHashMap<String, String> map = new ConcurrentLinkedHashMap.Builder<String, String>()
			.maximumWeightedCapacity(3).weigher(Weighers.singleton()).build();

	/** 带监听器(舍弃元素时触发) */
	private static ConcurrentLinkedHashMap<String, String> map2 = new ConcurrentLinkedHashMap.Builder<String, String>()
			.maximumWeightedCapacity(3).weigher(Weighers.singleton())
			.listener((key, value) -> System.out.println("Evicted key=" + key + ", value=" + value)).build();

	public static void main(String[] args) {
		// test1();
		// test2();
		test3();
	}

	public static void test1() {
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");

		// 因为我们定义最大容量为 3 ，所以 ConcurrentLinkedHashMap 只能存储 3 个，当我们 put
		// 第四个值时，我们最近最少使用的 1 就会被丢弃了。如果能储存4个则不会丢弃
		map.forEach((k, v) -> System.out.println(k + "," + v));
	}

	public static void test2() {
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.get("1");
		map.put("4", "4");

		// 我们在 put 第四个值的时候，我们可以调用下第一个值，再次运行期结果，会发现2被丢弃了
		map.forEach((k, v) -> System.out.println(k + "," + v));
	}

	public static void test3() {
		map2.put("1", "1");
		map2.put("2", "2");
		map2.put("3", "3");
		map2.put("4", "4");

		// ConcurrentLinkedHashMap默认在丢弃值时，是没有任何提示的，现加上监听器：
		map2.forEach((k, v) -> System.out.println(k + "," + v));
	}

}
