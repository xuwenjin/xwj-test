package leetcode;

import java.util.Arrays;

/**
 * 给定一个排序数组，你需要在 原地 删除重复出现的元素，使得每个元素只出现一次，返回移除后数组的新长度。
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组，并在使用 O(1) 额外空间的条件下完成。
 * 
 * 例如：
 * 给定 nums = [0,0,1,1,1,2,2,3,3,4],
 * 函数应该返回新的长度 5, 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4。
 * 
 * @author xuwenjin 2021年1月13日
 */
public class RemoveDuplicates {

	public static void main(String[] args) {
		int[] nums = { 0, 0, 1, 1, 1, 2, 2, 3, 3, 4 };
		int len = test(nums);
		System.out.println("返回长度：" + len);
		System.out.println("原数组结果：" + Arrays.toString(nums));
	}

	public static int test(int[] nums) {
		int len = nums.length;
		if (len <= 1) {
			return len;
		}
		int i = 1; // 遍历下标(1 -> nums.length - 1)
		int j = 1; // 发现不重复元素，j+1
		int tmpNum = nums[0]; // 遍历时，记录每个不重复元素
		while (i < len) {
			int num = nums[i];
			if (num > tmpNum) {
				tmpNum = nums[i];
				nums[j] = num;
				j++;
			}
			i++;
		}
		return j;
	}

}
