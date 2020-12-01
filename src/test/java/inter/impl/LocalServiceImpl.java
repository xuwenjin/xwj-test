package inter.impl;

import inter.IService;

public class LocalServiceImpl implements IService {

	@Override
	public String sayHello() {
		return "Hello LocalService";
	}

	@Override
	public String getScheme() {
		return "local";
	}

}
