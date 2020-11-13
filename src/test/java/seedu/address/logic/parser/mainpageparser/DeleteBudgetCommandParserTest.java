package seedu.address.logic.parser.mainpageparser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.main.DeleteBudgetCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.BudgetIndexManager;

class DeleteBudgetCommandParserTest {
    private DeleteBudgetCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new DeleteBudgetCommandParser();
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    void assertBudgetParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertTrue(((DeleteBudgetCommand) command).equals(expectedCommand));
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid user Input,", pe);
        }
    }

    @Test
    public void parse_indexPresent_success() {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        assertBudgetParseSuccess(parser, " 1",
                new DeleteBudgetCommand(budgetIndex));
    }

    @Test
    public void parse_indexZero_failure() {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        assertThrows(IllegalArgumentException.class, () ->
                assertBudgetParseSuccess(parser, "0", new DeleteBudgetCommand(budgetIndex)));
    }

    @Test
    public void parse_indexMismatch_failure() throws ParseException {
        BudgetIndex budgetIndex = new BudgetIndexManager(6);
        DeleteBudgetCommand mismatch = new DeleteBudgetCommand(budgetIndex);
        Command command = parser.parse("1");
        assertFalse(((DeleteBudgetCommand) command).equals(mismatch));
    }
}
