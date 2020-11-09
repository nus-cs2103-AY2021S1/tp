package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Person;

/**
 * Deletes a project identified using it's displayed index from the main catalogue.
 */
public class DeletePersonCommand extends Command {

    public static final String COMMAND_WORD = "deleteperson";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the teammate identified by the Git Username in the current project.\n"
            + "Parameters: Git UserName (must be a single word)\n"
            + "Example: " + COMMAND_WORD + " LucasTai98";

    public static final String MESSAGE_DELETED_PERSON_LOGGER = "A person was deleted";
    public static final String MESSAGE_DELETED_PERSON_FAILURE = "A person was deleted";
    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Person: %1$s";
    private static final Logger logger = Logger.getLogger("DeletePersonCommand");

    private final GitUserIndex gitUserIndex;

    public DeletePersonCommand(GitUserIndex gitUserIndex) {
        this.gitUserIndex = gitUserIndex;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!Person.isPresent(gitUserIndex)) {
            logger.log(Level.WARNING, MESSAGE_DELETED_PERSON_FAILURE);
            throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_NAME);
        }
        Person personToDelete = Person.getPersonFromList(gitUserIndex);
        Participation.deleteAllParticipationOf(model, personToDelete);
        Person.deletePersonFromList(personToDelete);
        model.deletePerson(personToDelete);

        logger.log(Level.INFO, MESSAGE_DELETED_PERSON_LOGGER);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, personToDelete));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePersonCommand) // instanceof handles nulls
                && gitUserIndex.equals(((DeletePersonCommand) other).gitUserIndex); // state check
    }
}
