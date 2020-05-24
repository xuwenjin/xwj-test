package jvm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 内存泄漏
 * 
 * 内存泄漏是指无用对象（不再使用的对象）持续占有内存或无用对象的内存得不到及时释放，从而造成内存空间的浪费称为内存泄漏。内存泄露有时不严重且不易察觉，这样开发者就不知道存在内存泄露，需要自主观察，比较严重的时候，没有内存可以分配，直接oom
 */
public class MemoryLeak {

	public static void main(String[] args) throws InterruptedException {
		List<Object> list = new LinkedList<>();
		int i = 0;
		while (true) {
			i++;
			if (i % 1000 == 0) {
				TimeUnit.MILLISECONDS.sleep(10);
//				MemoryInformation info = new MemoryInformation();
//				System.out.println("usededen：" + info.getUsedEdenSpace());
//				System.out.println("Survivor已使用内存：" + info.getUsedSurvivorSpace());
//				System.out.println("老年代(OldGen)已使用内存：" + info.getUsedOldGen());
//				System.out.println("heap使用内存：" + info.getUsedMemory());
				System.out.println("i:" + i);
			}
			list.add(new Object());
		}
	}

}
