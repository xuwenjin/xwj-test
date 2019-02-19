package algorithms;

/**
 * 算术-数组
 * 
 * @author xwj
 *
 */
public class AlgArr {

	/**
	 * 删除nums中值为val的元素(不允许新建一个数组)
	 */
	public int removeElement(int[] nums, int val) {
		int i = 0;
		int n = nums.length;
		while (i < n) {
			if (nums[i] == val) {
				nums[i] = nums[n - 1];
				n--;
			} else {
				i++;
			}
		}
		return n;
	}

}
