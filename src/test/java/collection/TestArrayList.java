package collection;

import java.util.ArrayList;
import java.util.List;

import jre8Test.Dish;

/**
 * 测试ArrayList的工作原理
 * 
 * @author XU.WJ 2018年3月5日
 */
public class TestArrayList {

	public static void main(String[] args) {
		List<Dish> menu = new ArrayList<Dish>(12);
		for (int i = 0; i < 5; i++) {
			Dish dish = new Dish();
			dish.setCalories(i);
			menu.add(dish);
		}
		menu.remove(1);
	}

}
