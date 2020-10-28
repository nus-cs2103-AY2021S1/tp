package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.ReadOnlyTodoList;
import seedu.address.model.TodoList;
import seedu.address.model.task.Task;



/**
 * An Immutable TodoList that is serializable to JSON format.
 */
@JsonRootName(value = "todolist")
public class JsonSerializableTodoList {

    public static final String MESSAGE_DUPLICATE_TASK = "Todo list contains duplicate task(s).";

    private final List<JsonAdaptedTask> tasks = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableTodoList} with the given contacts.
     */
    @JsonCreator
    public JsonSerializableTodoList(@JsonProperty("tasks") List<JsonAdaptedTask> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Converts a given {@code ReadOnlyTodoList} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableTodoList}.
     */
    public JsonSerializableTodoList(ReadOnlyTodoList source) {
        tasks.addAll(source.getTodoList().stream().map(JsonAdaptedTask::new).collect(Collectors.toList()));
    }

    /**
     * Converts this contact list into the model's {@code ContactList} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public TodoList toModelType() throws IllegalValueException {
        TodoList todoList = new TodoList();
        for (JsonAdaptedTask jsonAdaptedTask : tasks) {
            Task task = jsonAdaptedTask.toModelType();
            if (todoList.hasTask(task)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_TASK);
            }
            todoList.addTask(task);
        }
        return todoList;
    }
}

