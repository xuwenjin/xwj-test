package com.xwj.proxy.dynaproxy.jdk;

import com.xwj.proxy.RealSubject;
import com.xwj.proxy.Subject;

/**
 * 测试动态代理
 * 
 * @author XU.WJ 2018年2月26日
 */
public class Test {

	public static void main(String[] args) {
		// 实例化代理操作类
		JdkProxy handler = new JdkProxy();
		// 绑定实际对象
		Subject sub = (Subject) handler.bind(new RealSubject());

		String info = sub.say("xuwenjin", 25);
		System.out.println("返回结果：" + info);
	}

}
