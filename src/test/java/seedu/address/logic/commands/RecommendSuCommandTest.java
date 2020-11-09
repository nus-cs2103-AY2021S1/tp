package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.RecommendSuCommand.MESSAGE_FAILURE;
import static seedu.address.logic.commands.RecommendSuCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.RecommendSuCommand.MESSAGE_SUCCESS_NO_RECOMMENDATION;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.GradeBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.module.GoalTarget;
import seedu.address.model.module.Module;
import seedu.address.testutil.ModuleBuilder;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RecommendSuCommand.
 */
public class RecommendSuCommandTest {

    private Model model;
    private Model modelNoGoal;
    private Model modelWithModule;
    private Model modelWithModuleA;
    private Model modelWithModuleB;
    private Model expectedModelWithModuleA;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new GradeBook(), new UserPrefs(), new GoalTarget(4));
        modelNoGoal = new ModelManager(new GradeBook(), new UserPrefs(), new GoalTarget());

        Module module1 = new ModuleBuilder().withName("CS1231").withGrade("C").build();
        Module module2 = new ModuleBuilder().withName("MA1521").withGrade("C").build();
        Module module2a = new ModuleBuilder().withName("MA1521").withGrade("A").build();

        List<Module> moduleListTest1 = Arrays.asList(module1, module2);
        List<Module> moduleListTest2 = Arrays.asList(module1, module2a);
        List<Module> moduleListTest3 = Arrays.asList(module1, module2);
        List<Module> expectedModuleListTest2 = Collections.singletonList(module1);

        GradeBook gradeBookWithModulesA = new GradeBook();
        GradeBook gradeBookWithModulesB = new GradeBook();
        GradeBook expectedGradeBookWithModulesA = new GradeBook();

        gradeBookWithModulesA.setModules(moduleListTest2);
        gradeBookWithModulesB.setModules(moduleListTest3);
        expectedGradeBookWithModulesA.setModules(expectedModuleListTest2);

        modelWithModuleA = new ModelManager(gradeBookWithModulesA, new UserPrefs(), new GoalTarget(2));
        modelWithModuleB = new ModelManager(gradeBookWithModulesB, new UserPrefs(), new GoalTarget(2));

        expectedModelWithModuleA =
            new ModelManager(expectedGradeBookWithModulesA, new UserPrefs(), new GoalTarget(2));
        expectedModel = new ModelManager(model.getGradeBook(), new UserPrefs(), new GoalTarget(4));
    }

    @Test
    public void execute_recommendSu_noGoal() {
        CommandResult expectedCommandResult =
            new CommandResult(MESSAGE_FAILURE,
                false, false, true, false, false);
        assertCommandSuccess(new RecommendSuCommand(), modelNoGoal, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_recommendSu_modelSizeZero() {
        CommandResult expectedCommandResult =
            new CommandResult(MESSAGE_SUCCESS_NO_RECOMMENDATION,
                false, false, true, false, false);
        assertCommandSuccess(new RecommendSuCommand(), model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_recommendSu_success() {
        RecommendSuCommand recommendSuCommand = new RecommendSuCommand();
        CommandResult expectedCommandResult =
            new CommandResult(MESSAGE_SUCCESS,
                false, false, true, false, false);

        //one modules in list of 2 above goal(2, Distinction (CAP 4.00 ~ 4.49)), 1 module to SU

        CommandResult commandResult;
        try {
            commandResult = recommendSuCommand.execute(modelWithModuleA);
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }

        assertEquals(expectedCommandResult, commandResult);
        assertEquals(modelWithModuleA.getFilteredModuleList(), expectedModelWithModuleA.getFilteredModuleList());

        //all modules in list of 2 can be SU-ed,

        try {
            commandResult = recommendSuCommand.execute(modelWithModuleB);
        } catch (CommandException e) {
            throw new AssertionError("Execution of command should not fail.", e);
        }
        assertEquals(expectedCommandResult, commandResult);
        assertEquals(modelWithModuleB.getFilteredModuleList(), modelWithModuleB.getFilteredModuleList());

    }
}
