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
		 * Schedulers.io()	用于 IO 密集型任务，如果异步阻塞 IO 操作
		 * 
		 * Schedulers.newThread()	创建一个新的线程
		 * 
		 */
		Observable.create((e) -> {
			System.out.println("currentThread:" + Thread.currentThread().getName());
			Integer array[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
			for (int i = 0; i < array.length; i++) {
				e.onNext(array[i]);
			}
		}).observeOn(Schedulers.newThread()).subscribe(new MyObserver<Object>());

		System.in.read();
	}

}
