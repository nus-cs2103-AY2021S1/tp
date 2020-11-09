package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.commands.CommandTestUtil.PERSON_ID_DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PERSON_ID_AMY;
import static seedu.address.logic.parser.CliSyntax.*;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.*;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GetBillCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.roomservice.RoomService;
import seedu.address.model.roomservice.RoomServiceType;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Integer testId = 0;
        Person person = new PersonBuilder().withId(testId).build();
        Person.setNextAvailableId(testId);
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + PERSON_ID_DESC_AMY);
        assertEquals(new DeleteCommand(VALID_PERSON_ID_AMY), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + PERSON_ID_DESC_AMY + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(VALID_PERSON_ID_AMY, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_listBooking() throws Exception {
        assertTrue(parser.parseCommand(ListBookingCommand.COMMAND_WORD) instanceof ListBookingCommand);
    }

    @Test
    public void parseCommand_checkIn() throws Exception {
        final int personId = 123;
        final int roomId = 2103;
        final String startDate = "2020-06-22";
        final String endDate = "2020-11-29";


        AddBookingCommand command = (AddBookingCommand) parser.parseCommand(AddBookingCommand.COMMAND_WORD + " "
            + PREFIX_PERSON_ID + personId + " "
            + PREFIX_ROOM_ID + roomId + " "
            + PREFIX_START_DATE + startDate + " "
            + PREFIX_END_DATE + endDate);

        assertEquals(new AddBookingCommand(personId, roomId,
                LocalDate.parse(startDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
                LocalDate.parse(endDate, DateTimeFormatter.ofPattern("yyyy-MM-dd"))),
                command);
    }

    @Test
    public void parseCommand_archiveBooking() throws Exception {
        final int roomId = 2103;
        ArchiveBookingCommand command = (ArchiveBookingCommand) parser.parseCommand(
                ArchiveBookingCommand.COMMAND_WORD + " "
            + PREFIX_BOOKING_ID + roomId);

        assertEquals(new ArchiveBookingCommand(roomId), command);
    }

    @Test
    public void parseCommand_getBill() throws Exception {
        final int bookingId = 40;
        GetBillCommand command = (GetBillCommand) parser.parseCommand(GetBillCommand.COMMAND_WORD + " "
            + PREFIX_BOOKING_ID + bookingId);

        assertEquals(new GetBillCommand(bookingId), command);
    }

    @Test
    public void parseCommand_orderRoomService() throws Exception {
        RoomServiceCommand command = (RoomServiceCommand) parser.parseCommand(RoomServiceCommand.COMMAND_WORD
                + " " + PREFIX_BOOKING_ID + "1 " + PREFIX_ROOM_SERVICE_TYPE + RoomServiceType.WIFI.getName());

        assertEquals(new RoomServiceCommand(new RoomService(1, RoomServiceType.WIFI)), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
