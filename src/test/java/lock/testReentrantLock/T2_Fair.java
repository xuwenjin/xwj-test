package lock.testReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock-公平锁
 * 
 * 公平锁：谁等的时间最长，谁就先获取锁。
 */
public class T2_Fair {

	static Lock lock = new ReentrantLock(true);

	public static void main(String[] args) {
		new Thread(() -> test(), "线程A").start();
		new Thread(() -> test(), "线程B").start();
		new Thread(() -> test(), "线程C").start();
		new Thread(() -> test(), "线程D").start();
		new Thread(() -> test(), "线程E").start();
	}

	/**
	 * 由于每个线程持有锁之后，会sleep一段时间，所以，结果大概率上是按照线程执行顺序来
	 * 
	 * 比如线程A先持有锁，在A没有释放锁之前，还有线程(比如B和C)来获取锁时，会先放入等待队列。如果在A释放锁时，会先从队里头取等待的线程，然后该线程获取锁
	 * 谁先来的，谁先获取锁，所以是公平的
	 */
	private static void test() {
		try {
			lock.lock();
			System.out.println(Thread.currentThread().getName() + "获取了锁");
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

}
