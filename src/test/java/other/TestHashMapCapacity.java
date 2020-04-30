package other;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * 测试HashMap容量及扩容
 */
public class TestHashMapCapacity {

	public static void main(String[] args) throws Exception {
		// 容量测试
		capacityTest(1);
		capacityTest(2);
		capacityTest(3);
		capacityTest(4);
		capacityTest(5);
		capacityTest(7);
		capacityTest(9);
		// 结论 -- 1、实际容量可能是入参容量不一致
		// 结论 -- 2、实际容量是大于等于入参容量，并且最靠近入参容量且是2的幂次方

		// 扩容测试
		thresholdTest();
		// 结论，当map.size>threshold，则会扩容，扩容的容量是之前的一倍
	}

	/**
	 * 容量测试
	 * 
	 * @param initCapacity 入参容量
	 * @return 实际容量
	 */
	public static int capacityTest(int initCapacity) throws Exception {
		Map<String, String> map = new HashMap<String, String>(initCapacity);
		// 通过反射获取容量变量capacity,并调用map对象
		Method capacity = map.getClass().getDeclaredMethod("capacity");
		capacity.setAccessible(true);
		Integer realCapacity = (Integer) capacity.invoke(map);
		System.out.println("入参容量为" + initCapacity + "时，实际容量为" + realCapacity);
		return realCapacity;
	}

	/**
	 * 扩容测试
	 */
	public static void thresholdTest() throws Exception {
		Map<String, String> map = new HashMap<>();

		// 获取map扩容时临界阈值。阈值 = 容量 * 加载因子
		// 默认容量为16，加载因子默认为0.75
		Field threshold = map.getClass().getDeclaredField("threshold");
		Field size = map.getClass().getDeclaredField("size");
		Method capacity = map.getClass().getDeclaredMethod("capacity");

		threshold.setAccessible(true);
		size.setAccessible(true);
		capacity.setAccessible(true);

		// 未存放对象时，各项值测试
		System.out.println("初始阈值：" + threshold.get(map));
		System.out.println("初始容量：" + capacity.invoke(map));

		// 阈值值、容量测试
		for (int i = 1; i < 26; i++) {
			map.put(String.valueOf(i), i + "**");
			if (i == 11 || i == 12 || i == 13 || i == 23 || i == 24 || i == 25) {
				System.out.println("第" + i + "个对象, size为" + size.get(map) + ", 阈值为" + threshold.get(map) + ", 容量为"
						+ capacity.invoke(map));
			}
		}
	}

}
