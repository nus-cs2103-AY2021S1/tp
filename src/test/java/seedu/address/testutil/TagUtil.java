package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_FILE_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LABEL_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG_NAME;

import seedu.address.logic.commands.TagCommand;
import seedu.address.model.label.Label;
import seedu.address.model.tag.Tag;


/**
 * A utility class for Tag.
 */
public class TagUtil {

    /**
     * Returns an add command string for adding the {@code tag}.
     */
    public static String getTagCommand(Tag tag) {
        return TagCommand.COMMAND_WORD + " " + getTagDetails(tag);
    }

    /**
     * Returns the part of command string for the given {@code tag}'s details.
     */
    public static String getTagDetails(Tag tag) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TAG_NAME + tag.getTagName().tagName + " ");
        sb.append(PREFIX_FILE_ADDRESS + tag.getFileAddress().value + " ");

        StringBuilder labels = new StringBuilder();
        for (Label label : tag.getLabels()) {
            labels.append(PREFIX_LABEL_NAME + label.getLabel() + " ");
        }

        sb.append(labels);
        return sb.toString();
    }
}
