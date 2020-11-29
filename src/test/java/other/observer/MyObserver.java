package other.observer;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

/**
 * 观察者
 */
public class MyObserver implements Observer {

	@Override
	public void update(Observable arg0, Object arg1) {
//		System.out.println("收到消息：" + ((MyObservable) arg0).getMessage());
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}