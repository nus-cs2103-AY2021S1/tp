package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.item.*;
import seedu.address.model.item.Quantity;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class ItemBuilder {

    public static final String DEFAULT_NAME = "Chicken";
    public static final String DEFAULT_QUANTITY = "2";
    public static final String DEFAULT_SUPPLIER = "NTUC";

    private Name name;
    private Quantity quantity;
    private Supplier supplier;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public ItemBuilder() {
        name = new Name(DEFAULT_NAME);
        quantity = new Quantity(DEFAULT_QUANTITY);
        supplier = new Supplier(DEFAULT_SUPPLIER);
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public ItemBuilder(Item itemToCopy) {
        name = itemToCopy.getName();
        quantity = itemToCopy.getQuantity();
        supplier = itemToCopy.getSupplier();
        tags = new HashSet<>(itemToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public ItemBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public ItemBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public ItemBuilder withSupplier(String address) {
        this.supplier = new Supplier(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public ItemBuilder withQuantity(String phone) {
        this.quantity = new Quantity(phone);
        return this;
    }

    public Item build() {
        return new Item(name, quantity, supplier, tags);
    }

}
