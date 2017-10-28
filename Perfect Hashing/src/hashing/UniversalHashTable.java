package hashing;

import java.io.*;
import java.util.Arrays;

public class UniversalHashTable {
	private int[] hashArray;
	private boolean[] hashCollided;
	private PerfectHashingFunction hashingFunction;
	private int sizeSquared;
	private int rebuildsNumber;
	private int[] dataArray;

	public UniversalHashTable(int[] array) {
		initiallizingHashTable(array);
		rebuildsNumber = 0;
		createHashTable();
	}

	public UniversalHashTable(String filePath) {
		try {
			initiallizingHashTable(filePath);
		} catch (FileNotFoundException e) {

		}
		rebuildsNumber = 0;
		createHashTable();
	}

	private PerfectHashingFunction getHachingFunction(int sizeSquared) {
		return new PerfectHashingFunction(sizeSquared);
	}

	private void initiallizingHashTable(int[] array) {
		this.sizeSquared = (int) Math.pow(array.length, 2);
		this.dataArray = array;
		newChance(sizeSquared);
	}

	private void initiallizingHashTable(String path) throws FileNotFoundException {
		PreHashingOperations preHashing = new PreHashingOperations(path);
		this.sizeSquared = (int) Math.pow(preHashing.getFileSize(), 2);
		this.dataArray = preHashing.getFileArray();
		newChance(sizeSquared);
	}

	private void createHashingFunction(int sizeSquared) {
		hashingFunction = getHachingFunction(sizeSquared);
	}

	private void createHashArray(int sizeSquared) {
		hashArray = new int[sizeSquared];
	}

	private void createHashCollided(int sizeSquared) {
		hashCollided = new boolean[sizeSquared];
		Arrays.fill(hashCollided, false);
	}

	private void newChance(int sizeSquared) {
		createHashArray(sizeSquared);
		createHashCollided(sizeSquared);
		createHashingFunction(sizeSquared);
	}

	private void createHashTable() {
		int value, hashedValue;
		newChance(sizeSquared);
		for (int i = 0; i < this.dataArray.length; i++) {
			value = dataArray[i];
			hashedValue = this.hashingFunction.hashFunction(value);
			try {
				if (!hashCollided[hashedValue]) {
					hashArray[hashedValue] = value;
					hashCollided[hashedValue] = true;
				} else if (hashCollided[hashedValue] && hashArray[hashedValue] != value) {
					++rebuildsNumber;
					createHashTable();
					break;
				}
			} catch (Throwable x) {
				++rebuildsNumber;
				createHashTable();
				break;
			}
		}

	}

	public int getRebuildsNumber() {
		return this.rebuildsNumber;
	}

	public void print() {
		for (int i = 0; i < this.hashArray.length; i++)
			if (this.hashArray[i] != 0)
				System.out.print("\t" + this.hashArray[i]);
	}

	public boolean isThere(int key) {
		int hashedValue = this.hashingFunction.hashFunction(key);
		if (hashCollided[hashedValue] && this.hashArray[hashedValue] == key)
			return true;
		return false;
	}

}
