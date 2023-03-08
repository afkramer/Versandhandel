package Versandhandel;

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
				Gui.showRegistrationResults((Customer) user); 
				users = UserManagement.saveUser(user, users);
			} else if (output == "login"){
				user = Gui.login(users);
				if (user != null) {
					users = Gui.showMenu(user, users, cars);
				}
				
			} else if (output == "quit"){ 
				break;
			} else {
				Gui.showGeneralErrorMessage();
			}
		}
		
		Gui.verabschiedung(user);
	}
}