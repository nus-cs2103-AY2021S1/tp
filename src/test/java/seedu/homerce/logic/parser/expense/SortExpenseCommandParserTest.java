package seedu.homerce.logic.parser.expense;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.expense.SortExpenseCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;

public class SortExpenseCommandParserTest {

    private SortExpenseCommandParser parser = new SortExpenseCommandParser();

    @Test
    public void parse_validArgsAsc_success()throws ParseException {
        SortExpenseCommand command = parser.parse("asc");
        assertTrue(command.isAscending);
    }

    @Test
    public void parse_validArgsDesc_success()throws ParseException {
        SortExpenseCommand command = parser.parse("desc");
        assertTrue(!command.isAscending);
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("abc"));
    }

    @Test
    public void parse_emptyArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse(""));
    }
}
