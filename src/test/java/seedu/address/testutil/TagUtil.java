package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;

import seedu.address.logic.commands.tagcommands.TagAddCommand;
import seedu.address.logic.commands.tagcommands.TagEditCommand;
import seedu.address.model.tag.Tag;

/**
 * A utility class for Tag.
 */
public class TagUtil {

    /**
     * Returns a tagadd command string for adding the {@code tag}.
     */
    public static String getTagAddCommand(Tag tag) {
        return TagAddCommand.COMMAND_WORD + " " + getTagDetails(tag);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getTagDetails(Tag tag) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TAG + tag.getTagName().tagName + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
     */
    public static String getEditTagDescriptorDetails(TagEditCommand.EditTagDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTagName().ifPresent(tagname -> sb.append(PREFIX_TAG).append(tagname.tagName).append(" "));
        return sb.toString();
    }
}
