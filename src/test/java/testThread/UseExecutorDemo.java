package testThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.IntStream;

import org.junit.Test;

import util.IdWorker;

/**
 * 多线程使用示例
 * 
 * @author xuwenjin
 */
public class UseExecutorDemo {

	@Test
	public void testExecute() throws Exception {
		List<String> strList = IdWorker.getStrList(1000000);
		long start = System.currentTimeMillis();
		final CountDownLatch countDownLatch = new CountDownLatch(5);

		ExecutorService executor = Executors.newFixedThreadPool(5); //开启5个线程
		IntStream.range(0, 50).forEach(index -> {
			// 将1000000个数据分成50份，每份20000个数
			List<String> list = new ArrayList<>(20000);
			list.addAll(strList.subList(index * 20000, (index + 1) * 20000));

			Future<List<String>> task = executor.submit(new Callable<List<String>>() {
				@Override
				public List<String> call() throws Exception {
					System.out.println("执行线程" + Thread.currentThread().getName());
					List<String> resultList = new ArrayList<>();
					for (String str : list) {
						if (str.contains("5p")) {
							resultList.add(str);
						}
					}
					countDownLatch.countDown();
					return resultList;
				}
			});
			// 获取执行结果
			try {
				System.out.println("结果为" + task.get());
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		});
		countDownLatch.await();
		executor.shutdown();
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
}
