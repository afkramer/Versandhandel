package de.volkswagen.fakultaet.salesplatform.view;

import de.volkswagen.fakultaet.salesplatform.model.Administrator;
import de.volkswagen.fakultaet.salesplatform.model.Car;
import de.volkswagen.fakultaet.salesplatform.model.Customer;
import de.volkswagen.fakultaet.salesplatform.model.Invoice;
import de.volkswagen.fakultaet.salesplatform.model.User;
import de.volkswagen.fakultaet.salesplatform.model.UserType;
import de.volkswagen.fakultaet.salesplatform.service.CarManagement;
import de.volkswagen.fakultaet.salesplatform.service.InputManagement;
import de.volkswagen.fakultaet.salesplatform.service.PersistenceManagement;
import de.volkswagen.fakultaet.salesplatform.service.UserManagement;

public final class Gui {
	
	public static final String CYAN_TEXT = "\u001B[36m";
    public static final String GREEN_TEXT = "\u001B[32m";
    public static final String YELLOW_TEXT = "\u001B[33m";
    public static final String PURPLE_TEXT = "\u001B[35m";
    public static final String BLUE_TEXT = "\u001B[34m";
    public static final String RED_TEXT = "\u001B[31m";
    public static final String ANSCI_RESET = "\u001B[0m";

	private Gui() {
	}

