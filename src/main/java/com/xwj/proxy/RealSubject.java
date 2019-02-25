package com.xwj.proxy;

import com.xwj.proxy.Subject;

/**
 * 静态代理-目标实现类
 * 
 * @author XU.WJ 2018年2月26日
 */
public class RealSubject implements Subject {

	@Override
	public void say(String name) {
		System.out.println("我是真实对象~~~");
	}

}
