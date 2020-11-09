package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.TaskList;
import seedu.address.model.task.Todo;

/**
 * A utility class containing a list of {@code To-dos} objects to be used in tests.
 */
public class TypicalTodos {
    public static final Todo HOMEWORK = new TodoBuilder().withDescription("homework")
            .withDateTime("12-12-2020 2359").build();
    public static final Todo CHORES = new TodoBuilder().withDescription("do chores")
            .withDateTime("01-01-2020 1800").withRecurrence("1 week").build();
    public static final Todo ASSIGNMENT = new TodoBuilder().withDescription("finish assignment")
            .withDateTime("01-10-2020 1600").withTags("cs2100").build();
    public static final Todo USER_GUIDE = new TodoBuilder().withDescription("update user guide")
        .withDateTime("05-10-2021 2000").withTags("CS2103T").build();
    public static final Todo DEVELOPER_GUIDE = new TodoBuilder().withDescription("update developer guide")
        .withDateTime("15-11-2020 2300").withTags("CS2103T").build();
    public static final Todo HOMEWORK2 = new TodoBuilder().withDescription("homework2")
            .withDateTime("14-12-2020 2159").build();
    private TypicalTodos() {} // prevents instantiation

    /**
     * Returns an {@code TaskList} with all the typical todos.
     */
    public static TaskList getTypicalTodosTaskList() {
        TaskList ab = new TaskList();
        for (Todo todo: getTypicalTodos()) {
            ab.addTask(todo);
        }
        return ab;
    }

    public static List<Todo> getTypicalTodos() {
        return new ArrayList<>(Arrays.asList(HOMEWORK, CHORES, ASSIGNMENT, USER_GUIDE, DEVELOPER_GUIDE, HOMEWORK2));
    }
}
