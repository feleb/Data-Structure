package eg.edu.alexu.csd.datastructure.queue.cs51;

import org.junit.Assert;
import org.junit.Test;

public class tes {
	queuearray test = new queuearray(5);
	queuelinkedlist g = new queuelinkedlist();

	@Test
	public void test() {
		Assert.assertEquals(0, test.size());
		test.enqueue(1);
		test.enqueue(2);
		test.enqueue(3);
		test.enqueue(4);
		Assert.assertEquals(4, test.size());
		Assert.assertEquals(1, test.dequeue());

		Assert.assertEquals(3, test.size());
		test.dequeue();
		test.dequeue();
		test.dequeue();
		Assert.assertEquals(0, test.size());
		Assert.assertEquals(true, test.isEmpty());

	}

	@Test
	public void test1() {
		Assert.assertEquals(0, g.size());
		g.enqueue(10);
		g.enqueue(11);
		g.enqueue(12);
		g.enqueue(13);
		g.enqueue(14);
		Assert.assertEquals(10, g.dequeue());
		Assert.assertEquals(11, g.dequeue());
		Assert.assertEquals(3, g.size());
		Assert.assertEquals(false, g.isEmpty());
		g.enqueue(15);
		Assert.assertEquals(4, g.size());
		g.dequeue();
		g.dequeue();
		g.dequeue();
		g.dequeue();
		Assert.assertEquals(0, g.size());
		Assert.assertEquals(true, g.isEmpty());

	}

}
