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

	public static String getFirstNameInput() {
		System.out.println("Vorname:");
		return sc.nextLine();
	}

	public static String getSurnameInput() {
		System.out.println("Nachname:");
		return sc.nextLine();
	}

	public static String getStreetInput() {
		System.out.println("Straße:");
		return sc.nextLine();
	}

	public static String getHouseNumberInput() {
		System.out.println("Hausnummer:");
		return sc.nextLine();
	}

	public static String getZipCodeInput() {
		System.out.println("Postleitzahl:");
		return sc.nextLine();
	}

	public static String getCityInput() {
		System.out.println("Stadt:");
		return sc.nextLine();
	}
	
	public static String getPasswordInput() {
		System.out.println("Password:");
		return sc.nextLine();
	}

	public static int getCustomerNumber() {
		System.out.println("Bitte geben Sie hier Ihre Kundennummer ein: ");
		return Integer.parseInt(sc.nextLine());
	}

	public static int getUserChoiceForMenu(User user) {
		while (true) {
			System.out.println("Bitte wählen Sie eine der drei Möglichkeiten:\n");
			System.out.println("Drücken Sie die Taste 1, um sich auszuloggen.");
			System.out.println("Drücken Sie die Taste 2, um Ihre Angaben zu ändern.");
			if (user.getUserType().equals(UserType.ADMINISTRATOR)) {
				System.out.println("Drücken Sie die Taste 3, wenn Sie einen Kunden löschen möchten.");
				System.out.println("Drücken Sie die Taste 4, wenn Sie Produktdaten anpassen möchten.");
			} else {
				System.out.println("Drücken Sie die Taste 3, wenn Sie ein Auto kaufen möchten.");
			}
			
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException nfe) {
				Gui.showInvalidInputErrorMessage();
			}
		}
	}
	
	public static Car getDesiredProduct(Car[] cars) {
		int productNumber;
		Car car;
		
		while (true) {
			System.out.println("Welches Auto würden Sie gerne kaufen?");
			System.out.println("Bitte geben Sie die gewünschte Produktnummer ein:");
			try {
				productNumber = Integer.parseInt(sc.nextLine());
				car = CarManagement.findCarByProductNumber(productNumber, cars);
				if (car == null) {
					Gui.showProductDoesNotExist();
				} else {
					return car;
				}
			} catch (NumberFormatException nfe) {
				Gui.showInvalidInputErrorMessage();
			}
		}
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
