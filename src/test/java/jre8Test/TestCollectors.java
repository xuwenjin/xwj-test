package jre8Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

/**
 * 收集器Collectors使用
 * 
 * @author xwj
 *
 */
public class TestCollectors {

	private static List<Dish> dishList = new ArrayList<Dish>();

	static {
		Dish dish1 = new Dish("001", "张三");
		dishList.add(dish1);
		Dish dish2 = new Dish("001", "李四");
		dishList.add(dish2);
		Dish dish3 = new Dish("002", "王五");
		dishList.add(dish3);
	}

	/**
	 * 集合去重(将集合根据每个元素的id进行去重)
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testDistinctListById() {
		dishList.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));
		// id:001, name:张三
		// id:001, name:李四
		// id:002, name:王五

		System.out.println("---------根据id去重后：------------");
		List<Dish> newDishList = dishList.stream().collect(Collectors.collectingAndThen(
				Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Dish::getId))), ArrayList::new));
		newDishList.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));
		// id:001, name:张三
		// id:002, name:王五
	}

	/**
	 * 测试TreeSet默认比较器及传入比较器
	 */
	@Test
	public void testTreeSet() {
		// TreeSet不传比较器，则默认按照自然顺序排序
		TreeSet<Integer> treeSet = new TreeSet<>();
		treeSet.add(3);
		treeSet.add(1);
		treeSet.add(4);
		System.out.println(treeSet);
		// [1, 3, 4]

		// TreeSet中的元素，如果是实体，必须得传比较器(或者实体类需要实现Comparable中的compareTo方法)，不然就会报错
		// TreeSet是Set的子类，里面的元素有序且不能重复，可以去重
		TreeSet<Dish> treeSet2 = new TreeSet<>(Comparator.comparing(Dish::getId));
		treeSet2.addAll(dishList);
		treeSet2.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));
		// id:001, name:张三
		// id:002, name:王五

		List<Dish> newDishList = new ArrayList<>(treeSet2);
		newDishList.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));
		// id:001, name:张三
		// id:002, name:王五
	}

	/**
	 * 测试Collectors.toCollection方法：将结果收集到其它类型的集合中(这里是TreeSet)
	 */
	@Test
	public void testToCollection() {
		TreeSet<Integer> treeSet = Stream.of(3, 1, 4).collect(Collectors.toCollection(TreeSet::new));
		System.out.println(treeSet);
		// [1, 3, 4]

		TreeSet<Dish> treeSet2 = dishList.stream()
				.collect(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(Dish::getId))));
		treeSet2.forEach(d -> System.out.println("id:" + d.getId() + ", name:" + d.getName()));
		// id:001, name:张三
		// id:002, name:王五
	}

	/**
	 * 测试Collectors.collectingAndThen方法：将流中的数据通过Collector计算，计算的结果再通过Function处理一下
	 * (这里是将TreeSet转为ArrayList。即相当于将最终结果又经过了new ArrayList<>(treeSet))
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void testCollectingAndThen() {
		List<Integer> list = Stream.of(3, 1, 4)
				.collect(Collectors.collectingAndThen(Collectors.toCollection(TreeSet::new), ArrayList::new));
		System.out.println(list);
		// [1, 3, 4]
	}

}
