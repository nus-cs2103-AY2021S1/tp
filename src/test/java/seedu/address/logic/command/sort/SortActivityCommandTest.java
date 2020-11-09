package seedu.address.logic.command.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ParserUtil.ACTIVITY_INDEX;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.activity.Activity;



public class SortActivityCommandTest {

    public static final String KEYWORD_NAME = "name";
    public static final String KEYWORD_COST = "cost";
    public static final String KEYWORD_IMPORTANCE = "importance";
    public static final String KEYWORD_DATETIME = "datetime";

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());
        model.setDirectory(0);
    }

    @AfterEach
    public void tearDown() {
        model = null;
    }

    @Test
    public void execute_sortActivityName_success() {
        SortActivityCommand sortActivityCommand = new SortActivityCommand(KEYWORD_NAME);

        String expectedMessage = String.format(SortActivityCommand.MESSAGE_SORT_ACTIVITY_SUCCESS,
                KEYWORD_NAME);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Activity> nameComparator = Comparator.comparing(d -> d.getName().toString());

        expectedModel.setDirectory(0);
        expectedModel.sortActivityList(nameComparator);

        assertCommandSuccess(sortActivityCommand, model, expectedMessage, expectedModel, ACTIVITY_INDEX);
    }

    @Test
    public void execute_sortActivityCost_success() {
        SortActivityCommand sortActivityCommand = new SortActivityCommand(KEYWORD_COST);

        String expectedMessage = String.format(SortActivityCommand.MESSAGE_SORT_ACTIVITY_SUCCESS,
                KEYWORD_COST);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Activity> costComparator = Comparator.comparingInt(c -> -c.getCostAsInt());

        expectedModel.setDirectory(0);
        expectedModel.sortActivityList(costComparator);

        assertCommandSuccess(sortActivityCommand, model, expectedMessage, expectedModel, ACTIVITY_INDEX);
    }

    @Test
    public void execute_sortActivityImportance_success() {
        SortActivityCommand sortActivityCommand = new SortActivityCommand(KEYWORD_IMPORTANCE);

        String expectedMessage = String.format(SortActivityCommand.MESSAGE_SORT_ACTIVITY_SUCCESS,
                KEYWORD_IMPORTANCE);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Activity> importanceComparator = Comparator.comparing(i -> -i.getImportanceAsInt());

        expectedModel.setDirectory(0);
        expectedModel.sortActivityList(importanceComparator);

        assertCommandSuccess(sortActivityCommand, model, expectedMessage, expectedModel, ACTIVITY_INDEX);
    }

    @Test
    public void execute_sortActivityDateTime_success() {
        SortActivityCommand sortActivityCommand = new SortActivityCommand(KEYWORD_DATETIME);

        String expectedMessage = String.format(SortActivityCommand.MESSAGE_SORT_ACTIVITY_SUCCESS,
                KEYWORD_DATETIME);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Activity> dateComparator = Comparator.comparing(d -> d.getActivityDateTime().getValue());

        expectedModel.setDirectory(0);
        expectedModel.sortActivityList(dateComparator);

        assertCommandSuccess(sortActivityCommand, model, expectedMessage, expectedModel, ACTIVITY_INDEX);
    }

    @Test
    public void equals() {
        SortActivityCommand sortActivityFirstCommand = new SortActivityCommand(KEYWORD_NAME);
        SortActivityCommand sortActivitySecondCommand = new SortActivityCommand(KEYWORD_COST);

        // same object -> returns true
        assertTrue(sortActivityFirstCommand.equals(sortActivityFirstCommand));

        // same values -> returns true
        SortActivityCommand sortActivityFirstCommandCopy = new SortActivityCommand(KEYWORD_NAME);
        assertTrue(sortActivityFirstCommand.equals(sortActivityFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortActivityFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortActivityFirstCommand.equals(null));

        // different sort keyword -> returns false
        assertFalse(sortActivityFirstCommand.equals(sortActivitySecondCommand));
    }

}
