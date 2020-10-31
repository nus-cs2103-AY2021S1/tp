package seedu.cc.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;

import seedu.cc.commons.core.Messages;
import seedu.cc.commons.core.category.Category;
import seedu.cc.logic.commands.entrylevel.ClearCommand;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.logic.parser.util.ArgumentMultimap;
import seedu.cc.logic.parser.util.ArgumentTokenizer;
import seedu.cc.logic.parser.util.ParserUtil;


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
