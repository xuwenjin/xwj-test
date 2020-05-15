package jvm;

/**
 * 指令重排序
 * 
 * 编译器和处理器可能会对操作做重排序。编译器和处理器在重排序时，会遵守数据依赖性，编译器和处理器不会改变存在数据依赖关系的两个操作的执行顺序
 * 
 * as-if-serial语义的意思指：不管怎么重排序（编译器和处理器为了提高并行度），（单线程）程序的执行结果不能被改变。编译器，runtime和处理器都必须遵守as-if-serial语义
 */
public class CommandReOrder {

	private static int a = 0, b = 0;
	private static int x = 0, y = 0;

	public static void main(String[] args) throws InterruptedException {
		int i = 0;
		for (;;) {
			i++;
			x = 0;
			y = 0;
			a = 0;
			b = 0;

			Thread t1 = new Thread(() -> {
				// 由于线程t1先启动，下面这句话让它等一等线程t2
				shortWait(10000);
				a = 1;
				x = b;
			});

			Thread t2 = new Thread(() -> {
				b = 1;
				y = a;
			});

			t1.start();
			t2.start();
			t1.join();
			t2.join();

			String result = "第" + i + "次 (" + x + "," + y + "）";
			if (x == 0 && y == 0) {
				// 如果打印出x=0，y=0，表明有指令重排序
				System.out.println(result);
				break;
			} else {
				// System.out.println(result);
			}
		}
	}

	public static void shortWait(long interval) {
		long start = System.nanoTime();
		long end;
		do {
			end = System.nanoTime();
		} while (start + interval >= end);
	}

}
