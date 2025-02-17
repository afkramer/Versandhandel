package de.volkswagen.fakultaet.salesplatform.model;

public class Administrator extends User {
	
	private static int counter = 0; 
	private String password;
	
	public Administrator(int userId, UserType userType, String firstName, String surname, String street, String houseNumber, String zipCode, String city, String password) {
		super(userId, userType, firstName, surname, street, houseNumber, zipCode, city);
		this.password = password;
		counter++;
	}
	
	public Administrator(int userId, String firstName, String surname, String street, String houseNumber, String zipCode, String city) {
		this(userId, UserType.ADMINISTRATOR, firstName, surname, street, houseNumber, zipCode, city, "startPassword");
	}

	public Administrator (String firstName, String surname, String street, String houseNumber, String zipCode, String city) {
		this(0, firstName, surname, street, houseNumber, zipCode, city);
	}
	
	public int getNumberOfInstances() {
		return counter;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(); 
		sb.append(super.toString());
		sb.append("\nPassword: \n");
		sb.append(this.password);
		return sb.toString();
	}
	
	public String toCSVFormat () {
		return super.toCSVFormat() + this.password + ";";
	}

}
