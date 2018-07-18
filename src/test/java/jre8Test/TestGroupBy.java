package jre8Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 测试分组
 * 
 * @author XU.WJ 2017年7月8日
 */
public class TestGroupBy {
	
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
		testGroupBy(menu);
	}
	
	public static void testGroupBy(List<Dish> menu) { 
		Map<String, List<Dish>> groupMap = menu.stream().collect(Collectors.groupingBy(Dish::getName));
		System.out.println(groupMap);
	}

}
