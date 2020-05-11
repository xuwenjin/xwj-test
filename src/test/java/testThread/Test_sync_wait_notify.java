package testThread;

/**
 * 测试wait与notify方法
 */
public class Test_sync_wait_notify {

	final static Object obj = new Object();

	public static void main(String[] args) {
		new Thread(() -> {
			synchronized (obj) {
				System.out.println("t1--->wait start");
				try {
					// 调用wait方法后：
					// 1、当前线程就会从执行状态转变成等待状态，
					// 2、同时释放在实例对象上的锁，直到其它线程在刚才那个实例对象上调用notify方法并且释放实例对象上的锁
					obj.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t1--->wait end");
			}

			System.out.println("t1--->over");
		}).start();

		new Thread(() -> {
			synchronized (obj) {
				System.out.println("t2--->notify start");
				// notify：它就会从这个等待队列中随机选一个线程，并将其唤醒(不会释放锁)
				// notifyAll：唤醒所有等待线程
				obj.notify();
				System.out.println("t2--->notify end");

				try {
					System.out.println("t2--->sleep start");
					// sleep方法不会释放锁
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("t2--->sleep end");
			}

			System.out.println("t2--->over");
		}).start();

	}

}
