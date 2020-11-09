package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.Collection;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeleteAttendanceCommand object.
 */
public class DeleteAttendanceCommandParser implements Parser<DeleteAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteAttendanceCommand
     * and returns an DeleteAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_WEEK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAttendanceCommand.MESSAGE_USAGE),
                    e
            );
        }

        if (argumentMultimap.getAllValues(PREFIX_WEEK).isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAttendanceCommand.MESSAGE_USAGE)
            );
        }

        int[] weeksToDelete = parseWeeksToDelete(argumentMultimap.getAllValues(PREFIX_WEEK));
        return new DeleteAttendanceCommand(index, weeksToDelete);
    }

    /**
     * Parses {@code Collection<String> weeks} into a {@code int[] weeksToDelete}.
     */
    private int[] parseWeeksToDelete(Collection<String> weeks) throws ParseException {
        assert weeks != null;
        int size = weeks.size();
        String[] weeksToDeleteString = weeks.toArray(new String[0]);
        int[] weeksToDelete = new int[size];
        for (int i = 0; i < size; i++) {
            try {
                weeksToDelete[i] = Integer.parseInt(weeksToDeleteString[i]);
            } catch (NumberFormatException e) {
                throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteAttendanceCommand.MESSAGE_USAGE)
            );
            }
        }
        return weeksToDelete;
    }
}
