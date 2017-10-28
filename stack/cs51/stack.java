package eg.edu.alexu.csd.datastructure.stack.cs51;

import eg.edu.alexu.csd.datastructure.linkedList.cs51.SingleLinkedList;
import eg.edu.alexu.csd.datastructure.stack.IStack;

public class stack implements IStack {

	SingleLinkedList list = new SingleLinkedList();
	int top = -1;

	@Override
	public void add(int index, Object element) {
		// if (index >list.size())throw new RuntimeException("error in index");
		list.add(list.size() - index, element);
		top++;
	}

	@Override
	public Object pop() {
		if (top == -1)
			throw new RuntimeException("the stack is empty");
		Object v = list.get(0);
		list.remove(0);
		top--;
		return v;
	}

	@Override
	public Object peek() {
		Object x = list.get(0);
		return x;

	}

	@Override
	public void push(Object element) {
		list.add(0, element);
		top++;
	}

	@Override
	public boolean isEmpty() {

		return (top == -1);
	}

	@Override
	public int size() {
		return list.size();
	}
}
