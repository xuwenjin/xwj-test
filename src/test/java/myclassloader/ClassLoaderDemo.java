package myclassloader;

import org.junit.Test;

/**
 * 三种类加载器
 */
public class ClassLoaderDemo {

	/**
	 * 测试根类加载器
	 */
	@Test
	public void rootClassLoader() {
		ClassLoader cl = Object.class.getClassLoader();
		// 根类加载器打印的结果就是ull
		System.out.println("Object的类加载器：" + cl);
	}

	/**
	 * 测试扩展类加载器
	 */
	@Test
	public void extClassLoader() {
		// ClassLoader cl = ECKeyFactory.class.getClassLoader();
		// System.out.println("DNSNameService的类加载器：" + cl);
	}

	/**
	 * 测试系统(应用)类加载器
	 */
	@Test
	public void appClassLoader() {
		ClassLoader cl = ClassLoaderDemo.class.getClassLoader();
		System.out.println("ClassLoaderDemo的类加载器：" + cl);
	}

	/**
	 * 测试类记载器过程
	 * 
	 * 系统类加载器 => 扩展类加载器 => 根类加载器
	 * 
	 * 子类加载器会一直向上找，找不到再自己加载 ---> 双亲委派
	 * 
	 */
	@Test
	public void classLoader() {
		ClassLoader cl = ClassLoaderDemo.class.getClassLoader();
		while (cl != null) {
			System.out.println("当前类加载器：" + cl.getParent());
			cl = cl.getParent();
		}
		System.out.println("当前类加载器：" + cl);

		// 如果自定义的类和包路径，跟jdk一模一样，会优先加载父包里的。但是在编译期，本地代码会优先使用本地类
		cl = String.class.getClassLoader();
		System.out.println("String的类加载器：" + cl);
		System.out.println(String.valueOf(1));
	}

}
