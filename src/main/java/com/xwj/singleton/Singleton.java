package com.xwj.singleton;

/**
 * 单例模式-懒汉式
 * 
 * @author xuwenjin
 */
public class Singleton {

	/** singleton需要加volatile修饰符，防止对象初始化时指令重排序 */
	private static volatile Singleton singleton;

	/** 插入一个验证码，验证是否生成了多个对象 */
	public static int identifyCode;

	private Singleton() {
	}

	/**
	 * 存在线程安全
	 */
	public static Singleton getInstance() {
		if (singleton == null) {
			singleton = new Singleton();
			identifyCode++;
		}
		return singleton;
	}

	/**
	 * 在方法上加同步锁，有损效率
	 */
	public synchronized static Singleton getInstance2() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

	/**
	 * 双重校验，效果更好
	 */
	public static Singleton getInstance3() {
		if (singleton == null) {
			synchronized (Singleton.class) {// 在代码块中加同步锁,如果已经有线程访问,当前线程转为阻塞状态
				if (singleton == null) { //// 当第二个线程访问时，已经不为null了，那么不再创建对象
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}

}
