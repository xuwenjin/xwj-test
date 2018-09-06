package other;

import entity.Parent;
import entity.Son;

/**
 * 测试isAssignableFrom方法
 */
public class TestIsAssignableFrom {

	/**
	 * a.isAssignableFrom(b)
	 * 
	 * 满足以下条件返回true： 
	 * 1、a和b是同一个类或者通过一个接口 
	 * 2、a是b的父类或者接口
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		Class parentClass = Parent.class;
		Class sonClass = Son.class;
		System.out.println(parentClass.isAssignableFrom(sonClass));
		System.out.println(parentClass.isAssignableFrom(parentClass));
		System.out.println(sonClass.isAssignableFrom(parentClass));
		System.out.println(sonClass.isAssignableFrom(sonClass));
	}

}
