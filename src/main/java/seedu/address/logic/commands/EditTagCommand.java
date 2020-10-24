package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWTAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLDTAG;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_BUGS;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.bug.Bug;
import seedu.address.model.bug.Description;
import seedu.address.model.bug.Name;
import seedu.address.model.bug.Priority;
import seedu.address.model.bug.State;
import seedu.address.model.tag.Tag;

public class EditTagCommand extends Command {

    public static final String COMMAND_WORD = "editTag";

    //TODO Update the DG such that only valid tags are used

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the tags of "
            + "the bug identified by the index number used in the displayed bug list."
            + "The existing tag supplied by the user will be replaced with the new tag given"
            + "as input.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_OLDTAG + "OLD_TAG "
            + PREFIX_NEWTAG + "NEW_TAG\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_OLDTAG + "display " + PREFIX_NEWTAG + "Ui";

    public static final String MESSAGE_EDIT_BUG_SUCCESS = "Edited Tag: %1$s";
    public static final String MESSAGE_INVALID_OLD = "A valid existing tag must be supplied.";
    public static final String MESSAGE_INVALID_NEW = "The new tag already exists!";
    public static final String MESSAGE_NOT_UPDATED = "Input values cannot be null.";

    private Index index;
    private Tag oldTag;
    private Tag newTag;

    /**
     * Creates a new instance of an EditTagCommand with the appropriate {@code index},
     * {@code oldTag} and {@code newTag}.
     *
     * @param index of the bug in the filtered bug list to edit
     * @param oldTag to be modified
     * @param newTag to replace old tag
     */
    public EditTagCommand(Index index, Tag oldTag, Tag newTag) {
        requireNonNull(index);
        requireNonNull(oldTag);
        requireNonNull(newTag);
        this.index = index;
        this.oldTag = oldTag;
        this.newTag = newTag;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Bug> lastShownList = model.getFilteredBugList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_BUG_DISPLAYED_INDEX);
        }

        Bug bugToEdit = lastShownList.get(index.getZeroBased());
        Bug editedBug = updateTagInBug(bugToEdit, oldTag, newTag);

        model.setBug(bugToEdit, editedBug);
        model.updateFilteredBugList(PREDICATE_SHOW_ALL_BUGS);
        return new CommandResult(String.format(MESSAGE_EDIT_BUG_SUCCESS, editedBug));
    }

    /**
     * Updates the old tag in the specified bug and replaces it with the new tag.
     *
     * @param bugToEdit bug whose tag is to be edited
     * @param oldTag to be replaced
     * @param newTag to replace the old tag
     * @return updated bug
     * @throws CommandException if {@code oldTag} does not exist, the {@code newTag} already exists or the inputs are
     * null
     */
    public static Bug updateTagInBug(Bug bugToEdit, Tag oldTag, Tag newTag) throws CommandException {
        if (bugToEdit == null || oldTag == null || newTag == null) {
            throw new CommandException(MESSAGE_NOT_UPDATED);
        }

        Set<Tag> existingTagSet = bugToEdit.getTags();

        if (!existingTagSet.contains(oldTag)) {
            throw new CommandException((MESSAGE_INVALID_OLD));
        }

        if (existingTagSet.contains(newTag)) {
            throw new CommandException((MESSAGE_INVALID_NEW));
        }

        Name bugName = bugToEdit.getName();
        State bugState = bugToEdit.getState();
        Description bugDescription = bugToEdit.getDescription();
        Set<Tag> updatedTags = updateTagSet(existingTagSet, oldTag, newTag);
        Priority bugPriority = bugToEdit.getPriority();

        return new Bug(bugName, bugState, bugDescription, updatedTags, bugPriority);
    }

    private static Set<Tag> updateTagSet(Set<Tag> existingTagSet, Tag oldTag, Tag newTag) {
        assert existingTagSet.contains(oldTag);
        Set<Tag> setCopy = new HashSet<>(existingTagSet);
        setCopy.remove(oldTag);
        setCopy.add(newTag);
        return setCopy;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTagCommand)) {
            return false;
        }

        // state check
        EditTagCommand e = (EditTagCommand) other;
        return index.equals(e.index)
                       && oldTag.equals(e.oldTag)
                       && newTag.equals(e.newTag);
    }


}
