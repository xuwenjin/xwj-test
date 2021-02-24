package other;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * BitSet: 一种特殊类型的数组来保存位值，BitSet中数组大小会随需要增加
 */
public class TestBigSet {

	public static void main(String args[]) {
		// basicUse();
		useDemo();
	}

	/**
	 * BitSet基本使用
	 */
	public static void basicUse() {
		// 所有位初始化为0
		BitSet bits1 = new BitSet(16);
		BitSet bits2 = new BitSet(16);

		// 向bits1和bits2中塞值
		for (int i = 0; i < 16; i++) {
			if ((i % 2) == 0) {
				bits1.set(i);
			}
			if ((i % 5) != 0) {
				bits2.set(i);
			}
		}
		System.out.println("bits1初始值 ---->  " + bits1);
		System.out.println("bits2初始值 ---->  " + bits2);

		System.out.println("返回 bits1 中设置为 true 的位数：" + bits1.cardinality());
		System.out.println("返回 bits2 中设置为 true 的位数：" + bits2.cardinality());

		// bit2和bits1进行与运算
		// bits2.and(bits1);
		// System.out.println("bits2 AND bits1: " + bits2);

		// bit2和bits1进行或运算
		// bits2.or(bits1);
		// System.out.println("bits2 OR bits1: " + bits2);

		// bit2和bits1进行异或运算
		bits2.xor(bits1);
		System.out.println("bits2 XOR bits1: " + bits2);
	}

	/**
	 * BitSet基本使用
	 * 
	 * 有一千万个随机数，随机数的范围在1到1千万之间。现在要求写出一种算法，将1到1千万之间没有在随机数中的数求出来？
	 */
	public static void useDemo() {
		int count = 10000000;
		Random random = new Random();

		List<Integer> list = new ArrayList<>();
		BitSet bitSet = new BitSet(count);
		for (int i = 0; i < count; i++) {
			int randomResult = random.nextInt(count);
			list.add(randomResult);
			
			bitSet.set(randomResult);
		}
		System.out.println("产生的随机数有：" + list);

		System.out.println("在0~" + count + "范围内的随机数有  " + bitSet.cardinality() + " 个");
		List<Integer> excludeList = new ArrayList<>(count - bitSet.cardinality());
		for (int i = 0; i < count; i++) {
			if (!bitSet.get(i)) {
				excludeList.add(i);
			}
		}
		System.out.println("不在0~" + count + "范围内的随机数：  " + excludeList);
	}

}
