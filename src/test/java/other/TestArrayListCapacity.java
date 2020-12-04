package other;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * 测试ArrayList扩容
 */
public class TestArrayListCapacity {

	/**
	 * 测试扩容
	 */
	public static void main(String[] args) throws Exception {
		ArrayList<Integer> list = new ArrayList<>(6);

		System.out.println("空数组大小和容量分别是" + list.size() + "   " + getCapacity(list));

		list.add(1);
		print(1, list);

		list.add(1);
		print(2, list);

		for (int i = 0; i < 8; i++) {
			list.add(1);
		}
		print(10, list);

		list.add(1);
		print(11, list);

		/**
		 * 结论：
		 * 1、没有指定初始容量，且没有元素时，容量是0
		 * 2、没有指定初始容量，插入元素个数小于等于10个时，使用默认容量为10
		 * 3、没有指定初始容量，插入元素后大于10个时，它会先扩容到1.5倍大小，容量是10*1.5=15
		 * 
		 * 4、指定初始容量(比如6)，且没有元素时，容量是6
		 * 5、指定初始容量(比如6)，插入元素个数小于等于6个时，容量是6
		 * 6、指定初始容量(比如6)，插入元素个数大于6个，小于等于6*1.5=9时，容量是9
		 * 7、指定初始容量(比如6)，插入元素个数大于9个，小于等于9*1.5=13时，容量是13
		 */
	}

	public static void print(int index, ArrayList<Integer> list) {
		System.out.println("添加第【" + index + "】个元素后，" + "size:" + list.size() + ", 容量：" + getCapacity(list));
	}

	@SuppressWarnings("rawtypes")
	public static Integer getCapacity(ArrayList<Integer> list) {
		Integer length = null;
		Class c = list.getClass();
		Field f;
		try {
			f = c.getDeclaredField("elementData");
			f.setAccessible(true);
			Object[] o = (Object[]) f.get(list);
			length = o.length;
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return length;
	}

}
