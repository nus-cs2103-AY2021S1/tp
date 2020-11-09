package seedu.address.logic.commands.property;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.model.id.PropertyId.DEFAULT_PROPERTY_ID;
import static seedu.address.testutil.bidder.TypicalBidder.getTypicalBidderAddressBook;
import static seedu.address.testutil.property.TypicalProperties.getTypicalPropertyBook;
import static seedu.address.testutil.seller.TypicalSeller.getTypicalSellerAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.id.PropertyId;
import seedu.address.model.id.SellerId;
import seedu.address.model.property.Property;
import seedu.address.model.property.exceptions.InvalidSellerIdException;
import seedu.address.testutil.property.PropertyBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddPropertyCommand}.
 */
public class AddPropertyCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new UserPrefs(), new BidBook(), getTypicalPropertyBook(),
                getTypicalBidderAddressBook(), getTypicalSellerAddressBook(), new MeetingBook());
    }

    @Test
    public void execute_newProperty_success() {
        Property validProperty = new PropertyBuilder()
                .withPropertyId(DEFAULT_PROPERTY_ID.toString())
                .build();

        Model expectedModel = new ModelManager(
                new UserPrefs(),
                model.getBidBook(),
                model.getPropertyBook(),
                model.getBidderAddressBook(),
                model.getSellerAddressBook(),
                model.getMeetingBook()
        );
        expectedModel.addProperty(validProperty);

        assertCommandSuccess(new AddPropertyCommand(validProperty), model,
                String.format(AddPropertyCommand.MESSAGE_SUCCESS,
                        validProperty.setId(new PropertyId(4))), expectedModel);
    }

    @Test
    public void execute_duplicateProperty_throwsCommandException() {
        Property propertyInList = model.getPropertyBook().getPropertyList().get(0);
        assertCommandFailure(new AddPropertyCommand(propertyInList), model,
                AddPropertyCommand.MESSAGE_DUPLICATE_PROPERTY);
    }

    @Test
    public void execute_invalidSellerId_throwsInvalidSellerIdException() {
        SellerId sellerId = new SellerId(model.getSellerAddressBook().getSellerList().size() + 1);
        Property invalidProperty = new PropertyBuilder()
                .withPropertyId(DEFAULT_PROPERTY_ID.toString())
                .withSellerId(sellerId.toString())
                .build();
        assertCommandFailure(new AddPropertyCommand(invalidProperty), model,
                new InvalidSellerIdException().getMessage());
    }
}
