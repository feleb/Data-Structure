package eg.edu.alexu.csd.datastructure.linkedList.cs51;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

class DLinkedList {
	Object value;
	DLinkedList next;
	DLinkedList prev;

	DLinkedList(Object element, DLinkedList afternode, DLinkedList beforenode) {
		this.value = element;
		this.next = afternode;
		this.prev = beforenode;
	}
}

public class doubleLinkedlist implements ILinkedList {

	int size = 0;
	DLinkedList head = null;
	DLinkedList afternode;
	DLinkedList beforenode;

	public void add(int index, Object element) {
		if (index < 0 || index > size) {
			throw new RuntimeException("something error in index");
		} else if (size == 0) {
			DLinkedList newnode = new DLinkedList(element, null, null);
			head = newnode;
			size++;
		} else if (index == 0) {
			DLinkedList newnode = new DLinkedList(element, head, null);
			head.prev = newnode;
			head = newnode;
			size++;
		}

		else {
			beforenode = head;
			for (int i = 0; i < index - 1; i++) {
				beforenode = beforenode.next;
			}
			DLinkedList newnode = new DLinkedList(element, beforenode.next, beforenode);
			beforenode.next = newnode;
			beforenode.next.prev = newnode;
			size++;
		}

	}

	@Override
	public void add(Object element) {

		if (size == 0) {
			DLinkedList newnode = new DLinkedList(element, null, null);
			head = newnode;
		} else {
			beforenode = head;
			while (beforenode.next != null) {
				beforenode = beforenode.next;
			}
			DLinkedList newnode = new DLinkedList(element, null, beforenode);
			beforenode.next = newnode;
		}
		size++;
	}

	@Override
	public Object get(int index) {
		if (index < 0 || index > size)
			throw new RuntimeException("something error in index");
		else {
			if (index == size) {
				index--;
			}
			beforenode = head;
			for (int i = 0; i < index; i++) {
				beforenode = beforenode.next;
			}
			return beforenode.value;
		}
	}

	@Override
	public void set(int index, Object element) {
		if (index < 0 || index > size)
			throw new RuntimeException("something error in index");
		else {
			beforenode = head;
			for (int i = 0; i < index; i++) {
				beforenode = beforenode.next;
			}
			beforenode.value = element;
		}

	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public boolean isEmpty() {
		if (head == null || size == 0) {
			return true;
		} else
			return false;
	}

	@Override
	public void remove(int index) {
		if (index < 0 || index >= size)
			throw new RuntimeException("you try to remove wrong index ");
		else if (head == null)
			throw new RuntimeException("Linked list is empty");
		else if (index == 0) {
			beforenode = head.next;
			beforenode.prev = null;
			head = beforenode;
		} else {
			beforenode = head;
			for (int i = 0; i < index - 1; i++) {
				beforenode = beforenode.next;
			}

			beforenode.next.prev = beforenode;
			beforenode.next = beforenode.next.next;
		}
		size--;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {

		if (fromIndex < 0 || fromIndex > size || toIndex < 0 || toIndex > size || fromIndex > toIndex || head == null) {
			throw new RuntimeException("error at fromIndex or toIndex ");
		} else {
			doubleLinkedlist SUBLIST = new doubleLinkedlist();
			beforenode = head;
			for (int i = 0; i < fromIndex; i++) {
				beforenode = beforenode.next;
			}
			for (int i = fromIndex; i <= toIndex; i++) {
				SUBLIST.add(beforenode.value);
				beforenode = beforenode.next;
			}
			return SUBLIST;
		}
	}

	@Override
	public boolean contains(Object o) {
		beforenode = head;
		while (beforenode != null) {
			if (beforenode.value.equals(o)) {
				return true;
			}
			beforenode = beforenode.next;
		}
		return false;
	}

}
