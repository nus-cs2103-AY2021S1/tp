package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_LOCATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ITEM_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_INGREDIENTS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECIPE_PRODUCT_QUANTITY;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ItemList;
import seedu.address.model.LocationList;
import seedu.address.model.Model;
import seedu.address.model.RecipeList;
import seedu.address.model.item.Item;
import seedu.address.model.location.Location;
import seedu.address.model.recipe.Recipe;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    //Delimiters
    public static final String DELIMITER_1 = ",";
    public static final String DELIMITER_2 = ", ";
    public static final String DELIMITER_3 = " , ";

    // items
    public static final String VALID_ITEM_NAME_APPLE = "Apple";
    public static final String VALID_ITEM_NAME_BANANA = "Banana";
    public static final String VALID_ITEM_QUANTITY_APPLE = "9";
    public static final String VALID_ITEM_QUANTITY_BANANA = "99";
    public static final String VALID_ITEM_DESCRIPTION_APPLE = "Recovers 10 hp";
    public static final String VALID_ITEM_DESCRIPTION_BANANA = "Used as bait";
    public static final String VALID_ITEM_LOCATION_PEACH_ORCHARD = "Bob's peach orchard";
    public static final String VALID_ITEM_LOCATION_SPINACH_GARDEN = "Bob's spinach garden";
    public static final String VALID_ITEM_LOCATIONS_DELIMITER_1 = VALID_ITEM_LOCATION_PEACH_ORCHARD
            + DELIMITER_1 + VALID_ITEM_LOCATION_SPINACH_GARDEN;
    public static final String VALID_ITEM_LOCATIONS_DELIMITER_2 = VALID_ITEM_LOCATION_PEACH_ORCHARD
            + DELIMITER_2 + VALID_ITEM_LOCATION_SPINACH_GARDEN;
    public static final String VALID_ITEM_LOCATIONS_DELIMITER_3 = VALID_ITEM_LOCATION_PEACH_ORCHARD
            + DELIMITER_3 + VALID_ITEM_LOCATION_SPINACH_GARDEN;

    public static final String VALID_TAG_BERT = "bertmodel";
    public static final String VALID_TAG_TUTURU = "tuturu";
    public static final String VALID_TAG_ASD = "asd";
    public static final String VALID_TAG_ABC = "abc";
    public static final String VALID_TOKENIZABLE_TAGS = VALID_TAG_ASD
            + DELIMITER_1 + VALID_TAG_TUTURU + DELIMITER_3 + VALID_TAG_BERT + DELIMITER_2 + VALID_TAG_ABC;

    public static final String VALID_ITEM_QUANTITY_ORIGINAL = "50";
    public static final int VALID_QUANTITY_INT = 100;
    public static final int VALID_QUANTITY_INT_ALT = 200;
    public static final int VALID_QUANTITY_INCREMENT = 10;
    public static final String VALID_INCREASED_QUANTITY = "60";
    public static final int VALID_QUANTITY_DECREMENT = -50;
    public static final String VALID_DECREASED_QUANTITY = "0";
    public static final int INVALID_QUANTITY_DECREMENT = -51;

    // recipe
    public static final String VALID_RECIPE_PRODUCT_NAME_APPLE_PIE = "Apple Pie";
    public static final String VALID_RECIPE_PRODUCT_NAME_BANANA_PIE = "Banana Pie";
    public static final int VALID_RECIPE_ID_TWO = 2;
    public static final String VALID_RECIPE_QUANTITY_APPLE_PIE = "1";
    public static final String VALID_RECIPE_QUANTITY_APPLE_PIE_ALTERNATE = "2";
    public static final String VALID_RECIPE_QUANTITY_BANANA_PIE = "3";
    public static final String VALID_RECIPE_DESC_APPLE_PIE = "Apple-y!";
    public static final String VALID_RECIPE_DESC_APPLE_PIE_ALTERNATE = "Not Apple-y!";
    public static final String VALID_RECIPE_DESC_BANANA_PIE = "Banana-y!";
    public static final String VALID_RECIPE_INGREDIENTS_APPLE_PIE = "Apple[1] Banana[2]";
    public static final String VALID_RECIPE_INGREDIENTS_BANANA_PIE = "Apple[2] Banana[1]";
    public static final String INVALID_RECIPE_QUANTITY_APPLE_PIE = "-1";

    //prefix
    public static final String INVALID_NAME_PREFIX = " --n";

    // Command parts
    public static final String ITEM_NAME_DESC_APPLE = " "
            + PREFIX_ITEM_NAME + VALID_ITEM_NAME_APPLE;
    public static final String ITEM_NAME_DESC_BANANA = " "
            + PREFIX_ITEM_NAME + VALID_ITEM_NAME_BANANA;
    public static final String ITEM_QUANTITY_DESC_APPLE = " "
            + PREFIX_ITEM_QUANTITY + VALID_ITEM_QUANTITY_APPLE;
    public static final String ITEM_QUANTITY_DESC_BANANA = " "
            + PREFIX_ITEM_QUANTITY + VALID_ITEM_QUANTITY_BANANA;
    public static final String ITEM_DESCRIPTION_DESC_APPLE = " "
            + PREFIX_ITEM_DESCRIPTION + VALID_ITEM_DESCRIPTION_APPLE;
    public static final String ITEM_DESCRIPTION_DESC_BANANA = " "
            + PREFIX_ITEM_DESCRIPTION + VALID_ITEM_DESCRIPTION_BANANA;
    public static final String ITEM_LOCATION_DESC_PEACH_ORCHARD = " "
            + PREFIX_ITEM_LOCATION + VALID_ITEM_LOCATION_PEACH_ORCHARD;
    public static final String ITEM_LOCATION_DESC_SPINACH_GARDEN = " "
            + PREFIX_ITEM_LOCATION + VALID_ITEM_LOCATION_SPINACH_GARDEN;
    public static final String ITEM_LOCATION_DESCS_1 = " "
            + PREFIX_ITEM_LOCATION + VALID_ITEM_LOCATIONS_DELIMITER_1;
    public static final String ITEM_LOCATION_DESCS_2 = " "
            + PREFIX_ITEM_LOCATION + VALID_ITEM_LOCATIONS_DELIMITER_3;
    public static final String ITEM_LOCATION_DESCS_3 = " "
            + PREFIX_ITEM_LOCATION + VALID_ITEM_LOCATIONS_DELIMITER_3;
    public static final String ITEM_TAG_MULTIPARSE = " "
            + PREFIX_ITEM_TAG + VALID_TOKENIZABLE_TAGS;
    public static final String ITEM_TAG_BERT = " "
            + PREFIX_ITEM_TAG + VALID_TAG_BERT;



    public static final String RECIPE_PRODUCT_NAME_APPLE_PIE = " "
            + PREFIX_RECIPE_PRODUCT_NAME + VALID_RECIPE_PRODUCT_NAME_APPLE_PIE;
    public static final String RECIPE_PRODUCT_NAME_BANANA_PIE = " "
            + PREFIX_RECIPE_PRODUCT_NAME + VALID_RECIPE_PRODUCT_NAME_BANANA_PIE;
    public static final String RECIPE_QUANTITY_APPLE_PIE = " "
            + PREFIX_RECIPE_PRODUCT_QUANTITY + VALID_RECIPE_QUANTITY_APPLE_PIE;
    public static final String RECIPE_QUANTITY_BANANA_PIE = " "
            + PREFIX_RECIPE_PRODUCT_QUANTITY + VALID_RECIPE_QUANTITY_BANANA_PIE;
    public static final String RECIPE_DESCRIPTION_APPLE_PIE = " "
            + PREFIX_RECIPE_DESCRIPTION + VALID_RECIPE_DESC_APPLE_PIE;
    public static final String RECIPE_DESCRIPTION_BANANA_PIE = " "
            + PREFIX_RECIPE_DESCRIPTION + VALID_RECIPE_DESC_BANANA_PIE;
    public static final String RECIPE_INGREDIENTS_APPLE_PIE = " "
            + PREFIX_RECIPE_INGREDIENTS + VALID_RECIPE_INGREDIENTS_APPLE_PIE;
    public static final String RECIPE_INGREDIENTS_BANANA_PIE = " "
            + PREFIX_RECIPE_INGREDIENTS + VALID_RECIPE_INGREDIENTS_BANANA_PIE;
    public static final String INVALID_RECIPE_QUANTITY = " "
            + PREFIX_RECIPE_PRODUCT_QUANTITY + INVALID_RECIPE_QUANTITY_APPLE_PIE;

    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_ITEM_QUANTITY + "9a"; // 'a' not allowed in quantity

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
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
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Version for InventoryListCommands
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the item list, filtered item list, recipe list, filtered recipe list,
     *   location list, filtered location list {@code actualModel} remain unchanged
     * @param command Command to execute
     * @param actualModel the actual model
     * @param expectedMessage expected error message from execution
     */
    public static void assertInventoryCommandFailure(Command command, Model actualModel, String expectedMessage) {
        ItemList expectedItemList = new ItemList(actualModel.getItemList());
        List<Item> expectedFilteredItemList = new ArrayList<>(actualModel.getFilteredItemList());
        RecipeList expectedRecipeList = new RecipeList(actualModel.getRecipeList());
        List<Recipe> expectedFilteredRecipeList = new ArrayList<>(actualModel.getFilteredRecipeList());
        LocationList expectedLocationList = new LocationList(actualModel.getLocationList());
        List<Location> expectedFilteredLocationList = new ArrayList<>(actualModel.getFilteredLocationList());
        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedItemList, actualModel.getItemList());
        assertEquals(expectedFilteredItemList, actualModel.getFilteredItemList());
        assertEquals(expectedRecipeList, actualModel.getRecipeList());
        assertEquals(expectedFilteredRecipeList, actualModel.getFilteredRecipeList());
        assertEquals(expectedLocationList, actualModel.getLocationList());
        assertEquals(expectedFilteredLocationList, actualModel.getFilteredLocationList());
    }

}
