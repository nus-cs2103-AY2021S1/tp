package seedu.address.testutil.todolist;

import seedu.address.model.TodoList;
import seedu.address.model.task.Task;

/**
 * A utility class to help with building TodoList objects.
 * Example usage: <br>
 *     {@code TodoList todoList = new TodoList().withTask(LAB).build();}
 */
public class TodoListBuilder {

    private TodoList todoList;

    public TodoListBuilder() {
        todoList = new TodoList();
    }

    public TodoListBuilder(TodoList todoList) {
        this.todoList = todoList;
    }

    /**
     * Adds a new {@code Task} to the {@code TodoList} that we are building.
     */
    public TodoListBuilder withTask(Task task) {
        todoList.addTask(task);
        return this;
    }

    public TodoList build() {
        return todoList;
    }
}
