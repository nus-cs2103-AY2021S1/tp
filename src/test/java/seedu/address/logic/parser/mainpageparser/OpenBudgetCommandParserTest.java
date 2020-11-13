package seedu.address.logic.parser.mainpageparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.main.OpenBudgetCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.BudgetIndexManager;

public class OpenBudgetCommandParserTest {
    public static final BudgetIndex INVALID_BUDGET_INDEX = new BudgetIndexManager(0);

    public static final BudgetIndex VALID_BUDGET_INDEX = new BudgetIndexManager(1);
    public static final String VALID_ONE_BASED_INDEX = "2";

    @Test
    void parse_validInput_returnCloseBudgetCommand() throws ParseException {
        OpenBudgetCommand expected = new OpenBudgetCommand(VALID_BUDGET_INDEX);
        OpenBudgetCommand actual = new OpenBudgetCommandParser()
                .parse(VALID_ONE_BASED_INDEX);
        assertEquals(expected, actual);
    }

    @Test
    void parse_invalidInput_throwParseException() {
        assertThrows(ParseException.class, () -> new OpenBudgetCommandParser().parse(INVALID_BUDGET_INDEX.toString()));
    }

    @Test
    void parse_nullInput_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new OpenBudgetCommandParser().parse(null));
    }
}
