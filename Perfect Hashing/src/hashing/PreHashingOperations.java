package hashing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PreHashingOperations {

	private int size;
	private File file;
	private int[] fileArray;

	public PreHashingOperations(String path) throws FileNotFoundException {
		setFile(path);
		setFileSize(file);
	}

	private void setFileSize(File file) {
		String line = new String();
		try (Scanner scan = new Scanner(file)) {
			while (scan.hasNext()) {
				line += scan.nextLine();
			}
		} catch (FileNotFoundException e) {

		}
		setFileArray(convertToStringArray(line));
	}

	private String[] convertToStringArray(String line) {
		return line.split(",");
	}

	private void setFileArray(String[] stringArray) {
		fileArray = new int[stringArray.length];
		try{
		for (int i = 0; i < stringArray.length; i++)
			fileArray[i] = Integer.parseInt(stringArray[i]);
		setSize();
		} catch (Exception x) {
			System.out.println("Error");
			System.exit(0);
		}
	}

	private void setSize() {
		this.size = fileArray.length;
	}

	private void setFile(String path) throws FileNotFoundException {
		this.file = new File(path);
	}

	public int[] getFileArray() {
		return this.fileArray;
	}

	public int getFileSize() {
		return this.size;
	}

	public File getFile() {
		return this.file;
	}

}
