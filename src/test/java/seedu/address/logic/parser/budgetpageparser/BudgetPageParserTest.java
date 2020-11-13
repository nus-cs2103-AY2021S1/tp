package seedu.address.logic.parser.budgetpageparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.RedoCommand;
import seedu.address.logic.commands.UndoCommand;
import seedu.address.logic.commands.budget.CloseBudgetCommand;
import seedu.address.logic.commands.budget.DeleteExpenditureCommand;
import seedu.address.logic.commands.budget.FindExpenditureCommand;
import seedu.address.logic.commands.budget.HelpExpenditureCommand;
import seedu.address.logic.commands.budget.ListExpenditureCommand;
import seedu.address.logic.commands.budget.SortExpenditureCommand;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.logic.parser.exceptions.ParseException;

class BudgetPageParserTest {

    @Test
    void parseCommand_validCloseBudgetInput_returnsCorrectly() throws ParseException {
        String input = "close";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new CloseBudgetCommand(), command);
    }

    @Test
    void parseCommand_validDeleteExpenditureInput_returnsCorrectly() throws ParseException {
        String input = "delete 1";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new DeleteExpenditureCommand(ParserUtil.parseExpenditureIndex("1")), command);
    }

    //@Test
    //void parseCommand_validAddExpenditureInput_returnsCorrectly() throws ParseException {
    //    String input = "add n/Test p/10";
    //    Command command = new BudgetPageParser().parseCommand(input);
    //    assertEquals(new AddExpenditureCommand(new ExpenditureBuilder().withName("Test").withPrice("10")
    //            .withCreatedOn(LocalDateTime.now().toString()).build()), command);
    //}

    @Test
    void parseCommand_validFindExpenditureInput_returnsCorrectly() throws ParseException {
        String input = "find hello";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new FindExpenditureCommand(ParserUtil.parseExpenditureName("hello")), command);
    }

    @Test
    void parseCommand_validListExpenditureInput_returnsCorrectly() throws ParseException {
        String input = "list";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new ListExpenditureCommand(), command);
    }

    @Test
    void parseCommand_validSortExpenditureInput_returnsCorrectly() throws ParseException {
        String input = "sort name";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new SortExpenditureCommand(ParserUtil.parseSortType("name")), command);
    }

    @Test
    void parseCommand_validHelpExpenditureInput_returnsCorrectly() throws ParseException {
        String input = "help";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new HelpExpenditureCommand(), command);
    }

    @Test
    void parseCommand_validUndoExpenditureInput_returnsCorrectly() throws ParseException {
        String input = "undo";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new UndoCommand(), command);
    }

    @Test
    void parseCommand_validRedoExpenditureInput_returnsCorrectly() throws ParseException {
        String input = "redo";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new RedoCommand(), command);
    }

    @Test
    void parseCommand_validExitExpenditureInput_returnsCorrectly() throws ParseException {
        String input = "exit";
        Command command = new BudgetPageParser().parseCommand(input);
        assertEquals(new ExitCommand(), command);
    }

    @Test
    void parseCommand_invalidCommandInput_throwsParseException() {
        String input = "invalid command format";
        assertThrows(ParseException.class, () -> new BudgetPageParser().parseCommand(input));
    }

    @Test
    void parseCommand_nonMatchValidationRegex_throwsParseException() {
        String input = "";
        assertThrows(ParseException.class, () -> new BudgetPageParser().parseCommand(input));
    }
}
