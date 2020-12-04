package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用ArrayBlockingQueue实现异步的效果(参考Nacos往注册表写数据时的一段逻辑)
 */
public class UseArrayBlockingQueue {

	/**
	 * 控制台输入信息，然后移除打印结果
	 */
	public static void main(String[] args) throws IOException {
		final Notifier notifier = new Notifier();
		notifier.start();

		// 控制台输入内容
		for (;;) {
			String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
			notifier.addTask(str);
		}
	}

	/**
	 * 内部维护一个ArrayBlockingQueue。不断从队列中取数据，只有向队列中放入数据，则立马取出
	 */
	public static class Notifier extends Thread {

		private BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024 * 1024);

		/**
		 * 添加任务
		 */
		public void addTask(String line) {
			queue.offer(line);
		}

		@Override
		public void run() {
			for (;;) {
				try {
					String line = queue.take();
					System.out.println("line---->" + line + "\t");
				} catch (InterruptedException e) {
					System.err.println(e.getMessage());
				}
			}
		}
	}

}