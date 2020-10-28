package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.ParserUtil;


public class ClearCommandParser implements Parser<ClearCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ClearCommand
     * and returns a ClearCommand object for execution.
     * @throws ParseException if the user input does not conform to the expected format
     */
    public ClearCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        boolean ifNoCategory = !ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CATEGORY);
        if (ifNoCategory) {
            boolean areInvalidArgs = !argMultimap.isPreambleEmpty();

            if (areInvalidArgs) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, ClearCommand.MESSAGE_USAGE));
            }

            return new ClearCommand();
        }

        boolean areNumberOfPrefixCorrect = ParserUtil.areNumberOfPrefixesOnlyOne(argMultimap, PREFIX_CATEGORY);

        if (!areNumberOfPrefixCorrect) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_MULTIPLE_PREFIXES, ClearCommand.PREFIXES));
        }

        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());

        return new ClearCommand(category);
    }

}
