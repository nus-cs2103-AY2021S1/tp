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
import seedu.address.model.project.Project;

/**
 * Requests to view the details of an existing teammate in the project.
 */
public class ViewTeammateCommand extends Command {

    public static final String COMMAND_WORD = "viewteammate";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the details of the teammate identified "
            + "by the index number used in the displayed teammate list.\n"
            + "example: " + COMMAND_WORD + " LucasTai98 ";

    public static final String MESSAGE_VIEW_TEAMMATE_SUCCESS = "Started TEAMMATE: %1$s";

    private final GitUserIndex gitUserIndex;

    /**
     * @param gitUserIndex of the teammate in the current project to view
     *
     */
    public ViewTeammateCommand(GitUserIndex gitUserIndex) {
        requireNonNull(gitUserIndex);
        this.gitUserIndex = gitUserIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        Project project = model.getProjectToBeDisplayedOnDashboard().get();
        List<Participation> lastShownList = project.getTeammates();

        if (!project.hasParticipation(gitUserIndex.getGitUserNameString())) {
            throw new CommandException(Messages.MESSAGE_INVALID_TEAMMATE_DISPLAYED_NAME);
        }

        Participation teammate = project.getParticipation(gitUserIndex.getGitUserNameString());
        model.enter(teammate);

        return new CommandResult(String.format(MESSAGE_VIEW_TEAMMATE_SUCCESS, teammate));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewTeammateCommand)) {
            return false;
        }

        // state check
        ViewTeammateCommand e = (ViewTeammateCommand) other;
        return gitUserIndex.equals(e.gitUserIndex);
    }
}
