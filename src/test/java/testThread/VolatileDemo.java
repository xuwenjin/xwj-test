package testThread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * volatile能够实现变量的可见性，不具有原子性
 * 
 * @author xuwenjin
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

	//AtomicInteger是一个提供原子操作的Integer类，通过线程安全的方式操作加减
	private AtomicInteger count = new AtomicInteger(0);

	public void add4() {
		count.getAndIncrement();
	}
	
	public AtomicInteger getCount() {
		return count;
	}

	public static void main(String[] args) {
		final VolatileDemo demo = new VolatileDemo();
		for (int i = 0; i < 5000; i++) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					// demo.add();
					// demo.add2();
					// demo.add3();
					demo.add4();
				}
			}).start();
		}

		// 如果还有子线程在运行，主线程就让出CPU资源
		// 知道所有的子线程都运行完了，主线程再继续往下执行
		while (Thread.activeCount() > 1) {
			Thread.yield();
		}
//		System.out.println("number:" + demo.getNumber());
		System.out.println("count:" + demo.getCount());
	}

}
