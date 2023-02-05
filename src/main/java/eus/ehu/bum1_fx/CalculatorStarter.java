package eus.ehu.bum1_fx;

import eus.ehu.bum1_fx.business_logic.*;

import java.util.InputMismatchException;
import java.util.Locale;
import java.util.Scanner;

public class CalculatorStarter {

	private static ExchangeCalculator bizLogic;

	public static void printValidCurrencies() {
		System.out.println("Valid currencies with their codes are listed below.");
		int i = 1;
		for (String name : bizLogic.getCurrencyLongNames()) {
			if (i%4 == 0)
				System.out.println();
			System.out.printf("%-30s", name);
			i++;
		}
		System.out.println();
	}

	public static void main(String[] args) {
		bizLogic = new BarcenaysCalculator();
		Scanner input = new Scanner(System.in);
		input.useLocale(Locale.ENGLISH);

		System.out.println("\nWelcome to BARCENAYS CAPITAL. This is our "
				+ "Currency Exchange Calculator Service.");
		System.out.println("We will offer you the best exchange rates "
				+ "at a very moderate commission fee.");

		String origCurrency = "";
		double origAmount = 0.0;
		String endCurrency = "";

		printValidCurrencies();
		System.out.println("\nPlease indicate the currency that you intend to exchange "
				+ "(international 3 letter code):");
		try{
			origCurrency = input.next();
			bizLogic.ValueOf(origCurrency);
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}

		boolean waiting = true;
		while (waiting) {
			try {
				System.out.printf("How many %s do you plan to exchange?:%n", origCurrency);
				origAmount = input.nextDouble();
				waiting = false;
			} catch (InputMismatchException e) {
				System.out.print("Please introduce a valid amount. ");
				input.nextLine();    // to clear the buffer
			}
		}

		System.out.printf("Please indicate the currency to which you want to exchange "
				+ "your %s %.2f (international 3 letter code):\n", origCurrency, origAmount);
		try{
			endCurrency = input.next();
			bizLogic.ValueOf(endCurrency);
		} catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
		try {
			double endAmount = bizLogic.getChangeValue(origCurrency, origAmount, endCurrency);
			endAmount -= bizLogic.calculateCommission(endAmount, endCurrency);
			System.out.printf("\nYou can obtain a net exchange value of %s %.2f.%n", endCurrency, endAmount);
			System.out.println("You can make it effective at any BARCENAYS CAPITAL office.");
		} catch (Exception e1) {
			System.out.println("\nExcuse us, the conversion could not be done. Please try it a bit later.");
		}

		input.close();
	}
}
