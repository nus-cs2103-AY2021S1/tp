package seedu.address.storage;

import java.util.HashMap;

// two way map that links location to it's Id and vice versa
public class LocationStorage {
    private static HashMap<String, Integer> locations = new HashMap<>();
    private static HashMap<Integer, String> locationIds = new HashMap<>();

    public static HashMap<String, Integer> getLocations() {
        return locations;
    }

    public static HashMap<Integer, String> getLocationIds() {
        return locationIds;
    }
}
