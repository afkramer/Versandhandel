package Versandhandel;

public class Administrator extends User {
	
	private String password;
	
	public Administrator(int userId, String firstName, String surname, String street, String houseNumber, String zipCode, String city, String password) {
		super(userId, firstName, surname, street, houseNumber, zipCode, city);
		this.password = password;
	}
	
	public Administrator(int userId, String firstName, String surname, String street, String houseNumber, String zipCode, String city) {
		this(userId, firstName, surname, street, houseNumber, zipCode, city, null);
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

}
