package chopchop.testutil;

import chopchop.model.ingredient.Ingredient;
import chopchop.model.attributes.ExpiryDate;
import chopchop.model.attributes.Name;
import chopchop.model.attributes.Quantity;

public class IngredientBuilder {

    public static final String DEFAULT_NAME = "Egg";
    public static final int DEFAULT_QTY = 3;
    public static final String DEFAULT_EXPIRY = "2020-10-12 15:13";

    private Name name;
    private Quantity qty;
    private ExpiryDate expDate;


    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public IngredientBuilder() {
        name = new Name(DEFAULT_NAME);
        qty = new Quantity(DEFAULT_QTY);
        expDate = new ExpiryDate(DEFAULT_EXPIRY);
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public IngredientBuilder(Ingredient personToCopy) {
        name = personToCopy.getName();
        qty = personToCopy.getQuantity();
        expDate = personToCopy.getExpiryDate();

    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public IngredientBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public IngredientBuilder withQuantity(int qty) {
        this.qty = new Quantity(qty);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public IngredientBuilder withDate(String date) {
        this.expDate = new ExpiryDate(date);
        return this;
    }

    public Ingredient build() {
        return new Ingredient(name, qty, expDate);
    }

}
