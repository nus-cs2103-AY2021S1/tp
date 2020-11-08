package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.seller.TypicalSeller.SELLER_ALICE;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.id.SellerId;
import seedu.address.model.person.exceptions.DuplicatePersonException;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.testutil.seller.SellerBuilder;

public class SellerAddressBookTest {

    private final SellerAddressBook sellerAddressBook = new SellerAddressBook();

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), sellerAddressBook.getSellerList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sellerAddressBook.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyAddressBook_replacesData() {
        SellerAddressBook newData = getTypicalSellerAddressBook();
        sellerAddressBook.resetData(newData);
        assertEquals(newData, sellerAddressBook);
    }

    @Test
    public void resetData_withDuplicateSellers_throwsDuplicatePersonException() {
        // Two sellers with the same identity fields
        Seller editedAlice = new SellerBuilder(SELLER_ALICE).build();
        List<Seller> newSellers = Arrays.asList(SELLER_ALICE, editedAlice);
        SellerAddressBookStub newData = new SellerAddressBookStub(newSellers);

        assertThrows(DuplicatePersonException.class, () -> sellerAddressBook.resetData(newData));
    }

    @Test
    public void hasSeller_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> sellerAddressBook.hasSeller(null));
    }

    @Test
    public void hasSeller_sellerNotInAddressBook_returnsFalse() {
        assertFalse(sellerAddressBook.hasSeller(SELLER_ALICE));
    }

    @Test
    public void hasSeller_sellerInAddressBook_returnsTrue() {
        sellerAddressBook.addSeller(SELLER_ALICE);
        assertTrue(sellerAddressBook.hasSeller(SELLER_ALICE));
    }

    @Test
    public void hasSeller_sellerWithSameIdentityFieldsInAddressBook_returnsTrue() {
        sellerAddressBook.addSeller(SELLER_ALICE);
        Seller editedAlice = new SellerBuilder(SELLER_ALICE).build();
        assertTrue(sellerAddressBook.hasSeller(editedAlice));
    }

    @Test
    public void getSellerList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> sellerAddressBook.getSellerList().remove(0));
    }

    /**
     * A stub ReadOnlySellerAddressBook whose sellers list can violate interface constraints.
     */
    private static class SellerAddressBookStub implements ReadOnlySellerAddressBook {
        private final ObservableList<Seller> sellers = FXCollections.observableArrayList();

        SellerAddressBookStub(Collection<Seller> sellers) {
            this.sellers.setAll(sellers);
        }

        @Override
        public ObservableList<Seller> getSellerList() {
            return sellers;
        }
    }

}
