package testThread.AQS;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 实现Lock，自定义一把锁(使用同步器AQS)
 */
public class XwjLock implements Lock {

	private Sync sync = new Sync();

	@Override
	public void lock() {
		sync.acquire(1);
	}

	@Override
	public void lockInterruptibly() throws InterruptedException {

	}

	@Override
	public boolean tryLock() {
		return false;
	}

	@Override
	public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
		return false;
	}

	@Override
	public void unlock() {
		sync.release(1);
	}

	@Override
	public Condition newCondition() {
		return null;
	}

	/**
	 * AbstractQueuedSynchronizer 同步器
	 * 
	 * 同步器是用来构建锁和其他同步组件的基础框架，它的实现主要依赖一个int成员变量(state)来表示同步状态以及通过一个FIFO队列构成等待队列。
	 * 它的子类必须重写AQS的几个protected修饰的用来改变同步状态的方法，其他方法主要是实现了排队和阻塞机制
	 */
	private class Sync extends AbstractQueuedSynchronizer {

		private static final long serialVersionUID = 1L;

		/**
		 * 独占式获取同步状态，实现该方法需要查询当前状态并判断同步状态是否符合预期，然后再进行CAS设置同步状态
		 */
		@Override
		protected boolean tryAcquire(int arg) {
			assert arg == 1; // 断言，否则不运行下面的代码
			if (compareAndSetState(0, 1)) {
				setExclusiveOwnerThread(Thread.currentThread());
				return true;
			}
			return false;
		}

		/**
		 * 独占式释放同步状态，等待获取同步状态的线程将有机会获取同步状态
		 */
		@Override
		protected boolean tryRelease(int arg) {
			assert arg == 1; // 断言，否则不运行下面的代码
			if (!isHeldExclusively()) {
				// 如果不是当前线程占用锁,那么抛出异常
				throw new IllegalMonitorStateException();
			}
			// 清空独占线程
			setExclusiveOwnerThread(null);
			// 更新state值
			setState(0);

			return true;
		}

		/**
		 * 当前同步器是否在独占模式下被线程占用，一般该方法表示是否被当前线程所独占
		 */
		@Override
		protected boolean isHeldExclusively() {
			return getExclusiveOwnerThread() == Thread.currentThread();
		}
	}

}
