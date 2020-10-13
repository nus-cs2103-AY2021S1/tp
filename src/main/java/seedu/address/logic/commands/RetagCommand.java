package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class RetagCommand extends Command {
    public static final String COMMAND_WORD = "retag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Untags the tagged file identified by the index number used in the displayed Tag list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_RETAG_TAG_SUCCESS = "Untagged Tag: %1$s";
    public static final String MESSAGE_OLD_TAG_NOT_FOUND = " %s tag not found!"
            + "Please make sure that file is present before adding.";
    public static final String MESSAGE_DUPLICATE_TAG = "Duplicate tag name!";

    private final TagName newTagName;
    private final TagName oldTagName;

    /**
     * Creates a RetagCommand to retag the specified {@code oldTagName} to {@code newTagName}
     */
    public RetagCommand(TagName oldTagName, TagName newTagName) {
        requireNonNull(oldTagName);
        requireNonNull(newTagName);

        this.oldTagName = oldTagName;
        this.newTagName = newTagName;
    }

    /**
     * Executes the command and add the tag to model.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A Command result of executing tag command.
     * @throws CommandException
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check if oldTagName is in tag list
        List<Tag> oldTagList = model.findFilteredTagList(new TagNameEqualsKeywordPredicate(oldTagName));
        if (oldTagList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_OLD_TAG_NOT_FOUND, oldTagName.toString()));
        }

        // Get oldTagName
        Tag tagToChange = oldTagList.get(0);
        // Get file path
        FileAddress fileAddress = tagToChange.getFileAddress();

        // Check if newTagName is in tag list
        List<Tag> newTagList = model.findFilteredTagList(new TagNameEqualsKeywordPredicate(newTagName));
        if (!newTagList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_TAG));
        }

        // Delete old tag
        model.deleteTag(tagToChange);

        Tag newTag = new Tag(newTagName, fileAddress);
        // Add new tag
        model.addTag(newTag);
        return new CommandResult(String.format(MESSAGE_RETAG_TAG_SUCCESS, newTagName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RetagCommand // instanceof handles nulls
                && newTagName.equals(((RetagCommand) other).newTagName)
                && oldTagName.equals(((RetagCommand) other).oldTagName));
    }
}
