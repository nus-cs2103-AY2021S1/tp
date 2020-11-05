package seedu.address.logic;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.calendarnavigation.PrevCalendarNavigationCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;

public class PrevCalendarNavigationCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new BidBook(),
                getTypicalPropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(),
                new MeetingBook());
        expectedModel = new ModelManager(new UserPrefs(), new BidBook(),
                getTypicalPropertyBook(), getTypicalBidderAddressBook(), getTypicalSellerAddressBook(),
                new MeetingBook());
    }

    // no changes should happen to any of the books
    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new PrevCalendarNavigationCommand(), model,
                PrevCalendarNavigationCommand.MESSAGE_SUCCESS, expectedModel);
    }


}
