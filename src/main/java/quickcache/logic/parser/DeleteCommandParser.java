package quickcache.logic.parser;

import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.DeleteCommand;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.Tag;


/**
 * Parses input arguments and creates a new DeleteCommand object
 */
public class DeleteCommandParser implements Parser<DeleteCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the DeleteCommand
     * and returns a DeleteCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeleteCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (isTagPrefixPresent(argMultimap)) {
            if (!argMultimap.getPreamble().isEmpty()) {
                // there shouldn't be a preamble together with the tag prefix
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        DeleteCommand.MESSAGE_USAGE));
            }
            Set<Tag> tagsToMatch = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            FlashcardPredicate predicate = FlashcardPredicate.prepareOnlyTagsFlashcardPredicate(tagsToMatch);
            return DeleteCommand.withPredicate(predicate, tagsToMatch);
        } else {
            try {
                Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
                return DeleteCommand.withIndex(index);
            } catch (ParseException pe) {
                throw new ParseException(
                        String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, DeleteCommand.MESSAGE_USAGE), pe);
            }
        }
    }

    /**
     * Returns true if the tag prefix has at least one value.
     */
    private static boolean isTagPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_TAG).isPresent();
    }

}
