package other;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import other.po.MyTreeNode;

/**
 * 测试二叉树
 */
public class TestTreeNode {

	public static void main(String[] args) {
		int[] arr = { 7, 6, 4, 5, 11, 3, 1, 12, 8, 4, 4 };
		MyTreeNode root = createTreeNode(arr);
		System.out.println(levelPrint(root));

		ArrayList<ArrayList<Integer>> result = levelRowPrint(root);
		result.stream().forEach(System.out::println);
	}

	/**
	 * 创建一颗二叉树
	 */
	public static MyTreeNode createTreeNode(int[] arr) {
		MyTreeNode root = new MyTreeNode(arr[0]);
		for (int i = 1; i < arr.length; i++) {
			int data = arr[i];
			MyTreeNode newNode = new MyTreeNode(data);
			addNode(root, newNode);
		}
		return root;
	}

	public static void addNode(MyTreeNode parentNode, MyTreeNode newNode) {
		// 小于根节点放左边
		int parentData = parentNode.getData();
		int newData = newNode.getData();
		if (newData < parentData) {
			if (parentNode.getLeftChild() == null) {
				parentNode.setLeftChild(newNode);
			} else {
				addNode(parentNode.getLeftChild(), newNode);
			}
		}
		// 大于根节点放右边
		if (newData >= parentData) {
			if (parentNode.getRightChild() == null) {
				parentNode.setRightChild(newNode);
			} else {
				addNode(parentNode.getRightChild(), newNode);
			}
		}
	}

	/**
	 * 层级遍历二叉树(从上到下，从左到右)
	 */
	public static ArrayList<Integer> levelPrint(MyTreeNode root) {
		ArrayList<Integer> result = new ArrayList<>();

		Queue<MyTreeNode> queue = new LinkedList<>();
		queue.offer(root);

		while (!queue.isEmpty()) {
			// 从queue中弹出队顶元素
			MyTreeNode tempNode = queue.poll();
			result.add(tempNode.getData());

			// 左孩子不为空，则将左孩子压入队列底部
			if (tempNode.getLeftChild() != null) {
				queue.offer(tempNode.getLeftChild());
			}

			// 右孩子不为空，则将右孩子压入队列底部
			if (tempNode.getRightChild() != null) {
				queue.offer(tempNode.getRightChild());
			}
		}

		return result;
	}

	/**
	 * 层级遍历二叉树(从上到下，从左到右)  -- 返回的结果，每次是一层的数据
	 */
	public static ArrayList<ArrayList<Integer>> levelRowPrint(MyTreeNode node) {
		ArrayList<ArrayList<Integer>> result = new ArrayList<>();

		Queue<MyTreeNode> queue = new LinkedList<>();
		queue.offer(node);

		while (!queue.isEmpty()) {
			ArrayList<Integer> list = new ArrayList<>();
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				MyTreeNode tempNode = queue.poll();
				list.add(tempNode.getData());
				if (tempNode.getLeftChild() != null) {
					queue.offer(tempNode.getLeftChild());
				}
				if (tempNode.getRightChild() != null) {
					queue.offer(tempNode.getRightChild());
				}
			}
			result.add(list);
		}

		return result;
	}

}
