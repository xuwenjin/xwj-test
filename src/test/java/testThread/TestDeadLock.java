package testThread;

/**
 * 测试线程死锁
 * 
 * @author xuwenjin
 */
public class TestDeadLock {
	
	private static Object locka = new Object();
	private static Object lockb = new Object();
	
	/**
	 * 使用synchronized方式死锁
	 * 
	 * 线程thread1先获取锁locka，然后在同步块里嵌套锁lockb。而线程thread2获取锁lockb，然后在同步块里嵌套锁locka 
	 * 此时已经被线程thread1拥有，而thread1在等待lockb，而lockb被thread2拥有，thread2在等待locka……无线循环
	 * 
	 */
	private void deadLock(){
		Thread thread1 = new Thread(new Runnable() { //线程1
			@Override
			public void run() {
				synchronized (locka) {
					try {
						System.out.println(Thread.currentThread().getName()+" get locka ing!");  
						Thread.sleep(500);
						System.out.println(Thread.currentThread().getName()+" after sleep 500ms");  
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" need lockb!Just waiting!");
					synchronized (lockb) {
						System.out.println(Thread.currentThread().getName()+" get lockb ing!");  
					}
				}
			}
		});
		Thread thread2 = new Thread(new Runnable() { //线程2
			@Override
			public void run() {
				synchronized (lockb) {
					try {
						System.out.println(Thread.currentThread().getName()+" get lockb ing!");  
						Thread.sleep(500);
						System.out.println(Thread.currentThread().getName()+" after sleep 500ms");  
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName()+" need locka!Just waiting!");
					synchronized (locka) {
						System.out.println(Thread.currentThread().getName()+" get locka ing!");  
					}
				}
			}
		});
		
		thread1.start();
		thread2.start();
	}
	
	public static void main(String[] args) {
		new TestDeadLock().deadLock();
	}

}
