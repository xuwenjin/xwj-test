package spi.dubbo;

import com.alibaba.dubbo.common.extension.SPI;

@SPI("person")
public interface IBbService {

	String getScheme();

}
