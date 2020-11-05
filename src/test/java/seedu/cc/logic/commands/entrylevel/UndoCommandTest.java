package seedu.cc.logic.commands.entrylevel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.cc.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.cc.testutil.TypicalIndexes.INDEX_FIRST_ENTRY;

import org.junit.jupiter.api.Test;

import seedu.cc.commons.core.category.Category;
import seedu.cc.logic.commands.CommandResult;
import seedu.cc.logic.commands.CommandResultFactory;
import seedu.cc.logic.commands.exceptions.CommandException;
import seedu.cc.model.Model;
import seedu.cc.model.ModelManager;
import seedu.cc.model.UserPrefs;
import seedu.cc.model.account.ActiveAccount;
import seedu.cc.model.account.ActiveAccountManager;
import seedu.cc.model.account.entry.Expense;
import seedu.cc.testutil.EditEntryDescriptorBuilder;
import seedu.cc.testutil.ExpenseBuilder;
import seedu.cc.testutil.TypicalEntries;



public class UndoCommandTest {
    private static final String ERROR_MESSAGE = "Execution of command should not fail.";
    private static final String EXAMPLE_DESCRIPTION = "test";
    private static final String AMOUNT_DESCRIPTION = "0.39";
    private static final String EXAMPLE_EXPENSE_CATEGORY = "e";
    private static final String NEW_DESCRIPTION = "new description";
    private static final String NEW_AMOUNT = "1242.45";

    private final Model model = new ModelManager(TypicalEntries.getTypicalCommonCents(), new UserPrefs());
    private final ActiveAccount activeAccount = new ActiveAccountManager(TypicalEntries.getTypicalAccount());

    @Test
    public void execute_undoAddCommand_success() {
        ExpenseBuilder expenseBuilder = new ExpenseBuilder().withDescription(EXAMPLE_DESCRIPTION)
                .withAmount(AMOUNT_DESCRIPTION);
        Expense expenseStub = expenseBuilder.build();

        AddCommand addCommand = new AddCommand(expenseStub);
        addCommand.execute(model, activeAccount);

        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(TypicalEntries.getTypicalCommonCents(), new UserPrefs());
        CommandResult expectedCommandResult =
                CommandResultFactory.createCommandResultForEntryListChangingCommand(UndoCommand.MESSAGE_SUCCESS);

        try {
            CommandResult result = undoCommand.execute(model, activeAccount);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError(ERROR_MESSAGE, ce);
        }
    }

    @Test
    public void execute_undoDeleteCommand_success() {
        DeleteCommand deleteCommand = new DeleteCommand(INDEX_FIRST_ENTRY, new Category(EXAMPLE_EXPENSE_CATEGORY));
        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(TypicalEntries.getTypicalCommonCents(), new UserPrefs());
        CommandResult expectedCommandResult =
                CommandResultFactory.createCommandResultForEntryListChangingCommand(UndoCommand.MESSAGE_SUCCESS);
        try {
            deleteCommand.execute(model, activeAccount);
            CommandResult result = undoCommand.execute(model, activeAccount);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError(ERROR_MESSAGE, ce);
        }
    }

    @Test
    public void execute_undoEditCommand_success() {
        EditEntryDescriptorBuilder editEntryDescriptorBuilder = new EditEntryDescriptorBuilder();
        editEntryDescriptorBuilder.withDescription(NEW_DESCRIPTION).withAmount(NEW_AMOUNT)
                .withCategory(EXAMPLE_EXPENSE_CATEGORY);
        EditCommand editCommand = new EditCommand(INDEX_FIRST_ENTRY, editEntryDescriptorBuilder.build());
        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(TypicalEntries.getTypicalCommonCents(), new UserPrefs());
        CommandResult expectedCommandResult =
                CommandResultFactory.createCommandResultForEntryListChangingCommand(UndoCommand.MESSAGE_SUCCESS);
        try {
            editCommand.execute(model, activeAccount);
            CommandResult result = undoCommand.execute(model, activeAccount);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError(ERROR_MESSAGE, ce);
        }
    }

    @Test
    public void execute_undoClearCommand_success() {
        ClearCommand clearCommand = new ClearCommand();
        UndoCommand undoCommand = new UndoCommand();
        Model expectedModel = new ModelManager(TypicalEntries.getTypicalCommonCents(), new UserPrefs());
        CommandResult expectedCommandResult =
                CommandResultFactory.createCommandResultForEntryListChangingCommand(UndoCommand.MESSAGE_SUCCESS);
        try {
            clearCommand.execute(model, activeAccount);
            CommandResult result = undoCommand.execute(model, activeAccount);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, model);
        } catch (CommandException ce) {
            throw new AssertionError(ERROR_MESSAGE, ce);
        }
    }

    @Test
    public void execute_hasNoPreviousState_failure() {
        UndoCommand undoCommand = new UndoCommand();
        String expectedMessage = UndoCommand.MESSAGE_NO_PREVIOUS_COMMAND;
        assertCommandFailure(undoCommand, model, expectedMessage);
    }

}
