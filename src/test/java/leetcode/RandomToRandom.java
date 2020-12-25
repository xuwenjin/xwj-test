package leetcode;

/**
 * 给你一个随机函数f，等概率返回1~5中的一个数字，这是你唯一可以使用的随机机制。
 * 如果实现等概率返回1~7中的一个数字
 * 
 * 扩展：
 * 如果给定函数可以等概率返回7~13，需要等概率返回26~40，解法如下：
 * 1、7~13，一共7个数字，找到中间数10。调用随机函数，如果小于10则返回0，大于10则返回1，等于10则再次调用   -->实现等概率返回0和1
 * 2、如果想等概率返回26~40，则需要等概率返回0~14(40-26)，然后再加上26就可以了
 * 3、如果想等概率返回0~14，则需要至少4个二进制位，参考下面的b方法就行了
 * 
 * @author xuwenjin 2020年12月25日
 */
public class RandomToRandom {

	/**
	 * 等概率返回1~5。此函数只能用，不能改
	 */
	public static int f() {
		return (int) (Math.random() * 5) + 1;
	}

	/**
	 * 等概率返回0和1
	 */
	public static int a() {
		int ans = 0;
		// do-while 无条件的执行一次循环体，再来判断条件表达式的值。如果表达式为true，继续循环
		do {
			ans = f();
		} while (ans == 3); // 等于3，则继续调用f()函数

		// 小于3返回0，大于3返回1
		return ans < 3 ? 0 : 1;
	}

	/**
	 * 等概率返回0和6
	 */
	public static int b() {
		int ans = 0;
		do {
			// 调用三次a()函数，等概率返回二进制数000-110
			ans = (a() << 2) + (a() << 1) + a();
		} while (ans == 7); // 等于7，则继续调用b()函数
		return ans;
	}

	/**
	 * 等概率返回1和7
	 */
	public static int c() {
		return b() + 1;
	}

	public static void main(String[] args) {
		int[] countArr = new int[8];
		for (int i = 0; i < 1000000; i++) {
			int ran1_7 = c();
			countArr[ran1_7]++;
		}
		for (int i = 0; i < countArr.length; i++) {
			System.out.println(countArr[i]);
		}
	}

}
