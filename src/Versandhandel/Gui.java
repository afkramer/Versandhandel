package Versandhandel;

//TODO: make the output look nicer!
//TODO: wollen wir wirklich immer, dass der Kunde Enter drucken muss?

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
	public static User login(User[] users) {

		User user;

		while (true) {

			try {
				int kundennummer = InputUtility.getUserIdInput();
				if (UserManagement.isUserNumberValid(kundennummer, users)) {
					user = UserManagement.returnUserByUserId(kundennummer, users);
					if (user instanceof Customer) {
						greetUser(user);
					} else if (user instanceof Administrator){
						if (isPasswordValid((Administrator) user)) {
							greetUser(user);
						} else {
							user = null;
						}
					} else {
						showGeneralErrorMessage();
					}
					
					break;

				} else {
					System.out.println("Sie haben die falsche Kundennummer eingegegen. Bitte versuchen Sie es erneut.");
				}

			} catch (NumberFormatException nfe) {
				System.out.println("Bitte versuchen Sie es erneut.");
			}

		}

		InputUtility.waitingForPressingEnter();

		return user;

	}
	
	public static void greetUser(User user) {
		if (user instanceof Customer) {
			System.out.println("Herzlich Willkommen " + user.getFirstName());
		} else {
			System.out.println("Hallo Administrator " + user.getFirstName());
		}
	}
	
	public static boolean isPasswordValid(Administrator admin) {
		String password;
		for(int i = 0; i < 3; i++) {
			System.out.println("Bitte geben Sie Ihr Passwort ein:");
			password = InputUtility.getPasswordInput();
			if (password.equals(admin.getPassword())) {
				return true;
			} else {
				System.out.println("Das ist nicht richtig. Verbleibende Versuche " + (2 - i) + ".");
			}
		}
		System.out.println("Sie haben zu viele Fehlversuche gehabt.");
		return false;
	}
	
	public static void showRegistrationResults(Customer customer) {
		System.out.println("Vielen Dank für Ihre Registrierung " + customer.getFirstName());
		System.out.println("Deine neue Kundennummer lautet " + customer.getUserId());
	}

	public static User[] showMenu(User user, User[] users, Car[] cars) {
		int menge;
		UserType userType = user.getUserType();

		while (true) {

			try {
				int choice = InputUtility.getUserChoiceForMenu(user);
				if (choice == 1) {
					System.out.println("Sie haben sich erfolgreich ausgelogt");
					break;

				} else if (choice == 2) {
					UserManagement.changeData(user);
					Utility.writeUsersToFile(users);

				} else if (choice == 3) {
					if (userType.equals(UserType.ADMINISTRATOR)) {
						users = deleteUser(users);
						Utility.writeUsersToFile(users);
					} else {
						//TODO if time: this should be in a separate method
						// for example Utility.sellProduct();
						showProduct(cars);
						Car car = InputUtility.getDesiredProduct(cars, "kaufen");
						menge = InputUtility.getNumberOfProducts();
						Invoice invoice = new Invoice( (Customer) user, car, menge);
						showInvoice((Customer) user, car, invoice);
					}
					
				} else if (choice == 4){
					if (userType.equals(UserType.ADMINISTRATOR)) {
						showProduct(cars);
						Car car = InputUtility.getDesiredProduct(cars, "ändern");
						CarManagement.changeData(car);
						Utility.writeCarsToFile(cars);
					} else {
						showInvalidInputErrorMessage();
					}
					
				} else {
					showInvalidInputErrorMessage ();
				}

			} catch (NumberFormatException nfe) {
				showInvalidInputErrorMessage ();
			}
		}

		return users;
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
	
	public static User[] deleteUser(User[] users) {
		System.out.println("Welchen Kunden möchten Sie löschen?\n");
		int userId = InputUtility.getUserIdInput();
		String firstName = InputUtility.getFirstNameInput();
		String lastName = InputUtility.getSurnameInput();
		return UserManagement.deleteUser(firstName, lastName, userId, users);
	}

	/**
	 * Die Methode verabschiedet den Kunden.
	 *
	 * @param kundennummer Die dem Kunden zugeteilte Nummer.
	 */
	public static void verabschiedung(User user) {
		if (user == null) {
			System.out.println("\n\n\nVielen Dank für Ihren Besuch.");
		} else {
			System.out.println("\n\n\nVielen Dank für Ihren Besuch " + user.getFirstName() + "!\n");
		}
	}

	public static void showInvoice(Customer customer, Car car, Invoice invoice) {
		boolean customerIsPremium = customer.getUserType().equals(UserType.PREMIUM_CUSTOMER);
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("%n%n%s %s%n", customer.getFirstName(), customer.getSurname()));
		sb.append(String.format("%s %s%n", customer.getStreet(), customer.getHouseNumber()));
		sb.append(String.format("%s %s %n%n", customer.getZipCode(), customer.getCity()));
		sb.append(String.format("Danke %s für Ihren Einkauf!%n%n", customer.getFirstName()));
		
		if (customerIsPremium) {
			sb.append(String.format("Sie sind ein Premium Kunde! Vielen Dank für Ihre Treue.%n%n", customer.getUserType().germanText));
		}
		
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
		sb.append(String.format("%-9d", invoice.getQuantity()));
		sb.append(String.format("%-,9.2f€", car.getProductPrice()));
		sb.append(String.format("%,12.2f€%n%n", invoice.getTotalPrice()));
		sb.append(String.format("%s%n", car.getProductDescription()));
		sb.append("\n");
		sb.append(String.format((new String(new char[100])).replace("\0", "_")));
		sb.append("\n");
		sb.append(String.format("%26s", ""));
		sb.append(String.format("%20s", "Zwischensumme Netto:"));
		sb.append(String.format("%,12.2f€", invoice.getNetPrice()));
		sb.append("\n");
		sb.append(String.format("%26s", ""));
		sb.append(String.format("%20s", "Mehrwertsteuer:"));
		sb.append(String.format("%,12.2f€", invoice.getIncludedTax()));
		sb.append("\n");
		
		
		if (customerIsPremium) {
			sb.append("\n");
			sb.append(String.format("%26s", ""));
			sb.append(String.format("%20s", "Netto mit Rabbatt:"));
			sb.append(String.format("%,12.2f€", invoice.getDiscountedNetPrice()));
			sb.append("\n");
			sb.append(String.format("%26s", ""));
			sb.append(String.format("%20s", "MwSt. nach Rabatt:"));
			sb.append(String.format("%,12.2f€", invoice.getDiscountedIncludedTax()));
			sb.append("\n");
		}
		
		sb.append(String.format("%26s", ""));
		sb.append(String.format((new String(new char[35])).replace("\0", "_")));
		sb.append("\n");
		sb.append(String.format("%26s", ""));
		sb.append(String.format("%20s", "Gesamtbetrag:"));
		
		if (customerIsPremium) {
			sb.append(String.format("%,12.2f€", invoice.getDiscountedTotalPrice()));
		} else {
			sb.append(String.format("%,12.2f€", invoice.getTotalPrice()));
		}
		
		sb.append("\n\n\n");
		System.out.print(sb);
	}
	
	public static void showUserDeletionSuccessMessage() {
		System.out.println("Sie haben den Kunden erfolgreich gelöscht.");
	}
	
	public static void showProductChangeSuccessMessage(Car car) {
		System.out.println("Sie haben die Fahrzeugdaten erfolgreich geändert:");
		System.out.println(car);
	}
	
	public static void showUserChangeSuccessMessage(User user) {
		System.out.println("Sie haben die Userdaten erfolgreich geändert:");
		if (user instanceof Customer) {
			Customer customer = (Customer) user;
			System.out.println(customer);
		} else if (user instanceof Administrator) {
			Administrator admin = (Administrator) user;
			System.out.println(admin);
		} else {
			showGeneralErrorMessage();
		}
	}
	
	public static void showProductDoesNotExist() {
		System.out.println("Diese Produktnummer existiert nicht. Bitte versuchen Sie es erneut.");
	}
	
	public static void showNoAccessErrorMessage() {
		System.out.println("Sie haben nicht die erforderlichen Rechte, um diese Option zu verwenden.");
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
	
	public static void showGeneralErrorMessage() {
		System.out.println("Es gab ein Problem.");
	}
}
