package socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

import socket.Consts;

/**
 * NIO 同步非阻塞-客户端
 */
public class ClientNIO {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		System.out.println("Client...");

		// 1、获取客户端通道
		SocketChannel sChannel = SocketChannel.open(new InetSocketAddress(Consts.SERVER_PORT));
		sChannel.configureBlocking(false); // 设置非阻塞

		// 2、分配缓冲区大小
		ByteBuffer buffer = ByteBuffer.allocate(1024);

		// 3、发送数据给服务端
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.print("请输入: \t");
			String msg = sc.nextLine();
			buffer.put(msg.getBytes());
			buffer.flip();
			sChannel.write(buffer);
			buffer.clear();
		}
	}

}
