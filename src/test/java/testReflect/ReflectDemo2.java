package testReflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;

import lombok.SneakyThrows;

public class ReflectDemo2 {

	public static void main(String[] args) {
		Foo foo = new Foo();
		foo.setId("1");
		foo.setName("xuwenjin");
		foo.setAge(10);
		foo.setPhone("18207136674");
		foo.setSalary(BigDecimal.valueOf(12.4));

		long t1 = System.currentTimeMillis();
		String result = reflectBean(foo);
		long t2 = System.currentTimeMillis();
		System.out.println("结果:" + result + ", 用时：" + (t2 - t1));
	}

	/**
	 * 将Foo实体中的所有属性拼为一个字符串
	 */
	@SneakyThrows
	private static String reflectBean(Foo foo) {
		if (foo == null) {
			return null;
		}
		StringBuilder builder = new StringBuilder();
		// 拿到该类
		Class<?> clz = foo.getClass();
		// 获取实体类的所有属性，返回Field数组
		Field[] fields = clz.getDeclaredFields();
		for (Field field : fields) {
			// 获取属性的名字
			String name = field.getName();
			// 将属性的首字母大写
			name = upperCaseFirst(name);
			Method m = (Method) clz.getMethod("get" + name);
			Object val = m.invoke(foo);// 调用getter方法获取属性值
			if (val != null) {
				builder.append(val);
			}
		}
		return builder.toString();
	}

	/**
	 * 将字符串首字母变成大写
	 */
	private static String upperCaseFirst(String str) {
		char[] cs = str.toCharArray();
		// 进行字母的ascii编码前移，效率要高于截取字符串进行转换的操作
		// 小写比大写高32位
		cs[0] -= 32;
		return String.valueOf(cs);
	}

}
