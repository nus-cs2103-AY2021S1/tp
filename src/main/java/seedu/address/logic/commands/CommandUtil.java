package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Contains utility methods used in multiple command classes.
 */
public class CommandUtil {
    public static final String MESSAGE_GENERIC_DUPLICATE_PERSON = "This resident already exists in Hall-y.";
    public static final String MESSAGE_DUPLICATE_EMAIL =
            "An existing resident already has this email address in Hall-y.";
    public static final String MESSAGE_DUPLICATE_PHONE_NUMBER =
            "An existing resident already has this phone number in Hall-y.";
    public static final String MESSAGE_DUPLICATE_MATRICULATION_NUMBER =
            "An existing resident already has this matriculation number in Hall-y.";
    public static final String MESSAGE_DUPLICATE_BLOCK_ROOM =
            "An existing resident is already staying in this room in Hall-y.";

    /**
     * Checks that the person is unique in Hall-y
     * @param model the Hall-y Model
     * @param person the person to check uniqueness
     * @throws CommandException if the person is not unique
     */
    public static void requireUniquePerson(Model model, Person person) throws CommandException {
        requireNonNull(person);
        if (model.hasPerson(person)) {
            StringBuilder stringBuilder = new StringBuilder(MESSAGE_GENERIC_DUPLICATE_PERSON + "\n");
            if (model.hasMatriculationNumber(person.getMatriculationNumber())) {
                stringBuilder.append(MESSAGE_DUPLICATE_MATRICULATION_NUMBER + "\n");
            }
            if (model.hasBlockRoom(person.getBlock(), person.getRoom())) {
                stringBuilder.append(MESSAGE_DUPLICATE_BLOCK_ROOM + "\n");
            }
            if (model.hasEmail(person.getEmail())) {
                stringBuilder.append(MESSAGE_DUPLICATE_EMAIL + "\n");
            }
            if (model.hasPhone(person.getPhone())) {
                stringBuilder.append(MESSAGE_DUPLICATE_PHONE_NUMBER + "\n");
            }
            throw new CommandException(stringBuilder.toString());
        }
    }
}
