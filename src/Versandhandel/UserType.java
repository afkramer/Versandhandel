package Versandhandel;

public enum UserType {
	CUSTOMER("Kunde"),
	PREMIUM_CUSTOMER("Premium Kunde"),
	ADMINISTRATOR("Administrator");
	
	String germanText;
	
	private UserType(String germanText) {
		this.germanText = germanText;
	}
}
