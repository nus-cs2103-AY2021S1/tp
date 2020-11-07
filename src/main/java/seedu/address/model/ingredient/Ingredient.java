package seedu.address.model.ingredient;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents ALL ingredient that is tracked by tCheck.
 * OOP development plan : Each ingredient can be extracted out to make the design more OOP.
 */
public class Ingredient {

    public static final String BOBA = "Boba";
    public static final String PEARL = "Pearl";
    public static final String BROWN_SUGAR = "Brown Sugar";

    // Identity field
    private final IngredientName ingredientName;

    // Data field
    private final Amount amount;


    /**
     * Constructs an ingredient with the given amount and ingredient name.
     * Note that the ingredient name can only be one of the static
     * String constant declared in this class.
     *
     * @param amount         an integer representing the level of the ingredient
     * @param ingredientName name of the ingredient
     */
    public Ingredient(IngredientName ingredientName, Amount amount) {
        requireAllNonNull(ingredientName, amount);
        this.amount = amount;
        this.ingredientName = ingredientName;
    }

    /**
     * Constructs an ingredient with the given name.
     * Amount is set to 0 by default.
     *
     * @param ingredientName name of the ingredient
     */
    public Ingredient(IngredientName ingredientName) {
        this.amount = new Amount("0");
        this.ingredientName = ingredientName;
    }

    /**
     * Returns the amount of the ingredient.
     * @return the amount of the ingredient.
     */
    public Amount getAmount() {
        return this.amount;
    }

    /**
     * Returns the ingredient name of the ingredient.
     * @return the ingredient name of the ingredient.
     */
    public IngredientName getIngredientName() {
        return this.ingredientName;
    }

    /**
     * Returns true if a person with the same identity as {@code person} exists in the address book.
     */
    public boolean isSameIngredient(Ingredient otherIngredient) {
        if (otherIngredient == this) {
            return true;
        }

        return otherIngredient != null
                && otherIngredient.getIngredientName().equals(getIngredientName())
                && otherIngredient.getAmount().equals(getAmount());
    }

    /**
     * Returns true if the ingredient is a solid ingredient.
     * @return true if the ingredient is a solid ingredient, false otherwise.
     */
    public boolean isSolidIngredient() {
        Set<String> ingredientNames = new HashSet<>();
        ingredientNames.add(BOBA);
        ingredientNames.add(PEARL);
        ingredientNames.add(BROWN_SUGAR);
        String ingredientName = this.getIngredientNameAsString();

        if (ingredientNames.contains(ingredientName)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the ingredient is in shortage, which is when their levels fall below the default level.
     * @return true if the ingredient is in shortage, false otherwise.
     */
    public boolean isIngredientInShortage() {
        if (this.isSolidIngredient()) {
            return isSolidIngredientInShortage();
        } else {
            return isLiquidIngredientInShortage();
        }
    }

    /**
     * Returns true if the solid ingredient is in shortage.
     * @return true if the solid ingredient is in shortage, false otherwise.
     */
    public boolean isSolidIngredientInShortage() {
        Amount amountOfIngredient = this.getAmount();
        if (amountOfIngredient.isSolidBelowRestockLevel()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns true if the liquid ingredient is in shortage.
     * @return true if the liquid ingredient is in shortage, false otherwise.
     */
    public boolean isLiquidIngredientInShortage() {
        Amount amountOfIngredient = this.getAmount();
        if (amountOfIngredient.isLiquidBelowRestockLevel()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns the amount needed for the ingredient to reach its restock level which is the default level.
     * @return the amount needed for the ingredient to reach its restock level.
     */
    public String amountNeededToReachRestockLevel() {
        Amount amountOfIngredient = this.getAmount();
        if (this.isSolidIngredient()) {
            return amountOfIngredient.solidAmountToReachRestockLevel();
        } else {
            return amountOfIngredient.liquidAmountToReachRestockLevel();
        }
    }

    /**
     * Returns the string format of the ingredient name.
     * @return the string format of the ingredient name.
     */
    public String getIngredientNameAsString() {
        return ingredientName.toString();
    }

    /**
     * Returns true if both ingredients have the same identity and data fields.
     * This defines a stronger notion of equality between two ingredients.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Ingredient)) {
            return false;
        }

        Ingredient otherIngredient = (Ingredient) other;
        return otherIngredient.getIngredientName().equals(getIngredientName())
                && otherIngredient.getAmount().equals(getAmount());
    }

    @Override
    public int hashCode() {
        return Objects.hash(ingredientName, amount);
    }

    @Override
    public String toString() {

        final StringBuilder builder = new StringBuilder();
        builder.append(" Ingredient: ")
                .append(getIngredientName())
                .append(", ")
                .append(" Amount: ")
                .append(getAmount())
                .append("\n");
        return builder.toString();

    }
}
