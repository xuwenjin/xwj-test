package testThread;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.junit.Test;

/**
 * 测试线程池-ExecutorService对象
 * 
 * @author xuwenjin
 */
public class TestExecutorService {
	
	/**
	 *  Executors只是一个工厂类，它所有的方法返回的都是ThreadPoolExecutor、ScheduledThreadPoolExecutor这两个类的实例。
	 * 
	 *  1. newCachedThreadPool 创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
	 *	2. newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
	 * 	3. newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
	 *	4. newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。
	 */
	
	@Test
	public void testExecute(){
		ExecutorService executor = Executors.newFixedThreadPool(10);
		//execute(Runnable): 接收一个Runnable实例，并且异步的执行。没办法获取task返回值
		executor.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep(2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("Asynchronous task");
			}
		});
		//当我们使用完成ExecutorService之后应该关闭它，否则它里面的线程会一直处于运行状态
		//在调用shutdown()方法之后，ExecutorService不会立即关闭，但是它不再接收新的任务，直到当前所有线程执行完成才会关闭
		executor.shutdown();
	}
	
	@Test
	public void testSubmit(){
		ExecutorService executor = Executors.newFixedThreadPool(10);
		//submit(Runnable)：会返回一个future对象，通过返回的Future对象，我们可以检查提交的任务是否执行完毕
		Future<?> future = executor.submit(new Runnable() {
			@Override
			public void run() {
				System.out.println("Asynchronous task");
			}
		});
		try {
			//如果任务执行完成，future.get()方法会返回一个null。注意，future.get()方法会产生阻塞
			System.out.println(future.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testSubmit2(){
		ExecutorService executor = Executors.newFixedThreadPool(10);
		//submit(Callable)：会返回一个future对象，submit(Callable)接收的是一个Callable的实现
		//Callable接口中的call()方法有一个返回值，可以返回任务的执行结果。而Runnable接口中的run()方法是void的，没有返回值
		Future<?> future = executor.submit(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				System.out.println("Asynchronous Callable");
			    return "Callable Result";
			}
		});
		try {
			System.out.println("Hahah");
			//如果任务执行完成，future.get()方法会返回Callable任务的执行结果。注意，future.get()方法会产生阻塞
			System.out.println("future.get() = " + future.get());
			System.out.println("Ixixi");
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvokeAny(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		
		callables.add(new Callable<String>(){
			@Override
			public String call() throws Exception {
				System.out.println("Task1");
				return "Task1";
			}
		});
		
		callables.add(new Callable<String>(){
			@Override
			public String call() throws Exception {
				System.out.println("Task2");
				return "Task2";
			}
		});
		
		callables.add(new Callable<String>(){
			@Override
			public String call() throws Exception {
				System.out.println("Task3");
				return "Task3";
			}
		});
		
		try {
			//invokeAny(...)方法接收的是一个Callable的集合。会返回所有Callable任务中其中一个任务的执行结果
			String result = executor.invokeAny(callables);
			System.out.println("result = " + result);
			executor.shutdown();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testInvokeAll(){
		ExecutorService executor = Executors.newSingleThreadExecutor();
		Set<Callable<String>> callables = new HashSet<Callable<String>>();
		
		callables.add(new Callable<String>(){
			@Override
			public String call() throws Exception {
				return "Task1";
			}
		});
		
		callables.add(new Callable<String>(){
			@Override
			public String call() throws Exception {
				return "Task2";
			}
		});
		
		callables.add(new Callable<String>(){
			@Override
			public String call() throws Exception {
				return "Task3";
			}
		});
		//invokeAll(...)方法接收的是一个Callable的集合。执行之后会返回一个Future的List，其中对应着每个Callable任务执行后的Future对象
		try {
			List<Future<String>> futures = executor.invokeAll(callables);
			for(Future<String> future : futures){
				System.out.println("future.get = " + future.get());
			}
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		executor.shutdown();
	}

}
