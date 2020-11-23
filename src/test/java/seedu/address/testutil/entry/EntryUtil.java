package seedu.address.testutil.entry;

import static seedu.address.logic.parser.CliSyntax.PREFIX_TRANSLATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORD;

import seedu.address.logic.commands.entry.AddCommand;
import seedu.address.logic.commands.entry.EditCommand.EditEntryDescriptor;
import seedu.address.model.deck.entry.Entry;

/**
 * A utility class for Entry.
 */
public class EntryUtil {

    /**
     * Returns an add command string for adding the {@code entry}.
     */
    public static String getAddCommand(Entry entry) {
        return AddCommand.COMMAND_WORD + " " + getEntryDetails(entry);
    }

    /**
     * Returns the part of command string for the given {@code entry}'s details.
     */
    public static String getEntryDetails(Entry entry) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_WORD + entry.getWord().word + " ");
        sb.append(PREFIX_TRANSLATION + entry.getTranslation().translation + " ");
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditEntryDescriptor}'s details.
     */
    public static String getEditEntryDescriptorDetails(EditEntryDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getWord().ifPresent(word -> sb.append(PREFIX_WORD).append(word.word).append(" "));
        descriptor.getTranslation().ifPresent(translation -> sb.append(PREFIX_TRANSLATION)
                .append(translation.translation).append(" "));
        return sb.toString();
    }
}
