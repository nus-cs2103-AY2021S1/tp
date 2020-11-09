package seedu.address.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagTask;

/**
 * Mark the status of a particular task in the respective tag as done.
 */
public class TaskDoneCommand extends Command {
    public static final String COMMAND_WORD = "taskdone";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Mark the task identified by the alphabetical "
            + "index number used in the task list \n of the tag, which is identified by the numerical index used in "
            + "the displayed tag list. \n"
            + "Parameters: \n 1. INDEX (must be a positive integer from 1 to " + Integer.MAX_VALUE + ")\n"
            + "2. ALPHABETICAL INDEX (must be an alphabet in lower case from 'a' to 'z')\n"
            + "Example: " + COMMAND_WORD + " 1 a";

    public static final String MESSAGE_MARK_DONE_SUCCESS = "This task in tag %1$s has been marked done:\n" + "%2$s";

    private final Index index;
    private final Index taskIndex;

    /**
     * Creates an TaskDoneCommand to add the specified {@code Tag}
     * @param index of the tag in the filtered tag list to edit
     * @param taskIndex of the task in the targeted tag to edit
     */
    public TaskDoneCommand(Index index, Index taskIndex) {
        requireNonNull(index);
        requireNonNull(taskIndex);

        this.index = index;
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> lastShownList = model.getFilteredTagList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToEdit = lastShownList.get(index.getZeroBased());

        List<TagTask> editedTaskList = createModifiedTaskList(tagToEdit, taskIndex);

        Tag editedTag = new Tag(tagToEdit.getTagName(), editedTaskList, tagToEdit.getMeetingLink());

        model.setTag(tagToEdit, editedTag);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(String.format(MESSAGE_MARK_DONE_SUCCESS,
                                    tagToEdit.getTagName(),
                                    editedTaskList.get(taskIndex.getZeroBased())));
    }

    /**
     * Mark the targeted task as done and returns an edited {@code List<TagTask>}.
     */
    private static List<TagTask> createModifiedTaskList(Tag tagToEdit, Index taskIndex) throws CommandException {
        List<TagTask> oldTaskList = tagToEdit.getTagTasks();

        if (oldTaskList.isEmpty()) {
            throw new CommandException(Messages.MESSAGE_NO_TASK_LIST);
        }

        if (taskIndex.getZeroBased() >= oldTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        for (int i = 0; i < oldTaskList.size(); i++) {
            int target = taskIndex.getZeroBased();
            if (i == target) {
                TagTask taskToEdit = oldTaskList.get(target);
                taskToEdit.markDone();
            }
        }
        return oldTaskList;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskDoneCommand)) {
            return false;
        }

        // state check
        TaskDoneCommand e = (TaskDoneCommand) other;
        return index.equals(e.index)
                && taskIndex.equals(e.taskIndex);
    }
}
