package lock.testReentrantLock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ReentrantLock-非公平锁(默认)
 * 
 * 非公平锁：随机的获取，谁运气好，cpu时间片轮到哪个线程，哪个线程就能获取锁
 */
public class T3_NonFair {

	static Lock lock = new ReentrantLock(false);

	public static void main(String[] args) {
		new Thread(() -> test(), "线程A").start();
		new Thread(() -> test(), "线程B").start();
		new Thread(() -> test(), "线程C").start();
		new Thread(() -> test(), "线程D").start();
		new Thread(() -> test(), "线程E").start();
	}

	/**
	 * 执行结果是没有顺序的。
	 * 
	 * 比如线程A先持有锁，在A没有释放锁之前，还有线程(比如B和C)来获取锁时，会先放入等待队列。如果在A释放锁时，正好D来获取锁，那么D就会获取到锁。
	 * 不是按照谁先来谁就会先获取锁，所以这是非公平的
	 */
	private static void test() {
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

}
