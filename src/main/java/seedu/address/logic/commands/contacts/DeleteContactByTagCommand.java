package seedu.address.logic.commands.contacts;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.CommandType;
import seedu.address.logic.commands.CommandWord;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;

/**
 * Deletes all contacts with a particular tag
 */
public class DeleteContactByTagCommand extends Command {

    public static final String COMMAND_WORD = CommandWord.DELETE_BY_TAG.toString();

    public static final String COMMAND_TYPE = CommandType.CONTACT.toString();

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + COMMAND_TYPE
            + ": Deletes all contacts with the user\n\n"
            + "Parameters: tagForDeletion must be a valid tag\n\n"
            + "Example: " + COMMAND_WORD + " " + COMMAND_TYPE + "classmates";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted contacts tagged under: ";
    public static final String MESSAGE_NON_EXISTENT_TAG = "The tag you have entered does not exist in our database.";
    private final Tag tagForDeletion;

    /**
     * @param tagForDeletion contacts with this user-input tag will be deleted.
     */
    public DeleteContactByTagCommand(Tag tagForDeletion) {
        this.tagForDeletion = tagForDeletion;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!isExistingTag(model, tagForDeletion)) {
            throw new CommandException(MESSAGE_NON_EXISTENT_TAG);
        }
        model.deletePersonsByTag(tagForDeletion);
        return new CommandResult(MESSAGE_DELETE_PERSON_SUCCESS + tagForDeletion.tagName);
    }


    /**
     * Checks if this tag exists.
     */
    private static boolean isExistingTag(Model model, Tag tag) {
        return model.getPersonTags().contains(tag) || model.getSuperTags().contains(tag);
    }
    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof seedu.address.logic.commands.contacts.DeleteContactByTagCommand
                && tagForDeletion.equals(((seedu.address.logic.commands.contacts.DeleteContactByTagCommand) other)
                .tagForDeletion)); // state check
    }
}
