package myclassloader;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

import org.junit.Test;

/**
 * URLClassLoader-扩展了ClassLoader，能够从本地或者网络上指定的位置加载类
 */
public class ClassLoaderDemo2 {

	/**
	 * 测试URLClassLoader加载器
	 */
	@SuppressWarnings({ "resource", "rawtypes" })
	@Test
	public void testUrlClassLoader() throws Exception {
		File file = new File("D:/");
		URI uri = file.toURI();
		URL url = uri.toURL();

		URLClassLoader classLoader = new URLClassLoader(new URL[] { url });
		System.out.println("类加载器：" + classLoader);
		System.out.println("父类加载器：" + classLoader.getParent());

		// 将jvm/Test.class加载到jvm中
		Class cls = classLoader.loadClass("jvm.Test");
		// 实例化Test类
		cls.newInstance();
	}

}
