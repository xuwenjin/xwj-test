package collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jre8Test.Dish;

/**
 * 测试HashMap的工作原理
 * 
 * @author XU.WJ 2018年3月5日
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
		testMap(menu);
	}

	public static void testMap(List<Dish> menu) {
		Map<Integer, String> dishMap = new HashMap<>(10, 0.6F);
		for (int i = 0; i < 100; i++) {
			dishMap.put(i, i + "");
			Integer hashCode = new Integer(i);
			System.out.println("i: " + hashCode);
		}
		dishMap.get(1);
	}

}
