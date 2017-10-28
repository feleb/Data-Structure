package eg.edu.alexu.csd.filestructure.avl;

public class AVLTree<T extends Comparable<T>> implements IAVLTree<T> {
	private Node<T> root;

	public AVLTree() {
		root = new Node<T>(null, null);
	}

	@Override
	public void insert(T key) {

		Node<T> cursor = null; // y
		Node<T> current = root; // x
		Node<T> newNode = new Node<T>(key, null); // z
		while (current != null && current.getValue() != null) {
			cursor = current;
			try {
				if (newNode.getValue().compareTo(current.getValue()) < 0) {
					current = (Node<T>) current.getLeftChild();
				} else if (newNode.getValue().compareTo(current.getValue()) > 0) {
					current = (Node<T>) current.getRightChild();
				}
			} catch (NullPointerException ex) {
				break;
			}
		}

		newNode.setParent(cursor);
		if (cursor == null) {
			root = newNode;
		} else if (newNode.getValue().compareTo(cursor.getValue()) < 0) {
			cursor.setLeftChild(newNode);
			newNode.setParent(cursor);
			reBalance((Node<T>) cursor.getLeftChild());
		} else if (newNode.getValue().compareTo(cursor.getValue()) > 0) {
			cursor.setRightChild(newNode);
			newNode.setParent(cursor);
			reBalance((Node<T>) cursor.getRightChild());
		}

	}

	public void reBalance(Node<T> t) {
		Node<T> parent = null; // up
		Node<T> check = t;
		Node<T> child = t;
		while (check.hasParent()) {
			// parent = (Node<T>) check.getParent();
			child = check;
			check = (Node<T>) check.getParent();
			int balanceFactor = check.getBalanceFactor();
			if (balanceFactor == 2) {
				if (check.getParent() != null)
					parent = (Node<T>) check.getParent();
				if (child.getBalanceFactor() == 1) {
					leftLeft(check, child, parent);
					break;
				} else if (child.getBalanceFactor() == -1) {
					leftRight(check, child, parent);
					break;
				}
			} else if (balanceFactor == -2) {

				if (check.getParent() != null)
					parent = (Node<T>) check.getParent();
				if (child.getBalanceFactor() == 1) {
					rightLeft(check, child, parent);
					break;
				} else if (child.getBalanceFactor() == -1) {
					rightRight(check, child, parent);
					break;

				}
			}
		}
	}

	private void rightRight(Node<T> check, Node<T> child, Node<T> parent) {
		boolean right = false, noParent = false;
		check.setRightChild(null);
		child.setParent(null);
		if (parent == null)
			noParent = true;
		else if (parent.getValue().compareTo(check.getValue()) < 0)
			right = true;

		if (!noParent) {
			if (right)
				parent.setRightChild(child);
			else
				parent.setLeftChild(child);
			child.setParent(parent);
		}
		if (child.hasLeftChild()) {
			((Node<T>) child.getLeftChild()).setParent(check);
			check.setRightChild((Node<T>) child.getLeftChild());
		}
		child.setLeftChild(check);
		check.setParent(child);
		if (check == root)
			root = child;
	}

	private void rightLeft(Node<T> check, Node<T> child, Node<T> parent) {
		Node<T> n = (Node<T>) child.getLeftChild();
		boolean right = false, noParent = false;
		check.setRightChild(null);
		child.setLeftChild(null);
		if (parent == null)
			noParent = true;
		else if (parent.getValue().compareTo(check.getValue()) < 0)
			right = true;

		if (!noParent) {
			if (right)
				parent.setRightChild(n);
			else
				parent.setLeftChild(n);
			n.setParent(parent);
		}

		if (n.hasRightChild()) {
			((Node<T>) n.getRightChild()).setParent(child);
			child.setLeftChild((Node<T>) n.getRightChild());
		}
		n.setRightChild(child);
		child.setParent(n);
		if (n.hasLeftChild()) {
			((Node<T>) n.getLeftChild()).setParent(check);
			check.setRightChild((Node<T>) n.getLeftChild());
		}
		n.setLeftChild(check);
		check.setParent(n);
		if (check == root)
			root = n;

	}

