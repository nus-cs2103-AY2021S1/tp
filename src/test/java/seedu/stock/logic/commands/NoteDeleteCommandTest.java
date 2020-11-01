package seedu.stock.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.*;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalSerialNumberSets.getTypicalSerialNumberSetsBook;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_FIRST_STOCK;
import static seedu.stock.testutil.TypicalStocks.SERIAL_NUMBER_THIRD_STOCK;
import static seedu.stock.testutil.TypicalStocks.UNKNOWN_SERIAL_NUMBER;
import static seedu.stock.testutil.TypicalStocks.getTypicalStockBook;

import org.junit.jupiter.api.Test;

import seedu.stock.commons.core.Messages;
import seedu.stock.model.Model;
import seedu.stock.model.ModelManager;
import seedu.stock.model.SerialNumberSetsBook;
import seedu.stock.model.StockBook;
import seedu.stock.model.UserPrefs;
import seedu.stock.model.stock.NoteIndex;
import seedu.stock.model.stock.SerialNumber;
import seedu.stock.model.stock.Stock;
import seedu.stock.testutil.StockBuilder;
import seedu.stock.testutil.TypicalStocks;

/**
 * Contains integration tests (interaction with the Model) for {@code NoteDeleteCommand}.
 */
public class NoteDeleteCommandTest {

    private static final NoteIndex NOTE_INDEX_STUB = NoteIndex.fromOneBased(VALID_NOTE_INDEX);

    private SerialNumberSetsBook serialNumbers = getTypicalSerialNumberSetsBook();
    private Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), serialNumbers);

    @Test
    public void constructor_nullNote_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteDeleteCommand(SERIAL_NUMBER_THIRD_STOCK, null));
    }

    @Test
    public void constructor_nullSerialNumber_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new NoteDeleteCommand(null, NOTE_INDEX_STUB));
    }

    @Test
    public void execute_noteDeleteUnfilteredList_success() {

        SerialNumber serialNumberBanana = new SerialNumber("fairprice1");
        NoteIndex noteIndex = new NoteIndex("1");
        Stock secondStockWithDeletedNote = new StockBuilder(TypicalStocks.BANANA)
                .copyOfStockBuilder().deleteNote(1).build();

        NoteDeleteCommand noteDeleteCommand = new NoteDeleteCommand(serialNumberBanana, noteIndex);

        String expectedMessage = String.format(Messages.MESSAGE_DELETE_NOTE_SUCCESS, secondStockWithDeletedNote);

        Model expectedModel = new ModelManager(new StockBook(model.getStockBook()), new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));
        expectedModel.setStock(model.getFilteredStockList().get(1),
                secondStockWithDeletedNote);

        assertCommandSuccess(noteDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noteDeleteAllNotesUnfilteredList_success() {

        SerialNumberSetsBook serialNumbers = getTypicalSerialNumberSetsBook();
        Model model = new ModelManager(getTypicalStockBook(), new UserPrefs(), serialNumbers);

        Stock firstStockWithAllNotesDeleted = new StockBuilder(TypicalStocks.APPLE)
                .copyOfStockBuilder().withoutNotes().build();
        SerialNumber serialNumberBanana = new SerialNumber("ntuc1");
        NoteDeleteCommand noteDeleteCommand = new NoteDeleteCommand(serialNumberBanana,
                new NoteIndex("0"));

        String expectedMessage = String.format(Messages.MESSAGE_DELETE_NOTE_SUCCESS,
                firstStockWithAllNotesDeleted);

        Model expectedModel = new ModelManager(new StockBook(model.getStockBook()), new UserPrefs(),
                new SerialNumberSetsBook(model.getSerialNumberSetsBook()));
        expectedModel.setStock(model.getFilteredStockList().get(0),
                firstStockWithAllNotesDeleted);

        assertCommandSuccess(noteDeleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_deleteNoteFromStockWithoutNotesUnfilteredList_failure() {

        NoteDeleteCommand noteDeleteCommand = new NoteDeleteCommand(SERIAL_NUMBER_THIRD_STOCK,
                new NoteIndex(VALID_NOTE_INDEX));

        assertCommandFailure(noteDeleteCommand, model, Messages.MESSAGE_STOCK_HAS_NO_NOTE);
    }

    @Test
    public void execute_noteIndexNotFoundUnfilteredList_failure() {

        NoteDeleteCommand noteDeleteCommand = new NoteDeleteCommand(SERIAL_NUMBER_FIRST_STOCK,
                new NoteIndex(VALID_NOTE_INDEX_OUT_OF_BOUNDS));

        assertCommandFailure(noteDeleteCommand, model, Messages.MESSAGE_NOTE_INDEX_NOT_FOUND);
    }

    @Test
    public void execute_serialNumberNotFoundUnfilteredList_failure() {

        NoteDeleteCommand noteDeleteCommand = new NoteDeleteCommand(UNKNOWN_SERIAL_NUMBER,
                new NoteIndex(VALID_NOTE_INDEX));

        assertCommandFailureForNote(noteDeleteCommand, model, NoteCommand.MESSAGE_SERIAL_NUMBER_NOT_FOUND);
    }

    @Test
    public void equals() {

        final NoteDeleteCommand standardCommand = new NoteDeleteCommand(SERIAL_NUMBER_FIRST_STOCK,
                new NoteIndex(VALID_NOTE_INDEX));

        // same values -> returns true
        NoteDeleteCommand commandWithSameValues = new NoteDeleteCommand(SERIAL_NUMBER_FIRST_STOCK,
                new NoteIndex(VALID_NOTE_INDEX));

        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different serial number -> returns false
        assertFalse(standardCommand.equals(new NoteDeleteCommand(SERIAL_NUMBER_THIRD_STOCK,
                new NoteIndex(VALID_NOTE_INDEX))));

        // different note index -> returns false
        assertFalse(standardCommand.equals(new NoteDeleteCommand(SERIAL_NUMBER_FIRST_STOCK,
                new NoteIndex(VALID_NOTE_INDEX_FIRST_NOTE_BANANA))));
    }

}
