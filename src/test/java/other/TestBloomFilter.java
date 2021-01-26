package other;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;

/**
 * 测试布隆过滤器(可用于redis缓存穿透)
 * 
 * @author xwj
 */
public class TestBloomFilter {

	public static void main(String[] args) {
		// testGuavaBloomFilter();
		testMyBloomFilter();
	}

	/**
	 * 测试guava的布隆过滤器
	 */
	public static void testGuavaBloomFilter() {
		int total = 1000000;
		// BloomFilter<Integer> bf =
		// BloomFilter.create(Funnels.integerFunnel(), total);
		BloomFilter<Integer> bf = BloomFilter.create(Funnels.integerFunnel(), total, 0.0003);

		// 初始化1000000条数据到过滤器中
		for (int i = 0; i < total; i++) {
			bf.put(i);
		}

		// 匹配已在过滤器中的值，是否有匹配不上的
		for (int i = 0; i < total; i++) {
			if (!bf.mightContain(i)) {
				System.out.println("有坏人逃脱了~~~");
			}
		}

		// 匹配不在过滤器中的10000个值，有多少匹配出来
		int count = 0;
		for (int i = total; i < total + 10000; i++) {
			if (bf.mightContain(i)) {
				count++;
			}
		}
		System.out.println("误伤的数量：" + count);
	}

	/**
	 * 测试自定义的布隆过滤器
	 */
	public static void testMyBloomFilter() {
		int total = 1000000;
		MyBloomFilter bf = new MyBloomFilter();

		// 初始化1000000条数据到过滤器中
		for (int i = 0; i < total; i++) {
			bf.put(i + "");
		}

		// 匹配已在过滤器中的值，是否有匹配不上的
		for (int i = 0; i < total; i++) {
			if (!bf.mightContain(i + "")) {
				System.out.println("有坏人逃脱了~~~");
			}
		}

		// 匹配不在过滤器中的10000个值，有多少匹配出来
		int count = 0;
		for (int i = total; i < total + 10000; i++) {
			if (bf.mightContain(i + "")) {
				count++;
			}
		}
		System.out.println("误伤的数量：" + count);
	}

}
