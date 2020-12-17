package calc;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

/**
 * 从大数据中取值
 * 
 * @author xuwenjin 2020年12月17日
 */
public class TopBigData {

	/**
	 * 取前一百个数字
	 */
	public static Set<Integer> getTop100(int[] inputArray) {
		TreeSet<Integer> top100 = new TreeSet<>();
		for (int i = 0; i < inputArray.length; i++) {
			if (top100.size() < 100) {
				top100.add(inputArray[i]);
			} else if (top100.first() < inputArray[i]) {
				// 如果top100中最小的数字，小于当前inputArray数组中的数字，则替换掉最小的数字
				top100.remove(top100.first());
				top100.add(inputArray[i]);
			}
		}
		return top100;
	}

	public static void main(String[] args) {
		int numberCount = 100000000;

		// 构建1亿个数字，并放入inputArray
		int maxNumber = numberCount;
		int inputArray[] = new int[numberCount];
		Random random = new Random();
		for (int i = 0; i < numberCount; ++i) {
			inputArray[i] = Math.abs(random.nextInt(maxNumber));
		}

		System.out.println("Sort begin...");
		long current = System.currentTimeMillis();
		Set<Integer> result = getTop100(inputArray);
		System.out.println("Spend time:" + (System.currentTimeMillis() - current));
		System.out.println("top100----->" + result);
	}

}