package chopchop.logic.commands;

import static chopchop.testutil.Assert.assertThrows;
import static chopchop.testutil.TypicalUsages.Date.USAGE_DATE_A;
import static chopchop.testutil.TypicalUsages.Date.USAGE_DATE_A0;
import static chopchop.testutil.TypicalUsages.Date.USAGE_DATE_C;
import static chopchop.testutil.TypicalUsages.Date.USAGE_DATE_D;
import static chopchop.testutil.TypicalUsages.Date.USAGE_DATE_E0;
import static chopchop.testutil.TypicalUsages.INGREDIENT_A_A;
import static chopchop.testutil.TypicalUsages.INGREDIENT_A_E;
import static chopchop.testutil.TypicalUsages.INGREDIENT_B_A;
import static chopchop.testutil.TypicalUsages.INGREDIENT_B_E;
import static chopchop.testutil.TypicalUsages.getUnsortedIngredientList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import chopchop.commons.util.Pair;
import chopchop.logic.history.HistoryManager;
import chopchop.model.Model;
import chopchop.model.UsageList;
import chopchop.model.usage.Usage;
import chopchop.testutil.StubbedUsageModel;
import chopchop.testutil.TypicalUsages;

class StatsIngredientUsedCommandTest {
    private final DateTimeFormatter onFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    private Model model;
    private Model emptyModel;


    @BeforeEach
    public void setUp() {
        model = StubbedUsageModel.filled();
        emptyModel = StubbedUsageModel.empty();
    }

    @Test
    public void execute_expectedBeforeAfterEmptyModel_noIngredientsFound() {
        LocalDateTime before = USAGE_DATE_E0;
        LocalDateTime after = USAGE_DATE_A0;
        var cmd = new StatsIngredientUsedCommand(after, before);
        var cmdRes = cmd.execute(emptyModel, new HistoryManager());
        var expectedRes = CommandResult.statsMessage(new ArrayList<>(),
            String.format("No ingredients were used between %s and %s", after.format(formatter),
                before.format(formatter)));
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_beforeAndAfterNull_ingredientJustAddedInFound() {
        var now = LocalDateTime.now();
        LocalDateTime nowAfter = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), 0, 0);
        LocalDateTime nowBefore = nowAfter.plusDays(1);

        var cmd = new StatsIngredientUsedCommand(null, null);
        var cmdRes = cmd.execute(model, new HistoryManager());
        var expectedRes = new StatsIngredientUsedCommand(nowAfter, nowBefore).execute(model, new HistoryManager());
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_beforeEqualToAfterPlusOneDay_showIngredientsUsedOn() {
        LocalDateTime after = USAGE_DATE_A0;
        LocalDateTime before = after.plusDays(1);
        var cmd = new StatsIngredientUsedCommand(after, before);
        var cmdRes = cmd.execute(model, new HistoryManager());
        var expectedRes = CommandResult.statsMessage(new ArrayList<Pair<String, String>>(
                Arrays.asList(INGREDIENT_A_A.getListViewPair(), INGREDIENT_B_A.getListViewPair())),
            String.format("Showing ingredients used between %s and %s", after.format(formatter),
                before.format(formatter)));
        assertEquals(cmdRes, expectedRes);

        LocalDateTime afterOn = LocalDateTime.of(0, 1, 1, 0, 0);
        LocalDateTime beforeOn = LocalDateTime.of(0, 1, 2, 0, 0);
        cmd = new StatsIngredientUsedCommand(afterOn, beforeOn);
        cmdRes = cmd.execute(model, new HistoryManager());
        expectedRes = CommandResult.statsMessage(new ArrayList<>(),
            String.format("No ingredients were used on %s", afterOn.format(onFormatter)));
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_beforeIsNull_ingredientsAfterFound() {
        LocalDateTime after = USAGE_DATE_A0;
        var cmd = new StatsIngredientUsedCommand(after, null);
        var cmdRes = cmd.execute(model, new HistoryManager());
        var expectedRes = CommandResult.statsMessage(new ArrayList<>(
                TypicalUsages.getListViewIngredientList().stream()
                    .map(Usage::getListViewPair)
                    .collect(Collectors.toList())
            ),
            String.format("Showing ingredients used after %s", after.format(formatter)));
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_afterIsNull_ingredientsBeforeFound() {
        LocalDateTime before = USAGE_DATE_E0;
        var cmd = new StatsIngredientUsedCommand(null, before);
        var cmdRes = cmd.execute(model, new HistoryManager());
        var expectedRes = CommandResult.statsMessage(new ArrayList<>(
                TypicalUsages.getListViewIngredientList().stream()
                    .map(Usage::getListViewPair)
                    .collect(Collectors.toList())
            ),
            String.format("Showing ingredients used before %s", before.format(formatter)));
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_afterMoreThanBefore_noIngredientsFound() {
        var cmd = new StatsIngredientUsedCommand(USAGE_DATE_C, USAGE_DATE_A);
        var cmdRes = cmd.execute(model, new HistoryManager());
        var expectedRes = CommandResult.error("'after' date cannot be later than 'before' date");
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_afterEqualBefore_noIngredientsFound() {
        var cmd = new StatsIngredientUsedCommand(USAGE_DATE_C, USAGE_DATE_C);
        var cmdRes = cmd.execute(model, new HistoryManager());
        var expectedRes = CommandResult.error("'after' date cannot be later than 'before' date");
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_expectedBeforeAndAfter_ingredientsFound() {
        LocalDateTime before = USAGE_DATE_E0;
        LocalDateTime after = USAGE_DATE_D;
        var cmd = new StatsIngredientUsedCommand(after, before);
        var cmdRes = cmd.execute(model, new HistoryManager());
        var expectedRes = CommandResult.statsMessage(new ArrayList<>(
                Arrays.asList(INGREDIENT_A_E.getListViewPair(), INGREDIENT_B_E.getListViewPair())
            ),
            String.format("Showing ingredients used between %s and %s", after.format(formatter),
                before.format(formatter)));
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_unsortedUsages_ingredientsFound() {
        LocalDateTime before = USAGE_DATE_E0;
        LocalDateTime after = USAGE_DATE_D;
        this.model.setIngredientUsageList(new UsageList<>(getUnsortedIngredientList()));
        var cmd = new StatsIngredientUsedCommand(after, before);
        var cmdRes = cmd.execute(model, new HistoryManager());
        var expectedRes = CommandResult.statsMessage(new ArrayList<>(
                Arrays.asList(INGREDIENT_A_E.getListViewPair(), INGREDIENT_B_E.getListViewPair())
            ),
            String.format("Showing ingredients used between %s and %s", after.format(formatter),
                before.format(formatter)));
        assertEquals(cmdRes, expectedRes);
    }

    @Test
    public void execute_nullModel_nullPointerException() {
        var cmd = new StatsIngredientUsedCommand(null, null);
        assertThrows(AssertionError.class, () -> cmd.execute(null, new HistoryManager()));
    }

}
