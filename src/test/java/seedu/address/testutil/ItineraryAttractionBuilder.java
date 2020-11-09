package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.attraction.Address;
import seedu.address.model.attraction.Attraction;
import seedu.address.model.attraction.Email;
import seedu.address.model.attraction.Location;
import seedu.address.model.attraction.OpeningHours;
import seedu.address.model.attraction.Phone;
import seedu.address.model.attraction.PriceRange;
import seedu.address.model.attraction.Rating;
import seedu.address.model.attraction.Visited;
import seedu.address.model.commons.Description;
import seedu.address.model.commons.Name;
import seedu.address.model.itinerary.ItineraryAttraction;
import seedu.address.model.itinerary.ItineraryTime;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Itinerary Attraction objects.
 * Depends on ItineraryAttractionBuilder class.
 */
public class ItineraryAttractionBuilder {

    public static final String DEFAULT_NAME = "Tokyo Disneyland";
    public static final String DEFAULT_PHONE = "81453305211";
    public static final String DEFAULT_EMAIL = "contact@tokyodisneyresort.jp";
    public static final String DEFAULT_ADDRESS = "1-1 Maihama, Urayasu, Chiba 279-0031";
    public static final String DEFAULT_DESCRIPTION = "The first Disney park to be built outside the United States.";
    public static final String DEFAULT_LOCATION = "Urayasu, Japan";
    public static final String DEFAULT_OPENING_HOURS = "1000-1800";
    public static final String DEFAULT_PRICE_RANGE = "MEDIUM";
    public static final String DEFAULT_RATING = "4.5";
    public static final String DEFAULT_VISITED = "TRUE";
    public static final String DEFAULT_START_TIME = "1300";
    public static final String DEFAULT_END_TIME = "1500";

    private Name name;
    private Phone phone;
    private Email email;
    private Address address;
    private Description description;
    private Location location;
    private OpeningHours openingHours;
    private PriceRange priceRange;
    private Rating rating;
    private Visited visited;
    private Set<Tag> tags;
    private ItineraryTime startTime;
    private ItineraryTime endTime;

    /**
     * Creates a {@code ItineraryItineraryAttractionBuilder} with the default details.
     */
    public ItineraryAttractionBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        description = new Description(DEFAULT_DESCRIPTION);
        location = new Location(DEFAULT_LOCATION);
        openingHours = new OpeningHours(DEFAULT_OPENING_HOURS);
        priceRange = new PriceRange(DEFAULT_PRICE_RANGE);
        rating = new Rating(DEFAULT_RATING);
        visited = new Visited(DEFAULT_VISITED);
        tags = new HashSet<>();
        startTime = new ItineraryTime(DEFAULT_START_TIME);
        endTime = new ItineraryTime(DEFAULT_END_TIME);
    }

    /**
     * Initializes the ItineraryItineraryAttractionBuilder with the data of {@code iaToCopy}.
     */
    public ItineraryAttractionBuilder(ItineraryAttraction iaToCopy) {
        name = iaToCopy.getName();
        phone = iaToCopy.getPhone();
        email = iaToCopy.getEmail();
        address = iaToCopy.getAddress();
        description = iaToCopy.getDescription();
        location = iaToCopy.getLocation();
        openingHours = iaToCopy.getOpeningHours();
        priceRange = iaToCopy.getPriceRange();
        rating = iaToCopy.getRating();
        visited = iaToCopy.getVisited();
        tags = new HashSet<>(iaToCopy.getTags());
        startTime = iaToCopy.getStartTime();
        endTime = iaToCopy.getEndTime();
    }

    /**
     * Sets the {@code Name} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withPhone() {
        this.phone = new Phone();
        return this;
    }


    /**
     * Sets the {@code Email} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withEmail() {
        this.email = new Email();
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withAddress() {
        this.address = new Address();
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withDescription() {
        this.description = new Description();
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code OpeningHours} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withOpeningHours(String openingHours) {
        this.openingHours = new OpeningHours(openingHours);
        return this;
    }

    /**
     * Sets the {@code OpeningHours} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withOpeningHours() {
        this.openingHours = new OpeningHours();
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withPriceRange(String priceRange) {
        this.priceRange = new PriceRange(priceRange);
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withPriceRange() {
        this.priceRange = new PriceRange();
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withRating() {
        this.rating = new Rating();
        return this;
    }

    /**
     * Sets the {@code Visited} of the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withVisited(String visited) {
        this.visited = new Visited(visited);
        return this;
    }

    /**
     * Sets the {@code Visited} of the {@code Attraction} that we are building to empty string.
     */
    public ItineraryAttractionBuilder withVisited() {
        this.visited = new Visited();
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Attraction} that we are building.
     */
    public ItineraryAttractionBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code startTime} of the {@code ItineraryAttraction} that we are building.
     */
    public ItineraryAttractionBuilder withStartTime(String startTime) {
        this.startTime = new ItineraryTime(startTime);
        return this;
    }

    /**
     * Sets the {@code endTime} of the {@code ItineraryAttraction} that we are building.
     */
    public ItineraryAttractionBuilder withEndTime(String endTime) {
        this.endTime = new ItineraryTime(endTime);
        return this;
    }

    /**
     * Sets the attraction fields in itinerary attraction that we are building.
     */
    public ItineraryAttractionBuilder withAttraction(Attraction attraction) {
        name = attraction.getName();
        phone = attraction.getPhone();
        email = attraction.getEmail();
        address = attraction.getAddress();
        description = attraction.getDescription();
        location = attraction.getLocation();
        openingHours = attraction.getOpeningHours();
        priceRange = attraction.getPriceRange();
        rating = attraction.getRating();
        visited = attraction.getVisited();
        tags = attraction.getTags();
        return this;
    }

    /**
     * Initializes a new itinerary attraction.
     *
     * @return a new Attraction.
     */
    public ItineraryAttraction build() {
        return new ItineraryAttraction(
                new Attraction(name,
                        phone,
                        email,
                        address,
                        description,
                        location,
                        openingHours,
                        priceRange,
                        rating,
                        visited,
                        tags), startTime, endTime);
    }

}
