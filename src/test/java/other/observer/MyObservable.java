package other.observer;

import java.util.Observable;

/**
 * java自带的，实现被观察者
 * 
 * @author xuwenjin 2020年11月9日
 */
public class MyObservable extends Observable {

	/**消息*/
	private String message;

	public String getMessage() {
		return message;
	}

	/**
	 * 会阻塞
	 */
	public void setMessage(String message) {
		this.message = message;

		// 只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
		setChanged();

		// 通知消费者
		notifyObservers();
	}

}