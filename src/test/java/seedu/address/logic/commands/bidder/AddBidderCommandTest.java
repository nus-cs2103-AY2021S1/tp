package seedu.address.logic.commands.bidder;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.biddercommands.AddBidderCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.bidderaddressbook.ReadOnlyBidderAddressBook;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.testutil.bidder.BidderBuilder;

public class AddBidderCommandTest {

    @Test
    public void constructor_nullBidder_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBidderCommand(null));
    }

    @Test
    public void execute_bidderAcceptedByModel_addSuccessful() throws Exception {
        AddBidderCommandTest.ModelStubAcceptingBidderAdded modelStub =
                new AddBidderCommandTest.ModelStubAcceptingBidderAdded();
        Bidder validBidder = new BidderBuilder().build();

        CommandResult commandResult = new AddBidderCommand(validBidder).execute(modelStub);

        assertEquals(String.format(AddBidderCommand.MESSAGE_SUCCESS, validBidder), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBidder), modelStub.biddersAdded);
    }

    @Test
    public void execute_duplicateBidder_throwsCommandException() {
        Bidder validBidder = new BidderBuilder().build();
        AddBidderCommand addBidderCommand = new AddBidderCommand(validBidder);
        ModelStub modelStub = new ModelStubWithBidder(validBidder);

        assertThrows(CommandException.class,
                AddBidderCommand.MESSAGE_DUPLICATE_PERSON, () -> addBidderCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Bidder alice = new BidderBuilder().withName("Alice").build();
        Bidder bob = new BidderBuilder().withName("Bob").build();
        AddBidderCommand addAliceCommand = new AddBidderCommand(alice);
        AddBidderCommand addBobCommand = new AddBidderCommand(bob);

        // same object -> returns true
        assertTrue(addAliceCommand.equals(addAliceCommand));

        // same values -> returns true
        AddBidderCommand addAliceCommandCopy = new AddBidderCommand(alice);
        assertTrue(addAliceCommand.equals(addAliceCommandCopy));

        // different types -> returns false
        assertFalse(addAliceCommand.equals(1));

        // null -> returns false
        assertFalse(addAliceCommand.equals(null));

        // different person -> returns false
        assertFalse(addAliceCommand.equals(addBobCommand));
    }


    /**
     * A Model stub that contains a single bidder.
     */
    private class ModelStubWithBidder extends ModelStub {

        private final Bidder bidder;

        ModelStubWithBidder(Bidder bidder) {
            requireNonNull(bidder);
            this.bidder = bidder;
        }

        @Override
        public boolean hasBidder(Bidder bidder) {
            requireNonNull(bidder);
            return this.bidder.isSameBidder(bidder);
        }
    }

    /**
     * A Model stub that always accept the bidder being added.
     */
    private class ModelStubAcceptingBidderAdded extends ModelStub {

        final ArrayList<Bidder> biddersAdded = new ArrayList<>();

        @Override
        public boolean hasBidder(Bidder bidder) {
            requireNonNull(bidder);
            return biddersAdded.stream().anyMatch(bidder::isSameBidder);
        }

        @Override
        public void addBidder(Bidder bidder) {
            requireNonNull(bidder);
            biddersAdded.add(bidder);
        }

        @Override
        public ReadOnlyBidderAddressBook getBidderAddressBook() {
            return new BidderAddressBook();
        }

    }

}
