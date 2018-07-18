package com.xwj.test.spring.proxy.dynaproxy;

import com.xwj.test.spring.proxy.Subject;
import com.xwj.test.spring.proxy.staproxy.RealSubject;

/**
 * 测试动态代理
 * 
 * @author XU.WJ 2018年2月26日
 */
public class DynamicProxyDemo {
	
	public static void main(String[] args) {
		//实例化代理操作类
		MyHandler handler = new MyHandler();
		//绑定实际对象
		Subject sub = (Subject) handler.bind(new RealSubject());
		String info = sub.say("xuwenjin", 25);
		System.out.println(info);
	}

}
