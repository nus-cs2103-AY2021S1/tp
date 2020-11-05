package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.ANCHORVALE_BUILDER;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.EDIT_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.FIND_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_ASKING_PRICE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_IS_CLOSED_DEAL;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_IS_RENTAL;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_NAME;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_PROPERTY_TYPE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.INVALID_PROPERTY_SELLER_ID;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.PROPERTY_ID_DESC_ANCHORVALE;
import static seedu.address.logic.commands.property.PropertyCommandTestUtil.VALID_PROPERTY_PROPERTY_ID_ANCHORVALE;
import static seedu.address.logic.parser.property.PropertyCommandInputBuilder.getValidAddPropertyCommandInput;
import static seedu.address.logic.parser.property.PropertyCommandInputBuilder.getValidEditPropertyCommand;
import static seedu.address.logic.parser.property.PropertyCommandInputBuilder.getValidFindPropertyCommand;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROPERTY;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.biddercommands.AddBidderCommand;
import seedu.address.logic.commands.biddercommands.DeleteBidderCommand;
import seedu.address.logic.commands.biddercommands.EditBidderCommand;
import seedu.address.logic.commands.biddercommands.FindBidderCommand;
import seedu.address.logic.commands.biddercommands.ListBidderCommand;
import seedu.address.logic.commands.calendarnavigation.NextCalendarNavigationCommand;
import seedu.address.logic.commands.calendarnavigation.PrevCalendarNavigationCommand;
import seedu.address.logic.commands.property.AddPropertyCommand;
import seedu.address.logic.commands.property.DeletePropertyCommand;
import seedu.address.logic.commands.property.EditPropertyCommand;
import seedu.address.logic.commands.property.FindPropertyCommand;
import seedu.address.logic.commands.property.ListPropertyCommand;
import seedu.address.logic.commands.sellercommands.AddSellerCommand;
import seedu.address.logic.commands.sellercommands.DeleteSellerCommand;
import seedu.address.logic.commands.sellercommands.EditSellerCommand;
import seedu.address.logic.commands.sellercommands.FindSellerCommand;
import seedu.address.logic.commands.sellercommands.ListSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.property.PropertyCommandInputBuilder;
import seedu.address.model.id.PropertyId;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.bidder.Bidder;
import seedu.address.model.person.seller.Seller;
import seedu.address.testutil.bidder.BidderBuilder;
import seedu.address.testutil.bidder.BidderUtil;
import seedu.address.testutil.bidder.EditBidderDescriptorBuilder;
import seedu.address.testutil.seller.EditSellerDescriptorBuilder;
import seedu.address.testutil.seller.SellerBuilder;
import seedu.address.testutil.seller.SellerUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();


    // ====================== CALENDAR NAVIGATION ============================ //
    @Test
    public void calendarNavigation_nextCommandThrowsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand(NextCalendarNavigationCommand.COMMAND_WORD + "rubbish"));

        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("TESITNG" + NextCalendarNavigationCommand.COMMAND_WORD + " p/testing"));
    }

    @Test
    public void calendarNavigation_nextCommandSuccess() throws ParseException {
        assertTrue(parser.parseCommand(NextCalendarNavigationCommand.COMMAND_WORD)
                instanceof NextCalendarNavigationCommand);
    }

    @Test
    public void calendarNavigation_prevCommandThrowsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand(PrevCalendarNavigationCommand.COMMAND_WORD + "what"));

        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () ->
                parser.parseCommand("TESITNG" + PrevCalendarNavigationCommand.COMMAND_WORD + " p/testing"));
    }

    @Test
    public void calendarNavigation_prevCommandSuccess() throws ParseException {
        assertTrue(parser.parseCommand(PrevCalendarNavigationCommand.COMMAND_WORD)
                instanceof PrevCalendarNavigationCommand);
    }

    // ====================== BIDDER ============================ //
    @Test
    public void parseBidderCommand_bidderAdd() throws Exception {
        Bidder bidder = new BidderBuilder().build();
        AddBidderCommand command = (AddBidderCommand) parser.parseCommand(BidderUtil.getAddBidderCommand(bidder));
        assertEquals(new AddBidderCommand(bidder), command);

        // invalid input test
        // opposite input
        assertThrows(ParseException.class, () ->
                parser.parseCommand(AddBidderCommand.COMMAND_WORD + "n/12345 p/Kor Ming Soon"));
        // negative phone number
        assertThrows(ParseException.class, () ->
                parser.parseCommand(AddBidderCommand.COMMAND_WORD + "n/Ming Soon p/-78945"));
        // missing field - name
        assertThrows(ParseException.class, () ->
                parser.parseCommand(AddBidderCommand.COMMAND_WORD + "p/123456789"));
        // missing field - phone
        assertThrows(ParseException.class, () ->
                parser.parseCommand(AddBidderCommand.COMMAND_WORD + "n/Kor Ming Soon"));
    }

    @Test
    public void parseBidderCommand_bidderEdit() throws Exception {
        Bidder bidder = new BidderBuilder().build();
        EditBidderCommand.EditBidderDescriptor descriptor = new EditBidderDescriptorBuilder(bidder).build();
        EditBidderCommand command = (EditBidderCommand) parser.parseCommand(EditBidderCommand.COMMAND_WORD
                + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + BidderUtil.getEditBidderDescriptorDetails(descriptor));
        assertEquals(new EditBidderCommand(INDEX_FIRST_PERSON, descriptor), command);

        // invalid input test
        // name with number input
        assertThrows(ParseException.class, () ->
                parser.parseCommand(EditBidderCommand.COMMAND_WORD + "1 n/67890"));
        // phone with negative input
        assertThrows(ParseException.class, () ->
                parser.parseCommand(EditBidderCommand.COMMAND_WORD + "1 p/-1234"));
        // incorrect index number
        assertThrows(ParseException.class, () ->
                parser.parseCommand(EditBidderCommand.COMMAND_WORD + "-1 n/Kor Ming Soon"));
        // missing prefix
        assertThrows(ParseException.class, () ->
                parser.parseCommand(EditBidderCommand.COMMAND_WORD + "1 111"));
    }

    @Test
    public void parseBidderCommand_bidderFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindBidderCommand command = (FindBidderCommand) parser.parseCommand(
                FindBidderCommand.COMMAND_WORD
                        + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindBidderCommand(new NameContainsKeywordsPredicate(keywords)), command);

        // invalid input test
        // missing index
        assertThrows(ParseException.class, () ->
                parser.parseCommand(FindBidderCommand.COMMAND_WORD + "aaaaaaaa"));
        // number placed in "name" input field
        assertThrows(ParseException.class, () ->
                parser.parseCommand(FindBidderCommand.COMMAND_WORD + "1 159753"));
        // negative index
        assertThrows(ParseException.class, () ->
                parser.parseCommand(FindBidderCommand.COMMAND_WORD + "-1 Kor Ming Soon"));
    }

    @Test
    public void parseBidderCommand_bidderDelete() throws Exception {
        DeleteBidderCommand command = (DeleteBidderCommand) parser.parseCommand(
                DeleteBidderCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteBidderCommand(INDEX_FIRST_PERSON), command);

        // invalid input test
        // negative number
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeleteBidderCommand.COMMAND_WORD + "-1"));
        // decimal
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeleteBidderCommand.COMMAND_WORD + "0.1"));
        // wrong number
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeleteBidderCommand.COMMAND_WORD + "0"));
        // empty
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeleteBidderCommand.COMMAND_WORD + ""));
    }

    @Test
    public void parseBidderCommand_bidderList() throws Exception {
        assertTrue(parser.parseCommand(ListBidderCommand.COMMAND_WORD) instanceof ListBidderCommand);
        assertTrue(parser.parseCommand(ListBidderCommand.COMMAND_WORD + " 3") instanceof ListBidderCommand);
    }

    // ====================== SELLER ============================ //

    @Test
    public void parseSellerCommand_sellerAdd() throws Exception {
        Seller seller = new SellerBuilder().build();
        AddSellerCommand command = (AddSellerCommand) parser.parseCommand(SellerUtil.getAddSellerCommand(seller));
        assertEquals(new AddSellerCommand(seller), command);

        // invalid input test
        assertThrows(ParseException.class, () ->
                parser.parseCommand(AddSellerCommand.COMMAND_WORD + "n/12345 p/Kor Ming Soon"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(AddSellerCommand.COMMAND_WORD + "n/Ming Soon p/-78945"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(AddSellerCommand.COMMAND_WORD + "p/123456789"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(AddSellerCommand.COMMAND_WORD + "n/Kor Ming Soon"));
    }

    @Test
    public void parseSellerCommand_sellerEdit() throws Exception {
        Seller seller = new SellerBuilder().build();
        EditSellerCommand.EditSellerDescriptor descriptor =
                new EditSellerDescriptorBuilder(seller).build();
        EditSellerCommand command = (EditSellerCommand) parser.parseCommand(EditSellerCommand.COMMAND_WORD
                + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + SellerUtil.getEditSellerDescriptorDetails(descriptor));
        assertEquals(new EditSellerCommand(INDEX_FIRST_PERSON, descriptor), command);

        // invalid input test
        assertThrows(ParseException.class, () ->
                parser.parseCommand(EditSellerCommand.COMMAND_WORD + "1 n/67890"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(EditSellerCommand.COMMAND_WORD + "1 p/-1234"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(EditSellerCommand.COMMAND_WORD + "-1 n/Kor Ming Soon"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(EditSellerCommand.COMMAND_WORD + "1 111"));
    }

    @Test
    public void parseCommand_sellerFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindSellerCommand command = (FindSellerCommand) parser.parseCommand(
                FindSellerCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindSellerCommand(new NameContainsKeywordsPredicate(keywords)), command);

        // invalid input test
        assertThrows(ParseException.class, () ->
                parser.parseCommand(FindSellerCommand.COMMAND_WORD + "aaaaaaaa"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(FindSellerCommand.COMMAND_WORD + "1 159753"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(FindSellerCommand.COMMAND_WORD + "-1 Kor Ming Soon"));
    }

    @Test
    public void parseSellerCommand_sellerDelete() throws Exception {
        DeleteSellerCommand command = (DeleteSellerCommand) parser.parseCommand(
                DeleteSellerCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteSellerCommand(INDEX_FIRST_PERSON), command);

        // invalid input test
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeleteSellerCommand.COMMAND_WORD + "-1"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeleteSellerCommand.COMMAND_WORD + "0.1"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeleteSellerCommand.COMMAND_WORD + "0"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeleteSellerCommand.COMMAND_WORD + ""));
    }

    @Test
    public void parseSellerCommand_sellerList() throws Exception {
        assertTrue(parser.parseCommand(ListSellerCommand.COMMAND_WORD) instanceof ListSellerCommand);
        assertTrue(parser.parseCommand(ListSellerCommand.COMMAND_WORD + " 3") instanceof ListSellerCommand);
    }

    // ====================== PROPERTY ============================ //

    @Test
    public void parseAddPropertyCommand_missingCompulsoryFields_throwsParseException() {

        // missing all fields
        assertThrows(ParseException.class, () -> parser.parseCommand(AddPropertyCommand.COMMAND_WORD));

        // missing propertyName
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withPropertyName("").build()));

        // missing address
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withAddress("").build()));

        // missing propertyType
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withPropertyType("").build()));

        // missing sellerId
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withSellerId("").build()));

        // missing isRental
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withIsRental("").build()));

        // missing askingPrice
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withAskingPrice("").build()));
    }

    @Test
    public void parseAddPropertyCommand_invalidInput_throwsParseException() {
        // invalid propertyName
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withPropertyName(INVALID_PROPERTY_NAME).build()));

        // invalid seller id
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withSellerId(INVALID_PROPERTY_SELLER_ID).build()));

        // invalid property type
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withPropertyType(INVALID_PROPERTY_PROPERTY_TYPE).build()));

        // invalid asking price
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withAskingPrice(INVALID_PROPERTY_ASKING_PRICE).build()));

        // invalid is rental
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidAddPropertyCommandInput()
                        .withIsRental(INVALID_PROPERTY_IS_RENTAL).build()));
    }

    @Test
    public void parseAddPropertyCommand_validInput_returnsAddCommand() throws ParseException {
        Command command = parser.parseCommand(getValidAddPropertyCommandInput().build());
        AddPropertyCommand expected = new AddPropertyCommand(ANCHORVALE_BUILDER
                .withPropertyId(PropertyId.DEFAULT_PROPERTY_ID.toString())
                .build());
        assertEquals(expected, command);
    }

    @Test
    public void parseDeletePropertyCommand_invalidInput_throwsParseException() {
        // missing index and property id
        assertThrows(ParseException.class, () -> parser.parseCommand(DeletePropertyCommand.COMMAND_WORD));

        // invalid
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeletePropertyCommand.COMMAND_WORD + "a"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeletePropertyCommand.COMMAND_WORD + "0"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeletePropertyCommand.COMMAND_WORD + "-1"));
        assertThrows(ParseException.class, () ->
                parser.parseCommand(DeletePropertyCommand.COMMAND_WORD + "1 P1"));
    }

    @Test
    public void parseDeletePropertyCommand_validInput_returnsDeleteCommand() throws ParseException {
        // valid index
        Command command = parser.parseCommand(new PropertyCommandInputBuilder()
                .withCommandWord(DeletePropertyCommand.COMMAND_WORD).withIndex("1").build());
        DeletePropertyCommand expected = new DeletePropertyCommand(INDEX_FIRST_PROPERTY, null);
        assertEquals(expected, command);

        // valid property id
        command = parser.parseCommand(new PropertyCommandInputBuilder()
                .withCommandWord(DeletePropertyCommand.COMMAND_WORD)
                .withPropertyId(VALID_PROPERTY_PROPERTY_ID_ANCHORVALE)
                .build());
        expected = new DeletePropertyCommand(null, ANCHORVALE_BUILDER.build().getPropertyId());
        assertEquals(expected, command);
    }

    @Test
    public void parseListPropertyCommand_returnsListPropertyCommand() throws ParseException {
        assertEquals(new ListPropertyCommand(),
                parser.parseCommand(ListPropertyCommand.COMMAND_WORD));
        assertEquals(new ListPropertyCommand(),
                parser.parseCommand(ListPropertyCommand.COMMAND_WORD + " some rubbish"));
    }

    @Test
    public void parseEditPropertyCommand_invalidInput_throwsParseException() {

        // no index
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidEditPropertyCommand()
                        .withIndex("").build()));

        // no fields
        assertThrows(ParseException.class, () -> parser.parseCommand(new PropertyCommandInputBuilder()
                        .withCommandWord(EditPropertyCommand.COMMAND_WORD)
                        .withIndex("1")
                        .build()));

        // not allowed to edit property id
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidEditPropertyCommand()
                        .withPropertyId(PROPERTY_ID_DESC_ANCHORVALE)
                        .build()));

        // invalid propertyName
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidEditPropertyCommand()
                        .withPropertyName(INVALID_PROPERTY_NAME).build()));

        // invalid seller id
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidEditPropertyCommand()
                        .withSellerId(INVALID_PROPERTY_SELLER_ID).build()));

        // invalid property type
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidEditPropertyCommand()
                        .withPropertyType(INVALID_PROPERTY_PROPERTY_TYPE).build()));

        // invalid asking price
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidEditPropertyCommand()
                        .withAskingPrice(INVALID_PROPERTY_ASKING_PRICE).build()));

        // invalid is rental
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidEditPropertyCommand()
                        .withIsRental(INVALID_PROPERTY_IS_RENTAL).build()));
    }

    @Test
    public void parseEditPropertyCommand_validInput_returnsEditPropertyCommand() throws ParseException {
        EditPropertyCommand expected = new EditPropertyCommand(Index.fromOneBased(1), EDIT_DESC_ANCHORVALE);
        Command command = parser.parseCommand(getValidEditPropertyCommand().build());
        assertEquals(expected, command);
    }

    @Test
    public void parseFindPropertyCommand_invalidInput_throwsParseException() {
        // missing all fields
        assertThrows(ParseException.class, () -> parser.parseCommand(FindPropertyCommand.COMMAND_WORD));

        // invalid asking price
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidFindPropertyCommand()
                        .withAskingPrice(INVALID_PROPERTY_ASKING_PRICE).build()));

        // invalid is rental
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidFindPropertyCommand()
                        .withIsRental(INVALID_PROPERTY_IS_RENTAL).build()));

        // invalid is closed deal
        assertThrows(ParseException.class, () -> parser.parseCommand(getValidFindPropertyCommand()
                        .withIsClosedDeal(INVALID_PROPERTY_IS_CLOSED_DEAL)
                        .build()));
    }

    @Test
    public void parseFindPropertyCommand_validInput_returnsFindPropertyCommand() throws ParseException {
        Command command = parser.parseCommand(getValidFindPropertyCommand().build());
        FindPropertyCommand expected = new FindPropertyCommand(FIND_DESC_ANCHORVALE);
        assertEquals(expected, command);
    }

    // ================= MISC =========================== //
    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), () -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class,
                MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

}
