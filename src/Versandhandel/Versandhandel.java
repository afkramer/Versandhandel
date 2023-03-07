package Versandhandel;

//TODO: do I want to rename all references to Customer to User instead? 

public class Versandhandel {
	private static Car[] cars; 

	private static User[] users; 
	private User user;

	public void run () {
		cars = Utility.readCarsFromFile();
		users = Utility.readUsersFromFile();	
		Gui.showWelcomeScreen();

		while (true){
			String output = InputUtility.getUserChoice();
			if (output == "register"){
				user = UserManagement.registerCustomer(users);
				Gui.showRegistrationResults(user); 
				users = UserManagement.saveUser(user, users);
			} else if (output == "login"){
				user = Gui.login(users);
				users = Gui.showMenu(user, users, cars);
			} else if (output == "quit"){ 
				break;
			} else {
				System.out.println("Error!");
			}
		}
		
		Gui.verabschiedung(user);
	}
}