package seedu.taskmaster.logic.parser;

import static seedu.taskmaster.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_ATTENDANCE_TYPE;

import java.util.NoSuchElementException;

import seedu.taskmaster.commons.core.index.Index;
import seedu.taskmaster.logic.commands.MarkAllCommand;
import seedu.taskmaster.logic.commands.MarkCommand;
import seedu.taskmaster.logic.parser.exceptions.ParseException;
import seedu.taskmaster.model.record.AttendanceType;

/**
 * Parses input arguments and creates a new MarkCommand object
 */
public class MarkCommandParser implements Parser<MarkCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the MarkCommand
     * and returns a MarkCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public MarkCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE_TYPE);

        Index index;
        AttendanceType attendanceType;

        try {
            attendanceType = ParserUtil.parseAttendanceType(argMultimap.getValue(PREFIX_ATTENDANCE_TYPE).get());
            String preamble = argMultimap.getPreamble();

            if (preamble.equals("all")) {
                return new MarkAllCommand(attendanceType);
            } else {
                index = ParserUtil.parseIndex(preamble);
                return new MarkCommand(index, attendanceType);
            }
        } catch (ParseException | NoSuchElementException e) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, MarkCommand.MESSAGE_USAGE), e);
        }
    }
}
