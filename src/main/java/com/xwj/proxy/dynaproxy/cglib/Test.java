package com.xwj.proxy.dynaproxy.cglib;

/**
 * cglib动态代理测试
 * 
 * @author xuwenjin
 */
public class Test {

	public static void main(String[] args) {
		CglibProxy proxy = new CglibProxy();
		//获取Train的代理类
		Train train = (Train) proxy.getProxy(Train.class);
		train.run();
	}

}
