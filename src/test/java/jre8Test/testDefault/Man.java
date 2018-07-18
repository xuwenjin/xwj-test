package jre8Test.testDefault;

/**
 * 测试java8中，接口里加入default修饰符
 * 
 * @author xuwenjin
 */
public class Man implements People {

	@Override
	public double calclate(int a) {
		return 0;
	}

	public static void main(String[] args) {
		// 实现了People接口的子类只需要实现一个calculate方法，默认方法sqrt将在子类上可以直接使用。
		People p = new Man();
		p.calclate(1);
		p.sqrt(2);
	}

}
