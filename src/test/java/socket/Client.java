package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

/**
 * socket客户端
 * 
 * 客户端向服务器发送请求可分为以下步骤：
 * 
 * 1.创建一个Socket实例
 * 
 * 2.利用I/O流与服务器进行通信
 * 
 * 3.关闭socket
 */
public class Client {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		System.out.println("Client Start...");

		// 创建一个socket客户单，并将其连接到指定主机上的指定端口号
		Socket client = new Socket(Consts.SERVER_HOST, Consts.SERVER_PORT);

		while (true) {
			if (client.isClosed()) {
				System.out.println("客户端已关闭 ");
				break;
			}

			// 向服务器端发送数据
			PrintStream out = new PrintStream(client.getOutputStream());
			System.out.print("请输入: \t");
			String str = new BufferedReader(new InputStreamReader(System.in)).readLine();
			out.println(str);

			// 读取服务器端数据
			BufferedReader input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			String ret = input.readLine();
			System.out.println("服务端返回过来的是: " + ret);

			out.close();
			input.close();
		}
	}

}