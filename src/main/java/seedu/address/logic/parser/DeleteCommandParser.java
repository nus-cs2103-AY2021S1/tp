package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    public static Comparator<Index> compareIndexes = new Comparator<Index>() {
        @Override
        public int compare(Index firstIndex, Index secondIndex) {
            int firstIndexValue = firstIndex.getZeroBased();
            int secondIndexValue = secondIndex.getZeroBased();
            /*For descending order*/
            return secondIndexValue - firstIndexValue;
        };
    };

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            String[] indexes = args.split("\\s+");
            List<Index> parsedIndexes = new ArrayList<>();

            for (String s : indexes) {
                if (!s.isEmpty()) {
                    Index index = ParserUtil.parseIndex(s);
                    parsedIndexes.add(index);
                }
            }

            parsedIndexes.sort(compareIndexes);

            return new DeleteCommand(parsedIndexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }
}
