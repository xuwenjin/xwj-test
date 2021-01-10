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

	private int parallism;
	private ExecutorService pool;

	public UseExecutorDemo() {
		// CPU的核心数
		parallism = Runtime.getRuntime().availableProcessors();
		// 默认就用cpu核心数了
		pool = Executors.newFixedThreadPool(parallism);
	}

	public static void main(String[] args) {
		UseExecutorDemo demo = new UseExecutorDemo();

		// 结果：时间差不多
		List<String> strList = IdWorker.getStrList(10000);
		demo.testExecute(strList);
		demo.testExecute2(strList);
	}

	@SneakyThrows
	public void testExecute(List<String> strList) {
		long start = System.currentTimeMillis();

		int threadNum = parallism;
		final CountDownLatch countDownLatch = new CountDownLatch(threadNum);

		List<String> resultList = new ArrayList<>();
		IntStream.range(0, threadNum).forEach(index -> {
			int perLen = strList.size() / threadNum;
			List<String> list = new ArrayList<>(perLen);
			list.addAll(strList.subList(index * perLen, (index + 1) * perLen));
			Future<List<String>> task = pool.submit(new Callable<List<String>>() {
				@Override
				public List<String> call() throws Exception {
					List<String> resultList = new ArrayList<>();
					for (String str : list) {
						if (isMatch(str)) {
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
		pool.shutdown();
		long end = System.currentTimeMillis();
		System.out.println("匹配个数：" + resultList.size() + ", 用时" + (end - start));
	}

	public void testExecute2(List<String> strList) {
		long start = System.currentTimeMillis();
		List<String> resultList = new ArrayList<>();
		for (String str : strList) {
			if (isMatch(str)) {
				resultList.add(str);
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("匹配个数：" + resultList.size() + ", 用时" + (end - start));
	}

	private boolean isMatch(String str) {
		try {
			TimeUnit.MILLISECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str.contains("5p");
	}

}
