package spi.dubbo.impl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Activate;

import spi.dubbo.IDdService;

//@Adaptive
@Activate(group = "hddd")
public class HDFSServiceImpl implements IDdService {

	@Override
	public String getScheme() {
		System.out.println("hdfs");
		return "hdfs";
	}

	@Override
	public String getScheme(URL url) {
		System.out.println("hdfs:" + url);
		return "hdfs:" + url;
	}

}
