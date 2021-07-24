package com.bpn.assignment.question3;

public class Question3 {
	public static void main(String[] args) {

		int n = 5;
		int sum = 35;
		permutations(n, sum);

		sameDigitSum(n, sum);

	}

	public static void permutations(int n, int sum) {
		permutations1("", "123456789", n, sum);

	}

	private static void permutations1(String c, String r, int n, int s) {
		try {
			if (c.length() == n) {
				checkSum(c, s);

			}

			for (int i = 0; i < r.length(); i++) {
				permutations1(c + r.charAt(i), r.substring(0, i) + r.substring(i + 1), n, s);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void checkSum(String c, int s) {
		try {
			int sum = 0;
			for (int i = 0; i < c.length(); i++) {
				sum = sum + convertToInt(c.charAt(i) + "");
			}
			if (sum == s) {
				System.out.println(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
