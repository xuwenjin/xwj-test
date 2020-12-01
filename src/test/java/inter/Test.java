package inter;

import java.util.ServiceLoader;

/**
 * 测试ServiceLoader的用法
 * 
 * 参考文档地址：https://www.cnblogs.com/aspirant/p/10616704.html
 */
public class Test {

	public static void main(String[] args) {
		/*
		 * 可以看到ServiceLoader可以根据IService把定义的两个实现类找出来，返回一个ServiceLoader的实现，
		 * 而ServiceLoader实现了Iterable接口，所以可以通过ServiceLoader来遍历所有在配置文件中定义的类的实例。
		 */
		ServiceLoader<IService> serviceLoader = ServiceLoader.load(IService.class);
		for (IService service : serviceLoader) {
			System.out.println(service.getScheme() + "=" + service.sayHello());
		}
	}

}
