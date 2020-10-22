package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.LocationList;
import seedu.address.model.location.Location;

public class TypicalLocations {

    public static final Location NEW_YORK = new LocationBuilder().withName("New York").build();
    public static final Location DENVER = new LocationBuilder().withName("Denver").build();

    private TypicalLocations() {} // prevents instantiation

    /**
     * Returns an {@code LocationList} with all the typical locations.
     */
    public static LocationList getTypicalLocationsList() {
        LocationList locations = new LocationList();
        for (Location location : getTypicalLocations()) {
            locations.addLocation(location);
        }
        return locations;
    }

    public static List<Location> getTypicalLocations() {
        return new ArrayList<>(Arrays.asList(NEW_YORK, DENVER));
    }
}
