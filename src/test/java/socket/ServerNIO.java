package socket;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.LinkedList;
import java.util.concurrent.TimeUnit;

/**
 * socket服务端(接收客户端请求并相应) ----- NIO模型
 * 
 * NIO模型，这两种方式都不会阻塞：1、serverSocket.accept() 2、input.readLine()
 */
public class ServerNIO {

	static LinkedList<SocketChannel> clients = new LinkedList<>();

	public static void main(String[] args) throws Exception {
		System.out.println("Server...\n");

		// 开启SocketChannel服务端
		ServerSocketChannel server = ServerSocketChannel.open();
		server.bind(new InetSocketAddress(Consts.SERVER_PORT));
		server.configureBlocking(false); // 设置服务端非阻塞

		while (true) {
			TimeUnit.SECONDS.sleep(1);
			SocketChannel client = server.accept(); // 非阻塞
			if (client == null) {
				System.out.println("null---");
			} else {
				client.configureBlocking(false); // 设置客户端非阻塞
				int clientPort = client.socket().getPort();
				System.out.println("客户端[" + clientPort + "]建立连接");
				clients.add(client);
			}

			ByteBuffer buffer = ByteBuffer.allocateDirect(4096);
			for (SocketChannel sc : clients) {
				int num = sc.read(buffer);
				if (num > 0) {
					buffer.flip();

					// 读取客户端发过来的内容
					byte[] clientInputBytes = new byte[buffer.limit()];
					buffer.get(clientInputBytes);

					String clientInputStr = new String(clientInputBytes);
					System.out.println("客户端[" + sc.socket().getPort() + "]发送内容：" + clientInputStr);
					buffer.clear();
				}
			}
		}
	}

}
