package com.xwj.singleton;

import org.junit.Test;

/**
 * 测试单例模式
 * 
 * @author xuwenjin
 */
public class TestSingleton {

	@Test
	public void test1() {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1 == s2);
	}
	
	@Test
	public void test2() {
		Singleton2 s1 = Singleton2.getInstance();
		Singleton2 s2 = Singleton2.getInstance();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1 == s2);
	}
	
	@Test
	public void test3() {
		Singleton3 s1 = Singleton3.getInstance();
		Singleton3 s2 = Singleton3.getInstance();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1 == s2);
	}
	
	@Test
	public void test4() {
		Singleton4 s1 = Singleton4.getInstance();
		Singleton4 s2 = Singleton4.getInstance();
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(s1 == s2);
	}

}
