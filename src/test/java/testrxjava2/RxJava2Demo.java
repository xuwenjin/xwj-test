package testrxjava2;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * 提供一套异步编程的 API，这套 API 是基于观察者模式的，而且是链式调用的
 * 
 * RxJava 有以下三个基本的元素：
 * 
 * 被观察者（Observable）
 * 
 * 观察者（Observer）
 * 
 * 订阅（subscribe）
 */
public class RxJava2Demo {

	public static void main(String[] args) {
		// create() 创建一个被观察者
		Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
			@Override
			public void subscribe(ObservableEmitter<String> e) throws Exception {
				// onNext() 发送该事件时，观察者会回调 onNext() 方法
				e.onNext("Hello Observer");
				// onComplete() 发送该事件时，观察者会回调onComplete()方法，当发送该事件之后，其他事件将不会继续发送
				e.onComplete();
			}
		});

		// 创建一个消费者
		Consumer<String> consumer = new Consumer<String>() {
			@Override
			public void accept(String t) throws Exception {
				System.out.println("consumer接受信息：" + t);
			}
		};

		// 消费者订阅
		observable.subscribe(consumer);

		// 创建一个观察者
		Observer<String> observer = new Observer<String>() {
			@Override
			public void onSubscribe(Disposable d) {
				System.out.println("onSubscribe: " + d);
			}

			@Override
			public void onNext(String s) {
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
		};

		// 观察者订阅
		observable.subscribe(observer);

	}

}
