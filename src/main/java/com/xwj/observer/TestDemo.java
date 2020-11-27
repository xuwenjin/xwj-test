package com.xwj.observer;

/**
 * 测试
 */
public class TestDemo {

	public static void main(String[] args) {
		ServerManager sm = new ServerManager();
		AObserver a = new AObserver(sm);
		new BObserver(sm);
		sm.setData(5);

		// 注销观察者，以后被观察者有数据变化就不再通知这个已注销的观察者
		sm.deleteObserver(a);
		sm.setData(10);
	}

}