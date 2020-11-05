package seedu.cc.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;

import seedu.cc.commons.core.category.Category;
import seedu.cc.commons.core.index.Index;
import seedu.cc.logic.commands.entrylevel.DeleteCommand;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.logic.parser.util.ArgumentMultimap;
import seedu.cc.logic.parser.util.ArgumentTokenizer;
import seedu.cc.logic.parser.util.ParserUtil;


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
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY);

        boolean arePrefixPresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CATEGORY);
        boolean isPreambleEmpty = argMultimap.isPreambleEmpty();

        if (!arePrefixPresent || isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE));
        }

        boolean areNumberOfPrefixCorrect = ParserUtil.areNumberOfPrefixesOnlyOne(argMultimap, PREFIX_CATEGORY);

        if (!areNumberOfPrefixCorrect) {
            throw new ParseException(String.format(MESSAGE_MULTIPLE_PREFIXES, DeleteCommand.PREFIXES));
        }

        Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
        Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());

        return new DeleteCommand(index, category);
    }

}
