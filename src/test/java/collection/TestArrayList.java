package collection;

import java.util.ArrayList;
import java.util.List;

import jre8Test.po.Dish;

/**
 * 测试ArrayList
 * 
 * @author XU.WJ 2018年3月5日
 */
public class TestArrayList {

	public static void main(String[] args) {
		// test1();
		// test2();
		test3();
	}

	/**
	 * 测试扩容
	 */
	public static void test1() {
		List<Dish> menu = new ArrayList<Dish>(12);
		for (int i = 0; i < 5; i++) {
			Dish dish = new Dish();
			dish.setCalories(i);
			menu.add(dish);
		}
		menu.remove(1);
	}

	/**
	 * 测试泛型擦除
	 * 
	 * Java的泛型基本上都是在编译器这个层次上实现的，在生成的字节码中是不包含泛型中的类型信息的，使用泛型的时候加上类型参数，在编译器编
	 * 译的时候会去掉，这个过程成为类型擦除。
	 */
	public static void test2() {
		ArrayList<String> list1 = new ArrayList<String>();
		list1.add("abc");

		ArrayList<Integer> list2 = new ArrayList<Integer>();
		list2.add(123);

		System.out.println(list1.getClass());
		System.out.println(list2.getClass());
		System.out.println(list1.getClass() == list2.getClass());
	}

	/**
	 * 测试泛型擦除
	 */
	public static void test3() {
		ArrayList<Integer> list = new ArrayList<Integer>();

		list.add(1); // 这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer

		try {
			list.getClass().getMethod("add", Object.class).invoke(list, "asd");
		} catch (Exception e) {
		}

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}

}
