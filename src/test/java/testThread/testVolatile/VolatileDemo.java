package testThread.testVolatile;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile能够实现变量的可见性，不具有原子性
 */
public class VolatileDemo {

	private volatile int number = 0;

	private Lock lock = new ReentrantLock();

	public int getNumber() {
		return this.number;
	}

	public void add() {
		this.number++;
	}

	public void add2() {
		// 加上synchronized后，就可以保证原子性
		synchronized (this) {
			this.number++;
		}
	}

	public void add3() {
		// 加上lock后，保证原子性
		lock.lock();
		try {
			this.number++;
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		final VolatileDemo demo = new VolatileDemo();

		int threadNums = 500;
		final CountDownLatch latch = new CountDownLatch(threadNums);
		for (int i = 0; i < threadNums; i++) {
			new Thread(() -> {
				demo.add();
				// demo.add2();
				// demo.add3();
				latch.countDown();
			}).start();
		}
		latch.await();

		System.out.println("number:" + demo.getNumber());
	}

}
