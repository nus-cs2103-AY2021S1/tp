package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.getTypicalAccount;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.AddAccountCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteAccountCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditAccountNameCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GetProfitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAccountCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SwitchAccountCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.account.Account;
import seedu.address.model.account.Name;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.ExpenseDescriptionContainsKeywordsPredicate;
import seedu.address.model.account.entry.Revenue;
import seedu.address.model.account.entry.RevenueDescriptionContainsKeywordsPredicate;
import seedu.address.testutil.EditEntryDescriptorBuilder;
import seedu.address.testutil.EntryUtil;
import seedu.address.testutil.ExpenseBuilder;
import seedu.address.testutil.RevenueBuilder;
import seedu.address.testutil.TypicalEntries;

public class CommonCentsParserTest {
    private final CommonCentsParser parser = new CommonCentsParser();

    @Test
    public void parseAddCommand_addExpense() throws Exception {
        Expense expense = new ExpenseBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EntryUtil.getAddCommand(expense));
        assertEquals(new AddCommand(expense), command);
    }

    @Test
    public void parseAddCommand_addRevenue() throws Exception {
        Revenue revenue = new RevenueBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(EntryUtil.getAddCommand(revenue));
        assertEquals(new AddCommand(revenue), command);
    }

    @Test
    public void parseDeleteCommand_deleteExpense() throws Exception {
        Category category = new Category(EntryUtil.EXPENSE_STRING);
        assertEquals(new DeleteCommand(INDEX_FIRST_ENTRY, category),
                parser.parseCommand(EntryUtil.getDeleteExpenseCommand()));
    }

    @Test
    public void parseDeleteCommand_deleteRevenue() throws Exception {
        Category category = new Category(EntryUtil.REVENUE_STRING);
        assertEquals(new DeleteCommand(INDEX_FIRST_ENTRY, category),
                parser.parseCommand(EntryUtil.getDeleteRevenueCommand()));
    }

    @Test
    public void parseEditCommandForExpense_validArguments_success() throws Exception {
        Expense expense = TypicalEntries.PAY_RENT;
        EditCommand.EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(expense).build();
        EditCommand actualCommand = (EditCommand) parser.parseCommand(EntryUtil.getValidEditExpenseCommand());
        EditCommand expectedCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseEditCommandForRevenue_validArguments_success() throws Exception {
        Revenue revenue = TypicalEntries.SELL_FLOWER_SEEDS;
        EditCommand.EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(revenue).build();
        EditCommand actualCommand = (EditCommand) parser.parseCommand(EntryUtil.getValidEditRevenueCommand());
        EditCommand expectedCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseFindCommand_validArguments_success() throws ParseException {
        List<String> expenseKeywords = Arrays.asList("flowers", "food");
        List<String> revenueKeywords = Arrays.asList("canvas");
        List<String> generalKeywords = Arrays.asList("rent", "flowers");

        FindCommand actualFindCommandExpense = (FindCommand) parser.parseCommand(
                EntryUtil.getValidFindExpenseCommand());
        FindCommand expectedFindCommandExpense = new FindCommand(
                new ExpenseDescriptionContainsKeywordsPredicate(expenseKeywords));
        assertEquals(expectedFindCommandExpense, actualFindCommandExpense);

        FindCommand actualFindCommandRevenue = (FindCommand) parser.parseCommand(
                EntryUtil.getValidFindRevenueCommand());
        FindCommand expectedFindCommandRevenue = new FindCommand(
                new RevenueDescriptionContainsKeywordsPredicate(revenueKeywords));
        assertEquals(expectedFindCommandRevenue, actualFindCommandRevenue);

        FindCommand actualFindCommandGeneral = (FindCommand) parser.parseCommand(
                EntryUtil.getValidFindGeneralCommand());
        FindCommand expectedFindCommandGeneral = new FindCommand(
                new ExpenseDescriptionContainsKeywordsPredicate(generalKeywords),
                new RevenueDescriptionContainsKeywordsPredicate(generalKeywords));
        assertEquals(expectedFindCommandGeneral, actualFindCommandGeneral);
    }

    @Test
    public void parseValidListCommand_success() throws ParseException {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseValidClearCommand_success() throws ParseException {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);

        ClearCommand expectedExpenseClearCommand = new ClearCommand(new Category("expense"));
        ClearCommand actualExpenseClearCommand = (ClearCommand) parser.parseCommand(
                EntryUtil.getValidClearExpenseCommand());
        assertEquals(expectedExpenseClearCommand, actualExpenseClearCommand);

        ClearCommand expectedRevenueClearCommand = new ClearCommand(new Category("revenue"));
        ClearCommand actualRevenueClearCommand = (ClearCommand) parser.parseCommand(
                EntryUtil.getValidClearRevenueCommand());
        assertEquals(expectedRevenueClearCommand, actualRevenueClearCommand);
    }

    @Test
    public void parseValidProfitCommand_success() throws Exception {
        assertTrue(parser.parseCommand(GetProfitCommand.COMMAND_WORD) instanceof GetProfitCommand);
        assertTrue(parser.parseCommand(GetProfitCommand.COMMAND_WORD + " 3") instanceof GetProfitCommand);
    }

    @Test
    public void parseValidUndoCommand_success() throws Exception {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + " 3") instanceof UndoCommand);
    }

    @Test
    public void parseValidAddAccCommand_success() throws ParseException {
        AddAccountCommand actualCommand = (AddAccountCommand) parser.parseCommand(
                EntryUtil.getValidAddAccountCommand());
        AddAccountCommand expectedCommand = new AddAccountCommand(new Account(new Name("newacc")));
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseValidEditAccCommand_success() throws ParseException {
        EditAccountNameCommand actualCommand = (EditAccountNameCommand) parser.parseCommand(
                EntryUtil.getValidEditAccountCommand());
        EditAccountNameCommand expectedCommand = new EditAccountNameCommand(new Name("anotheracc"));
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseValidDeleteAccCommand_success() throws ParseException {
        DeleteAccountCommand actualCommand = (DeleteAccountCommand) parser.parseCommand(
                EntryUtil.getValidDeleteAccountCommand());
        DeleteAccountCommand expectedCommand = new DeleteAccountCommand(INDEX_FIRST_ENTRY);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseValidSwitchAccCommand_success() throws ParseException {
        SwitchAccountCommand actualCommand = (SwitchAccountCommand) parser.parseCommand(
                EntryUtil.getValidSwitchAccountCommand());
        SwitchAccountCommand expectedCommand = new SwitchAccountCommand(INDEX_FIRST_ENTRY);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseValidListAccCommand_success() throws Exception {
        assertTrue(parser.parseCommand(ListAccountCommand.COMMAND_WORD) instanceof ListAccountCommand);
        assertTrue(parser.parseCommand(ListAccountCommand.COMMAND_WORD + " 3") instanceof ListAccountCommand);
    }


    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseAddAccountCommand() throws Exception {
        Account acc = getTypicalAccount();

    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}