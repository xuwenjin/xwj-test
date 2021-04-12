package myclassloader;

import org.junit.Test;

/**
 * 三种类加载器
 */
public class ClassLoaderDemo {

	/**
	 * 测试根类加载器---Bootstrap Class Loader
	 * 由C++开发，是JVM虚拟机的一部分，本身不是Java类，所以打印出来是null
	 */
	@Test
	public void rootClassLoader() {
		ClassLoader cl = Object.class.getClassLoader();
		// 根类加载器打印的结果就是ull
		System.out.println("Object的类加载器：" + cl);
	}

	/**
	 * 测试扩展类加载器---Extention Class Loader
	 */
	@Test
	public void extClassLoader() {
		// ClassLoader cl = ECKeyFactory.class.getClassLoader();
		// System.out.println("DNSNameService的类加载器：" + cl);
	}

	/**
	 * 测试系统(应用)类加载器---Application Class Loader
	 */
	@Test
	public void appClassLoader() {
		ClassLoader cl = ClassLoaderDemo.class.getClassLoader();
		System.out.println("ClassLoaderDemo的类加载器：" + cl);
	}

	/**
	 * 测试类记载器过程
	 * 
	 * 自定义类加载器 ==> 系统类加载器 ==> 扩展类加载器 ==> 根类加载器
	 * 
	 * 双亲委派：一个java类加载金JVM内存的过程：
	 *  1、每个类加载器对它加载过的类都有一个缓存
	 *  2、向上委托查找(找到就返回)，向下委托加载
	 * 
	 * 双亲委派好处：
	 * 1、安全性，避免用户自定义的类替换JDK中的核心类。比如：java.lang.String
	 * 2、避免类的重复加载：相同的class文件被不同的ClassLoader加载就是不同的两个类
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
