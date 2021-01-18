package leetcode;

/**
 * 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 
 * 例如：
 * [1, -2, 3, 10, -4, 7, 2, -5, 6]
 * 最大子串为[10, -4, 7, 2]，和为18
 * 
 * @author xuwenjin 2021年1月16日
 */
public class MaxSubArray {

	public static void main(String[] args) {
		// int[] nums = { 1, -2, 3, 10, -4, 7, 2, -5 };
		int[] nums = { -2, -1 };
		// System.out.println(test(nums));
		System.out.println(test2(nums));
	}

	/**
	 * 解题分析：
	 * 1、两根指针：i、j，都是从0开始
	 * 2、计算中的子数组和subTotal(从i到len-1的累计值)、已知的最大子数组和subMax(每次和subTotal比较后的大值)
	 * 3、i和j不断移动时，比较subMax的最大值，即为最大子数组和
	 * 
	 * 过程如下(示例[1, -2, 3, 10, -4, 7, 2, -5, 6])：
	 * 第一轮：从1到6，每次进行累计(subTotal)，并取最大值subMax
	 * 第二轮：从-2到6，每次进行累计(subTotal)，并与上一轮的subMax进行比较，大值重新赋给subMax
	 * 第三轮，从3到6，重复上面的步骤，最终得到最大的subMax
	 */
	public static int test(int[] nums) {
		int len = nums.length;
		if (len == 0) {
			return 0;
		}
		if (len == 1) {
			return nums[0];
		}

		int subMax = 0;
		for (int i = 0; i < len; i++) {
			int subTotal = 0;
			for (int j = i; j < len; j++) {
				subTotal += nums[j];
				subMax = Math.max(subMax, subTotal);
			}
		}
		return subMax;
	}

	/**
	 * 解题分析：
	 * 1、一根指针：i，从0开始
	 * 2、挨个遍历每个元素，并求累计值subTotal
	 * 3、如果当前subTotal小于等于0时，直接丢弃(subTotal赋值成当前遍历数字)，因为它只会使最终结果越来越小
	 * 4、如果当前subTotal大于0，则将此时的subTotal与subMax比较，并将大值赋给subMax，遍历完后，subMax即是最子数组和
	 * 
	 * 过程如下(示例[1, -2, 3, 10, -4, 7, 2, -5, 6])：
	 * 第一次遍历：subTotal=1，subMax=1
	 * 第二次遍历：subTotal=-1，subMax=1
	 * 第三次遍历：subTotal=3，subMax=3
	 * 第四次遍历：subTotal=13，subMax=13
	 * 第五次遍历：subTotal=9，subMax=13
	 * 。。。
	 */
	public static int test2(int[] nums) {
		int len = nums.length;
		if (len == 0) {
			return 0;
		}
		if (len == 1) {
			return nums[0];
		}

		int subTotal = nums[0];
		int subMax = nums[0];
		for (int i = 1; i < len; i++) {
			if (subTotal > 0) {
				// subTotal大于0，则累加
				subTotal += nums[i];
			} else {
				// subTotal小于0，直接丢弃原有累计值，赋值成当前值(如果当前值小于0，小一轮还是会走到这里，进行丢弃)
				subTotal = nums[i];
			}
			subMax = Math.max(subMax, subTotal);
		}
		return subMax;
	}

}
