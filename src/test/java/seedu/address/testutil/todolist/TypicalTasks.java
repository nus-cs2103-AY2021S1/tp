package seedu.address.testutil.todolist;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_HIGH;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PRIORITY_NORMAL;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_COMPLETED;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STATUS_LAB07;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_CS2100;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB05;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_LAB07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TodoList;
import seedu.address.model.task.Task;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    // LABS
    public static final Task LAB_01 = new TaskBuilder().withName("Finish Lab01")
            .withTags("Lab", "CS2030").withPriority("HIGH").withDate("2020-11-05")
            .withStatus("NOT_COMPLETED").build();
    public static final Task LAB_02 = new TaskBuilder().withName("Finish Lab02")
            .withTags("Lab", "CS2030").withPriority("NORMAL").withDate("2020-11-06")
            .withStatus("COMPLETED").build();

    // ASSIGNMENTS
    public static final Task ASSIGNMENT_01 = new TaskBuilder().withName("Finish Assignment01")
            .withTags("Assignment", "CS2105").withPriority("HIGH").withDate("2020-11-07")
            .withStatus("NOT_COMPLETED").build();
    public static final Task ASSIGNMENT_02 = new TaskBuilder().withName("Finish Assignment02")
            .withTags("Assignment", "CS2105").withPriority("NORMAL").withDate("2020-11-08")
            .withStatus("COMPLETED").build();

    // PROBLEM SETS
    public static final Task PROBLEM_SET_01 = new TaskBuilder().withName("Submit Problem Set 1")
            .withTags("ProblemSet", "CS2040").withPriority("HIGH").withDate("2020-11-09")
            .withStatus("NOT_COMPLETED").build();
    public static final Task PROBLEM_SET_02 = new TaskBuilder().withName("Submit Problem Set 2")
            .withTags("ProblemSet", "CS2040").withPriority("NORMAL").withDate("2020-11-10")
            .withStatus("COMPLETED").build();

    // QUIZZES
    public static final Task QUIZ_01 = new TaskBuilder().withName("Submit Quiz Week 1")
            .withTags("Quiz", "CS2103T").withPriority("HIGH").withDate("2020-11-11")
            .withStatus("NOT_COMPLETED").build();
    public static final Task QUIZ_02 = new TaskBuilder().withName("Submit Quiz Week 2")
            .withTags("Quiz", "CS2103T").withPriority("NORMAL").withDate("2020-11-12")
            .withStatus("COMPLETED").build();

    // Manually added
    public static final Task TUTORIAL_01 = new TaskBuilder().withName("attemp Tutorial 1")
            .withTags("Tutorial", "CS2106").withPriority("LOW").withDate("2020-11-13")
            .withStatus("COMPLETED").build();
    public static final Task TUTORIAL_02 = new TaskBuilder().withName("attemp Tutorial 2")
            .withTags("Tutorial", "CS1231S").withPriority("LOW").withDate("2020-11-14")
            .withStatus("COMPLETED").build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final Task LAB_05 = new TaskBuilder().withName(VALID_NAME_LAB05)
            .withTags(VALID_TAG_LAB05).withPriority(VALID_PRIORITY_LAB05)
            .withDate(VALID_DATE_LAB05).withStatus(VALID_STATUS_LAB05)
            .build();
    public static final Task LAB_07 = new TaskBuilder().withName(VALID_NAME_LAB07)
            .withTags(VALID_TAG_LAB07).withPriority(VALID_PRIORITY_LAB07)
            .withDate(VALID_DATE_LAB07).withStatus(VALID_STATUS_LAB07)
            .build();

    public static final String KEYWORD_MATCHING_LAB_01 = "Finish Lab01"; // A keyword that matches LAB_01

    private TypicalTasks() {
    } // prevents instantiation

    /**
     * Returns an {@code TodoList} with all the typical tasks.
     */
    public static TodoList getTypicalTodoList() {
        TodoList tl = new TodoList();
        for (Task task : getTypicalTasks()) {
            tl.addTask(task);
        }
        return tl;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(LAB_01, LAB_02, ASSIGNMENT_01, ASSIGNMENT_02, PROBLEM_SET_01,
                PROBLEM_SET_02, QUIZ_01, QUIZ_02));
    }
}
