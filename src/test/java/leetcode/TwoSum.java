package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 两个数相加
 * 
 * 给定一个整数数组，返回两个数字的索引，使它们相加到特定目标(不能两次使用相同的元素)
 * 
 * nums = [2, 7, 11, 15], target = 9,
 * 
 * nums[0] + nums[1] = 2 + 7 = 9, return [0, 1].
 */
public class TwoSum {

	public static void main(String[] args) {
		int[] nums = { 3, 2, 4 };
		int target = 6;
		System.out.println(Arrays.toString(test1(nums, target)));
		System.out.println(Arrays.toString(test2(nums, target)));
		System.out.println(Arrays.toString(test3(nums, target)));
	}

	/**
	 * 暴力解法，时间复杂度O(n^2)
	 */
	public static int[] test1(int[] nums, int target) {
		int[] result = new int[2];
		for (int i = 0; i < nums.length; i++) {
			if (i < nums.length - 1) {
				for (int j = i + 1; j < nums.length; j++) {
					if (nums[i] + nums[j] == target) {
						result[0] = i;
						result[1] = j;
						return result;
					}
				}
			}
		}
		return null;
	}

	/**
	 * 优化解法，时间复杂度O(n)
	 */
	public static int[] test2(int[] nums, int target) {
		int[] result = new int[2];

		// 1、遍历数组，生成一个map。key为当前数的另一个半，value为当前数的下标
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			map.put(target - nums[i], i);
		}

		// 2、遍历数据，得到两个下标
		for (int i = 0; i < nums.length; i++) {
			Integer j = map.get(nums[i]);
			if (j != null && i != j) {
				result[0] = i;
				result[1] = j;
				return result;
			}
		}
		return null;
	}

	/**
	 * 解法2的升级版
	 */
	public static int[] test3(int[] nums, int target) {
		// key为当前数的另一个半，value为当前数的下标
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			int remain = target - nums[i];
			if (map.containsKey(remain)) {
				return new int[] { map.get(remain), i };
			}
			map.put(nums[i], i);
		}
		return null;
	}

}
