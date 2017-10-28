package testing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import hashing.PerfectHashTable;
import hashing.UniversalHashTable;

public class TestingHashing {

	public static void main(String[] args) {
		double time1 = System.currentTimeMillis();
		PerfectHashTable p = new PerfectHashTable("/Users/FNSY/Downloads/testCases_lab4/keys4.txt");
		double time2 = System.currentTimeMillis();
		System.out.println(p.isThere(19));
		System.out.println(p.isThere(22));
		System.out.println(p.isThere(46));
		System.out.println(p.isThere(22));
		System.out.println(p.isThere(41));
		System.out.println(p.isThere(12));
		System.out.println(p.isThere(123));
		System.out.println(p.isThere(292));
		System.out.println(p.isThere(96));
		System.out.println(p.isThere(5));
		System.out.println("time for 1,000,000 entry: " + (time2 - time1) + " milliseconds");
	}

}

// int[] arr = { 1, 2, 465, 43, -32, 8768, -1221, -12 };
// PerfectHashTable u = new
// PerfectHashTable("/Users/FNSY/Downloads/testCases_lab4/keys4.txt");