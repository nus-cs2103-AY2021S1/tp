package seedu.address.logic.commands.seller;

import static seedu.address.logic.commands.seller.SellerCommandTestUtil.assertSellerCommandFailure;
import static seedu.address.logic.commands.seller.SellerCommandTestUtil.assertSellerCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.sellercommands.AddSellerCommand;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.person.seller.Seller;
import seedu.address.testutil.seller.SellerBuilder;

public class AddSellerCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs(), new BidBook(), getTypicalPropertyBook(),
                getTypicalBidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
    }

    @Test
    public void execute_newSeller_success() {
        Seller validSeller = new SellerBuilder().build().setSellerTag();

        Model expectedModel = new ModelManager(
                model.getAddressBook(),
                new UserPrefs(),
                model.getBidBook(),
                model.getPropertyBook(),
                model.getBidderAddressBook(),
                model.getSellerAddressBook(),
                model.getMeetingBook()
        );

        expectedModel.addSeller(validSeller);

        assertSellerCommandSuccess(new AddSellerCommand(validSeller.setDefaultSellerId()), model,
                String.format(AddSellerCommand.MESSAGE_SUCCESS, validSeller), expectedModel);
    }

    @Test
    public void execute_duplicateSeller_throwsCommandException() {
        Seller sellerInList = model.getSellerAddressBook().getSellerList().get(0);
        assertSellerCommandFailure(new AddSellerCommand(sellerInList), model,
                AddSellerCommand.MESSAGE_DUPLICATE_PERSON);
    }
}
