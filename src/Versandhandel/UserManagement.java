package Versandhandel;

public class UserManagement {

	private static final int CUSTOMER_NUMBER_LENGTH = 6;
	
	private UserManagement() {}
	
	public static User[] createCustomers () {
		Customer max = new Customer("Max", "Mustermann", "Volkswagenweg", "1", "38440", "Wolfsburg");
		Customer mareike = new Customer ("Mareike", "Musterfrau", "Audiweg", "2", "38100", "Braunschweig"); 
		Customer lara = new Customer ("Lara", "Schneider", "Skodaweg", "3", "38446", "Wolfsburg");
		return assignCustomerNumber(max, mareike, lara);
	}

	//TODO: show the new customer number!
	public static Customer registerCustomer(User[] users) {
		String firstName = InputUtility.getFirstNameInput();
		String surname = InputUtility.getSurnameInput();
		String street = InputUtility.getStreetInput();
		String houseNumber = InputUtility.getHouseNumberInput();
		String zipCode = InputUtility.getZipCodeInput();
		String city = InputUtility.getCityInput();
		Customer customer = new Customer(firstName, surname, street, houseNumber, zipCode, city);
		UserManagement.createUserId(customer, users);
		return customer;
	}
	
	public static User[] assignCustomerNumber ( User... users ) { //x-beliebige Parameter der Klasse Customer 
		for( int i = 0; i < users.length; i++) {
			createUserId(users[i], users);
		}   
        return users;
	}

	public static void createUserId (User user, User[] users){
		int minNumber = (int) Math.pow(10, CUSTOMER_NUMBER_LENGTH - 1);
		int maxNumber = (int) Math.pow(10, CUSTOMER_NUMBER_LENGTH) - 1;

		while (true){
			int randomNumber = (int) (Math.random() * maxNumber); 
			boolean istInArray = isUserNumberValid(randomNumber, users);
			if (!istInArray && randomNumber >= minNumber && randomNumber <= maxNumber){
				user.setUserId(randomNumber);
				break;
			}
		}
	}
	

    /**
    * Speichert die neue Kundennumer in einem neuen Array.
	* 
	* @param kundennummer Die dem Kunden zugeteilte Nummer.
	*/
	public static User[] saveUser (User user, User[] users){
		users = enlargeUserArray(user, users);
		Utility.writeUsersToFile(users);
		return users;
	}
    
	
	/**
	* Die Methode gleicht die eingegebene mit den vorhandenen Kundennummern ab.
	*
	* @param kundennummer          Die dem Kunden zugeteilte Nummer.
	* @return                      Gibt zurück ob die Kundennummer im Array ist.
	*/
	public static boolean isUserNumberValid (int userId, User[] users) {
		for(int i = 0; i <users.length; i++) {
			if (users[i] != null && userId == users[i].getUserId()) {
				return true;
			}
		}
		return false;
	}

	
	/**
	* Der entsprechende Kunde zu einer Kundennummer zurückgeben
	*/
	public static User returnUserByUserId (int userId, User[] users) {

		for(int i = 0; i < users.length; i++) {
			if (userId == users[i].getUserId()) {
				return users[i];
			}

		}
		return null;
	}

	//TODO: show what the results of the update are
	public static void changeData(User user) {
		int choice;
		while (true) {
			choice = InputUtility.getUserData();

			if (choice == 1) {
				user.setFirstName(InputUtility.getFirstNameInput());
			} else if (choice == 2) {
				user.setSurname(InputUtility.getSurnameInput());
			} else if (choice == 3) {
				user.setStreet(InputUtility.getStreetInput());
			} else if (choice == 4) {
				user.setHouseNumber(InputUtility.getHouseNumberInput());
			} else if (choice == 5) {
				user.setZipCode(InputUtility.getZipCodeInput());
			} else if (choice == 6) {
				user.setCity(InputUtility.getCityInput());
			} else if (choice == 7) {
				break;
			} else {
				System.out.println("Bitte geben Sie eine Zahl von 1-7 ein.");
			}
		} 
	}


	public static User[] deleteUser(String firstName, String lastName, int userId, User[] users){
		User user = returnUserByUserId(userId, users);
		
		if (user != null && user.getFirstName().equals(firstName) && user.getSurname().equals(lastName)){
			User[] newUsers = new Customer[users.length - 1]; 
			int newIndex = 0; 
			for (int i = 0; i < users.length; i++) { 
				if (userId != users[i].getUserId()) {
					newUsers[newIndex] = users[i]; 
					newIndex++; 
				}
			}
			return newUsers; 
		} else {
			Gui.showDeleteErrorMessage();
			return users;
		}
	}
	
	
	public static User[] enlargeUserArray (User user, User[] users) {
		User[] newUsers = new User[users.length + 1];
		for(int i = 0; i < users.length; i++) {
			newUsers[i] = users[i];
		}
		
		newUsers[newUsers.length - 1] = user;
		return newUsers;
	}
} 