	private void leftRight(Node<T> check, Node<T> child, Node<T> parent) {
		Node<T> n = (Node<T>) child.getRightChild();
		boolean right = false, noParent = false;
		check.setLeftChild(null);
		child.setRightChild(null);
		if (parent == null)
			noParent = true;
		else if (parent.getValue().compareTo(check.getValue()) < 0)
			right = true;

		if (!noParent) {
			if (right)
				parent.setRightChild(n);
			else
				parent.setLeftChild(n);
			n.setParent(parent);
		}

		if (n.hasRightChild()) {
			((Node<T>) n.getRightChild()).setParent(check);
			check.setLeftChild((Node<T>) n.getRightChild());
		}
		n.setRightChild(check);
		check.setParent(n);
		if (n.hasLeftChild()) {
			((Node<T>) n.getLeftChild()).setParent(child);
			child.setRightChild((Node<T>) n.getLeftChild());
		}
		n.setLeftChild(child);
		child.setParent(n);
		if (check == root)
			root = n;

	}

	private void leftLeft(Node<T> check, Node<T> child, Node<T> parent) {
		boolean right = false, noParent = false;
		check.setLeftChild(null);
		child.setParent(null);
		if (parent == null)
			noParent = true;
		else if (parent.getValue().compareTo(check.getValue()) < 0)
			right = true;

		if (!noParent) {
			if (right)
				parent.setRightChild(child);
			else
				parent.setLeftChild(child);
			child.setParent(parent);
		}
		if (child.hasRightChild()) {
			((Node<T>) child.getRightChild()).setParent(check);
			check.setLeftChild((Node<T>) child.getRightChild());

		}
		child.setRightChild(check);
		check.setParent(child);
		if (check == root)
			root = child;
	}

	public void inOrder(Node root) {

		if (root != null) {
			inOrder((Node<T>) root.getLeftChild());
			System.out.println(root.getValue());
			inOrder((Node) root.getRightChild());
		}
	}

	private Node<T> minimum(Node<T> node) {
		if (!node.hasLeftChild())
			return node;
		return minimum((Node<T>) node.getLeftChild());

	}

	private void deleteRebalance(Node<T> t) {
		Node<T> parent = null; // up
		Node<T> check = t;
		Node<T> child = t;
		if (check.getValue() != root.getValue()) {
			while (check.hasParent()) {
				check = (Node<T>) check.getParent();
				int balanceFactor = check.getBalanceFactor();
				if (balanceFactor == 2) {
					if (check.getParent() != null)
						parent = (Node<T>) check.getParent();
					if (check.hasLeftChild()) {
						int leftChildBF = ((Node) check.getLeftChild()).getBalanceFactor();
						if (leftChildBF == 1 || leftChildBF == 0) {
							child = (Node<T>) check.getLeftChild();
							leftLeft(check, child, parent);
						} else if (leftChildBF == -1) {
							child = (Node<T>) check.getLeftChild();
							leftRight(check, child, parent);
						}
					}
				} else if (balanceFactor == -2) {
					if (check.getParent() != null)
						parent = (Node<T>) check.getParent();
					if (check.hasRightChild()) {
						int rightChildBF = ((Node) check.getRightChild()).getBalanceFactor();
						if (rightChildBF == -1 || rightChildBF == 0) {
							child = (Node<T>) check.getRightChild();
							rightRight(check, child, parent);
						} else if (rightChildBF == 1) {
							child = (Node<T>) check.getRightChild();
							rightLeft(check, child, parent);
						}
					}
				}
			}
		} else {
			int balanceFactor = check.getBalanceFactor();
			if (balanceFactor == 2) {
				child = (Node<T>) check.getLeftChild();
				if (check.getParent() != null)
					parent = (Node<T>) check.getParent();
				if (check.hasLeftChild()) {
					int leftChildBF = child.getBalanceFactor();
					if (leftChildBF == 1 || leftChildBF == 0) {
						leftLeft(check, child, parent);
					} else if (leftChildBF == -1) {
						leftRight(check, child, parent);
					}
				}
			} else if (balanceFactor == -2) {
				child = (Node<T>) check.getRightChild();
				if (check.getParent() != null)
					parent = (Node<T>) check.getParent();
				if (check.hasRightChild()) {
					int rightChildBF = child.getBalanceFactor();
					if (rightChildBF == -1 || rightChildBF == 0) {
						rightRight(check, child, parent);
					} else if (rightChildBF == 1) {
						rightLeft(check, child, parent);
					}
				}
			}
		}
	}

