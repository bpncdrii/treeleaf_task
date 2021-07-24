package com.bpn.assignment.question3;

public class Test {

	public static void main(String[] args) {

		permutations(3, 15);

		sameDigitSum(3, 15);

	}

	public static void permutations(int n, int sum) {
		permutations("", "123456789", n, sum);
	}

	private static void permutations(String c, String r, int n, int s) {
		if (c.length() == n) {
			checkSum(c, s);

		}

		for (int i = 0; i < r.length(); ++i) {
			permutations(c + r.charAt(i), r.substring(0, i) + r.substring(i + 1), n, s);
		}

	}

	private static void checkSum(String c, int s) {
		int sum = 0;
		for (int i = 0; i < c.length(); i++) {
			sum = sum + convertToInt(c.charAt(i) + "");
		}
		if (sum == s) {
			System.out.println(c);
		}
	}

	private static int convertToInt(String c) {
		return Integer.parseInt(c);
	}

	private static void sameDigitSum(int n, int sum) {

		for (int i = 1; i <= 9; i++) {
			String val = "";
			for (int j = 0; j < n; j++) {
				val = val + i;
			}

			checkSum(val, sum);
		}

	}

}
