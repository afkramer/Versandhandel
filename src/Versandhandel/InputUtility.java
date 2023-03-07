package Versandhandel;

import java.util.Scanner;

public class InputUtility {

	private static Scanner sc = new Scanner(System.in);

	private InputUtility() {}

	public static void waitingForPressingEnter() {
		System.out.println("Bitte drücken Sie die <Enter>-Taste");
		sc.nextLine();
	}

	public static String getUserChoice() {
		int logIn;
		String output;

		while (true) {
			System.out.println("Bitte wählen Sie einer der drei Möglichkeiten aus:");
			System.out.println("Drücken Sie die Taste 1, wenn Sie sich registrieren wollen.");
			System.out.println("Drücken Sie die Taste 2, wenn Sie sich einloggen wollen.");
			System.out.println("Drücken Sie die Taste 3, wenn Sie das Programm beenden wollen.");
			try {
				logIn = Integer.parseInt(sc.nextLine());
				if (logIn == 1) {
					output = "register";
					break;

				} else if (logIn == 2) {
					output = "login";
					break;

				} else if (logIn == 3) {
					output = "quit";
					break;

				} else {
					System.out.println("Falsche Eingabe. Bitte versuchen Sie es erneut.");
				}

			} catch (NumberFormatException nfe) {
				System.out.println("Bitte versuchen Sie es erneut.");
			}

		}
		return output;
	}

	public static String getFirstNameForRegistration() {
		System.out.println("Vorname:");
		return sc.nextLine();
	}

	public static String getSurnameForRegistration() {
		System.out.println("Nachname:");
		return sc.nextLine();
	}

	public static String getStreetForRegistration() {
		System.out.println("Straße:");
		return sc.nextLine();
	}

	public static String getHouseNumberForRegistration() {
		System.out.println("Hausnummer:");
		return sc.nextLine();
	}

	public static String getZipCodeForRegistration() {
		System.out.println("Postleitzahl:");
		return sc.nextLine();
	}

	public static String getPlaceForRegistration() {
		System.out.println("Stadt:");
		return sc.nextLine();
	}

	public static int getCustomerNumber() {
		System.out.println("Bitte geben Sie hier Ihre Kundennummer ein: ");
		return Integer.parseInt(sc.nextLine());
	}

	//TODO: Error handling -> what happens if user enters 4? "a"?
	public static int getUserChoiceForMenu() {
		System.out.println("Bitte wählen Sie eine der drei Möglichkeiten:");
		System.out.println("Drücken Sie die Taste 1, um sich auszuloggen.");
		System.out.println("Drücken Sie die Taste 2, um Ihre Angaben zu ändern.");
		System.out.println("Drücken Sie die Taste 3, wenn Sie weiter einkaufen wollen.");
		return Integer.parseInt(sc.nextLine());
	}

	/**
	 * Die Methode erhält die gewünschte Menge vom Kunden und gibt es zurück.
	 *
	 * @param sc Scanner zum einlesen der Kundeneingaben
	 * @return Gewünschte Menge, die der Kunde eingegeben hat.
	 */
	public static int getAmountOfProducts() {

		while (true) {

			try {
				System.out.println("Bitte geben Sie die gewünschte Menge ein");
				return Integer.parseInt(sc.nextLine()); // T

			} catch (NumberFormatException nfe) {
				System.out.println("Bitte versuchen Sie es erneut.");
			}
		}

	}

	public static int getUserData() {
		while (true) {
			System.out.println("Welche Daten möchten Sie ändern?");
			System.out.println("1) Vorname");
			System.out.println("2) Nachname");
			System.out.println("3) Straße");
			System.out.println("4) Hausnummer");
			System.out.println("5) Postleitzahl");
			System.out.println("6) Stadt");
			System.out.println("7) Zurück zum Hauptmenü");
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException nfe) {
				Gui.showInvalidInputErrorMessage(); 
			}
		}
	}

}
