package hashing;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class PerfectHashTable {
	private Object[] levelOneArray;
	private boolean[] levelOneCollided;
	private PerfectHashingFunction levelOneHashingFunction;
	private int[] dataArray;
	private int size;

	public PerfectHashTable(int[] array) {
		initiallizingHashTable(array);
		normalHashing();
	}

	public PerfectHashTable(String filePath) {
		try {
			initiallizingHashTable(filePath);
		} catch (FileNotFoundException e) {

		}
		normalHashing();
	}

	private void addArrayLists() {
		for (int i = 0; i < this.levelOneArray.length; i++)
			levelOneArray[i] = new ArrayList<Integer>();
	}

	private PerfectHashingFunction getHachingFunction(int size) {
		return new PerfectHashingFunction(size);
	}

	private void initiallizingHashTable(int[] array) {
		this.size = array.length;
		this.dataArray = array;
		newChance(size);
	}

	private void initiallizingHashTable(String path) throws FileNotFoundException {
		PreHashingOperations preHashing = new PreHashingOperations(path);
		this.size = preHashing.getFileSize();
		this.dataArray = preHashing.getFileArray();
		newChance(size);
	}

	private void createLevelOneHashingFunction(int size) {
		levelOneHashingFunction = getHachingFunction(size);
	}

	private void createLevelOneArray(int size) {
		levelOneArray = new Object[size];
	}

	private void createLevelOneCollided(int size) {
		levelOneCollided = new boolean[size];
		Arrays.fill(levelOneCollided, false);
	}

	private void newChance(int sizeSquared) {
		createLevelOneArray(sizeSquared);
		createLevelOneCollided(sizeSquared);
		createLevelOneHashingFunction(sizeSquared);
		addArrayLists();
	}

	@SuppressWarnings("unchecked")
	private void normalHashing() {
		int value, hashedValue;
		ArrayList<Integer> carry = new ArrayList<Integer>();
		for (int i = 0; i < this.dataArray.length; i++) {
			value = dataArray[i];
			hashedValue = this.levelOneHashingFunction.hashFunction(value);
			carry = ((ArrayList<Integer>) this.levelOneArray[hashedValue]);
			if (!carry.contains(value))
				carry.add(value);
		}
		doublehashing();

	}

	private int[] transformToArray(ArrayList<Integer> arrayList) {
		int[] transformed = new int[arrayList.size()];
		for (int i = 0; i < arrayList.size(); i++)
			transformed[i] = arrayList.get(i);
		return transformed;
	}

	@SuppressWarnings("unchecked")
	private void doublehashing() {
		ArrayList<Integer> arrayList;
		for (int i = 0; i < this.levelOneArray.length; i++) {
			arrayList = ((ArrayList<Integer>) this.levelOneArray[i]);
			if (arrayList.size() == 1) {
				this.levelOneArray[i] = arrayList.get(0);
			} else if (arrayList.size() == 0) {
				this.levelOneArray[i] = null;
			} else {
				this.levelOneArray[i] = new UniversalHashTable(transformToArray(arrayList));
			}
		}
	}

	public boolean isThere(int key) {
		int carry, hashedValue;
		hashedValue = this.levelOneHashingFunction.hashFunction(key);
		if (this.levelOneArray[hashedValue] != null) {
			if (this.levelOneArray[hashedValue] instanceof Integer) {
				carry = (int) levelOneArray[hashedValue];
				if (carry == key) {
					return true;
				}
			} else {
				return ((UniversalHashTable) this.levelOneArray[hashedValue]).isThere(key);
			}
		}

		return false;

	}

	public void print() {
		for (int i = 0; i < this.levelOneArray.length; i++) {
			if (this.levelOneArray[i] != null)
				if (this.levelOneArray[i] instanceof Integer) {
					System.out.println(this.levelOneArray[i]);
				} else {
					((UniversalHashTable) this.levelOneArray[i]).print();
					System.out.println();
				}
		}

	}

}
