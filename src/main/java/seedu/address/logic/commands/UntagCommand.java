package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import java.util.List;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.tag.TagName;
import seedu.address.model.tag.TagNameEqualsKeywordPredicate;

/**
 * Untags a Tag identified using it's unique tag.
 */
public class UntagCommand extends Command {

    public static final String COMMAND_WORD = "untag";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Untags the tagged file identified by the unique tag used in the displayed Tag list.\n\n"
            + "Parameters: " + PREFIX_TAG_NAME + "TAG_NAME (Must be an existing tag)\n\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_TAG_NAME + "HelloFile.txt";
    public static final String UNTAG_MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_TAG_NAME + "<TAG_NAME>";
    public static final String MESSAGE_UNTAG_TAG_SUCCESS = "Untagged Tag: %s";
    public static final String MESSAGE_TAG_NOT_FOUND = " %s tag not found!"
            + " Please make sure that the tag is present before untagging.";

    private final TagName tagName;

    public UntagCommand(TagName tagName) {
        this.tagName = tagName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        // Check if tag to untag is in tag list
        List<Tag> tagList = model.findFilteredTagList(new TagNameEqualsKeywordPredicate(tagName));
        if (tagList.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_TAG_NOT_FOUND, tagName));
        }

        model.deleteTag(tagList.get(0));

        // Save commit for undo
        model.commitAddressBook();

        return new CommandResult(String.format(MESSAGE_UNTAG_TAG_SUCCESS, tagName));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UntagCommand // instanceof handles nulls
                && tagName.equals(((UntagCommand) other).tagName)); // state check
    }
}
