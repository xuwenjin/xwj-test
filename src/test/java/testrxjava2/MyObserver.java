package testrxjava2;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * 自定义观察者
 */
public class MyObserver<T> implements Observer<T> {

	@Override
	public void onSubscribe(Disposable d) {
		System.out.println("onSubscribe: " + d);
	}

	@Override
	public void onNext(T s) {
		try {
			TimeUnit.MILLISECONDS.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("currentThread:" + Thread.currentThread().getName());
		System.out.println("onNext: " + s);
	}

	@Override
	public void onError(Throwable e) {
		System.out.println("onError: " + e);
	}

	@Override
	public void onComplete() {
		System.out.println("onComplete");
	}

}
