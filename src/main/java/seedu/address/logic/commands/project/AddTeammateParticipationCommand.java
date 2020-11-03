package seedu.address.logic.commands.project;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Adds teammate to this project
 */
public class AddTeammateParticipationCommand extends Command {

    public static final String COMMAND_WORD = "addpart";
    public static final String MESSAGE_ADD_TEAMMATE_PARTICIPATION_SUCCESS = "New Teammate added: %1$s";
    public static final String MESSAGE_TEAMMATE_NOT_FOUND = "Teammate not found";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds teammate to this project"
        + "Parameters: Git UserName (must be a single word)\n "
        + "Example: " + COMMAND_WORD + " LucasTai98 ";

    private static final Logger logger = Logger.getLogger("AddTeammateParticipationCommandLogger");
    private final Person toAdd;

    /**
     * Creates an new teammate that is associated with the project
     */
    public AddTeammateParticipationCommand(GitUserIndex index) throws ParseException {
        requireNonNull(index);
        toAdd = Person.getPersonFromList(index);
        if (isNull(toAdd)) {
            logger.log(Level.INFO, "Teammate gitusername incorrect");
            throw new ParseException(MESSAGE_TEAMMATE_NOT_FOUND);
        }
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
     */
    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        toAdd.addProject(project);
        project.addParticipation(toAdd);
        model.addParticipation(project.getParticipation(toAdd.getGitUserNameString()));
        logger.log(Level.INFO, "Teammate added to project");
        return new CommandResult(String.format(MESSAGE_ADD_TEAMMATE_PARTICIPATION_SUCCESS,
            toAdd.getGitUserNameString()));
    }

}
