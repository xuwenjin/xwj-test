package com.xwj.proxy.dynaproxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * JDK动态代理类JdkProxy
 * 
 * @author XU.WJ 2018年2月26日
 */
public class JdkProxy implements InvocationHandler {
	
	private Object target; //代理中含有具体实现类的引用
	
	public Object bind(Object obj){ //绑定具体实现类
		this.target = obj;
		/*
		 * 通过反射机制获取代理对象
		 * 
		 * loader 类加载器
		 * interfaces 实现接口
		 */
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
	}

	/**
	 * 参数：
	 * proxy 被代理对象
	 * method 被代理对象的方法
	 * args 方法的参数
	 * 		
	 * @return 方法的返回值
	 */
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("前事件处理...");
		Object obj = method.invoke(target, args);
		System.out.println("后事件处理...");
		return obj;
	}

}
