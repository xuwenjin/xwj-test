package com.xwj.test.spring.proxy.staproxy;

import com.xwj.test.spring.proxy.Subject;

/**
 * 测试静态代理
 * 
 * @author XU.WJ 2018年2月26日
 */
public class Test {
	
	public static void main(String[] args) {
		Subject sub = new Proxy(new RealSubject());
		String info = sub.say("xwj", 26);
		System.out.println(info);
	}

}
