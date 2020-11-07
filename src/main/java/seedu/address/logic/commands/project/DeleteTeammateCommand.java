package seedu.address.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.GitUserIndex;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.participation.Participation;
import seedu.address.model.person.Person;
import seedu.address.model.project.Project;

/**
 * Deletes a project identified using it's displayed index from the main catalogue.
 */
public class DeleteTeammateCommand extends Command {

    public static final String COMMAND_WORD = "deleteteammate";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the teammate identified by the Git Username in the current project.\n"
            + "Parameters: Git UserName (must be a single word)\n"
            + "Example: " + COMMAND_WORD + " LucasTai98";

    public static final String MESSAGE_DELETE_TEAMMATE_SUCCESS = "Deleted Teammate: %1$s";

    private final GitUserIndex gitUserIndex;

    public DeleteTeammateCommand(GitUserIndex gitUserIndex) {
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
    //    public CommandResult execute(Model model) throws CommandException {
    //        requireNonNull(model);
    //        Project project = model.getProjectToBeDisplayedOnDashboard().get();
    //        List<Participation> lastShownList = project.getTeammates();
    //
    //        if (!project.hasParticipation(gitUserIndex.getGitUserNameString())) {
    //            throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_NAME);
    //        }
    //
    //        Participation participation = project.getParticipation(gitUserIndex.getGitUserNameString());
    //        Person personToDelete = Person.getPersonFromList(gitUserIndex);
    //
    //        assert personToDelete != null;
    //        Project currentProject;
    //        for (int i = 0; i < Project.getAllProjects().size(); i++) {
    //            currentProject = Project.getAllProjects().get(i);
    //            if (currentProject.hasParticipation(personToDelete.getGitUserNameString())) {
    //                participation = currentProject.getParticipation(personToDelete.getGitUserNameString());
    //                currentProject.removeParticipation(participation);
    //                if (model.hasParticipation(participation)) {
    //                    model.deleteParticipation(participation);
    //                }
    //            }
    //        }
    //        Person.getAllPeople().remove(personToDelete);
    //        model.deletePerson(personToDelete);
    //
    //        if (model.getTeammateToBeDisplayedOnDashboard().isPresent()
    //                && model.getTeammateToBeDisplayedOnDashboard().get().equals(participation)) {
    //            model.resetTeammateToBeDisplayedOnDashboard();
    //            project.updateTeammateOnView(null);
    //        }
    //
    //        return new CommandResult(String.format(MESSAGE_DELETE_TEAMMATE_SUCCESS, personToDelete));
    //    }
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Participation> lastShownList = project.getTeammates();

        if (!project.hasParticipation(gitUserIndex.getGitUserNameString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_NAME);
        }

        Participation participation = project.getParticipation(gitUserIndex.getGitUserNameString());
        Person personToDelete = Person.getPersonFromList(gitUserIndex);

        //        Project.deleteAllParticipationOf(participation.getPerson().getGitUserNameString());
        //        model.deleteParticipation(participation);
        Participation currentParticipation;
        Project currentProject;
        for (int i = 0; i < Project.getAllProjects().size(); i++) {
            currentProject = Project.getAllProjects().get(i);
            if (currentProject.hasParticipation(personToDelete.getGitUserNameString())) {
                currentParticipation = currentProject.getParticipation(personToDelete.getGitUserNameString());
                currentProject.removeParticipation(currentParticipation);
                if (model.hasParticipation(currentParticipation)) {
                    model.deleteParticipation(currentParticipation);
                }
            }
        }

        model.deletePerson(personToDelete);

        if (model.getTeammateToBeDisplayedOnDashboard().isPresent()
                && model.getTeammateToBeDisplayedOnDashboard().get().equals(participation)) {
            model.resetTeammateToBeDisplayedOnDashboard();
            project.updateTeammateOnView(null);
        }

        return new CommandResult(String.format(MESSAGE_DELETE_TEAMMATE_SUCCESS, personToDelete));
    }


    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTeammateCommand) // instanceof handles nulls
                && gitUserIndex.equals(((DeleteTeammateCommand) other).gitUserIndex); // state check
    }
}
