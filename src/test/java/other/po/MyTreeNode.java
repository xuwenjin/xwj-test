package other.po;

/**
 * 二叉树
 */
public class MyTreeNode {

	/**当前节点值*/
	private int data;

	/**左孩子节点*/
	private MyTreeNode leftChild;

	/**右孩子节点*/
	private MyTreeNode rightChild;

	public MyTreeNode() {
	}

	public MyTreeNode(int data) {
		this.data = data;
		this.leftChild = null;
		this.rightChild = null;
	}

	public int getData() {
		return data;
	}

	public void setData(int data) {
		this.data = data;
	}

	public MyTreeNode getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(MyTreeNode leftChild) {
		this.leftChild = leftChild;
	}

	public MyTreeNode getRightChild() {
		return rightChild;
	}

	public void setRightChild(MyTreeNode rightChild) {
		this.rightChild = rightChild;
	}

	@Override
	public String toString() {
		return "MyTreeNode [data=" + data + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
	}

}
