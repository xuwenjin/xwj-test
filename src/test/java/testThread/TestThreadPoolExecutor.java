package testThread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TestThreadPoolExecutor {

	public static void main(String[] args) {
		/**
		 * corePoolSize: 2
		 * maximumPoolSize: 5
		 * workQueue: ArrayBlockingQueue(10)
		 * 默认情况下，在创建了线程池后，线程池中的线程数为0                          ----------> 当前线程数=0，任务数=0
		 * 当任务数小于等于核心线程数时，创建线程                                                                         ----------> 当前线程数<=2，任务数>0
		 * 当任务数大于核心线程数，且任务队列未满时，将任务放入任务队列                        	   ----------> 当前线程数=2，任务数>2
		 * 当任务数大于核心线程数，且任务队列已满：                                                                     ----------> 当前线程数=2，任务数>2+5
		 *   (1)若当前线程数小于最大线程数，创建线程。                                                               ----------> 当前线程数>2 && 当前线程数<=4，任务数>2+5
		 *   (2)若当前线程数大于最大线程数，抛出异常，拒绝任务，开始使用拒绝策略拒绝      ----------> 当前线程数>4，任务数>5+4
		 *   
		 *   
		 * keepAliveTime: 10s
		 * 当线程池中的线程数大于corePoolSize时，临时线程允许空闲的最大时长为10秒(即等待新任务最长10秒)，超时后空闲线程将被回收
		 * 通过ExecutorService创建的线程池，keepAliveTime=0，表示多余的空闲线程会立即被回收
		 * 如果allowCoreThreadTimeOut=true，那核心线程也会被回收，允许空闲的最大时长也为10秒
		 *   
		 *   
		 * workQueue: ArrayBlockingQueue(10)
		 * 指定使用ArrayBlockingQueue作为缓存队列，该队列是基于数组结构的有界队列(必须指定队列大小)
		 * 通过ExecutorService创建的线程池，默认是LinkedBlockingQueue，该队列是基于单向列表的无界队列，默认大小是Integer.MAX_VALUE，所以不建议使用ExecutorService创建线程池
		 * 
		 *   
		 * handler: 拒绝策略
		 * AbortPolicy(默认)：丢弃任务并抛出RejectedExecutionException异常
		 * DiscardPolicy: 丢弃任务，但是不抛出异常(如果线程队列已满，则后续提交的任务都会被丢弃，且是静默丢弃)
		 * DiscardOldestPolicy: 丢弃队列最前面的任务，然后重新提交被拒绝的任务
		 * CallerRunsPolicy: 由调用线程处理该任务(如果任务被拒绝了，则由调用线程（提交任务的线程）直接执行此任务)
		 */
		ThreadPoolExecutor pool = new ThreadPoolExecutor(2, 4, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5));
		pool.allowCoreThreadTimeOut(true); // 允许核心线程数超时(默认false)。如果为true，则也是使用keepAliveSeconds作为允许空闲时间

		for (int i = 1; i <= 10; i++) {
			pool.execute(new MyTask(i)); // 创建任务并提交
			System.out.println(pool);
		}
		pool.shutdown();
	}

	static class MyTask implements Runnable {
		private int index;

		public MyTask(int index) {
			this.index = index;
		}

		@Override
		public void run() {
			System.out.println(Thread.currentThread().getName() + "------->" + index);
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
