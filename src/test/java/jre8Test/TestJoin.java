package jre8Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestJoin {
	
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
		testJoining(menu);
	}
	
	public static void testJoining(List<Dish> menu) { // 连接字符串

		String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
		System.out.println(shortMenu);
		
		String shortMenu2 = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
		System.out.println(shortMenu2);
		
		String shortMenu3 = menu.stream().map(Dish::getName).collect(Collectors.joining(",","[", "]"));
		System.out.println(shortMenu3);
	}

}
