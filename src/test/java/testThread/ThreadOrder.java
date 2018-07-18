package testThread;

/**
 * 线程的顺序执行
 * 
 * 有三个线程T1 T2 T3，如何保证他们按顺序执行-转载 在T2的run中，调用t1.join，让t1执行完成后再让T2执行
 * 在T3的run中，调用t2.join，让t2执行完成后再让T3执行
 *
 * Thread类中的join方法的主要作用就是同步，它可以使得线程之间的并行执行变为串行执行
 * 
 * @author xuwenjin
 */
public class ThreadOrder {

	static Thread t1 = new Thread(new Runnable() {

		@Override
		public void run() {
			System.out.println("执行t1");
		}
	});

	static Thread t2 = new Thread(new Runnable() {

		@Override
		public void run() {
			try {
				/*
				 * t2线程准备执行时，先等待t1执行完，t2再执行
				 */
				t1.join();
				/*
				 * 当join不参参数时，表示一直等，直到t1执行完。如果传参(比如下面传10)，表示t2会等待t1
				 * 10毫秒，10毫秒之后，t1、t2并行执行
				 */
				// t1.join(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("执行t2");
		}
	});

	static Thread t3 = new Thread(new Runnable() {

		@Override
		public void run() {
			try {
				/*
				 * t3线程准备执行时，先等待t2执行完，t3再执行
				 */
				t2.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("执行t3");
		}
	});

	public static void main(String[] args) {
		t1.start();
		t2.start();
		t3.start();
	}

}
