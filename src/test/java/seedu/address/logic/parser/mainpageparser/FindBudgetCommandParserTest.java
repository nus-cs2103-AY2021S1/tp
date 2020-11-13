package seedu.address.logic.parser.mainpageparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.main.FindBudgetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.budget.Name;
import seedu.address.testutil.TypicalBudget;

class FindBudgetCommandParserTest {
    public static final String INVALID_NAME = "Invalid;";

    public static final Name VALID_NAME = TypicalBudget.KFC.getName();

    @Test
    void parse_validInput_returnFindBudgetCommand() throws ParseException {
        FindBudgetCommand expected = new FindBudgetCommand(VALID_NAME);
        FindBudgetCommand actual = new FindBudgetCommandParser().parse(VALID_NAME.value);
        assertEquals(expected, actual);
    }

    @Test
    void parse_invalidInput_throwParseException() {
        assertThrows(ParseException.class, () -> new FindBudgetCommandParser().parse(INVALID_NAME));
    }

    @Test
    void parse_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindBudgetCommandParser().parse(null));
    }

}
