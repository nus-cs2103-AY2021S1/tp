package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;
import seedu.jarvis.model.mission.Mission;

/**
 * Lists all ungraded missions.
 */
public class ViewUngradedMissionCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed all ungraded missions";

    public static final Predicate<Mission> PREDICATE_SHOW_UNGRADED_MISSIONS = mission -> mission.getIsGraded() == false;

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateMissionsList(PREDICATE_SHOW_UNGRADED_MISSIONS);

        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.Missions);
    }
}
