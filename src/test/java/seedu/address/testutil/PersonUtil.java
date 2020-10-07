package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.AddCommand;
import seedu.address.model.person.Tag;


/**
 * A utility class for Tag.
 */
public class PersonUtil {

    /**
     * Returns an add command string for adding the {@code tag}.
     */
    public static String getAddCommand(Tag tag) {
        return AddCommand.COMMAND_WORD + " " + getPersonDetails(tag);
    }

    /**
     * Returns the part of command string for the given {@code tag}'s details.
     */
    public static String getPersonDetails(Tag tag) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + tag.getTagName().tagName + " ");
        sb.append(PREFIX_ADDRESS + tag.getFileAddress().value + " ");
        return sb.toString();
    }
}
