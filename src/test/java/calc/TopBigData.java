package calc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.Callable;
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
			if (top100.size() <= 100) {
				top100.add(inputArray[i]);
			} else if (top100.first() < inputArray[i]) {
				// 如果top100中最小的数字，小于当前inputArray数组中的数字，则替换掉最小的数字
				top100.pollFirst();
				top100.add(inputArray[i]);
			}
		}
		if (top100.size() == 101) {
			// 发现第100个和第101个数字，有的时候位置搞反了(没搞懂为啥)。所以上面先取前101个数字，这里再剔除掉最小的数字
			top100.pollFirst();
		}
		return top100;
	}

	/**
	 * 取前一百个数字(使用多线程)
	 */
	@SneakyThrows
	public static int[] getTop100_2(int[] inputArray) {
		int threadNum = Runtime.getRuntime().availableProcessors(); // CPU核心数

		int size = inputArray.length;
		int perSize = size / threadNum;
		ExecutorService executor = Executors.newFixedThreadPool(threadNum);
		List<Callable<TreeSet<Integer>>> callableList = new ArrayList<>();
		IntStream.range(0, threadNum).forEach(index -> {
			int workInputArray[] = Arrays.copyOfRange(inputArray, index * perSize, (index + 1) * perSize);
			callableList.add(() -> getTop100(workInputArray));
		});

		// invokeAll(...)方法接收的是一个Callable的集合。执行之后会返回一个Future的List，其中对应着每个Callable任务执行后的Future对象
		List<Future<TreeSet<Integer>>> futureList = executor.invokeAll(callableList);

		// 获取执行结果
		TreeSet<Integer> resultSet = new TreeSet<Integer>();
		for (Future<TreeSet<Integer>> future : futureList) {
			TreeSet<Integer> result = future.get();
			if (!CollectionUtils.isEmpty(result)) {
				resultSet.addAll(result);
			}
		}

		executor.shutdown();

		int[] top100Arr = new int[100];
		for (int i = 99; i >= 0; i--) {
			top100Arr[i] = resultSet.pollLast();
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
		System.out.println("传统方式用时:" + (System.currentTimeMillis() - t1));
		System.out.println("top100个数：" + result.size());
		System.out.println("top100结果：" + result);

		System.out.println("---------------------------");

		long t2 = System.currentTimeMillis();
		int[] resultArr = getTop100_2(inputArray);
		System.out.println("多线程用时:" + (System.currentTimeMillis() - t2));
		System.out.println("top100个数：" + resultArr.length);
		System.out.println("top100结果：" + Arrays.toString(resultArr));
	}

}