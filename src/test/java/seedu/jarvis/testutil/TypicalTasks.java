package seedu.jarvis.testutil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.jarvis.model.AddressBook;
import seedu.jarvis.model.task.Deadline;
import seedu.jarvis.model.task.Event;
import seedu.jarvis.model.task.Task;
import seedu.jarvis.model.task.Todo;

public class TypicalTasks {
    public static final String TEST_TASK_DESCRIPTION_FIRST = "Read book";
    public static final String TEST_TASK_DESCRIPTION_SECOND = "Sweep floor";
    public static final String TEST_TASK_DESCRIPTION_THIRD = "Wash dish";
    public static final String TEST_TASK_DESCRIPTION_FOURTH = "Eat food";

    public static final Todo TEST_TODO_ONE = new TodoBuilder().build();
    public static final Todo TEST_TODO_TWO = new TodoBuilder().withDescription("Do more workouts").build();
    public static final Todo TEST_TODO_THREE = new TodoBuilder().withDescription("Practice the flute").buildJson("T22");
    public static final Task TEST_TODO_FOUR = new TodoBuilder().withDescription("Get milk from cold storage").build();

    public static final Event TEST_EVENT_ONE = new EventBuilder().build();
    public static final Event TEST_EVENT_TWO = new EventBuilder().withDescription("Eat more vegetable")
            .withDateTime("2020-01-01 20:15").build();
    public static final Event TEST_EVENT_THREE = new EventBuilder().withDescription("Eat more meat")
            .withDateTime("2020-02-06 00:45").buildJson("E17");
    public static final Task TEST_EVENT_FOUR = new EventBuilder().withDescription("Eat more fish")
            .withDateTime("2020-05-12 07:08").build();

    public static final Deadline TEST_DEADLINE_ONE = new DeadlineBuilder().build();
    public static final Deadline TEST_DEADLINE_TWO = new DeadlineBuilder().withDescription("Drink more water")
            .withDateTime("2021-01-01 13:55").build();
    public static final Deadline TEST_DEADLINE_THREE = new DeadlineBuilder().withDescription("Drink more juice")
            .withDateTime("2019-10-15 22:10").buildJson("D15");
    public static final Task TEST_DEADLINE_FOUR = new DeadlineBuilder().withDescription("Drink more tea")
            .withDateTime("2022-07-17 18:17").build();

    private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm";
    private static final DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern(DATE_FORMAT);

    public static final LocalDateTime TEST_TASK_DATETIME_FIRST =
            LocalDateTime.parse("2020-08-26 20:20", dateTimeFormat);
    public static final LocalDateTime TEST_TASK_DATETIME_SECOND =
            LocalDateTime.parse("2021-04-20 10:20", dateTimeFormat);
    public static final LocalDateTime TEST_TASK_DATETIME_THIRD =
            LocalDateTime.parse("2020-11-06 23:30", dateTimeFormat);
    public static final LocalDateTime TEST_TASK_DATETIME_FOURTH =
            LocalDateTime.parse("2019-02-19 08:58", dateTimeFormat);
    public static final String FORMATTED_DATETIME_ONE =
            TEST_TASK_DATETIME_FIRST.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_TWO =
            TEST_TASK_DATETIME_SECOND.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_THREE =
            TEST_TASK_DATETIME_THIRD.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));
    public static final String FORMATTED_DATETIME_FOUR =
            TEST_TASK_DATETIME_FOURTH.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT));

    /**
     * Returns an {@code AddressBook} with all the typical tasks.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Task task : getTypicalTasks()) {
            ab.addTask(task);
        }
        return ab;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(TEST_TODO_TWO, TEST_DEADLINE_TWO, TEST_EVENT_FOUR, TEST_DEADLINE_FOUR,
                TEST_EVENT_TWO, TEST_TODO_FOUR));
    }

    public static List<Task> getTypicalTodos() {
        return new ArrayList<>(Arrays.asList(TEST_TODO_TWO, TEST_TODO_FOUR));
    }

    public static List<Task> getTypicalEvents() {
        return new ArrayList<>(Arrays.asList(TEST_EVENT_FOUR, TEST_EVENT_TWO));
    }

    public static List<Task> getTypicalDeadlines() {
        return new ArrayList<>(Arrays.asList(TEST_DEADLINE_TWO, TEST_DEADLINE_FOUR));
    }

}
