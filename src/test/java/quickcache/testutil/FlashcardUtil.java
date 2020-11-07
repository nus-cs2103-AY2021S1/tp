package quickcache.testutil;

import static quickcache.logic.commands.EditCommand.EditFlashcardDescriptor;
import static quickcache.logic.parser.CliSyntax.PREFIX_ANSWER;
import static quickcache.logic.parser.CliSyntax.PREFIX_CHOICE;
import static quickcache.logic.parser.CliSyntax.PREFIX_DIFFICULTY;
import static quickcache.logic.parser.CliSyntax.PREFIX_QUESTION;
import static quickcache.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Optional;
import java.util.Set;

import quickcache.logic.commands.AddMultipleChoiceQuestionCommand;
import quickcache.logic.commands.AddOpenEndedQuestionCommand;
import quickcache.model.flashcard.Choice;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.Tag;

/**
 * A utility class for Flashcard.
 */
public class FlashcardUtil {

    /**
     * Returns an add command string for adding the {@code flashcard}.
     */
    public static String getAddCommand(Flashcard flashcard) {
        return AddOpenEndedQuestionCommand.COMMAND_WORD + " " + getFlashcardDetails(flashcard);
    }

    /**
     * Returns an addMcq command string for adding the {@code flashcard}.
     */
    public static String getAddMcqCommand(Flashcard flashcard) {
        return AddMultipleChoiceQuestionCommand.COMMAND_WORD + " " + getFlashcardDetails(flashcard);
    }

    /**
     * Returns the part of command string for the given {@code flashcard}'s details.
     */
    public static String getFlashcardDetails(Flashcard flashcard) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + flashcard.getQuestion().toString() + " ");
        sb.append(PREFIX_ANSWER + flashcard.getAnswer().toString() + " ");
        Optional<Choice[]> choices = flashcard.getQuestion().getChoices();
        if (choices.isPresent()) {
            Choice[] choicesArray = choices.get();
            for (Choice choice : choicesArray) {
                sb.append(PREFIX_CHOICE + choice.getValue() + " ");
            }
        }
        Set<Tag> tags = flashcard.getTags();

        for (Tag tag: tags) {
            sb.append(PREFIX_TAG + tag.getName() + " ");
        }

        sb.append(PREFIX_DIFFICULTY + flashcard.getDifficulty().value);

        return sb.toString();
    }



    /**
     * Returns the part of command string for the given {@code EditFlashcardDescriptor}'s details.
     */
    public static String getEditFlashcardDescriptorDetails(EditFlashcardDescriptor descriptor) {
        StringBuilder sb = new StringBuilder();
        descriptor.getAnswer().ifPresent(answer -> sb.append(PREFIX_ANSWER).append(answer.getValue()).append(" "));
        descriptor.getQuestion().ifPresent(question -> sb.append(PREFIX_QUESTION)
                .append(question).append(" "));
        descriptor.getChoices().ifPresent(choice -> {
            for (int i = 0; i < choice.length; i++) {
                sb.append(PREFIX_CHOICE).append(choice[i].getValue()).append(" ");
            }
        });
        descriptor.getTags().ifPresent(tags -> {
            Tag[] empty = new Tag[0];
            Tag[] temp = tags.toArray(empty);
            for (int i = 0; i < temp.length; i++) {
                sb.append(PREFIX_TAG).append(temp[i].getName()).append(" ");
            }
        });
        descriptor.getDifficulty().ifPresent(difficulty -> sb.append(PREFIX_DIFFICULTY)
                .append(difficulty.getValue()).append(" "));
        return sb.toString();
    }
}
