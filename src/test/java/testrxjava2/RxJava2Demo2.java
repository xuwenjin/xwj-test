package testrxjava2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

/**
 * 创建观察者的各种方式
 */
public class RxJava2Demo2 {

	public static void main(String[] args) {
		// just() 创建一个被观察者，并发送事件，发送的事件不可以超过10个以上
		Observable.just(1, 2, 3).subscribe(new MyObserver<Integer>());

		System.out.println("---------------------fromArray----------------------");
		// fromArray() 这个方法和 just() 类似，只不过 fromArray 可以传入多于10个的变量，并且可以传入一个数组
		Integer array[] = { 1, 2, 3, 4 };
		Observable.fromArray(array).subscribe(new MyObserver<Integer>());

		System.out.println("----------------------fromIterable---------------------");
		// fromIterable() 直接发送一个 List 集合数据给观察者
		List<Integer> list = new ArrayList<>();
		list.add(0);
		list.add(1);
		list.add(2);
		list.add(3);
		Observable.fromIterable(list).subscribe(new MyObserver<Integer>());

		System.out.println("----------------------fromCallable---------------------");
		// fromCallable() 接收一个Callable对象，并将Callbale的返回值发送给观察者
		Observable.fromCallable(new Callable<Integer>() {
			@Override
			public Integer call() throws Exception {
				return 1;
			}
		}).subscribe(new MyObserver<Integer>());

		System.out.println("----------------------timer---------------------");
		// timer() 当到指定时间(delay)后就会发送一个 0L的值给观察者。
		Observable.timer(3, TimeUnit.SECONDS).subscribe(new MyObserver<Long>());

		System.out.println("----------------------interval---------------------");
		// interval() 每隔一段时间(period)就会发送一个事件，这个事件是从0开始，不断增1的数字
		Observable.interval(1, TimeUnit.SECONDS).subscribe(new MyObserver<Long>());

		for (;;)
			;
	}

}
