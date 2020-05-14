package testThread.testVolatile;

import java.util.concurrent.TimeUnit;

/**
 * 测试volatile特性-内存可见性
 */
public class Volatile_Visible {

	private static volatile boolean running = true;

	public static void main(String[] args) {

		// 子线程
		new Thread(() -> {
			// 子线程从主内存中读取running值到工作内存，然后判断running的值
			while (running) {
				// 如果running没有volatile修饰符，则就算主线程将running改为false，子线程中running的值也不会变化
				// 如果running有volatile修饰符，则只要running发生变化，则所有子线程中running立马变成最新的值，这也就测试了volatile的内存可行性
			}
			System.out.println("~~~end~~~");
		}).start();

		try {
			// 这里sleep一下，是为了子线程能尽量在下面主线程改变running值前运行
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 主线程改变running值
		running = false;
	}

}
