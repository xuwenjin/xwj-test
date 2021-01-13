package leetcode;

import java.util.Arrays;

/**
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
 * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * 
 * 例如：
 * 给定 nums = [3,2,2,3], val = 3
 * 函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。
 */
public class RemoveTargetValue {

	public static void main(String[] args) {
		int[] nums = { 3, 2, 5, 7, 2, 4 };
		int target = 2;
		System.out.println(test(nums, target));
		System.out.println(Arrays.toString(nums));
	}

	public static int test(int[] nums, int target) {
		int i = 0;
		int n = nums.length;
		while (i < n) {
			if (nums[i] == target) {
				nums[i] = nums[n - 1];
				n--;
			} else {
				i++;
			}
		}
		return n;
	}

}
