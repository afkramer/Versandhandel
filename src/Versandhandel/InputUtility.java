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

	
	
	public static String getCarNameInput() {
		System.out.println("Auto Name:");
		return sc.nextLine();
	}
	
	//TODO: error handling!
	public static double getCarPriceInput() {
		System.out.println("Auto Preis:");
		return Double.parseDouble(sc.nextLine());
	}
	
	public static String getCarDescriptionInput() {
		System.out.println("Auto Beschreibung:");
		return sc.nextLine();
	}
	
	
	//TODO: error handling
	public static int getUserIdInput() {
		System.out.println("Bitte geben Sie hier Ihre User-ID ein: ");
		return Integer.parseInt(sc.nextLine());
	}

	public static int getUserChoiceForMenu(User user) {
		while (true) {
			System.out.println("Bitte wählen Sie eine der vier Möglichkeiten:\n");
			System.out.println("Drücken Sie die Taste 1, um sich auszuloggen.");
			System.out.println("Drücken Sie die Taste 2, um Ihre Angaben zu ändern.");
			if (user.getUserType().equals(UserType.ADMINISTRATOR)) {
				System.out.println("Drücken Sie die Taste 3, wenn Sie einen User löschen möchten.");
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
	
	public static Car getDesiredProduct(Car[] cars, String action) {
		int productNumber;
		Car car;
		
		while (true) {
			System.out.println("Welches Fahrzeug würden Sie gerne " + action + "?");
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
	public static int getNumberOfProducts() {

		while (true) {

			try {
				System.out.println("Bitte geben Sie die gewünschte Menge ein");
				return Integer.parseInt(sc.nextLine()); // T

			} catch (NumberFormatException nfe) {
				System.out.println("Bitte versuchen Sie es erneut.");
			}
		}

	}

	//TODO: put this closer to the methods that have to do with updating the customer
	//TODO: change method definition to include UserType parameter
	//TODO: based on user type, also allow the user to change password (if Admin)
	//TODO: check where else we need to allow the user to select choice 8 (change password)
	public static int getUserFieldToChange(User user) {
		while (true) {
			System.out.println("Welche Kundendaten möchten Sie ändern?\n");
			System.out.println("0) Zurück zum Hauptmenü\n");
			
			System.out.println("1) Vorname");
			System.out.println("2) Nachname");
			System.out.println("3) Straße");
			System.out.println("4) Hausnummer");
			System.out.println("5) Postleitzahl");
			System.out.println("6) Stadt");
			
			if (user.getUserType().equals(UserType.ADMINISTRATOR)) {
				System.out.println("7) Passwort");
			}
			
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException nfe) {
				Gui.showInvalidInputErrorMessage(); 
			}
		}
	}
	
	public static int getCarFieldToChange() {
		while(true) {
			System.out.println("Welche Fahrzeugdaten möchten Sie ändern?\n");
			System.out.println("0) Zurück zum Hauptmenü");
			
			System.out.println("1) Fahrzeugname");
			System.out.println("2) Fahrzeugpreis");
			System.out.println("3) Fahrzeug Beschreibung");
			try {
				return Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException nfe) {
				Gui.showInvalidInputErrorMessage();
			}
		}
	}

}
