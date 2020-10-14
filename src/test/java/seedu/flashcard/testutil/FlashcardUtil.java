package seedu.flashcard.testutil;

/**
 * A utility class for Person.
 */
public class FlashcardUtil {
//
//    /**
//     * Returns an add command string for adding the {@code person}.
//     */
//    public static String getAddCommand(Flashcard flashcard) {
//        return AddCommand.COMMAND_WORD + " " + getPersonDetails(flashcard);
//    }
//
//    /**
//     * Returns the part of command string for the given {@code person}'s details.
//     */
//    public static String getPersonDetails(Flashcard flashcard) {
//        StringBuilder sb = new StringBuilder();
//        sb.append(PREFIX_NAME + flashcard.getName().fullName + " ");
//        sb.append(PREFIX_PHONE + flashcard.getPhone().value + " ");
//        sb.append(PREFIX_EMAIL + flashcard.getEmail().value + " ");
//        sb.append(PREFIX_ADDRESS + flashcard.getAddress().value + " ");
//        flashcard.getTags().stream().forEach(
//            s -> sb.append(PREFIX_TAG + s.tagName + " ")
//        );
//        return sb.toString();
//    }
//
//    /**
//     * Returns the part of command string for the given {@code EditPersonDescriptor}'s details.
//     */
//    public static String getEditPersonDescriptorDetails(EditPersonDescriptor descriptor) {
//        StringBuilder sb = new StringBuilder();
//        descriptor.getName().ifPresent(name -> sb.append(PREFIX_NAME).append(name.fullName).append(" "));
//        descriptor.getPhone().ifPresent(phone -> sb.append(PREFIX_PHONE).append(phone.value).append(" "));
//        descriptor.getEmail().ifPresent(email -> sb.append(PREFIX_EMAIL).append(email.value).append(" "));
//        descriptor.getAddress().ifPresent(address -> sb.append(PREFIX_ADDRESS).append(address.value).append(" "));
//        if (descriptor.getTags().isPresent()) {
//            Set<Tag> tags = descriptor.getTags().get();
//            if (tags.isEmpty()) {
//                sb.append(PREFIX_TAG);
//            } else {
//                tags.forEach(s -> sb.append(PREFIX_TAG).append(s.tagName).append(" "));
//            }
//        }
//        return sb.toString();
//    }
}
