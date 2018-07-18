package com.xwj.singleton;

/**
 * 单例模式-静态内部类
 * 
 * 既实现了线程安全，又避免了同步带来的性能影响
 * 
 * @author xuwenjin
 */
public class Singleton2 {

	private Singleton2() {

	}

	public static Singleton2 getInstance() {
		return LazyHolder.INSTANCE;
	}

	private static class LazyHolder {
		private static final Singleton2 INSTANCE = new Singleton2();
	}

}
