package seedu.expense.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.expense.logic.commands.DeleteCategoryCommand;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.tag.Tag;

/**
 * Parses input arguments and creates a new DeleteCategoryCommand object.
 */
public class DeleteCategoryCommandParser implements Parser<DeleteCategoryCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCategoryCommand
     * and returns an DeleteCategoryCommand object for execution.
     *
     * @throws ParseException if the user input does not conform to the expected format.
     */
    public DeleteCategoryCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (!isTagPrefixPresent(argMultimap) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteCategoryCommand.MESSAGE_USAGE));
        }

        Tag tag = ParserUtil.parseTag(argMultimap.getValue(PREFIX_TAG).get());
        return new DeleteCategoryCommand(tag);
    }

    /**
     * Returns true if the tag prefix contains a non-empty {@code Optional} value in the given
     * {@code ArgumentMultimap}.
     */
    public static boolean isTagPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_TAG).isPresent();
    }

}
