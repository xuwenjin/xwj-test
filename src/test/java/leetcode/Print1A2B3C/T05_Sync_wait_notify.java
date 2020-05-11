package leetcode.Print1A2B3C;

/**
 * 使用wait+nofity(下面的代码，无法保证哪个行程谁先执行)
 */
public class T05_Sync_wait_notify {

	public static void main(String[] args) {
		final Object obj = new Object();

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
