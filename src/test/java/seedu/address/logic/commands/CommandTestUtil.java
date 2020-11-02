package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.MeetingBook;
import seedu.address.model.Model;
import seedu.address.model.bid.Bid;
import seedu.address.model.bid.BidContainsKeywordsPredicate;
import seedu.address.model.bidbook.BidBook;
import seedu.address.model.bidderaddressbook.BidderAddressBook;
import seedu.address.model.meeting.Meeting;
import seedu.address.model.meeting.VenueContainsKeywordsPredicate;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.seller.Seller;
import seedu.address.model.property.Property;
import seedu.address.model.propertybook.PropertyBook;
import seedu.address.model.selleraddressbook.SellerAddressBook;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_PHONE_AMY = "11111111";
    public static final String VALID_PHONE_BOB = "22222222";
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    //MEETING
    public static final String VALID_TIME_01 = "03-05-2022";
    public static final String VALID_TIME_02 = "04-06-2022";

    public static final String NAME_DESC_AMY = " " + PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + PREFIX_NAME + VALID_NAME_BOB;
    public static final String PHONE_DESC_AMY = " " + PREFIX_PHONE + VALID_PHONE_AMY;
    public static final String PHONE_DESC_BOB = " " + PREFIX_PHONE + VALID_PHONE_BOB;
    public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_NAME_DESC = " " + PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_PHONE_DESC = " " + PREFIX_PHONE + "911a"; // 'a' not allowed in phones

    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PropertyBook expectedPropertyBook = new PropertyBook(actualModel.getPropertyBook());
        BidderAddressBook expectedBidderAddressBook = new BidderAddressBook(actualModel.getBidderAddressBook());
        SellerAddressBook expectedSellerAddressBook = new SellerAddressBook(actualModel.getSellerAddressBook());
        MeetingBook expectedMeetingBook = new MeetingBook(actualModel.getMeetingBook());
        BidBook expectedBidBook = new BidBook(actualModel.getBidBook());

        List<Property> expectedFilteredProperties = new ArrayList<>(actualModel.getFilteredPropertyList());
        List<Bidder> expectedFilteredBidders = new ArrayList<>(actualModel.getFilteredBidderList());
        List<Seller> expectedFilteredSellers = new ArrayList<>(actualModel.getFilteredSellerList());
        List<Meeting> expectedFilteredMeetings = new ArrayList<>(actualModel.getFilteredMeetingList());
        List<Bid> expectedFilteredBids = new ArrayList<>(actualModel.getFilteredBidList());


        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPropertyBook, actualModel.getPropertyBook());
        assertEquals(expectedBidderAddressBook, actualModel.getBidderAddressBook());
        assertEquals(expectedSellerAddressBook, actualModel.getSellerAddressBook());
        assertEquals(expectedMeetingBook, actualModel.getMeetingBook());
        assertEquals(expectedBidBook, actualModel.getBidBook());

        assertEquals(expectedFilteredProperties, actualModel.getFilteredPropertyList());
        assertEquals(expectedFilteredBidders, actualModel.getFilteredBidderList());
        assertEquals(expectedFilteredSellers, actualModel.getFilteredSellerList());
        assertEquals(expectedFilteredMeetings, actualModel.getFilteredMeetingList());
        assertEquals(expectedFilteredBids, actualModel.getFilteredBidList());

    }

    /**
     * Updates {@code model}'s filtered list to show only the bid at the given {@code targetIndex} in the
     * {@code model}'s bid book.
     */
    public static void showBidAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredBidList().size());

        Bid bid = model.getFilteredBidList().get(targetIndex.getZeroBased());
        final String[] splitName = bid.getPropertyId().toString().split("\\s+");
        model.updateFilteredBidList(new BidContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredBidList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the meeting at the given {@code targetIndex} in the
     * {@code model}'s Meeting book.
     */
    public static void showMeetingAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredMeetingList().size());

        Meeting meeting = model.getFilteredMeetingList().get(targetIndex.getZeroBased());
        String splitInt = meeting.getVenue().venue;
        final String[] splitName = splitInt.split("\\s+");
        model.updateFilteredMeetingList(new VenueContainsKeywordsPredicate((Arrays.asList(splitName[0]))));

        assertEquals(1, model.getFilteredMeetingList().size());
    }

}
