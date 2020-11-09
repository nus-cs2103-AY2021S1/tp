package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.INVALID_SEMESTER;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_CAP_C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOAL_TARGET_B;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOAL_TARGET_C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GOAL_TARGET_D;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_C;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_A;
import static seedu.address.logic.commands.CommandTestUtil.VALID_MOD_NAME_B;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.ProgressCommand.MESSAGE_ACHIEVABLE_CAP;
import static seedu.address.logic.commands.ProgressCommand.MESSAGE_REQUIRED_CAP;
import static seedu.address.logic.commands.ProgressCommand.MESSAGE_TARGET_CAP;
import static seedu.address.logic.commands.ProgressCommand.MESSAGE_UNACHIEVABLE_CAP;
import static seedu.address.testutil.TypicalModules.getTypicalGradeBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

public class ProgressCommandTest {

    private Model model = new ModelManager(getTypicalGradeBook(), new UserPrefs(), new GoalTarget());

    private final boolean isDdp = true;

    @Test
    public void execute_progressWithoutDdp_success() {
        model.setGoalTarget(new GoalTarget(VALID_GOAL_TARGET_B));
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_TARGET_CAP, VALID_CAP_A)
                        + String.format(MESSAGE_REQUIRED_CAP, VALID_CAP_A));

        ModelManager expectedModel = new ModelManager(
                getTypicalGradeBook(), new UserPrefs(), new GoalTarget(VALID_GOAL_TARGET_B));

        ProgressCommand progressCommand = new ProgressCommand(!isDdp);
        assertCommandSuccess(progressCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_progressWithDdp_success() {
        model.setGoalTarget(new GoalTarget(VALID_GOAL_TARGET_B));
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_TARGET_CAP, VALID_CAP_A)
                        + String.format(MESSAGE_REQUIRED_CAP, VALID_CAP_A));

        ModelManager expectedModel = new ModelManager(
                getTypicalGradeBook(), new UserPrefs(), new GoalTarget(VALID_GOAL_TARGET_B));

        ProgressCommand progressCommand = new ProgressCommand(isDdp);
        assertCommandSuccess(progressCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_progressWithUnachievableGoal_success() {
        Model model = new ModelManager();
        model.setGoalTarget(new GoalTarget(VALID_GOAL_TARGET_D));

        Module failedModule1 = new ModuleBuilder().withName(VALID_MOD_NAME_A)
                .withGrade(VALID_GRADE_C).withModularCredit(16)
                .withSemester(INVALID_SEMESTER.name()).build(); //Semester is not relevant here
        Module failedModule2 = new ModuleBuilder().withName(VALID_MOD_NAME_B)
                .withGrade(VALID_GRADE_C).withModularCredit(16)
                .withSemester(INVALID_SEMESTER.name()).build(); //Semester is not relevant here
        model.addModule(failedModule1);
        model.addModule(failedModule2);

        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_TARGET_CAP, VALID_CAP_C)
                        + MESSAGE_UNACHIEVABLE_CAP);

        ModelManager expectedModel = new ModelManager();
        expectedModel.setGoalTarget(new GoalTarget(VALID_GOAL_TARGET_D));
        expectedModel.addModule(failedModule1);
        expectedModel.addModule(failedModule2);

        ProgressCommand progressCommand = new ProgressCommand(isDdp);
        assertCommandSuccess(progressCommand, model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_progressWithAchievableGoal_success() {
        model.setGoalTarget(new GoalTarget(VALID_GOAL_TARGET_C));
        CommandResult expectedCommandResult = new CommandResult(
                String.format(MESSAGE_TARGET_CAP, VALID_CAP_B)
                        + MESSAGE_ACHIEVABLE_CAP);

        ModelManager expectedModel = new ModelManager(
                getTypicalGradeBook(), new UserPrefs(), new GoalTarget(VALID_GOAL_TARGET_C));

        ProgressCommand progressCommand = new ProgressCommand(isDdp);
        assertCommandSuccess(progressCommand, model, expectedCommandResult, expectedModel);
    }
}
