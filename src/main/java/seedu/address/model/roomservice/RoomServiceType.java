package seedu.address.model.roomservice;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum RoomServiceType {
    WIFI("WIFI", "Wifi service", 15),
    DINING("DINING", "Dining in service", 50),
    MASSAGE("MASSAGE", "Massaging service", 70);

    private final String name;
    private final String verboseName;
    private final Integer price;

    RoomServiceType(String name, String verboseName, Integer price) {
        this.name = name;
        this.verboseName = verboseName;
        this.price = price;
    }

    public String getName() {
        return this.name;
    }

    public String getVerboseName() {
        return this.verboseName;
    }

    public Integer getPrice() {
        return this.price;
    }

    public static String getAllNames() {
        return Arrays.stream(values()).map(RoomServiceType::getName).collect(Collectors.toList()).toString();
    }
}
