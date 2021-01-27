package jre8Test;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.junit.Test;

import jre8Test.po.Dish;

/**
 * 测试foreach循环
 * 
 * 
 * 测试结论：
 * 
 * 1、当循环中，执行逻辑非常快(纯java计算)，使用传统方式比java8快很多
 * 
 * 2、当循环中，执行逻辑需要时间(比如发送请求)，使用java8方式快些
 * 
 * 3、当每次处理逻辑都是独立时，可以使用parallelStream做并行处理
 * 
 * @author XU.WJ 2017年9月25日
 */
public class TestForEach {

	private List<Dish> getList() {
		return InitData.dishList(100);
	}

	@Test
	public void testList() {
		List<Dish> menu = getList();
		long start = System.currentTimeMillis();
		for (Dish dish : menu) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("list遍历，传统计算：" + (end - start));//

		start = System.currentTimeMillis();
		// parallelStream表示并行处理
		// Stream是顺序执行
		menu.parallelStream().forEach(dish -> {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		end = System.currentTimeMillis();
		System.out.println("list遍历，java8计算：" + (end - start)); //
	}

	@Test
	public void testMap() {
		List<Dish> menu = getList();
		Map<String, Dish> map = menu.stream().collect(Collectors.toMap(Dish::getId, d -> d));

		long start = System.currentTimeMillis();
		for (String sKey : map.keySet()) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		long end = System.currentTimeMillis();
		System.out.println("map遍历，传统计算1：" + (end - start)); // 51

		start = System.currentTimeMillis();
		for (Map.Entry<String, Dish> entry : map.entrySet()) {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		end = System.currentTimeMillis();
		System.out.println("map遍历，传统计算2：" + (end - start)); // 26

		start = System.currentTimeMillis();
		map.forEach((key, value) -> {
			try {
				TimeUnit.MILLISECONDS.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		end = System.currentTimeMillis();
		System.out.println("map遍历，java8计算：" + (end - start)); // 27
	}

}
