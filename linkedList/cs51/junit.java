package eg.edu.alexu.csd.datastructure.linkedList.cs51;

import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Test;

public class junit {
	SingleLinkedList test = new SingleLinkedList();

	@Test
	public void test1() {

		Assert.assertEquals(0, test.size());
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		Assert.assertEquals(1, test.get(0));
		Assert.assertEquals(2, test.get(1));
		Assert.assertEquals(3, test.get(2));
		Assert.assertEquals(4, test.get(3));
		Assert.assertEquals(4, test.size());
		boolean thrown;
		thrown = false;
		try {
			test.get(-1);
			test.get(12);
		} catch (RuntimeException error) {
			thrown = true;
		}
		assertTrue(thrown);
		// Assert.assertEquals(4,test.get(12)); //index > size
		// Assert.assertEquals(4,test.get(-3)); negative index
	}

	@Test
	public void test2() {
		Assert.assertEquals(0, test.size());
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(2, 7);
		Assert.assertEquals(7, test.get(2));
	}

	@Test
	public void test3() {
		Assert.assertEquals(0, test.size());
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.set(0, 7);
		Assert.assertEquals(7, test.get(0));
	}

	@Test
	public void test4() {
		SingleLinkedList sub = new SingleLinkedList();
		Assert.assertEquals(0, test.size());
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.add(4, 8);
		test.add(5, 9);
		sub.add(2);
		sub.add(3);
		sub.add(4);
		Assert.assertEquals(3, sub.size());
		Assert.assertEquals(4, sub.get(2));
	}

	@Test
	public void test5() {
		Assert.assertEquals(0, test.size());
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		Assert.assertEquals(4, test.size());
		test.remove(1);
		Assert.assertEquals(3, test.size());
		Assert.assertEquals(3, test.get(1));
	}

	@Test
	public void test6() {
		Assert.assertEquals(0, test.size());
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		Assert.assertEquals(true, test.contains(1));
		Assert.assertEquals(false, test.contains(12));
	}

	@Test
	public void test7() {
		Assert.assertEquals(0, test.size());
		test.add(1);
		test.add(2);
		test.add(3);
		test.add(4);
		test.clear();
		Assert.assertEquals(true, test.isEmpty());
	}
}
