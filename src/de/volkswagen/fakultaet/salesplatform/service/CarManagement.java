package de.volkswagen.fakultaet.salesplatform.service;

import de.volkswagen.fakultaet.salesplatform.annotations.TestMethod;
import de.volkswagen.fakultaet.salesplatform.model.Car;
import de.volkswagen.fakultaet.salesplatform.model.Customer;
import de.volkswagen.fakultaet.salesplatform.model.Invoice;
import de.volkswagen.fakultaet.salesplatform.model.User;
import de.volkswagen.fakultaet.salesplatform.model.VehicleClass;
import de.volkswagen.fakultaet.salesplatform.view.Gui;

public final class CarManagement {
	public static final double TAX = 119;

	@TestMethod(reason = "database replacement")
	public static Car[] createCars() {
		Car golf = new Car(12345, "Golf 7", 18999.99, "Golf 7 in Schwarz", VehicleClass.SMALL);
		Car id3 = new Car(12346, "ID-3", 26987.98, "ID-3 in Pink", VehicleClass.MIDDLESIZE);
		Car touran = new Car(12348, "Touran", 23567.87, "Touran in Grün", VehicleClass.MIDDLESIZE);

		return new Car[] { golf, id3, touran };
	}
	
	public static Car findCarByProductNumber(int productNumber, Car[] cars) {
		for (int i = 0; i < cars.length; i++) {
			if (productNumber == cars[i].getProductNumber()) {
				return cars[i];
			}
		}
		return null;
	}

	public static void changeData(Car car) {
		int choice;
		while (true) {
			choice = InputManagement.getCarFieldToChange();

			if (choice == 0) {
				break;
			} else if (choice == 1) {
				car.setProductName(InputManagement.getCarNameInput());
				Gui.showProductChangeSuccessMessage(car);
			} else if (choice == 2) {
				car.setProductPrice(InputManagement.getCarPriceInput());
				Gui.showProductChangeSuccessMessage(car);
			} else if (choice == 3) {
				car.setProductDescription(InputManagement.getCarDescriptionInput());
				Gui.showProductChangeSuccessMessage(car);
			} else {
				Gui.showInvalidInputErrorMessage();
			}
		} 
	}
	
	public static void buyCar(Car[] cars, User[] users, User user) {
		Gui.showProduct(cars);
		Car car = InputManagement.getDesiredProduct(cars, "kaufen");
		int menge;
		while (true) {
			menge = InputManagement.getNumberOfProducts();
			if (menge == 0) {
				Gui.showAbortSaleMessage();

				break;
			} else if (menge > 0) {
				Invoice invoice = new Invoice((Customer) user, car, menge);
				Gui.showInvoice((Customer) user, car, invoice);
				PersistenceManagement.writeUsersToFile(users);

				break;

			} else {
				Gui.showInvalidInputErrorMessage();
			}
		}
	}

	/**
	 * Die Methode berechnet die Mehrwertsteuer.
	 *
	 * @param gesamterPreis Der berechnete Preis aus Menge * Preis
	 * @param mwstSatz      Mehrwertsteuer in Prozent, z.B. 0.19
	 * @return Die Mehrwertsteuer wird auf zwei Nachkommastellen gerundet
	 *         ausgeggeben
	 */
	public static double calculateIncludedTax(double totalPrice) {
		double netPrice = totalPrice / TAX * 100;
		return Math.round((totalPrice - netPrice) * 100) / 100.0;
	}

	/**
	 * Die Methode berechnet den gesamten Preis.
	 *
	 * @param menge        Die vom Kunden gewünschte Menge
	 * @param produktpreis Der festgelegte Preis pro Stück
	 * @return Der gesamte Preis wird ausgegeben
	 */
	public static double calculateTotalPrice(int menge, Car car) {
		return Math.round(menge * car.getProductPrice() * 100) / 100.0;
	}
}
