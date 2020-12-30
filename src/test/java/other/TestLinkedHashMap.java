package other;

import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

/**
 * 测试LinkedHashMap
 * 
 * @author xuwenjin 2020年12月30日
 */
public class TestLinkedHashMap {

	/**
	 * LinkedHashMap基本使用
	 */
	@Test
	public void test1() {
		// 默认accessOrder=false，表示不是访问顺序而是插入顺序存储的
		// 如果是插入顺序，则最后插入的放到最后面
		Map<String, String> map = new LinkedHashMap<>();
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		System.out.println(map);
		System.out.println(map.get("key2"));
		System.out.println("---------------------");

		// 替换key对应的value
		System.out.println(map.replace("key3", "333"));
		System.out.println(map.get("key3"));
		System.out.println("---------------------");

		// 替换key对应的value
		System.out.println(map.put("key4", "444"));
		System.out.println(map.get("key4"));
		System.out.println("---------------------");

		// 替换key对应的value(如果key不存在则赋值，否则不赋值)
		System.out.println(map.putIfAbsent("key2", "222"));
		System.out.println(map);
		System.out.println("---------------------");

		// 判断value是否存在
		System.out.println(map.containsValue("111"));
		System.out.println(map.containsValue("value1"));
	}

	/**
	 * LinkedHashMap-按访问顺序排序
	 */
	@Test
	public void test2() {
		// accessOrder=true，表示按访问顺序排序
		// 最近访问的，会放到最后面
		Map<String, String> map = new LinkedHashMap<>(16, 0.75f, true);
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");
		System.out.println(map);
		System.out.println(map.get("key2"));
		System.out.println(map);
		System.out.println(map.get("key1"));
		System.out.println(map);
		System.out.println(map.get("key4"));
		System.out.println(map);
	}

	/**
	 * LinkedHashMap-自定义删除策略
	 */
	@SuppressWarnings("serial")
	@Test
	public void test3() {
		Map<String, String> map = new LinkedHashMap<String, String>(16, 0.75f, true) {
			/**
			 * 当返回true时，删除最旧的元素
			 */
			@Override
			protected boolean removeEldestEntry(Map.Entry<String, String> eldest) {
				// 自定义删除策略(超过3个元素，就删除最旧的元素，删除的结果是eldest)
				boolean remove = size() > 3;
				if (remove) {
					System.out.println("被删除的元素：" + eldest);
				}
				return remove;
			}
		};
		map.put("key1", "value1");
		map.put("key2", "value2");
		map.put("key3", "value3");
		map.put("key4", "value4");

		// 上面的4个元素，key1是最旧的，所以在保存key4时，会自动删除key1

		System.out.println(map);
		System.out.println(map.get("key2"));
		System.out.println(map);
		System.out.println(map.get("key1"));
		System.out.println(map);
		System.out.println(map.get("key4"));
		System.out.println(map);
	}

}
