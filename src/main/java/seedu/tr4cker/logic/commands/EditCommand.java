package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_ALL_TODOS;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.commons.util.CollectionUtil;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.daily.Todo;
import seedu.tr4cker.model.module.ModuleCode;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.CompletionStatus;
import seedu.tr4cker.model.task.Deadline;
import seedu.tr4cker.model.task.Name;
import seedu.tr4cker.model.task.Task;
import seedu.tr4cker.model.task.TaskDescription;

/**
 * Edits the details of an existing task in TR4CKER.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = "Looks like you're trying to use the " + COMMAND_WORD + " command: "
            + "Edits details of the task identified by the index number used in the specified task list\n"
            + "Compulsory Parameters: INDEX (must be a positive integer) and at least 1 field to be edited\n"
            + "Fields that can be edited: " + PREFIX_NAME + "NAME " + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_TASK_DESCRIPTION + "DESCRIPTION " + PREFIX_MODULE_CODE + "MODULECODE\n"
            + "To edit task in Pending Tasks: " + "(E.g. " + COMMAND_WORD + " 1 "
            + PREFIX_DEADLINE + "10-Dec-2020 2300)\n"
            + "To edit task in Expired Tasks: " + "(E.g. " + COMMAND_WORD + " expired 1 "
            + PREFIX_DEADLINE + "10-Dec-2020 2300)";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_UNCHANGED = "Looks like nothing has been changed.";
    public static final String MESSAGE_MODULE_ALREADY_DELETED = "Looks like there is no module code to delete.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in TR4CKER.";
    public static final String MESSAGE_INVALID_MODULE = "Given module does not exist in TR4CKER.";

    protected final Index index;
    protected final EditTaskDescriptor editTaskDescriptor;
    protected final EditTodoDescriptor editTodoDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditCommand(Index index, EditTaskDescriptor editTaskDescriptor, EditTodoDescriptor editTodoDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
        this.editTodoDescriptor = new EditTodoDescriptor(editTodoDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredPendingTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Todo todoToEdit = new Todo(taskToEdit.getName(), taskToEdit.getDeadline());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
        Todo editedTodo = createEditedTodo(todoToEdit, editTodoDescriptor);

        if (!taskToEdit.isSameTask(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        if (!taskToEdit.isEdited(editedTask)) {
            throw new CommandException(MESSAGE_UNCHANGED);
        }

        if (!model.hasValidModuleField(editedTask)) {
            throw new CommandException(MESSAGE_INVALID_MODULE);
        }

        if (taskToEdit.getModuleCode().isEmpty() && editTaskDescriptor.isModuleDeleted()) {
            throw new CommandException(MESSAGE_MODULE_ALREADY_DELETED);
        }

        if (model.hasTodo(todoToEdit)) {
            model.setTodo(todoToEdit, editedTodo);
            model.updateFilteredTodoList(PREDICATE_SHOW_ALL_TODOS);
        }

        assert taskToEdit != null : "Task to edit should not be null here.";
        assert editedTask != null : "Edited task should not be null here.";
        model.setTask(taskToEdit, editedTask);
        model.updateFilteredPendingTaskList(PREDICATE_SHOW_PENDING_TASKS);

        CommandResult commandResult = new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
        commandResult.setHomeTab();
        return commandResult;
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    protected static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        Name updatedName = editTaskDescriptor.getName().orElse(taskToEdit.getName());
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline());
        CompletionStatus initialCompletionStatus =
                taskToEdit.getCompletionStatus(); // edit command does not allow editing completion status
        TaskDescription updatedTaskDescription =
                editTaskDescriptor.getTaskDescription().orElse(taskToEdit.getTaskDescription());
        Set<ModuleCode> updatedModuleCode =
                editTaskDescriptor.getModuleCode().orElse(taskToEdit.getModuleCode());
        Set<Tag> initialTags = taskToEdit.getTags(); // edit command does not allow editing of tags

        return new Task(updatedName, updatedDeadline, initialCompletionStatus,
                updatedTaskDescription, updatedModuleCode, initialTags);
    }

    /**
     * Creates and returns a {@code Todo} with the details of {@code todoToEdit}
     * edited with {@code editTodoDescriptor}.
     */
    protected static Todo createEditedTodo(Todo todoToEdit, EditTodoDescriptor editTodoDescriptor) {
        requireNonNull(todoToEdit);

        Name updatedName = editTodoDescriptor.getName().orElse(todoToEdit.getName());
        Deadline updatedDeadline = editTodoDescriptor.getDeadline().orElse(todoToEdit.getDeadline());

        return new Todo(updatedName, updatedDeadline);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand)) {
            return false;
        }

        // state check
        EditCommand e = (EditCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private Name name;
        private Deadline deadline;
        private TaskDescription taskDescription;
        private Set<ModuleCode> moduleCode;
        private Set<Tag> tags;
        private boolean isModuleDeleted = false;

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            requireNonNull(toCopy);
            setName(toCopy.name);
            setDeadline(toCopy.deadline);
            setDescription(toCopy.taskDescription);
            setModuleCode(toCopy.moduleCode);
            setTags(toCopy.tags);
            this.isModuleDeleted = toCopy.isModuleDeleted;
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, deadline, taskDescription, moduleCode);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setDescription(TaskDescription taskDescription) {
            this.taskDescription = taskDescription;
        }

        public Optional<TaskDescription> getTaskDescription() {
            return Optional.ofNullable(taskDescription);
        }

        /**
         * Returns true if module code of the task is to be deleted.
         */
        public boolean isModuleDeleted() {
            return isModuleDeleted;
        }

        /**
         * Sets {@code isModuleDeleted} to be true.
         */
        public void setModuleDeleted() {
            this.isModuleDeleted = true;
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         */
        public void setModuleCode(Set<ModuleCode> moduleCode) {
            this.moduleCode = (moduleCode != null) ? new HashSet<>(moduleCode) : null;
        }

        /**
         * Returns an unmodifiable module code set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code moduleCode} is null.
         */
        public Optional<Set<ModuleCode>> getModuleCode() {
            return (moduleCode != null)
                    ? Optional.of(Collections.unmodifiableSet(moduleCode))
                    : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getName().equals(e.getName())
                    && getDeadline().equals(e.getDeadline())
                    && getTaskDescription().equals(e.getTaskDescription())
                    && getModuleCode().equals(e.getModuleCode());
        }
    }

    /**
     * Stores the details to edit the todo with. Each non-empty field value will replace the
     * corresponding field value of the todo.
     */
    public static class EditTodoDescriptor {
        private Name name;
        private Deadline deadline;

        public EditTodoDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTodoDescriptor(EditTodoDescriptor toCopy) {
            requireNonNull(toCopy);
            setName(toCopy.name);
            setDeadline(toCopy.deadline);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, deadline);
        }

        /**
         * Sets name of an edited Todo
         */
        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        /**
         * Sets deadline of an edited Todo
         */
        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTodoDescriptor)) {
                return false;
            }

            // state check
            EditTodoDescriptor e = (EditTodoDescriptor) other;

            return getName().equals(e.getName())
                    && getDeadline().equals(e.getDeadline());
        }
    }

}
