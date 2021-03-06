package spi.dubbo;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.common.extension.Adaptive;
import com.alibaba.dubbo.common.extension.SPI;

@SPI("hfds")
public interface IDdService {

	String sayHello();

	String getScheme();

	@Adaptive("demo")
	String getScheme(URL url);

}
