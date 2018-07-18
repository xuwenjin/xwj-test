package testJson;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.serializer.SerializerFeature;

import entity.XwjUser;

/**
 * 测试fastjson序列化、反序列化
 * 
 * @author xuwenjin
 */
public class TestFastJson {

	/**
	 * 默认将字段首字母变为小写
	 * 
	 * 转为对象时，一定要有无参构造器
	 */
	@Test
	public void testObject() {
		XwjUser user = new XwjUser(1, "Hello World", new Date());
		user.setNodeName("node");

		// 第二个参数为是否格式化json
		String jsonStr = JSON.toJSONString(user, true);
		System.out.println("对象转为字符串：" + jsonStr);

		XwjUser userDes = JSON.parseObject(jsonStr, XwjUser.class);
		System.out.println("字符串转为对象：" + userDes);
	}

	/**
	 * 测试map
	 */
	@Test
	public void testMap() {
		Map<String, Object> testMap = new HashMap<>();
		testMap.put("name", "merry");
		testMap.put("age", 30);
		testMap.put("date", new Date());
		testMap.put("isDel", true);
		testMap.put("user", new XwjUser(1, "Hello World", new Date()));

		String jsonStr = JSON.toJSONString(testMap);
		System.out.println("Map转为字符串：" + jsonStr);

		Map<String, Object> mapDes = JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {
		});
		System.out.println("字符串转map：" + mapDes);
	}

	@SuppressWarnings("rawtypes")
	@Test
	public void testMapList() {
		List<Map<String, Object>> mapList = new ArrayList<>();

		Map<String, Object> map1 = new HashMap<>();
		map1.put("name", "merry");
		map1.put("age", 30);
		map1.put("date", new Date());
		map1.put("isDel", true);

		Map<String, Object> map2 = new HashMap<>();
		map2.put("name", "jim");
		map2.put("age", 28);
		map2.put("isDel", false);

		mapList.add(map1);
		mapList.add(map2);

		String jsonStr = JSON.toJSONString(mapList);
		System.out.println("list转为字符串：" + jsonStr);

		// 传入泛型类型
		List<Map> listDes = JSON.parseArray(jsonStr, Map.class);
		System.out.println("字符串转为list：" + listDes);
	}

	/**
	 * 测试日期格式
	 */
	@Test
	public void testDateFormat() {
		Date now = new Date();
		String dateStr = JSON.toJSONString(now);
		System.out.println("默认日期：" + dateStr);

		// 按照指定格式格式化日期，格式为yyyy-MM-dd HH:mm:ss
		String dateStr2 = JSON.toJSONString(new Date(), SerializerFeature.WriteDateUseDateFormat);
		System.out.println("格式化日期：" + dateStr2);

		String dateStr3 = JSON.toJSONStringWithDateFormat(now, "yyyy-MM-dd HH:mm");
		System.out.println("格式化日期：" + dateStr3);
	}

	/**
	 * 测试单引号
	 */
	@Test
	public void testSingleQuotes() {
		List<Object> list = new ArrayList<>();
		list.add("home");
		list.add("hello");
		list.add(true);
		list.add(45.78);
		String listJson = JSON.toJSONString(list, SerializerFeature.UseSingleQuotes);
		System.out.println("转为单引号字符串：" + listJson);
	}

	/**
	 * 测试输出值为null情况
	 */
	@Test
	public void testNull() {
		Map<String, Object> testMap = new HashMap<>();
		testMap.put("name", "merry");
		testMap.put("age", 30);
		testMap.put("date", null);

		// 缺省情况下，FastJSON不输入为值Null的字段
		String jsonStr = JSON.toJSONString(testMap);
		System.out.println("转为字符串：" + jsonStr);

		String jsonStr2 = JSON.toJSONString(testMap, SerializerFeature.WriteMapNullValue);
		System.out.println("转为字符串：" + jsonStr2);
	}

	/**
	 * 将Map转成JSONObject，然后添加元素，输出
	 */
	@Test
	public void testJsonObject() {
		Map<String, Object> testMap = new HashMap<>();
		testMap.put("key1", "value1");
		testMap.put("key2", "value2");
		
		JSONObject jsonObj = new JSONObject(testMap);
		jsonObj.put("key3", "value3");
		System.out.println(jsonObj);
		System.out.println(jsonObj.get("key2"));
	}
	
	/**
	 * 将List对象转成JSONArray，然后输出
	 */
	@Test
	public void testJsonArray() {
		List<Object> list = new ArrayList<>();
		list.add("home");
		list.add(60);
		list.add(true);
		list.add(new XwjUser(1, "Hello World", new Date()));
		
		JSONArray jsonArr = JSONArray.parseArray(JSON.toJSONString(list));
		System.out.println(jsonArr);
	}

}
