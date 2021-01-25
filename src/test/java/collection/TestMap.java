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
	 * jdk1.8以前，构造方法中创建了一个长度是16的Entry[] table数组用来存储键值对数据的
	 * jdb1.8以后，不是在构造方法中创建数组了，是在第一次调用put方法时创建的数组---Node[] table。其中Node是Entry的子类
	 * 如果节点长度即链表长度大于8并且数组长度大于64，则将链表转为红黑树
	 * 
	 * 
	 * capacity: table当前容量，为2的幂次
	 * 
	 * threshold: 阈值。当table=={}时，该值为初始容量（初始容量默认为16）；当table被填充了，也就是为table分配内存空间后，threshold一般为
	 * capacity*loadFactory。当超过阈值时，table容量会自动扩大两倍
	 * 
	 * initialCapacity: 初始化容量(会向上取最接近2的幂次，如10，则初始容量会变成16)
	 * 
	 * loadFactor: 负载因子，代表了table的填充度有多少
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
