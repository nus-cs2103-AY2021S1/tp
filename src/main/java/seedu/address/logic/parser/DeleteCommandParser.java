package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        try {
            String[] indexes = args.split("\\s+");
            System.out.println(Arrays.toString(indexes));
            List<Index> parsedIndexes = new ArrayList<>();
            for (String s : indexes) {
                if (!s.isEmpty()) {
                    Index index = ParserUtil.parseIndex(s);
                    parsedIndexes.add(index);
                }
            }
            return new DeleteCommand(parsedIndexes);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
        }
    }

}
