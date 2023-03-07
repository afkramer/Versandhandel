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
			for (int i = 0; i < amountLines; i++) {
				String line = reader.readLine();
				String[] value = line.split(";");
				// TODO: differentiate here what kind of user it is ->
				// if it is an admin, there will be no sales or discount values
				customers[i] = new Customer(Integer.parseInt(value[0]), value[1], value[2], value[3], value[4],
						value[5], value[6]);
			}
		} catch (IOException e) {
			customers = CustomerManagement.createCustomers();
			Gui.showReadErrorMessage();
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
	

