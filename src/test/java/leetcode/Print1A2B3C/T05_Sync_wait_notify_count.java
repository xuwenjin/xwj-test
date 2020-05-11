package leetcode.Print1A2B3C;

import java.util.concurrent.CountDownLatch;

/**
 * 使用wait+nofity。为了必须线程t1先执行，加了CountDownLatch
 */
public class T05_Sync_wait_notify_count {

	public static void main(String[] args) {
		final Object obj = new Object();

		final CountDownLatch latch = new CountDownLatch(1);

		char[] aI = "123456".toCharArray();
		char[] aC = "ABCDEF".toCharArray();

		/**
		 * obj.wait(): 当前线程放弃锁，并处于等待状态
		 * 
		 * obj.notify(): 随机唤醒一个处于等待中的线程
		 */

		// 线程t1
		new Thread(() -> {
			synchronized (obj) {
				for (char c : aI) {
					System.out.println(c);

					// 计数器减1
					latch.countDown();

					obj.notify();
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 防止最后一个wait死锁
				obj.notify();
			}
		}).start();

		// 线程t2
		new Thread(() -> {
			try {
				// 计数器为0时，才能往下执行
				latch.await();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}

			synchronized (obj) {
				for (char c : aC) {
					System.out.println(c);
					obj.notify();
					try {
						obj.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				// 防止最后一个wait死锁
				obj.notify();
			}
		}).start();
	}

}
