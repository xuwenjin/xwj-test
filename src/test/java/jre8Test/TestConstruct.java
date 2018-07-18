package jre8Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 构造器方法引用
 * 
 * @author XU.WJ 2017年7月15日
 */
public class TestConstruct {

	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			Apple apple = new Apple();
			apple.setWeight(i);
			if (i < 300) {
				apple.setColor("green");
			} else if (i >= 300 && i < 600) {
				apple.setColor("red");
			} else {
				apple.setColor("blue");
			}
		}

		Supplier<Apple> c1 = Apple::new; // 构造函数引用指向默认的Apple()构造函数
		Apple apple = c1.get(); // 调用Supplier的get方法将产生一个新的Apple

		Function<Integer, Apple> c2 = Apple::new; // 指向Apple(Integer weight)的构造函数引用
		Apple apple2 = c2.apply(110); // 调用该Function函数的apply方法，并给出要求的重量，将产生一个Apple

		List<String> list = Arrays.asList("a", "b");
		
		test();

	}

	private static void test() {
		List<Integer> weights = Arrays.asList(7, 3, 4, 10);
		List<Apple> apples = map(weights, Apple::new);
		apples.stream().map(Apple::getWeight).forEach(System.out::println);
	}

	//一个由Integer构成的List中的每个元素都通过我们前面定义的类似的map方法传递给了Apple的构造函数，得到了一个具有不同重量苹果的List：
	public static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
		List<Apple> result = new ArrayList<>();
		for (Integer e : list) {
			result.add(f.apply(e));
		}
		return result;
	}

}
