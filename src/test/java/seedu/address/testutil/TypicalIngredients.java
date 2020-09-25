package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.INGREDIENT_DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.WishfulShrinking;
import seedu.address.model.recipe.Ingredient;

/**
 * A utility class containing a list of {@code Ingredient} objects to be used in tests.
 */
public class TypicalIngredients {

    public static final Ingredient ALICE = new IngredientBuilder().withValue("Alice Pauline").build();
    public static final Ingredient BENSON = new IngredientBuilder().withValue("Benson Meier").build();
    public static final Ingredient CARL = new IngredientBuilder().withValue("Carl Kurz").build();
    public static final Ingredient DANIEL = new IngredientBuilder().withValue("Daniel Meier").build();
    public static final Ingredient ELLE = new IngredientBuilder().withValue("Elle Meyer").build();
    public static final Ingredient FIONA = new IngredientBuilder().withValue("Fiona Kunz").build();
    public static final Ingredient GEORGE = new IngredientBuilder().withValue("George Best").build();

    // Manually added
    public static final Ingredient HOON = new IngredientBuilder().withValue("Hoon Meier").build();
    public static final Ingredient IDA = new IngredientBuilder().withValue("Ida Mueller").build();

    // Manually added - Ingredient's details found in {@code CommandTestUtil}
    public static final Ingredient AMY = new IngredientBuilder().withValue(VALID_NAME_AMY).build();
    public static final Ingredient BOB = new IngredientBuilder().withValue(INGREDIENT_DESC_BOB).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalIngredients() {} // prevents instantiation

    /**
     * Returns an {@code WishfulShrinking} with all the typical ingredients.
     */
    public static WishfulShrinking getTypicalWishfulShrinking() {
        WishfulShrinking ab = new WishfulShrinking();
        for (Ingredient ingredient : getTypicalIngredients()) {
            ab.addIngredient(ingredient);
        }
        return ab;
    }

    public static List<Ingredient> getTypicalIngredients() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
