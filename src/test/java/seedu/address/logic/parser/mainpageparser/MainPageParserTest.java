package seedu.address.logic.parser.mainpageparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.main.ClearBudgetsCommand;
import seedu.address.logic.commands.main.DeleteBudgetCommand;
import seedu.address.logic.commands.main.FindBudgetCommand;
import seedu.address.logic.commands.main.HelpBudgetCommand;
import seedu.address.logic.commands.main.ListBudgetCommand;
import seedu.address.logic.commands.main.OpenBudgetCommand;
import seedu.address.logic.commands.main.SortBudgetCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.budget.Name;
import seedu.address.model.sort.SortType;

class MainPageParserTest {

    @Test
    void parseCommand_validOpenBudgetInput_returnsCorrectly() throws ParseException {
        String input = "open 1";
        Command command = new MainPageParser().parseCommand(input);
        assertEquals(new OpenBudgetCommand(ParserUtil.parseBudgetIndex("1")), command);
    }

    @Test
    void parseCommand_validClearBudgetInput_returnsCorrectly() throws ParseException {
        String input = "clear";
        Command command = new MainPageParser().parseCommand(input);
        assertEquals(new ClearBudgetsCommand(), command);
    }

    @Test
    void parseCommand_validDeleteBudgetInput_returnsCorrectly() throws ParseException {
        String input = "delete 1";
        Command command = new MainPageParser().parseCommand(input);
        assertEquals(new DeleteBudgetCommand(ParserUtil.parseBudgetIndex("1")), command);
    }

    /*@Test
    void parseCommand_validCreateBudgetInput_returnsCorrectly() throws ParseException {
        String name = "Test";
        String input = "create n/" + name;
        Command command = new MainPageParser().parseCommand(input);
        CreateBudgetCommand expected = new CreateBudgetCommand(new BudgetBuilder().withName(name)
                .withExpenditures(new ExpenditureList()).withThreshold("0").build());
        assertTrue(expected.contentEquals(command));
    }

    @Test
    void parseCommand_validEditBudgetInput_returnsCorrectly() throws ParseException {
        String name = "Test";
        String input = "edit 1 n/" + name;
        Command command = new MainPageParser().parseCommand(input);
        assertEquals(new EditBudgetCommand(ParserUtil.parseBudgetIndex("1"),
                new EditBudgetCommand.EditBudgetDescriptor()), command);
    }*/

    @Test
    void parseCommand_validFindBudgetInput_returnsCorrectly() throws ParseException {
        String name = "Test";
        String input = "find " + name;
        Command command = new MainPageParser().parseCommand(input);
        FindBudgetCommand expected = new FindBudgetCommand(new Name(name));
        assertEquals(expected, command);
    }

    @Test
    void parseCommand_validListBudgetInput_returnsCorrectly() throws ParseException {
        Command expected = new ListBudgetCommand();
        String input = "list";
        Command actual = new MainPageParser().parseCommand(input);
        assertEquals(expected, actual);
    }

    @Test
    void parseCommand_validSortBudgetName_returnsCorrectly() throws ParseException {
        Command expected = new SortBudgetCommand(SortType.NAME);
        String input = "sort name";
        Command actual = new MainPageParser().parseCommand(input);
        assertEquals(expected, actual);
    }

    @Test
    void parseCommand_validSortBudgetTime_returnsCorrectly() throws ParseException {
        Command expected = new SortBudgetCommand(SortType.TIME);
        String input = "sort time";
        Command actual = new MainPageParser().parseCommand(input);
        assertEquals(expected, actual);
    }

    @Test
    void parseCommand_validHelpBudgetInput_returnsCorrectly() throws ParseException {
        Command expected = new HelpBudgetCommand();
        String input = "help";
        Command actual = new MainPageParser().parseCommand(input);
        assertEquals(expected, actual);
    }

    @Test
    void parseCommand_validUndoBudget_returnsCorrectly() throws ParseException {
        Command expected = new UndoCommand();
        String input = "undo";
        Command actual = new MainPageParser().parseCommand(input);
        assertEquals(expected, actual);
    }

    @Test
    void parseCommand_validRedoBudget_returnsCorrectly() throws ParseException {
        Command expected = new RedoCommand();
        String input = "redo";
        Command actual = new MainPageParser().parseCommand(input);
        assertEquals(expected, actual);
    }

    @Test
    void parseCommand_validExitCommand_returnsCorrectly() throws ParseException {
        Command expected = new ExitCommand();
        String input = "exit";
        Command actual = new MainPageParser().parseCommand(input);
        assertEquals(expected, actual);
    }

    @Test
    void parseCommand_invalidCommandInput_throwsParseException() {
        String input = "invalid command format";
        assertThrows(ParseException.class, () -> new MainPageParser().parseCommand(input));
    }

    @Test
    void parseCommand_nonMatchValidationRegex_throwsParseException() {
        String input = "";
        assertThrows(ParseException.class, () -> new MainPageParser().parseCommand(input));
    }

}
