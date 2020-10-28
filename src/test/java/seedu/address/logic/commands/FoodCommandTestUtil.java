package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
//import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.food.Food;
import seedu.address.model.food.NameContainsKeywordsPredicate;
import seedu.address.storage.StorageManager;

/**
 * Contains helper methods for testing commands.
 */
public class FoodCommandTestUtil {

    public static final double VALID_PRICE_MILO = 1.50;
    public static final String VALID_NAME_PRATA = "Prata";
    public static final String VALID_NAME_MILO = "Milo";
    //    public static final String VALID_PRICE_PRATA = "$1.00";
    //    public static final String VALID_PRICE_STRING_MILO = "$1.50";
    public static final String VALID_TAG_CLASSIC = "classic";
    public static final String VALID_TAG_ICED = "ICED";

    //    public static final String NAME_DESC_PRATA = " " + PREFIX_NAME + VALID_NAME_PRATA;
    //    public static final String NAME_DESC_MILO = " " + PREFIX_NAME + VALID_NAME_MILO;
    //    public static final String TAG_DESC_CLASSIC= " " + PREFIX_TAG + VALID_TAG_CLASSIC;
    //    public static final String TAG_DESC_ICED = " " + PREFIX_TAG + VALID_TAG_ICED;
    //
    //    public static final String INVALID_PRICE_DESC = " " + PREFIX_PRICE + "-$0.30"; //negative price is not allowed
    //    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "milo*"; // '*' not allowed in tags
    //
    //    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    //    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    //    public static final EditCommand.EditFoodDescriptor DESC_AMY;
    //    public static final EditCommand.EditFoodDescriptor DESC_BOB;

    //    static {
    //        DESC_AMY = new EditFoodDescriptorBuilder().withName(VALID_NAME_AMY)
    //                .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
    //                .withTags(VALID_TAG_FRIEND).build();
    //        DESC_BOB = new EditFoodDescriptorBuilder().withName(VALID_NAME_BOB)
    //                .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
    //                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    //    }
    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, new StorageManager());
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
     * - the address book, filtered food list and selected food in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        //check first filtered food list
        List<Food> expectedFilteredList = new ArrayList<>(actualModel.getFilteredFoodList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, new StorageManager()));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredFoodList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the food at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showFoodAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredFoodList().size());

        Food food = model.getFilteredFoodList().get(targetIndex.getZeroBased());
        final String[] splitName = food.getName().split("\\s+");
        model.updateFilteredFoodList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredFoodList().size());
    }

}
