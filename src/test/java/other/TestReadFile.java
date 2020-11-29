package other;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import com.google.common.base.Charsets;

import other.observer.MyObservable;
import other.observer.MyObserver;

/**
 * 测试读取文件
 */
public class TestReadFile {

	@Test
	public void test1() throws InterruptedException {
		long t1 = System.currentTimeMillis();
		Path path = Paths.get("C:/Users/xwj/Desktop/catalina.log.2020-11-13");
		List<String> lines = new ArrayList<>();
		try {
			// 读取文件，每一行为一个元素
			lines = Files.readAllLines(path, Charsets.UTF_8);
			for (int i = 0; i < lines.size(); i++) {
				// TimeUnit.MILLISECONDS.sleep(1);
			}
		} catch (IOException e) {
			System.err.println("该路径下找不到该文件，请重试: " + e.getMessage());
			// 退出程序
			System.exit(1);
		}
		System.out.println(lines.size()); // 328
		System.out.println("用时：" + (System.currentTimeMillis() - t1)); // 580
	}

	@Test
	public void test2() throws InterruptedException {
		long t1 = System.currentTimeMillis();
		FileInputStream inputStream = null;
		Scanner sc = null;

		List<String> lines = new ArrayList<>();
		try {
			inputStream = new FileInputStream("C:/Users/xwj/Desktop/catalina.log.2020-11-13");
			sc = new Scanner(inputStream, "UTF-8");
			while (sc.hasNextLine()) {
				lines.add(sc.nextLine());
				TimeUnit.MILLISECONDS.sleep(1);
			}
		} catch (IOException e) {
			System.err.println("该路径下找不到该文件，请重试: " + e.getMessage());
			// 退出程序
			System.exit(1);
		}
		System.out.println(lines.size()); // 328
		System.out.println("用时：" + (System.currentTimeMillis() - t1)); // 586
	}

	@Test
	public void test3() throws InterruptedException {
		long t1 = System.currentTimeMillis();
		FileInputStream inputStream = null;
		Scanner sc = null;

		List<String> lines = new ArrayList<>();
		try {
			inputStream = new FileInputStream("C:/Users/xwj/Desktop/catalina.log.2020-11-13");
			sc = new Scanner(inputStream, "UTF-8");
			MyObservable sm = new MyObservable();
			sm.addObserver(new MyObserver());
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				lines.add(line);
				sm.setMessage(line);
			}
		} catch (IOException e) {
			System.err.println("该路径下找不到该文件，请重试: " + e.getMessage());
			// 退出程序
			System.exit(1);
		}
		System.out.println(lines.size()); // 328
		System.out.println("用时：" + (System.currentTimeMillis() - t1)); // 597
	}

}
