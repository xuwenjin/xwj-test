package jre8Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 比较效率
 * 
 * @author XU.WJ 2017年7月6日
 */
public class Test2 {

	public static void main(String[] args) {
		List<Dish> menu = new ArrayList<Dish>();
		for (int i = 0; i < 10; i++) {
			Dish Dish = new Dish();
			Dish.setCalories(i);
			if (i < 3000) {
				Dish.setName("鸡肉");
			} else if (i >= 3000 && i < 6000) {
				Dish.setName("鸭肉");
			} else {
				Dish.setName("鱼");
			}
		}
		testBefore(menu);
		testNow(menu);
	}

	public static void testBefore(List<Dish> menu) {
		long start = System.currentTimeMillis();
		List<Dish> lowCaloricDishes = new ArrayList<>();
		for (Dish d : menu) {
			if (d.getCalories() < 400) {
				lowCaloricDishes.add(d);
			}
		}
//		
		List<String> lowCaloricDishesName = new ArrayList<>();
		for (Dish d : lowCaloricDishes) {
			lowCaloricDishesName.add(d.getName());
		}
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public static void testNow(List<Dish> menu) {
		long start = System.currentTimeMillis();
		List<String> lowCaloricDishesName = menu.stream().filter(d -> {
			System.out.println(d.getName());
			return d.getCalories() < 400;
		}).map(d -> {
			System.out.println(d.getName());
			return d.getName();
		})
				.collect(Collectors.toList());
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}
	
}
