package eg.edu.alexu.csd.datastructure.linkedList.cs51;

import java.awt.Point;

import eg.edu.alexu.csd.datastructure.linkedList.IPolynomialSolver;

public class poly implements IPolynomialSolver {
	SingleLinkedList A = new SingleLinkedList();
	SingleLinkedList B = new SingleLinkedList();
	SingleLinkedList C = new SingleLinkedList();
	SingleLinkedList R = new SingleLinkedList();

	public void setPolynomial(char poly, int[][] terms) {
		for (int k = 0; k < terms.length; k++) {
			if (terms[k][1] < 0) {
				throw new RuntimeException("error in index");
			}
		}
		for (int i = 0; i + 1 < terms.length; i++) {
			if (terms[i][1] < terms[i + 1][1])
				throw new RuntimeException("unorder");

		}
		SingleLinkedList f = new SingleLinkedList();
		f = check(poly);
		f.clear();
		for (int i = 0; i < terms.length; i++) {
			f.add(new Point(terms[i][0], terms[i][1]));
		}

	}

	public String print(char poly) {
		SingleLinkedList f = check(poly);
		if (f.size == 0) {
			return null;
		} else {
			String polynomial = "";
			for (int i = 0; i < f.size; i++) {
				Point help = (Point) f.get(i);
				if (help.y == 0) {
					polynomial += help.x;
				} else if (help.x == 1) {
					polynomial += "+" + "X^" + help.y;
				} else {
					polynomial += "+" + help.x + "*" + "X^" + help.y;
				}
			}
			polynomial = polynomial.substring(1);
			return polynomial;
		}

	}

	public void clearPolynomial(char poly) {
		SingleLinkedList f = check(poly);
		f.clear();
	}

	public float evaluatePolynomial(char poly, float value) {
		SingleLinkedList f = check(poly);
		float finall = 0;
		if (f.size == 0) {
			throw new RuntimeException("this unset");
		} else {
			for (int i = 0; i < f.size; i++) {
				Point help = (Point) f.get(i);
				finall += help.x * Math.pow(value, help.y);
			}

		}
		return finall;
	}

	public int[][] add(char poly1, char poly2) {
		unset(poly1);
		unset(poly2);
		R.clear();
		SingleLinkedList f = check(poly2);
		if (poly1 == poly2) {
			int arr[][] = new int[f.size][2];
			for (int i = 0; i < f.size; i++) {
				Point finall = (Point) f.get(i);
				arr[i][0] = 2 * finall.x;
				arr[i][1] = finall.y;
			}
			return arr;
		}
		for (int i = 0; i < f.size; i++) {
			Point help = (Point) f.get(i);
			f.set(i, new Point(-1 * help.x, help.y));
		}
		int s = subtract(poly1, poly2).length;
		int arr[][] = new int[s][2];
		arr = subtract(poly1, poly2);
		for (int i = 0; i < f.size; i++) {
			Point help = (Point) f.get(i);
			f.set(i, new Point(-1 * help.x, help.y));
		}
		return arr;
	}

