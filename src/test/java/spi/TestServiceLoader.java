package inter;

import java.util.ServiceLoader;

/**
 * 测试ServiceLoader的用法(JDK自带的)
 * 
 * 参考文档地址：https://www.cnblogs.com/aspirant/p/10616704.html
 * 
 * 
 * 1、通过在资源目录META-INF/services中放置提供者配置文件来标识服务提供者，且文件名称是服务类型的完全限定名，如inter.IService
 * 2、定义对应的接口和实现类：IService接口、HDFSServiceImpl和LocalServiceImpl实现类
 * 3、通过ServiceLoader.load(IService.class)就可以找到IService的实现类
 * 
 * 可以看到ServiceLoader可以根据IService把定义的两个实现类找出来，返回一个ServiceLoader的实现，
 * 而ServiceLoader实现了Iterable接口，所以可以通过ServiceLoader来遍历所有在配置文件中定义的类的实例。
 * 
 * 使用场景：一般使用接口的实现类都是静态new一个实现类赋值给接口引用
 * 
 * 缺点：会一次加载所有实现类，并生成实例对象
 * 
 */
public class TestServiceLoader {

	public static void main(String[] args) {
		/**
		 * 底层原理：通过类的全路径，反射生成实例对象
		 */
		ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
		for (IService service : serviceLoader) {
			System.out.println(service.getScheme() + "=" + service.sayHello());
		}
	}

}
