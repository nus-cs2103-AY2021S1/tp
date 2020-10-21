package quickcache.logic.parser;

import static quickcache.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static quickcache.logic.parser.CliSyntax.PREFIX_QUESTION;
import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Stream;

import quickcache.logic.commands.FindCommand;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;
import quickcache.model.flashcard.FlashcardPredicate;
import quickcache.model.flashcard.QuestionContainsKeywordsPredicate;
import quickcache.model.flashcard.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean areSomePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).anyMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_QUESTION, PREFIX_TAG);

        if (!areSomePrefixesPresent(argMultimap, PREFIX_QUESTION, PREFIX_TAG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    FindCommand.MESSAGE_USAGE));
        }

        Set<Tag> tagsToMatch = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        List<String> questionKeywords = ParserUtil.parseKeywords(argMultimap.getAllValues(PREFIX_QUESTION));

        assert !(tagsToMatch.isEmpty() && questionKeywords.isEmpty());

        FlashcardPredicate predicate = getFlashcardPredicate(tagsToMatch, questionKeywords);

        return new FindCommand(predicate);
    }

    private FlashcardPredicate getFlashcardPredicate(Set<Tag> tagsToMatch, List<String> questionKeywords) {
        ArrayList<Predicate<Flashcard>> predicates = new ArrayList<>();

        assert !(tagsToMatch.isEmpty() && questionKeywords.isEmpty());

        if (!tagsToMatch.isEmpty()) {
            predicates.add(new FlashcardContainsTagPredicate(tagsToMatch));
        }

        if (!questionKeywords.isEmpty()) {
            predicates.add(new QuestionContainsKeywordsPredicate(questionKeywords));
        }

        assert !predicates.isEmpty();
        return new FlashcardPredicate(predicates);
    }

}
