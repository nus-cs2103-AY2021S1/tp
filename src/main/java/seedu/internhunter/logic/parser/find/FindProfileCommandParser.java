package seedu.internhunter.logic.parser.find;

import static java.util.Objects.requireNonNull;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.checkCommandDetailsIsNotBlank;
import static seedu.internhunter.logic.parser.util.GeneralParserUtil.getTrimmedArgsKeywords;

import java.util.Arrays;

import seedu.internhunter.logic.commands.find.FindProfileCommand;
import seedu.internhunter.logic.parser.Parser;
import seedu.internhunter.logic.parser.exceptions.ParseException;
import seedu.internhunter.model.profile.ProfileItemContainsKeywordPredicate;

/**
 * Parses input arguments and creates a new FindProfileCommand object.
 */
public class FindProfileCommandParser implements Parser<FindProfileCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindProfileCommand
     * and returns an FindProfileCommand object for execution.
     *
     * @param args to be parsed.
     * @return FindProfileCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format.
     */
    @Override
    public FindProfileCommand parse(String args) throws ParseException {
        requireNonNull(args);
        checkCommandDetailsIsNotBlank(args, FindProfileCommand.MESSAGE_USAGE);
        String[] profileItemTitleKeywords = getTrimmedArgsKeywords(args);
        ProfileItemContainsKeywordPredicate predicate =
                new ProfileItemContainsKeywordPredicate(Arrays.asList(profileItemTitleKeywords));
        return new FindProfileCommand(predicate);
    }
}
