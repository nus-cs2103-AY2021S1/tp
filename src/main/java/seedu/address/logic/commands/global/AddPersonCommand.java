package seedu.address.logic.commands.global;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_GIT_USERNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PERSON_PHONE;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Creates a new person within a project
 */
public class AddPersonCommand extends Command {

    public static final String COMMAND_WORD = "addperson";


    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Creates a new teammate as a part of this project"
        + "\nParameters: "
        + PREFIX_PERSON_NAME + "NAME "
        + PREFIX_PERSON_GIT_USERNAME + "GIT USERNAME "
        + PREFIX_PERSON_PHONE + "PHONE "
        + PREFIX_PERSON_EMAIL + "EMAIL "
        + PREFIX_PERSON_ADDRESS + "ADDRESS\n"
        + "Example: " + COMMAND_WORD + " mn/Lucas mg/LucasTai98 mp/93824823 me/lucas@gmail.com ma/18 Evelyn Road";

    public static final String MESSAGE_NEW_PERSON_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the main catalogue";

    private static final Logger logger = Logger.getLogger("AddPersonCommandLogger");
    private final Person toAdd;

    /**
     * Creates an new teammate that is associated with the project
     */
    public AddPersonCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (model.hasPerson(toAdd)) {
            logger.log(Level.WARNING, MESSAGE_DUPLICATE_PERSON);
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }
        model.addPerson(toAdd);
        switch (model.getStatus()) {
        case PROJECT:
        case TEAMMATE:
        case TASK:
            Project project = model.getProjectToBeDisplayedOnDashboard().get();
            toAdd.addProject(project);
            project.addParticipation(toAdd);
            model.addParticipation(project.getParticipation(toAdd.getGitUserNameString()));
            break;
        default:
        }
        logger.log(Level.INFO, "New Person added");

        return new CommandResult(String.format(MESSAGE_NEW_PERSON_SUCCESS, toAdd.getGitUserNameString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof AddPersonCommand // instanceof handles nulls
            && toAdd.equals(((AddPersonCommand) other).toAdd));
    }
}
