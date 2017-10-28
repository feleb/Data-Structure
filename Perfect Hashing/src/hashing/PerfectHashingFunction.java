package hashing;

import java.util.Random;

public class PerfectHashingFunction {
	private int a;
	private int b;
	private int size;
	private int p = 2147483629;

	public PerfectHashingFunction(int size) {
		setSize(size);
		universalHashing();
	}

	public void setSize(int size) {
		this.size = size;
	}

	private void universalHashing() {
		Random rand = new Random();
		a = rand.nextInt(p - 1) + 1;
		b = rand.nextInt(p - 1);
	}

	public int hashFunction(int k) {
		int x = Math.abs((((a * k + b) % p) % size));
		return x;
	}
}