package seedu.address.logic.parser.budgetpageparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.budget.FindExpenditureCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.expenditure.Name;
import seedu.address.testutil.TypicalExpenditure;

class FindExpenditureCommandParserTest {
    public static final String INVALID_NAME = "Invalid;";
    public static final Name VALID_NAME = TypicalExpenditure.MC_MUFFIN.getName();

    @Test
    void parse_validInput_returnFindBudgetCommand() throws ParseException {
        FindExpenditureCommand expected = new FindExpenditureCommand(VALID_NAME);
        FindExpenditureCommand actual = new FindExpenditureCommandParser().parse(VALID_NAME.value);
        assertEquals(expected, actual);
    }

    @Test
    void parse_invalidInput_throwParseException() {
        assertThrows(ParseException.class, () -> new FindExpenditureCommandParser().parse(INVALID_NAME));
    }

    @Test
    void parse_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindExpenditureCommandParser().parse(null));
    }

}
