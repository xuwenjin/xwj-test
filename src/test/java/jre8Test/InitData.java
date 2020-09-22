package jre8Test;

import java.util.ArrayList;
import java.util.List;

import jre8Test.po.Dish;

public class InitData {

	/**
	 * 初始化一个DishList
	 */
	public static List<Dish> dishList(int num) {
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
		// menu.stream().forEach(d -> System.out.println(d.getName() + ":" +
		// d.getCalories()));
		return menu;
	}

}
