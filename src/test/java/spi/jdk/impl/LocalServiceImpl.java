package spi.jdk.impl;

import spi.jdk.IBaseService;

public class LocalServiceImpl implements IBaseService {

	@Override
	public String sayHello() {
		return "Hello LocalService";
	}

	@Override
	public String getScheme() {
		return "local";
	}

}
