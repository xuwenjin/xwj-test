package other.observer;

import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

/**
 * 观察者
 * 
 * @author xuwenjin 2020年11月9日
 */
public class MyObserver implements Observer {

	/**sleep时长(单位毫秒)*/
	private long sleepTime;

	public MyObserver(long sleepTime) {
		super();
		this.sleepTime = sleepTime;
	}

	/**
	 * 收到消息后，进行处理
	 */
	@Override
	public void update(Observable arg0, Object arg1) {
		System.out.println("收到消息：" + ((MyObservable) arg0).getMessage());
		try {
			TimeUnit.MILLISECONDS.sleep(sleepTime);
		} catch (InterruptedException e) {
			System.err.println(e);
		}
	}

}