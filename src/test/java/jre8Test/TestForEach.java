package jre8Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 测试foreach循环
 * 
 * @author XU.WJ 2017年9月25日
 */
public class TestForEach {

	public static void main(String[] args) {
		List<Dish> menu = new ArrayList<Dish>();
		for (int i = 0; i < 100; i++) {
			Dish dish = new Dish();
			dish.setId(i + "");
			if (i % 3 == 0) {
				dish.setName("鸡肉");
			} else if (i % 3 == 1) {
				dish.setName("鸭肉");
			} else {
				dish.setName("鱼");
			}
			menu.add(dish);
		}
		System.out.println("----------------------------------------");
		testList(menu);
		System.out.println("----------------------------------------");
		testMap(menu);
	}

	public static void testList(List<Dish> menu) {
		long start = System.currentTimeMillis();
		for (Dish dish : menu) {
			dish.getName();
			dish.getCalories();
		}
		long end = System.currentTimeMillis();
		System.out.println("传统计算：" + (end - start));//19

		start = System.currentTimeMillis();
		menu.parallelStream().forEach(dish -> {
			dish.getName();
			dish.getCalories();
		});
		end = System.currentTimeMillis();
		System.out.println("java8计算：" + (end - start)); //71
	}

	public static void testMap(List<Dish> menu) {
		Map<String, Dish> map = menu.stream().collect(Collectors.toMap(Dish::getId, d -> d));

		long start = System.currentTimeMillis();
		for (String sKey : map.keySet()) {
//			map.get(sKey);
		}
		long end = System.currentTimeMillis();
		System.out.println("传统计算1：" + (end - start));  //51

		start = System.currentTimeMillis();
		for (Map.Entry<String, Dish> entry : map.entrySet()) {
//			entry.getKey();
//			entry.getValue();
		}
		end = System.currentTimeMillis();
		System.out.println("传统计算2：" + (end - start)); //26

		start = System.currentTimeMillis();
		map.forEach((key, value) -> {
			
		});
		end = System.currentTimeMillis();
		System.out.println("java8计算：" + (end - start)); //27
	}

}
