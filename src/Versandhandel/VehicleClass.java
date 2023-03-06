package Versandhandel;

    public enum VehicleClass { 
    	SMALL ("Kleinwagen"),
    	MIDDLESIZE ("Mittelklasse"),
    	NON_CATEGORY ("Ohne Kategorie"); 
        
        String category;
        
        private VehicleClass(String category) {
            this.category = category; 
        }
    }
