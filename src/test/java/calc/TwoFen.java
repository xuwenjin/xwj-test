package calc;

/**
 * 二分查找
 */
public class TwoFen {

	public static void main(String[] args) {
		int[] arr = { 0, 2, 4, 5, 6, 7, 8, 9, 10, 11 };
		int target = 7;
		System.out.println("结果：" + test1(arr, target));
	}

	/**
	 * 给定一个有序数组arr，找出target所在下标位置，找不到返回-1
	 */
	public static int test1(int[] arr, int target) {
		int left = 0;
		int right = arr.length - 1;
		int mid = -1;

		while (left <= right) {
			// 寻找中间数。该做法等同于(left + right) / 2，只不过下面的写法可以防止left+right后溢出
			mid = left + ((right - left) >> 1);
			int midValue = arr[mid];
			if (midValue == target) {
				return mid;
			} else if (midValue > target) {
				right = mid - 1;
			} else if (midValue < target) {
				left = mid + 1;
			}
		}

		return -1;
	}

}
