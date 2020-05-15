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

	private static void test() {
		for (int i = 0; i < 2; i++) {
			try {
				// 每次遍历，三个线程执行顺序都一样
				lock.lock();
				System.out.println("第" + i + "次--" + Thread.currentThread().getName() + "获取了锁");
				TimeUnit.MILLISECONDS.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}

}
