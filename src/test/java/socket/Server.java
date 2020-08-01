package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * socket服务端(接收客户端请求并相应)
 * 
 * 服务器接收客户端请求步骤：
 * 
 * 1.创建一个ServerSocket实例，监听客户端发来的请求
 * 
 * 2.与客户端获取连接后，创建一个Socket实例，利用I/O流与客户端进行通信，完毕后关闭Socket。
 * 
 * 当然，服务器可以接收多个客户端的请求，所以如果服务器是一个一个顺序相应肯定会带来不好的体验，因此使用多线程来为多个客户端提供服务
 * 
 * 
 * ServerSocket详解：https://www.jianshu.com/p/665994c2e784
 */
public class Server {

	private static final int port = 8080;// 监听的端口号
	// private AtomicInteger onlineCount = new AtomicInteger();

	public static void main(String[] args) {
		System.out.println("Server...\n");
		Server server = new Server();
		server.init();
	}

	public void init() {
		try {
			// 创建一个ServerSocket，这里可以指定连接请求的队列长度
			// 意味着当队列中有3个连接请求，如果Client再请求连接，就会被Server拒绝
			ServerSocket serverSocket = new ServerSocket(port, 3);
			while (true) {
				// 从请求队列中取出一个连接，如果当前没有请求，accpet方法会一直阻塞
				Socket client = serverSocket.accept();

				System.out.println("KeepAlive:" + client.getKeepAlive());
				System.out.println("isBound:" + client.isBound());
				System.out.println("isClosed:" + client.isClosed());
				System.out.println("isConnected:" + client.isConnected());

				// System.out.println("新的连接：" + onlineCount.incrementAndGet());

				// 处理这次连接
				new HandlerThread(client);
			}
		} catch (Exception e) {
			System.out.println("服务器异常: " + e.getMessage());
		}
	}

	private class HandlerThread implements Runnable {

		private Socket socket;

		public HandlerThread(Socket client) {
			socket = client;
			new Thread(this).start();
		}

		public void run() {
			try {
				// 读取客户端数据
				BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String clientInputStr = input.readLine();// 这里要注意和客户端输出流的写方法对应,否则会抛EOFException

				// 处理客户端数据
				System.out.println("客户端发过来的内容:" + clientInputStr);

				// 向客户端回复信息
				PrintStream out = new PrintStream(socket.getOutputStream());
				System.out.print("请输入:\t");
				// 发送键盘输入的一行
				String s = new BufferedReader(new InputStreamReader(System.in)).readLine();
				out.println(s);

				out.close();
				input.close();
			} catch (Exception e) {
				System.out.println("服务器 run 异常: " + e.getMessage());
			} finally {
				if (socket != null) {
					try {
						socket.close();
					} catch (Exception e) {
						socket = null;
						System.out.println("服务端 finally 异常:" + e.getMessage());
					}
				}
			}
		}
	}
}