package seedu.address.logic.parser.mainpageparser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.main.EditBudgetCommand;
import seedu.address.model.budget.Name;
import seedu.address.model.budget.Threshold;
import seedu.address.state.budgetindex.BudgetIndexManager;

class EditBudgetCommandParserTest {
    private EditBudgetCommandParser parser = new EditBudgetCommandParser();

    @Test
    public void parse_allFieldPresent_success() {
        EditBudgetCommand.EditBudgetDescriptor expectedDescriptor = new EditBudgetCommand.EditBudgetDescriptor();
        expectedDescriptor.setName(new Name("KFC"));
        expectedDescriptor.setThreshold(new Threshold("123.0").toOptional());
        assertParseSuccess(parser, " 1 n/KFC p/123",
                new EditBudgetCommand(new BudgetIndexManager(0), expectedDescriptor));
    }
}
