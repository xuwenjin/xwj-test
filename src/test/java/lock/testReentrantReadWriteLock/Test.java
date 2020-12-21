package lock.testReentrantReadWriteLock;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 读写锁ReentrantReadWriteLock
 * 
 * 只允许一个线程写入(写锁加锁时，其它读锁和写锁都阻塞住)
 * 没有写入时，多个线程允许同时读（提高性能）。
 */
public class Test {

	public static void main(String[] args) {
		// test1();
		test2();
	}

	static void test1() {
		final ReentrantReadWriteLockDemo lockDemo = new ReentrantReadWriteLockDemo();
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 0; i < 3; i++) {
			executorService.execute(() -> {
				for (int j = 0; j < 3; j++) {
					lockDemo.put(new Random().nextInt(1000)); // 写操作
				}
			});
		}
		for (int i = 0; i < 3; i++) {
			executorService.execute(() -> {
				for (int j = 0; j < 3; j++) {
					lockDemo.get(); // 读操作
				}
			});
		}
		executorService.shutdown();
	}

	static void test2() {
		Counter counter = new Counter();
		new Thread(() -> {
			System.out.println(Arrays.toString(counter.get()));
		}).start();
		new Thread(() -> {
			counter.inc(1);
		}).start();
		new Thread(() -> {
			System.out.println(Arrays.toString(counter.get()));
		}).start();
		new Thread(() -> {
			System.out.println(Arrays.toString(counter.get()));
		}).start();
	}

}
