package com.xwj.singleton;

/**
 * 测试多线程下的单例模式(99%情况下不会出现)
 * 
 * cpu是单线程的,不是多线程.前面进入的线程并没有完全跑完方法,下一个线程又进入了,执行了一部分数
 * 据运算,然后回到剩下的线程,所以会出现的生成两个对象
 * 
 * @author xuwenjin
 */
public class MyThread extends Thread {

	@Override
	public void run() {
		System.out.println(Singleton.getInstance());
	}

	public static void main(String[] args) {
		int i = 0;
		while (Singleton.identifyCode != 2) { // 如果验证码变成2,就表明生成了两个对象
			for (int j = 0; j < 2; j++) {// 模拟两个用户同时访问这个方法
				new MyThread().start();
			}
			i++;
		}
		System.out.println(i + "次后,生成了第二个sington对象");
	}
}
