package test;

import java.beans.PropertyDescriptor;

/**
 * 测试PropertyDescriptor
 * 
 * PropertyDescriptor类表示JavaBean类通过存储器导出一个属性。主要方法： 1.
 * getReadMethod()，获得用于读取属性值的方法 2. getWriteMethod()，获得用于写入属性值的方法
 * 
 * 注：避免使用拼装方法名，反射获取Method对象。如tId,方法名格式与其他不统一，拼装方法名会错误。
 * 
 * @author XU.WJ 2017年9月7日
 */
public class ReflectDemo {

	public static void main(String[] args) throws Exception {

		Person p = new Person();
		p.setId("0");
		p.settId("0");
		
		test(p, "id");
		test(p, "tId");
	}

	public static void test(Person p, String propertyName) throws Exception {
		// 获取getter方法，反射获取id值
		PropertyDescriptor prop = new PropertyDescriptor(propertyName, Person.class);
		Object str = prop.getReadMethod().invoke(p);

		// 获取setter方法，反射赋值
		prop.getWriteMethod().invoke(p, "1");

		System.out.println("获取" + propertyName + "值:" + str);
		System.out.println("赋值" + propertyName + ":" + prop.getReadMethod().invoke(p));
	}

}
