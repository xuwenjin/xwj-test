package jvm;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 内存溢出(java.lang.OutOfMemoryError: Java heap space)
 * 
 * 指程序申请内存时，没有足够的内存供申请者使用，或者说，给了你一块存储int类型数据的存储空间，但是你却存储long类型的数据，那么结果就是内存不够用，此时就会报错OOM
 */
public class TestOutOfMemory {

	public static void main(String[] args) throws InterruptedException {
		List<Object> list = new LinkedList<>();
		int i = 0;
		while (true) {
			i++;
			if (i % 1000 == 0) {
				TimeUnit.MILLISECONDS.sleep(100);
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
