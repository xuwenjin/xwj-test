package com.xwj.singleton;

/**
 * 单例模式-枚举
 * 
 * 解决序列化的问题
 * 
 * @author xuwenjin
 */
public class Singleton4 {

	/**
	 * 枚举变成class文件时，变量会变成final staitc的，所以只会被实例化一次
	 * 
	 * @author xuwenjin
	 */
	private enum EnmuSingleton {
		INSTANCE;
		private Singleton4 singleton4;

		private EnmuSingleton() {
			singleton4 = new Singleton4();
		}

		public Singleton4 getInstance() {
			return singleton4;
		}
	}

	public static Singleton4 getInstance() {
		return EnmuSingleton.INSTANCE.getInstance();
	}

}
