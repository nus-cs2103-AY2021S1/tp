package seedu.address.logic.parser.budgetpageparser;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.budget.DeleteExpenditureCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.state.expenditureindex.ExpenditureIndex;
import seedu.address.state.expenditureindex.ExpenditureIndexManager;

class DeleteExpenditureCommandParserTest {

    private DeleteExpenditureCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new DeleteExpenditureCommandParser();
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    void assertExpenditureParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertTrue(((DeleteExpenditureCommand) command).equals(expectedCommand));
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid user Input,", pe);
        }
    }

    @Test
    public void parse_indexPresent_success() {
        ExpenditureIndex expenditureIndex = new ExpenditureIndexManager(0);
        assertExpenditureParseSuccess(parser, "1",
                new DeleteExpenditureCommand(expenditureIndex));
    }

    @Test
    public void parse_indexZero_failure() {
        ExpenditureIndex expenditureIndex = new ExpenditureIndexManager(0);
        assertThrows(IllegalArgumentException.class, () ->
                assertExpenditureParseSuccess(parser, "0", new DeleteExpenditureCommand(expenditureIndex)));
    }

    @Test
    public void parse_indexMismatch_failure() throws ParseException {
        ExpenditureIndex expenditureIndex = new ExpenditureIndexManager(6);
        DeleteExpenditureCommand mismatch = new DeleteExpenditureCommand(expenditureIndex);
        Command command = parser.parse("1");
        assertFalse(((DeleteExpenditureCommand) command).equals(mismatch));
    }
}
