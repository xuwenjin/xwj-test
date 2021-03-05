package spi.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * 测试Dubbo的spi
 * 
 * 1、在META-INFO/dubbo下，新建文件名称是服务类型的完全限定名，如spi.dubbo.IDdService
 * 2、在spi.dubbo.IDdService文件中，写入key = value(实现类全类名)
 * 3、在IDdService接口上，一定要加上@SPI注解
 * 
 */
public class TestDubboSpi {

	public static void main(String[] args) {
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

}
