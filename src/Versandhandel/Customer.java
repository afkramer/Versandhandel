package Versandhandel;

public class Customer {
	
	private int customerNumber; 
	
	private String firstName; 
	
	private String surname; 
	
	private String street; 
	
	private String houseNumber; 
	
	private String zipCode; 
	
	private String city; 

	public Customer(){

	}

	public Customer(String firstName, String surname, String street, String houseNumber, String zipCode, String city){ 
		this.firstName = firstName; 
		this.surname = surname; 
		this.street = street; 
		this.houseNumber = houseNumber; 
		this.zipCode = zipCode; 
		this.city = city;
		
	}

	public Customer(int customerNumber, String firstName, String surname, String street, String houseNumber, String zipCode, String city){ 
		this.customerNumber = customerNumber; 
		this.firstName = firstName; 
		this.surname = surname; 
		this.street = street; 
		this.houseNumber = houseNumber; 
		this.zipCode = zipCode; 
		this.city = city;
		
	}
	
	public int getCustomerNumber() {
		return customerNumber; 
	}
	
	public void setCustomerNumber(int customerNumber) {
		this.customerNumber = customerNumber; 
		
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
		return customerNumber + ";" + firstName + ";" + surname + ";" + street + ";" + houseNumber + ";" + zipCode + ";" + city + ";";
	}

}
