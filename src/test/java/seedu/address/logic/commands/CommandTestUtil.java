package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MAX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_METRIC;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SUPPLIER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.results.CommandResult;
import seedu.address.model.InventoryBook;
import seedu.address.model.Model;
import seedu.address.model.item.Item;
import seedu.address.model.item.ItemContainsKeywordsPredicate;
import seedu.address.testutil.EditItemDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_CHICKEN = "Chicken";
    public static final String VALID_NAME_DUCK = "Duck";
    public static final String VALID_QUANTITY_CHICKEN = "11111111";
    public static final String VALID_QUANTITY_DUCK = "22222222";
    public static final String VALID_SUPPLIER_CHICKEN = "GIANT";
    public static final String VALID_SUPPLIER_DUCK = "Sheng Siong";
    public static final String VALID_TAG_DUCK = "meat";
    public static final String VALID_TAG_CHICKEN = "poultry";
    public static final String VALID_MAX_QUANTITY = "500";
    public static final String VALID_MAX_QUANTITY_CHICKEN = "50";
    public static final String VALID_MAX_QUANTITY_DUCK = "30";
    public static final String VALID_METRIC = "kg";

    public static final String NAME_DESC_CHICKEN = " " + PREFIX_NAME + VALID_NAME_CHICKEN;
    public static final String NAME_DESC_DUCK = " " + PREFIX_NAME + VALID_NAME_DUCK;
    public static final String QUANTITY_DESC_CHICKEN = " " + PREFIX_QUANTITY + VALID_QUANTITY_CHICKEN;
    public static final String QUANTITY_DESC_DUCK = " " + PREFIX_QUANTITY + VALID_QUANTITY_DUCK;
    public static final String SUPPLIER_DESC_CHICKEN = " " + PREFIX_SUPPLIER + VALID_SUPPLIER_CHICKEN;
    public static final String SUPPLIER_DESC_DUCK = " " + PREFIX_SUPPLIER + VALID_SUPPLIER_DUCK;
    public static final String TAG_DESC_CHICKEN = " " + PREFIX_TAG + VALID_TAG_CHICKEN;
    public static final String TAG_DESC_DUCK = " " + PREFIX_TAG + VALID_TAG_DUCK;
    public static final String MAX_QUANTITY_DESC = " " + PREFIX_MAX_QUANTITY + VALID_MAX_QUANTITY;
    public static final String MAX_QUANTITY_DESC_CHICKEN = " " + PREFIX_MAX_QUANTITY + VALID_MAX_QUANTITY_CHICKEN;
    public static final String MAX_QUANTITY_DESC_DUCK = " " + PREFIX_MAX_QUANTITY + VALID_MAX_QUANTITY_DUCK;
    public static final String METRIC_DESC_CHICKEN = " " + PREFIX_METRIC + VALID_METRIC;
    public static final String METRIC_DESC_DUCK = " " + PREFIX_METRIC + VALID_METRIC;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "Salt&"; // '&' not allowed in names
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "911a"; // 'a' not allowed in quantity
    public static final String INVALID_SUPPLIER_DESC = " " + PREFIX_SUPPLIER; // empty string not allowed for suppliers
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "liquids*"; // '*' not allowed in tags
    public static final String INVALID_MAX_QUANTITY_DESC = " " + PREFIX_MAX_QUANTITY + "-30"; // neg num not allowed
    public static final String INVALID_METRIC = " " + PREFIX_METRIC + "kgs*"; // '*' not allowed in metric

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditCommand.EditItemDescriptor DESC_CHICKEN;
    public static final EditCommand.EditItemDescriptor DESC_DUCK;

    static {
        DESC_CHICKEN = new EditItemDescriptorBuilder().withName(VALID_NAME_CHICKEN)
                .withQuantity(VALID_QUANTITY_CHICKEN).withSupplier(VALID_SUPPLIER_CHICKEN)
                .withTags(VALID_TAG_CHICKEN).build();
        DESC_DUCK = new EditItemDescriptorBuilder().withName(VALID_NAME_DUCK)
                .withQuantity(VALID_QUANTITY_DUCK).withSupplier(VALID_SUPPLIER_DUCK)
                .withTags(VALID_TAG_DUCK, VALID_TAG_CHICKEN).build();
    }

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
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the inventory book, filtered item list and selected item in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        InventoryBook expectedInventoryBook = new InventoryBook(actualModel.getInventoryBook());
        List<Item> expectedFilteredList = new ArrayList<>(actualModel.getFilteredItemList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedInventoryBook, actualModel.getInventoryBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredItemList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the item at the given {@code targetIndex} in the
     * {@code model}'s inventory book.
     */
    public static void showItemAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredItemList().size());

        Item item = model.getFilteredItemList().get(targetIndex.getZeroBased());
        final String[] splitName = item.getName().fullName.split("\\s+");
        model.updateFilteredItemList(new ItemContainsKeywordsPredicate(Arrays.asList(splitName[0]), PREFIX_NAME));

        assertEquals(1, model.getFilteredItemList().size());
    }

}
