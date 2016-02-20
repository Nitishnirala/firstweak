package com.app.demcons;

/**
 * constructor use to initialize the instance variable of class this purpose use
 * the constructor concept.
 */
public class First {
	/*
	 * instance variable
	 */
	int first_number;
	int second_number;

	First(int a, int b) {
		first_number = a;
		second_number = b;
	}

	public void print() {
		int sum = first_number + second_number;
		System.out.println("sum of=" + sum);
	}

	public static void main(String[] args) {

		First Obj = new First(60, 40);
		Obj.print();
	}
}
