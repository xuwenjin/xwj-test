package calc;

import java.util.Arrays;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.springframework.util.CollectionUtils;

import lombok.SneakyThrows;

/**
 * 从大数据中取值
 * 
 * @author xuwenjin 2020年12月17日
 */
public class TopBigData {

	/**
	 * 取前一百个数字
	 */
	public static TreeSet<Integer> getTop100(int[] inputArray) {
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

	/**
	 *  取前一百个数字(使用多线程) ---> 效果并不好
	 */
	@SneakyThrows
	public static int[] getTop100_2(int[] inputArray) {
		int threadNum = 2;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

		int size = inputArray.length;
		int perSize = size / threadNum;
		TreeSet<Integer> result = new TreeSet<Integer>();
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		IntStream.range(0, threadNum).forEach(index -> {
			int workInputArray[] = new int[perSize];
			System.arraycopy(inputArray, index * perSize, workInputArray, 0, perSize);
			Future<TreeSet<Integer>> task = executor.submit(() -> {
				TreeSet<Integer> top100 = getTop100(workInputArray);
				countDownLatch.countDown();
				return top100;
			});
			// 获取执行结果
			try {
				TreeSet<Integer> perResult = task.get();
				if (!CollectionUtils.isEmpty(perResult)) {
					result.addAll(perResult);
				}
			} catch (Exception e) {
				System.err.println(e);
			}
		});
		countDownLatch.await();
		executor.shutdown();

		int[] top100Arr = new int[100];
		for (int i = 99; i >= 0; i--) {
			top100Arr[i] = result.pollLast();
		}
		return top100Arr;
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
		long t1 = System.currentTimeMillis();
		Set<Integer> result = getTop100(inputArray);
		System.out.println("Spend time:" + (System.currentTimeMillis() - t1));
		System.out.println("top100----->" + result);

		long t2 = System.currentTimeMillis();
		int[] resultArr = getTop100_2(inputArray);
		System.out.println("Spend time:" + (System.currentTimeMillis() - t2));
		System.out.println("top100----->" + Arrays.toString(resultArr));
	}

}