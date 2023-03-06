package Versandhandel;

public class Versandhandel {
	private static Car[] carArray; 

	private static Customer[] customerArray; 
	private Customer customer;

	public void run () {
		carArray = Utility.createCars();
		customerArray = Utility.readCustomersFromFile();	
		Gui.showWelcomeScreen();

		while (true){
			String output = InputUtility.getUserChoice();
			if (output == "register"){
				customer = InputUtility.getCompleteRegistration(customer, customerArray);
				customerArray = CustomerManagement.kundeSpeichern(customer, customerArray);
			} else if (output == "login"){
				customer = Gui.login(customerArray);
				Gui.showMenu(customer, carArray);
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