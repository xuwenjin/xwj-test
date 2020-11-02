package lock.testReentrantReadWriteLock;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReentrantReadWriteLock-读写锁
 * 
 * 适用场景：对共享资源有读和写的操作，且写操作没有读操作那么频繁
 * 
 * 在没有写操作的时候，多个线程同时读一个资源没有任何问题，所以应该允许多个线程同时读取共享资源；
 * 但是如果一个线程想去写这些共享资源，就不应该允许其他线程对该资源进行读和写的操作了
 */
public class ReentrantReadWriteLockDemo {

	private final ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private final Lock read = readWriteLock.readLock(); // 读锁
	private final Lock write = readWriteLock.writeLock(); // 写锁
	protected Object object;

	/**
	 * 读锁(共享锁)
	 * 
	 * 线程进入读锁的前提条件： 1、没有其他线程的写锁
	 */
	public void get() {
		try {
			read.lock();
			System.out.println(Thread.currentThread().getName() + "准备读取数据");
			Thread.sleep(new Random().nextInt(1000));
			System.out.println(Thread.currentThread().getName() + "读数据为：" + this.object);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			read.unlock();
		}
	}

	/**
	 * 写锁(排他锁)
	 * 
	 * 线程进入写锁的前提条件： 1、没有其他线程的读锁 2、没有其他线程的写锁
	 */
	public void put(Object object) {
		try {
			write.lock();
			System.out.println(Thread.currentThread().getName() + "准备写数据-------");
			Thread.sleep(new Random().nextInt(1000));
			this.object = object;
			System.out.println(Thread.currentThread().getName() + "写数据为：" + object);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			write.unlock();
		}
	}

}
