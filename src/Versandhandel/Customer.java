package Versandhandel;

public class Customer extends User {
	
	private double totalSales;
	private double discount; 


	public Customer(int userId, String firstName, String surname, String street, String houseNumber, String zipCode, String city){ 
		super(userId, firstName, surname, street, houseNumber, zipCode, city);
		this.totalSales = 0.0;
		this.discount = 0.0;
	}
	
	public Customer(String firstName, String surname, String street, String houseNumber, String zipCode, String city){ 
		this(0, firstName, surname, street, houseNumber, zipCode, city);
	}
	
	public double getTotalSales() {
		return this.totalSales;
	}
	
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
	}
	
	public double getDiscount() {
		return this.discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	
	public void addToTotalSales(double sale) {
		totalSales += sale;
	}
	
	public double applyDiscount(double total) {
		return total * (1 - this.discount);
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(); 
		sb.append(super.toString());
		sb.append("Gesamt Verkäufe: \n");
		sb.append(this.totalSales + "\n");
		sb.append("Rabatt: \n");
		sb.append(this.discount + "\n");
		return sb.toString();
	}
	
	public String toCSVFormat () {
		return super.toCSVFormat() + this.totalSales + ";" + this.discount + ";";
	}

}
