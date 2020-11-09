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
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Attraction objects.
 */
public class AttractionBuilder {

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

    /**
     * Creates a {@code AttractionBuilder} with the default details.
     */
    public AttractionBuilder() {
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
    }

    /**
     * Initializes the AttractionBuilder with the data of {@code attractionToCopy}.
     */
    public AttractionBuilder(Attraction attractionToCopy) {
        name = attractionToCopy.getName();
        phone = attractionToCopy.getPhone();
        email = attractionToCopy.getEmail();
        address = attractionToCopy.getAddress();
        description = attractionToCopy.getDescription();
        location = attractionToCopy.getLocation();
        openingHours = attractionToCopy.getOpeningHours();
        priceRange = attractionToCopy.getPriceRange();
        rating = attractionToCopy.getRating();
        visited = attractionToCopy.getVisited();
        tags = new HashSet<>(attractionToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Attraction} that we are building to empty string.
     */
    public AttractionBuilder withPhone() {
        this.phone = new Phone();
        return this;
    }


    /**
     * Sets the {@code Email} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Attraction} that we are building to empty string.
     */
    public AttractionBuilder withEmail() {
        this.email = new Email();
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Attraction} that we are building to empty string.
     */
    public AttractionBuilder withAddress() {
        this.address = new Address();
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Attraction} that we are building to empty string.
     */
    public AttractionBuilder withDescription() {
        this.description = new Description();
        return this;
    }

    /**
     * Sets the {@code Location} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withLocation(String location) {
        this.location = new Location(location);
        return this;
    }

    /**
     * Sets the {@code OpeningHours} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withOpeningHours(String openingHours) {
        this.openingHours = new OpeningHours(openingHours);
        return this;
    }

    /**
     * Sets the {@code OpeningHours} of the {@code Attraction} that we are building to empty string.
     */
    public AttractionBuilder withOpeningHours() {
        this.openingHours = new OpeningHours();
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withPriceRange(String priceRange) {
        this.priceRange = new PriceRange(priceRange);
        return this;
    }

    /**
     * Sets the {@code PriceRange} of the {@code Attraction} that we are building to empty string.
     */
    public AttractionBuilder withPriceRange() {
        this.priceRange = new PriceRange();
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withRating(String rating) {
        this.rating = new Rating(rating);
        return this;
    }

    /**
     * Sets the {@code Rating} of the {@code Attraction} that we are building to empty string.
     */
    public AttractionBuilder withRating() {
        this.rating = new Rating();
        return this;
    }

    /**
     * Sets the {@code Visited} of the {@code Attraction} that we are building.
     */
    public AttractionBuilder withVisited(String visited) {
        this.visited = new Visited(visited);
        return this;
    }

    /**
     * Sets the {@code Visited} of the {@code Attraction} that we are building to empty string.
     */
    public AttractionBuilder withVisited() {
        this.visited = new Visited();
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Attraction} that we are building.
     */
    public AttractionBuilder withTags(String... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Initializes a new attraction.
     *
     * @return a new Attraction.
     */
    public Attraction build() {
        return new Attraction(name, phone, email, address, description, location,
                openingHours, priceRange, rating, visited, tags);
    }

}
