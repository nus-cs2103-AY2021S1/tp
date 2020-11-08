package seedu.cc.logic.commands.entrylevel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.cc.commons.core.Messages.MESSAGE_EMPTY_FILTERED_LIST;
import static seedu.cc.commons.core.Messages.MESSAGE_ENTRIES_UPDATED;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.ActiveAccountManager;
import seedu.cc.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.cc.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;
import seedu.cc.testutil.ModelStub;
import seedu.cc.testutil.TypicalEntries;

public class FindCommandTest {
    private static final String FIRST_EXPENSE_KEYWORD = "firstExpense";
    private static final String SECOND_EXPENSE_KEYWORD = "secondExpense";
    private static final String FIRST_REVENUE_KEYWORD = "firstRevenue";
    private static final String SECOND_REVENUE_KEYWORD = "secondRevenue";
    private static final String VALID_NONEXISTENT_KEYWORD = "none";
    private static final String VALID_EXISTING_EXPENSE_KEYWORD = "shovel";
    private static final String VALID_EXISTING_REVENUE_KEYWORD = "sold";
    private static final String VALID_EXISTING_KEYWORDS = "rent flowers";
    private final Model modelStub = new ModelStub();
    private final ActiveAccount activeAccount = new ActiveAccountManager(TypicalEntries.getTypicalAccount());
    @Test
    public void equals() {
        ExpenseDescriptionContainsKeywordsPredicate firstExpensePredicate =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList(FIRST_EXPENSE_KEYWORD));
        ExpenseDescriptionContainsKeywordsPredicate secondExpensePredicate =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList(SECOND_EXPENSE_KEYWORD));

        RevenueDescriptionContainsKeywordsPredicate firstRevenuePredicate =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList(FIRST_REVENUE_KEYWORD));
        RevenueDescriptionContainsKeywordsPredicate secondRevenuePredicate =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList(SECOND_REVENUE_KEYWORD));

        FindCommand findFirstExpenseCommand = new FindCommand(firstExpensePredicate);
        FindCommand findSecondExpenseCommand = new FindCommand(secondExpensePredicate);

        FindCommand findFirstRevenueCommand = new FindCommand(firstRevenuePredicate);
        FindCommand findSecondRevenueCommand = new FindCommand(secondRevenuePredicate);

        FindCommand findBothCommand = new FindCommand(firstExpensePredicate, firstRevenuePredicate);

        // same object -> returns true
        assertEquals(findFirstExpenseCommand, findFirstExpenseCommand);
        assertEquals(findFirstRevenueCommand, findFirstRevenueCommand);

        // same values -> returns true
        FindCommand findFirstExpenseCommandCopy = new FindCommand(firstExpensePredicate);
        assertEquals(findFirstExpenseCommand, findFirstExpenseCommandCopy);
        FindCommand findFirstRevenueCommandCopy = new FindCommand(firstRevenuePredicate);
        assertEquals(findFirstRevenueCommand, findFirstRevenueCommandCopy);
        FindCommand findBothCommandCopy = new FindCommand(firstExpensePredicate, firstRevenuePredicate);
        assertEquals(findBothCommandCopy, findBothCommand);

        // different types -> returns false
        assertNotEquals(1, findFirstExpenseCommand);
        assertNotEquals(1, findFirstRevenueCommand);
        assertNotEquals(1, findBothCommand);

        // null -> returns false
        assertNotEquals(null, findFirstExpenseCommand);
        assertNotEquals(null, findFirstRevenueCommand);
        assertNotEquals(null, findBothCommand);

        // different entries -> returns false
        assertNotEquals(findFirstExpenseCommand, findSecondExpenseCommand);
        assertNotEquals(findFirstRevenueCommand, findSecondRevenueCommand);
        assertNotEquals(findFirstExpenseCommand, findSecondRevenueCommand);
    }

    @Test
    public void execute_nonexistentKeyword_noExpenseFound() {
        ExpenseDescriptionContainsKeywordsPredicate predicate =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList(VALID_NONEXISTENT_KEYWORD));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_EMPTY_FILTERED_LIST);
        CommandResult actualCommandResult = new FindCommand(predicate).execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_nonexistentKeyword_noRevenueFound() {
        RevenueDescriptionContainsKeywordsPredicate predicate =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList(VALID_NONEXISTENT_KEYWORD));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_EMPTY_FILTERED_LIST);
        CommandResult actualCommandResult = new FindCommand(predicate).execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_nonexistentKeyword_noEntryFound() {
        ExpenseDescriptionContainsKeywordsPredicate predicateExpense =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList(VALID_NONEXISTENT_KEYWORD));
        RevenueDescriptionContainsKeywordsPredicate predicateRevenue =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList(VALID_NONEXISTENT_KEYWORD));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_EMPTY_FILTERED_LIST);
        CommandResult actualCommandResult = new FindCommand(predicateExpense, predicateRevenue)
            .execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_validKeywords_foundInExpenseList() {
        ExpenseDescriptionContainsKeywordsPredicate predicateExpense =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList(VALID_EXISTING_EXPENSE_KEYWORD));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_ENTRIES_UPDATED);
        CommandResult actualCommandResult = new FindCommand(predicateExpense).execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_validKeywords_foundInRevenueList() {
        RevenueDescriptionContainsKeywordsPredicate predicateRevenue =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList(VALID_EXISTING_REVENUE_KEYWORD));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_ENTRIES_UPDATED);
        CommandResult actualCommandResult = new FindCommand(predicateRevenue).execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_validKeywords_foundInEitherList() {
        ExpenseDescriptionContainsKeywordsPredicate predicateExpense =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList(VALID_EXISTING_EXPENSE_KEYWORD));
        RevenueDescriptionContainsKeywordsPredicate predicateRevenue =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList(VALID_EXISTING_REVENUE_KEYWORD));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_ENTRIES_UPDATED);
        CommandResult actualCommandResult = new FindCommand(predicateExpense, predicateRevenue)
            .execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_validMultipleKeyWords_foundInEitherList() {
        ExpenseDescriptionContainsKeywordsPredicate predicateExpense = prepareExpensePredicate(VALID_EXISTING_KEYWORDS);
        RevenueDescriptionContainsKeywordsPredicate predicateRevenue = prepareRevenuePredicate(VALID_EXISTING_KEYWORDS);
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_ENTRIES_UPDATED);
        CommandResult actualCommandResult = new FindCommand(predicateExpense, predicateRevenue)
            .execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    /**
     * Parses {@code userInput} into a {@code ExpenseDescriptionContainsKeywordsPredicate}.
     */
    private ExpenseDescriptionContainsKeywordsPredicate prepareExpensePredicate(String userInput) {
        return new ExpenseDescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }

    /**
     * Parses {@code userInput} into a {@code ExpenseDescriptionContainsKeywordsPredicate}.
     */
    private RevenueDescriptionContainsKeywordsPredicate prepareRevenuePredicate(String userInput) {
        return new RevenueDescriptionContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
