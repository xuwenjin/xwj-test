package other;

import java.util.BitSet;

/**
 * 手写布隆过滤器
 * 
 * @author xuwenjin 2021年1月26日
 */
public class MyBloomFilter {

	/**布隆过滤器的长度*/
	private static final int BIT_SIZE = 2 << 28;

	/**这里要选取质数，能很好的降低错误率*/
	private static final int[] seeds = { 3, 5, 7, 11, 13, 31, 37, 61 };

	private BitSet bits = new BitSet(BIT_SIZE);

	private HashFunc[] hashFuncs = new HashFunc[seeds.length];// 用于存储8个随机哈希值对象

	public MyBloomFilter() {
		for (int i = 0; i < seeds.length; i++) {
			hashFuncs[i] = new HashFunc(BIT_SIZE, seeds[i]);
		}
	}

	/**
	* 向过滤器中添加字符串
	*/
	public void put(String value) {
		if (value != null) {
			// 将字符串value哈希为8个整数，然后在这些整数的bit上置为1
			for (HashFunc func : hashFuncs) {
				bits.set(func.hash(value));
			}
		}
	}

	/**
	 * 判断字符串是否包含在布隆过滤器中
	 */
	public boolean mightContain(String value) {
		if (value == null) {
			return false;
		}

		boolean ret = true;
		for (HashFunc func : hashFuncs) {
			// 将要比较的字符串重新以上述方法计算hash值，再与布隆过滤器比对
			if (!bits.get(func.hash(value))) {
				ret = false;
				break;
			}
		}
		return ret;
	}

	/**
	* 随机哈希值对象
	*/
	public static class HashFunc {
		private int size;// 二进制向量数组大小
		private int seed;// 随机数种子

		public HashFunc(int cap, int seed) {
			this.size = cap;
			this.seed = seed;
		}

		/**
		 * 计算哈希值(也可以选用别的恰当的哈希函数)
		 */
		public int hash(String value) {
			int result = 0;
			int len = value.length();
			for (int i = 0; i < len; i++) {
				result = seed * result + value.charAt(i);
			}
			return (size - 1) & result;
		}
	}

}
