package quickcache.logic.parser;

import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.Set;
import java.util.function.Predicate;

import quickcache.commons.core.Messages;
import quickcache.commons.core.index.Index;
import quickcache.logic.commands.StatsCommand;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.Tag;

/**
 * Parses input arguments and creates a new StatsCommand object
 */
public class StatsCommandParser implements Parser<StatsCommand> {

    /**
     * Returns true if the tag prefix has at least one value.
     */
    private static boolean isTagPrefixPresent(ArgumentMultimap argumentMultimap) {
        return argumentMultimap.getValue(PREFIX_TAG).isPresent();
    }

    /**
     * Parses the given {@code String} of arguments in the context of the StatsCommand
     * and returns a StatsCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public StatsCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
            ArgumentTokenizer.tokenize(args, PREFIX_TAG);

        if (isTagPrefixPresent(argMultimap)) {
            if (!argMultimap.getPreamble().isEmpty()) {
                // there shouldn't be a preamble together with the tag prefix
                throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                        StatsCommand.MESSAGE_USAGE));
            }
            Set<Tag> tagsToMatch = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
            FlashcardPredicate predicate = getFlashcardPredicate(tagsToMatch);
            return new StatsCommand(predicate, tagsToMatch);
        } else {
            try {
                Index index = ParserUtil.parseIndex(argMultimap.getPreamble());
                return new StatsCommand(index);
            } catch (ParseException pe) {
                throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, StatsCommand.MESSAGE_USAGE), pe);
            }
        }
    }

    private FlashcardPredicate getFlashcardPredicate(Set<Tag> tagsToMatch) {
        ArrayList<Predicate<Flashcard>> predicates = new ArrayList<>();

        if (!tagsToMatch.isEmpty()) {
            predicates.add(new FlashcardContainsTagPredicate(tagsToMatch));
        }
        return new FlashcardPredicate(predicates);
    }

}
