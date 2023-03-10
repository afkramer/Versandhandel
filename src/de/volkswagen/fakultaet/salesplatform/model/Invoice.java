package de.volkswagen.fakultaet.salesplatform.model;

import de.volkswagen.fakultaet.salesplatform.service.CarManagement;

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
		this.customer = customer;
		this.quantity = quantity;
		this.totalPrice = CarManagement.calculateTotalPrice(quantity, car);
		this.includedTax = CarManagement.calculateIncludedTax(this.totalPrice);
		this.netPrice = totalPrice - includedTax;
		customer.updatePremiumStatus(this.netPrice);
		applyDiscount();
		customer.addToTotalSales(discountedNetPrice); 
	}
	
	public void applyDiscount() {
		this.discountedNetPrice = this.netPrice * (1 - customer.getDiscount());
		this.discountedIncludedTax = this.includedTax * (1 - customer.getDiscount());
		this.discountedTotalPrice = discountedNetPrice + discountedIncludedTax;
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
