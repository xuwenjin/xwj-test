package leetcode;

import java.util.Arrays;

/**
 * 两个数相加(升级版)
 * 
 * 给定一个有序整数数组，返回两个数字的索引，使它们相加到特定目标(不能两次使用相同的元素)
 * 
 * nums = [2, 7, 11, 15, 16], target = 18
 * 2 + 16、7 + 11
 * return [0, 4]、[1, 2]
 */
public class TwoSumPlus {

	public static void main(String[] args) {
		int[] nums = { 2, 7, 11, 15, 16 };
		int target = 18;
		test(nums, target);
	}

	/**
	 * 双向遍历：一个从前往后，一个从后往前
	 */
	public static void test(int[] nums, int target) {
		int left = 0;
		int right = nums.length - 1;
		while (left < right) {
			int total = nums[left] + nums[right];
			if (total == target) {
				// a、当前左值+右值=目标值，则打印，然后left+1，right-1
				System.out.println(Arrays.toString(new int[] { left, right }));
				left++;
				right--;
			} else if (total < target) {
				// a、当前左值+右值<目标值，则需要将值变大，所以left+1
				left++;
			} else {
				// a、当前左值+右值>目标值，则需要将值变小，所以right-1
				right--;
			}
		}
	}

}
