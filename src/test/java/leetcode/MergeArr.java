package leetcode;

import java.util.Arrays;

/**
 * 给你两个有序整数数组 nums1 和 nums2，请你将 nums2 合并到 nums1 中，使 nums1 成为一个有序数组。
 * 初始化 nums1 和 nums2 的元素数量分别为 m 和 n 。你可以假设 nums1 有足够的空间（空间大小等于 m + n）来保存 nums2 中的元素。
 * 
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 
 * @author xuwenjin 2021年1月19日
 */
public class MergeArr {

	public static void main(String[] args) {
		// int[] numArr1 = { 1, 2, 3, 4, 0, 0, 0 };
		// int m = 4;
		// int[] numArr2 = { -1, 5, 6 };
		// int n = 3;
		// int[] numArr1 = { 0 };
		// int m = 0;
		// int[] numArr2 = { 2 };
		// int n = 1;
		int[] numArr1 = { 2, 0 };
		int m = 1;
		int[] numArr2 = { 1 };
		int n = 1;
		merge(numArr1, m, numArr2, n);
		System.out.println(Arrays.toString(numArr1));
	}

	/**
	 * 解题分析
	 * 
	 * 从两个数组的尾部分别取出元素进行比较，大值将放到numArr1的尾部
	 * 大值的数组的下标，向前移动
	 */
	public static void merge(int[] numArr1, int m, int[] numArr2, int n) {
		int index = m + n - 1;
		while (m > 0 && n > 0) {
			numArr1[index--] = numArr1[m - 1] > numArr2[n - 1] ? numArr1[(m--) - 1] : numArr2[(n--) - 1];
		}

		// 当numArr2[0] < numArr1[0]时，上面一步合并完后，会出现numArr2中仍然有元素，这里还得继续处理
		if (m == 0 && n > 0) {
			System.arraycopy(numArr2, 0, numArr1, 0, n);
		}
	}

}
