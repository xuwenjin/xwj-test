package testThread;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 测试延迟执行任务(只执行一次)
 */
public class TestScheduledExecutor {

	public static void main(String[] args) {
		System.out.println("创建任务时间：" + new Date());
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
		executor.schedule(new MyTask("haha"), 10, TimeUnit.SECONDS);
		executor.shutdown();
	}

}
