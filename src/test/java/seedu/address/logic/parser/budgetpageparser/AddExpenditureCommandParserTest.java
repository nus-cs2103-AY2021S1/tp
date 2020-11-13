package seedu.address.logic.parser.budgetpageparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MORE_THAN_THREE_TAGS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.budget.AddExpenditureCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.testutil.TypicalExpenditure;

public class AddExpenditureCommandParserTest {
    private AddExpenditureCommandParser parser;

    @BeforeEach
    void setUp() {
        parser = new AddExpenditureCommandParser();
    }

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */

    void assertExpenditureParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertEquals(true, ((AddExpenditureCommand) command).contentEquals(expectedCommand));
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid user Input.", pe);
        }
    }

    @Test
    public void parse_allFieldPresent_success() {
        assertExpenditureParseSuccess(parser, " n/McMuffin p/4.50 t/breakfast t/delicious",
                new AddExpenditureCommand(TypicalExpenditure.getMcMuffinExpenditureWithTags()));
    }

    @Test
    public void parse_allFieldExceptTagsPresent_success() {
        assertExpenditureParseSuccess(parser, " n/McMuffin p/4.50",
                new AddExpenditureCommand(TypicalExpenditure.getMcMuffinExpenditureWithCurrentCreatedOn()));
    }

    @Test
    public void parse_allFieldPresentDifferentCreatedOn_failure() throws ParseException {
        Command expected = new AddExpenditureCommand(TypicalExpenditure.getMcMuffinExpenditureWithTags());
        assertEquals(false, parser
                 .parse(" n/McMuffin p/4.50 t/breakfast t/delicious").equals(expected));
    }

    @Test
    public void parse_allFieldExceptTagsPresentDifferentCreatedOn_failure()
            throws ParseException, InterruptedException {
        Command expected = new AddExpenditureCommand(TypicalExpenditure.getMcMuffinExpenditureWithTags());
        //sleep to ensure createdOn is different
        Thread.sleep(500);
        Command actual = parser.parse(" n/McMuffin p/4.50 t/breakfast t/delicious");
        assertEquals(false, parser.parse(" n/McMuffin p/4.50 t/breakfast t/delicious").equals(expected));
    }

    @Test
    public void parse_nameNotPresent_throwParseException() {
        assertParseFailure(parser, " p/100 t/breakfast",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExpenditureCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_priceNotPresent_throwParseException() {
        assertParseFailure(parser, " n/McMuffin t/breakfast",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddExpenditureCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_moreThan3Tags_throwParseException() {
        assertParseFailure(parser, " n/McMuffin p/4.50 t/breakfast t/delicious t/123 t/456",
                MESSAGE_MORE_THAN_THREE_TAGS);
    }
}
