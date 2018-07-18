package jre8Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 测试map()方法
 * 
 * @author XU.WJ 2017年7月8日
 */
public class TestMap {

	public static void main(String[] args) {
		List<Dish> menu = new ArrayList<Dish>();
		for (int i = 0; i < 10; i++) {
			Dish dish = new Dish();
			dish.setCalories(i);
			if (i % 3 == 0) {
				dish.setName("鸡肉");
			} else if (i % 3 == 1) {
				dish.setName("鸭肉");
			} else {
				dish.setName("鱼");
			}
			menu.add(dish);
		}
		menu.stream().forEach(d -> System.out.println(d.getName() + ":" +d.getCalories()));
		System.out.println("----------------------------------------");
		testMap(menu);
	}

	public static void testMap(List<Dish> menu) { // 去重
		String[] nameArr = menu.stream().map(Dish::getName).distinct().toArray(String[]::new);
		Arrays.asList(nameArr).stream().forEach(System.out::println);
	}
	
}
