package testReflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.junit.Test;

/**
 * 字节码文件：java源文件(*.java)进行编译(javac)之后的文件(*.class)。
 * 
 * 字节码文件对象：jvm把字节码文件加载到jvm内存中之后，jvm就认为这个字节码文件就是一个字节码文件对象
 * (java中，万物皆对象，字节码文件对象也是一个对象(Class))
 * 
 * @author xuwenjin 2021年2月26日
 */
public class TestClass {

	/**
	 * 在一次程序的运行过程中，通过同一个类创建的对象得到的字节码文件对象是同一个
	 * 
	 * 获取字节码文件对象三种方式：
	 *	1、obj.getClass()
	 *  2、类型.class
	 *  3、Class.forName("类全路径")
	 */
	@Test
	public void getObjClass() throws ClassNotFoundException {
		Person p1 = new Person();
		Person p2 = new Person();

		// 1、obj.getClass()
		Class<?> cl1 = p1.getClass();
		Class<?> cl2 = p2.getClass();
		System.out.println(cl1);
		System.out.println(cl2);
		System.out.println(cl1 == cl2); // true

		// 2、类型.class
		// 这里的类型可以是基本类型
		Class<?> cl3 = Person.class;
		System.out.println(cl1 == cl3); // true
		System.out.println(int.class);
		System.out.println(Integer.class);

		// 3、Class.forName("类全路径")
		Class<?> cl4 = Class.forName("testReflect.Person"); // 一定要是全路径，不然会报错
		System.out.println(cl1 == cl4); // true
	}

	/**
	 * 字节码文件对象包含：
	 * 1、构造方法对象(类型：Constructor)
	 * 2、成员变量对象(类型：Field)
	 * 3、成员方法对象(类型：Method)
	 */

	/**
	 * 测试构造方法对象
	 */
	@SuppressWarnings("rawtypes")
	@Test
	public void testConstructor() throws Exception {
		// 获取字节码文件对象
		Class<?> cls = Class.forName("testReflect.Person");

		// 获取字节码文件对象中的(所有)构造方法对象
		// getDeclaredConstructors(): 可以得到所有的构造器对象，不管是公有还是私有的
		// getConstructors(): 只能获取公有的
		Constructor[] cons = cls.getConstructors();
		System.out.println(cons.length);
		System.out.println(cons[0]);
		System.out.println(cons[1]);

		// 使用无参构造方法对象，创建Person对象
		Person p = (Person) cons[0].newInstance();
		p.eat();

		Person p1 = (Person) cons[0].newInstance();
		// 同一字节码文件对象，通过构造方法对象实例化的对象不是同一个
		System.out.println(p == p1); // false

		// 使用有参构造方法对象，创建Person对象
		Person p2 = (Person) cons[1].newInstance("abc");
		p2.eat();

		Constructor con = cls.getConstructor(String.class);
		Person p3 = (Person) con.newInstance("ddd");
		p3.eat();
	}

	/**
	 * 测试成员方法对象
	 */
	@Test
	public void testMethod() throws Exception {
		// 获取字节码文件对象
		Class<?> cls = Class.forName("testReflect.Person");

		// 获取字节码文件对象中的(所有)成员方法对象
		// getDeclaredMethods(): 得到当前类中的所有Method
		// getMethods(): 得到自己及所有父类中的公有的Method
		Method[] methods = cls.getDeclaredMethods();
		for (Method method : methods) {
			System.out.println(method);
		}

		// 得到指定的成员方法对象
		Method method = cls.getMethod("drink", String.class);
		System.out.println(method);
		// 执行方法
		Object instance = cls.newInstance();
		Object result = method.invoke(instance, "xwj"); // result为执行方法后的返回值
		System.out.println(result);

		// 如果是private方法，必须得设置访问权限才能调用：method.setAccessible(true);
	}

	/**
	 * 测试成员变量对象
	 */
	@Test
	public void testField() throws Exception {
		// 获取字节码文件对象
		Class<?> cls = Class.forName("testReflect.Person");

		// 获取字节码文件对象中的(所有)成员变量对象
		// getDeclaredFields(): 得到当前类中的所有Field
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field);
		}

		// 得到指定的成员变量对象
		Field id = cls.getDeclaredField("id");
		id.setAccessible(true); // private变量，需要改变访问权限
		System.out.println(id);

		Object instance = cls.newInstance();
		id.set(instance, "ccc"); // 对变量赋值
		System.out.println(instance); // Person [tId=null, id=ccc]
	}

}
