package spi.jdk.impl;

import spi.jdk.IBaseService;

public class HDFSServiceImpl implements IBaseService {

	@Override
	public String sayHello() {
		return "Hello HDFSService";
	}

	@Override
	public String getScheme() {
		return "hdfs";
	}

}
