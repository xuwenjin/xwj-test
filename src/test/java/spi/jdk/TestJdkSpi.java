package spi.jdk;

import java.util.ServiceLoader;

import org.junit.Test;

import spi.jdk.IBaseService;

/**
 * 测试ServiceLoader的用法(JDK自带的)
 * 
 * 参考文档地址：https://www.cnblogs.com/aspirant/p/10616704.html
 * 
 * 
 * 1、通过在资源目录META-INF/services下，新建文件名称是服务类型的完全限定名，如spi.jdk.IBaseService
 * 2、定义对应的接口和实现类：IBaseService接口、HDFSServiceImpl和LocalServiceImpl实现类
 * 3、通过ServiceLoader.load(IBaseService.class)就可以找到IBaseService的实现类
 * 
 * 可以看到ServiceLoader可以根据IBaseService把定义的两个实现类找出来，返回一个ServiceLoader的实现，
 * 而ServiceLoader实现了Iterable接口，所以可以通过ServiceLoader来遍历所有在配置文件中定义的类的实例。
 * 
 * 使用场景：一般使用接口的实现类都是静态new一个实现类赋值给接口引用
 * 
 * 缺点：会一次加载所有实现类，并生成实例对象
 * 
 */
public class TestJdkSpi {

	/**
	 * 测试ServiceLoader.load的结果是否单例
	 * 
	 * 结果：
	 * 1、每一个扩展点接口的ServiceLoader都是一个新的实例
	 * 2、每一个扩展点接口实现类都是一个新的实例
	 */
	@Test
	public void testFor() {
		for (int i = 0; i < 5; i++) {
			ServiceLoader<IBaseService> serviceLoader = ServiceLoader.load(IBaseService.class);
			System.out.println(serviceLoader.hashCode());

			IBaseService service = serviceLoader.iterator().next();
			System.out.println(service.hashCode());

			System.out.println("-----------------------------");

		}
	}

	@Test
	public void test1() {
		/**
		 * 底层原理：通过类的全路径，反射生成实例对象
		 */
		ServiceLoader<IBaseService> serviceLoader = ServiceLoader.load(IBaseService.class);
		for (IBaseService service : serviceLoader) {
			System.out.println(service.getScheme() + "=" + service.sayHello());
		}
	}

}
