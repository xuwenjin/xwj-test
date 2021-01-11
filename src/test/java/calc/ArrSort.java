package calc;

import java.util.Arrays;

/**
 * 排序算法
 * 
 * @author xuwenjin 2020年1月11日
 */
public class ArrSort {

	public static void main(String[] args) {
		int[] arr = { 6, 2, 5, 9, 1, 3, 8, 7 };
		// bubbleSort(arr);
		int[] sortArr = mergeSort(arr);
		System.out.println(Arrays.toString(sortArr));
	}

	/**
	 * 冒泡排序：每次比较两个相邻的元素，将值大的元素交换到右边
	 * 
	 * 时间复杂度: O(N^2)
	 * 空间复杂度: O(1)
	 */
	public static void bubbleSort(int[] arr) {
		int len = arr.length;
		for (int i = 0; i < len; i++) {
			// 每比较完一轮后，本轮最大的数会在最右边，下一轮就可以少比较一次(大数就可以不用参加比较了)
			for (int j = 0; j < len - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}

	/**
	 * 归并排序：基于分治的思想。
	 * 
	 * 例子：[6, 2, 5, 9, 1, 3, 8, 7]
	 * 1、将一个大数组分递归拆分成多个小数组，直到小数组最多两个元素。比如先分成[6, 2, 5, 9]和[1, 3, 8, 7]，再分别分成[6, 2][5, 9][1, 3][8, 7]
	 * 2、将每个最小数组进行排序。  ==>[2, 6][5, 9][1, 3][7, 8]
	 * 3、分别合并[2, 6]与[5, 9]、[1, 3]与[7, 8]，并排序。  ==>[2, 5, 6, 9]和[1, 3, 7, 8]
	 * 4、分别合并[2, 5, 6, 9]与[1, 3, 7, 8]，并排序。  ==>[1, 2, 3, 5, 6, 7, 8, 9]
	 * 
	 * 时间复杂度: O(nlogn)
	 * 空间复杂度: O(n)
	 */
	public static int[] mergeSort(int[] arr) {
		int len = arr.length;
		if (len <= 2) {
			if (len <= 1) {
				return arr;
			} else {
				if (arr[0] > arr[1]) {
					int temp = arr[0];
					arr[0] = arr[1];
					arr[1] = temp;
				}
				return arr;
			}
		}
		int mid = len / 2;
		int[] leftArr = Arrays.copyOfRange(arr, 0, mid);
		int[] rightArr = Arrays.copyOfRange(arr, mid, len);
		return merge(mergeSort(leftArr), mergeSort(rightArr));
	}

	/**
	 * 将两个有序数组合并，合并后的数组也要是有序的
	 */
	private static int[] merge(int[] arr1, int[] arr2) {
		// 1 处理边界情况
		int arrLen1 = arr1.length;
		int arrLen2 = arr2.length;
		if (arr1 == null || arrLen1 == 0) {
			return arr2 == null || arrLen1 == 0 ? null : arr2;
		}
		if (arr2 == null || arrLen2 == 0) {
			return arr1;
		}

		// 2、初始化合并数组
		int[] mergeArr = new int[arrLen1 + arrLen2];
		int mergeIndex = 0; // 结果数组下标
		int tmpArr1Len = 0;
		int tmpArr2Len = 0;
		while (tmpArr1Len < arrLen1 && tmpArr2Len < arrLen2) {
			// 3、如果arr1<arr2，则将arr1的元素放入合并数组
			// 3、当arr1或arr2中某一个数组的元素已经遍历完了，则跳出
			mergeArr[mergeIndex++] = arr1[tmpArr1Len] < arr2[tmpArr2Len] ? arr1[tmpArr1Len++] : arr2[tmpArr2Len++];
		}

		// 4、跳出后，将仍有元素的数组，直接追加到合并数组
		if (tmpArr1Len < arrLen1) {
			System.arraycopy(arr1, tmpArr1Len, mergeArr, mergeIndex, arrLen1 - tmpArr1Len);
		}
		if (tmpArr2Len < arrLen2) {
			System.arraycopy(arr2, tmpArr2Len, mergeArr, mergeIndex, arrLen2 - tmpArr2Len);
		}

		return mergeArr;
	}

}
