package eg.edu.alexu.csd.filestructure.avl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Dictionary implements IDictionary {
	
	AVLTree<String> dic = new AVLTree<>();
	int size = 0;

	@Override
	public void load(File file) {
		FileReader in;
		try {
			in = new FileReader(file);
			BufferedReader br = new BufferedReader(in);
			String line;
			while ((line = br.readLine()) != null) {
				insert(line);
			}
			in.close();
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException x) {
			x.printStackTrace();
		}
	}

	@Override
	public boolean insert(String word) {
		if (exists(word))
			return false;
		dic.insert(word);
		size++;
		return true;
	}

	@Override
	public boolean exists(String word) {
		if (size == 0)
			return false;
		return (dic.search(word));
	}

	@Override
	public boolean delete(String word) {
		if (exists(word)) {
			dic.delete(word);
			size--;
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int height() {
		return dic.height();
	}

}