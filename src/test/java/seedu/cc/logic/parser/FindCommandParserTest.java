package seedu.cc.logic.parser;

import static seedu.cc.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.cc.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_CATEGORY;
import static seedu.cc.logic.parser.util.CliSyntax.PREFIX_KEYWORDS;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.commands.entrylevel.FindCommand;
import seedu.cc.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.cc.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;

public class FindCommandParserTest {
    private static final String WHITE_SPACE = " ";
    private static final String KEYWORDS = "pots flower rent";
    private static final List<String> KEYWORD_LIST = Arrays.asList(KEYWORDS.split("\\s+"));
    private static final String EXPENSE_STRING = "expense";
    private static final String REVENUE_STRING = "revenue";
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
        assertParseFailure(parser, WHITE_SPACE, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommandWithBothPredicates() {
        ExpenseDescriptionContainsKeywordsPredicate expensePredicate = new
            ExpenseDescriptionContainsKeywordsPredicate(KEYWORD_LIST);
        RevenueDescriptionContainsKeywordsPredicate revenuePredicate = new
            RevenueDescriptionContainsKeywordsPredicate(KEYWORD_LIST);
        FindCommand expectedFindCommand = new FindCommand(expensePredicate, revenuePredicate);

        assertParseSuccess(parser, VALID_USER_INPUT_NO_CATEGORY, expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommandWithExpensePredicate() {
        ExpenseDescriptionContainsKeywordsPredicate expensePredicate = new
            ExpenseDescriptionContainsKeywordsPredicate(KEYWORD_LIST);
        FindCommand expectedFindCommand = new FindCommand(expensePredicate);

        assertParseSuccess(parser, VALID_USER_INPUT_WITH_EXPENSE_CATEGORY, expectedFindCommand);
    }

    @Test
    public void parse_validArgs_returnsFindCommandWithRevenuePredicate() {
        RevenueDescriptionContainsKeywordsPredicate revenuePredicate = new
            RevenueDescriptionContainsKeywordsPredicate(KEYWORD_LIST);
        FindCommand expectedFindCommand = new FindCommand(revenuePredicate);

        assertParseSuccess(parser, VALID_USER_INPUT_WITH_REVENUE_CATEGORY, expectedFindCommand);
    }

    @Test
    public void parse_emptyKeywordList_throwsParseException() {
        assertParseFailure(parser, INVALID_USER_INPUT_NO_KEYWORDS, EMPTY_KEYWORDS_MESSAGE);
    }

}
