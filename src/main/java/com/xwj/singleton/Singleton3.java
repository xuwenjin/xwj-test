package com.xwj.singleton;

/**
 * 单例模式-饿汉式
 * 
 * 当class文件被加载时候，就会被实例化，天生线程安全
 * 
 * @author xuwenjin
 */
public class Singleton3 {
	
	private static Singleton3 INSTANCE = new Singleton3();
	
	private Singleton3() {

	}

	public static Singleton3 getInstance() {
		return INSTANCE;
	}

}
