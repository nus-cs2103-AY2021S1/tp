package seedu.tr4cker.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.tr4cker.testutil.TypicalTasks.getTypicalTr4cker;

import java.time.LocalDate;
import java.time.YearMonth;

import org.junit.jupiter.api.Test;

import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.ModelManager;
import seedu.tr4cker.model.UserPrefs;
import seedu.tr4cker.model.task.TaskDueInPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code PlannerCommand}.
 */
class PlannerCommandTest {

    private final Model model = new ModelManager(getTypicalTr4cker(), new UserPrefs());
    private String message = "18-Oct-2020";
    private final TaskDueInPredicate taskDueInPredicate = new TaskDueInPredicate(LocalDate.of(2020, 10, 18));

    @Test
    public void execute_switchPlannerTab_success() {
        CommandResult commandResult = new PlannerCommand().execute(model);
        CommandResult expectedCommandResult =
                new CommandResult(PlannerCommand.MESSAGE_SWITCH_TAB_SUCCESS, false, false);
        assertEquals(commandResult, expectedCommandResult);
    }

    @Test
    public void execute_gotoDay_success() {
        LocalDate localDate = LocalDate.of(2020, 10, 18);
        PlannerCommand plannerCommand = new PlannerCommand(message, localDate, null, taskDueInPredicate);
        CommandResult commandResult = plannerCommand.execute(model);
        CommandResult expectedCommandResult =
                new CommandResult("Showed tasks on: 18-Oct-2020", null, null);
        assertEquals(commandResult, expectedCommandResult);

        message += " (TODAY)";
        plannerCommand = new PlannerCommand(message, localDate, null, taskDueInPredicate);
        commandResult = plannerCommand.execute(model);
        expectedCommandResult =
                new CommandResult("Showed tasks on: 18-Oct-2020 (TODAY)", null, null);
        assertEquals(commandResult, expectedCommandResult);
    }

    @Test
    public void equals() {
        PlannerCommand plannerCommand1 = new PlannerCommand();
        PlannerCommand plannerCommand2 = new PlannerCommand();
        LocalDate localDate = LocalDate.now();
        YearMonth yearMonth = YearMonth.now();
        PlannerCommand plannerCommand3 = new PlannerCommand(message, localDate, yearMonth, taskDueInPredicate);
        PlannerCommand plannerCommand4 = new PlannerCommand(message, localDate, yearMonth, taskDueInPredicate);

        // same object -> returns true
        assertEquals(plannerCommand1, plannerCommand1);
        assertEquals(plannerCommand3, plannerCommand3);

        // same values -> returns true
        assertEquals(plannerCommand1, plannerCommand2);
        assertEquals(plannerCommand3, plannerCommand4);

        // different types -> returns false
        assertNotEquals(plannerCommand1, 1);
        assertNotEquals(plannerCommand3, "hello");

        // null -> returns false
        assertNotEquals(plannerCommand1, null);
        assertNotEquals(plannerCommand3, null);
    }

}
