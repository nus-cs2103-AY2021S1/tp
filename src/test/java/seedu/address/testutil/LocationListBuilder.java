package seedu.address.testutil;

import seedu.address.model.LocationList;
import seedu.address.model.location.Location;

/**
 * A utility class to help with building LocationList objects.
 * Example usage: <br>
 *     {@code LocationList ab = new LocationListBuilder().withLocation("City").build();}
 */
public class LocationListBuilder {

    private LocationList locationList;

    public LocationListBuilder() {
        locationList = new LocationList();
    }

    public LocationListBuilder(LocationList locationList) {
        this.locationList = locationList;
    }

    /**
     * Adds a new {@code Location} to the {@code LocationList} that we are building.
     */
    public LocationListBuilder withLocation(Location location) {
        locationList.addLocation(new LocationBuilder(location).build());
        return this;
    }

    public LocationList build() {
        return locationList;
    }

}
