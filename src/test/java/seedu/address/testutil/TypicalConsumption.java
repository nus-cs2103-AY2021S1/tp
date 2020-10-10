package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CALORIES_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_INGREDIENT_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_QUANTITY_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.WishfulShrinking;
import seedu.address.model.consumption.Consumption;
import seedu.address.model.recipe.Recipe;

public class TypicalConsumption {
    // Recipe
    public static final Recipe ALICE_RECIPE = new RecipeBuilder().withName("Alice Pauline")
            .withIngredient("94351253", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();
    public static final Recipe BENSON_RECIPE = new RecipeBuilder().withName("Benson Meier")
            .withIngredient("98765432", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();
    public static final Recipe CARL_RECIPE = new RecipeBuilder().withName("Carl Kurz")
            .withIngredient("95352563", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();
    public static final Recipe DANIEL_RECIPE = new RecipeBuilder().withName("Daniel Meier")
            .withIngredient("87652533", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();
    public static final Recipe ELLE_RECIPE = new RecipeBuilder().withName("Elle Meyer")
            .withIngredient("9482224", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();
    public static final Recipe FIONA_RECIPE = new RecipeBuilder().withName("Fiona Kunz")
            .withIngredient("9482427", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();
    public static final Recipe GEORGE_RECIPE = new RecipeBuilder().withName("George Best")
            .withIngredient("9482442", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();

    // Manually added
    public static final Recipe HOON_RECIPE = new RecipeBuilder().withName("Hoon Meier")
            .withIngredient("8482424", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();
    public static final Recipe IDA_RECIPE = new RecipeBuilder().withName("Ida Mueller")
            .withIngredient("8482131", "1 cup")
            .withCalories(10).withInstruction("instruction").withRecipeImage("images/healthy1").withTags("healthy")
            .build();

    // Manually added - Recipe's details found in {@code CommandTestUtil}
    public static final Recipe AMY_RECIPE = new RecipeBuilder().withName(VALID_NAME_AMY)
            .withIngredient(VALID_INGREDIENT_AMY, VALID_QUANTITY_AMY)
            .withCalories(VALID_CALORIES_AMY).withTags(VALID_TAG_AMY).build();
    public static final Recipe BOB_RECIPE = new RecipeBuilder().withName(VALID_NAME_BOB)
            .withIngredient(VALID_INGREDIENT_BOB, VALID_QUANTITY_BOB)
            .withCalories(VALID_CALORIES_BOB).withTags(VALID_TAG_BOB).build();

    public static final Consumption ALICE = new ConsumptionBuilder().withRecipe(ALICE_RECIPE).build();
    public static final Consumption BENSON = new ConsumptionBuilder().withRecipe(BENSON_RECIPE).build();
    public static final Consumption CARL = new ConsumptionBuilder().withRecipe(CARL_RECIPE).build();
    public static final Consumption DANIEL = new ConsumptionBuilder().withRecipe(DANIEL_RECIPE).build();
    public static final Consumption ELLE = new ConsumptionBuilder().withRecipe(ELLE_RECIPE).build();
    public static final Consumption FIONA = new ConsumptionBuilder().withRecipe(FIONA_RECIPE).build();
    public static final Consumption GEORGE = new ConsumptionBuilder().withRecipe(GEORGE_RECIPE).build();

    // Manually added
    public static final Consumption HOON = new ConsumptionBuilder().withRecipe(HOON_RECIPE).build();
    public static final Consumption IDA = new ConsumptionBuilder().withRecipe(IDA_RECIPE).build();

    // Manually added - Consumption's details found in {@code CommandTestUtil}
    public static final Consumption AMY = new ConsumptionBuilder().withRecipe(AMY_RECIPE).build();
    public static final Consumption BOB = new ConsumptionBuilder().withRecipe(BOB_RECIPE).build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalConsumption() {} // prevents instantiation

    /**
     * Returns an {@code WishfulShrinking} with all the typical consumption.
     */
    public static WishfulShrinking getTypicalWishfulShrinking() {
        WishfulShrinking ab = new WishfulShrinking();
        for (Consumption consumption : getTypicalConsumption()) {
            ab.addConsumption(consumption);
        }
        return ab;
    }

    public static List<Consumption> getTypicalConsumption() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
