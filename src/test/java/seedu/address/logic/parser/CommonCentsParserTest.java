package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEntries.getTypicalAccount;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.category.Category;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.account.Account;
import seedu.address.model.account.entry.Expense;
import seedu.address.model.account.entry.Revenue;
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
    public void parseEditCommand_editExpense() throws Exception {
        Expense expense = TypicalEntries.PAY_RENT;
        EditCommand.EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(expense).build();
        EditCommand actualCommand = (EditCommand) parser.parseCommand(EntryUtil.getEditExpenseCommand());
        EditCommand expectedCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        System.out.println(EntryUtil.getEditExpenseCommand());
        System.out.println(descriptor.getDescription());
        System.out.println(descriptor.getAmount());
        System.out.println(descriptor.getCategory());
        System.out.println(descriptor.getTags());
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parseEditCommand_editRevenue() throws Exception {
        Revenue revenue = TypicalEntries.SELL_FLOWER_SEEDS;
        EditCommand.EditEntryDescriptor descriptor = new EditEntryDescriptorBuilder(revenue).build();
        EditCommand actualCommand = (EditCommand) parser.parseCommand(EntryUtil.getEditRevenueCommand());
        EditCommand expectedCommand = new EditCommand(INDEX_FIRST_ENTRY, descriptor);
        assertEquals(expectedCommand, actualCommand);
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
