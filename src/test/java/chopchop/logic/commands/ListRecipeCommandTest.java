package chopchop.logic.commands;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static chopchop.testutil.TypicalReferences.INDEXED_FIRST;
import static chopchop.testutil.TypicalRecipes.getTypicalRecipeBook;

import chopchop.model.EntryBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UserPrefs;

public class ListRecipeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRecipeBook(), new EntryBook<>(), new UserPrefs());
        expectedModel = new ModelManager(model.getRecipeBook(), new EntryBook<>(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListRecipeCommand(), model, expectedModel);
    }

    @Test
    public void execute_listIfFiltered_showsEverything() {
        showRecipeAtIndex(model, INDEXED_FIRST);
        assertCommandSuccess(new ListRecipeCommand(), model, expectedModel);
    }
}
