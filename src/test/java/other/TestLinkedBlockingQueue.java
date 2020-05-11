package other;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 测试链表阻塞队列-LinkedBlockingQueue(FIFO-先进先出)
 * 
 * LinkedBlockingQueue是一个基于链表实现的可选容量的阻塞队列。队头的元素是插入时间最长的，队尾的元素是最新插入的。新的元素将会被插入到队列的尾部。 
 * LinkedBlockingQueue的容量限制是可选的，如果在初始化时没有指定容量，那么默认使用int的最大值作为队列容量。
 * 
 */
public class TestLinkedBlockingQueue {

	private static LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<String>(10);

	public static void main(String[] args) {
		System.out.println("--------入队--------");
		// add();
		// offer();
		// offer2();
		put();

		System.out.println("--------出队--------");
		// poll();
		// poll2();
		// peek();
		take();
	}

	/**
	 * add 方法:队列已满，报java.lang.IllegalStateException: Queue full 错误
	 */
	public static void add() {
		for (int i = 0; i < 5; i++) {
			linkedBlockingQueue.add(String.valueOf(i));
			System.out.println("入队: " + i);
		}
		System.out.println(linkedBlockingQueue.size());
	}

	/**
	 * offer方法(不会阻塞)。如果队列没满，立即返回true； 如果队列满了，立即返回false
	 */
	public static void offer() {
		for (int i = 0; i < 5; i++) {
			boolean notFull = linkedBlockingQueue.offer(String.valueOf(i));
			System.out.println("notFull: " + notFull);
		}
		System.out.println(linkedBlockingQueue.size());
	}

	/**
	 * offer方法(阻塞)。在队尾插入一个元素,，如果队列已满，则进入等待，直到出现以下三种情况：
	 * 
	 * 被唤醒
	 * 
	 * 等待时间超时
	 * 
	 * 当前线程被中断
	 */
	public static void offer2() {
		for (int i = 0; i < 15; i++) {
			try {
				boolean notFull = linkedBlockingQueue.offer(String.valueOf(i), 3, TimeUnit.SECONDS);
				System.out.println("notFull: " + notFull);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(linkedBlockingQueue.size());
	}

	/**
	 * put方法(阻塞)。当队列满了，再添加元素时，会阻塞，直到队列不满了或者线程被中断
	 */
	public static void put() {
		for (int i = 0; i < 10; i++) {
			try {
				String e = String.valueOf(i);
				linkedBlockingQueue.put(e);
				System.out.println("入队：" + e);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		System.out.println("队列深度：" + linkedBlockingQueue.size());
	}

	/**
	 * poll方法，弹出队顶元素(按添加顺序取)，队列为空时返回null
	 */
	public static void poll() {
		for (int i = 0; i < 2; i++) {
			String e = linkedBlockingQueue.poll();
			System.out.println("出队：" + e);
		}
	}

	/**
	 * poll方法，弹出队顶元素(按添加顺序取)。如果队列已空且已经超时，返回null；如果队列已空且时间未超时，则进入等待，直到出现以下三种情况：
	 * 
	 * 被唤醒
	 * 
	 * 等待时间超时
	 * 
	 * 当前线程被中断
	 */
	public static void poll2() {
		for (int i = 0; i < 10; i++) {
			try {
				String e = linkedBlockingQueue.poll(5, TimeUnit.SECONDS);
				System.out.println("出队：" + e);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * peek方法，返回队列顶元素，但顶元素不弹出，队列为空时返回null(一直取的队列顶元素，不知道这个方法能用在什么场景)
	 */
	public static void peek() {
		for (int i = 0; i < 5; i++) {
			String e = linkedBlockingQueue.peek();
			System.out.println("出队：" + e);
		}
	}

	/**
	 * take方法(阻塞)。当队列为空，阻塞，不为空时，出队
	 */
	public static void take() {
		for (int i = 0; i < 10; i++) {
			String e = null;
			try {
				e = linkedBlockingQueue.take();
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			System.out.println("出队：" + e);
		}
	}

}