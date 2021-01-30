package socket.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

import socket.Consts;

/**
 * NIO 同步非阻塞-服务端
 * 
 * NIO 三大核心部分：
 * Channel(通道)：支持(同步或异步)读取或写入缓存区，也可以同时进行读写
 * Buffer(缓冲区)：本质上是一块可以写入数据，也可以从中读取数据的内内存
 * Selector(多路复用器)：可以监听一个或多个NIO通道，并确定哪些通道已经准备好进行读取或者写入
 * 
 * NIO 模型，这两种方式都不会阻塞：1、accept() 2、read()
 */
public class ServerNIO {

	public static void main(String[] args) throws Exception {
		System.out.println("Server...");

		// 1、获取服务端通道
		ServerSocketChannel ssChannel = ServerSocketChannel.open();
		ssChannel.bind(new InetSocketAddress(Consts.SERVER_PORT));// 绑定客户端连接的端口
		ssChannel.configureBlocking(false); // 设置非阻塞

		// 2、获取多路复用器
		Selector selector = Selector.open();

		// 3、将服务端通道注册到选择器，并且指定为accept事件
		ssChannel.register(selector, SelectionKey.OP_ACCEPT);

		// 4、多路复用器轮询是否有已经就绪的事件
		while (selector.select() > 0) {

			// 5、获取选择器中，所有注册的通道中已经就绪的事件Key
			Iterator<SelectionKey> it = selector.selectedKeys().iterator();

			while (it.hasNext()) {
				SelectionKey key = it.next();
				if (key.isAcceptable()) { // 判断是否为accept事件
					System.out.println("accpet事件~~");
					SocketChannel sChannel = ssChannel.accept(); // 非阻塞
					sChannel.configureBlocking(false); // 设置客户端非阻塞

					// 6、将客户端通道注册到多路复用器，并且指定到为read事件
					sChannel.register(selector, SelectionKey.OP_READ);
				} else if (key.isReadable()) { // 判断是否为read事件
					System.out.println("read事件~~");

					// 7、获取当前多路复用器上的读就绪事件
					SocketChannel sChannel = (SocketChannel) key.channel();

					// 8、读取数据
					ByteBuffer buffer = ByteBuffer.allocate(1024);
					int len = 0;
					while ((len = sChannel.read(buffer)) > 0) {
						buffer.flip();
						String clientInputStr = new String(buffer.array(), 0, len);
						System.out.println("客户端[" + sChannel.socket().getPort() + "]发送的内容：" + clientInputStr);
						buffer.clear();
					}
				}

				// 9、事件处理完后，移除事件
				it.remove();
			}
		}
	}

}
