package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnattendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.attendance.Attendance;
/**
 * Parses input arguments and creates a new UnattendCommand object
 */
public class UnattendCommandParser implements Parser<UnattendCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the UnattendCommand
     * and returns a UnattendCommand object for execution.
     * @param args arguments inputted into the object.
     * @return new UnattendCommand object.
     * @throws ParseException if the user input does not conform the expected format
     */
    public UnattendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        Index index;
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_DATE);

        if (argMultimap.getValue(PREFIX_DATE).isEmpty() || argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnattendCommand.MESSAGE_USAGE));
        }
        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX));
        }
        Attendance attendance = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_DATE).get());

        return new UnattendCommand(index, attendance);
    }
}
