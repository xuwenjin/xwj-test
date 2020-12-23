package testThread;

import java.util.concurrent.TimeUnit;

/**
 * 测试ThreadLocal
 * 
 * ThreadLocal自身并不储存值
 * 
 * ThreadLocal里面维护了一个ThreadLocalMap，该map里装的是一个个Entry，而Entry的key是当前ThreadLocal(即下面的tl)，value是set的值
 * 其中ThreadLocalMap是当前线程(Thread)的一个成员变量。所以ThreadLocal.set()，相当于是对当前线程的成员变量赋值，也就是值只能当前线程所有
 * 
 * Thread中维护了ThreadLocalMap，所以ThreadLocalMap的生命周期和Thread（当前线程）一样长。
 * 如果Entry的key使用强引用，引用的ThreadLocal的对象被回收了，但是ThreadLocalMap还持有ThreadLocal的强引用，如果没有手动删除，ThreadLocal不会被回收，导致Entry内存泄漏
 * 如果key为弱引用，引用的ThreadLocal的对象被回收了，由于ThreadLocalMap持有ThreadLocal的弱引用，即使没有手动删除，ThreadLocal也会被回收
 * 
 * 就算Entry的key被回收了，如果当前线程不结束，那value仍然存在。所以在使用完ThreadLocal后，一定要调用remove()方法
 */
public class TestThreadLocal {

	private static ThreadLocal<String> tl = new ThreadLocal<>();

	public static void main(String[] args) {
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

}
