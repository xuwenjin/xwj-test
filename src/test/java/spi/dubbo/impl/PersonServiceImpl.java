package spi.dubbo.impl;

import com.alibaba.dubbo.common.extension.Adaptive;

import spi.dubbo.IBbService;

@Adaptive
public class PersonServiceImpl implements IBbService {

	@Override
	public String getScheme() {
		System.out.println("person");
		return null;
	}

}
