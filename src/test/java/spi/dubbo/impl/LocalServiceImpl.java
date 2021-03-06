package spi.dubbo.impl;

import com.alibaba.dubbo.common.URL;

import spi.dubbo.IDdService;

public class LocalServiceImpl implements IDdService {

	@Override
	public String sayHello() {
		System.out.println("Hello LocalService");
		return "Hello LocalService";
	}

	@Override
	public String getScheme() {
		System.out.println("local");
		return "local";
	}

	@Override
	public String getScheme(URL url) {
		System.out.println("local:" + url);
		return "local:" + url;
	}

}
