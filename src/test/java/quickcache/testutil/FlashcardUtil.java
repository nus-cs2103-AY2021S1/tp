package quickcache.testutil;

import static quickcache.logic.parser.CliSyntax.PREFIX_ANSWER;
import static quickcache.logic.parser.CliSyntax.PREFIX_QUESTION;

import quickcache.logic.commands.AddOpenEndedQuestionCommand;
import quickcache.model.flashcard.Flashcard;


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

    //        /**
    //         * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
    //         */
    //        public static String getEditFlashcardDescriptorDetails(EditFlashcardDescriptor descriptor) {
    //            StringBuilder sb = new StringBuilder();
    //            descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
    //            descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
    //            descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
    //            descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS)
    //            .append(address.value).append(" "));
    //
    //            return sb.toString();
    //        }
}
