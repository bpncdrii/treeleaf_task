package com.bpn.assignment.question2;

public class Question2 {

	public static void main(String[] args) {
		int num[] = { 1, 9, 6, 4, 5 };
		System.out.println("Total number of inverions: " + findInversion(num));
	}

	private static int findInversion(int[] num) {
		int count = 0;
		int size = num.length;
		System.out.println("Invirsion pair: ");
		for (int i = 0; i < size - 1; i++) {
			for (int j = i + 1; j < size; j++) {

				if (num[i] > num[j]) {
					count++;
					System.out.println(count + ". (" + num[i] + "," + num[j] + ")");

				}
			}
		}

		return count;
	}

}
