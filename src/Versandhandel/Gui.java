package Versandhandel;

public final class Gui {

	private static final String BLUE_TEXT = "\u001B[34m";
	private static final String ANSCI_RESET = "\u001B[0m";

	private Gui() {
	}

	/**
	 * Die Methode heißt den Kunden willkommen.
	 *
	 */
	public static void showWelcomeScreen() {
		System.out.println(BLUE_TEXT + "\n\n\n***************************************");
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.println("**********Herzlich Willkommen**********");
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.println("***************************************\n\n\n" + ANSCI_RESET);

		InputUtility.waitingForPressingEnter();
	}

	/**
	 * Die Methode fordert den Kunden auf seine Kundennummer einzugeben, diese wird
	 * im Anschluss ausgegeben.
	 *
	 * @return Ein Customer object.
	 */
	public static Customer login(Customer[] customerArray) {

		Customer customer;

		while (true) {

			try {
				int kundennummer = InputUtility.getCustomerNumber();
				if (CustomerManagement.isCustomerNumberValid(kundennummer, customerArray)) {
					customer = CustomerManagement.returnCustomer(kundennummer, customerArray);
					System.out.println("Herzlich Willkommen " + customer.getFirstName() + "");
					break;

				} else {
					System.out.println("Sie haben die falsche Kundennummer eingegegen. Bitte versuchen Sie es erneut.");
				}

			} catch (NumberFormatException nfe) {
				System.out.println("Bitte versuchen Sie es erneut.");
			}

		}

		InputUtility.waitingForPressingEnter();

		return customer;

	}
	
	public static void showRegistrationResults(Customer customer) {
		System.out.println("Vielen Dank für Ihre Registrierung " + customer.getFirstName());
		System.out.println("Deine neue Kundennummer lautet " + customer.getUserId());
	}

	public static Customer[] showMenu(Customer customer, Customer[] customers, Car[] carArray) {
		int menge;

		while (true) {

			try {
				int choice = InputUtility.getUserChoiceForMenu();
				if (choice == 1) {
					System.out.println("Sie haben sich erfolgreich ausgelogt");
					break;

				} else if (choice == 2) {
					CustomerManagement.changeData(customer);
					Utility.writeCustomersToFile(customers);

				} else if (choice == 3) {
					showProduct(carArray);
					Car car = InputUtility.getDesiredProduct(carArray);
					menge = InputUtility.getAmountOfProducts();
					showInvoice(customer, car, menge);
					
				} else if (choice == 4){
					//TODO: delete customer, only if the user is an administrator
				} else {
					System.out.println("Falsche Eingabe. Bitte versuchen Sie es erneut.");
				}

			} catch (NumberFormatException nfe) {
				System.out.println("Bitte versuchen Sie es erneut.");
			}
		}

		return customers;
	}

	public static void showProduct(Car[] carArray) {
		System.out.println("\n\n\nDiese Autos stehen zur Verfügung:");
		for (int i = 0; i < carArray.length; i++) {
			showProduct(carArray[i]);
		}
		InputUtility.waitingForPressingEnter();
	}

	/**
	 * Die Methode zeigt eine Produktübersicht mit allen Merkmalen zu den Produkten.
	 *
	 * @param sc                  Scanner zum einlesen der Kundeneingaben
	 * @param produktnummer       Nummer zur Identifikation des Produkts
	 * @param produktname         Name zur Idenfikation des Produkts
	 * @param produktpreis        Der festgelegte Preis pro Stück
	 * @param produktbeschreibung Beschreibung des Produkts
	 */
	public static void showProduct(Car car) {
		System.out.println(car);
		System.out.println();
	}

	/**
	 * Die Methode verabschiedet den Kunden.
	 *
	 * @param kundennummer Die dem Kunden zugeteilte Nummer.
	 */
	public static void verabschiedung(Customer customer) {

		System.out.println("\n\n\nVielen Dank für Ihren Besuch " + customer.getFirstName() + "!\n");
	}

	public static void showInvoice(Customer customer, Car car, int quantity) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%n%n%s %s%n", customer.getFirstName(), customer.getSurname()));
		sb.append(String.format("%s %s%n", customer.getStreet(), customer.getHouseNumber()));
		sb.append(String.format("%s %s %n%n%n", customer.getZipCode(), customer.getCity()));
		sb.append(String.format("Danke %s für Ihren Einkauf!%n", customer.getFirstName()));
		sb.append("Hiermit stellen wir Ihnen die folgenden Produkte in Rechnung:\n");
		sb.append(String.format((new String(new char[100])).replace("\0", "_")));
		sb.append("\n");
		sb.append(String.format("%-7s", "Pos."));
		sb.append(String.format("%-20s", "Beschreibung"));
		sb.append(String.format("%-9s", "Menge"));
		sb.append(String.format("%-12s", "Preis"));
		sb.append(String.format("%-13s%n", "Gesamtpreis"));
		sb.append(String.format("%03d", 1));
		sb.append(String.format("%4s", ""));
		sb.append(String.format("%-20s", car.getProductName()));
		sb.append(String.format("%-9d", quantity));
		sb.append(String.format("%-,9.2f€", car.getProductPrice()));
		sb.append(String.format("%,12.2f€%n%n", CarManagement.totalPrice(quantity, car)));
		sb.append(String.format("%s", car.getProductDescription()));
		sb.append("\n");
		sb.append(String.format((new String(new char[100])).replace("\0", "_")));
		sb.append("\n");
		sb.append(String.format("%26s", ""));
		sb.append(String.format("%20s", "Zwischensumme Netto:"));
		sb.append(String.format("%,12.2f€", CarManagement.totalPrice(quantity, car)
				- CarManagement.mehrwertsteuer(CarManagement.totalPrice(quantity, car), CarManagement.TAX)));
		sb.append("\n");
		sb.append(String.format("%26s", ""));
		sb.append(String.format("%20s", "Mehrwertsteuer:"));
		sb.append(String.format("%,12.2f€", CarManagement.mehrwertsteuer(CarManagement.totalPrice(quantity, car), CarManagement.TAX)));
		sb.append("\n");
		sb.append(String.format("%26s", ""));
		sb.append(String.format((new String(new char[35])).replace("\0", "_")));
		sb.append("\n");
		sb.append(String.format("%26s", ""));
		sb.append(String.format("%20s", "Gesamtbetrag:"));
		sb.append(String.format("%,12.2f€", CarManagement.totalPrice(quantity, car)));
		sb.append("\n\n\n");
		System.out.print(sb);
	}
	
	public static void showProductDoesNotExist() {
		System.out.println("Diese Produktnummer existiert nicht. Bitte versuchen Sie es erneut.");
	}

	public static void showDeleteErrorMessage() {
		System.out.println("Der Kunde konnte nicht gelöscht werden. Bitte kontrollieren Sie Ihre Daten noch einmal."); 
	}
	
	public static void showReadErrorMessage() {
		System.out.println("Die Daten konnten nicht gelesen werden. Es werden die Backup Daten verwendet.");
	}
	public static void showWriteErrorMessage() {
		System.out.println("Die Daten konnten nicht gespeichert werden. Bitte kontrollieren Sie Ihre Daten.");
	}
	public static void showInvalidInputErrorMessage () {
		System.out.println("Ihre Eingabe ist fehlerhaft. Bitte versuchen Sie es erneut."); 
	}
}
