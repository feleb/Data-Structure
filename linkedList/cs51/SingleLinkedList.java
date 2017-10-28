package eg.edu.alexu.csd.datastructure.linkedList.cs51;

import eg.edu.alexu.csd.datastructure.linkedList.ILinkedList;

class Linkedlist {
	public Object value;
	public Linkedlist next;

	public Linkedlist(Object element, Linkedlist pointer) { // constructor with
															// value has type of
															// object and
															// pointer will
															// refer to the next
															// element
		this.value = element;
		this.next = pointer;
	}

}

public class SingleLinkedList implements ILinkedList {
	int size = 0;
	Linkedlist head = null;
	Linkedlist pointer;

	@Override
	public void add(int index, Object element) {
		if (index > size || index < 0) {
			throw new RuntimeException("you try to add wrong index");
		} else if (index == 0) // head will refer to null then the new node will
								// refer to null too!
		{
			Linkedlist newnode = new Linkedlist(element, head);// create new
																// node with
																// value =
																// element and
																// pointer to
																// head
			head = newnode;
			size++;
		} else {

			pointer = head;
			for (int i = 0; i < index - 1; i++) { // index-1 the prev element to
													// the new node place
				pointer = pointer.next;
			}
			Linkedlist newnode = new Linkedlist(element, pointer.next); // create
																		// new
																		// node
			pointer.next = newnode;
			size++;
		}

	}

	@Override
	public void add(Object element) {
		Linkedlist newnode = new Linkedlist(element, null);
		if (size == 0) {
			head = newnode;
		} else {
			pointer = head;
			while (pointer.next != null) {
				pointer = pointer.next;
			}
			pointer.next = newnode;
		}
		size++;

	}

	@Override
	public Object get(int index) {
		if (index > size || index < 0) { // incorrect index
			throw new RuntimeException("you try to get unfound index");
		}

		else {
			if (index == size) {
				index = index - 1;
			}
			pointer = head;
			for (int i = 0; i < index; i++) {
				pointer = pointer.next;
			}
			return pointer.value;
		}

	}

	@Override
	public void set(int index, Object element) {
		if (index > size || index < 0) { // incorrect index
			throw new RuntimeException("you try to set value at incorrect index");
		} else {
			pointer = head;
			for (int i = 0; i < index; i++) { // get the index of the wanted
												// element
				pointer = pointer.next;
			}
			pointer.value = element; // set the given value
		}

	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	public boolean isEmpty() {
		if (head == null || size == 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void remove(int index) {
		if (index < 0 || index >= size)
			throw new RuntimeException("you try to remove wrong index ");
		else if (head == null)
			throw new RuntimeException("error linkedlist is empty");
		else if (index == 0) {
			pointer = head;
			head = head.next;
			pointer.next = null;
			size--;
		} else {
			pointer = head;
			for (int j = 0; j < index - 1; j++) {
				pointer = pointer.next;
			}
			pointer.next = pointer.next.next;
			size--;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public ILinkedList sublist(int fromIndex, int toIndex) {
		if (fromIndex < 0 || fromIndex > size || toIndex < 0 || toIndex > size || fromIndex > toIndex) {
			throw new RuntimeException("incorrect bounders ");
		} else {
			SingleLinkedList SUBLIST = new SingleLinkedList();
			pointer = head;
			for (int i = 0; i < fromIndex; i++) {
				pointer = pointer.next;
			}
			for (int i = fromIndex; i <= toIndex; i++) {
				SUBLIST.add(pointer.value);
				pointer = pointer.next;
			}
			return SUBLIST;
		}

	}

	public boolean contains(Object o) {
		pointer = head;
		while (pointer != null) {
			if (pointer.value.equals(o)) {
				return true;
			}
			pointer = pointer.next;
		}
		return false;
	}

}