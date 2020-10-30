package chopchop.logic.commands;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.logic.commands.CommandTestUtil.showRecipeAtIndex;
import static chopchop.testutil.TypicalReferences.INDEXED_FIRST;
import static chopchop.testutil.TypicalRecipes.getTypicalRecipeBook;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import chopchop.model.usage.IngredientUsage;
import chopchop.model.usage.RecipeUsage;
import chopchop.model.EntryBook;
import chopchop.model.Model;
import chopchop.model.ModelManager;
import chopchop.model.UsageList;
import chopchop.model.UserPrefs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ListRecipeCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalRecipeBook(), new EntryBook<>(), new UsageList<RecipeUsage>(),
                new UsageList<IngredientUsage>(), new UserPrefs());
        expectedModel = new ModelManager(model.getRecipeBook(), new EntryBook<>(), new UsageList<RecipeUsage>(),
                new UsageList<IngredientUsage>(), new UserPrefs());
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

    @Test
    public void test_equals() {
        var c1 = new ListRecipeCommand();
        var c2 = new ListRecipeCommand();
        assertEquals(c1, c1);
        assertEquals(c1, c2);
        assertNotEquals(c1, "owo");
    }
}
