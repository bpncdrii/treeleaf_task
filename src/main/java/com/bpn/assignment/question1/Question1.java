package com.bpn.assignment.question1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Question1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Enter any integer number: ");

		try {
			Scanner sc = new Scanner(System.in);
			int num = sc.nextInt();
			sc.close();

			ShowResult s = (n, o) -> {
				switch (o) {
				case 0:
					if (n % 2 == 1) {
						return true;

					} else {
						return false;
					}

				case 1:
					boolean result = true;
					int midPoint = n / 2;
					if (n != 0 || n != 1) {
						for (int i = 2; i <= midPoint; i++) {
							if (n % i == 0) {
								result = false;
								break;
							}
						}
					} else {
						result = false;
					}
					return result;

				case 2:
					
					int rem;
					int sum = 0;
					int hold;

					hold = n;

					while (n > 0) {
						rem = n % 10;
						sum = (sum * 10) + rem;
						n = n / 10;
					}

					if (hold == sum) {
						return true;
					} else {
						return false;
					}

				default:
					return false;

				}

			};
			System.out.println("The number you entered is: ");
			System.out.println("Odd: " + s.show(num, 0));
			System.out.println("Prime: " + s.show(num, 1));
			System.out.println("Palindrome: " + s.show(num, 2));

		} catch (InputMismatchException e) {
			System.out.println("Please enter integer number and with length less than 10 digit!!!");
		}

	}

}
