package seedu.address.logic.commands.bids;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.bidcommands.AddBidCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelStub;
import seedu.address.model.bid.Bid;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidbook.ReadOnlyBidBook;
import seedu.address.testutil.bids.BidBuilder;


public class AddBidCommandTest {

    @Test
    public void constructor_nullBid_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBidCommand(null));
    }

    @Test
    public void execute_bidAcceptedByModel_addSuccessful() throws Exception {
        seedu.address.logic.commands.bids.AddBidCommandTest.ModelStubAcceptingBidAdded modelStub =
                new seedu.address.logic.commands.bids.AddBidCommandTest.ModelStubAcceptingBidAdded();
        Bid validBid = new BidBuilder().build();

        CommandResult commandResult = new AddBidCommand(validBid).execute(modelStub);
        assertEquals(String.format(AddBidCommand.MESSAGE_SUCCESS, validBid), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validBid), modelStub.bidsAdded);
    }



    @Test
    public void execute_duplicateBid_throwsCommandException() {
        Bid validBid = new BidBuilder().build();
        AddBidCommand addBidCommand = new AddBidCommand(validBid);
        ModelStub modelStub = new seedu.address.logic.commands.bids.AddBidCommandTest.ModelStubWithBid(validBid);

        assertThrows(CommandException.class,
                AddBidCommand.MESSAGE_DUPLICATE_BID, () -> addBidCommand.execute(modelStub));
    }

    @Test
    public void equals() {

        Bid one = new BidBuilder().withPropertyId("P01").build();
        Bid two = new BidBuilder().withPropertyId("P02").build();
        AddBidCommand addOneCommand = new AddBidCommand(one);
        AddBidCommand addTwoCommand = new AddBidCommand(two);

        // same object -> returns true
        assertTrue(addOneCommand.equals(addOneCommand));

        // same values -> returns true
        AddBidCommand addOneCommandCopy = new AddBidCommand(one);
        assertTrue(addOneCommand.equals(addOneCommandCopy));

        // different types -> returns false
        assertFalse(addOneCommand.equals(1));

        // null -> returns false
        assertFalse(addOneCommand.equals(null));

        // different bid -> returns false
        assertFalse(addOneCommand.equals(addTwoCommand));
    }

    /**
     * A Model stub that contains a single bid.
     */
    private class ModelStubWithBid extends ModelStub {
        private final Bid bid;

        ModelStubWithBid(Bid bid) {
            requireNonNull(bid);
            this.bid = bid;
        }

        @Override
        public boolean hasBid(Bid bid) {
            requireNonNull(bid);
            return this.bid.isSameBid(bid);
        }
    }

    /**
     * A Model stub that always accept the bid being added.
     */
    private class ModelStubAcceptingBidAdded extends ModelStub {
        final ArrayList<Bid> bidsAdded = new ArrayList<>();

        @Override
        public boolean hasBid(Bid bid) {
            requireNonNull(bid);
            return bidsAdded.stream().anyMatch(bid::isSameBid);
        }

        @Override
        public void addBid(Bid bid) {
            requireNonNull(bid);
            bidsAdded.add(bid);
        }

        @Override
        public ReadOnlyBidBook getBidBook() {
            return new BidBook();
        }

    }

}
