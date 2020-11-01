package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NOTE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NOTE_APPLE;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_NOTE_ORANGE;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandFailureForNote;
import static seedu.stock.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.stock.logic.commands.CommandTestUtil.isSerialNumberInStockBook;
import static seedu.stock.logic.commands.CommandTestUtil.showStockAtSerialNumber;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.INDEX_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.INDEX_SECOND_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_SECOND_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_THIRD_STOCK;
import static seedu.stock.testutil.TypicalStocks.UNKNOWN_SERIAL_NUMBER;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import org.junit.jupiter.api.Test;

import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.Note;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;
import seedu.stock.testutil.StockBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code NoteCommand}.
 */
public class NoteCommandTest {

    private static final String NOTE_STUB = VALID_NOTE;

    private SerialNumberSetsBook serialNumbers = getTypicalSerialNumberSetsBook();
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), serialNumbers);


    @Test
    public void constructor_nullSerialNumber_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteCommand(null, new Note(VALID_NOTE)));
    }

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteCommand(SERIAL_NUMBER_FIRST_STOCK, null));
    }

    @Test
    public void execute_addNoteUnfilteredList_success() {

        Stock firstStock = model.getFilteredStockList().get(INDEX_FIRST_STOCK.getZeroBased());

        Stock firstStockWithAddedNote = new StockBuilder(firstStock).copyOfStockBuilder().addNote(NOTE_STUB).build();

        NoteCommand noteCommand = new NoteCommand(SERIAL_NUMBER_FIRST_STOCK, new Note(NOTE_STUB));

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS, firstStockWithAddedNote);

        Model expectedModel = new ModelManager(getTypicalStockBook(), new UserPrefs(),
                getTypicalSerialNumberSetsBook());
        expectedModel.setStock(firstStock, firstStockWithAddedNote);

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_addNoteFilteredList_success() {

        showStockAtSerialNumber(model, SERIAL_NUMBER_FIRST_STOCK);

        Stock firstStock = model.getFilteredStockList().get(INDEX_FIRST_STOCK.getZeroBased());

        Stock firstStockWithAddedNote = new StockBuilder(firstStock).copyOfStockBuilder().addNote(NOTE_STUB).build();

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS, firstStockWithAddedNote);
        Model expectedModel = new ModelManager(new StockBook(model.getStockBook()), new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));
        expectedModel.setStock(firstStock, firstStockWithAddedNote);

        NoteCommand noteCommand = new NoteCommand(SERIAL_NUMBER_FIRST_STOCK, new Note(NOTE_STUB));

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_serialNumberNotFoundUnfilteredList_failure() {

        NoteCommand noteCommand = new NoteCommand(UNKNOWN_SERIAL_NUMBER, new Note(VALID_NOTE));

        assertCommandFailureForNote(noteCommand, model, NoteCommand.MESSAGE_SERIAL_NUMBER_NOT_FOUND);
    }

    /**
     * Edit filtered list where only shows the first stock of the stock book.
     */
    @Test
    public void execute_validSerialNumberNotInFilteredList_success() {
        showStockAtSerialNumber(model, SERIAL_NUMBER_FIRST_STOCK);

        SerialNumber serialNumberNotInFilteredList = SERIAL_NUMBER_SECOND_STOCK;
        // ensures that serial number not in filtered list is still in stock book
        assertTrue(isSerialNumberInStockBook(model, serialNumberNotInFilteredList));

        Stock secondStock = model.getStockBook().getStockList().get(INDEX_SECOND_STOCK.getZeroBased());

        Stock secondStockWithAddedNote = new StockBuilder(secondStock).copyOfStockBuilder().addNote(VALID_NOTE).build();

        String expectedMessage = String.format(NoteCommand.MESSAGE_ADD_NOTE_SUCCESS, secondStockWithAddedNote);

        Model expectedModel = new ModelManager(new StockBook(model.getStockBook()), new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));
        expectedModel.setStock(secondStock, secondStockWithAddedNote);

        NoteCommand noteCommand = new NoteCommand(serialNumberNotInFilteredList, new Note(VALID_NOTE));

        assertCommandSuccess(noteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        final NoteCommand standardCommand = new NoteCommand(SERIAL_NUMBER_FIRST_STOCK,
                new Note(VALID_NOTE_APPLE));

        // same values -> returns true
        NoteCommand commandWithSameValues = new NoteCommand(SERIAL_NUMBER_FIRST_STOCK,
                new Note(VALID_NOTE_APPLE));
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different serial number -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(SERIAL_NUMBER_THIRD_STOCK,
                new Note(VALID_NOTE_APPLE))));

        // different note -> returns false
        assertFalse(standardCommand.equals(new NoteCommand(SERIAL_NUMBER_FIRST_STOCK,
                new Note(VALID_NOTE_ORANGE))));
    }

}
