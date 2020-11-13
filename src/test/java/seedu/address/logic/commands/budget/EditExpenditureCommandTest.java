package seedu.address.logic.commands.budget;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalBudgets.getTypicalNusave;

import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.budget.EditExpenditureCommand.EditExpenditureDescriptor;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.expenditure.Date;
import seedu.address.model.expenditure.Name;
import seedu.address.model.expenditure.Price;
import seedu.address.state.budgetindex.BudgetIndex;
import seedu.address.state.budgetindex.BudgetIndexManager;
import seedu.address.state.expenditureindex.ExpenditureIndex;
import seedu.address.state.expenditureindex.ExpenditureIndexManager;


public class EditExpenditureCommandTest {

    private Model model = new ModelManager(getTypicalNusave(), new UserPrefs());

    @Test
    public void execute_editAllField_success() throws CommandException {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        model.openBudget(budgetIndex);

        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptor();
        descriptor.setName(new Name("McMuffin"));
        descriptor.setDate(new Date("2020-10-09T00:00:00.000000"));
        descriptor.setPrice(new Price("10"));

        ExpenditureIndex expenditureIndex = new ExpenditureIndexManager(0);

        EditExpenditureCommand command = new EditExpenditureCommand(expenditureIndex, descriptor);
        String expectedMessage = EditExpenditureCommand.MESSAGE_SUCCESS;
        CommandResult expectedResult = new CommandResult(expectedMessage);

        CommandResult commandResult = command.execute(model);

        assertEquals(expectedResult, commandResult);
    }

    @Test
    public void write_editOutOfBound_commandExceptionThrown() throws CommandException {
        BudgetIndex budgetIndex = new BudgetIndexManager(0);
        model.openBudget(budgetIndex);

        EditExpenditureDescriptor descriptor = new EditExpenditureDescriptor();
        descriptor.setName(new Name("McMuffin"));
        descriptor.setDate(new Date("2020-10-09T00:00:00.000000"));
        descriptor.setPrice(new Price("10"));
        descriptor.setTags(new HashSet<>());

        EditExpenditureDescriptor descriptor2 = new EditExpenditureDescriptor(descriptor);

        ExpenditureIndex expenditureIndex = new ExpenditureIndexManager(6);
        EditExpenditureCommand command = new EditExpenditureCommand(expenditureIndex, descriptor2);

        assertThrows(CommandException.class, () -> command.execute(model));
    }
}
