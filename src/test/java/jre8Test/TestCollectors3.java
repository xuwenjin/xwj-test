package jre8Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jre8Test.po.Dish;

public class TestCollectors3 {

	public static void main(String[] args) {
		List<Dish> menu = InitData.dishList(10);
		// testJoining(menu);
		// testGroupBy(menu);
		testMap(menu);
	}

	/**
	 * 测试joining方法
	 */
	public static void testJoining(List<Dish> menu) { // 连接字符串
		String shortMenu = menu.stream().map(Dish::getName).collect(Collectors.joining());
		System.out.println(shortMenu);

		String shortMenu2 = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
		System.out.println(shortMenu2);

		String shortMenu3 = menu.stream().map(Dish::getName).collect(Collectors.joining(",", "[", "]"));
		System.out.println(shortMenu3);
	}

	/**
	 * 测试groupingBy方法
	 */
	public static void testGroupBy(List<Dish> menu) {
		Map<String, List<Dish>> groupMap = menu.stream().collect(Collectors.groupingBy(Dish::getName));
		System.out.println(groupMap);
	}

	/**
	 * 测试map()方法
	 */
	public static void testMap(List<Dish> menu) {
		String[] nameArr = menu.stream().map(d -> d.getName() + "-->" + d.getCalories()).toArray(String[]::new);
		Arrays.asList(nameArr).stream().forEach(System.out::println);
	}

}
