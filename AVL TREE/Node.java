package eg.edu.alexu.csd.filestructure.avl;

public class Node<T extends Comparable<T>> implements INode<T> {
	private T value;
	private Node<T> rightChild = null;
	private Node<T> leftChild = null;
	private Node<T> parent = null;

	public Node(T value, Node parent) {
		this.value = value;
		this.parent = parent;
	}

	public int getBalanceFactor() {
		return this.getLeftHeight() - this.getRightHeight();
	}

	public int getLeftHeight() {

		if (this.hasLeftChild()) {
			int leftH = ((Node) this.getLeftChild()).getLeftHeight();
			int rightH = ((Node) this.getLeftChild()).getRightHeight();
			if (rightH > leftH) {
				return rightH + 1;
			} else
				return leftH + 1;
		}

		return 0;
	}

	public int getRightHeight() {

		if (this.hasRightChild()) {
			int leftH = ((Node) this.getRightChild()).getLeftHeight();
			int rightH = ((Node) this.getRightChild()).getRightHeight();
			if (rightH > leftH) {
				return rightH + 1;
			} else
				return leftH + 1;
		}
		return 0;
	}

	public boolean hasLeftChild() {
		if (this.leftChild == null)
			return false;
		return true;
	}

	public boolean hasParent() {
		if (this.parent == null)
			return false;
		return true;
	}

	public boolean hasRightChild() {
		if (this.rightChild == null)
			return false;
		return true;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	@Override
	public INode<T> getLeftChild() {
		return this.leftChild;
	}

	@Override
	public INode<T> getRightChild() {
		return this.rightChild;
	}

	public INode<T> getParent() {
		return this.parent;
	}

	@Override
	public T getValue() {
		return this.value;
	}

	@Override
	public void setValue(T value) {
		this.value = value;

	}

}