package seedu.address.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Deletes a link from a particular tag identified by its index from the tag list.
 */
public class LinkDeleteCommand extends Command {
    public static final String COMMAND_WORD = "linkdelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the link from the tag identified by the index number used in the displayed tag list.\n"
            + "Parameters: INDEX (must be a positive integer from 1 to " + Integer.MAX_VALUE + ")\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_LINK_SUCCESS = "Deleted Link: %1$s from %2$s";
    public static final String MESSAGE_NO_LINK_FAILURE = "This tag does not have link!";

    private final Index targetIndex;

    public LinkDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tag> lastShownList = model.getFilteredTagList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tagToDeleteLinkFrom = lastShownList.get(targetIndex.getZeroBased());

        if (tagToDeleteLinkFrom.getMeetingLink().isEmpty()) {
            throw new CommandException(MESSAGE_NO_LINK_FAILURE);
        }

        Tag editedTag = new Tag(tagToDeleteLinkFrom.getTagName(),
                                tagToDeleteLinkFrom.getTagTasks(), Optional.empty());
        model.setTag(tagToDeleteLinkFrom, editedTag);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);

        return new CommandResult(String.format(MESSAGE_DELETE_LINK_SUCCESS,
                                                tagToDeleteLinkFrom.getMeetingLink().get(),
                                                tagToDeleteLinkFrom.getTagName()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((LinkDeleteCommand) other).targetIndex)); // state check
    }
}
