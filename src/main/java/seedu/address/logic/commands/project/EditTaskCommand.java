package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_PROGRESS;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.project.Deadline;
import seedu.address.model.project.Project;
import seedu.address.model.task.Task;

/**
 * Edits the details of an existing task in the main catalogue.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edittask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
            + "by the index number used in the displayed task list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_TASK_NAME + "TASK NAME] "
            + "[" + PREFIX_TASK_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_DESCRIPTION + "TASK DESCRIPTION] "
            + "[" + PREFIX_TASK_PROGRESS + "TASK PROGRESS]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK_DEADLINE + "29-02-2020 00:00:00 ";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited TASK: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the current project.";
    private static final Logger logger = Logger.getLogger("EditTaskCommandLogger");

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index              of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Task> lastShownList = project.getFilteredSortedTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);
        project.deleteTask(taskToEdit);

        if (!project.addTask(editedTask)) {
            project.addTask(taskToEdit);
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        if (editedTask.hasAnyAssignee()) {
            editedTask.getAssignees().forEach(assignee -> project.getParticipation(assignee).deleteTask(taskToEdit));
            editedTask.getAssignees().forEach(assignee -> project.getParticipation(assignee).addTask(editedTask));
        }

        if (model.getTaskToBeDisplayedOnDashboard().isPresent()
                && model.getTaskToBeDisplayedOnDashboard().get().equals(taskToEdit)) {
            model.enter(editedTask);
        }
        logger.log(Level.INFO, "Task edited");

        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editTaskDescriptor}.
     */
    private static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor)
            throws CommandException {
        assert taskToEdit != null;
        if (!editTaskDescriptor.getTaskName().isPresent()
                && !editTaskDescriptor.getDeadline().isPresent()
                && !editTaskDescriptor.getProgress().isPresent()
                && !editTaskDescriptor.getTaskDescription().isPresent()
                && !editTaskDescriptor.getAssignees().isPresent()) {
            throw new CommandException(MESSAGE_NOT_EDITED);
        }

        String updatedTaskName = editTaskDescriptor.getTaskName().orElse(taskToEdit.getTaskName());
        Deadline updatedDeadline = editTaskDescriptor.getDeadline().orElse(taskToEdit.getDeadline().orElse(null));
        Double updatedProgress = editTaskDescriptor.getProgress().orElse(taskToEdit.getProgress());
        String updatedTaskDescription = editTaskDescriptor.getTaskDescription()
                .orElse(taskToEdit.getDescription());
        Set<String> updatedAssignees = editTaskDescriptor.getAssignees().orElse(
                taskToEdit.getAssignees());

        Task updatedTask = new Task(updatedTaskName, updatedTaskDescription, updatedDeadline,
                updatedProgress);
        updatedTask.getAssignees().addAll(updatedAssignees);

        return updatedTask;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if is same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
                && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private String taskName;
        private String description;
        private LocalDate publishDate;
        private Deadline deadline;
        private Double progress;
        private Boolean isDone;
        private Set<String> assignees;

        public EditTaskDescriptor() {
        }

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTaskName(toCopy.taskName);
            setDeadline(toCopy.deadline);
            setTaskDescription(toCopy.description);
            setAssignees(toCopy.assignees);
            setIsDone(toCopy.isDone);
            setProgress(toCopy.progress);
            setPublishDate(toCopy.publishDate);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(taskName, deadline, progress,
                    description, isDone, publishDate, assignees);
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        public Optional<String> getTaskName() {
            return Optional.ofNullable(taskName);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setPublishDate(LocalDate date) {
            this.publishDate = date;
        }

        public Optional<LocalDate> getPublishDate() {
            return Optional.ofNullable(publishDate);
        }

        public void setProgress(Double progress) {
            this.progress = progress;
        }

        public Optional<Double> getProgress() {
            return Optional.ofNullable(progress);
        }

        public void setIsDone(Boolean isDone) {
            this.isDone = isDone;
        }

        public Optional<Boolean> getIsDone() {
            return Optional.ofNullable(isDone);
        }

        public void setTaskDescription(String taskDescription) {
            this.description = taskDescription;
        }

        public Optional<String> getTaskDescription() {
            return Optional.ofNullable(description);
        }

        /**
         * Sets {@code assignees} to this object's {@code assignees}.
         * A defensive copy of {@code assignees} is used internally.
         */
        public void setAssignees(Set<String> assignees) {
            this.assignees = (assignees != null) ? new HashSet<>(assignees) : null;
        }

        /**
         * Returns an unmodifiable assignees set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code assignees} is null.
         */
        public Optional<Set<String>> getAssignees() {
            return (assignees != null) ? Optional.of(Collections.unmodifiableSet(assignees)) : Optional.empty();
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

            return getTaskName().equals(e.getTaskName())
                    && getDeadline().equals(e.getDeadline())
                    && getTaskDescription().equals(e.getTaskDescription())
                    && getAssignees().equals(e.getAssignees())
                    && getIsDone().equals(e.getIsDone())
                    && getProgress().equals(e.getProgress())
                    && getPublishDate().equals(getPublishDate());
        }
    }
}
