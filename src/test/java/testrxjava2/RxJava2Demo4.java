package testrxjava2;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

/**
 * observeOn()：指定观察者的线程，每指定一次就会生效一次。
 */
public class RxJava2Demo4 {

	public static void main(String[] args) throws IOException {
		/**
		 * RxJava 中的调度器：
		 * 
		 * observeOn(): 指定观察者的线程，每指定一次就会生效一次(一个观察者，只产生一个线程)
		 * 
		 * Schedulers.io()	                用于 IO 密集型任务 --> 在此调度器接收到任务后，会使用线程池。如果每次都没有空闲线程使用，可以无上限的创建新线程。
		 * Schedulers.newThread()	创建一个新的线程    --> 在每执行一个任务时创建一个新的线程，不具有线程缓存机制
		 * Schedulers.trampoline()  在当前线程立即执行任务，如果当前线程有任务在执行，则会将其暂停，等插入进来的任务执行完之后，再将未完成的任务接着执行
		 * Schedulers.single()      拥有一个线程单例，所有的任务都在这一个线程中执行，当此线程中有任务执行时，其他任务将会按照先进先出的顺序依次执行
		 */
		Observable.create((e) -> {
			int array[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			for (int i = 0; i < array.length; i++) {
				int value = array[i];
				System.out.println("currentThread:" + Thread.currentThread().getName() + "----->value");
				e.onNext(value);
			}
		}).observeOn(Schedulers.single()).subscribe(new MyObserver<Object>());

		System.in.read();
	}

}
