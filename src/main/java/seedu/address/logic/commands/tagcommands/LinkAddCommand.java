package seedu.address.logic.commands.tagcommands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TAGS;

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

/**
 * Adds a link to the tag's meeting link field in Projact.
 */
public class LinkAddCommand extends Command {

    public static final String COMMAND_WORD = "linkadd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a link to a tag in Projact. "
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_LINK + "LINK\n"
            + "Example: " + COMMAND_WORD
            + " 1 " + PREFIX_LINK + "https://skype.com/cs2101g01";

    public static final String MESSAGE_SUCCESS = "New link added: %1$s";
    public static final String MESSAGE_LINK_NOT_PROVIDED = "A link must be provided.";
    public static final String MESSAGE_LINK_ALREADY_EXISTS = "This tag already has a link tied to it.";

    private final Index targetIndex;
    private final MeetingLink toAdd;

    /**
     * Creates a LinkAddCommand to add the specified {@code MeetingLink}.
     */
    public LinkAddCommand(Index index, MeetingLink link) {
        requireAllNonNull(index, link);
        this.targetIndex = index;
        this.toAdd = link;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        List<Tag> lastShownList = model.getFilteredTagList();
        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TAG_DISPLAYED_INDEX);
        }

        Tag tag = lastShownList.get(targetIndex.getZeroBased());

        if (tag.getMeetingLink().isPresent()) {
            throw new CommandException(MESSAGE_LINK_ALREADY_EXISTS);
        }

        Tag editedTag = createEditedTag(tag, toAdd);
        model.setTag(tag, editedTag);
        model.updateFilteredTagList(PREDICATE_SHOW_ALL_TAGS);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    /**
     * Creates and returns a {@code Tag} with the name of {@code tagToEdit} with the new meetingLink.
     */
    private static Tag createEditedTag(Tag tagToEdit, MeetingLink meetingLink) {
        assert tagToEdit != null;

        return new Tag(tagToEdit.getTagName(), tagToEdit.getTagTasks(), Optional.of(meetingLink));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof LinkAddCommand // instanceof handles nulls
                && toAdd.equals(((LinkAddCommand) other).toAdd));
    }
}
