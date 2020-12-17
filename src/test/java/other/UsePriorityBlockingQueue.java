package other;

import java.util.concurrent.PriorityBlockingQueue;

/**
 * 测试PriorityBlockingQueue
 * 
 * @author xuwenjin 2020年12月17日
 */
public class UsePriorityBlockingQueue {

	/**
	 * PriorityBlockingQueue 优先级无界队列(会进行排序)
	 */
	public static void main(String[] args) throws InterruptedException {
		PriorityBlockingQueue<Integer> queue = new PriorityBlockingQueue<>();
		queue.add(3);
		queue.add(2);
		queue.add(7);
		queue.add(6);
		queue.add(3);
		queue.add(1);
		queue.add(9);
		queue.add(8);
		System.out.println(queue);
		System.out.println("poll() ------> " + queue.poll()); // 弹出最小的元素
		System.out.println(queue);
		System.out.println("take() ------> " + queue.take());
		System.out.println(queue);
		System.out.println("take() ------> " + queue.take());
		System.out.println(queue);
		System.out.println("peek() ------> " + queue.peek()); // 查看优先队列中最小数字
		System.out.println(queue);
	}

}