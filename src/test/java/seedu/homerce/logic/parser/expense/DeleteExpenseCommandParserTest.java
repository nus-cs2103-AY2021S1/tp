package seedu.homerce.logic.parser.expense;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.homerce.testutil.Assert.assertThrows;
import static seedu.homerce.testutil.expense.TypicalExpenseIndexes.INDEX_FIRST_EXPENSE;

import org.junit.jupiter.api.Test;

import seedu.homerce.logic.commands.expense.DeleteExpenseCommand;
import seedu.homerce.logic.parser.exceptions.ParseException;

public class DeleteExpenseCommandParserTest {

    private DeleteExpenseCommandParser parser = new DeleteExpenseCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() throws ParseException {
        DeleteExpenseCommand command = parser.parse("1");
        DeleteExpenseCommand expectedCommand = new DeleteExpenseCommand(INDEX_FIRST_EXPENSE);
        assertTrue(command.targetIndex.equals(expectedCommand.targetIndex));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("a"));
    }
}
