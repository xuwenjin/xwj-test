package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import other.po.Notifier;

/**
 * 使用ArrayBlockingQueue实现异步的效果(参考了Nacos往注册表写数据时的一段逻辑)
 * 
 * @author xuwenjin 2019年07月15日
 */
public class UseArrayBlockingQueue {

	/**
	 * 控制台输入信息，然后移除打印结果
	 */
	public static void main(String[] args) throws IOException {
		Notifier notifier = new Notifier();
		// 从线程池中获取一个线程并启动
		ExecutorService pool = Executors.newSingleThreadExecutor();
		pool.execute(notifier);

		// 控制台输入内容
		for (;;) {
			String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
			notifier.addTask(str);
		}
	}

}