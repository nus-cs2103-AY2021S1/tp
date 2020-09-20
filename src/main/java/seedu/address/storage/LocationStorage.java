package seedu.address.storage;

import java.util.HashMap;

public class LocationStorage {
    private static HashMap<String, Integer> locations = new HashMap<>();

    public static HashMap<String, Integer> getLocations() {
        return locations;
    }
}
