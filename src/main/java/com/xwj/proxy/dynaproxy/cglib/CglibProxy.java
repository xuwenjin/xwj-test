package com.xwj.proxy.dynaproxy.cglib;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 * 动态代理-cglib
 * 
 * @author xuwenjin
 */
public class CglibProxy implements MethodInterceptor {
	
	private Enhancer enhancer = new Enhancer();
	
	/**
	 * 创建代理类
	 * @param clazz
	 * @return
	 */
	public Object getProxy(Class<?> clazz){
		enhancer.setSuperclass(clazz); //设置创建子类的类(即设置父类)
		enhancer.setCallback(this);
		return enhancer.create();
	}

	/**
	 * 拦截所有目标类方法的调用
	 * 
	 * obj 目标类的实例
	 * method 目标类的方法
	 * args 方法的参数
	 * proxy 代理类的实例
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		System.out.println("前事件处理...");
		Object retObj = proxy.invokeSuper(obj, args); //代理类调用父类的方法
		System.out.println("后事件处理...");
		return retObj;
	}

}
