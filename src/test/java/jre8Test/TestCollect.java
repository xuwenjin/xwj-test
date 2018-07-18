package jre8Test;

import java.util.ArrayList;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 测试收集器
 * 
 * @author XU.WJ 2017年7月21日
 */
public class TestCollect {

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
		menu.stream().forEach(d -> System.out.println(d.getName() + ":" + d.getCalories()));
		System.out.println("----------------------------------------");
		testCollect(menu);
	}

	public static void testCollect(List<Dish> menu) {
		// 统计总数
		int total = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
		int total2 = menu.stream().mapToInt(Dish::getCalories).sum();
		System.out.println(total);
		System.out.println(total2);
		
		// 计算平均数
		double avg = menu.stream().collect(Collectors.averagingInt(Dish::getCalories)); // 求平均数
		System.out.println(avg);

		System.out.println("个数：" + menu.stream().count()); // 个数

		//这个收集器会把所有这些信息收集到一个叫作IntSummaryStatistics的类里，它提供了方便的取值（getter）方法来访问结果
		IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		System.out.println(menuStatistics.getMax());
	}

}