	private void transplant(Node<T> toBeRemoved, Node<T> toBePlanted) {

		Node<T> toBeRemovedParent = (Node<T>) toBeRemoved.getParent();
		if (!toBeRemoved.hasParent()) {
			root = toBePlanted;
		} else if (toBeRemoved.equals(toBeRemovedParent.getLeftChild())) {
			toBeRemovedParent.setLeftChild(toBePlanted);
		} else if (toBeRemoved.equals(toBeRemovedParent.getRightChild())) {
			toBeRemovedParent.setRightChild(toBePlanted);
		}
		toBeRemoved.setParent(null);

		if (toBePlanted != null) {
			Node<T> toBePlantedParent = (Node<T>) toBePlanted.getParent();
			if (!toBeRemoved.equals(toBePlantedParent)) {
				if (toBePlantedParent.hasLeftChild() && toBePlantedParent.getLeftChild().equals(toBePlanted)) {
					toBePlantedParent.setLeftChild((Node) toBePlanted.getRightChild());
					if (toBePlanted.hasRightChild())
						((Node) toBePlanted.getRightChild()).setParent(toBePlantedParent);
				} else if (toBePlantedParent.hasRightChild() && toBePlantedParent.getRightChild().equals(toBePlanted)) {
					toBePlantedParent.setRightChild(null);
				}

				toBePlanted.setParent(toBeRemovedParent);

				if (toBeRemoved.hasLeftChild()) {
					Node<T> leftOfToBeRemoved = ((Node<T>) toBeRemoved.getLeftChild());
					toBePlanted.setLeftChild(leftOfToBeRemoved);
					leftOfToBeRemoved.setParent(toBePlanted);
					toBeRemoved.setLeftChild(null);

				}

				if (toBeRemoved.hasRightChild()) {
					Node<T> rightOfToBeRemoved = ((Node<T>) toBeRemoved.getRightChild());
					toBePlanted.setRightChild(rightOfToBeRemoved);
					rightOfToBeRemoved.setParent(toBePlanted);
					toBeRemoved.setRightChild(null);

				}
			} else if (toBeRemoved.equals(toBePlantedParent)) {
				toBePlanted.setParent(toBeRemovedParent);
				if (toBeRemoved.hasRightChild() && toBeRemoved.getRightChild().equals(toBePlanted)) {
					toBePlanted.setLeftChild((Node) toBeRemoved.getLeftChild());
					if (toBeRemoved.hasLeftChild())
						((Node) toBePlanted.getLeftChild()).setParent(toBePlanted);
				} else if (toBeRemoved.hasLeftChild() && toBeRemoved.getLeftChild().equals(toBePlanted)) {
					toBePlanted.setRightChild((Node) toBeRemoved.getRightChild());
					if (toBeRemoved.hasRightChild())
						((Node) toBePlanted.getRightChild()).setParent(toBePlanted);
				}
			}
			}
		if (toBeRemovedParent != null){
			deleteRebalance(toBeRemovedParent);
		}
		else{
			deleteRebalance(root);
		}
	}

	@Override
	public boolean delete(T key) {
		if (!search(key))
			return false;

		Node<T> toBeDeleted = searchReturnsNode(key);
		if (!toBeDeleted.hasLeftChild()) {
			this.transplant(toBeDeleted, (Node<T>) toBeDeleted.getRightChild());
		} else if (!toBeDeleted.hasRightChild()) {
			this.transplant(toBeDeleted, (Node<T>) toBeDeleted.getLeftChild());
		} else {
			Node<T> node = minimum((Node<T>) toBeDeleted.getRightChild());
			this.transplant(toBeDeleted, node);
		}

		return true;
	}

	private Node<T> searchReturnsNode(T key, Node<T> node) {
		if (key.compareTo(node.getValue()) == 0)
			return node;
		if (key.compareTo(node.getValue()) < 0)
			return searchReturnsNode(key, (Node<T>) node.getLeftChild());
		else
			return searchReturnsNode(key, (Node<T>) node.getRightChild());

	}

	public Node<T> searchReturnsNode(T key) {
		return this.searchReturnsNode(key, root);
	}

	private boolean search(T key, Node<T> node) {
		if (node != null) {
			if (key.compareTo(node.getValue()) == 0)
				return true;
			if (key.compareTo(node.getValue()) < 0)
				return search(key, (Node<T>) node.getLeftChild());
			else
				return search(key, (Node<T>) node.getRightChild());
		}
		return false;

	}

	@Override
	public boolean search(T key) {
		return this.search(key, root);
	}

	@Override
	public int height() {
		if (root.getValue() == null) {
			return -1;
		} else {
			return Math.max(root.getLeftHeight(), root.getLeftHeight()) + 1;
		}
	}

	@Override
	public INode<T> getTree() {
		return root;
	}

}