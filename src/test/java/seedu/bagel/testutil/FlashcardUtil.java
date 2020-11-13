package seedu.bagel.testutil;

import static seedu.bagel.logic.parser.CliSyntax.PREFIX_DESC;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_LINK;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_SET;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.bagel.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Set;

import seedu.bagel.logic.commands.AddCommand;
import seedu.bagel.logic.commands.EditCommand.EditFlashcardDescriptor;
import seedu.bagel.model.flashcard.Flashcard;
import seedu.bagel.model.tag.Tag;

/**
 * A utility class for Flashcard.
 */
public class FlashcardUtil {

    /**
     * Returns an add command string for adding the {@code flashcard}.
     */
    public static String getAddCommand(Flashcard flashcard) {
        return AddCommand.COMMAND_WORD + " " + getFlashcardDetails(flashcard);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getFlashcardDetails(Flashcard flashcard) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + flashcard.getTitle().fullTitle + " ");
        sb.append(PREFIX_DESC + flashcard.getDescription().value + " ");
        sb.append(PREFIX_SET + flashcard.getFlashcardSet().value + " ");
        sb.append(PREFIX_LINK + flashcard.getLink().value + " ");
        flashcard.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFlashcardDescriptor}'s details.
     */
    public static String getEditFlashcardDescriptorDetails(EditFlashcardDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getTitle().ifPresent(title -> sb.append(PREFIX_TITLE).append(title.fullTitle).append(" "));
        descriptor.getDescription().ifPresent(description -> sb.append(PREFIX_DESC).append(description.value)
                .append(" "));
        descriptor.getLink().ifPresent(link -> sb.append(PREFIX_LINK).append(link.value).append(" "));
        descriptor.getFlashcardSet().ifPresent(flashcardSet -> sb.append(PREFIX_SET).append(flashcardSet.value)
                .append(" "));
        if (descriptor.getTags().isPresent()) {
            Set<Tag> tags = descriptor.getTags().get();
            if (tags.isEmpty()) {
                sb.append(PREFIX_TAG);
            } else {
                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
            }
        }
        return sb.toString();
    }
}
