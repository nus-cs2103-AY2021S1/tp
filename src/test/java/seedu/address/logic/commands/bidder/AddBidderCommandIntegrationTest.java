package seedu.address.logic.commands.bidder;

import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.assertBidderCommandFailure;
import static seedu.address.logic.commands.bidder.BidderCommandTestUtil.assertBidderCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.biddercommands.AddBidderCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.testutil.bidder.BidderBuilder;

public class AddBidderCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new BidBook(), getTypicalPropertyBook(),
                getTypicalBidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
    }

    @Test
    public void execute_newBidder_success() {
        Bidder validBidder = new BidderBuilder().build();

        Model expectedModel = new ModelManager(
                model.getAddressBook(),
                new UserPrefs(),
                model.getBidBook(),
                model.getPropertyBook(),
                model.getBidderAddressBook(),
                model.getSellerAddressBook(),
                model.getMeetingBook()
        );

        expectedModel.addBidder(validBidder);

        assertBidderCommandSuccess(new AddBidderCommand(validBidder.setDefaultBidderId()), model,
                String.format(AddBidderCommand.MESSAGE_SUCCESS, validBidder), expectedModel);
    }

    @Test
    public void execute_duplicateBidder_throwsCommandException() {
        Bidder bidderInList = model.getBidderAddressBook().getBidderList().get(0);
        assertBidderCommandFailure(new AddBidderCommand(bidderInList), model,
                AddBidderCommand.MESSAGE_DUPLICATE_PERSON);
    }
}
