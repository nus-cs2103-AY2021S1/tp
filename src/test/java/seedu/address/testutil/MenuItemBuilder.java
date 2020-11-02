package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.food.MenuItem;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Food objects.
 */
public class MenuItemBuilder {

    public static final String DEFAULT_NAME = "Plain Prata";
    public static final double DEFAULT_PRICE = 1.20;
    public static final String DEFAULT_PRICE_STRING = "1.20";


    private String name;
    private double price;
    private Set<Tag> tags;
    private String filePath;

    /**
     * Creates a {@code FoodBuilder} with the default details.
     */
    public MenuItemBuilder() {
        this.name = DEFAULT_NAME;
        this.price = DEFAULT_PRICE;
        this.tags = new HashSet<>();
        this.filePath = "";
    }

    /**
     * Initializes the MenuItemBuilder with the data of {@code itemToCopy}.
     */
    public MenuItemBuilder(MenuItem itemToCopy) {
        name = itemToCopy.getName();
        price = itemToCopy.getPrice();
        tags = new HashSet<>(itemToCopy.getTags());
        filePath = itemToCopy.getFilePath();
    }

    /**
     * Sets the {@code Name} of the {@code MenuItem} that we are building.
     */
    public MenuItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code MenuItem} that we are building.
     */
    public MenuItemBuilder withPrice(double price) {
        this.price = price;
        return this;
    }


    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code MenuItem} that we are building.
     */
    public MenuItemBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public MenuItem build() {
        return new MenuItem(name, price, tags, filePath);
    }

}
