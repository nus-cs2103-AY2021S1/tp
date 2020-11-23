package seedu.tr4cker.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_DELETE_TAG;
import static seedu.tr4cker.logic.parser.CliSyntax.PREFIX_NEW_TAG;
import static seedu.tr4cker.model.Model.PREDICATE_SHOW_PENDING_TASKS;

import java.util.List;
import java.util.Set;

import seedu.tr4cker.commons.core.Messages;
import seedu.tr4cker.commons.core.index.Index;
import seedu.tr4cker.logic.commands.exceptions.CommandException;
import seedu.tr4cker.model.Model;
import seedu.tr4cker.model.tag.Tag;
import seedu.tr4cker.model.task.Task;

/**
 * Allows user to add or delete tags from a specified task.
 */
public class TagCommand extends Command {

    public static final String COMMAND_WORD = "tag";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds/Deletes tag for a task on TR4CKER\n"
            + "Parameters: "
            + "[" + PREFIX_NEW_TAG + "NEW_TAG]... "
            + "[" + PREFIX_DELETE_TAG + "TAG_TO_DELETE]...\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_NEW_TAG + "urgent "
            + PREFIX_DELETE_TAG + "stillHaveTime";

    public static final String MESSAGE_SUCCESS = "Tags edited for Task: %1$s";
    public static final String MESSAGE_SUCCESS_DUPLICATE_TAGS = "\nAddition of duplicate tags detected: %1$s";
    public static final String MESSAGE_SUCCESS_NON_EXISTING_TAGS = "\nDeletion of non-existing tags detected: %1$s";

    private final Index index;
    private final Set<Tag> tagsToAdd;
    private final Set<Tag> tagsToDelete;

    /**
     * @param index of the task in the filtered task list to edit the tags.
     * @param tagsToAdd Tags to be added to the task.
     * @param tagsToDelete Tags to be deleted from the task.
     */
    public TagCommand(Index index, Set<Tag> tagsToAdd, Set<Tag> tagsToDelete) {
        requireNonNull(index);

        this.index = index;
        this.tagsToAdd = tagsToAdd;
        this.tagsToDelete = tagsToDelete;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredPendingTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Set<Tag> duplicateTags = taskToEdit.addTags(tagsToAdd);
        Set<Tag> nonExistingTags = taskToEdit.deleteTags(tagsToDelete);
        Task editedTask = new Task(taskToEdit.getName(), taskToEdit.getDeadline(), taskToEdit.getCompletionStatus(),
                taskToEdit.getTaskDescription(), taskToEdit.getModuleCode(), taskToEdit.getTags());

        assert editedTask != null : "Edited task should not be null here.";

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredPendingTaskList(PREDICATE_SHOW_PENDING_TASKS);

        return new CommandResult(generateSuccessMessage(editedTask, duplicateTags, nonExistingTags));
    }

    /**
     * Generates a command execution success message when tag(s) are added
     * to and/or removed from {@code taskToEdit}.
     */
    private String generateSuccessMessage(Task taskToEdit, Set<Tag> duplicateTags, Set<Tag> nonExistingTags) {
        String message = "";
        if (duplicateTags.isEmpty() && nonExistingTags.isEmpty()) {
            return String.format(MESSAGE_SUCCESS, taskToEdit);
        } else if (!duplicateTags.isEmpty() && nonExistingTags.isEmpty()) {
            message += String.format(MESSAGE_SUCCESS, taskToEdit);
            message += String.format(MESSAGE_SUCCESS_DUPLICATE_TAGS, duplicateTags);
        } else if (duplicateTags.isEmpty()) {
            message += String.format(MESSAGE_SUCCESS, taskToEdit);
            message += String.format(MESSAGE_SUCCESS_NON_EXISTING_TAGS, nonExistingTags);
        } else {
            message += String.format(MESSAGE_SUCCESS, taskToEdit);
            message += String.format(MESSAGE_SUCCESS_DUPLICATE_TAGS, duplicateTags);
            message += String.format(MESSAGE_SUCCESS_NON_EXISTING_TAGS, nonExistingTags);
        }
        return message;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TagCommand)) {
            return false;
        }

        // state check
        TagCommand e = (TagCommand) other;
        return index.equals(e.index)
                && tagsToAdd.equals(e.tagsToAdd)
                && tagsToDelete.equals(e.tagsToDelete);
    }

}
