package Versandhandel;

//TODO: do I want to rename all references to Customer to User instead? 

public class Versandhandel {
	private static Car[] carArray; 

	private static Customer[] customerArray; 
	private Customer customer;

	public void run () {
		carArray = Utility.readCarsFromFile();
		customerArray = Utility.readCustomersFromFile();	
		Gui.showWelcomeScreen();

		while (true){
			String output = InputUtility.getUserChoice();
			if (output == "register"){
				customer = CustomerManagement.registerCustomer(customerArray);
				Gui.showRegistrationResults(customer); 
				customerArray = CustomerManagement.kundeSpeichern(customer, customerArray);
			} else if (output == "login"){
				customer = Gui.login(customerArray);
				customerArray = Gui.showMenu(customer, customerArray, carArray);
			} else if (output == "quit"){ 
				break;
			} else {
				System.out.println("Error!");
			}
		}
		
		Gui.verabschiedung(customer);
	}
	
	public Customer getCustomer() {
		return customer; 
	}
	
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}