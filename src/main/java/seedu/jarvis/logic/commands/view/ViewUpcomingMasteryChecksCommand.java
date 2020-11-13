package seedu.jarvis.logic.commands.view;

import static java.util.Objects.requireNonNull;
import static seedu.jarvis.model.Model.PREDICATE_SHOW_UPCOMING_MASTERY_CHECKS;

import seedu.jarvis.logic.commands.CommandResult;
import seedu.jarvis.logic.commands.CommandTargetFeature;
import seedu.jarvis.model.Model;

/**
 * Lists only upcoming mastery checks.
 */
public class ViewUpcomingMasteryChecksCommand extends ViewCommand {
    public static final String MESSAGE_SUCCESS = "Listed upcoming mastery check sessions";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateMasteryChecksList(PREDICATE_SHOW_UPCOMING_MASTERY_CHECKS);

        return new CommandResult(MESSAGE_SUCCESS, CommandTargetFeature.MasteryCheck);
    }
}
