package seedu.address.logic.parser.budgetpageparser;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.budget.EditExpenditureCommand;
import seedu.address.logic.parser.Parser;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.Name;
import seedu.address.model.expenditure.Price;
import seedu.address.state.expenditureindex.ExpenditureIndexManager;


class EditExpenditureCommandParserTest {
    private EditExpenditureCommandParser parser = new EditExpenditureCommandParser();

    /**
     * Asserts that the parsing of {@code userInput} by {@code parser} is successful and the command created
     * equals to {@code expectedCommand}.
     */
    void assertExpenditureParseSuccess(Parser parser, String userInput, Command expectedCommand) {
        try {
            Command command = parser.parse(userInput);
            assertTrue(((EditExpenditureCommand) command).equals(expectedCommand));
        } catch (ParseException pe) {
            throw new IllegalArgumentException("Invalid user Input,", pe);
        }
    }

    @Test
    void parse_allFieldPresent_success() {
        EditExpenditureCommand.EditExpenditureDescriptor expectedDescriptor =
                new EditExpenditureCommand.EditExpenditureDescriptor();
        expectedDescriptor.setName(new Name("McMuffin"));
        expectedDescriptor.setPrice(new Price("10"));
        assertExpenditureParseSuccess(parser, " 1 n/McMuffin p/10",
                new EditExpenditureCommand(new ExpenditureIndexManager(0), expectedDescriptor));
    }
}
