package Versandhandel;

// FIXME: Alle Daten nochmal anzeigen lassen, nachdem man sie geändert hat 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Utility {
	private static final String CUSTOMER_CSV_PATH = "Customer.csv";
	private static final String CAR_CSV_PATH = "Car.csv";
	
	private Utility() { }
	
	// Create methode, die Produkte in der Product Array anlegt
	public static Car[] createCars () {
		Car golf = new Car(12345, "Golf 7", 18999.99, "Golf 7 in Schwarz", VehicleClass.SMALL);
		Car id3 = new Car(12346, "ID-3", 26987.98, "ID-3 in Pink", VehicleClass.MIDDLESIZE);
		Car touran = new Car(12348, "Touran", 23567.87, "Touran in Grün", VehicleClass.MIDDLESIZE);
		
		return new Car[] {golf, id3, touran};
	}


	/**
	* Die Methode berechnet die Mehrwertsteuer. 
	*
	* @param gesamterPreis     Der berechnete Preis aus Menge * Preis
	* @param mwstSatz          Mehrwertsteuer in Prozent, z.B. 0.19 
	* @return                  Die Mehrwertsteuer wird auf zwei Nachkommastellen gerundet ausgeggeben
	*/
	public static double mehrwertsteuer (double totalPrice, double mwstSatz){
		return Math.round(totalPrice * mwstSatz * 100) / 100.0;
	}


	/**
	* Die Methode berechnet den gesamten Preis. 
	*
	* @param menge             Die vom Kunden gewünschte Menge
	* @param produktpreis      Der festgelegte Preis pro Stück
	* @return                  Der gesamte Preis wird ausgegeben
	*/
	public static double totalPrice (int menge, Car car){
		return Math.round(menge * car.getProductPrice() * 100) / 100.0; 
	}
	
	
	public static void writeCustomersToFile(Customer customerArray[]) {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CUSTOMER_CSV_PATH))) {

			for (Customer customer : customerArray) {
				writer.write(customer.toCSVFormat());
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static Customer[] readCustomersFromFile() {
		Customer[] customers = null;

		try (BufferedReader reader = Files.newBufferedReader(Paths.get(CUSTOMER_CSV_PATH))) {

			int amountLines = (int) Files.lines(Paths.get(CUSTOMER_CSV_PATH)).count();
			customers = new Customer[amountLines];
			for (int i = 0; i <= amountLines - 1; i++) {
				String line = reader.readLine();
				String[] value = line.split(";");
				System.out.println(value);
				customers[i] = new Customer(Integer.parseInt(value[0]), value[1], value[2], value[3], value[4],
						value[5], value[6]);

			}

		} catch (IOException e) {
			System.out.println("CSV-Datei konnte nicht geladen werden. Backupdaten werden verwendet!");
			customers = CustomerManagement.createCustomers();

		}
		return customers;

	}
	
	public static void writeCarsToFile (Car[] carArray) {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CAR_CSV_PATH))) {
			for (Car car : carArray) {
				writer.write(car.toCSVFormat()); 
				writer.newLine();
				
			}
		} catch (IOException e){
			
		}
	}
}


