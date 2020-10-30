package chopchop.logic.commands;

import chopchop.commons.util.Pair;
import chopchop.model.EntryBook;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UsageList;
import chopchop.model.UserPrefs;
import chopchop.model.attributes.units.Mass;
import chopchop.model.usage.IngredientUsage;
import chopchop.model.usage.RecipeUsage;
import chopchop.logic.commands.exceptions.CommandException;
import chopchop.logic.history.HistoryManager;
import chopchop.logic.parser.CommandParser;
import chopchop.logic.parser.ItemReference;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static chopchop.logic.commands.CommandTestUtil.assertCommandFailure;
import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.showIngredientAtIndex;
import static chopchop.testutil.TypicalReferences.INDEXED_FIRST;
import static chopchop.testutil.TypicalReferences.INDEXED_SECOND;
import static chopchop.testutil.TypicalIngredients.getTypicalIngredientBook;

public class DeleteIngredientCommandTest {

    private Model model = new ModelManager(new EntryBook<>(), getTypicalIngredientBook(), new UsageList<RecipeUsage>(),
        new UsageList<IngredientUsage>(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        var indToDelete = model.getFilteredIngredientList().get(INDEXED_FIRST.getZeroIndex());
        var deleteCommand = new DeleteIngredientCommand(INDEXED_FIRST);


        var expectedModel = new ModelManager(new EntryBook<>(), model.getIngredientBook(),
            new UsageList<RecipeUsage>(), new UsageList<IngredientUsage>(), new UserPrefs());
        expectedModel.deleteIngredient(indToDelete);

        assertCommandSuccess(deleteCommand, model, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        var outOfBoundIndex = ItemReference.ofOneIndex(model.getFilteredIngredientList().size() + 1);
        var deleteCommand = new DeleteIngredientCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showIngredientAtIndex(model, INDEXED_FIRST);

        var indToDelete = model.getFilteredIngredientList().get(INDEXED_FIRST.getZeroIndex());
        var deleteCommand = new DeleteIngredientCommand(INDEXED_FIRST);

        var expectedModel = new ModelManager(new EntryBook<>(), model.getIngredientBook(), new UsageList<RecipeUsage>(),
            new UsageList<IngredientUsage>(), new UserPrefs());
        expectedModel.deleteIngredient(indToDelete);
        showNoIngredient(expectedModel);

        assertCommandSuccess(deleteCommand, model, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showIngredientAtIndex(model, INDEXED_FIRST);

        var outOfBoundIndex = INDEXED_SECOND;
        // ensures that outOfBoundIndex is still in bounds of ingredient book list
        assertTrue(outOfBoundIndex.getZeroIndex() < model.getIngredientBook().getEntryList().size());

        var deleteCommand = new DeleteIngredientCommand(outOfBoundIndex);

        assertCommandFailure(deleteCommand, model);
    }

    @Test
    public void equals() {
        var deleteFirstCommand = new DeleteIngredientCommand(INDEXED_FIRST);
        var deleteSecondCommand = new DeleteIngredientCommand(INDEXED_SECOND);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        var deleteFirstCommandCopy = new DeleteIngredientCommand(INDEXED_FIRST);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different values -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoIngredient(Model model) {
        model.updateFilteredIngredientList(p -> false);

        assertTrue(model.getFilteredIngredientList().isEmpty());
    }



    private Pair<Command, CommandResult> runCommand(Model m, String str) {
        var parser = new CommandParser();
        var c = parser.parse(str);

        if (c.isError()) {
            assertEquals(1, c.getError());
        }

        assertTrue(c.hasValue());
        var cmd = c.getValue();

        try {
            return Pair.of(cmd, cmd.execute(m, new HistoryManager()));
        } catch (CommandException e) {
            return Pair.of(cmd, CommandResult.error(e.getMessage()));
        }
    }

    @Test
    public void test_deleteQuantity() {

        runCommand(this.model, "add ingredient sprinkles /qty 400g");
        var c1 = runCommand(this.model, "delete ingredient sprinkles /qty 200g").fst();

        assertEquals(Mass.grams(200), this.model.findIngredientWithName("sprinkles").get().getQuantity());

        var c2 = runCommand(this.model, "delete ingredient sprinkles /qty 200g").fst();
        assertTrue(this.model.findIngredientWithName("sprinkles").isEmpty());

        assertEquals(c1, c2);

        runCommand(this.model, "add ingredient sprinkles /qty 400g");

        var p = runCommand(this.model, "delete ingredient sprinkles /qty 999ml");
        assertFalse(p.snd().didSucceed());

        assertNotEquals(c1, p.fst());

        var p2 = runCommand(this.model, "delete ingredient sprinkles /qty 30g");
        assertTrue(p2.snd().didSucceed());
        try {
            assertTrue(((Undoable) p2.fst()).undo(this.model).didSucceed());
        } catch (CommandException e) {
            assertTrue(false);
        }
    }
}












