package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import other.po.Notifier;

/**
 * 使用ArrayBlockingQueue实现异步的效果(参考了Nacos往注册表写数据时的一段逻辑)
 * 
 * @author xuwenjin 2019年07月15日
 */
public class UseArrayBlockingQueue {

	/**
	 * 控制台输入信息，然后移除打印结果
	 */
	public static void main(String[] args) throws IOException {
		final Notifier notifier = new Notifier();
		notifier.start();

		// 控制台输入内容
		for (;;) {
			String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
			notifier.addTask(str);
		}
	}

}