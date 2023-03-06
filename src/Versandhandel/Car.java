package Versandhandel;
public class Car {
    private static final String CAR_CSV_PATH = "/src/Versandhandel/Car.csv";

	private int productNumber; 
    
    private String productName; 
       
    private double productPrice;
        
    private String productDescription;  
    
    private double tax = 0.19; 
    
    private VehicleClass category;
    
    public Car(int productNumber, String productName, double productPrice, String productDescription, VehicleClass category){
    	this.productNumber = productNumber; 
    	this.productName = productName;
    	this.productPrice = productPrice; 
    	this.productDescription = productDescription; 
        this.category = category;
    }

    public int getProductNumber() {
    	return productNumber; 
    }

    public void setProductNumber(int productNumber) {
    	this.productNumber = productNumber; 
    }
    
    public String getProductName() {
    	return productName;
    }
    
    public void setProductName(String productName) {
    	this.productName = productName; 
    }
    
    public double getProductPrice() {
    	return productPrice; 
    }
    
    
    public void setProductPrice(double productPrice) {
    	this.productPrice = productPrice;
    }
    
    public String getProductDescription() {
    	return productDescription;
    }
    
    public void setProductDescription(String productDescription) {
    	this.productDescription = productDescription;
    }
	
    public double getTax() {
    	return tax;
    }
    
    public void setTax(double tax) {
    	this.tax = tax; 
    }
    
    public VehicleClass getCategory() {
		return category;
	}

	public void setCategory(VehicleClass category) {
		this.category = category;
	}

	public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Modell: \n");
        sb.append(this.productNumber + " " + this.productName + "\n");
        sb.append("Preis:");
        sb.append(this.productPrice + " € \n");
        sb.append("Beschreibung:\n");
        sb.append(this.productDescription + "\n");
        sb.append("Fahrzeugklasse: \n"); 
        sb.append(this.category.category);

        return sb.toString();
    
    }

} 
