package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_PAST_CONSULTATIONS;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;


/**
 * View only past consultations.
 */
public class ViewPastConsultationsCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed all past consultations";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateConsultationsList(PREDICATE_SHOW_PAST_CONSULTATIONS);
        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.Consultations);
    }
}
