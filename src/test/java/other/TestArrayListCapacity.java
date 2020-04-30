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
		ArrayList<Integer> list = new ArrayList<>();

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

		// 结论：1、ArrayList默认容量是10，即使里面为空，容量也是10
		// 结论：2、插入前扩容，比如要插第11个数据，那么它会先扩容到1.5倍大小，即15

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
