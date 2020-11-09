package seedu.address.logic.command.sort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.command.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.parser.ParserUtil.ACCOMMODATION_INDEX;
import static seedu.address.testutil.typicals.TypicalTravelPlans.getTypicalTravelPlanner;

import java.util.Comparator;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.accommodation.Accommodation;


public class SortAccommodationCommandTest {
    public static final String KEYWORD_NAME = "name";
    public static final String KEYWORD_COST = "cost";
    public static final String KEYWORD_DATE = "date";

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
    public void execute_sortAccommodationName_success() {
        SortAccommodationCommand sortAccommodationCommand = new SortAccommodationCommand(KEYWORD_NAME);

        String expectedMessage = String.format(SortAccommodationCommand.MESSAGE_SORT_ACCOMMODATION_SUCCESS,
                KEYWORD_NAME);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Accommodation> nameComparator = Comparator.comparing(d -> d.getName().toString());

        expectedModel.setDirectory(0);
        expectedModel.sortAccommodationList(nameComparator);

        assertCommandSuccess(sortAccommodationCommand, model, expectedMessage, expectedModel, ACCOMMODATION_INDEX);
    }

    @Test
    public void execute_sortAccommodationCost_success() {
        SortAccommodationCommand sortAccommodationCommand = new SortAccommodationCommand(KEYWORD_COST);

        String expectedMessage = String.format(SortAccommodationCommand.MESSAGE_SORT_ACCOMMODATION_SUCCESS,
                KEYWORD_COST);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Accommodation> costComparator = Comparator.comparingInt(c -> -c.getCostAsInt());

        expectedModel.setDirectory(0);
        expectedModel.sortAccommodationList(costComparator);

        assertCommandSuccess(sortAccommodationCommand, model, expectedMessage, expectedModel, ACCOMMODATION_INDEX);
    }

    @Test
    public void execute_sortAccommodationDate_success() {
        SortAccommodationCommand sortAccommodationCommand = new SortAccommodationCommand(KEYWORD_DATE);

        String expectedMessage = String.format(SortAccommodationCommand.MESSAGE_SORT_ACCOMMODATION_SUCCESS,
                KEYWORD_DATE);

        ModelManager expectedModel = new ModelManager(getTypicalTravelPlanner(), new UserPrefs());

        Comparator<Accommodation> dateComparator = Comparator.comparing(d -> d.getStartDate().getValue());

        expectedModel.setDirectory(0);
        expectedModel.sortAccommodationList(dateComparator);

        assertCommandSuccess(sortAccommodationCommand, model, expectedMessage, expectedModel, ACCOMMODATION_INDEX);
    }

    @Test
    public void equals() {
        SortAccommodationCommand sortAccommodationFirstCommand = new SortAccommodationCommand(KEYWORD_NAME);
        SortAccommodationCommand sortAccommodationSecondCommand = new SortAccommodationCommand(KEYWORD_COST);

        // same object -> returns true
        assertTrue(sortAccommodationFirstCommand.equals(sortAccommodationFirstCommand));

        // same values -> returns true
        SortAccommodationCommand sortAccommodationFirstCommandCopy = new SortAccommodationCommand(KEYWORD_NAME);
        assertTrue(sortAccommodationFirstCommand.equals(sortAccommodationFirstCommandCopy));

        // different types -> returns false
        assertFalse(sortAccommodationFirstCommand.equals(1));

        // null -> returns false
        assertFalse(sortAccommodationFirstCommand.equals(null));

        // different sort keyword -> returns false
        assertFalse(sortAccommodationFirstCommand.equals(sortAccommodationSecondCommand));
    }
}
