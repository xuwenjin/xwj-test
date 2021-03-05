package spi.dubbo;

import com.alibaba.dubbo.common.extension.SPI;

@SPI("hfds")
public interface IDdService {

	String sayHello();

	String getScheme();

}
