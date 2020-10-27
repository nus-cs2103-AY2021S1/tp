package seedu.expense.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.expense.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.expense.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.expense.logic.commands.CommandTestUtil.VALID_TAG_FOOD;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.expense.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.expense.testutil.Assert.assertThrows;
import static seedu.expense.testutil.TypicalIndexes.INDEX_FIRST_EXPENSE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.expense.logic.commands.AddCategoryCommand;
import seedu.expense.logic.commands.AddCommand;
import seedu.expense.logic.commands.ClearCommand;
import seedu.expense.logic.commands.DeleteCategoryCommand;
import seedu.expense.logic.commands.DeleteCommand;
import seedu.expense.logic.commands.EditCommand;
import seedu.expense.logic.commands.EditCommand.EditExpenseDescriptor;
import seedu.expense.logic.commands.ExitCommand;
import seedu.expense.logic.commands.FindCommand;
import seedu.expense.logic.commands.HelpCommand;
import seedu.expense.logic.commands.ListCommand;
import seedu.expense.logic.commands.RemarkCommand;
import seedu.expense.logic.commands.SwitchCommand;
import seedu.expense.logic.parser.exceptions.ParseException;
import seedu.expense.model.expense.DateMatchesPredicate;
import seedu.expense.model.expense.DescriptionContainsKeywordsPredicate;
import seedu.expense.model.expense.Expense;
import seedu.expense.model.expense.Remark;
import seedu.expense.model.tag.Tag;
import seedu.expense.testutil.CategoryUtil;
import seedu.expense.testutil.EditExpenseDescriptorBuilder;
import seedu.expense.testutil.ExpenseBuilder;
import seedu.expense.testutil.ExpenseUtil;

public class ExpenseBookParserTest {

    private final ExpenseBookParser parser = new ExpenseBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Expense expense = new ExpenseBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(ExpenseUtil.getAddCommand(expense));
        assertEquals(new AddCommand(expense), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_EXPENSE.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_EXPENSE), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Expense expense = new ExpenseBuilder().build();
        EditExpenseDescriptor descriptor = new EditExpenseDescriptorBuilder(expense).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EXPENSE.getOneBased() + " " + ExpenseUtil.getEditExpenseDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_EXPENSE, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        String dateString = "09-10-2020";
        List<String> dateStrings = Arrays.asList(dateString);
        List<String> hs = new ArrayList<>();
        hs.add("CS");
        hs.add("bee");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + PREFIX_DESCRIPTION + " "
                        + keywords.stream().collect(Collectors.joining(" "))
                        + " " + PREFIX_DATE + dateString + " "
        );
        FindCommand findCommand = new FindCommand(
                new DescriptionContainsKeywordsPredicate(keywords),
                new DateMatchesPredicate(dateStrings));
        assertEquals(findCommand, command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        final Remark remark = new Remark("Some remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
                + INDEX_FIRST_EXPENSE.getOneBased() + " " + PREFIX_REMARK + remark.value);
        assertEquals(new RemarkCommand(INDEX_FIRST_EXPENSE, remark), command);
    }

    @Test
    public void parseCommand_addCat() throws Exception {
        Tag tag = new Tag(VALID_TAG_FOOD);
        AddCategoryCommand command = (AddCategoryCommand) parser.parseCommand(CategoryUtil.getAddCategoryCommand(tag));
        assertEquals(new AddCategoryCommand(tag), command);
    }

    @Test
    public void parseCommand_deleteCat() throws Exception {
        Tag tag = new Tag(VALID_TAG_FOOD);
        DeleteCategoryCommand command = (DeleteCategoryCommand) parser.parseCommand(
            DeleteCategoryCommand.COMMAND_WORD + " " + PREFIX_TAG + "Food"
        );
        assertEquals(new DeleteCategoryCommand(tag), command);
    }

    @Test
    public void parseCommand_switch() throws Exception {
        Tag foodTag = new Tag("Food");
        SwitchCommand command = (SwitchCommand) parser.parseCommand(
            SwitchCommand.COMMAND_WORD + " " + PREFIX_TAG + "Food"
        );
        SwitchCommand switchCommand = new SwitchCommand(foodTag);
        assertEquals(switchCommand, command);
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
