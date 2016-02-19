package demoKeywords;

import java.util.Scanner;

public class DemoFinally {

	private static Scanner sc;

	public static void main(String[] args) {

		sc = new Scanner(System.in);
		System.out.println("enter any two integer number");
		int a = sc.nextInt();
		int b = sc.nextInt();
		try {
			int div = a / b;
			System.out.println("value of div=" + div);
		} catch (Exception e) {
			e.printStackTrace();

		}
		/**
		 * finally is used to place important code weather exception is created
		 * or not for example database connection closing Note finally is block
		 * .
		 */
		finally {
			System.out.println("value af A=" + a);
		}
	}
}
