package calc;

import org.junit.Test;

/**
 * 计算类
 */
public class CommonCalc {

	/**
	 * 多目运算
	 */
	@Test
	public void test1() {
		int num = 2;
		// 大于，返回1
		// 等于，返回0
		// 小于，返回-1
		System.out.println(num >= 3 ? num == 3 ? 0 : 1 : -1);
	}

}
