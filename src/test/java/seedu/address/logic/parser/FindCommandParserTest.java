package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.category.Category.EXPENSE_STRING;
import static seedu.address.commons.core.category.Category.REVENUE_STRING;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.address.logic.parser.util.CliSyntax.PREFIX_KEYWORDS;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindCommand;
import seedu.address.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.address.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;

public class FindCommandParserTest {
    private static final String WHITE_SPACE = " ";
    private static final String KEYWORDS = "pots flower rent";
    private static final String INVALID_USER_INPUT_NO_KEYWORDS = WHITE_SPACE + PREFIX_KEYWORDS + WHITE_SPACE;
    private static final String VALID_USER_INPUT_NO_CATEGORY = WHITE_SPACE + PREFIX_KEYWORDS + KEYWORDS;
    private static final String VALID_USER_INPUT_WITH_EXPENSE_CATEGORY =
        WHITE_SPACE + PREFIX_CATEGORY + EXPENSE_STRING + VALID_USER_INPUT_NO_CATEGORY;
    private static final String VALID_USER_INPUT_WITH_REVENUE_CATEGORY =
        WHITE_SPACE + PREFIX_CATEGORY + REVENUE_STRING + VALID_USER_INPUT_NO_CATEGORY;
    private static final String EMPTY_KEYWORDS_MESSAGE = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
        FindCommand.EMPTY_KEYWORD_LIST_MESSAGE);
    private FindCommandParser parser = new FindCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommandWithBothPredicates() {
        List<String> keywordList = Arrays.asList("pots", "flower", "rent");
        ExpenseDescriptionContainsKeywordsPredicate expensePredicate = new
            ExpenseDescriptionContainsKeywordsPredicate(keywordList);
        RevenueDescriptionContainsKeywordsPredicate revenuePredicate = new
            RevenueDescriptionContainsKeywordsPredicate(keywordList);
        FindCommand expectedFindCommand = new FindCommand(expensePredicate, revenuePredicate);

        assertParseSuccess(parser, VALID_USER_INPUT_NO_CATEGORY, expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommandWithExpensePredicate() {
        List<String> keywordList = Arrays.asList("pots", "flower", "rent");
        ExpenseDescriptionContainsKeywordsPredicate expensePredicate = new
            ExpenseDescriptionContainsKeywordsPredicate(keywordList);
        FindCommand expectedFindCommand = new FindCommand(expensePredicate);

        assertParseSuccess(parser, VALID_USER_INPUT_WITH_EXPENSE_CATEGORY, expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommandWithRevenuePredicate() {
        List<String> keywordList = Arrays.asList("pots", "flower", "rent");
        RevenueDescriptionContainsKeywordsPredicate revenuePredicate = new
            RevenueDescriptionContainsKeywordsPredicate(keywordList);
        FindCommand expectedFindCommand = new FindCommand(revenuePredicate);

        assertParseSuccess(parser, VALID_USER_INPUT_WITH_REVENUE_CATEGORY, expectedFindCommand);
    }

    @Test
    public void parse_emptyKeywordList_throwsParseException() {
        assertParseFailure(parser, INVALID_USER_INPUT_NO_KEYWORDS, EMPTY_KEYWORDS_MESSAGE);
    }

}
