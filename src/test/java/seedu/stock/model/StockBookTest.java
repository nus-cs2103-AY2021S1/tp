package seedu.stock.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.stock.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.stock.testutil.Assert.assertThrows;
import static seedu.stock.testutil.TypicalPersons.ALICE;
import static seedu.stock.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.stock.model.stock.Stock;
import seedu.stock.model.stock.exceptions.DuplicateStockException;
import seedu.stock.testutil.PersonBuilder;

public class StockBookTest {

    private final StockBook stockBook = new StockBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), stockBook.getStockList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> stockBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        StockBook newData = getTypicalAddressBook();
        stockBook.resetData(newData);
        assertEquals(newData, stockBook);
    }

    @Test
    public void resetData_withDuplicatePersons_throwsDuplicatePersonException() {
        // Two persons with the same identity fields
        Stock editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        List<Stock> newStocks = Arrays.asList(ALICE, editedAlice);
        StockBookStub newData = new StockBookStub(newStocks);

        assertThrows(DuplicateStockException.class, () -> stockBook.resetData(newData));
    }

    @Test
    public void hasPerson_nullPerson_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> stockBook.hasStock(null));
    }

    @Test
    public void hasPerson_personNotInAddressBook_returnsFalse() {
        assertFalse(stockBook.hasStock(ALICE));
    }

    @Test
    public void hasPerson_personInAddressBook_returnsTrue() {
        stockBook.addStock(ALICE);
        assertTrue(stockBook.hasStock(ALICE));
    }

    @Test
    public void hasPerson_personWithSameIdentityFieldsInAddressBook_returnsTrue() {
        stockBook.addStock(ALICE);
        Stock editedAlice = new PersonBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).withTags(VALID_TAG_HUSBAND)
                .build();
        assertTrue(stockBook.hasStock(editedAlice));
    }

    @Test
    public void getPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> stockBook.getStockList().remove(0));
    }

    /**
     * A stub ReadOnlyAddressBook whose persons list can violate interface constraints.
     */
    private static class StockBookStub implements ReadOnlyStockBook {
        private final ObservableList<Stock> stocks = FXCollections.observableArrayList();

        StockBookStub(Collection<Stock> stocks) {
            this.stocks.setAll(stocks);
        }

        @Override
        public ObservableList<Stock> getStockList() {
            return stocks;
        }
    }

}
