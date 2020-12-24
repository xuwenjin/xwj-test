package testThread;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import entity.XwjUser;

/**
 * 测试ThreadLocal
 * 
 * ThreadLocal自身并不储存值(***很重要的一句话***)
 * 
 * ThreadLocal里面维护了一个内部类ThreadLocalMap，该map里也有一个内部类Entry和一个成员变量Entry[]，而Entry的key是当前ThreadLocal(即下面的tl)，value是set的值
 * 其中ThreadLocalMap是当前线程(Thread)的一个成员变量。所以ThreadLocal.set()，相当于是对当前线程的成员变量赋值，也就是值只能当前线程所有
 * 
 * Thread中维护了ThreadLocalMap，所以ThreadLocalMap的生命周期和Thread（当前线程）一样长。
 * 如果Entry的key使用强引用，引用的ThreadLocal的对象被回收了，但是ThreadLocalMap还持有ThreadLocal的强引用，如果没有手动删除，ThreadLocal不会被回收，导致Entry内存泄漏
 * 如果key为弱引用，引用的ThreadLocal的对象被回收了，由于ThreadLocalMap持有ThreadLocal的弱引用，即使没有手动删除，ThreadLocal也会被回收
 * 
 * 就算Entry的key被回收了，如果当前线程不结束，那value仍然存在。所以在使用完ThreadLocal后，一定要调用remove()方法
 */
public class TestThreadLocal {

	private ThreadLocal<String> tl = new ThreadLocal<>();
	private ThreadLocal<List<XwjUser>> tl2 = new ThreadLocal<>();

	@Test
	public void test1() {
		// 新建一个线程
		new Thread(() -> {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(tl.get()); // null
		}).start();

		// 再新建一个线程
		new Thread(() -> {
			tl.set("abc");
		}).start();
	}

	@Test
	public void testOOM() {
		ExecutorService executorService = Executors.newFixedThreadPool(1000);
		for (int i = 0; i < 1000; i++) {
			executorService.execute(() -> {
				tl2.set(new TestThreadLocal().addBigData());
				System.out.println(Thread.currentThread().getName());
				// 如果用完后调用remove()方法就不会OOM(记住是在当前线程内部调用)
				// tl2.remove();
			});
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
		}
		executorService.shutdown();
	}

	public List<XwjUser> addBigData() {
		List<XwjUser> list = new ArrayList<>();
		for (int i = 0; i < 20000; i++) {
			list.add(new XwjUser(i, "" + i, new Date()));
		}
		return list;
	}

}
