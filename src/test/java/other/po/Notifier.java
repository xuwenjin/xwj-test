package other.po;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 内部维护一个ArrayBlockingQueue。不断从队列中取数据，只要向队列中放入数据，则立马取出
 * 
 * @author xuwenjin 2019年07月15日
 */
public class Notifier implements Runnable {

	/**
	 * ArrayBlockingQueue：
	 * 1、阻塞式的有界队列(即初始化时指定的容量，就是队列最大的容量，不会出现扩容，容量满，则阻塞进队操作；容量空，则阻塞出队操作)。数据结构：Object[] items
	 * 2、先进先出(队列头的是最先进队的元素；队列尾的是最后进队的元素)
	 * 3、队列不支持空元素
	 */
	private BlockingQueue<String> queue = new ArrayBlockingQueue<>(1024 * 1024);

	/**
	 * 添加任务
	 */
	public void addTask(String line) {
		queue.offer(line);
	}

	@Override
	public void run() {
		for (;;) {
			try {
				String line = queue.take();
				System.out.println("line---->" + line + "\t");
			} catch (InterruptedException e) {
				System.err.println(e.getMessage());
			}
		}
	}

}
