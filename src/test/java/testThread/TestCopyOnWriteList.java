package testThread;

import java.util.List;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CountDownLatch;

/**
 * 线程安全集合-CopyOnWriteArrayList
 * 
 * 原理：当我们往一个容器添加元素的时候，不直接往当前容器添加，而是先将当前容器进行Copy，复制出一个新的容器，然后新的容器里添加元素，添加完元素之后，再将原容器的引用指向新的容器
 * 
 * 问题点：数据不一致的问题。如果写线程还没来得及写会内存，其他的线程就会读到了脏数据。所以CopyOnWriteArrayList使用条件也很局限，那就是在读多写少的情况下比较好
 */
public class TestCopyOnWriteList {

	static List<String> list = new Vector<>();

	public static void main(String[] args) throws InterruptedException {
		// 线程不安全
		// List<String> list = new ArrayList<>();

		// 线程安全，读和写都会使用synchronized加锁
		// List<String> list = new Vector()<>();

		// 线程安全，写的时候进行复制，并且只有在写的时候使用ReentrantLock加锁，读的时候不加锁
		List<String> list = new CopyOnWriteArrayList<>();

		long s1 = System.currentTimeMillis();

		int threadNum = 100;
		final CountDownLatch latch = new CountDownLatch(threadNum);
		Random random = new Random();
		for (int i = 0; i < threadNum; i++) {
			new Thread(() -> {
				for (int j = 0; j < 1000; j++) {
					list.add("a" + random.nextInt(10000));
				}
				// System.out.println(Thread.currentThread().getName() + "-->" +
				// list.size());
				latch.countDown();
			}).start();
		}

		// 线程未执行完前，一直阻塞
		latch.await();

		long s2 = System.currentTimeMillis();
		System.out.println("用时：" + (s2 - s1) + "，list长度：" + list.size());
	}

}
