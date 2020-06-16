package jvm;

import java.util.concurrent.TimeUnit;

/**
 * 用于Arthas测试jad/mc/redefine命令
 */
public class MathGame {

	public static void main(String[] args) throws InterruptedException {
		MathGame game = new MathGame();
		while (true) {
			game.run();
			TimeUnit.SECONDS.sleep(1);

			// 这个不生效，因为代码一直跑在 while里
			System.out.println("in loop");
		}
	}

	public void run() throws InterruptedException {
		// 这个生效，因为run()函数每次都可以完整结束
		System.out.println("call run()");
	}

}