package chopchop.logic.commands;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.model.UsageList;
import chopchop.testutil.StubbedModel;

class StatsIngredientClearCommandTest {
    private Model model;
    private Model emptyModel;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = StubbedModel.filled();
        emptyModel = StubbedModel.empty();
        expectedModel = StubbedModel.filled();
        expectedModel.setIngredientUsageList(new UsageList<>());
    }

    @Test
    void execute_filledModel_success() {
        var cmd = new StatsIngredientClearCommand();
        assertCommandSuccess(cmd, model, expectedModel);
    }

    @Test
    void execute_emptyModel_success() {
        var cmd = new StatsIngredientClearCommand();
        assertCommandSuccess(cmd, emptyModel, emptyModel);
    }

    @Test
    void undo() {
        var cmd = new StatsIngredientClearCommand();
        cmd.execute(model, new HistoryManager());
        assertEquals(model, expectedModel);
        cmd.undo(model);
        assertEquals(model, model);
    }
}
