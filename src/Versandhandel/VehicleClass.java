package Versandhandel;

    public enum VehicleClass { 
    	SMALL ("Kleinwagen"),
    	MIDDLESIZE ("Mittelklasse"),
    	NON_CATEGORY ("Ohne Kategorie"); 
        
        String category;
        
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
    }
