package testThread;

/**
 * synchronized能够实现原子性(同步)、可见性
 * 
 * @author xuwenjin
 */
public class SynchronizedDemo {

	//共享变量
	private boolean ready = false;
	private int result = 0;
	private int number = 1;

	/**
	 * 写操作
	 */
	public synchronized void write() {
		ready = true; //1.1
		number = 2;   //1.2
	}

	/**
	 * 读操作
	 */
	public synchronized void read() {
		if (ready) {             //2.1
			result = number * 3; //2.2
		}
		System.out.println("result:" + result);
	}

	//内部线程类
	private class WriteReadThread extends Thread {
		
		private  boolean flag = false;
		
		public WriteReadThread(boolean flag){
			this.flag = flag;
		}
		
		@Override
		public void run() {
			if (flag) {
				write();
			}else {
				read();
			}
		}
	}
	
	public static void main(String[] args) {
		SynchronizedDemo demo = new SynchronizedDemo();
		//启动线程执行写操作
		demo.new WriteReadThread(true).start();
		//启动线程执行读操作
		demo.new WriteReadThread(false).start();
	}

}
