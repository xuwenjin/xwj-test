package leetcode.Print1A2B3C;

import java.util.concurrent.locks.LockSupport;

/**
 * 使用LockSupport
 */
public class T02_LockSupport {

	static Thread t1 = null;
	static Thread t2 = null;

	public static void main(String[] args) {
		char[] aI = "123456".toCharArray();
		char[] aC = "ABCDEF".toCharArray();

		/**
		 * LockSupport.unpark(thread): 唤醒处于阻塞状态的线程
		 * 
		 * LockSupport.park(): 阻塞当前线程
		 */

		t1 = new Thread(() -> {

			for (char c : aI) {
				// 1、t1打印
				System.out.println(c);
				// 2、唤醒t2
				LockSupport.unpark(t2);
				// 3、阻塞当前线程t1
				LockSupport.park();
			}

		});

		t2 = new Thread(() -> {

			for (char c : aC) {
				// 4、阻塞当前线程t2
				LockSupport.park();
				// 5、t2打印
				System.out.println(c);
				// 6、唤醒t1
				LockSupport.unpark(t1);

			}

		});

		t1.start();
		t2.start();
	}

	/**
	 * 如果第一次是t1先执行，那执行顺序是：1->2->3->4->5->6->1->2->3->4->5->6依次循环、或者是1->2->4->3->5->6->1->2->4->3->5->6依次循环
	 * 
	 * 如果第一次是t2先执行，那执行顺序是：4->1->2->3->5->6->4->1->2->3->5->6依次循环
	 * 
	 * 结论：一定是t1先执行打印，然后才是t2再执行打印，依次循环
	 */

}
