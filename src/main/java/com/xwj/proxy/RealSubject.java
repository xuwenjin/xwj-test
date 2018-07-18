package com.xwj.proxy;

import com.xwj.proxy.Subject;

/**
 * 静态代理-目标实现类
 * 
 * @author XU.WJ 2018年2月26日
 */
public class RealSubject implements Subject {

	@Override
	public String say(String name, int age) {
		return "name:" + name + " age:" + age;
	}
	
	public void eat(){
		System.out.println("我要吃");
	}
	
	public void drink(){
		System.out.println("我要喝");
	}
	
}
