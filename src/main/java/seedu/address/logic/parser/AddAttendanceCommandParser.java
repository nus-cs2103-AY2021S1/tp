package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEEK;

import java.util.Collection;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.AddAttendanceCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new AddAttendanceCommand object.
 */
public class AddAttendanceCommandParser implements Parser<AddAttendanceCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddAttendanceCommand
     * and returns an AddAttendanceCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddAttendanceCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argumentMultimap = ArgumentTokenizer.tokenize(args, PREFIX_WEEK);

        Index index;

        try {
            index = ParserUtil.parseIndex(argumentMultimap.getPreamble());
        } catch (ParseException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttendanceCommand.MESSAGE_USAGE),
                    e
            );
        }

        if (argumentMultimap.getAllValues(PREFIX_WEEK).isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttendanceCommand.MESSAGE_USAGE));
        }

        int[] weeksToAdd = parseWeeksToAdd(argumentMultimap.getAllValues(PREFIX_WEEK));
        return new AddAttendanceCommand(index, weeksToAdd);
    }

    /**
     * Parses {@code Collection<String> weeks} into a {@code int[] weeksToAdd}.
     */
    private int[] parseWeeksToAdd(Collection<String> weeks) throws ParseException {
        assert weeks != null;
        int size = weeks.size();
        String[] weeksToAddString = weeks.toArray(new String[0]);
        int[] weeksToAdd = new int[size];
        for (int i = 0; i < size; i++) {
            try {
                weeksToAdd[i] = Integer.parseInt(weeksToAddString[i]);
            } catch (NumberFormatException e) {
                throw new ParseException(
                        String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddAttendanceCommand.MESSAGE_USAGE)
                );
            }
        }
        return weeksToAdd;
    }
}
