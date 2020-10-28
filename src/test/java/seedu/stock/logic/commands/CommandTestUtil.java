package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_FILE_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_INCREMENT_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LIST_TYPE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOCATION;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_LOW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NEW_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_NOTE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_QUANTITY;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SERIAL_NUMBER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_FIELD;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SORT_ORDER;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_SOURCE;
import static seedu.stock.logic.parser.CliSyntax.PREFIX_STATISTICS_TYPE;
import static seedu.stock.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.logic.commands.exceptions.SourceCompanyNotFoundException;
import seedu.stock.model.Model;
import seedu.stock.model.StockBook;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.predicates.NameContainsKeywordsPredicate;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_APPLE = "Apple Juice";
    public static final String VALID_NAME_BANANA = "Banana Bun";
    public static final String VALID_SERIAL_NUMBER_APPLE = "Ntuc1";
    public static final String VALID_SERIAL_NUMBER_BANANA = "Fairprice1";
    public static final String VALID_SOURCE_APPLE = "Ntuc";
    public static final String VALID_SOURCE_BANANA = "Fairprice";
    public static final String VALID_QUANTITY_APPLE = "2000";
    public static final String VALID_QUANTITY_BANANA = "1000";
    public static final String VALID_LOW_QUANTITY_APPLE = "0";
    public static final String VALID_LOW_QUANTITY_BANANA = "2200";
    public static final String VALID_LOCATION_APPLE = "Fruit Section, Subsection C";
    public static final String VALID_LOCATION_BANANA = "Fruits section, Subsection B";
    public static final String VALID_FILE_NAME = "test";
    public static final String VALID_SORT_ORDER_ASCENDING = "ascending";
    public static final String VALID_SORT_ORDER_DESCENDING = "descending";
    public static final String VALID_SORT_FIELD = "serialnumber";
    public static final String VALID_NOTE = "This is a note";

    public static final String NAME_DESC_APPLE = " " + PREFIX_NAME + VALID_NAME_APPLE;
    public static final String NAME_DESC_BANANA = " " + PREFIX_NAME + VALID_NAME_BANANA;
    public static final String NAME_DESC_BANANA_WITH_WHITESPACES_BETWEEN = " " + PREFIX_NAME + "Banana         Bun";
    public static final String SERIAL_NUMBER_DESC_APPLE = " " + PREFIX_SERIAL_NUMBER + VALID_SERIAL_NUMBER_APPLE;
    public static final String SERIAL_NUMBER_DESC_BANANA = " " + PREFIX_SERIAL_NUMBER + VALID_SERIAL_NUMBER_BANANA;
    public static final String SOURCE_DESC_APPLE = " " + PREFIX_SOURCE + VALID_SOURCE_APPLE;
    public static final String SOURCE_DESC_BANANA = " " + PREFIX_SOURCE + VALID_SOURCE_BANANA;
    public static final String QUANTITY_DESC_APPLE = " " + PREFIX_QUANTITY + VALID_QUANTITY_APPLE;
    public static final String QUANTITY_DESC_BANANA = " " + PREFIX_QUANTITY + VALID_QUANTITY_BANANA;
    public static final String LOW_QUANTITY_DESC_APPLE = " " + PREFIX_LOW_QUANTITY + VALID_LOW_QUANTITY_APPLE;
    public static final String LOW_QUANTITY_DESC_BANANA = " " + PREFIX_LOW_QUANTITY + VALID_LOW_QUANTITY_BANANA;
    public static final String NEW_QUANTITY_DESC_APPLE = " " + PREFIX_NEW_QUANTITY + VALID_QUANTITY_APPLE;
    public static final String NEW_QUANTITY_DESC_BANANA = " " + PREFIX_NEW_QUANTITY + VALID_QUANTITY_BANANA;
    public static final String INCREMENT_QUANTITY_DESC_APPLE = " " + PREFIX_INCREMENT_QUANTITY + VALID_QUANTITY_APPLE;
    public static final String INCREMENT_QUANTITY_DESC_BANANA = " " + PREFIX_INCREMENT_QUANTITY + VALID_QUANTITY_BANANA;
    public static final String LOCATION_DESC_APPLE = " " + PREFIX_LOCATION + VALID_LOCATION_APPLE;
    public static final String LOCATION_DESC_BANANA = " " + PREFIX_LOCATION + VALID_LOCATION_BANANA;
    public static final String FILE_NAME_DESC = " " + PREFIX_FILE_NAME + VALID_FILE_NAME;
    public static final String SORT_ORDER_ASCENDING_DESC = " " + PREFIX_SORT_ORDER + VALID_SORT_ORDER_ASCENDING;
    public static final String SORT_ORDER_DESCENDING_DESC = " " + PREFIX_SORT_ORDER + VALID_SORT_ORDER_DESCENDING;
    public static final String SORT_FIELD_DESC = " " + PREFIX_SORT_FIELD + VALID_SORT_FIELD;
    public static final String NOTE_DESC = " " + PREFIX_NOTE + VALID_NOTE;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "donje#y"; // '#' not allowed in names
    public static final String INVALID_SERIAL_NUMBER_DESC = " " + PREFIX_SERIAL_NUMBER;
    public static final String INVALID_SOURCE_DESC = " " + PREFIX_SOURCE; // missing source
    public static final String INVALID_QUANTITY_DESC = " " + PREFIX_QUANTITY + "-100"; // negative quantity not allowed
    public static final String INVALID_LOCATION_DESC = " " + PREFIX_LOCATION; // '*' not allowed in tags
    public static final String INVALID_INCREMENT_QUANTITY_DESC = " " + PREFIX_INCREMENT_QUANTITY + "two";
    public static final String INVALID_NEW_QUANTITY_DESC = " " + PREFIX_NEW_QUANTITY + "-100";
    public static final String INVALID_NEW_QUANTITY_DESC2 = " " + PREFIX_NEW_QUANTITY + "two";
    public static final String INVALID_LOW_QUANTITY_DESC = " " + PREFIX_LOW_QUANTITY + "-100";
    public static final String INVALID_LOW_QUANTITY_DESC2 = " " + PREFIX_LOW_QUANTITY + "two";
    public static final String INVALID_FILE_NAME_DESC = " " + PREFIX_FILE_NAME + "test+2";
    public static final String INVALID_SORT_ORDER_DESC = " " + PREFIX_SORT_ORDER + "random";
    public static final String INVALID_SORT_FIELD_DESC = " " + PREFIX_SORT_FIELD + "note";
    public static final String INVALID_LIST_TYPE_DESC = " " + PREFIX_LIST_TYPE + "a";
    public static final String INVALID_STATISTICS_TYPE_DESC = " " + PREFIX_STATISTICS_TYPE + "sour";


    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";
    public static final String DEFAULT_SERIAL_NUMBER = "0";

    public static final String[] VALID_NAME_APPLE_KEYWORDS = VALID_NAME_APPLE.split("\\s+");
    public static final String[] VALID_NAME_BANANA_KEYWORDS = VALID_NAME_BANANA.split("\\s+");
    public static final String[] VALID_SOURCE_APPLE_KEYWORDS = VALID_SOURCE_APPLE.split("\\s+");
    public static final String[] VALID_LOCATION_APPLE_KEYWORDS = VALID_LOCATION_APPLE.split("\\s+");
    public static final String[] VALID_SERIAL_NUMBER_BANANA_KEYWORDS = VALID_SERIAL_NUMBER_BANANA.split("\\s+");

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
        } catch (CommandException | SourceCompanyNotFoundException ce) {
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
     * - the stock book, filtered stock list and selected stock in {@code actualModel} remain unchanged
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

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code SourceCompanyNotFoundException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the stock book, filtered stock list and selected stock in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailureForStatistics(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        StockBook expectedStockBook = new StockBook(actualModel.getStockBook());
        List<Stock> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStockList());

        assertThrows(SourceCompanyNotFoundException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedStockBook, actualModel.getStockBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStockList());
    }

    /**
     * Updates {@code model}'s filtered list to show only the stock at the given {@code targetSerialNumber} in the
     * {@code model}'s stock book.
     */
    public static void showStockAtSerialNumber(Model model, SerialNumber targetSerialNumber) {
        //stocks should exist
        assertTrue(model.getFilteredStockList().stream().anyMatch(stock ->
                                                stock.getSerialNumber().equals(targetSerialNumber)));

        List<Stock> stockWithTargetSerialNumber = new ArrayList<>();

        for (int i = 0; i < model.getFilteredStockList().size(); i++) {
            Stock curr = model.getFilteredStockList().get(i);
            if (curr.getSerialNumber().equals(targetSerialNumber)) {
                stockWithTargetSerialNumber.add(curr);
            }
        }

        assertEquals(1, stockWithTargetSerialNumber.size());

        List<String> nameForPredicate = stockWithTargetSerialNumber.stream()
                                    .map(stock -> stock.getName().fullName).collect(Collectors.toList());

        model.updateFilteredStockList((new NameContainsKeywordsPredicate(nameForPredicate)));

    }
}
