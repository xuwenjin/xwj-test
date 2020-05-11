package testThread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch这个类使一个线程等待其他线程各自执行完毕后再执行。
 * 
 * 是通过一个计数器来实现的，计数器的初始值是线程的数量。每当一个线程执行完毕后，计数器的值就-1，当计数器的值为0时，表示所有线程都执行完毕，然后在闭锁上等待的线程就可以恢复工作了。
 */
public class TestCountDownLatch {

	public static void main(String[] args) {
		System.out.println("主线程开始执行~~~");

		// 构造器中count为线程数量
		final CountDownLatch latch = new CountDownLatch(2);

		ExecutorService es1 = Executors.newSingleThreadExecutor();
		es1.execute(() -> {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("子线程：" + Thread.currentThread().getName() + "执行。。。");

			// countDown()方法：计数器的值减一
			latch.countDown();
		});
		es1.shutdown();

		ExecutorService es2 = Executors.newSingleThreadExecutor();
		es2.execute(() -> {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("子线程：" + Thread.currentThread().getName() + "执行。。。");

			// countDown()方法：计数器的值减一
			latch.countDown();
		});
		es2.shutdown();
		System.out.println("等待两个线程执行完毕~~~");

		try {
			// 调用await()方法的线程会被挂起，它会等待直到count值为0才继续执行
			latch.await();
			// 和await(timeout, unit)类似，只不过等待一定的时间后count值还没变为0的话就会继续执行
			// latch.await(1500, TimeUnit.MILLISECONDS);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("两个子线程都执行完毕，继续执行主线程~~~");
	}

}
