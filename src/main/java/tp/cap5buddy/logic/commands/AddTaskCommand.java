package tp.cap5buddy.logic.commands;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.Date;
import tp.cap5buddy.todolist.Description;
import tp.cap5buddy.todolist.Priority;
import tp.cap5buddy.todolist.Status;
import tp.cap5buddy.todolist.Task;
import tp.cap5buddy.todolist.TodoList;
import tp.cap5buddy.todolist.Type;

public class AddTaskCommand extends Command {
    private final Type type;
    private final Description description;
    private final Date date;
    private final Priority priority;

    /**
     * Command to add a task to the todo list.
     *
     * @param type Type of task.
     * @param description Description of the task.
     * @param date Date or deadline of the task.
     * @param priority Priority of the task.
     */
    public AddTaskCommand(Type type, Description description, Date date, Priority priority) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.priority = priority;
    }

    /**
     * Adds a task to the todolist.
     *
     * @param moduleList The related module list.
     * @param contactList The related contact list.
     * @param todolist The related todo list.
     * @return A CommandResult base on the command.
     */
    @Override
    public CommandResult execute(ModuleList moduleList, ContactList contactList, TodoList todolist) {
        Task toAdd = new Task(this.type, this.description, this.priority, this.date, Status.NOT_COMPLETED);
        todolist.add(toAdd);
        String message = createSuccessMessage(toAdd);
        return new CommandResult(message, isExit());
    }

    /**
     * Creates success message after execution of command.
     *
     * @param task Task that is added to the todolist.
     * @return A String representing the outcome of the execution.
     */
    public String createSuccessMessage(Task task) {
        StringBuilder builder = new StringBuilder();
        builder.append("New task added: ")
                .append("\n")
                .append(task);
        return builder.toString();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
