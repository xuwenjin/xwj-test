package spi.dubbo.impl;

import com.alibaba.dubbo.common.URL;

import spi.dubbo.IDdService;

public class LocalServiceImpl implements IDdService {

//	private IBbService bbService;
//
//	/**
//	* setter注入(IOC的特性)
//	*/
//	public void setBbService(IBbService bbService) {
//		this.bbService = bbService;
//	}

	@Override
	public String getScheme() {
		System.out.println("local");

//		bbService.getScheme();

		return "local";
	}

	@Override
	public String getScheme(URL url) {
		System.out.println("local:" + url);
		return "local:" + url;
	}

}
