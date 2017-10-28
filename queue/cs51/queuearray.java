package eg.edu.alexu.csd.datastructure.queue.cs51;

import eg.edu.alexu.csd.datastructure.queue.IArrayBased;
import eg.edu.alexu.csd.datastructure.queue.IQueue;

public class queuearray implements IQueue, IArrayBased {

	public int n, first = -1, last = -1;;
	Object[] arr;
	int count;

	public queuearray(int s) {
		n = s;
		arr = new Object[n];

	}

	@Override
	public void enqueue(Object item) {

		if (last == n - 1 && first == -1)
			throw new RuntimeException("the array is full");
		else {
			last = (last + 1) % n;
			arr[last] = item;
		}
		count++;
	}

	@Override
	public Object dequeue() {
		Object h;
		if (count == 0)
			throw new RuntimeException("the queue is empty");
		else {
			first = (first + 1) % n;
			h = arr[first];
		}
		count--;
		return h;
	}

	@Override
	public boolean isEmpty() {

		return (count == 0);
	}

	@Override
	public int size() {
		return (count);
	}
	/*
	 * public static void main (String [] args){ queuearray g =new
	 * queuearray(4); g.enqueue(1); g.enqueue(2); g.enqueue(3); g.enqueue(4);
	 * g.dequeue(); g.enqueue(5); g.dequeue(); g.dequeue(); g.dequeue();
	 * System.out.println(g.dequeue()); }
	 */

}
