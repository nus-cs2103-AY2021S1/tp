package seedu.address.logic.parser.mainpageparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.main.CreateBudgetCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.TypicalBudget;

class CreateBudgetCommandParserTest {
    private CreateBudgetCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new CreateBudgetCommandParser();
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    void assertBudgetParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(true, ((CreateBudgetCommand) command).contentEquals(expectedCommand));
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid user Input.", pe);
        }
    }

    @Test
    public void parse_allFieldPresent_success() throws ParseException {
        CreateBudgetCommand expected = new CreateBudgetCommand(TypicalBudget.getEmptyMcDonaldsBudget());
        assertBudgetParseSuccess(parser, " n/McDonalds p/100.0", expected);
    }

    @Test
    public void parse_allFieldPresentButDifferentCreatedOn_throwsParseException()
            throws ParseException, InterruptedException {
        CreateBudgetCommand actual = parser.parse(" n/McDonalds p/100.0");
        //sleep to ensure the 2 created commands have different time
        Thread.sleep(500);
        CreateBudgetCommand expected = new CreateBudgetCommand(TypicalBudget.getEmptyMcDonaldsBudget());
        assertEquals(false, expected.equals(actual));
    }

    @Test
    public void parse_nameNotPresent_throwParseException() {
        assertParseFailure(parser, " p/100",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, CreateBudgetCommand.MESSAGE_USAGE));
    }

}
