package seedu.address.model.food;

import static seedu.address.commons.util.AppUtil.checkArgument;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/**
 * Represents a Food item in AddressBook.
 */
public class Food {
    public static final String FOOD_NAME_MESSAGE_CONTRAINT = "Food name can take in any value, and it cannot be blank";

    /*
     * The first character of name must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";

    // Identity field names
    private final String name;
    private final Protein protein;
    private final Carbohydrate carbs;
    private final Fat fat;

    /**
     * Every field must be present and not null.
     */
    public Food(String name, Protein protein, Carbohydrate carbs, Fat fat) {
        requireAllNonNull(name, protein, carbs, fat);
        checkArgument(isValidName(name), FOOD_NAME_MESSAGE_CONTRAINT);
        this.name = name;
        this.protein = protein;
        this.carbs = carbs;
        this.fat = fat;
    }

    // Constructor for convenience

    /**
     * Every field must be present and not null.
     * A Constructor made for convenience
     */
    public Food(String name, int proteinAmount, int carbsAmount, int fatAmount) {
        this(name, new Protein(proteinAmount), new Carbohydrate(carbsAmount), new Fat(fatAmount));
    }

    private boolean isValidName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    // getters : make when needed
    private String getName() {
        return this.name;
    }

    private Protein getProtein() {
        return this.protein;
    }

    private Carbohydrate getCarbs() {
        return this.carbs;
    }

    private Fat getFat() {
        return this.fat;
    }

    @Override
    public boolean equals(Object other) {
        requireAllNonNull(protein, carbs, fat);

        if (other == this) {
            return true;
        }

        if (!(other instanceof Food)) {
            return false;
        }

        Food otherFood = (Food) other;

        return otherFood != null
            && this.getName().equals(otherFood.getName())
            && this.getProtein().equals(otherFood.getProtein())
            && this.getCarbs().equals(otherFood.getCarbs())
            && this.getFat().equals(otherFood.getFat());
    }

    // Displays
    // name + PCF details + total calories
    @Override
    public String toString() {
        return "Food:" + this.getName() + "\n"
            + "protein: " + protein.getAmount() + "\n"
            + "carbs: " + carbs.getAmount() + "\n"
            + "fat: " + fat.getAmount() + "\n";
    }

}
