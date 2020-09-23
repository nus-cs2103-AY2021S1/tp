package seedu.stock.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.stock.model.stock.Location;
import seedu.stock.model.stock.Quantity;
import seedu.stock.model.stock.Source;
import seedu.stock.model.stock.Name;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Alice Pauline";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "alice@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";

    private Name name;
    private Quantity quantity;
    private Source source;
    private Location location;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        quantity = new Quantity(DEFAULT_PHONE);
        source = new Source(DEFAULT_EMAIL);
        location = new Location(DEFAULT_ADDRESS);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Stock stockToCopy) {
        name = stockToCopy.getName();
        quantity = stockToCopy.getQuantity();
        source = stockToCopy.getSource();
        location = stockToCopy.getLocation();
        tags = new HashSet<>(stockToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public PersonBuilder withAddress(String address) {
        this.location = new Location(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.quantity = new Quantity(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.source = new Source(email);
        return this;
    }

    public Stock build() {
        return new Stock(name, quantity, source, location, tags);
    }

}
