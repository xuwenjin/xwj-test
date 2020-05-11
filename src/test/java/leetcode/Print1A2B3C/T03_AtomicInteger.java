package leetcode.Print1A2B3C;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 使用AtomicInteger
 */
public class T03_AtomicInteger {

	static AtomicInteger at = new AtomicInteger(1);

	public static void main(String[] args) {
		char[] aI = "123456".toCharArray();
		char[] aC = "ABCDEF".toCharArray();

		new Thread(() -> {

			for (char c : aI) {
				while (at.get() != 1) {
					// 自旋
				}
				System.out.println(c);
				at.set(2);
			}

		}).start();

		new Thread(() -> {

			for (char c : aC) {
				while (at.get() != 2) {
					// 自旋
				}
				System.out.println(c);
				at.set(1);
			}

		}).start();
	}

}
