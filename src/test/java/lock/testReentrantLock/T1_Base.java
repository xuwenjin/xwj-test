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

	public static void main(String[] args) {
		new Thread(() -> test(), "线程A").start();
		new Thread(() -> test(), "线程B").start();
		new Thread(() -> test(), "线程C").start();
	}

	private static void test() {
		try {
			// 默认是非公平锁
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "获取了锁");
			TimeUnit.MILLISECONDS.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
