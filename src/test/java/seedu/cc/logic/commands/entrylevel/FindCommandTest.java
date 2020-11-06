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
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.ActiveAccountManager;
import seedu.cc.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.cc.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;
import seedu.cc.testutil.ModelStub;
import seedu.cc.testutil.TypicalEntries;

public class FindCommandTest {

    private final Model modelStub = new ModelStub();
    private final ActiveAccount activeAccount = new ActiveAccountManager(TypicalEntries.getTypicalAccount());

    @Test
    public void equals() {
        ExpenseDescriptionContainsKeywordsPredicate firstExpensePredicate =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList("firstExpense"));
        ExpenseDescriptionContainsKeywordsPredicate secondExpensePredicate =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList("secondExpense"));

        RevenueDescriptionContainsKeywordsPredicate firstRevenuePredicate =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList("firstRevenue"));
        RevenueDescriptionContainsKeywordsPredicate secondRevenuePredicate =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList("secondRevenue"));

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
    public void execute_nonexistentKeyword_noExpenseFound() throws CommandException {
        ExpenseDescriptionContainsKeywordsPredicate predicate =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList("NONE"));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_EMPTY_FILTERED_LIST);
        CommandResult actualCommandResult = new FindCommand(predicate).execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_nonexistentKeyword_noRevenueFound() throws CommandException {
        RevenueDescriptionContainsKeywordsPredicate predicate =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList("NONE"));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_EMPTY_FILTERED_LIST);
        CommandResult actualCommandResult = new FindCommand(predicate).execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_nonexistentKeyword_noEntryFound() throws CommandException {
        ExpenseDescriptionContainsKeywordsPredicate predicateExpense =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList("NONE"));
        RevenueDescriptionContainsKeywordsPredicate predicateRevenue =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList("NONE"));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_EMPTY_FILTERED_LIST);
        CommandResult actualCommandResult = new FindCommand(predicateExpense, predicateRevenue)
            .execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_validKeywords_foundInExpenseList() throws CommandException {
        ExpenseDescriptionContainsKeywordsPredicate predicateExpense =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList("shovel"));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_ENTRIES_UPDATED);
        CommandResult actualCommandResult = new FindCommand(predicateExpense).execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_validKeywords_foundInRevenueList() throws CommandException {
        RevenueDescriptionContainsKeywordsPredicate predicateRevenue =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList("sold"));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_ENTRIES_UPDATED);
        CommandResult actualCommandResult = new FindCommand(predicateRevenue).execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_validKeywords_foundInEitherList() throws CommandException {
        ExpenseDescriptionContainsKeywordsPredicate predicateExpense =
            new ExpenseDescriptionContainsKeywordsPredicate(Collections.singletonList("shovel"));
        RevenueDescriptionContainsKeywordsPredicate predicateRevenue =
            new RevenueDescriptionContainsKeywordsPredicate(Collections.singletonList("flower"));
        CommandResult expectedCommandResult = CommandResultFactory
            .createDefaultCommandResult(MESSAGE_ENTRIES_UPDATED);
        CommandResult actualCommandResult = new FindCommand(predicateExpense, predicateRevenue)
            .execute(modelStub, activeAccount);
        assertEquals(expectedCommandResult, actualCommandResult);
    }

    @Test
    public void execute_validMultipleKeyWords_foundInEitherList() throws CommandException {
        ExpenseDescriptionContainsKeywordsPredicate predicateExpense = prepareExpensePredicate("rent flower");
        RevenueDescriptionContainsKeywordsPredicate predicateRevenue = prepareRevenuePredicate("rent flower");
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
