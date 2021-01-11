package testThread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;
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
	private ForkJoinPool forkPool;

	public UseExecutorDemo() {
		// CPU的核心数
		parallism = Runtime.getRuntime().availableProcessors();
		System.out.println("CPU核心数：" + parallism);
		// 默认就用cpu核心数了
		pool = Executors.newFixedThreadPool(parallism);
		forkPool = new ForkJoinPool();
	}

	public static void main(String[] args) {
		UseExecutorDemo demo = new UseExecutorDemo();

		List<String> strList = IdWorker.getStrList(10000);
		demo.testExecute(strList); // 16582
		demo.testExecute2(strList); // 4416
		demo.testExecute3(strList); // 4241
	}

	/**
	 * 传统方式
	 */
	public void testExecute(List<String> strList) {
		long start = System.currentTimeMillis();
		List<String> resultList = getMatchList(strList);
		long end = System.currentTimeMillis();
		System.out.println("匹配个数：" + resultList.size() + ", 用时：" + (end - start));
	}

	/**
	 * 多线程(ExecutorService)
	 */
	@SneakyThrows
	public void testExecute2(List<String> strList) {
		long start = System.currentTimeMillis();

		int threadNum = parallism;
		List<String> resultList = new ArrayList<>();
		List<Callable<List<String>>> callableList = new ArrayList<>();
		IntStream.range(0, threadNum).forEach(index -> {
			int perLen = strList.size() / threadNum;
			List<String> list = new ArrayList<>(perLen);
			list.addAll(strList.subList(index * perLen, (index + 1) * perLen));
			callableList.add(() -> getMatchList(list));
		});

		// invokeAll(...)方法接收的是一个Callable的集合。执行之后会返回一个Future的List，其中对应着每个Callable任务执行后的Future对象
		List<Future<List<String>>> futureList = pool.invokeAll(callableList);

		// 获取执行结果
		for (Future<List<String>> future : futureList) {
			List<String> result = future.get();
			if (!CollectionUtils.isEmpty(result)) {
				resultList.addAll(result);
			}
		}

		pool.shutdown();
		long end = System.currentTimeMillis();
		System.out.println("匹配个数：" + resultList.size() + ", 用时：" + (end - start));
	}

	/**
	 * 多线程(ForkJoinPool)
	 */
	@SneakyThrows
	public void testExecute3(List<String> strList) {
		long start = System.currentTimeMillis();

		List<String> resultList = forkPool.invoke(new MyForkTask(strList, 0, strList.size() - 1));
		forkPool.shutdown();

		long end = System.currentTimeMillis();
		System.out.println("匹配个数：" + resultList.size() + ", 用时：" + (end - start));
	}

	/**
	 * 执行任务RecursiveTask有返回值，RecursiveAction无返回值
	 */
	class MyForkTask extends RecursiveTask<List<String>> {

		private static final long serialVersionUID = 1L;

		private List<String> list;
		private int from;
		private int to;

		private static final int THRESHOLD = 20; // 每个小任务最多只处理20个元素

		public MyForkTask(List<String> list, int from, int to) {
			this.list = list;
			this.from = from;
			this.to = to;
		}

		/**
		 * 此方法为ForkJoin的核心方法：对任务进行拆分  拆分的好坏决定了效率的高低
		 */
		@Override
		protected List<String> compute() {
			if (to - from < THRESHOLD) {
				// 当需要计算的元素个数小于阈值时，直接采用for loop方式计算结果
				return getMatchList(list.subList(from, to));
			} else {
				// 否则，把任务一分为二，递归拆分(注意此处有递归)到底拆分成多少份，需要根据具体情况而定
				int mid = from + ((to - from) >> 1);
				MyForkTask taskLeft = new MyForkTask(list, from, mid);
				MyForkTask taskRight = new MyForkTask(list, mid, to);
				// 并行执行两个小任务
				invokeAll(taskLeft, taskRight);
				List<String> result = new ArrayList<>(taskLeft.join());
				result.addAll(taskRight.join());
				return result;
			}
		}
	}

	/**
	 * 获取匹配后的字符串列表
	 */
	private List<String> getMatchList(List<String> sourceList) {
		List<String> resultList = new ArrayList<>();
		for (String str : sourceList) {
			if (isMatch(str)) {
				resultList.add(str);
			}
		}
		return resultList;
	}

	private boolean isMatch(String str) {
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return str.contains("5p");
	}

}
