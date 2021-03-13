package spi.dubbo;

import org.junit.Test;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * 测试Dubbo的spi
 * 
 * 1、在META-INFO/dubbo下，新建文件名称是服务类型的完全限定名，如spi.dubbo.IDdService
 * 2、在spi.dubbo.IDdService文件中，写入key = value(实现类全类名)
 * 3、在IDdService接口上，一定要加上@SPI注解
 */
public class TestDubboSpi {

	/**
	 * 测试ExtensionLoader.getExtensionLoader的结果是否单例
	 * 
	 * 结果：
	 * 1、对于每一个扩展接口，有且仅有一个ExtensionLoader与其对应(使用了本地缓存 EXTENSION_LOADERS)
	 * 2、对于每一个扩展点对象(接口实现类)，有且仅有一个实例对象(使用了double check(变量用volatile修饰))
	 */
	@Test
	public void testFor() {
		for (int i = 0; i < 5; i++) {
			ExtensionLoader<IDdService> loader = ExtensionLoader.getExtensionLoader(IDdService.class);
			System.out.println(loader.hashCode());

			IDdService ddService = loader.getExtension("local");
			System.out.println(ddService.hashCode());

			System.out.println("-----------------------------");
		}
	}

	/**
	 * 初始化IBbService扩展对象
	 */
	@Test
	public void initBbService() {
		// 默认扩展点
		ExtensionLoader<IBbService> loader = ExtensionLoader.getExtensionLoader(IBbService.class);
		IBbService bbService = loader.getDefaultExtension();
		bbService.getScheme();
	}

	/**
	 * 测试ExtensionLoader。按需加载实现类
	 */
	@Test
	public void testExtensionLoader() {
//		initBbService();

		// 默认扩展点
		ExtensionLoader<IDdService> loader = ExtensionLoader.getExtensionLoader(IDdService.class);

		// 使用默认扩展点。
		// 如果接口上未加@SPI，则获取不到IDdService，直接报错
		// 如果接口上加了@SPI，而未指定value，则获取ddService为null
		IDdService ddService = loader.getDefaultExtension();
		ddService.getScheme();

		// 通过name获取实现类(和spi.dubbo.IDdService文件中的key对应)
		ddService = loader.getExtension("local");
		ddService.getScheme();
	}

	/**
	 * 自适应扩展@Adaptive。作用于实现类上(优先级会高于放在接口的方法上)
	 * 
	 * 参考地址：https://zhuanlan.zhihu.com/p/87075689
	 */
	@Test
	public void testAdaptive() {
		// 默认扩展点
		ExtensionLoader<IDdService> loader = ExtensionLoader.getExtensionLoader(IDdService.class);

		// @Adaptive 自适应扩展 cachedAdaptiveClass
		// a、测试@Adaptive作用于实现类上
		IDdService ddService = loader.getAdaptiveExtension();
		ddService.getScheme();
	}

	/**
	 * 自适应扩展@Adaptive。作用于接口的方法上
	 */
	@Test
	public void testAdaptiveUrl() {
		// 默认扩展点
		ExtensionLoader<IDdService> loader = ExtensionLoader.getExtensionLoader(IDdService.class);

		// 解析url参数(?key=value)，通过key，可以得到具体的实现类
		// 生成IDdService的动态子类：IDdService$Adaptive
		// 1、先生成动态子类的字符串(ExtensionLoader.createAdaptiveExtensionClassCode)，
		// 2、然后使用javassist(默认)进行Compiler，得到IDdService的字节码文件对象(Class对象)
		URL url = URL.valueOf("dubbo://localhost:8888/test?demo=local");
		IDdService ddService = loader.getAdaptiveExtension();
		ddService.getScheme(url);
	}

}
