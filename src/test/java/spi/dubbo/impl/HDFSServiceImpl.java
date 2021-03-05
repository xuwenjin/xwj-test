package spi.dubbo.impl;

import spi.dubbo.IDdService;

public class HDFSServiceImpl implements IDdService {

	@Override
	public String sayHello() {
		System.out.println("Hello HDFSService");
		return "Hello HDFSService";
	}

	@Override
	public String getScheme() {
		System.out.println("hdfs");
		return "hdfs";
	}

}
