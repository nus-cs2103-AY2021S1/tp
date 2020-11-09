package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Flashcard;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Priority;
import seedu.address.model.person.PriorityContainsKeywordsPredicate;
import seedu.address.model.person.TagContainsKeywordsPredicate;
import seedu.address.model.person.Title;
import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_TAG, PREFIX_PRIORITY);
        List<Predicate<Flashcard>> predicates = new ArrayList<>();
        List<String> allTitleKeywords = argMultimap.getAllValues(PREFIX_TITLE);
        List<String> allTagKeywords = argMultimap.getAllValues(PREFIX_TAG);
        List<String> allPriorityKeywords = argMultimap.getAllValues(PREFIX_PRIORITY);

        for (String str : allTitleKeywords) {
            Title keyword = ParserUtil.parseTitle(str);
            predicates.add(new NameContainsKeywordsPredicate(keyword.fullTitle));
        }

        for (String str : allTagKeywords) {
            Tag keyword = ParserUtil.parseTag(str);
            predicates.add(new TagContainsKeywordsPredicate(keyword.tagName));
        }

        for (String str : allPriorityKeywords) {
            Priority keyword = ParserUtil.parsePriority(str);
            predicates.add(new PriorityContainsKeywordsPredicate(keyword.priority));
        }


        if (predicates.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        return new FindCommand(predicates);
    }

}
