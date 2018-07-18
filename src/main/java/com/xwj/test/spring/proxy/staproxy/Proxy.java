package com.xwj.test.spring.proxy.staproxy;

import com.xwj.test.spring.proxy.Subject;

/**
 * 静态代理类Proxy
 * 
 * @author XU.WJ 2018年2月26日
 */
public class Proxy implements Subject {

	private RealSubject realSubject = null;

	public Proxy(RealSubject realSubject) {
		this.realSubject = realSubject;
	}

	@Override
	public String say(String name, int age) {
		// 在转调具体的目标对象之前，可以执行一些功能处理，比如权限判断

		// 转调具体的目标对象
		return realSubject.say(name, age);

		// 在转调具体的目标对象之后，可以执行一些功能处理
		// 这也是代理模式的核心
	}

}
