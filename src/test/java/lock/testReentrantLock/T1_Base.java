package lock.testReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock加锁和解锁需要手动进行
 * 
 * ReentrantLock是一个互斥锁，也是一个可重入锁（Reentrant就是再次进入的意思）。
 * 
 * ReentrantLock锁在同一个时间点只能被一个线程锁持有，但是它可以被单个线程多次获取，每获取一次AQS的state就加1，每释放一次state就减1
 */
public class T1_Base {

	static Lock lock = new ReentrantLock();

	public static void main(String[] args) throws InterruptedException {
		// new Thread(() -> testLock(), "A").start();
		// new Thread(() -> testLock(), "B").start();
		// new Thread(() -> testLock(), "C").start();
		new Thread(() -> testTryLock(), "A").start();
		TimeUnit.MILLISECONDS.sleep(10);
		new Thread(() -> testTryLock(), "B").start();
	}

	static void testLock() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "获取了锁");
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	/**
	 * tryLock：在指定的时间内如果获取到锁就返回true，获取不到则返回false(会抛出异常：IllegalMonitorStateException)
	 * 
	 * 这种机制避免了线程无限期的等待锁释放
	 */
	static void testTryLock() {
		try {
			String threadName = Thread.currentThread().getName();
			lock.tryLock(1000, TimeUnit.MILLISECONDS);
			System.out.println(threadName + "获取了锁");
			if ("A".equals(threadName)) {
				TimeUnit.MILLISECONDS.sleep(1500);
			}
			if ("B".equals(threadName)) {
				TimeUnit.MILLISECONDS.sleep(500);
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
