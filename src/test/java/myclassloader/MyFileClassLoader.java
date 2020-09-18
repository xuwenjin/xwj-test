package myclassloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 自定义一个类加载器
 *
 * 1、继承ClassLoader
 * 
 * 2、重写findClass方法
 */
public class MyFileClassLoader extends ClassLoader {

	/** 被加载类所在目录 */
	private String directory;

	public MyFileClassLoader(String directory) {
		// 默认父类加载器就是系统加载器(AppClassLoader)
		this.directory = directory;
	}

	public MyFileClassLoader(String directory, ClassLoader parent) {
		// 自己指定父类加载器(第一个类加载器)
		super(parent);
		this.directory = directory;
	}

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		try {
			// 将类名拼成一个class文件所在目录
			String file = directory + name.replace(".", File.separator) + ".class";
			// 构建输入流
			InputStream in = new FileInputStream(file);
			// 构建字节输出流
			ByteArrayOutputStream baos = new ByteArrayOutputStream();

			byte buf[] = new byte[1024];
			int len = -1;
			while ((len = in.read(buf)) != -1) {
				baos.write(buf, 0, len);
			}

			byte data[] = baos.toByteArray(); // 读取到的字节码的二进制数据
			in.close();
			baos.close();

			// 调用父类的方法，将class的字节码数组转换成Class对象
			return defineClass(name, data, 0, data.length);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws Exception {
		MyFileClassLoader classLoader = new MyFileClassLoader("D:/");
		Class cls = classLoader.loadClass("jvm.Test");
		cls.newInstance();
	}

}
