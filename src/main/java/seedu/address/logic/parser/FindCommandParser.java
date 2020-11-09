package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.ParserUtil.FIND_WORD_POSITION;
import static seedu.address.logic.parser.ParserUtil.OBJECT_TYPE_POSITION;
import static seedu.address.logic.parser.ParserUtil.removeDash;

import java.util.Arrays;

import seedu.address.logic.command.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.accommodation.Accommodation;
import seedu.address.model.activity.Activity;
import seedu.address.model.commons.NameContainsKeywordsPredicate;
import seedu.address.model.friend.Friend;


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
        requireNonNull(args);

        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        try {
            String[] keywords = args.split(" ", FindCommand.COMMAND_TOKEN);
            String travelPlanObjectType = removeDash(keywords[OBJECT_TYPE_POSITION], FindCommand.MESSAGE_USAGE);

            String findWord = keywords[FIND_WORD_POSITION];
            String[] nameKeywords = findWord.split("\\s+");


            switch (travelPlanObjectType) {
            case Friend.TPO_WORD:
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                        ParserUtil.FRIEND_INDEX);

            case Activity.TPO_WORD:
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                        ParserUtil.ACTIVITY_INDEX);

            case Accommodation.TPO_WORD:
                return new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList(nameKeywords)),
                        ParserUtil.ACCOMMODATION_INDEX);

            default:
                throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));

            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MISSING_KEYWORDS));
        }

    }
}

