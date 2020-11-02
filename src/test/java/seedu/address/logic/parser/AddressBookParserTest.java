package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.biddercommands.AddBidderCommand;
import seedu.address.logic.commands.biddercommands.DeleteBidderCommand;
import seedu.address.logic.commands.biddercommands.EditBidderCommand;
import seedu.address.logic.commands.biddercommands.FindBidderCommand;
import seedu.address.logic.commands.biddercommands.ListBidderCommand;
import seedu.address.logic.commands.sellercommands.AddSellerCommand;
import seedu.address.logic.commands.sellercommands.DeleteSellerCommand;
import seedu.address.logic.commands.sellercommands.EditSellerCommand;
import seedu.address.logic.commands.sellercommands.FindSellerCommand;
import seedu.address.logic.commands.sellercommands.ListSellerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
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


    // ====================== BIDDER ============================ //
    @Test
    public void parseBidderCommand_bidderAdd() throws Exception {
        Bidder bidder = new BidderBuilder().build();
        AddBidderCommand command = (AddBidderCommand) parser.parseCommand(BidderUtil.getAddBidderCommand(bidder));
        assertEquals(new AddBidderCommand(bidder), command);
    }

    @Test
    public void parseBidderCommand_bidderEdit() throws Exception {
        Bidder bidder = new BidderBuilder().build();
        EditBidderCommand.EditBidderDescriptor descriptor = new EditBidderDescriptorBuilder(bidder).build();
        EditBidderCommand command = (EditBidderCommand) parser.parseCommand(EditBidderCommand.COMMAND_WORD
                + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + BidderUtil.getEditBidderDescriptorDetails(descriptor));
        //assertEquals(new EditBidderCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseBidderCommand_bidderFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindBidderCommand command = (FindBidderCommand) parser.parseCommand(
                FindBidderCommand.COMMAND_WORD
                        + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindBidderCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseBidderCommand_bidderDelete() throws Exception {
        DeleteBidderCommand command = (DeleteBidderCommand) parser.parseCommand(
                DeleteBidderCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteBidderCommand(INDEX_FIRST_PERSON), command);
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
    }

    @Test
    public void parseSellerCommand_sellerEdit() throws Exception {
        Seller seller = new SellerBuilder().build();
        EditSellerCommand.EditSellerDescriptor descriptor = new EditSellerDescriptorBuilder(seller).build();
        EditSellerCommand command = (EditSellerCommand) parser.parseCommand(EditSellerCommand.COMMAND_WORD
                + " " + INDEX_FIRST_PERSON.getOneBased()
                + " " + SellerUtil.getEditSellerDescriptorDetails(descriptor));
        //assertEquals(new EditSellerCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_sellerFind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindSellerCommand command = (FindSellerCommand) parser.parseCommand(
                FindSellerCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindSellerCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseSellerCommand_sellerDelete() throws Exception {
        DeleteSellerCommand command = (DeleteSellerCommand) parser.parseCommand(
                DeleteSellerCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteSellerCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseSellerCommand_sellerList() throws Exception {
        assertTrue(parser.parseCommand(ListSellerCommand.COMMAND_WORD) instanceof ListSellerCommand);
        assertTrue(parser.parseCommand(ListSellerCommand.COMMAND_WORD + " 3") instanceof ListSellerCommand);
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
