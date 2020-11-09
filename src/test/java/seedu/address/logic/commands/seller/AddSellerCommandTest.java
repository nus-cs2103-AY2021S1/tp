package seedu.address.logic.commands.seller;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.commands.sellercommands.AddSellerCommand;
import seedu.address.model.ModelStub;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.selleraddressbook.ReadOnlySellerAddressBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;
import seedu.address.testutil.seller.SellerBuilder;

public class AddSellerCommandTest {

    @Test
    public void constructor_nullSeller_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddSellerCommand(null));
    }

    @Test
    public void execute_sellerAcceptedByModel_addSuccessful() throws Exception {
        AddSellerCommandTest.ModelStubAcceptingSellerAdded modelStub =
                new AddSellerCommandTest.ModelStubAcceptingSellerAdded();
        Seller validSeller = new SellerBuilder().build();

        CommandResult commandResult = new AddSellerCommand(validSeller).execute(modelStub);

        assertEquals(String.format(AddSellerCommand.MESSAGE_SUCCESS, validSeller), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validSeller), modelStub.sellersAdded);
    }

    @Test
    public void execute_duplicateSeller_throwsCommandException() {
        Seller validSeller = new SellerBuilder().build();
        AddSellerCommand addSellerCommand = new AddSellerCommand(validSeller);
        ModelStub modelStub = new ModelStubWithSeller(validSeller);

        assertThrows(CommandException.class,
                AddSellerCommand.MESSAGE_DUPLICATE_PERSON, () -> addSellerCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Seller alice = new SellerBuilder().withName("Alice").build();
        Seller bob = new SellerBuilder().withName("Bob").build();
        AddSellerCommand addAliceCommand = new AddSellerCommand(alice);
        AddSellerCommand addBobCommand = new AddSellerCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddSellerCommand addAliceCommandCopy = new AddSellerCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }


    /**
     * A Model stub that contains a single seller.
     */
    private class ModelStubWithSeller extends ModelStub {

        private final Seller seller;

        ModelStubWithSeller(Seller seller) {
            requireNonNull(seller);
            this.seller = seller;
        }

        @Override
        public boolean hasSeller(Seller seller) {
            requireNonNull(seller);
            return this.seller.isSameSeller(seller);
        }
    }

    /**
     * A Model stub that always accept the seller being added.
     */
    private class ModelStubAcceptingSellerAdded extends ModelStub {

        final ArrayList<Seller> sellersAdded = new ArrayList<>();

        @Override
        public boolean hasSeller(Seller seller) {
            requireNonNull(seller);
            return sellersAdded.stream().anyMatch(seller::isSameSeller);
        }

        @Override
        public void addSeller(Seller seller) {
            requireNonNull(seller);
            sellersAdded.add(seller);
        }

        @Override
        public ReadOnlySellerAddressBook getSellerAddressBook() {
            return new SellerAddressBook();
        }

    }

}
