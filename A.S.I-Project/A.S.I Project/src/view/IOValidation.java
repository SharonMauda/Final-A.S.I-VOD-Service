package view;

import java.util.ArrayList;
import java.util.Scanner;

public class IOValidation {

	private Scanner scan;

	public String stringScan() {
		scan = new Scanner(System.in);
		String input = scan.nextLine();
		return input;
	}

	public int intScan() {
		scan = new Scanner(System.in);
		boolean wrongInputFlag = true;
		int input = 0;
		while (wrongInputFlag) {
			try {
				input = scan.nextInt();
				wrongInputFlag = false;
			} catch (Exception e) {
				System.out.println("Wrong input. Please enter numbers only.");
				scan.next();
			}
		}
		return input;
	}

	public int guaranteeRange(ArrayList<String> list) {
		int input = intScan();
		while (input < 0 || input > list.size()) {
			System.out.print("Incorrect input. Please try again: ");
			input = intScan();
		}
		return input;
	}

	public boolean guaranteeInput() {
		String input = stringScan();
		while (!input.equals("y") && !input.equals("Y") && !input.equals("n") && !input.equals("N")) {
			System.out.print("Incorrect input. Please try again: ");
			input = stringScan();
		}
		if (input.equals("n") || input.equals("N")) {
			return false;
		}
		return true;
	}

}
