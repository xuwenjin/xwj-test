package other;

import java.util.LinkedList;

/**
 * 测试LinkedList
 * 
 * @author xuwenjin 2020年12月16日
 */
public class TestLinkedList {

	/**
	 * 1、LinkedList是通过双向链表实现的，因此不存在容量不足的问题，所以不需要扩容。
	 * 2、插入效率很高(头插入、尾插入不需要移动任何元素，中间插入只需要建立前后指向就可以)。
	 * 3、它的随机访问效率比ArrayList要低，顺序访问的效率要比较的高。
	 * 4、实现了Deque，所以可以当做双端队列来使用。
	 */
	public static void main(String[] args) {
		LinkedList<Integer> linkedList = new LinkedList<>();
		// 顺序插入
		linkedList.add(1);
		linkedList.add(2);
		linkedList.add(3);
		linkedList.add(4);
		System.out.println(linkedList);

		// 中间插入
		linkedList.add(2, 10);
		linkedList.add(3, 20);
		System.out.println(linkedList);

		// 头插入
		linkedList.addFirst(-1);
		linkedList.addFirst(-2);
		System.out.println(linkedList);

		// 尾插入(和顺序插入一样的)
		linkedList.addLast(100);
		linkedList.addLast(200);
		System.out.println(linkedList);

		System.out.println("-------------------get-----------------");
		System.out.println(linkedList.getFirst());
		System.out.println(linkedList.getLast());
		System.out.println(linkedList.get(2));

		System.out.println("-------------------set-----------------");
		linkedList.set(3, 30);
		linkedList.set(4, 40);
		System.out.println(linkedList);

		System.out.println("-------------------remove-----------------");
		System.out.println(linkedList.remove(2));
		System.out.println(linkedList);
		System.out.println(linkedList.removeFirst());
		System.out.println(linkedList);
		System.out.println(linkedList.removeLast());
		System.out.println(linkedList);
		System.out.println(linkedList.remove()); // remove()和removeFirst()是一样的
		System.out.println(linkedList);

		System.out.println("-------------------offer-----------------");
		linkedList.offer(-10); // 和add一样
		linkedList.offerFirst(-9);
		linkedList.offerLast(1000);
		System.out.println(linkedList);

		System.out.println("-------------------poll-----------------");
		System.out.println(linkedList.poll()); // 和remove一样
		System.out.println(linkedList.pollFirst());
		System.out.println(linkedList.pollLast());
		System.out.println(linkedList);
	}

}