	public int[][] subtract(char poly1, char poly2) {
		unset(poly1);
		unset(poly2);
		R.clear();
		SingleLinkedList first, second; // to know the object A or B or C
		int index1 = 0, index2 = 0; // index to get the nodes from the objects
		first = check(poly1);
		second = check(poly2);
		Point one; // point of the nodes in the first linked list
		Point two;// point of the nodes in the second linked list
		while (index1 < first.size && index2 < second.size) { // condition to
																// get the end
																// of one of the
																// two linked
																// list
			one = (Point) first.get(index1); // point in the nodes of the first
												// linked list
			two = (Point) second.get(index2); // point in the nodes of the
												// second linked list
			if (one.y == two.y) { // if the two exponent are equal then subtract
				if (one.x - two.x != 0) {
					R.add(new Point(one.x - two.x, one.y));
				}
				index1++;// so will increase the two index both
				index2++;
			} else if (one.y > two.y) { // if the first exponent > then take its
										// node
				R.add(new Point(one.x, one.y));
				index1++; // increase the first index because i took its node
			} else { // if the second exponent > then take its node
				R.add(new Point(-1 * two.x, two.y));// subtract is node
				index2++;// increase
			}
		}
		if (index1 == first.size && index2 < second.size) {
			for (int i = index2; i < second.size; i++) {
				two = (Point) second.get(index2);
				R.add(new Point(-1 * two.x, two.y));
			}
		} else if (index2 == second.size && index1 < first.size) {
			for (int i = index1; i < first.size; i++) {
				one = (Point) first.get(index1);
				R.add(new Point(one.x, one.y));
			}
		}
		int arr[][] = new int[R.size][2];
		for (int i = 0; i < R.size; i++) {
			Point finall = (Point) R.get(i);
			arr[i][0] = finall.x;
			arr[i][1] = finall.y;
		}
		if (R.size == 0) {
			int arrr[][] = { { 0, 0 } };
			return arrr;
		}
		return arr;
	}

	public int[][] multiply(char poly1, char poly2) {
		unset(poly1);
		unset(poly2);
		R.clear();
		int x = 0, y = 0;
		SingleLinkedList first, second;
		SingleLinkedList help = new SingleLinkedList();
		first = check(poly1);
		second = check(poly2);
		for (int i = 0; i < first.size; i++) {
			for (int j = 0; j < second.size; j++) {
				Point one = (Point) first.get(i);
				Point two = (Point) second.get(j);
				help.add(new Point(one.x * two.x, one.y + two.y));
			}
		}
		boolean mark[] = new boolean[help.size];
		for (int i = 0; i < help.size; i++) {
			if (mark[i] == false) {
				Point one = (Point) help.get(i);
				y = one.y;
				x = one.x;
				for (int j = i + 1; j < help.size; j++) {
					Point two = (Point) help.get(j);
					if (one.y == two.y) {
						x += two.x;
						mark[j] = true;
					}

				}
				R.add(new Point(x, y));
			}
		}
		int arr[][] = new int[R.size][2];
		for (int i = 0; i < R.size; i++) {
			Point finall = (Point) R.get(i);
			arr[i][0] = finall.x;
			arr[i][1] = finall.y;
		}
		return arr;
	}

	public SingleLinkedList check(char a) { // SingleLinkedList the name of the
											// class for my single linked list
		if (a == 'A') {
			return A;
		} else if (a == 'B') {
			return B;
		} else if (a == 'C') {
			return C;
		} else if (a == 'R') {
			return R;
		} else {
			throw new RuntimeException("unfound character");
		}
	}

	public void unset(char a) {
		SingleLinkedList f = check(a);
		if (f.size == 0) {
			throw new RuntimeException("this unset");
		}

	}
}
/*
 * public static void main (String []args){ int te [][]={{-4,4},{-5,3}}; int te2
 * [][]={{4,4},{5,3}}; Application obj =new Application();
 * obj.setPolynomial('A', te); obj.setPolynomial('B', te2); String k=
 * obj.print('A'); System.out.println(k); k= obj.print('B');
 * System.out.println(k); int f [][]=new int [1000][2]; f=obj.subtract('A',
 * 'A'); for (int i =0;i<1;i++){ System.out.print(f[i][0]); System.out.print(
 * "  "); System.out.println(f[i][1]);
 * 
 * } f =new int [1000][2]; f=obj.add('A', 'A'); for (int i =0;i<2;i++){
 * System.out.print(f[i][0]); System.out.print("  ");
 * System.out.println(f[i][1]);
 * 
 * }}} /* f =new int [1000][2]; f=obj.subtract('A', 'B'); for (int i
 * =0;i<3;i++){ System.out.print(f[i][0]); System.out.print("  ");
 * System.out.println(f[i][1]);
 * 
 * }
 * 
 * }
 */