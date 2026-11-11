package td7.domain.cruise.cabin;

import td7.domain.cruise.cabin.exceptions.InvalidCabinTypeException;

public enum CabinType {
    STANDARD("standard"),
    DELUXE("deluxe"),
    SUITE("suite");

    private final String type;

    CabinType(String type) {
        this.type = type;
    }

    public static CabinType fromString(String type) {
        for (CabinType cabinType : CabinType.values()) {
            if (cabinType.type.equalsIgnoreCase(type)) {
                return cabinType;
            }
        }

        throw new InvalidCabinTypeException();
    }
}
