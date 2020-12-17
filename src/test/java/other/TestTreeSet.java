package other;

import java.util.TreeSet;

/**
 * 测试TreeSet
 * 
 * @author xuwenjin 2020年12月16日
 */
public class TestTreeSet {

	/**
	 * TreeSet，内部是一个TreeMap。add时，只插入key。特点：
	 * 
	 * 1、不能有重复的元素； 
	 * 2、具有排序功能； 
	 * 3、TreeSet中的元素必须实现Comparable接口并重写compareTo()方法，TreeSet判断元素是否重复 、以及确定元素的顺序 靠的都是这个方法； 
	 *    对于java类库中定义的类，TreeSet可以直接对其进行存储，如String，Integer等,因为这些类已经实现了Comparable接口); 
	 *    对于自定义类，如果不做适当的处理，TreeSet中只能存储一个该类型的对象实例，否则无法判断是否重复。 
	 * 4、依赖TreeMap。 
	 * 5、相对HashSet，TreeSet的优势是有序，劣势是相对读取慢
	 */
	public static void main(String[] args) {
		TreeSet<Integer> treeSet = new TreeSet<>();
		treeSet.add(3);
		treeSet.add(2);
		treeSet.add(7);
		treeSet.add(6);
		treeSet.add(3);
		treeSet.add(1);
		treeSet.add(9);
		treeSet.add(8);
		System.out.println(treeSet);
		System.out.println(treeSet.first()); // 取最小的元素
		System.out.println(treeSet.last()); // 取最大的元素
		System.out.println(treeSet.pollFirst()); // 弹出最小的元素(会影响treeSet)
		System.out.println(treeSet);
		System.out.println(treeSet.pollLast()); // 弹出最大的元素(会影响treeSet)
		System.out.println(treeSet);

		System.out.println(treeSet.tailSet(3)); // 查询大于指定数字的元素集合
		System.out.println(treeSet.headSet(3)); // 查询小于指定数字的元素集合
		System.out.println(treeSet.subSet(3, 6));// 查询指定数字区间的元素集合

	}

}
