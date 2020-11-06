package chopchop.logic.commands;

import static chopchop.logic.commands.CommandTestUtil.assertCommandSuccess;
import static chopchop.testutil.Assert.assertThrows;
import static chopchop.testutil.TypicalUsages.INGREDIENT_A_A;
import static chopchop.testutil.TypicalUsages.INGREDIENT_B_A;
import static chopchop.testutil.TypicalUsages.getIngredientList;
import static chopchop.testutil.TypicalUsages.getIngredientUsageList;
import static chopchop.testutil.TypicalUsages.getListViewIngredientList;
import static chopchop.testutil.TypicalUsages.getUnsortedIngredientList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chopchop.commons.util.Pair;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.model.UsageList;
import chopchop.model.usage.Usage;
import chopchop.testutil.StubbedUsageModel;

class StatsIngredientRecentCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        this.model = StubbedUsageModel.filled();
    }

    @Test
    public void execute_modelUnchanged_success() {
        var cmd = new StatsIngredientRecentCommand();
        assertCommandSuccess(cmd, model, model);
    }

    // in the case where the user changed the order of usage
    @Test
    public void execute_modelUnsortedUsageListUnchanged_success() {
        var unsortedModel = StubbedUsageModel.filled();
        unsortedModel.setIngredientUsageList(new UsageList<>(getUnsortedIngredientList()));
        model.setIngredientUsageList(new UsageList<>(getUnsortedIngredientList()));
        var cmd = new StatsIngredientRecentCommand();
        assertCommandSuccess(cmd, unsortedModel, model);
    }

    @Test
    public void execute_noRecent() {
        var expectedRes = CommandResult.statsMessage(new ArrayList<>(),
            "No ingredients were used recently");
        var cmd = new StatsIngredientRecentCommand();
        var cmdRes = cmd.execute(StubbedUsageModel.empty(), new HistoryManager());
        assertEquals(expectedRes, cmdRes);
    }

    @Test
    public void execute_oneRecent() {
        var expectedRes = CommandResult.statsMessage(new ArrayList<>(
                List.of(INGREDIENT_A_A.getListViewPair())),
            "Here are your recently used ingredients");
        model.setIngredientUsageList(new UsageList<>(List.of(INGREDIENT_A_A)));
        var cmd = new StatsIngredientRecentCommand();
        var cmdRes = cmd.execute(model, new HistoryManager());
        assertEquals(expectedRes, cmdRes);
    }

    @Test
    public void execute_sortedUsages() {
        var expectedRes = CommandResult.statsMessage(getListViewIngredientList().stream()
                .map(Usage::getListViewPair).collect(Collectors.toList()),
            "Here are your recently used ingredients");
        model.setIngredientUsageList(getIngredientUsageList());
        var cmd = new StatsIngredientRecentCommand();
        var cmdRes = cmd.execute(model, new HistoryManager());
        assertEquals(expectedRes, cmdRes);
    }

    @Test
    public void execute_unsortedUsages() {
        var expectedRes = CommandResult.statsMessage(getListViewIngredientList().stream()
                .map(Usage::getListViewPair).collect(Collectors.toList()),
            "Here are your recently used ingredients");
        model.setIngredientUsageList(new UsageList<>(getUnsortedIngredientList()));
        var cmd = new StatsIngredientRecentCommand();
        var cmdRes = cmd.execute(model, new HistoryManager());
        assertEquals(expectedRes, cmdRes);
    }


    //max of 10 items in the list
    @Test
    public void execute_numerousUsages() {
        var expectedRes = CommandResult.statsMessage(getListViewIngredientList().stream()
                .map(Usage::getListViewPair).collect(Collectors.toList()),
            "Here are your recently used ingredients");
        var newList = getIngredientList();
        newList.add(INGREDIENT_B_A);
        model.setIngredientUsageList(new UsageList<>(newList));
        var cmd = new StatsIngredientRecentCommand();
        var cmdRes = cmd.execute(model, new HistoryManager());
        assertEquals(expectedRes, cmdRes);
    }

    @Test
    public void execute_nullModel_nullPointerException() {
        var cmd = new StatsIngredientRecentCommand();
        assertThrows(AssertionError.class, () -> cmd.execute(null, new HistoryManager()));
    }
}
