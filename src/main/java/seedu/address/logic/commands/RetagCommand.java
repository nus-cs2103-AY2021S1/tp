package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OLD_TAG_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.FileAddress;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

/**
 * Changes a tag's name in the address book.
 */
public class RetagCommand extends Command {
    public static final String COMMAND_WORD = "retag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retags the tagged file identified by the old tag into the new tag.\n\n"
            + "Parameters: "
            + PREFIX_OLD_TAG_NAME + "OLD_TAG_NAME "
            + PREFIX_TAG_NAME + "NEW_TAG_NAME\n\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_OLD_TAG_NAME + "my_files "
            + PREFIX_TAG_NAME + "my_old_files ";
    public static final String RETAG_COMMAND_USAGE = COMMAND_WORD + " "
            + PREFIX_OLD_TAG_NAME + "<OLD_TAG_NAME> " + PREFIX_TAG_NAME + "<NEW_TAG_NAME>";
    public static final String MESSAGE_RETAG_TAG_SUCCESS = "Retagged Tag: %s to Tag: %s";
    public static final String MESSAGE_OLD_TAG_NOT_FOUND = " %s tag not found!"
            + " Please make sure that the old tag is present before retagging.";
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
     * Executes the command and renames the tag in the model to the new tag.
     *
     * @param model {@code Model} which the command should operate on.
     * @return A Command result of executing retag command.
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

        // Check if newTagName is in tag list
        List<Tag> newTagList = model.findFilteredTagList(new TagNameEqualsKeywordPredicate(newTagName));
        if (!newTagList.isEmpty() && !newTagName.equals(oldTagName)) {
            throw new CommandException(MESSAGE_DUPLICATE_TAG);
        }

        // Get oldTagName
        Tag tagToChange = oldTagList.get(0);
        // Get file path
        FileAddress fileAddress = tagToChange.getFileAddress();
        // Delete old tag
        model.deleteTag(tagToChange);

        // Add new tag
        Tag newTag = new Tag(newTagName, fileAddress, tagToChange.getLabels());
        model.addTag(newTag);

        // Save commit for undo
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_RETAG_TAG_SUCCESS, oldTagName, newTagName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RetagCommand // instanceof handles nulls
                && newTagName.equals(((RetagCommand) other).newTagName)
                && oldTagName.equals(((RetagCommand) other).oldTagName));
    }
}
