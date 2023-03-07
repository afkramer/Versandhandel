package Versandhandel;

public enum UserType {
	CUSTOMER("Kunde"),
	PREMIUM_CUSTOMER("Premium Kunde"),
	ADMINISTRATOR("Administrator");
	
	String germanText;
	
	private UserType(String germanText) {
		this.germanText = germanText;
	}
	
	public static UserType parseUserTypeFromGermanText(String germanText) {
		switch(germanText) {
		case "Kunde":
			return CUSTOMER;
		case "Premium Kunde":
			return PREMIUM_CUSTOMER;
		case "Administrator":
			return ADMINISTRATOR;
		default:
			return CUSTOMER;
		}
	}
}
