package lock.testReentrantReadWriteLock;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Counter {

	private final ReadWriteLock rwlock = new ReentrantReadWriteLock();
	private final Lock rlock = rwlock.readLock();
	private final Lock wlock = rwlock.writeLock();
	private int[] counts = new int[10];

	public void inc(int index) {
		wlock.lock(); // 加写锁
		try {
			System.out.println(Thread.currentThread().getName() + " -----> 加写锁");
			counts[index] += 1;
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} finally {
			wlock.unlock(); // 释放写锁
			System.out.println(Thread.currentThread().getName() + " -----> 解写锁");
		}
	}

	public int[] get() {
		rlock.lock(); // 加读锁
		try {
			System.out.println(Thread.currentThread().getName() + " -----> 加读锁");
			return Arrays.copyOf(counts, counts.length);
		} finally {
			rlock.unlock(); // 释放读锁
			System.out.println(Thread.currentThread().getName() + " -----> 解读锁");
		}
	}

}