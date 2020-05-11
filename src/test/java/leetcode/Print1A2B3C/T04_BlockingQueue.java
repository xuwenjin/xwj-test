package leetcode.Print1A2B3C;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 使用BlockQueue
 */
public class T04_BlockingQueue {

	static BlockingQueue<String> q1 = new LinkedBlockingQueue<String>(1);
	static BlockingQueue<String> q2 = new LinkedBlockingQueue<String>(1);

	public static void main(String[] args) {
		char[] aI = "123456".toCharArray();
		char[] aC = "ABCDEF".toCharArray();

		/**
		 * put方法(阻塞)。当队列满了，再添加元素时，会阻塞，直到队列不满了或者线程被中断
		 * 
		 * take方法(阻塞)。当队列为空，阻塞，不为空时，出队
		 */

		// 线程t1
		new Thread(() -> {

			for (char c : aI) {
				// 1、t1打印
				System.out.println(c);
				try {
					// 2、q2赋值
					q1.put("ok");
					// 3、t1阻塞
					q2.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}).start();

		// 线程t2
		new Thread(() -> {

			for (char c : aC) {
				try {
					// 4、t2阻塞
					q1.take();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// 5、t2打印
				System.out.println(c);

				try {
					// 6、q1赋值
					q2.put("ok");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}).start();
	}

	/**
	 * 如果第一次是t1先执行，那执行顺序是：1->2->3->4->5->6->1->2->3->4->5->6依次循环、或者是1->2->4->3->5->6->1->2->4->3->5->6依次循环
	 * 
	 * 如果第一次是t2先执行，那执行顺序是：4->1->2->3->5->6->4->1->2->3->5->6依次循环
	 * 
	 * 结论：利用take阻塞。一定是t1先执行打印，然后才是t2再执行打印，依次循环
	 */

}
