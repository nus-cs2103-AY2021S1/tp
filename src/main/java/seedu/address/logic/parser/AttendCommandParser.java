package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AttendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Attendance;

public class AttendCommandParser implements Parser<AttendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AttendCommand
     * and returns a AttendCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        if (argMultimap.getValue(PREFIX_DATE).isEmpty() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AttendCommand.MESSAGE_USAGE), pe);
        }
        Attendance attendance = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_DATE).get());

        return new AttendCommand(index, attendance);
    }

}
