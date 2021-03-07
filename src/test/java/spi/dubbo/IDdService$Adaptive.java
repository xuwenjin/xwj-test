package spi.dubbo;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * 使用javassist生成的IDdService动态子类
 */
public class IDdService$Adaptive implements spi.dubbo.IDdService {
	
	public java.lang.String getScheme() {
		throw new UnsupportedOperationException("method public abstract java.lang.String spi.dubbo.IDdService.getScheme() of interface spi.dubbo.IDdService is not adaptive method!");
	}

	public java.lang.String getScheme(com.alibaba.dubbo.common.URL arg0) {
		if (arg0 == null)
			throw new IllegalArgumentException("url == null");
		com.alibaba.dubbo.common.URL url = arg0;
		String extName = url.getParameter("demo", "hfds");
		if (extName == null)
			throw new IllegalStateException("Fail to get extension(spi.dubbo.IDdService) name from url(" + url.toString() + ") use keys([demo])");
		spi.dubbo.IDdService extension = (spi.dubbo.IDdService) ExtensionLoader.getExtensionLoader(spi.dubbo.IDdService.class).getExtension(extName);
		return extension.getScheme(arg0);
	}

	public java.lang.String sayHello() {
		throw new UnsupportedOperationException("method public abstract java.lang.String spi.dubbo.IDdService.sayHello() of interface spi.dubbo.IDdService is not adaptive method!");
	}
}