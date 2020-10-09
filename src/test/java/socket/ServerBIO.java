package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端(接收客户端请求并相应) ----- BIO模型
 * 
 * 服务器接收客户端请求步骤：
 * 
 * 1.创建一个ServerSocket实例，监听客户端发来的请求
 * 
 * 2.与客户端获取连接后，创建一个Socket实例，利用I/O流与客户端进行通信，完毕后关闭Socket。
 * 
 * 当然，服务器可以接收多个客户端的请求，所以如果服务器是一个一个顺序响应肯定会带来不好的体验，因此使用多线程来为多个客户端提供服务
 * 
 * 
 * BIO模型，会有两次阻塞：1、serverSocket.accept() 2、input.readLine()
 * 
 * 
 * ServerSocket详解：https://www.jianshu.com/p/665994c2e784
 */
public class ServerBIO {

	public static void main(String[] args) {
		System.out.println("Server...\n");
		ServerBIO server = new ServerBIO();
		server.init();
	}

	@SuppressWarnings("resource")
	public void init() {
		try {
			// 创建一个ServerSocket，这里可以指定连接请求的队列长度
			// 意味着当队列中有3个连接请求，如果Client再请求连接，就会被Server拒绝
			ServerSocket serverSocket = new ServerSocket(Consts.SERVER_PORT, 3);
			while (true) {
				// 从请求队列中取出一个连接，如果当前没有请求，accpet方法会一直阻塞
				System.out.println("null---");
				Socket client = serverSocket.accept();

				// 建立连接后，创建一个子线程，处理这次连接
				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

	private class HandlerThread implements Runnable {

		private Socket client;

		public HandlerThread(Socket client) {
			this.client = client;
			new Thread(this).start();
		}

		public void run() {
			try {
				int clientPort = client.getPort();
				System.out.println("客户端[" + clientPort + "]建立连接");

				// 读取客户端数据 --- 会阻塞
				BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
				String clientInputStr = input.readLine();// 这里要注意和客户端输出流的写方法对应,否则会抛EOFException

				// 处理客户端数据
				System.out.println("客户端[" + clientPort + "]发送内容：" + clientInputStr);

				// 向客户端回复信息
				PrintStream out = new PrintStream(client.getOutputStream());
				System.out.print("请输入:\t");
				// 发送键盘输入的一行
				String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
				out.println(s);

				out.close();
				input.close();
			} catch (Exception e) {
				System.out.println("服务器 run 异常: " + e.getMessage());
			} finally {
				if (client != null) {
					try {
						client.close();
					} catch (Exception e) {
						client = null;
						System.out.println("服务端 finally 异常:" + e.getMessage());
					}
				}
			}
		}
	}
}