package seedu.simplykitchen.testutil;

import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_DESCRIPTION_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_EXPIRY_DATE_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_APPLE_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_PRIORITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_QUANTITY_APPLY_PIE;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_QUANTITY_BREAD;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_FROZEN;
import static seedu.simplykitchen.logic.commands.CommandTestUtil.VALID_TAG_WHOLEMEAL;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.food.Food;

/**
 * A utility class containing a list of {@code Food} objects to be used in tests.
 */
public class TypicalFood {

    public static final Food ANCHOVIES = new FoodBuilder().withDescription("Anchovies").withPriority("low")
            .withExpiryDate("1-1-2020").withQuantity("1 jar").withTags("raw", "fresh water").build();
    public static final Food BAGEL = new FoodBuilder().withDescription("Bagel").withPriority("low")
            .withExpiryDate("1-2-2020").withQuantity("5").withTags("sugar-free", "cheese").build();
    public static final Food CARROT_CAKE = new FoodBuilder().withDescription("Carrot Cake").withPriority("low")
            .withExpiryDate("1-3-2020").withQuantity("10 slice").build();
    public static final Food DARK_CHOCOLATE = new FoodBuilder().withDescription("Dark Chocolate").withPriority("high")
            .withExpiryDate("1-6-2020").withQuantity("1.5 bar").withTags("85%").build();
    public static final Food EGGS = new FoodBuilder().withDescription("Eggs").withPriority("high")
            .withExpiryDate("1-12-2020").withQuantity("1.25 carton").build();
    public static final Food FRENCH_FRIES = new FoodBuilder().withDescription("French Fries").withPriority("low")
            .withExpiryDate("31-1-2020").withQuantity("1 packet").build();
    public static final Food GINGER = new FoodBuilder().withDescription("Ginger").withPriority("low")
            .withExpiryDate("31-12-2020").withQuantity("200 g").build();

    // Manually added
    public static final Food APPLE = new FoodBuilder().withDescription("apple").withPriority("medium")
            .withExpiryDate("12-12-2120").withQuantity("50 g").build();
    public static final Food APRICOT = new FoodBuilder().withDescription("apricot").withPriority("high")
            .withExpiryDate("12-12-2120").withQuantity("50 g").build();
    public static final Food CHEAP_EGGS = new FoodBuilder().withDescription("Eggs").withPriority("low")
            .withExpiryDate("1-12-2020").withQuantity("1.25 carton").withTags("cheap").build();
    public static final Food HUMMUS = new FoodBuilder().withDescription("Hummus").withPriority("low")
            .withExpiryDate("12-12-2120").withQuantity("1").build();
    public static final Food ICEBERG_LETTUCE = new FoodBuilder().withDescription("Iceberg Lettuce").withPriority("high")
            .withExpiryDate("12-12-2120").withQuantity("125.50 g").build();
    public static final Food OLD_EGGS = new FoodBuilder().withDescription("Eggs").withPriority("high")
            .withExpiryDate("1-1-2020").withQuantity("1.25 carton").build();
    public static final Food WHITE_CHOCOLATE = new FoodBuilder().withDescription("White Chocolate").withPriority("high")
            .withExpiryDate("1-6-2020").withQuantity("1.5 bar").withTags("85%").build();

    // Manually added - for use in QuantityTest
    public static final Food BAKING_SODA = new FoodBuilder().withDescription("baking soda").withPriority("medium")
            .withExpiryDate("06-06-2030").withQuantity("0.02 g").build();
    public static final Food FLOUR = new FoodBuilder().withDescription("flour").withPriority("low")
            .withExpiryDate("06-06-2030").withQuantity("99999.56 g").build();

    // Manually added - Food's details found in {@code CommandTestUtil}
    public static final Food APPLE_PIE = new FoodBuilder().withDescription(VALID_DESCRIPTION_APPLE_PIE)
            .withPriority(VALID_PRIORITY_APPLE_PIE).withExpiryDate(VALID_EXPIRY_DATE_APPLE_PIE)
            .withQuantity(VALID_QUANTITY_APPLY_PIE).withTags(VALID_TAG_FROZEN).build();
    public static final Food BREAD = new FoodBuilder().withDescription(VALID_DESCRIPTION_BREAD)
            .withPriority(VALID_PRIORITY_BREAD).withExpiryDate(VALID_EXPIRY_DATE_BREAD)
            .withQuantity(VALID_QUANTITY_BREAD).withTags(VALID_TAG_WHOLEMEAL, VALID_TAG_FROZEN).build();

    public static final String KEYWORD_MATCHING_LETTUCE = "Lettuce"; // A keyword that matches LETTUCE

    private TypicalFood() {
    } // prevents instantiation

    /**
     * Returns an {@code FoodInventory} with all the typical Foods.
     */
    public static FoodInventory getTypicalFoodInventory() {
        FoodInventory fi = new FoodInventory();
        for (Food food : getTypicalFood()) {
            fi.addFood(food);
        }
        return fi;
    }

    public static List<Food> getTypicalFood() {
        return new ArrayList<>(Arrays.asList(ANCHOVIES, BAGEL, CARROT_CAKE,
                DARK_CHOCOLATE, EGGS, FRENCH_FRIES, GINGER));
    }
}
