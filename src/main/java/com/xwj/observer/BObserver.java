package com.xwj.observer;

import java.util.Observable;
import java.util.Observer;

/**
 * 观察者B
 */
public class BObserver implements Observer {

	public BObserver(ServerManager sm) {
		super();

		// 注册加入观察者
		sm.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		System.out.println("BObserver receive:Data has changed to " + ((ServerManager) o).getData());
	}

}