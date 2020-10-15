package quickcache.testutil;

import static quickcache.logic.commands.EditCommand.EditFlashcardDescriptor;
import static quickcache.logic.parser.CliSyntax.*;

import quickcache.logic.commands.AddOpenEndedQuestionCommand;
import quickcache.model.flashcard.Flashcard;
import quickcache.model.flashcard.Tag;


/**
 * A utility class for Person.
 */
public class FlashcardUtil {

    /**
     * Returns an add command string for adding the {@code person}.
     */
    public static String getAddCommand(Flashcard flashcard) {
        return AddOpenEndedQuestionCommand.COMMAND_WORD + " " + getFlashcardDetails(flashcard);
    }

    /**
     * Returns the part of command string for the given {@code person}'s details.
     */
    public static String getFlashcardDetails(Flashcard flashcard) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_QUESTION + flashcard.getQuestion().toString() + " ");
        sb.append(PREFIX_ANSWER + flashcard.getAnswer().toString() + " ");


        return sb.toString();
    }

        /**
         * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
         */
        public static String getEditFlashcardDescriptorDetails(EditFlashcardDescriptor descriptor) {
            StringBuilder sb = new StringBuilder();
            descriptor.getAnswer().ifPresent(answer -> sb.append(PREFIX_ANSWER).append(answer.getValue()).append(" "));
            descriptor.getQuestion().ifPresent(question -> sb.append(PREFIX_QUESTION).append(question.getValue()).append(" "));
            descriptor.getChoices().ifPresent(choice -> {
                for (int i = 0; i < choice.length; i++) {
                    sb.append(PREFIX_CHOICE).append(choice[i].getValue());
                }
            });
            descriptor.getTags().ifPresent(tags-> {
                for (int i = 0; i < tags.size(); i++) {
                    Tag[] temp = (Tag[]) tags.toArray();
                    sb.append(PREFIX_TAG).append(temp[i].getName());
                }
            });
            return sb.toString();
        }
}
