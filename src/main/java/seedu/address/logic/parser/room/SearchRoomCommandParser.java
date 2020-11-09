package seedu.address.logic.parser.room;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.room.SearchRoomCommand.MESSAGE_USAGE;
import static seedu.address.logic.parser.patient.PatientCliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.room.RoomCliSyntax.PREFIX_ROOM_NUMBER;

import seedu.address.logic.commands.room.SearchRoomCommand;
import seedu.address.logic.parser.ArgumentMultimap;
import seedu.address.logic.parser.ArgumentTokenizer;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.patient.PatientParserUtil;
import seedu.address.model.patient.Name;

//@@author chiamyunqing
/**
 * Parses input arguments and creates a new SearchRoomCommand object.
 */
public class SearchRoomCommandParser implements Parser<SearchRoomCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of SearchRoomCommand
     * and returns a SearchRoomCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public SearchRoomCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_ROOM_NUMBER);

        if (!ParserUtil.isExactlyOnePrefixPresent(argMultimap, PREFIX_NAME, PREFIX_ROOM_NUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
        }

        SearchRoomCommand.SearchRoomDescriptor descriptor = new SearchRoomCommand.SearchRoomDescriptor();

        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            Name patientName = PatientParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
            descriptor.setPatientName(patientName);
            return new SearchRoomCommand(descriptor);
        }
        //definitely have prefix room number if no prefix name
        Integer roomNumber = ParserUtil.parseRoomNumber(argMultimap.getValue(PREFIX_ROOM_NUMBER).get());
        descriptor.setRoomNumber(roomNumber);
        return new SearchRoomCommand(descriptor);

    }
}
