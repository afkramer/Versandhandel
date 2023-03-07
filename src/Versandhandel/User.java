package Versandhandel;

public class User {
	private int userId; 
	
	private UserType userType;
	
	private String firstName; 
	
	private String surname; 
	
	private String street; 
	
	private String houseNumber; 
	
	private String zipCode; 
	
	private String city; 

	public User(){

	}

	public User(String firstName, String surname, String street, String houseNumber, String zipCode, String city){ 
		this.firstName = firstName; 
		this.surname = surname; 
		this.street = street; 
		this.houseNumber = houseNumber; 
		this.zipCode = zipCode; 
		this.city = city;
		
	}

	public User(int customerNumber, String firstName, String surname, String street, String houseNumber, String zipCode, String city){ 
		this.userId = customerNumber; 
		this.firstName = firstName; 
		this.surname = surname; 
		this.street = street; 
		this.houseNumber = houseNumber; 
		this.zipCode = zipCode; 
		this.city = city;
		
	}
	
	public int getUserId() {
		return this.userId; 
	}
	
	public void setUserId(int userId) {
		this.userId = userId; 
		
	}
	
	public UserType getUserType() {
		return this.userType;
	}
	
	public String getFirstName() {
		return firstName; 
	}
	
	public void setFirstName (String firstName) {
		this.firstName = firstName;
	}
	
	public String getSurname() {
		return surname; 
	}
	
	public void setSurname (String surname) {
		this.surname = surname; 
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet (String street) {
		this.street = street;
	}
	
	public String getHouseNumber() {
		return houseNumber; 
	}
	
	public void setHouseNumber (String houseNumber) {
		this.houseNumber = houseNumber;
	}
	
	public String getZipCode() {
		return zipCode;
	}
	
	public void setZipCode (String zipCode) {
		this.zipCode = zipCode;
	}
	
	public String getCity() {
		return city; 
	}
	
	public void setCity (String city) {
		this.city = city;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(); 
		sb.append("Name: \n");
		sb.append(this.firstName + " " + this.surname + "\n\n");
		sb.append("Adresse: \n");
		sb.append(this.street + " " + this.houseNumber + "\n");
		sb.append(this.zipCode + " " + this.city + "\n\n"); 
		return sb.toString();
	}
	
	public String toCSVFormat () {
		return userId + ";" + firstName + ";" + surname + ";" + street + ";" + houseNumber + ";" + zipCode + ";" + city + ";";
	}
}
