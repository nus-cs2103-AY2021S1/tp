package quickcache.logic.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import quickcache.commons.core.Messages;
import quickcache.logic.commands.FindCommand;
import quickcache.logic.parser.exceptions.ParseException;
import quickcache.model.flashcard.FlashcardContainsTagPredicate;
import quickcache.model.flashcard.Tag;

/**
 * Parses input arguments and creates a new FindCommand object
 */
public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        try {
            List<Tag> tagsToMatch = Arrays.stream(trimmedArgs.split("\\s+"))
                    .map(Tag::new).collect(Collectors.toCollection(ArrayList::new));

            return new FindCommand(new FlashcardContainsTagPredicate(tagsToMatch));
        } catch (IllegalArgumentException pe) {
            throw new ParseException("Keyword must be alphanumeric");
        }
    }

}
