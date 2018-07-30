package jre8Test;

import java.util.Optional;

/**
 * 测试jdk8中的Optional对象
 * 
 * @author xwj
 *
 */
public class TestOptional {

	public static void main(String[] args) {
		Optional<Integer> optional1 = Optional.of(1); // 参数不能是null
		Optional<Integer> optional2 = Optional.ofNullable(null); // 参数可以是null
		Optional<Integer> optional3 = Optional.ofNullable(2); // 参数可以是非null

		// isPresent: 判断值是否存在
		System.out.println(optional1.isPresent());
		System.out.println(optional2.isPresent());
		System.out.println(optional3.isPresent());

		// orElse: 如果optional对象保存的值不是null，则返回原来的值，否则返回value
		System.out.println(optional1.orElse(1000));
		System.out.println(optional2.orElse(100));
		System.out.println(optional3.orElse(10));

		// orElseThrow()：值不存在则抛出异常，存在则什么不做，有点类似Guava的Precoditions
		System.out.println(optional1.orElseThrow(() -> {
			throw new IllegalStateException();
		}));
		// System.out.println(optional2.orElseThrow(() -> {
		// throw new NullPointerException();
		// }));

		// filter(Predicate)：判断Optional对象中保存的值是否满足Predicate，并返回新的Optional。
		// 没有值，则返回Optional.empty
		System.out.println(optional1.filter(d -> d != 1));
		System.out.println(optional2.filter(d -> d != 1));

		// map(Function)：对Optional中保存的值进行函数运算，并返回新的Optional(可以是任何类型)
		System.out.println(optional1.map(d -> d + 1).get());
		System.out.println(optional2.map(d -> d + 1).isPresent());

	}

}
