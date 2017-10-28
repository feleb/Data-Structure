package eg.edu.alexu.csd.datastructure.stack.cs51;

import eg.edu.alexu.csd.datastructure.stack.IExpressionEvaluator;

public class app implements IExpressionEvaluator {

	@Override
	public String infixToPostfix(String expression) {
		// throw new RuntimeException(expression);
		stack t = new stack();
		String x = expression;
		StringBuilder y = new StringBuilder();
		int n = 0, m = 0, z = 0;
		int p = 0, c = 0;
		/*
		 * for (int i=0;i<x.length();i++){ if (x.charAt(i)=='(')p++; else
		 * if(x.charAt(i)==')')c++; else continue; }
		 */
		// if (p!=c)throw new RuntimeException("wrong braces");

		for (int i = 0; i < x.length(); i++) {
			if (x.charAt(i) == ' ' || x.charAt(i) == ')' || x.charAt(i) == '(')
				continue;

			else if (x.charAt(i) != '+' && x.charAt(i) != '-' && x.charAt(i) != '/' && x.charAt(i) != '*') {

				while (i < x.length() && x.charAt(i) != ' ') {
					if (x.charAt(i) == '+' || x.charAt(i) == '-' || x.charAt(i) == '/' || x.charAt(i) == '*') {
						i--;
						break;
					} else if (i < x.length())
						z = z * 10 + (x.charAt(i) - '0');
					else
						throw new RuntimeException("out of index");
					i++;
				}
				z = 0;
				m++;
			} else
				n++;

		}
		if (n >= m)
			throw new RuntimeException("invalid");

		for (int i = 0; i < x.length(); i++) {
			if (x.charAt(i) == ' ')
				continue;
			else if (x.charAt(i) != '+' && x.charAt(i) != '-' && x.charAt(i) != '/' && x.charAt(i) != '*'
					&& x.charAt(i) != '(' && x.charAt(i) != ')') {
				while (i < x.length() && x.charAt(i) != ' ') {
					if (x.charAt(i) != ')' && x.charAt(i) != '+' && x.charAt(i) != '-' && x.charAt(i) != '/'
							&& x.charAt(i) != '*' && x.charAt(i) != '(') {
						y.append(Character.toString(x.charAt(i)));
						i++;
					} else {
						i--;
						break;
					}

				}
				y.append(" ");
			} else {
				switch (x.charAt(i)) {
				case '-':
				case '+':
					if (t.isEmpty() || (char) t.peek() == '(') {
						t.push(x.charAt(i));
					} else {
						while (!t.isEmpty() && (char) t.peek() != '(') {
							y.append(t.pop().toString());
							y.append(" ");
						}
						t.push(x.charAt(i));
					}
					break;
				case '*':
				case '/':
					if (t.isEmpty() || ((char) t.peek() != '*' && (char) t.peek() != '/')) {
						t.push(x.charAt(i));
					} else {
						while (!t.isEmpty() && ((char) t.peek() == '*' || (char) t.peek() == '/')) {
							y.append(t.pop().toString());
							y.append(" ");
						}
						t.push(x.charAt(i));
					}
					break;
				case '(': {
					p++;
					t.push(x.charAt(i));
					break;
				}
				case ')':
					while ((char) t.peek() != '(') {
						y.append(t.pop().toString());
						y.append(" ");
					}
					t.pop();
					c++;
					break;
				default:
					throw new RuntimeException("error index");
				}
			}
		}
		while (!t.isEmpty()) {
			y.append(t.pop().toString());
			y.append(" ");
		}
		if (p != c)
			throw new RuntimeException("wrong braces");
		y.toString();
		y.deleteCharAt(y.length() - 1);
		return y.toString();
	}

	@Override
	public int evaluate(String expression) {
		// throw new RuntimeException(expression);
		int flag = 0;
		stack h = new stack();
		String v = expression;
		/*
		 * int n=0,m=0; for (int i=0;i<v.length();i++){ if(v.charAt(i)=='
		 * ')continue; else if (v.charAt(i)=='+' ||v.charAt(i)=='-'
		 * ||v.charAt(i)=='/' ||v.charAt(i)=='*'){n++;} else {m++;} } if (m!=
		 * n+1)throw new RuntimeException(" invalid");
		 */
		if (v == null)
			throw new RuntimeException("expression empty");
		int x;
		double z = 0;
		/*
		 * for(int i=0;i<v.length();i++){ if
		 * (v.charAt(i)>='a'&&v.charAt(i)<='z')throw new
		 * RuntimeException("symbolic"); }
		 */
		for (int i = 0; i < v.length(); i++) {
			if (v.charAt(i) == ' ')
				continue;
			else if (v.charAt(i) != '+' && v.charAt(i) != '-' && v.charAt(i) != '/' && v.charAt(i) != '*') {
				// if (v.charAt(i+1)==' '){ h.push(v.charAt(i)-'0');}

				while (v.charAt(i) != ' ') {
					if (v.charAt(i) == '+' || v.charAt(i) == '-' || v.charAt(i) == '/' || v.charAt(i) == '*') {
						i--;
						break;
					} else if (i < v.length() && v.charAt(i) <= '9' && v.charAt(i) >= '0')
						z = z * 10 + (v.charAt(i) - '0');
					else
						throw new RuntimeException("out of index");
					i++;
				}
				h.push(z);
				z = 0;
			}

			else {
				if (h.size() < 2)
					flag = 1;
				else {
					double y;
					double a = (double) h.pop();
					double b = (double) h.pop();
					switch (v.charAt(i)) {
					case '+': {
						y = (a + b);
						h.push(y);
						break;
					}
					case '-': {
						y = (b - a);
						h.push(y);
						break;
					}
					case '*': {
						y = (a * b);
						h.push(y);
						break;
					}
					case '/': {
						y = (b / a);
						h.push(y);
						break;
					}
					default:
						throw new RuntimeException("out of index");
					}
				}
			}
		}
		if (flag == 1)
			x = 0;
		else {
			double r = (double) h.pop();
			x = (int) (r);
		}
		return x;
	}

	public static void main(String[] args) {
		String x = "2 + (3 * 5)";
		app p = new app();
		String y = p.infixToPostfix(x);
		int r = p.evaluate(y);
		System.out.println(r);
	}
}
