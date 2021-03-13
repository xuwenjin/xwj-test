package spi.dubbo.impl;

import spi.dubbo.IBbService;

public class SonServiceImpl implements IBbService {

	@Override
	public String getScheme() {
		System.out.println("son");
		return null;
	}

}
