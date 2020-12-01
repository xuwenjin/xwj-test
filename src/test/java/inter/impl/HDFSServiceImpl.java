package inter.impl;

import inter.IService;

public class HDFSServiceImpl implements IService {

	@Override
	public String sayHello() {
		return "Hello HDFSService";
	}

	@Override
	public String getScheme() {
		return "hdfs";
	}

}
