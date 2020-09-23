package seedu.stock.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.stock.commons.core.GuiSettings;
import seedu.stock.logic.commands.exceptions.CommandException;
import seedu.stock.model.StockBook;
import seedu.stock.model.Model;
import seedu.stock.model.ReadOnlyStockBook;
import seedu.stock.model.ReadOnlyUserPrefs;
import seedu.stock.model.stock.Stock;
import seedu.stock.testutil.PersonBuilder;

public class AddCommandTest {

    @Test
    public void constructor_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddCommand(null));
    }

    @Test
    public void execute_personAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingPersonAdded modelStub = new ModelStubAcceptingPersonAdded();
        Stock validStock = new PersonBuilder().build();

        CommandResult commandResult = new AddCommand(validStock).execute(modelStub);

        assertEquals(String.format(AddCommand.MESSAGE_SUCCESS, validStock), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validStock), modelStub.personsAdded);
    }

    @Test
    public void execute_duplicatePerson_throwsCommandException() {
        Stock validStock = new PersonBuilder().build();
        AddCommand addCommand = new AddCommand(validStock);
        ModelStub modelStub = new ModelStubWithPerson(validStock);

        assertThrows(CommandException.class, AddCommand.MESSAGE_DUPLICATE_STOCK, () -> addCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Stock alice = new PersonBuilder().withName("Alice").build();
        Stock bob = new PersonBuilder().withName("Bob").build();
        AddCommand addAliceCommand = new AddCommand(alice);
        AddCommand addBobCommand = new AddCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddCommand addAliceCommandCopy = new AddCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getStockBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStockBookFilePath(Path stockBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addStock(Stock stock) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setStockBook(ReadOnlyStockBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyStockBook getStockBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasStock(Stock stock) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deletePerson(Stock target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setPerson(Stock target, Stock editedStock) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Stock> getFilteredStockList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredStockList(Predicate<Stock> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithPerson extends ModelStub {
        private final Stock stock;

        ModelStubWithPerson(Stock stock) {
            requireNonNull(stock);
            this.stock = stock;
        }

        @Override
        public boolean hasStock(Stock stock) {
            requireNonNull(stock);
            return this.stock.isSamePerson(stock);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingPersonAdded extends ModelStub {
        final ArrayList<Stock> personsAdded = new ArrayList<>();

        @Override
        public boolean hasStock(Stock stock) {
            requireNonNull(stock);
            return personsAdded.stream().anyMatch(stock::isSamePerson);
        }

        @Override
        public void addStock(Stock stock) {
            requireNonNull(stock);
            personsAdded.add(stock);
        }

        @Override
        public ReadOnlyStockBook getStockBook() {
            return new StockBook();
        }
    }

}
