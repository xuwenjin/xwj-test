package jvm;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * 打印jvm信息
 * 
 * 可以参考：https://www.jianshu.com/p/5d854245051d
 */
public class PrintJvm {

	/**
	 * ManagementFactory是一个为我们提供各种获取JVM信息的工厂类，使用ManagementFactory可以获取大量的运行时JVM信息，
	 * 比如JVM堆的使用情况，以及GC情况，线程信息等，通过这些数据项我们可以了解正在运行的JVM的情况，以便我们可以做出相应的调整。
	 */

	@Test
	public void testMemoryMXBean() {
		MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();

		System.out.println("堆内存信息: " + memoryBean.getHeapMemoryUsage());
		System.out.println("方法区内存信息: " + memoryBean.getNonHeapMemoryUsage());
	}

	@Test
	public void testThreadMXBean() {
		final ThreadMXBean threadBean = ManagementFactory.getThreadMXBean();
		Map<String, Number> map = new LinkedHashMap<String, Number>();
		map.put("jvm.thread.count", threadBean.getThreadCount());
		map.put("jvm.thread.daemon.count", threadBean.getDaemonThreadCount());
		map.put("jvm.thread.totalstarted.count", threadBean.getTotalStartedThreadCount());
		ThreadInfo[] threadInfos = threadBean.getThreadInfo(threadBean.getAllThreadIds());

		int newThreadCount = 0;
		int runnableThreadCount = 0;
		int blockedThreadCount = 0;
		int waitThreadCount = 0;
		int timeWaitThreadCount = 0;
		int terminatedThreadCount = 0;

		if (threadInfos != null) {
			for (ThreadInfo threadInfo : threadInfos) {
				if (threadInfo != null) {
					switch (threadInfo.getThreadState()) {
					case NEW:
						newThreadCount++;
						break;
					case RUNNABLE:
						runnableThreadCount++;
						break;
					case BLOCKED:
						blockedThreadCount++;
						break;
					case WAITING:
						waitThreadCount++;
						break;
					case TIMED_WAITING:
						timeWaitThreadCount++;
						break;
					case TERMINATED:
						terminatedThreadCount++;
						break;
					default:
						break;
					}
				} else {
					/*
					 * If a thread of a given ID is not alive or does not exist,
					 * the corresponding element in the returned array will,
					 * contain null,because is mut exist ,so the thread is
					 * terminated
					 */
					terminatedThreadCount++;
				}
			}
		}

		map.put("jvm.thread.new.count", newThreadCount);
		map.put("jvm.thread.runnable.count", runnableThreadCount);
		map.put("jvm.thread.blocked.count", blockedThreadCount);
		map.put("jvm.thread.waiting.count", waitThreadCount);
		map.put("jvm.thread.time_waiting.count", timeWaitThreadCount);
		map.put("jvm.thread.terminated.count", terminatedThreadCount);

		long[] ids = threadBean.findDeadlockedThreads();
		map.put("jvm.thread.deadlock.count", ids == null ? 0 : ids.length);

		System.out.println("####################运行时JVM内的线程信息####################");
		map.forEach((key, val) -> {
			System.out.println(key + "=" + val);
		});
	}

	@Test
	public void testRuntimeMXBean() {
		List<String> inputArgs = ManagementFactory.getRuntimeMXBean().getInputArguments();
		System.out.println("\n####################运行时设置的JVM参数####################");
		System.out.println(inputArgs);
	}

	@Test
	public void testRuntime() {
		System.out.println("\n####################运行时内存情况#######################");
		long totle = Runtime.getRuntime().totalMemory();
		System.out.println("总的内存量 [" + totle + "]");
		long free = Runtime.getRuntime().freeMemory();
		System.out.println("空闲的内存量 [" + free + "]");
		long max = Runtime.getRuntime().maxMemory();
		System.out.println("最大的内存量 [" + max + "]");
	}

	@Test
	public void printMemoryInfo() {
		MemoryInformation info = new MemoryInformation();
		System.out.println("新生代(eden)已使用内存：" + info.getUsedEdenSpace());
		System.out.println("新生代(eden)分配内存：" + info.getMaxEdenSpace());
		System.out.println("Survivor已使用内存：" + info.getUsedSurvivorSpace());
		System.out.println("Survivor分配内存：" + info.getMaxSurvivorSpace());

		// 年轻代(YoungGen) = 新生代(eden) + Survivor(From) + Survivor(to)
		System.out.println("年轻代(YoungGen)已使用内存：" + (info.getUsedEdenSpace() + info.getUsedSurvivorSpace()));
		System.out.println("年轻代(YoungGen)分配内存：" + (info.getMaxEdenSpace() + info.getMaxSurvivorSpace()));

		System.out.println("老年代(OldGen)已使用内存：" + info.getUsedOldGen());
		System.out.println("老年代(OldGen)分配内存：" + info.getMaxOldGen());

		System.out.println("heap使用内存：" + info.getUsedMemory());
		System.out.println("heap分配内存：" + info.getMaxMemory());
	}

}
