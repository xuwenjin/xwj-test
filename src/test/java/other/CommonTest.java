package other;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.junit.Test;

import entity.XwjUser;

public class CommonTest {

	/**
	 * 删除元素后继续循环会报错误信息ConcurrentModificationException，因为元素在使用的时候发生了并发的修改，导致异常抛出
	 */
	@Test
	public void test1() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		for (Integer i : list) {
			if (i == 1) {
				list.remove(i);
			}
		}
		list.forEach(d -> System.out.print(d));
	}

	/**
	 * 使用ListIterator迭代器，在遍历时，删除元素、修改元素、增加元素
	 */
	@Test
	public void test2() {
		List<Integer> list = new ArrayList<>();
		list.add(1);
		list.add(2);
		list.add(3);
		ListIterator<Integer> it = list.listIterator();
		while (it.hasNext()) {
			Integer i = it.next();
			if (i == 1) {
				// 删除
				it.remove();
			}
			if (i == 2) {
				i = i + 10;
				// 修改
				it.set(i);
			}
			if (i == 3) {
				// 新增
				it.add(4);
			}
		}
		list.forEach(d -> System.out.println(d));
	}

	@Test
	public void test3() {
		List<XwjUser> list = new ArrayList<>();
		list.add(new XwjUser(1));
		list.add(new XwjUser(2));
		list.add(new XwjUser(3));

		XwjUser user1 = list.get(0);

		list.clear();

		System.out.println(list.size());
		System.out.println(user1.getId());
	}

	/**
	 * Java 基本类型的包装类的大部分都实现了常量池技术，即 Byte,Short,Integer,Long,Character,Boolean；
	 * 这 5 种包装类默认创建了数值[-128，127] 的相应类型的缓存数据，但是超出此范围仍然会去创建新的对象。
	 */
	@Test
	public void testBasicType() {
		Short s1 = 33;
		Short s2 = 33;
		System.out.println(s1 == s2);// 输出 true

		Integer i1 = 33;
		Integer i2 = 33;
		System.out.println(i1 == i2);// 输出 true

		// 超出范围，创建新对象
		Integer i11 = 333;
		Integer i22 = 333;
		System.out.println(i11 == i22);// 输出 false

		Double i3 = 1.2;
		Double i4 = 1.2;
		System.out.println(i3 == i4);// 输出 false
	}

}
