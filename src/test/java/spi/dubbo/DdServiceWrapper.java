package spi.dubbo;

import com.alibaba.dubbo.common.URL;

import spi.dubbo.IDdService;

/**
 * Dubbo实现的AOP功能--可以在IDdService的所有实现类前后执行自定义逻辑(自己感觉像静态代理模式)
 * 
 * 操作步骤：
 * 1、自定义一个Wrapper类实现IDdService接口
 * 2、在META-INFO/dubbo/spi.dubbo.IDdService文件中，加上内容：spi.dubbo.DdServiceWrapper
 */
public class DdServiceWrapper implements IDdService {

	private IDdService ddService;

	public DdServiceWrapper(IDdService ddService) {
		this.ddService = ddService;
	}

	@Override
	public String getScheme() {
		System.out.println("wrapper~~~~~~getScheme()~~~~~~前");
		ddService.getScheme();
		System.out.println("wrapper~~~~~~getScheme()~~~~~~后");
		return "wrapper";
	}

	@Override
	public String getScheme(URL url) {
		System.out.println("wrapper~~~~~~getScheme(url)~~~~~~前");
		ddService.getScheme(url);
		System.out.println("wrapper~~~~~~getScheme(url)~~~~~~后");
		return "wrapper:" + url;
	}

}
