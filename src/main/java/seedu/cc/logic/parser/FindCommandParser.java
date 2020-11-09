package seedu.cc.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.commons.core.Messages.MESSAGE_MULTIPLE_PREFIXES;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_KEYWORDS;

import java.util.List;

import seedu.cc.commons.core.category.Category;
import seedu.cc.logic.commands.entrylevel.FindCommand;
import seedu.cc.logic.parser.exceptions.ParseException;
import seedu.cc.logic.parser.util.ArgumentMultimap;
import seedu.cc.logic.parser.util.ArgumentTokenizer;
import seedu.cc.logic.parser.util.ParserUtil;
import seedu.cc.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.cc.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;

public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_KEYWORDS);
        handleArgMultimapException(argMultimap);

        List<String> keywords = ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_KEYWORDS).get());
        handleEmptyKeywordListException(keywords);

        boolean isCategoryPrefixPresent = ParserUtil.arePrefixesPresent(argMultimap, PREFIX_CATEGORY);

        if (isCategoryPrefixPresent) {
            Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
            if (category.isExpense()) {
                return new FindCommand(new ExpenseDescriptionContainsKeywordsPredicate(keywords));
            } else {
                assert category.isRevenue();
                return new FindCommand(new RevenueDescriptionContainsKeywordsPredicate(keywords));
            }
        } else {
            return new FindCommand(new ExpenseDescriptionContainsKeywordsPredicate(keywords),
                new RevenueDescriptionContainsKeywordsPredicate(keywords));
        }
    }

    private void handleArgMultimapException(ArgumentMultimap multimap) throws ParseException {
        boolean isKeywordPrefixPresent = ParserUtil.arePrefixesPresent(multimap, PREFIX_KEYWORDS);
        boolean isPreambleEmpty = multimap.getPreamble().isEmpty();

        // Check if there is a keyword prefix and no preambles
        if (!isKeywordPrefixPresent || !isPreambleEmpty) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        // Check if keyword prefix are only used once or none
        boolean isNumberOfKeywordPrefixesCorrect = ParserUtil.areNumberOfPrefixesOnlyOne(multimap, PREFIX_KEYWORDS);

        // Check if optional prefixes are only used once or none
        boolean isNumberOfOtherPrefixesCorrect =
            ParserUtil.areNumberOfPrefixesOneOrNone(multimap, PREFIX_CATEGORY);

        if (!isNumberOfKeywordPrefixesCorrect || !isNumberOfOtherPrefixesCorrect) {
            throw new ParseException(String.format(MESSAGE_MULTIPLE_PREFIXES, FindCommand.PREFIXES));
        }
    }

    private void handleEmptyKeywordListException(List<String> keywords) throws ParseException {
        // Check if keyword list is empty
        boolean isKeywordListEmpty = keywords.size() == 1 && keywords.get(0).equals("");
        if (isKeywordListEmpty) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.EMPTY_KEYWORD_LIST_MESSAGE));
        }
    }
}
