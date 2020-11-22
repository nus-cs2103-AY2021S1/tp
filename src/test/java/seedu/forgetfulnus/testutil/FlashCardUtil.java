package seedu.forgetfulnus.testutil;

import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_DIFFICULTY_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_ENGLISH_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GENDER_TAG;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_GERMAN_PHRASE;
import static seedu.forgetfulnus.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Set;

import seedu.forgetfulnus.logic.commands.AddCommand;
import seedu.forgetfulnus.logic.commands.EditCommand;
import seedu.forgetfulnus.model.flashcard.FlashCard;
import seedu.forgetfulnus.model.tag.Tag;

/**
 * A utility class for FlashCard.
 */
public class FlashCardUtil {

    /**
     * Returns an add command string for adding the {@code flashCard}.
     */
    public static String getAddCommand(FlashCard flashCard) {
        return AddCommand.COMMAND_WORD + " " + getFlashCardDetails(flashCard);
    }

    /**
     * Returns the part of command string for the given {@code flashCard}'s details.
     */
    public static String getFlashCardDetails(FlashCard flashCard) {
        StringBuilder sb = new StringBuilder();

        sb.append(PREFIX_GERMAN_PHRASE + flashCard.getGermanPhrase().toString() + " ");
        sb.append(PREFIX_ENGLISH_PHRASE + flashCard.getEnglishPhrase().toString() + " ");
        sb.append(PREFIX_DIFFICULTY_TAG + flashCard.getDifficultyTag().toString() + " ");
        sb.append(PREFIX_GENDER_TAG + flashCard.getGenderTag().toString() + " ");
        flashCard.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }

    /**
     * Returns the part of command string for the given {@code EditFlashCardDescriptor}'s details.
     */
    public static String getEditFlashCardDescriptorDetails(EditCommand.EditFlashCardDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getGermanPhrase()
                .ifPresent(germanPhrase -> sb.append(PREFIX_GERMAN_PHRASE)
                        .append(germanPhrase.toString()).append(" "));
        descriptor.getEnglishPhrase()
                .ifPresent(englishPhrase -> sb.append(PREFIX_ENGLISH_PHRASE)
                        .append(englishPhrase.toString()).append(" "));
        descriptor.getDifficultyTag()
                .ifPresent(difficultyTag -> sb.append(PREFIX_DIFFICULTY_TAG)
                        .append(difficultyTag.toString()).append((" ")));
        descriptor.getGenderTag()
                .ifPresent(genderTag -> sb.append(PREFIX_GENDER_TAG)
                        .append(genderTag.toString()).append((" ")));

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
