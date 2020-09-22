package testrxjava2;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import testrxjava2.po.MyPerson;

/**
 * 转换操作符
 */
public class RxJava2Demo3 {

	public static void main(String[] args) {
		// map() 将被观察者发送的数据类型转变成其他的类型
		// map() 是一对一的，integer -> String
		Observable.just(1, 2, 3).map(d -> "I am " + d).subscribe(new MyObserver<String>());

		System.out.println("---------------------flatMap----------------------");
		// flatMap() 其实与 map() 类似，但是 flatMap() 返回的是一个 Observerable
		// flatMap() 是一对多的，MyPerson -> Observable<List>
		List<MyPerson> personList = new ArrayList<>();
		Observable.fromIterable(personList)
			.flatMap(person -> Observable.fromIterable(person.getPlanList()))
			.flatMap(plan -> Observable.fromIterable(plan.getActionList()))
			.subscribe(new MyObserver<String>());

	}

}
