package seedu.tasklist.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.tasklist.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.tasklist.testutil.TypicalAssignments.getTypicalProductiveNus;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Collections;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.tasklist.commons.core.Messages;
import seedu.tasklist.commons.core.index.Index;
import seedu.tasklist.model.Model;
import seedu.tasklist.model.ModelManager;
import seedu.tasklist.model.UserPrefs;
import seedu.tasklist.model.assignment.Assignment;
import seedu.tasklist.model.task.Time;


/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;
    private Index index;

    private Predicate<Assignment> showLimitedAssignments() {
        return assignment -> {
            DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern(Time.DEADLINE_DATE_TIME_FORMAT)
                    .withResolverStyle(ResolverStyle.STRICT);
            String dateAndTimeToParse = assignment.getDeadline().value;
            LocalDateTime currentDateAndTime = LocalDateTime.now();
            LocalDateTime lastDateAndTime = currentDateAndTime.plusDays(index.getZeroBased());
            LocalDateTime parsedDateAndTime = LocalDateTime.parse(dateAndTimeToParse, inputFormat);

            boolean isAfterCurrentDateAndTime = parsedDateAndTime.isAfter(currentDateAndTime);
            boolean isBeforeLastDateAndTime = parsedDateAndTime.isBefore(lastDateAndTime);

            return isAfterCurrentDateAndTime && isBeforeLastDateAndTime;
        };
    }

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalProductiveNus(), new UserPrefs(), null);
        expectedModel = new ModelManager(model.getProductiveNus(), new UserPrefs(), null);
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(
                Index.fromZeroBased(0)), model, String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size()), expectedModel);
    }

    @Test
    public void execute_oneDayFromCurrentDate_showAssignments() {
        index = Index.fromZeroBased(1);
        model.updateFilteredAssignmentList(showLimitedAssignments());
        String expectedMessage = String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size());
        ListCommand listForOneDay = new ListCommand(Index.fromZeroBased(1));
        expectedModel.updateFilteredAssignmentList(showLimitedAssignments());
        assertCommandSuccess(listForOneDay, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAssignmentList());
    }

    @Test
    public void execute_fourDaysFromCurrentDate_showAssignments() {
        index = Index.fromZeroBased(4);
        model.updateFilteredAssignmentList(showLimitedAssignments());
        String expectedMessage = String.format(
                Messages.MESSAGE_ASSIGNMENTS_LISTED_OVERVIEW, model.getFilteredAssignmentList().size());
        ListCommand listForFourDays = new ListCommand(Index.fromZeroBased(4));
        expectedModel.updateFilteredAssignmentList(showLimitedAssignments());
        assertCommandSuccess(listForFourDays, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAssignmentList());
    }
}
