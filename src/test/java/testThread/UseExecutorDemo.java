package testThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.springframework.util.CollectionUtils;

import lombok.SneakyThrows;
import util.IdWorker;

/**
 * 多线程使用示例
 * 
 * @author xuwenjin
 */
public class UseExecutorDemo {

	public static void main(String[] args) {
		List<String> strList = IdWorker.getStrList(10000);
		testExecute(strList);
		testExecute2(strList);
	}

	@SneakyThrows
	public static void testExecute(List<String> strList) {
		long start = System.currentTimeMillis();
		
		int threadNum = 100;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

		List<String> resultList = new ArrayList<>();
		ExecutorService executor = Executors.newFixedThreadPool(threadNum); // 开启5个线程
		IntStream.range(0, threadNum).forEach(index -> {
			// 将10000个数据分成100份，每份100个数
			List<String> list = new ArrayList<>(100);
			list.addAll(strList.subList(index * 100, (index + 1) * 100));

			Future<List<String>> task = executor.submit(new Callable<List<String>>() {
				@Override
				public List<String> call() throws Exception {
					List<String> resultList = new ArrayList<>();
					for (String str : list) {
						if (isMatch(strList, str)) {
							resultList.add(str);
						}
					}
					countDownLatch.countDown();
					return resultList;
				}
			});
			// 获取执行结果
			try {
				List<String> result = task.get();
				if (!CollectionUtils.isEmpty(result)) {
					resultList.addAll(result);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		});
		countDownLatch.await();
		executor.shutdown();
		long end = System.currentTimeMillis();
		System.out.println("匹配个数：" + resultList.size() + ", 用时" + (end - start));
	}

	public static void testExecute2(List<String> strList) {
		long start = System.currentTimeMillis();
		List<String> resultList = new ArrayList<>();
		for (String str : strList) {
			if (isMatch(strList, str)) {
				resultList.add(str);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("匹配个数：" + resultList.size() + ", 用时" + (end - start));
	}

	private static boolean isMatch(List<String> strList, String str) {
		try {
			TimeUnit.MILLISECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str.contains("5p");
	}

}
