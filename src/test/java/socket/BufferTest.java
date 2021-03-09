package socket;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * 测试缓冲区Buffer
 * 
 * @author xuwenjin 2021年3月9日
 */
public class BufferTest {

	/**
	 * ByteBuffer包含几个基本的属性：
	 * 
	 * position：当前的下标位置，表示进行下一个读写操作时的起始位置
	 * limit：结束标记下标，表示进行下一个读写操作时的（最大）结束位置
	 * capacity：该ByteBuffer容量
	 * 
	 * position <= limit <= capacity
	 */
	@Test
	public void test1() {
		// 1、分配一个缓冲区，容量设置为10
		ByteBuffer buf = ByteBuffer.allocate(10);
		System.out.println(buf.position()); // 0
		System.out.println(buf.limit()); // 10
		System.out.println(buf.capacity()); // 10
		System.out.println("-----------------------");

		// 2、put：写模式下，往buffer里写一个字节，并把postion移动一位
		String name = "xuwj";
		buf.put(name.getBytes());
		System.out.println(buf.position()); // 4
		System.out.println(buf.limit()); // 10
		System.out.println(buf.capacity()); // 10
		System.out.println("-----------------------");

		// 3、flip：写完数据，需要开始读的时候，将postion复位到0，并将limit设为当前postion
		buf.flip();
		System.out.println(buf.position()); // 0
		System.out.println(buf.limit()); // 4
		System.out.println(buf.capacity()); // 10
		System.out.println("-----------------------");

		// 4、get：从buffer里读一个字节，并把postion移动一位。上限是limit，即写入数据的最后位置。
		char ch = (char) buf.get();
		System.out.println(ch); // x
		System.out.println(buf.position()); // 1
		System.out.println(buf.limit()); // 4
		System.out.println(buf.capacity()); // 10
		System.out.println("-----------------------");

		// 5、循环get
		for (int i = 1; i < buf.capacity(); i++) {
			// limit后的内容不能读写，否则报异常BufferUnderflowException
			System.out.println((char) buf.get());
		}

	}

}
