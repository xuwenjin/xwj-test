package other;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试Map的put与PutIfAbsent方法
 */
public class TestPutIfAbsent {

	/**
	 * put与putIfAbsent区别:
	 * put在放入数据时，如果放入数据的key已经存在与Map中，最后放入的数据会覆盖之前存在的数据，
	 * 而putIfAbsent在放入数据时，如果存在重复的key，那么putIfAbsent不会放入值。
	 * 
	 * putIfAbsent
	 * 如果传入key对应的value已经存在，就返回存在的value，不进行替换。如果不存在，就添加key和value，返回null
	 * 
	 */
	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("1", "1");
		
		System.out.println(map.put("2", "2")); //null
		System.out.println(map.put("1", "3")); //1
		System.out.println(map.get("1")); //3

		
		System.out.println(map.putIfAbsent("4", "4")); //null
		System.out.println(map.putIfAbsent("1", "2")); //3
		System.out.println(map.get("1")); //3
	}

}
