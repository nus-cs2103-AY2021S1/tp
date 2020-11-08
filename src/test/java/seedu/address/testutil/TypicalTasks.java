package seedu.address.testutil;

import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DATETIME_DUE_ORDER_BEDSHEET;
import static seedu.address.testutil.command.TaskCommandTestUtil.VALID_DESCRIPTION_ORDER_BEDSHEET;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Task REMIND_PATIENT = new TaskBuilder()
            .withDescription("Remind Alice to change bedsheets.")
            .withDateTimeDue(Optional.of("20201230 2359")).build();
    public static final Task RESTOCK_SUPPLY = new TaskBuilder()
            .withDescription("Room #1 is running low on masks and needs to be restocked.")
            .withDateTimeDue(Optional.of("20200925")).build();
    public static final Task CALL_EMBASSY = new TaskBuilder()
            .withDescription("Call the U.S. embassy for Bob.")
            .withDateTimeDue(Optional.of("20201014 1800")).build();

    // Manually added
    public static final Task FIX_SHOWER = new TaskBuilder()
            .withDescription("Fix the shower in Room #4.")
            .withDateTimeDue(Optional.of("20201212 2359")).build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task ORDER_BEDSHEET = new TaskBuilder()
            .withDescription(VALID_DESCRIPTION_ORDER_BEDSHEET)
            .withDateTimeDue(Optional.of(VALID_DATETIME_DUE_ORDER_BEDSHEET)).build();

    private TypicalTasks() {} // prevents instantiation

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(REMIND_PATIENT, RESTOCK_SUPPLY, CALL_EMBASSY));
    }
}
