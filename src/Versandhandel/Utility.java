package Versandhandel;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public final class Utility {
	private static final String CUSTOMER_CSV_PATH = "Customer.csv";
	private static final String CAR_CSV_PATH = "Cars.csv";
	
	private Utility() { }
	
	public static void writeUsersToFile(User[] users) {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CUSTOMER_CSV_PATH))) {

			for (User user : users) {
				writer.write(user.toCSVFormat());
				writer.newLine();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static User[] readUsersFromFile() {
		User[] users = null;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(CUSTOMER_CSV_PATH))) {
			int amountLines = (int) Files.lines(Paths.get(CUSTOMER_CSV_PATH)).count();
			users = new User[amountLines];
			for (int i = 0; i < amountLines; i++) {
				String line = reader.readLine();
				String[] fields = line.split(";");
				if (fields[1].equals(UserType.ADMINISTRATOR.germanText)) {
					users[i] = parseAdministratorFromFields(fields);
				} else {
					users[i] = parseCustomerFromFields(fields);
				}
			}
		} catch (IOException e) {
			users = UserManagement.createCustomers();
			Gui.showReadErrorMessage();
		}
		return users;
	}
	
	public static Customer parseCustomerFromFields(String[] fields) {
		int userId = Integer.parseInt(fields[0]);
		UserType userType = UserType.parseUserTypeFromGermanText(fields[1]);
		String firstName = fields[2];
		String surname = fields[3];
		String street = fields[4];
		String houseNumber = fields[5];
		String zipCode = fields[6];
		String city = fields[7];
		double totalSales = Double.parseDouble(fields[8]);
		double discount =  Double.parseDouble(fields[9]);
		return new Customer(userId, userType, firstName, surname, street, houseNumber, zipCode, city, totalSales, discount);
	}
	
	public static Administrator parseAdministratorFromFields(String[] fields) {
		int userId = Integer.parseInt(fields[0]);
		UserType userType = UserType.parseUserTypeFromGermanText(fields[1]);
		String firstName = fields[2];
		String surname = fields[3];
		String street = fields[4];
		String houseNumber = fields[5];
		String zipCode = fields[6];
		String city = fields[7];
		String password = fields[8];
		return new Administrator(userId, userType, firstName, surname, street, houseNumber, zipCode, city, password);
	}
	
	public static void writeCarsToFile (Car[] carArray) {
		try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(CAR_CSV_PATH))) {
			for (Car car : carArray) {
				writer.write(car.toCSVFormat()); 
				writer.newLine();
			}
		} catch (IOException e){
			Gui.showWriteErrorMessage(); 
		}
	}
	
	public static Car[] readCarsFromFile () {
		Car[] cars = null;
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(CAR_CSV_PATH))) {
			int amountLines = (int) Files.lines(Paths.get(CAR_CSV_PATH)).count();
			cars = new Car[amountLines];
			for (int i = 0; i < amountLines; i++){
				String line = reader.readLine();
				String[] fields = line.split(";");
				cars[i] = new Car( Integer.parseInt(fields[0]), fields[1], Double.parseDouble(fields[2]), fields[3], VehicleClass.parseVehicleClassFromGermanText(fields[4]));  
			}
			
		} catch (IOException e){
			Gui.showReadErrorMessage();
			cars = CarManagement.createCars(); 
		}	
		return cars; 	
	}
}
	

