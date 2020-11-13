package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Expenditure;
import seedu.address.model.expenditure.Name;
import seedu.address.model.expenditure.Price;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Expenditure objects.
 */
public class ExpenditureBuilder {
    public static final String DEFAULT_NAME = "BREAKFAST";
    public static final String DEFAULT_PRICE = "12.50";
    public static final String DEFAULT_CREATED_ON = "2020-10-02T00:00:00.000000";

    private Name name;
    private Price price;
    private Date createdOn;
    private Set<Tag> tags;

    /**
     * Creates a {@code ExpenditureBuilder} with the default details.
     */
    public ExpenditureBuilder() {
        name = new Name(DEFAULT_NAME);
        price = new Price(DEFAULT_PRICE);
        createdOn = new Date(DEFAULT_CREATED_ON);
        tags = new HashSet<>();
    }

    /**
     * Creates a {@code ExpenditureBuilder} with the data of {@code expenditureToCopy}.
     */
    public ExpenditureBuilder(Expenditure expenditureToCopy) {
        name = expenditureToCopy.getName();
        price = expenditureToCopy.getPrice();
        createdOn = expenditureToCopy.getCreatedOn();
        tags = new HashSet<>(expenditureToCopy.getTags());
    }

    /**
     * Sets the {@code name} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code price} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withPrice(String price) {
        this.price = new Price(price);
        return this;
    }

    /**
     * Sets the {@code createdOn} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withCreatedOn(String createdOn) {
        this.createdOn = new Date(createdOn);
        return this;
    }

    /**
     * Sets the {@code tags} of the {@code Expenditure} that we are building.
     */
    public ExpenditureBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Expenditure build() {
        return new Expenditure(name, price, createdOn, tags);
    }
}
