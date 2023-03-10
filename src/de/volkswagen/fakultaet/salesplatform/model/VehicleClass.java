package de.volkswagen.fakultaet.salesplatform.model;

    public enum VehicleClass { 
    	SMALL ("Kleinwagen"),
    	MIDDLESIZE ("Mittelklasse"),
    	NON_CATEGORY ("Ohne Kategorie"); 
        
        private String category;
        
        private VehicleClass(String category) {
            this.category = category; 
        }

        public static VehicleClass parseVehicleClassFromGermanText(String germanText){
            switch (germanText){
                case "Kleinwagen":
                    return SMALL;
                case "Mittelklasse":
                    return MIDDLESIZE;
                case "Ohne Kategorie":
                    return NON_CATEGORY;
                default:
                    return NON_CATEGORY;
            }
        }
        
        public String getCategory() {
        	return this.category;
        }
    }
