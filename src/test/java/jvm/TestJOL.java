package jvm;

import org.openjdk.jol.info.ClassLayout;

import entity.Son;

/**
 * jol：JVM中对象布局的工具
 * 
 * java对象的组成：
 * 
 * 1、对象头(markword)：在jvm虚拟机中每一个java对象都有一个对象头，对象头中包含标记字段以及对象指针，标记字段用来储存hash码、gc信息以及锁信息，而指针则指向改对象的类
 * 
 * 2、实例数据(instance data)：类中所有的实例字段数据
 * 
 * 3、内存填充部分：该部分作用是用来保证java对象在虚拟机中占内存大小为8bytes的倍数
 */
public class TestJOL {

	public static void main(String[] args) {

		Son o = new Son();

		// 打印o对象在jvm中信息
		System.out.println(ClassLayout.parseInstance(o).toPrintable());

		synchronized (o) {
			// 使用synchronized加锁，其实就是修改了markword
			System.out.println(ClassLayout.parseInstance(o).toPrintable());
		}

	}

}
