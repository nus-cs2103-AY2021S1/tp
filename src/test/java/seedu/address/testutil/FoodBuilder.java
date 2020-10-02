package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.food.Food;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Food objects.
 */
public class FoodBuilder {

    public static final String DEFAULT_NAME = "Plain Prata";
    public static final double DEFAULT_PRICE = 1.20;
    public static final String DEFAULT_PRICE_STRING = "1.20";


    private String name;
    private double price;
    private Set<Tag> tags;

    /**
     * Creates a {@code FoodBuilder} with the default details.
     */
    public FoodBuilder() {
        this.name = DEFAULT_NAME;
        this.price = DEFAULT_PRICE;
        this.tags = new HashSet<>();
    }

    /**
     * Initializes the FoodBuilder with the data of {@code foodToCopy}.
     */
    public FoodBuilder(Food foodToCopy) {
        name = foodToCopy.getName();
        price = foodToCopy.getPrice();
        tags = new HashSet<>(foodToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Food} that we are building.
     */
    public FoodBuilder withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Sets the {@code Price} of the {@code Food} that we are building.
     */
    public FoodBuilder withPrice(double price) {
        this.price = price;
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Food} that we are building.
     */
    public FoodBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }


    public Food build() {
        return new Food(name, price, tags);
    }

}
