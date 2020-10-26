package seedu.address.logic.parser;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ARCHIVE;

import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class ListCommandParser implements Parser<ListCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ListCommand parse(String args) {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_ARCHIVE);

        if (argMultimap.getValue(PREFIX_ARCHIVE).isPresent()) {
            boolean isArchiveMode = true;
            return new ListCommand(isArchiveMode);
        } else {
            return new ListCommand();
        }
    }
}
