package com.xwj.test.spring.proxy.dynaproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

/**
 * 动态代理类MyHandler
 * 
 * @author XU.WJ 2018年2月26日
 */
public class MyHandler implements InvocationHandler {
	
	private Object obj; //代理中含有具体实现类的引用
	
	public Object bind(Object obj){ //绑定具体实现类
		this.obj = obj;
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this); //获取代理对象
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass()); //class $Proxy0
		System.out.println(method.getName());
		System.out.println(Arrays.toString(args));
		
		Object temp = method.invoke(this.obj, args);
		return temp;
	}

}
