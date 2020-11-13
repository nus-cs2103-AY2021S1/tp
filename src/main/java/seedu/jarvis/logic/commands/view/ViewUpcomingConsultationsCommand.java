package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_UPCOMING_CONSULTATIONS;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;

/**
 * View only upcoming consultations.
 */
public class ViewUpcomingConsultationsCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed all upcoming consultations";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateConsultationsList(PREDICATE_SHOW_UPCOMING_CONSULTATIONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.Consultations);
    }
}
