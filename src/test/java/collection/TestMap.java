package collection;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试HashMap的工作原理
 * 
 * @author XU.WJ 2018年3月5日
 */
public class TestMap {

	/**
	 * threshold 阈值。当table=={}时，该值为初始容量（初始容量默认为16）；当table被填充了，也就是为table分配内存空间后，threshold一般为
	 * capacity*loadFactory。当超过阈值时，table容量会自动扩大两倍
	 * 
	 * initialCapacity 初始化容量(会向上取最接近2的幂次，如10，则初始容量会变成16)
	 * 
	 * loadFactor 负载因子，代表了table的填充度有多少
	 * 
	 */
	public static void main(String[] args) {
		Map<Integer, String> dishMap = new HashMap<>(10, 0.6F);
		for (int i = 0; i < 100; i++) {
			dishMap.put(i, i + "");
			Integer hashCode = new Integer(i);
			System.out.println("i: " + hashCode);
		}
		dishMap.get(1);
	}

}
