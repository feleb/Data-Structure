package eg.edu.alexu.csd.datastructure.stack.cs51;

import org.junit.Assert;
import org.junit.Test;

public class blbl {
	stack h = new stack();
	app t = new app();

	@Test
	public void test() {
		Assert.assertEquals("2", t.evaluate("2 1 + -"));
		/*
		 * h.push(1); h.push(2); h.push(3); h.push(4);
		 * Assert.assertEquals(4,h.pop()); Assert.assertEquals(3,h.peek());
		 * Assert.assertEquals(3,h.size()); Assert.assertEquals(3,h.pop());
		 * Assert.assertEquals(2,h.pop()); Assert.assertEquals(1,h.pop());
		 * Assert.assertEquals(true,h.isEmpty());
		 * //Assert.assertEquals(1,h.pop());
		 */
	}

}
