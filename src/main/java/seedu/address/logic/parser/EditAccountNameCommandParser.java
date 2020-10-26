package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.EditAccountNameCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.ParserUtil;
import seedu.address.model.account.Name;


/**
 * Parses input arguments and creates a new EditAccountCommand object
 */
public class EditAccountNameCommandParser implements Parser<EditAccountNameCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditAccountCommand
     * and returns an EditAccountCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditAccountNameCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        boolean isPrefixPresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_NAME);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();

        if (!isPrefixPresent || !isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditAccountNameCommand.MESSAGE_USAGE));
        }

        boolean areNumberOfPrefixCorrect = ParserUtil.areNumberOfPrefixesOnlyOne(argMultimap, PREFIX_NAME);

        if (!areNumberOfPrefixCorrect) {
            throw new ParseException(String.format(MESSAGE_MULTIPLE_PREFIXES, EditAccountNameCommand.PREFIXES));
        }

        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());

        return new EditAccountNameCommand(name);
    }

}
