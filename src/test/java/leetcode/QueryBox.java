package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数组为{3,2,2,3,1}，查询为(0,3,2)。意思就是在数组里下标0-3这个范围内，有几个2？返回2
 * 假设给你一个数组arr，对这个数组查询非常频繁，请返回所有查询的结果。
 */
public class QueryBox {

	private static Map<Integer, List<Integer>> map = new HashMap<>();

	public static void main(String[] args) {
		int[] sourceArr = { 1, 2, 1, 3, 1, 2, 3, 1, 3, 2 };
		putInMap(sourceArr);
		System.out.println(map);

		System.out.println(query(0, 5, 3));
		System.out.println(query(1, 6, 2));
		System.out.println(query(2, 3, 1));
	}

	/**
	 * 将数组放入到map中，key为数组中的值，value为值所对于应的下标
	 */
	public static void putInMap(int[] nums) {
		List<Integer> list = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			int value = nums[i];
			if (!map.containsKey(value)) {
				list = new ArrayList<>();
				map.put(value, list);
			}
			map.get(value).add(i);
		}
	}

	/**
	 * 查询left-right范围内，有多少个target
	 */
	public static int query(int left, int right, int target) {
		List<Integer> list = map.get(target);
		if (list == null) {
			return 0;
		}
		// 如果list中是{1, 3, 4, 6}，需要查询的范围是[2,5]

		// 办法一：写两个二分寻找：
		// >=2，最左
		// <=6，最右

		// 办法二：写一个二分寻找：
		// <2 a
		// <7 b
		// b - a

		// 找出 < left的下标有几个
		int min = countLess(list, left);
		// 找出 < right + 1的下标有几个
		int max = countLess(list, right + 1);

		return max - min;
	}

	/**
	 * 在有序数组中，用二分的方法找出<limit的数有几个
	 * 
	 * 也就是用二分法，找出<limit的数中最右的位置
	 */
	public static int countLess(List<Integer> list, int limit) {
		int left = 0;
		int right = list.size() - 1;
		int mostRight = -1; // 最右测的数

		while (left <= right) {
			// 寻找中间数。该做法等同于(left + right) / 2，只不过下面的写法可以防止left+right后溢出
			int mid = left + ((right - left) >> 1);
			if (list.get(mid) < limit) {
				mostRight = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}

		return mostRight + 1;
	}

}
