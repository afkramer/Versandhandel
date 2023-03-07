package Versandhandel;

//TODO: double check the logic of how to calculate the net price

public class Invoice {
	private Customer customer;
	private int quantity;
	private double totalPrice;
	private double netPrice;
	private double includedTax;
	private double discountedNetPrice;
	private double discountedIncludedTax;
	private double discountedTotalPrice;
	
	
	public Invoice(Customer customer, Car car, int quantity) {
		this.customer = customer;;
		this.quantity = quantity;
		this.totalPrice = CarManagement.totalPrice(quantity, car);
		applyDiscount();
		this.includedTax = CarManagement.mehrwertsteuer(this.totalPrice, CarManagement.TAX);
		this.netPrice = totalPrice - includedTax;
		updateCustomerData();
		
	}
	
	public void applyDiscount() {
		this.discountedNetPrice = this.netPrice * (1 - customer.getDiscount());
		this.discountedIncludedTax = discountedNetPrice * (1 + CarManagement.TAX);
		this.discountedTotalPrice = discountedNetPrice + discountedIncludedTax;
	}
	
	public void updateCustomerData() {
		customer.addToTotalSales(totalPrice);
	}
	
	public int getQuantity() {
		return this.quantity;
	}
	
	public double getTotalPrice() {
		return this.totalPrice;
	}
	
	public double getNetPrice() {
		return this.netPrice;
	}
	
	public double getIncludedTax() {
		return this.includedTax;
	}

	public double getDiscountedNetPrice() {
		return discountedNetPrice;
	}

	public double getDiscountedIncludedTax() {
		return discountedIncludedTax;
	}

	public double getDiscountedTotalPrice() {
		return discountedTotalPrice;
	}
}