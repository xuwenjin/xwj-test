package other.observer;

import java.util.Observable;

/**
 * 被观察者
 */
public class MyObservable extends Observable {

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
		
		// 只有在setChange()被调用后，notifyObservers()才会去调用update()，否则什么都不干。
		setChanged();
		
		// 通知消费者
		notifyObservers();
	}

}