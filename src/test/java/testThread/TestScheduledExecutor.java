package testThread;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

/**
 * 测试延迟执行任务(只执行一次)
 */
public class TestScheduledExecutor {

	// 创建一个定长线程池，支持定时及周期性任务执行
	ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

	@Test
	public void test1() {
		System.out.println("创建任务时间：" + new Date());
		// 建立一个延时任务，10秒钟之后执行
		ScheduledFuture<String> futrue = executor.schedule(new MyTask("haha"), 10, TimeUnit.SECONDS);
		try {
			// 如果任务执行完成，future.get()方法会返回一个null。注意，future.get()方法会产生阻塞
			System.out.println(futrue.get());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 当前线程执行完之后，关闭与线程池的连接
		executor.shutdown();
	}

	@Test
	public void test2() {
		// 建立一个定时任务，每隔1秒执行一次
		executor.scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				System.out.println("执行定时任务");
			}
		}, 0, 1L, TimeUnit.SECONDS);
		for (;;)
			;
	}

}

class MyTask implements Callable<String> {

	private String name;

	public MyTask(String name) {
		super();
		this.name = name;
	}

	@Override
	public String call() throws Exception {
		System.out.println("任务开始执行时间：" + new Date());
		return name;
	}
}
