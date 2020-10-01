package seedu.address.testutil;

import seedu.address.model.location.Location;

/**
 * A utility class to help with building Location objects.
 */
public class LocationBuilder {

    public static final String DEFAULT_NAME = "SEATTLE";

    private String name;

    /**
     * Creates a {@code LocationBuilder} with the default details.
     */
    public LocationBuilder() {
        this.name = DEFAULT_NAME;
    }

    /**
     * Initializes the LocationBuilder with the data of {@code locationToCopy}.
     */
    public LocationBuilder(Location locationToCopy) {
        name = locationToCopy.getName();
    }

    /**
     * Sets the {@code Name} of the {@code Location} that we are building.
     */
    public LocationBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Builds a location.
     *
     * @return a sample Location
     */
    public Location build() {
        return new Location(name);
    }
}
