package com.xwj.proxy;

public class Train {
	
	public void run(){
		System.out.println("火车行驶中");
	}
	
	public final void go() {
		System.out.println("这是一个final方法");
	}

}
