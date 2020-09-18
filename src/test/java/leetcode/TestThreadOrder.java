package leetcode;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class TestThreadOrder {

	static AtomicInteger at = new AtomicInteger(1);

	public static void main(String[] args) {
		int loopCount = 5;

		new Thread(() -> {
			IntStream.range(0, loopCount).forEach(index -> {
				while (at.get() != 1) {
					// 自旋
				}
				printA();
				at.set(2);
			});
		}).start();

		new Thread(() -> {

			IntStream.range(0, loopCount).forEach(index -> {
				while (at.get() != 2) {
					// 自旋
				}
				printB();
				at.set(3);
			});

		}).start();

		new Thread(() -> {

			IntStream.range(0, loopCount).forEach(index -> {
				while (at.get() != 3) {
					// 自旋
				}
				printC();
				at.set(1);
				System.out.println("------------------");
			});

		}).start();
	}

	public static void printA() {
		IntStream.range(0, 2).forEach(index -> {
			System.out.println("A---->" + index);
		});
	}

	public static void printB() {
		IntStream.range(0, 3).forEach(index -> {
			System.out.println("B---->" + index);
		});
	}

	public static void printC() {
		IntStream.range(0, 4).forEach(index -> {
			System.out.println("C---->" + index);
		});
	}

}
