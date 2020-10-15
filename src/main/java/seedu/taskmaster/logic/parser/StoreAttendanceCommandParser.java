package seedu.taskmaster.logic.parser;

import static seedu.taskmaster.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taskmaster.logic.parser.CliSyntax.PREFIX_ATTENDANCE_FILENAME;

import java.util.NoSuchElementException;
import java.util.Optional;

import seedu.taskmaster.logic.commands.StoreAttendanceCommand;
import seedu.taskmaster.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new LoadAttendanceCommand object
 */
public class StoreAttendanceCommandParser implements Parser<StoreAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the LoadAttendanceCommand
     * and returns a LoadAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    @Override
    public StoreAttendanceCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ATTENDANCE_FILENAME);

        String filename;

        // TODO: figure out what's going on here
        try {
            Optional<String> unprocessedFilename = argMultimap.getValue(PREFIX_ATTENDANCE_FILENAME);
            unprocessedFilename.orElseThrow();

            filename = ParserUtil.parseFilename(argMultimap.getValue(PREFIX_ATTENDANCE_FILENAME).get());
        } catch (NoSuchElementException nsee) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StoreAttendanceCommand.MESSAGE_USAGE));
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StoreAttendanceCommand.MESSAGE_USAGE), pe);
        }

        return new StoreAttendanceCommand(filename);
    }
}
