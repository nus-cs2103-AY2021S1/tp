package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_ALL_MISSIONS;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;

/**
 * Lists all mission deadlines.
 */
public class ViewMissionDeadlineCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed all current mission deadlines";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateMissionsList(PREDICATE_SHOW_ALL_MISSIONS);

        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.Missions);
    }
}
