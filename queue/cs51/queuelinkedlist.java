package eg.edu.alexu.csd.datastructure.queue.cs51;

import eg.edu.alexu.csd.datastructure.linkedList.cs51.SingleLinkedList;
import eg.edu.alexu.csd.datastructure.queue.ILinkedBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

public class queuelinkedlist implements IQueue, ILinkedBased {

	int temp = 0;
	SingleLinkedList k = new SingleLinkedList();

	@Override
	public void enqueue(Object item) {

		k.add(item);
		temp++;
	}

	@Override
	public Object dequeue() {
		if (temp == 0)
			throw new RuntimeException("the queue is empty");
		Object r = k.get(0);
		k.remove(0);
		temp--;
		return r;
	}

	@Override
	public boolean isEmpty() {

		return (temp == 0);
	}

	@Override
	public int size() {
		return temp;
	}

	/*
	 * public static void main (String[]args){ queuelinkedlist c =new
	 * queuelinkedlist(); c.enqueue(1); c.enqueue(2); c.enqueue(3);
	 * System.out.println(c.dequeue()); }
	 */
}
