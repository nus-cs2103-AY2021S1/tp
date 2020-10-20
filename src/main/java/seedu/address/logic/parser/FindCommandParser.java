package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_KEYWORDS;

import java.util.List;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.logic.parser.util.ArgumentMultimap;
import seedu.address.logic.parser.util.ArgumentTokenizer;
import seedu.address.logic.parser.util.ParserUtil;
import seedu.address.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.address.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;

public class FindCommandParser implements Parser<FindCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the FindCommand
     * and returns a FindCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public FindCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_CATEGORY, PREFIX_KEYWORDS);
        List<String> keywords = ParserUtil.parseKeywords(argMultimap.getValue(PREFIX_KEYWORDS).get());
        boolean isCategoryPrefixPresent = (argMultimap.getAllValues(PREFIX_CATEGORY).size() > 0);
        boolean isKeywordListNotEmpty = keywords.size() == 1 && keywords.get(0).equals("");

        if (!ParserUtil.arePrefixesPresent(argMultimap, PREFIX_KEYWORDS)
            || isKeywordListNotEmpty
            || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
        }

        if (isCategoryPrefixPresent) {
            Category category = ParserUtil.parseCategory(argMultimap.getValue(PREFIX_CATEGORY).get());
            if (category.isExpense()) {
                return new FindCommand(new ExpenseDescriptionContainsKeywordsPredicate(keywords));
            } else if (category.isRevenue()) {
                return new FindCommand(new RevenueDescriptionContainsKeywordsPredicate(keywords));
            } else {
                return null;
            }
        } else {
            return new FindCommand(new ExpenseDescriptionContainsKeywordsPredicate(keywords),
                new RevenueDescriptionContainsKeywordsPredicate(keywords));
        }
    }

}
