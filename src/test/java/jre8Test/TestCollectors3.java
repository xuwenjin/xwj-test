package jre8Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jre8Test.po.Dish;

public class TestCollectors3 {

	public static void main(String[] args) {
		// List<Dish> menu = InitData.dishList(10);
		// testJoining(menu);
		// testGroupBy(menu);
		// testMap(menu);
		testFlatMap();
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

		System.out.println("--------------------------------");

		List<String> list = menu.stream().map(d -> d.getName() + "-->" + d.getCalories()).collect(Collectors.toList());
		list.forEach(System.out::println);
	}

	/**
	 * 测试flatMap()方法
	 * 
	 * 使用flatMap方法的效果是，各个数组并不是分别映射一个流，而是映射成流的内容。所有使用flatMap(Array::stream)时生成的单个流被合并起来，即扁平化为一个流
	 * 
	 * 重点：将多个流合并成一个流
	 */
	public static void testFlatMap() {
		// 案例：对给定单词列表 ["Hello","World"], 返回列表["H","e","l","o","W","r","d"]
		String[] wordArr = new String[] { "Hello", "World" };

		System.out.println("---------------打印原始值-----------------");
		Arrays.stream(wordArr).forEach(System.out::println);

		System.out.println("---------------使用map-----------------");
		// 返回了一个字符串数组集合 -- 错误
		List<String[]> strArrList = Arrays.stream(wordArr).map(word -> word.split("")).distinct()
				.collect(Collectors.toList());
		strArrList.forEach(System.out::println);

		System.out.println("---------------使用flatMap(Arrays::stream)-----------------");

		List<String> strList = Arrays.stream(wordArr).map(word -> word.split("")).flatMap(Arrays::stream).distinct()
				.collect(Collectors.toList());
		strList.forEach(System.out::println);

		System.out.println("---------------使用flatMap(Stream::of)-----------------");

		List<String> strList2 = Stream.of(wordArr).map(word -> word.split("")).flatMap(Stream::of).distinct()
				.collect(Collectors.toList());
		strList2.forEach(System.out::println);

		System.out.println("---------------使用flatMap(升级版)-----------------");

		List<String> strList3 = Stream.of(wordArr).flatMap(word -> Stream.of(word.split(""))).distinct()
				.collect(Collectors.toList());
		strList3.forEach(System.out::println);
	}

}