	/**
	 * Die Methode heißt den Kunden willkommen.
	 *
	 */
	public static void showWelcomeScreen() {
		System.out.println(BLUE_TEXT + "\n\n\n***************************************");
		System.out.print(PURPLE_TEXT);
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.print(CYAN_TEXT);
		System.out.println("**********Herzlich Willkommen**********");
		System.out.print(PURPLE_TEXT);
		System.out.println("***************************************");
		System.out.println("***************************************");
		System.out.print(BLUE_TEXT);
		System.out.println("***************************************\n\n\n" + ANSCI_RESET);

		InputManagement.waitingForPressingEnter();
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
				int kundennummer = InputManagement.getUserIdInput();
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
					showInvalidInputErrorMessage();
				}

			} catch (NumberFormatException nfe) {
				showInvalidInputErrorMessage();
			}

		}

		InputManagement.waitingForPressingEnter();

		return user;

	}
	
	public static void greetUser(User user) {
		if (user instanceof Customer) {
			System.out.print(CYAN_TEXT);
			System.out.println("\n\nHerzlich Willkommen " + user.getFirstName());
			System.out.print(ANSCI_RESET);
		} else {
			System.out.print(CYAN_TEXT);
			System.out.println("\n\nHallo Administrator " + user.getFirstName());
			System.out.print(ANSCI_RESET);
		}
	}
	
	public static boolean isPasswordValid(Administrator admin) {
		String password;
		for(int i = 0; i < 3; i++) {
			System.out.print(CYAN_TEXT);
			System.out.println("\nBitte geben Sie Ihr Passwort ein:\n\n");
			System.out.print(ANSCI_RESET);
			password = InputManagement.getPasswordInput();
			if (password.equals(admin.getPassword())) {
				return true;
			} else {
				System.out.print(RED_TEXT);
				System.out.println("\nDas ist nicht richtig. Verbleibende Versuche " + (2 - i) + ".\n\n");
				System.out.print(ANSCI_RESET);
			}
		}
		System.out.print(RED_TEXT);
		System.out.println("\nSie haben zu viele Fehlversuche gehabt.\n\n");
		System.out.print(ANSCI_RESET);
		return false;
	}
	
	public static void showRegistrationResults(Customer customer) {
		System.out.print(CYAN_TEXT);
		System.out.println("\n\nVielen Dank für Ihre Registrierung " + customer.getFirstName());
		System.out.println("Deine neue Kundennummer lautet " + customer.getUserId());
		System.out.print(ANSCI_RESET);
	}

	public static User[] showMenu(User user, User[] users, Car[] cars) {
		int menge;
		UserType userType = user.getUserType();

		while (true) {

			try {
				int choice = InputManagement.getUserChoiceForMenu(user);
				if (choice == 1) {
					System.out.print(GREEN_TEXT);
					System.out.println("Sie haben sich erfolgreich ausgeloggt\n\n");
					System.out.print(ANSCI_RESET);
					break;

				} else if (choice == 2) {
					System.out.print(CYAN_TEXT);
					System.out.println("\nDeine aktuelle Kundendaten:");
					System.out.print(ANSCI_RESET);
					if (user instanceof Customer) {
						System.out.println((Customer) user + "\n");
					} else {
						System.out.println((Administrator) user + "\n");
					}
					
					UserManagement.changeData(user);
					Gui.showUserChangeSuccessMessage(user);
					PersistenceManagement.writeUsersToFile(users);

				} else if (choice == 3) {
					if (userType.equals(UserType.ADMINISTRATOR)) {
						users = deleteUser(users, user);
						PersistenceManagement.writeUsersToFile(users);
					} else {
						CarManagement.buyCar(cars, users, user);
					}
					
				} else if (choice == 4){
					if (userType.equals(UserType.ADMINISTRATOR)) {
						showProduct(cars);
						Car car = InputManagement.getDesiredProduct(cars, "ändern");
						CarManagement.changeData(car);
						PersistenceManagement.writeCarsToFile(cars);
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
		System.out.print(CYAN_TEXT);
		System.out.println("\n\n\nDiese Autos stehen zur Verfügung:\n\n");
		System.out.print(ANSCI_RESET);
		for (int i = 0; i < carArray.length; i++) {
			showProduct(carArray[i]);
		}
		InputManagement.waitingForPressingEnter();
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
	
	public static User[] deleteUser(User[] users, User currentUser) {
		System.out.print(CYAN_TEXT);
		System.out.println("Welchen Kunden möchten Sie löschen?\n\n");
		int userId = InputManagement.getUserIdInput();
		String firstName = InputManagement.getFirstNameInput();
		String lastName = InputManagement.getSurnameInput();
		return UserManagement.deleteUser(firstName, lastName, userId, users, currentUser);
	}

	/**
	 * Die Methode verabschiedet den Kunden.
	 *
	 * @param kundennummer Die dem Kunden zugeteilte Nummer.
	 */
	public static void showFarewell(User user) {
		if (user == null) {
			System.out.print(PURPLE_TEXT);
			System.out.println("\n\n\nVielen Dank für Ihren Besuch.\n\n");
			System.out.print(ANSCI_RESET);
		} else {
			System.out.print(PURPLE_TEXT);
			System.out.println("\n\n\nVielen Dank für Ihren Besuch " + user.getFirstName() + "!\n\n");
			System.out.print(ANSCI_RESET);
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
			System.out.print(GREEN_TEXT);
			sb.append(String.format("Sie sind ein Premium Kunde! Vielen Dank für Ihre Treue.%n%n", customer.getUserType().getGermanText()));
			System.out.print(ANSCI_RESET);
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
		System.out.print(GREEN_TEXT);
		System.out.println("Sie haben den Kunden erfolgreich gelöscht.");
		System.out.print(ANSCI_RESET);
	}
	
	public static void showProductChangeSuccessMessage(Car car) {
		System.out.print(GREEN_TEXT);
		System.out.println("Sie haben die Fahrzeugdaten erfolgreich geändert:");
		System.out.print(ANSCI_RESET);
		System.out.println(car);
		
	}
	
	public static void showUserChangeSuccessMessage(User user) {
		System.out.print(GREEN_TEXT);
		System.out.println("Sie haben die Userdaten erfolgreich geändert:");
		System.out.print(ANSCI_RESET);
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
	
	public static void showAbortSaleMessage() {
		System.out.print(GREEN_TEXT);
		System.out.println("Sie haben den Kaufvorgang erfolgreich abgebrochen.");
		System.out.print(ANSCI_RESET);
	}
	
	public static void showProductDoesNotExist() {
		System.out.print(RED_TEXT);
		System.out.println("Diese Produktnummer existiert nicht. Bitte versuchen Sie es erneut.\n\n");
		System.out.print(ANSCI_RESET);
	}
	
	public static void showCanNotDeleteSelfErrorMessage() {
		System.out.print(RED_TEXT);
		System.out.println("Du kannst Dich selbst nicht löschen!\n\n");
		System.out.print(ANSCI_RESET);
	}
	
	public static void showNoAccessErrorMessage() {
		System.out.print(RED_TEXT);
		System.out.println("Sie haben nicht die erforderlichen Rechte, um diese Option zu verwenden.\n\n");
		System.out.print(ANSCI_RESET);
	}

	public static void showDeleteErrorMessage() {
		System.out.print(RED_TEXT);
		System.out.println("Der Kunde konnte nicht gelöscht werden. Bitte kontrollieren Sie Ihre Daten noch einmal.\n\n");
		System.out.print(ANSCI_RESET);
	}
	
	public static void showReadErrorMessage() {
		System.out.print(RED_TEXT);
		System.out.println("Die Daten konnten nicht gelesen werden. Es werden die Backup Daten verwendet.\n\n");
		System.out.print(ANSCI_RESET);
	}
	public static void showWriteErrorMessage() {
		System.out.print(RED_TEXT);
		System.out.println("Die Daten konnten nicht gespeichert werden. Bitte kontrollieren Sie Ihre Daten.\n\n");
		System.out.print(ANSCI_RESET);
	}
	public static void showInvalidInputErrorMessage () {
		System.out.print(RED_TEXT);
		System.out.println("Ihre Eingabe ist fehlerhaft. Bitte versuchen Sie es erneut.\n\n");
		System.out.print(ANSCI_RESET);
	}
	
	public static void showGeneralErrorMessage() {
		System.out.print(RED_TEXT);
		System.out.println("Es gab ein Problem.\n\n");
		System.out.print(ANSCI_RESET);
	}
}
