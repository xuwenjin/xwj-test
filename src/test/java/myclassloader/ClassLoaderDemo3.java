package myclassloader;

import org.junit.Test;

/**
 * 自定义加载器的使用场景：热加载(SpringBoot devtools的RestartClassLoader)
 * 
 * 其核心是loadClass的加载方式，我们发现其通过修改了双亲委托机制，默认优先从自己加载，如果自己没有加载到，就从parent进行加载。
 * (每个类加载器对它加载过的类都有一个缓存，修改了代码的类在类加载器缓存里已经被加载过了，如果不打破双亲委派，则会直接从缓存中取，这样就没办法热加载了)
 */
public class ClassLoaderDemo3 {

	/**
	 * 同一个类被同一个类加载器加载，只会被加载一次，加载出来的对象是同一个
	 * 
	 * 使用ClassLoader.loadClass()，会进行正常的双亲委派
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void test1() throws Exception {
		MyFileClassLoader classLoader1 = new MyFileClassLoader("D:/");
		MyFileClassLoader classLoader2 = new MyFileClassLoader("D:/", classLoader1);

		// 双亲委派，递归调用父类加载器(AppClassLoader->ExtClassLoader->BootStrapClassLoader)，一直找不到，则自己加载
		Class cls1 = classLoader1.loadClass("jvm.Test");
		// 指定了父类加载器是classLoader1，则先看classLoader1有没有加载过。已经加载过了，则直接取出来返回
		Class cls2 = classLoader2.loadClass("jvm.Test");

		System.out.println(cls1.hashCode());
		System.out.println(cls2.hashCode());

		// 所有cls1和cls2是同一个类
		System.out.println(cls1 == cls2);
	}

	/**
	 * 同一个类被不同类加载器加载，会被加载多次，加载出来的对象是不同的
	 * 
	 * 使用自定义的类加载器MyFileClassLoader.findClass()，会破坏双亲委派机制
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void test2() throws Exception {
		MyFileClassLoader classLoader1 = new MyFileClassLoader("D:/");
		MyFileClassLoader classLoader2 = new MyFileClassLoader("D:/", classLoader1);

		// 避开双亲委派机制，从而实现一个类被多次加载，实现热加载
		Class cls1 = classLoader1.findClass("jvm.Test");
		Class cls2 = classLoader2.findClass("jvm.Test");
		System.out.println(cls1.hashCode());
		System.out.println(cls2.hashCode());

		// 所有cls1和cls2不是同一个类
		System.out.println(cls1 == cls2);
	}

}
