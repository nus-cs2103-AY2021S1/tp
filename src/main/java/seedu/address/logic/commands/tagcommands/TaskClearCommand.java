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

public class TaskClearCommand extends Command {
    public static final String COMMAND_WORD = "taskclear";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Clears all completed tasks from the tag identified by the index number used "
            + "in the displayed tag list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_CLEAR_TASK_SUCCESS = "The completed tasks under %1$s have been cleared.";

    public static final String MESSAGE_NO_TASKS = "There are no completed tasks from %1$s to be cleared.";

    private final Index index;

    /**
     * Creates an TaskDeleteCommand to remove all completed tasks from the specified {@code Tag}
     * @param index of the tag in the filtered tag list to edit
     */
    public TaskClearCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> lastShownList = model.getFilteredTagList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag identifiedTag = lastShownList.get(index.getZeroBased());
        List<TagTask> newTagTasks = identifiedTag.getAllUncompletedTasks();
        Tag editedTag = new Tag(identifiedTag.getTagName(), newTagTasks, identifiedTag.getMeetingLink());

        if (identifiedTag.getTagTasks().size() == newTagTasks.size()) {
            throw new CommandException(String.format(MESSAGE_NO_TASKS, identifiedTag.getTagName()));
        }

        model.setTag(identifiedTag, editedTag);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(String.format(MESSAGE_CLEAR_TASK_SUCCESS, identifiedTag.getTagName()));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskClearCommand)) {
            return false;
        }

        // state check
        TaskClearCommand e = (TaskClearCommand) other;
        return index.equals(e.index);
    }
}
