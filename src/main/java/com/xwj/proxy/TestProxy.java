package com.xwj.proxy;

import org.junit.Test;

import com.xwj.proxy.dynaproxy.cglib.CglibProxy;
import com.xwj.proxy.dynaproxy.jdk.JdkProxy;
import com.xwj.proxy.staproxy.ProxySubject;

/**
 * 测试类
 * 
 * @author xwj
 *
 */
public class TestProxy {

	/**
	 * 测试静态代理
	 */
	@Test
	public void testStaProxy() {
		Subject sub = new ProxySubject(new RealSubject());
		sub.say("xwj");
	}

	/**
	 * 测试jdk动态代理
	 */
	@Test
	public void testJdkProxy() {
		// 实例化代理操作类
		JdkProxy handler = new JdkProxy();
		// 代理接口的实现类
		// Subject sub = (Subject) handler.bind(new RealSubject());
		// sub.say("xuwenjin");

		// 代理没有接口的类
		Train train = (Train) handler.bind(new Train());
		train.run();
	}

	/**
	 * 测试cglib动态代理
	 */
	@Test
	public void testCglibProxy() {
		CglibProxy proxy = new CglibProxy();
		// 获取Train的代理类
		Train train = (Train) proxy.getProxy(Train.class);
		// 非final方法
		train.run();
		// final方法
		// train.go();
	}

}
