package Versandhandel;

public class Customer extends User {
	
	private double totalSales;
	private double discount; 
	
	public Customer(int userId, UserType userType, String firstName, String surname, String street, String houseNumber, String zipCode, String city, double totalSales, double discount){ 
		super(userId, userType, firstName, surname, street, houseNumber, zipCode, city);
		this.totalSales = totalSales;
		this.discount = discount;
	}
	
	public Customer(String firstName, String surname, String street, String houseNumber, String zipCode, String city){ 
		this(0, UserType.CUSTOMER, firstName, surname, street, houseNumber, zipCode, city, 0.0, 0.0);
	}
	
	public double getTotalSales() {
		return this.totalSales;
	}
	
	public void setTotalSales(double totalSales) {
		this.totalSales = totalSales;
		updatePremiumStatus();
	}
	
	public void addToTotalSales(double sale) {
		totalSales += sale;
		updatePremiumStatus();
	}
	
	public void updatePremiumStatus() {
		if (this.totalSales >= UserManagement.THRESHOLD_FOR_PREMIUM_STATUS) {
			setUserType(UserType.PREMIUM_CUSTOMER);
			this.discount = 0.03;
		}
	}
	
	public void updatePremiumStatus(double currentSaleTotal) {
		if (this.totalSales + currentSaleTotal >= UserManagement.THRESHOLD_FOR_PREMIUM_STATUS) {
			setUserType(UserType.PREMIUM_CUSTOMER);
			this.discount = 0.03;
		}
	}
	
	public double getDiscount() {
		return this.discount;
	}
	
	public void setDiscount(double discount) {
		this.discount = discount;
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
