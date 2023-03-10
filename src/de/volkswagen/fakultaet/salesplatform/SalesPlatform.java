package de.volkswagen.fakultaet.salesplatform;

import de.volkswagen.fakultaet.salesplatform.model.Car;
import de.volkswagen.fakultaet.salesplatform.model.Customer;
import de.volkswagen.fakultaet.salesplatform.model.User;
import de.volkswagen.fakultaet.salesplatform.service.InputManagement;
import de.volkswagen.fakultaet.salesplatform.service.PersistenceManagement;
import de.volkswagen.fakultaet.salesplatform.service.UserManagement;
import de.volkswagen.fakultaet.salesplatform.view.Gui;

public class SalesPlatform {
	private static Car[] cars; 

	private static User[] users; 
	private User user;

	public void run () {
		cars = PersistenceManagement.readCarsFromFile();
		users = PersistenceManagement.readUsersFromFile();	
		Gui.showWelcomeScreen();

		while (true){
			String output = InputManagement.getUserChoice();
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
		
		Gui.showFarewell(user);
	}
}