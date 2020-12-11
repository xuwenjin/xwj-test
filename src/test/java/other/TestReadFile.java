package other;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Charsets;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import other.observer.MyObservable;
import other.observer.MyObserver;
import other.observer.Rxjava2Observer;
import other.po.Notifier;

/**
 * 测试读取文件
 * 
 * @author xuwenjin 2020年11月9日
 */
public class TestReadFile {

	/**文件路径*/
	private final String FILE_PATH = "D:/logs/input.txt";
	// private final String FILE_PATH = "D:/logs/catalina.log.2020-11-09";

	/**sleep时长(单位毫秒)*/
	private final long SLEEP_TIME = 1000;

	/**
	 * 使用Files.readAllLines，一次读取所有数据
	 */
	@Test
	public void test1() throws InterruptedException {
		long t1 = System.currentTimeMillis();
		Path path = Paths.get(FILE_PATH);
		List<String> lines = new ArrayList<>();
		try {
			// 读取文件，每一行为一个元素
			lines = Files.readAllLines(path, Charsets.UTF_8);
			for (int i = 0; i < lines.size(); i++) {
				TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
			}
		} catch (IOException e) {
			System.err.println("该路径下找不到该文件，请重试: " + e.getMessage());
		}
		System.out.println(lines.size()); // 12、328
		System.out.println("用时：" + (System.currentTimeMillis() - t1)); // 146、580
	}

	/**
	 * 使用Scanner，一行一行读取文件
	 */
	@Test
	public void test2() throws InterruptedException {
		long t1 = System.currentTimeMillis();
		FileInputStream inputStream = null;
		Scanner sc = null;

		List<String> lines = new ArrayList<>();
		try {
			inputStream = new FileInputStream(FILE_PATH);
			sc = new Scanner(inputStream, "UTF-8");
			
			// 一行一行读取文件
			while (sc.hasNextLine()) {
				lines.add(sc.nextLine());
				TimeUnit.MILLISECONDS.sleep(SLEEP_TIME);
			}
		} catch (IOException e) {
			System.err.println("该路径下找不到该文件，请重试: " + e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		System.out.println(lines.size()); // 12、328
		System.out.println("用时：" + (System.currentTimeMillis() - t1)); // 140、586
	}

	/**
	 * 使用Scanner + java自带观察者模式读取文件   ----  同步
	 */
	@Test
	public void test3() throws InterruptedException {
		long t1 = System.currentTimeMillis();
		FileInputStream inputStream = null;
		Scanner sc = null;
		List<String> lines = new ArrayList<>();
		try {
			inputStream = new FileInputStream(FILE_PATH);
			sc = new Scanner(inputStream, "UTF-8");
			
			// 创建被观察者、观察者
			MyObservable sm = new MyObservable();
			sm.addObserver(new MyObserver(SLEEP_TIME));
			
			// 一行一行读取文件
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println("发送消息--->" + line);
				lines.add(line);
				sm.setMessage(line);
			}
		} catch (IOException e) {
			System.err.println("该路径下找不到该文件，请重试: " + e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		System.out.println(lines.size()); // 12、328
		System.out.println("用时：" + (System.currentTimeMillis() - t1)); // 138、597
	}

	/**
	 * 使用Scanner + RxJava2观察者模式读取文件   ---- 异步
	 */
	@SuppressWarnings("resource")
	@Test
	public void test4() throws InterruptedException {
		// 计数器
		final CountDownLatch latch = new CountDownLatch(12);

		long t1 = System.currentTimeMillis();
		List<String> lines = new ArrayList<>();
		try {
			FileInputStream inputStream = new FileInputStream(FILE_PATH);
			Scanner sc = new Scanner(inputStream, "UTF-8");

			// create() 创建一个被观察者
			Observable.create((e) -> {
				// 一行一行读取文件
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					System.out.println("发送消息--->" + line);
					e.onNext(line);
					lines.add(line);

					// 计数器的值减一
					latch.countDown();
				}
			})
			.observeOn(Schedulers.io())// 为观察者创建一个新线程(从线程池中取，没有则创建，无上限)
			.subscribe(new Rxjava2Observer<Object>(SLEEP_TIME)); // 订阅
		} catch (Exception e) {
			System.err.println("该路径下找不到该文件，请重试: " + e.getMessage());
		}

		// 阻塞
		latch.await();

		System.out.println(lines.size()); // 12
		System.out.println("用时：" + (System.currentTimeMillis() - t1)); // 574

		// 防止主线程已经执行完，但是子线程还在执行
		for (;;)
			;
	}

	/**
	 * 使用Scanner + ArrayBlockingQueue读取文件   ---- 异步
	 */
	@Test
	public void test5() throws InterruptedException {
		long t1 = System.currentTimeMillis();
		FileInputStream inputStream = null;
		Scanner sc = null;

		List<String> lines = new ArrayList<>();
		try {
			inputStream = new FileInputStream(FILE_PATH);
			sc = new Scanner(inputStream, "UTF-8");

			// 创建线程并启动
			final Notifier notifier = new Notifier();
			notifier.start();

			// 一行一行读取文件
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				System.out.println("发送消息--->" + line);
				lines.add(line);
				notifier.addTask(line);
			}
		} catch (IOException e) {
			System.err.println("该路径下找不到该文件，请重试: " + e.getMessage());
		} finally {
			if (sc != null) {
				sc.close();
			}
		}
		System.out.println(lines.size()); // 12
		System.out.println("用时：" + (System.currentTimeMillis() - t1));
	}

	/**
	 * 结论：当文件内容越多或者读取内容后的处理逻辑时间越长，异步(test4、test5)的效果越好
	 */

}
