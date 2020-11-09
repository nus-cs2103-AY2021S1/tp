package seedu.address.testutil.builders;

import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.commons.Cost;
import seedu.address.model.commons.Location;
import seedu.address.model.commons.Name;
import seedu.address.model.commons.WanderlustDate;

public class AccommodationBuilder {

    public static final String DEFAULT_NAME = "Barry's Farmhouse";
    public static final String DEFAULT_START_DATE = "2021-05-10";
    public static final String DEFAULT_END_DATE = "2021-05-11";
    public static final String DEFAULT_LOCATION = "100 Acre Woods";
    public static final String DEFAULT_COST = "123";


    private Name name;
    private WanderlustDate startDate;
    private WanderlustDate endDate;
    private Location location;
    private Cost cost;

    /**
     * Creates a {@code AccommodationBuilder} with the default details.
     */
    public AccommodationBuilder() {
        name = new Name(DEFAULT_NAME);
        location = new Location(DEFAULT_LOCATION);
        cost = new Cost(DEFAULT_COST);
        startDate = new WanderlustDate(DEFAULT_START_DATE);
        endDate = new WanderlustDate(DEFAULT_END_DATE);
    }

    /**
     * Initializes the AccommodationBuilder with the data of {@code AccommodationToCopy}.
     */
    public AccommodationBuilder(Accommodation accommodationToCopy) {
        name = accommodationToCopy.getName();
        location = accommodationToCopy.getLocation();
        cost = accommodationToCopy.getCost();
        startDate = accommodationToCopy.getStartDate();
        endDate = accommodationToCopy.getEndDate();
    }

    /**
     * Sets the {@code Name} of the {@code Accommodation} that we are building.
     */
    public AccommodationBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code startDate} of the {@code Accommodation} that we are building.
     */
    public AccommodationBuilder withStartDate(String startDate) {
        this.startDate = new WanderlustDate(startDate);
        return this;
    }

    /**
     * Sets the {@code endDate} of the {@code Accommodation} that we are building.
     */
    public AccommodationBuilder withEndDate(String endDate) {
        this.endDate = new WanderlustDate(endDate);
        return this;
    }

    /**
     * Sets the {@code Cost} of the {@code Accommodation} that we are building.
     */
    public AccommodationBuilder withCost(String cost) {
        this.cost = new Cost(cost);
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Accommodation} that we are building.
     */
    public AccommodationBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    public Accommodation build() {
        return new Accommodation(name, startDate, endDate, cost, location);
    }
}
