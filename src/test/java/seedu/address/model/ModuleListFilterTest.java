package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandTestUtil.setInvalidSemester;
import static seedu.address.logic.commands.CommandTestUtil.setValidCorrectSemester;
import static seedu.address.testutil.TypicalModules.getTypicalGradeBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.module.GoalTarget;

public class ModuleListFilterTest {

    private Model model = new ModelManager(getTypicalGradeBook(), new UserPrefs(), new GoalTarget());

    @Test
    public void filterValidSemesterSuccessfulModules() {
        setValidCorrectSemester();
        assertEquals(model.filterModuleListBySem().size(), 2);
    }

    @Test
    public void filterInvalidSemesterSuccessfulModules() {
        setInvalidSemester();
        assertEquals(model.filterModuleListBySem().size(), 7);
    }
}
