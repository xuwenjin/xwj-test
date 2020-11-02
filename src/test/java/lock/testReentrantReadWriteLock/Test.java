package lock.testReentrantReadWriteLock;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test {

	public static void main(String[] args) {
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

}
