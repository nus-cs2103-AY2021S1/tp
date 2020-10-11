package tp.cap5buddy.logic.commands;

import java.util.Optional;

import tp.cap5buddy.contacts.ContactList;
import tp.cap5buddy.modules.ModuleList;
import tp.cap5buddy.todolist.Date;
import tp.cap5buddy.todolist.Description;
import tp.cap5buddy.todolist.Priority;
import tp.cap5buddy.todolist.Status;
import tp.cap5buddy.todolist.Task;
import tp.cap5buddy.todolist.TodoList;
import tp.cap5buddy.todolist.Type;

public class EditTaskCommand extends Command {
    /**
     * Index of task.
     */
    private final int indexToEdit;
    /**
     * Descriptor for editing the task.
     */
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Constructs an EditTaskCommand.
     *
     * @param indexToEdit name of the task that will be edited.
     * @param editTaskDescriptor descriptor for editing the task.
     */
    public EditTaskCommand(int indexToEdit, EditTaskDescriptor editTaskDescriptor) {
        this.indexToEdit = indexToEdit;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    /**
     * Executes the command by editing the task based on the descriptor.
     *
     * @param todoList the related todolist that contains the task that needs to be edited.
     * @return a CommandResult.
     */
    @Override
    public CommandResult execute(ModuleList moduleList, ContactList contacts, TodoList todoList) {
        Task taskToEdit = todoList.get(indexToEdit);
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
        todoList.set(taskToEdit, editedTask);
        String message = createSuccessMessage(taskToEdit, editedTask);
        return new CommandResult(message, isExit());
    }

    /**
     * Creates edited version of the original task.
     *
     * @param taskToEdit original task to be edited.
     * @return the edited task.
     * */
    public Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Type updatedType = editTaskDescriptor.getType().orElse(taskToEdit.getType());
        Description updatedDescription = editTaskDescriptor.getDescription().orElse(taskToEdit.getDescription());
        Priority updatedPriority = editTaskDescriptor.getPriority().orElse(taskToEdit.getPriority());
        Date updatedDate = editTaskDescriptor.getDate().orElse(taskToEdit.getDate());
        Status status = taskToEdit.getStatus();

        return new Task(updatedType, updatedDescription, updatedPriority, updatedDate, status);
    }

    /**
     * Creates success message after successfully executing this command.
     *
     * @param taskToEdit the original task.
     * @param editedTask the task after editing.
     * @return String that represent a success message followed by the content of the original
     *         task and the content after the task has been edited.
     */
    public String createSuccessMessage(Task taskToEdit, Task editedTask) {
        String message = ""
            + "Task " + (indexToEdit + 1) + " has been successfully edited!\n\n"
            + "Before edited: \n"
            + "Type        : " + taskToEdit.getType() + "\n"
            + "Description : " + taskToEdit.getDescription() + "\n"
            + "Date        : " + taskToEdit.getDate() + "\n"
            + "Priority    : " + taskToEdit.getPriority() + "\n\n"
            + "After Edited: \n"
            + "Type        : " + editedTask.getType() + "\n"
            + "Description : " + editedTask.getDescription() + "\n"
            + "Date        : " + editedTask.getDate() + "\n"
            + "Priority    : " + editedTask.getPriority() + "\n";
        return message;
    }

    /**
     * Indicates if the application session has ended.
     *
     * @return True if the session has been terminated, false otherwise.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        /**
         * the edited type.
         */
        private Type type;
        /**
         * the edited description.
         */
        private Description description;
        /**
         * the edited date/deadline.
         */
        private Date date;
        /**
         * the edited priority.
         */
        private Priority priority;

        /**
         * Constructs an empty EditTaskDescriptor.
         */
        public EditTaskDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setType(toCopy.type);
            setDescription(toCopy.description);
            setDate(toCopy.date);
            setPriority(toCopy.priority);
        }

        public void setType(Type editedType) {
            this.type = editedType;
        }

        public Optional<Type> getType() {
            return Optional.ofNullable(this.type);
        }

        public void setDescription(Description editedDescription) {
            this.description = editedDescription;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(this.description);
        }

        public void setDate(Date editedDate) {
            this.date = editedDate;
        }

        public Optional<Date> getDate() {
            return Optional.ofNullable(this.date);
        }

        public void setPriority(Priority editedPriority) {
            this.priority = editedPriority;
        }

        public Optional<Priority> getPriority() {
            return Optional.ofNullable(this.priority);
        }
    }
}
