package seedu.address.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.MeetingLink;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagTask;

/**
 * Adds a task to a tag.
 */
public class TaskAddCommand extends Command {

    public static final String COMMAND_WORD = "taskadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to a tag. "
            + "Parameters: INDEX (must be a positive integer from 1 to " + Integer.MAX_VALUE + ") "
            + PREFIX_TASK + "TASK DESCRIPTION "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_TASK + "Peer review ";

    public static final String MESSAGE_SUCCESS = "New task added to the tag %1$s : \n%2$s";
    public static final String MESSAGE_TASK_NAME_NOT_PROVIDED = "Name of task must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in this tag.";
    public static final String MESSAGE_MAXIMUM_TASKS_EXCEEDED = "The maximum number of tasks that are allowed to be "
            + "added has been exceeded.";

    private final Index index;
    private final EditTagDescriptor editTagDescriptor;

    /**
     * Creates an TaskAddCommand to add the specified {@code Tag}
     */
    public TaskAddCommand(Index index, EditTagDescriptor editTagDescriptor) {
        requireNonNull(index);
        requireNonNull(editTagDescriptor);

        this.index = index;
        this.editTagDescriptor = new EditTagDescriptor(editTagDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> lastShownList = model.getFilteredTagList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToEdit = lastShownList.get(index.getZeroBased());
        Tag editedTag = createEditedTag(tagToEdit, editTagDescriptor);

        if (tagToEdit.getTagTasks().size() == 26) {
            throw new CommandException(MESSAGE_MAXIMUM_TASKS_EXCEEDED);
        }

        if (!editTagDescriptor.isNewTagTask(tagToEdit.getTagTasks())) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTag(tagToEdit, editedTag);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, tagToEdit.getTagName(), editTagDescriptor.newTagTask));
    }

    /**
     * Creates and returns a {@code Tag} with the name of {@code tagToEdit}
     * edited with {@code editTagDescriptor}.
     */
    private static Tag createEditedTag(Tag tagToEdit, EditTagDescriptor editTagDescriptor) {
        assert tagToEdit != null;

        TagName tagName = tagToEdit.getTagName();
        List<TagTask> oldTagTasks = tagToEdit.getTagTasks();
        // add tasks to tagtasks
        List<TagTask> updatedTagTasks = new ArrayList<>(oldTagTasks);
        updatedTagTasks.add(editTagDescriptor.newTagTask);
        Optional<MeetingLink> meetingLink = tagToEdit.getMeetingLink();
        return new Tag(tagName, updatedTagTasks, meetingLink);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskAddCommand)) {
            return false;
        }

        // state check
        TaskAddCommand e = (TaskAddCommand) other;
        return index.equals(e.index)
                && editTagDescriptor.equals(e.editTagDescriptor);
    }

    /**
     * Stores the details to edit the tag with. The name will replace the
     * corresponding name of the tag.
     */
    public static class EditTagDescriptor {
        private TagTask newTagTask;

        public EditTagDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTagDescriptor(EditTagDescriptor toCopy) {
            setNewTagTask(toCopy.newTagTask);
        }

        /**
         * Returns true if new task added is not equal to task in tasks.
         */
        public boolean isNewTagTask(List<TagTask> tasks) {
            for (TagTask task : tasks) {
                if (task.isSameTagTask(newTagTask)) {
                    return false;
                }
            }
            return true;
        }

        public void setNewTagTask(TagTask task) {
            this.newTagTask = task;
        }

        public Optional<TagTask> getNewTagTask() {
            return Optional.ofNullable(newTagTask);
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTagDescriptor)) {
                return false;
            }

            // state check
            EditTagDescriptor e = (EditTagDescriptor) other;

            return getNewTagTask().equals(e.getNewTagTask());
        }
    }
}
