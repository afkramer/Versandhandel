package Versandhandel;

// FIXME: Alle Daten nochmal anzeigen lassen, nachdem man sie geändert hat 
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Utility {
    private static final int CUSTOMER_NUMBER_LENGTH = 6;
	private static final String CUSTOMER_CSV_PATH = "Customer.csv";
	private static final String CAR_CSV_PATH = "/src/Versandhandel/Car.csv";
	
	private Utility() { }
	
	// Create methode, die Produkte in der Product Array anlegt
	public static Car[] createCars () {
		Car golf = new Car(12345, "Golf 7", 18999.99, "Golf 7 in Schwarz", VehicleClass.SMALL);
		Car id3 = new Car(12346, "ID-3", 26987.98, "ID-3 in Pink", VehicleClass.MIDDLESIZE);
		Car touran = new Car(12348, "Touran", 23567.87, "Touran in Grün", VehicleClass.MIDDLESIZE);
		
		return new Car[] {golf, id3, touran};
	}
	
	public static Customer[] createCustomers () {
		Customer max = new Customer("Max", "Mustermann", "Volkswagenweg", "1", "38440", "Wolfsburg");
		Customer mareike = new Customer ("Mareike", "Musterfrau", "Audiweg", "2", "38100", "Braunschweig"); 
		Customer lara = new Customer ("Lara", "Schneider", "Skodaweg", "3", "38446", "Wolfsburg");
		return assignCustomerNumber(max, mareike, lara);
	}
	
	public static Customer[] assignCustomerNumber ( Customer... customers ) { //x-beliebige Parameter der Klasse Customer 
		Customer[] customerArray = new Customer[customers.length];
		for( int i = 0; i < customers.length; i++) {
			createCustomerNumber(customers[i], customerArray);
			customerArray[i] = customers[i]; 
		}   
        return customerArray;
	}

	public static void createCustomerNumber (Customer customer, Customer[] customerArray){
		int minNumber = (int) Math.pow(10, CUSTOMER_NUMBER_LENGTH - 1);
		int maxNumber = (int) Math.pow(10, CUSTOMER_NUMBER_LENGTH) - 1;

		while (true){
			int zufallsZahl = (int) (Math.random() * maxNumber); 
			boolean istInArray = isCustomerNumberValid(zufallsZahl, customerArray);
			if (!istInArray && zufallsZahl >= minNumber && zufallsZahl <= maxNumber){
				customer.setCustomerNumber(zufallsZahl);
				break;
			}
		}
	}

    /**
    * Speichert die neue Kundennumer in einem neuen Array.
	* 
	* @param kundennummer Die dem Kunden zugeteilte Nummer.
	*/
	public static Customer[] kundeSpeichern (Customer customer, Customer[] customerArray){
		 
		customerArray = customerArrayVergroessern(customer, customerArray);
		copyContents(customerArray);
		return customerArray;
	}
    
	/**
	* Die Methode gleicht die eingegebene mit den vorhandenen Kundennummern ab.
	*
	* @param kundennummer          Die dem Kunden zugeteilte Nummer.
	* @return                      Gibt zurück ob die Kundennummer im Array ist.
	*/
	public static boolean isCustomerNumberValid (int kundennummer, Customer[] customerArray) {
		for(int i = 0; i <customerArray.length; i++) {
			if (customerArray[i] != null && kundennummer == customerArray[i].getCustomerNumber()) {
				return true;
			}
		}
		return false;
	}

	/**
	* Der entsprechende Kunde zu einer Kundennummer zurückgeben
	*/
	public static Customer returnCustomer (int kundennummer, Customer[] customerArray) {

		for(int i = 0; i < customerArray.length; i++) {
			if (kundennummer == customerArray[i].getCustomerNumber()) {
				return customerArray[i];
			}

		}
		return null;
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
	
	public static void updateCustomer (Customer customer, String newData, int choice) { //TODO: newDatat kann dann rausgenommen werden
		switch (choice) {
			case 1:
				customer.setFirstName(newData); //TODO; Wenn fertig, InputUtility.getFirstName();
				break;
			case 2: 
				customer.setSurname(newData);
				break; 
			case 3: 
				customer.setStreet(newData);
				break; 
			case 4: 
				customer.setHouseNumber(newData);
				break; 
			case 5: 
				customer.setZipCode(newData); 
				break; 
			case 6: 
				customer.setCity(newData);
				break; 
			default: 
				System.out.println("Falsche Eingabe."); 
		}
	}

	
		public static void copyContents (Customer customerArray[]){
			try(BufferedWriter writer = Files.newBufferedWriter(Paths.get(CUSTOMER_CSV_PATH))) {
				
				for (Customer customer : customerArray) {
					writer.write(customer.toCSVFormat()); 
					writer.newLine();
				}


			} catch (IOException e){
				e.printStackTrace();
			}
		}
			
		public static Customer[] readCustomersFromFile() {
			Customer[] customers = null;
			
			try (BufferedReader reader = Files.newBufferedReader(Paths.get(CUSTOMER_CSV_PATH))) {
				
				int amountLines = (int) Files.lines(Paths.get(CUSTOMER_CSV_PATH)).count(); 
				customers = new Customer[amountLines];
				for (int i = 0; i <= amountLines -1; i++) {
					String line = reader.readLine();
					String [] value = line.split(";"); 
					System.out.println(value); 
					customers[i] = new Customer(Integer.parseInt(value[0]), value[1], value[2], value[3], value[4], value[5], value[6]);
	
				}
				
			} catch (IOException e){
				System.out.println("CSV-Datei konnte nicht geladen werden. Backupdaten werden verwendet!");
				customers = createCustomers();
			
			} 
			return customers;

		} 

		public static Customer[] customerArrayVergroessern (Customer customer, Customer[] customerArray) {
			Customer[] newCustomerArray = new Customer[customerArray.length + 1];
			for(int i = 0; i < customerArray.length; i++) {
				newCustomerArray[i] = customerArray[i];
			}
			
			newCustomerArray[newCustomerArray.length - 1] = customer;
			return newCustomerArray;
		}
		
		
		
		//public Customer[] deleteCustomer (Customer customer) {
			//return customer = null;
		//}

		/*
		public static Customer[] deleteCustomer (Customer customer, Customer[] customerArray) {
			Customer[] newCustomerArray = new Customer [customerArray.length -1];
			for (int i =0; i < customerArray.length; i++) {
				
			}
		}
		*/	

}


