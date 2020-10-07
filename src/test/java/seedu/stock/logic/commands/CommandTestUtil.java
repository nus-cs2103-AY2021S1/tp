package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.stock.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.Model;
import seedu.stock.model.StockBook;
import seedu.stock.model.stock.Stock;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_APPLE = "Apple Juice";
    public static final String VALID_NAME_BANANA = "Banana Bun";
    public static final String VALID_SERIALNUMBER_APPLE = "Ntuc1";
    public static final String VALID_SERIALNUMBER_BANANA = "Fairprice1";
    public static final String VALID_SOURCE_APPLE = "Ntuc";
    public static final String VALID_SOURCE_BANANA = "Fairprice";
    public static final String VALID_QUANTITY_APPLE = "2000";
    public static final String VALID_QUANTITY_BANANA = "1000";
    public static final String VALID_LOCATION_APPLE = "Fruit Section, Subsection C";
    public static final String VALID_LOCATION_BANANA = "Fruits section, Subsection B";

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
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StockBook expectedStockBook = new StockBook(actualModel.getStockBook());
        List<Stock> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStockList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStockBook, actualModel.getStockBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStockList());
    }
}
