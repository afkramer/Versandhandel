package Versandhandel;

public class CustomerManagement {

	private static final int CUSTOMER_NUMBER_LENGTH = 6;
	
	private CustomerManagement() {}
	
	public static Customer[] createCustomers () {
		Customer max = new Customer("Max", "Mustermann", "Volkswagenweg", "1", "38440", "Wolfsburg");
		Customer mareike = new Customer ("Mareike", "Musterfrau", "Audiweg", "2", "38100", "Braunschweig"); 
		Customer lara = new Customer ("Lara", "Schneider", "Skodaweg", "3", "38446", "Wolfsburg");
		return assignCustomerNumber(max, mareike, lara);
	}

	//TODO: show the new customer number!
	public static Customer registerCustomer(Customer[] customerArray) {
		String firstName = InputUtility.getFirstNameForRegistration();
		String surname = InputUtility.getSurnameForRegistration();
		String street = InputUtility.getStreetForRegistration();
		String houseNumber = InputUtility.getHouseNumberForRegistration();
		String zipCode = InputUtility.getZipCodeForRegistration();
		String city = InputUtility.getPlaceForRegistration();
		Customer customer = new Customer(firstName, surname, street, houseNumber, zipCode, city);
		CustomerManagement.createUserId(customer, customerArray);
		return customer;
	}
	
	public static Customer[] assignCustomerNumber ( Customer... customers ) { //x-beliebige Parameter der Klasse Customer 
		Customer[] customerArray = new Customer[customers.length];
		for( int i = 0; i < customers.length; i++) {
			createUserId(customers[i], customerArray);
			customerArray[i] = customers[i]; 
		}   
        return customerArray;
	}

	public static void createUserId (Customer customer, Customer[] customerArray){
		int minNumber = (int) Math.pow(10, CUSTOMER_NUMBER_LENGTH - 1);
		int maxNumber = (int) Math.pow(10, CUSTOMER_NUMBER_LENGTH) - 1;

		while (true){
			int zufallsZahl = (int) (Math.random() * maxNumber); 
			boolean istInArray = isCustomerNumberValid(zufallsZahl, customerArray);
			if (!istInArray && zufallsZahl >= minNumber && zufallsZahl <= maxNumber){
				customer.setUserId(zufallsZahl);
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
		Utility.writeCustomersToFile(customerArray);
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
			if (customerArray[i] != null && kundennummer == customerArray[i].getUserId()) {
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
			if (kundennummer == customerArray[i].getUserId()) {
				return customerArray[i];
			}

		}
		return null;
	}

	public static void changeData(Customer customer) {
		int choice;
		while (true) {
			choice = InputUtility.getUserData();

			if (choice == 1) {
				customer.setFirstName(InputUtility.getFirstNameForRegistration());
			} else if (choice == 2) {
				customer.setSurname(InputUtility.getSurnameForRegistration());
			} else if (choice == 3) {
				customer.setStreet(InputUtility.getStreetForRegistration());
			} else if (choice == 4) {
				customer.setHouseNumber(InputUtility.getHouseNumberForRegistration());
			} else if (choice == 5) {
				customer.setZipCode(InputUtility.getZipCodeForRegistration());
			} else if (choice == 6) {
				customer.setCity(InputUtility.getPlaceForRegistration());
			} else if (choice == 7) {
				break;
			} else {
				System.out.println("Bitte geben Sie eine Zahl von 1-7 ein.");
			}
		} 
	}


	public static Customer[] deleteCustomer(String firstName, String lastName, int customerNumber, Customer[] customerArray){
		Customer customer = returnCustomer(customerNumber, customerArray);
		
		if (customer != null && customer.getFirstName().equals(firstName) && customer.getSurname().equals(lastName)){
			Customer[] newCustomerArray = new Customer[customerArray.length - 1]; 
			int newIndex = 0; 
			for (int i = 0; i < customerArray.length; i++) { 
				if (customerNumber != customerArray[i].getUserId()) {
					newCustomerArray[newIndex] = customerArray[i]; 
					newIndex++; 
				}
			}
			return newCustomerArray; 
		} else {
			Gui.showDeleteErrorMessage();
			return customerArray;
		}
	}
	
	
	public static Customer[] customerArrayVergroessern (Customer customer, Customer[] customerArray) {
		Customer[] newCustomerArray = new Customer[customerArray.length + 1];
		for(int i = 0; i < customerArray.length; i++) {
			newCustomerArray[i] = customerArray[i];
		}
		
		newCustomerArray[newCustomerArray.length - 1] = customer;
		return newCustomerArray;
	}
} 



