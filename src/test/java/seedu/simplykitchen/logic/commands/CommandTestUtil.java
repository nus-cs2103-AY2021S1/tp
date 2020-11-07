package seedu.simplykitchen.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_AMOUNT;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_EXPIRY_DATE;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.simplykitchen.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.simplykitchen.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.simplykitchen.commons.core.index.Index;
import seedu.simplykitchen.logic.commands.exceptions.CommandException;
import seedu.simplykitchen.model.FoodInventory;
import seedu.simplykitchen.model.Model;
import seedu.simplykitchen.model.food.DescriptionContainsKeywordsPredicate;
import seedu.simplykitchen.model.food.Food;
import seedu.simplykitchen.testutil.EditFoodDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {
    public static final String VALID_DESCRIPTION_APPLE_PIE = "Apple Pie";
    public static final String VALID_DESCRIPTION_BREAD = "Bread";
    public static final String VALID_PRIORITY_APPLE_PIE = "high";
    public static final String VALID_PRIORITY_BREAD = "medium";
    public static final String VALID_EXPIRY_DATE_APPLE_PIE = "1-1-2021";
    public static final String VALID_EXPIRY_DATE_BREAD = "31-12-2021";
    public static final String VALID_QUANTITY_APPLY_PIE = "1.5 pie";
    public static final String VALID_QUANTITY_BREAD = "2";
    public static final String VALID_AMOUNT_APPLE_PIE = "+1";
    public static final String VALID_AMOUNT_BREAD = "-0.25";
    public static final String VALID_TAG_FROZEN = "Frozen";
    public static final String VALID_TAG_WHOLEMEAL = "Wholemeal";

    public static final String DESCRIPTION_DESC_APPLE_PIE = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_APPLE_PIE;
    public static final String DESCRIPTION_DESC_BREAD = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_BREAD;
    public static final String PRIORITY_DESC_APPLE_PIE = " " + PREFIX_PRIORITY + VALID_PRIORITY_APPLE_PIE;
    public static final String PRIORITY_DESC_BREAD = " " + PREFIX_PRIORITY + VALID_PRIORITY_BREAD;
    public static final String EXPIRY_DATE_DESC_APPLE_PIE = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_APPLE_PIE;
    public static final String EXPIRY_DATE_DESC_BREAD = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_BREAD;
    public static final String QUANTITY_DESC_APPLE_PIE = " " + PREFIX_QUANTITY + VALID_QUANTITY_APPLY_PIE;
    public static final String QUANTITY_DESC_BREAD = " " + PREFIX_QUANTITY + VALID_QUANTITY_BREAD;
    public static final String AMOUNT_DESC_APPLE_PIE = " " + PREFIX_AMOUNT + VALID_AMOUNT_APPLE_PIE;
    public static final String AMOUNT_DESC_BREAD = " " + PREFIX_AMOUNT + VALID_AMOUNT_BREAD;
    public static final String TAG_DESC_FROZEN = " " + PREFIX_TAG + VALID_TAG_FROZEN;
    public static final String TAG_DESC_WHOLEMEAL = " " + PREFIX_TAG + VALID_TAG_WHOLEMEAL;

    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION
            + "D&nut"; // '&' not allowed in descriptions
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "lower"; // 'lower' not valid priority
    public static final String INVALID_EXPIRY_DATE_DESC = " " + PREFIX_EXPIRY_DATE + "1--1-2020"; // extra dash
    public static final String INVALID_QUANTITY_UNIT = " " + PREFIX_QUANTITY + "1 @@"; // only alphabets allowed
    public static final String INVALID_QUANTITY_VALUE = " " + PREFIX_QUANTITY + "-1"; // only positive numbers allowed
    public static final String INVALID_QUANTITY_ZERO_VALUE = " " + PREFIX_QUANTITY
            + "0 UNIT"; // only positive numbers allowed
    public static final String INVALID_AMOUNT_DESC = " " + PREFIX_AMOUNT + "+1.234"; // more than 2 decimal places
    public static final String INVALID_AMOUNT_SIZE_DESC = " " + PREFIX_AMOUNT + "+"
            + Integer.MAX_VALUE; // amount size too big
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "frozen?"; // '?' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditFoodDescriptor DESC_APPLE_PIE;
    public static final EditCommand.EditFoodDescriptor DESC_BREAD;

    static {
        DESC_APPLE_PIE = new EditFoodDescriptorBuilder().withDescription(VALID_DESCRIPTION_APPLE_PIE)
                .withPriority(VALID_PRIORITY_APPLE_PIE).withExpiryDate(VALID_EXPIRY_DATE_APPLE_PIE)
                .withQuantity(VALID_QUANTITY_APPLY_PIE).withTags(VALID_TAG_FROZEN).build();
        DESC_BREAD = new EditFoodDescriptorBuilder().withDescription(VALID_DESCRIPTION_BREAD)
                .withPriority(VALID_PRIORITY_BREAD).withExpiryDate(VALID_EXPIRY_DATE_BREAD)
                .withQuantity(VALID_QUANTITY_BREAD).withTags(VALID_TAG_WHOLEMEAL, VALID_TAG_WHOLEMEAL).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the SimplyKitchen inventory, filtered food list and selected food in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        FoodInventory expectedFoodInventory =
                new FoodInventory(actualModel.getFoodInventory());
        List<Food> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFoodList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedFoodInventory, actualModel.getFoodInventory());
        assertEquals(expectedFilteredList, actualModel.getFilteredFoodList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the food at the given {@code targetIndex} in the
     * {@code model}'s food inventory.
     */
    public static void showFoodAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFoodList().size());

        Food food = model.getFilteredFoodList().get(targetIndex.getZeroBased());
        final String[] splitDescription = food.getDescription().fullDescription.split("\\s+");
        model.updateFilteredFoodList(new DescriptionContainsKeywordsPredicate(Arrays.asList(splitDescription[0])));

        assertEquals(1, model.getFilteredFoodList().size());
    }

    /**
     * Deletes the first food in {@code model}'s filtered list from {@code model}'s food inventory.
     */
    public static void deleteFirstFood(Model model) {
        Food firstFood = model.getFilteredFoodList().get(0);
        model.deleteFood(firstFood);
        model.commitFoodInventory();
    }
}
