package jre8Test;

import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

import jre8Test.po.Dish;

/**
 * 测试收集器
 * 
 * @author XU.WJ 2017年7月21日
 */
public class TestCollectors2 {

	public static void main(String[] args) {
		List<Dish> menu = InitData.dishList(10);
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

		// 这个收集器会把所有这些信息收集到一个叫作IntSummaryStatistics的类里，它提供了方便的取值（getter）方法来访问结果
		IntSummaryStatistics menuStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
		System.out.println(menuStatistics.getMax());
	}

}
