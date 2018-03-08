package javastory.util;

import java.util.Scanner;

public class ConsoleUtil {
	//
	private Scanner scanner;
	private Narrator narrator;
	
	public ConsoleUtil() {
		scanner = new Scanner(System.in);
	}
	
	public ConsoleUtil(Object target) {
		//
		this();
		this.narrator = new Narrator(target.getClass().getSimpleName(), TalkingAt.Left);
	}
	
	public ConsoleUtil(Narrator narrator) {
		//
		this.scanner = new Scanner(System.in);
		this.narrator = narrator;
	}
	
	public String getValueOf(String label) {
		//
		System.out.print(label + ": ");
		
		String inputStr = scanner.nextLine();
		inputStr = inputStr.trim();
		return inputStr;
	}
	
	public int getIntegerOf(String label) {
		//
		System.out.print(label + ": ");
		
		int inputInt = scanner.nextInt();
		scanner.nextLine();
		return inputInt;
	}
	
	public double getDoubleOf(String label) {
		//
		System.out.print(label + ": ");
		
		double inputDouble = scanner.nextDouble();
		scanner.nextLine();
		return inputDouble;
	}
}
